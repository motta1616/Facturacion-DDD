package co.com.aquiles.facturacion.usecase;

import co.com.aquiles.facturacion.domain.factura.events.*;
import co.com.aquiles.facturacion.domain.factura.factory.ProductosFactory;
import co.com.aquiles.facturacion.domain.factura.values.*;
import co.com.aquiles.facturacion.usecase.factura.CalcularPagoUseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalcularPagoUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void calcularPago() {

        var facturaId = FacturaId.of("F-aaa");
        var event = createTriggeredEventWith(facturaId);
        var useCase = new CalcularPagoUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(facturaId.toString())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var pagoCalculado = (PagoCalculado) events.get(0);
        var productos = pagoCalculado.getProductos();

        var precioTotal =  productos.entrySet().stream().map(e -> (e.getValue().precio().value())
                * e.getValue().cantidad().value()).reduce(0D, Double::sum);

        Assertions.assertEquals(36000.0, precioTotal);
    }

    private ProductosAsignados createTriggeredEventWith(FacturaId facturaId) {

        var factory = ProductosFactory.builder();
        factory.nuevoProducto(ProductoId.of("P-001"), new Precio(12000.0), new Cantidad(1));
        factory.nuevoProducto(ProductoId.of("P-002"), new Precio(12000.0), new Cantidad(2));

        var event = new ProductosAsignados(facturaId, factory);
        event.setAggregateRootId(facturaId.value());
        return event;
    }

    private List<DomainEvent> eventStored(FacturaId facturaId) {

        var factory = ProductosFactory.builder();
        factory.nuevoProducto(ProductoId.of("P-001"), new Precio(12000.0), new Cantidad(1));
        factory.nuevoProducto(ProductoId.of("P-002"), new Precio(12000.0), new Cantidad(2));

        return List.of(
                new FacturaCreada(facturaId),
                new TiendaAsignada(TiendaId.of("T-aaa"), new Descuento(50.0)),
                new ClienteAsignado(Cedula.of("1234"),new Nombre("LEO"), new Direccion("siempre viva")),
                new ProductosAsignados(facturaId, factory)
        );
    }
}
    

