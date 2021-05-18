package co.com.aquiles.facturacion.usecase.factura;


import co.com.aquiles.facturacion.domain.factura.Factura;
import co.com.aquiles.facturacion.domain.factura.Tienda;
import co.com.aquiles.facturacion.domain.factura.command.CrearFatura;
import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;

public class CrearFacturaUseCase extends UseCase<RequestCommand<CrearFatura>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearFatura> input) {
        var command = input.getCommand();
        var facturaId = new FacturaId();
        var tienda = new Tienda(command.TiendaId(), command.descuento());

        var factura = new Factura(facturaId, tienda);
        emit().onResponse(new ResponseEvents(factura.getUncommittedChanges()));
    }
}
