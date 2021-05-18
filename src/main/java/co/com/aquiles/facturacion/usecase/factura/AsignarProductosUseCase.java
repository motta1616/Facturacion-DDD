package co.com.aquiles.facturacion.usecase.factura;

import co.com.aquiles.facturacion.domain.factura.Factura;
import co.com.aquiles.facturacion.domain.factura.command.AsignarProductos;
import co.com.aquiles.facturacion.domain.factura.factory.ProductosFactory;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class AsignarProductosUseCase extends UseCase<RequestCommand<AsignarProductos>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AsignarProductos> input) {
        var command = input.getCommand();

        var factory = ProductosFactory.builder();
        command.precios().forEach((productoId, precio) ->
                factory.nuevoProducto(productoId, precio, command.cantidades().get(productoId)));


        var factura = Factura.from(command.facturaId(), retrieveEvents());
        factura.asignarProductos(command.facturaId(), factory);
        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
