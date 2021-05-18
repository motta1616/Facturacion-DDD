package co.com.aquiles.facturacion.usecase;

import co.com.aquiles.facturacion.domain.factura.events.*;
import co.com.aquiles.facturacion.domain.factura.factory.ProductosFactory;
import co.com.aquiles.facturacion.domain.factura.values.*;
import co.com.aquiles.facturacion.usecase.factura.EnvioProductosUseCase;
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
import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnvioProductosUseCaseTest {

    @Mock
    private DomainEventRepository repository;

    @Test
    void productosEnviado() {
        var facturaId = FacturaId.of("F-aaa");
        var event = createTriggeredEventWith(facturaId);
        var useCase = new EnvioProductosUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId));
        useCase.addRepository(repository);

        var events = UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(facturaId.toString())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();

        var productosEnviado = (ProductosEnviado) events.get(0);
        Assertions.assertTrue(Objects.nonNull(productosEnviado));
    }

    private PagoRealizado createTriggeredEventWith(FacturaId facturaId) {

        var event = new PagoRealizado(20.0, new PrecioTotal(20.0));
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