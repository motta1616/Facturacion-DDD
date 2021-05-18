package co.com.aquiles.facturacion.usecase.factura;

import co.com.aquiles.facturacion.domain.factura.Factura;
import co.com.aquiles.facturacion.domain.factura.events.PagoRealizado;

import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

public class EnvioProductosUseCase extends UseCase<TriggeredEvent<PagoRealizado>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<PagoRealizado> input) {
        var event = input.getDomainEvent();
        var factura = Factura.from(FacturaId.of(event.aggregateRootId()), retrieveEvents());

        factura.envioProductos();

        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
