package co.com.aquiles.facturacion.usecase.factura;

import co.com.aquiles.facturacion.domain.factura.Factura;
import co.com.aquiles.facturacion.domain.factura.command.AsignarCliente;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AsignarClienteUseCase extends UseCase<RequestCommand<AsignarCliente>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AsignarCliente> input) {
        var command = input.getCommand();

        var factura = Factura.from(command.facturaId(), retrieveEvents());
        factura.asignarCliente(command.cedula(), command.nombre(), command.direccion());

        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
