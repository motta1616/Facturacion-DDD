package co.com.aquiles.facturacion.usecase.factura.handle;


import co.com.aquiles.facturacion.domain.factura.Factura;
import co.com.aquiles.facturacion.domain.factura.Tienda;
import co.com.aquiles.facturacion.domain.factura.command.CrearFatura;
import co.com.aquiles.facturacion.domain.factura.values.Descuento;
import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.aquiles.facturacion.domain.factura.values.Fecha;
import co.com.aquiles.facturacion.domain.factura.values.TiendaId;
import co.com.aquiles.facturacion.usecase.factura.CrearFacturaUseCase;
import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;

import java.util.Map;


@CommandHandles
@CommandType(name = "facturacion.factura.crearfactura", aggregate = "factura")

public class CrearFacturaHandle extends UseCaseExecutor{
    private RequestCommand<CrearFatura> request;

    @Override
    public void run() {
        runUseCase(new CrearFacturaUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {
        var productoEnvido = Boolean.FALSE;
        var fecha = new Fecha();
        request =  new RequestCommand<>(new CrearFatura( FacturaId.of(aggregateId()), TiendaId.of("T-aaa"), new Descuento(20.0)));
    }


}
