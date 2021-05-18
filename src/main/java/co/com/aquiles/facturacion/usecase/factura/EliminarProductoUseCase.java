package co.com.aquiles.facturacion.usecase.factura;

import co.com.aquiles.facturacion.domain.factura.Factura;
import co.com.aquiles.facturacion.domain.factura.command.EliminarProducto;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class EliminarProductoUseCase extends UseCase<RequestCommand<EliminarProducto>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<EliminarProducto> input) {
        var command = input.getCommand();

        var factura = Factura.from(command.facturaId(), retrieveEvents());
        factura.eliminarProducto(command.facturaId(), command.productoId());
        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
