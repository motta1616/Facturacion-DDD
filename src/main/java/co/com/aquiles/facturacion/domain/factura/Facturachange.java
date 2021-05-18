package co.com.aquiles.facturacion.domain.factura;

import co.com.aquiles.facturacion.domain.factura.events.*;
import co.com.aquiles.facturacion.domain.factura.values.Fecha;
import co.com.aquiles.facturacion.domain.factura.values.PrecioTotal;
import co.com.sofka.domain.generic.EventChange;

import java.util.HashMap;

public class Facturachange extends EventChange {

    public Facturachange(Factura factura) {

        apply((FacturaCreada event) -> {
            factura.productoEnvido = Boolean.FALSE;
            factura.fecha = new Fecha();
            factura.productos = new HashMap<>();
        });

        apply((TiendaAsignada events) -> {
            factura.tienda = new Tienda(
                    events.tiendaId(),
                    events.descuento()
            );
        });

        apply((ClienteAsignado event) -> {
            factura.cliente = new Cliente(
                    event.cedula(),
                    event.nombre(),
                    event.direccion()
            );
        });

        apply((ProductosAsignados event) -> {
            event.factory().productos().forEach(producto -> factura.productos.put(producto.identity(),
                    new Producto(producto.identity(), producto.precio(), producto.cantidad())));
        });

        apply((ProductoEliminado event) -> {
            factura.productos.remove(event.productoId());
        });

        apply((PagoCalculado event) ->{
             var precioTotal =  factura.productos.entrySet().stream().map(e -> (e.getValue().precio().value())
                     * e.getValue().cantidad().value()).reduce(0D, Double::sum);
             factura.precioTotal = new PrecioTotal(precioTotal);
        });

        apply((PagoRealizado event) -> {
            var descuento = (factura.tienda.descuento().value()) / 100 ;
            var precioDescuento = factura.precioTotal.value() * descuento;
            factura.precioTotal =  new PrecioTotal(factura.precioTotal.value() - precioDescuento);
        });

        apply((ProductosEnviado event) -> {
            factura.productoEnvido = Boolean.TRUE;
        });
    }
}
