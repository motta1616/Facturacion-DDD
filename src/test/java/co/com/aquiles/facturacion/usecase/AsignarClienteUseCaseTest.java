package co.com.aquiles.facturacion.usecase;

import co.com.aquiles.facturacion.domain.factura.command.AsignarCliente;
import co.com.aquiles.facturacion.domain.factura.events.ClienteAsignado;
import co.com.aquiles.facturacion.domain.factura.events.FacturaCreada;
import co.com.aquiles.facturacion.domain.factura.events.TiendaAsignada;
import co.com.aquiles.facturacion.domain.factura.values.*;
import co.com.aquiles.facturacion.usecase.factura.AsignarClienteUseCase;
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

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AsignarClienteUseCaseTest {

     @Mock
     private DomainEventRepository repository;

    @Test
    void AgregarUnCliente() {

        var facturaId = FacturaId.of("F-aaa");
        var command = new AsignarCliente(facturaId, new Cedula("12345"), new Nombre("leo"), new Direccion("avenida viva"));
        var useCase = new AsignarClienteUseCase();

        when(repository.getEventsBy(facturaId.value())).thenReturn(eventStored(facturaId));
        useCase.addRepository(repository);

        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(facturaId.value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var clienteAsignado = (ClienteAsignado) events.get(0);
        Assertions.assertEquals("12345",clienteAsignado.cedula().value());
        Assertions.assertEquals("leo",clienteAsignado.nombre().value());
        Assertions.assertEquals("avenida viva",clienteAsignado.direccion().value());
    }

    private List<DomainEvent> eventStored(FacturaId facturaId) {
        return List.of(
                new FacturaCreada(facturaId),
                new TiendaAsignada(TiendaId.of("T-aaa"), new Descuento(50.0))
        );
    }
}