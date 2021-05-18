package co.com.aquiles.facturacion.usecase.factura;

import co.com.aquiles.facturacion.domain.factura.Factura;
import co.com.aquiles.facturacion.domain.factura.command.RealizarPago;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class RealizarPagoUseCase extends UseCase<RequestCommand<RealizarPago>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<RealizarPago> input) {
        var command = input.getCommand();
        var factura = Factura.from(command.facturaId(), retrieveEvents());

        factura.realizarPago();

        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
