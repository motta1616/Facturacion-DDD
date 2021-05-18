package co.com.aquiles.facturacion.usecase;

import co.com.aquiles.facturacion.domain.factura.command.AsignarProductos;
import co.com.aquiles.facturacion.domain.factura.events.ClienteAsignado;
import co.com.aquiles.facturacion.domain.factura.events.FacturaCreada;
import co.com.aquiles.facturacion.domain.factura.events.ProductosAsignados;
import co.com.aquiles.facturacion.domain.factura.events.TiendaAsignada;
import co.com.aquiles.facturacion.domain.factura.values.*;
import co.com.aquiles.facturacion.usecase.factura.AsignarProductosUseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AsignarProductosUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void asignarProductos() {

        var facturaid = FacturaId.of("F-aaa");
        var precios = Map.of(
                ProductoId.of("P-001"), new Precio(12000.0),
                ProductoId.of("P-002"), new Precio(12000.0)
        );

        var cantidades = Map.of(
                ProductoId.of("P-001"), new Cantidad(1),
                ProductoId.of("P-002"), new Cantidad(1)
        );

        var command = new AsignarProductos(facturaid, precios, cantidades);
        var useCase = new AsignarProductosUseCase();

        when(repository.getEventsBy(facturaid.value())).thenReturn(eventStored(facturaid));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaid.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var productosAsignados = (ProductosAsignados) events.get(0);
        Assertions.assertEquals(2, productosAsignados.factory().productos().size());
    }


    private List<DomainEvent> eventStored(FacturaId facturaId) {
        return List.of(
                new FacturaCreada(facturaId),
                new TiendaAsignada(TiendaId.of("T-aaa"), new Descuento(50.0)),
                new ClienteAsignado(Cedula.of("1234"), new Nombre("leo"), new Direccion("ddd"))
        );
    }
}