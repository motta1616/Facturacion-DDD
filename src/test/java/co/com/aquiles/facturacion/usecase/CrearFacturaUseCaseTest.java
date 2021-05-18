package co.com.aquiles.facturacion.usecase;

import co.com.aquiles.facturacion.domain.factura.command.CrearFatura;
import co.com.aquiles.facturacion.domain.factura.events.FacturaCreada;
import co.com.aquiles.facturacion.domain.factura.events.TiendaAsignada;
import co.com.aquiles.facturacion.domain.factura.values.Descuento;
import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.aquiles.facturacion.domain.factura.values.TiendaId;
import co.com.aquiles.facturacion.usecase.factura.CrearFacturaUseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Objects;


class CrearFacturaUseCaseTest {

    @Test
    void crearUnaFactura() {

        var command = new CrearFatura(FacturaId.of("F-aaa"), TiendaId.of("T-aaa"), new Descuento(50.0));
        var usecase = new CrearFacturaUseCase();

        var events = UseCaseHandler.getInstance()
                .syncExecutor(usecase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var facturaCreada = (FacturaCreada) events.get(0);
        var tiendaasignada = (TiendaAsignada) events.get(1);

        Assertions.assertTrue(Objects.nonNull(facturaCreada.facturaId().value()));
        Assertions.assertEquals("T-aaa", tiendaasignada.tiendaId().value());
        Assertions.assertEquals(50.0, tiendaasignada.descuento().value());
    }

}