package co.com.aquiles.facturacion.usecase.factura;

import co.com.aquiles.facturacion.domain.factura.Factura;
import co.com.aquiles.facturacion.domain.factura.events.ProductosAsignados;
import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;

public class CalcularPagoUseCase extends UseCase<TriggeredEvent<ProductosAsignados>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<ProductosAsignados> input) {
        var event = input.getDomainEvent();
        var factura = Factura.from(FacturaId.of(event.aggregateRootId()), retrieveEvents());

        factura.calcularPago();

        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
