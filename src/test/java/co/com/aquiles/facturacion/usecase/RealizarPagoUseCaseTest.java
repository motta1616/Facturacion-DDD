package co.com.aquiles.facturacion.usecase;

import co.com.aquiles.facturacion.domain.factura.Producto;
import co.com.aquiles.facturacion.domain.factura.command.RealizarPago;
import co.com.aquiles.facturacion.domain.factura.events.*;
import co.com.aquiles.facturacion.domain.factura.factory.ProductosFactory;
import co.com.aquiles.facturacion.domain.factura.values.*;
import co.com.aquiles.facturacion.usecase.factura.RealizarPagoUseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RealizarPagoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void RealizarPago() {

        var facturaId = FacturaId.of("F-aaa");
        var command = new RealizarPago(facturaId);
        var useCase = new RealizarPagoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();


        var pagoRealizado = (PagoRealizado) events.get(0);

        var descuento = (pagoRealizado.descuento()) / 100 ;
        var precioDescuento = pagoRealizado.precioTotal().value() * descuento;
        var precioTotal = (pagoRealizado.precioTotal().value() - precioDescuento);

        Assertions.assertEquals(18000.0, precioTotal);
    }

    private List<DomainEvent> eventStored(FacturaId facturaId) {

        var factory = ProductosFactory.builder();
        factory.nuevoProducto(ProductoId.of("P-001"), new Precio(12000.0), new Cantidad(1));
        factory.nuevoProducto(ProductoId.of("P-002"), new Precio(12000.0), new Cantidad(2));

        Map<ProductoId, Producto> productos = new HashMap<>();
        productos.put(ProductoId.of("P-001"),
                new Producto(ProductoId.of("P-001"), new Precio(12000.0), new Cantidad(1)));
        productos.put(ProductoId.of("P-002"),
                new Producto(ProductoId.of("P-002"), new Precio(12000.0), new Cantidad(2)));

        return List.of(
                new FacturaCreada(facturaId),
                new TiendaAsignada(TiendaId.of("T-aaa"), new Descuento(50.0)),
                new ClienteAsignado(Cedula.of("1234"),new Nombre("LEO"), new Direccion("siempre viva")),
                new ProductosAsignados(facturaId, factory),
                new PagoCalculado(productos)
        );
    }
}