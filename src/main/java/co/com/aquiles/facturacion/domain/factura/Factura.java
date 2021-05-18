package co.com.aquiles.facturacion.domain.factura;

import co.com.aquiles.facturacion.domain.factura.events.*;
import co.com.aquiles.facturacion.domain.factura.factory.ProductosFactory;
import co.com.aquiles.facturacion.domain.factura.values.*;
import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;

public class Factura extends AggregateEvent<FacturaId> {
    protected Boolean productoEnvido;
    protected Cliente cliente;
    protected Tienda tienda;
    protected Map<ProductoId, Producto> productos;
    protected Fecha fecha;
    protected PrecioTotal precioTotal;

    public Factura(FacturaId entityId, Tienda tienda) {
        super(entityId);
        this.tienda = tienda;
        appendChange(new FacturaCreada(entityId)).apply();
        asignarTienda(tienda.identity(), tienda.descuento());
    }

    private Factura(FacturaId entityId) {
        super(entityId);
        subscribe(new Facturachange(this));
    }

    public static Factura from(FacturaId entityId, List<DomainEvent> event) {
        var factura = new Factura(entityId);
        event.forEach(factura::applyEvent);
        return factura;
    }

    public void asignarTienda(TiendaId tiendaId, Descuento descuento) {
        appendChange(new TiendaAsignada(tiendaId, descuento)).apply();
    }

    public void asignarCliente(Cedula cedula, Nombre nombre, Direccion direccion) {
        appendChange(new ClienteAsignado(cedula, nombre, direccion)).apply();
    }

    public void asignarProductos(FacturaId facturaId, ProductosFactory factory) {
        appendChange(new ProductosAsignados(facturaId, factory)).apply();

    }

    public void eliminarProducto(FacturaId facturaId, ProductoId productoId) {
        appendChange(new ProductoEliminado(facturaId, productoId)).apply();
    }

    public void calcularPago() {
        appendChange(new PagoCalculado(productos)).apply();
    }

    public void realizarPago() {
        appendChange(new PagoRealizado(tienda.descuento().value(), precioTotal)).apply();
    }

    public void envioProductos() {
        appendChange(new ProductosEnviado()).apply();
    }
}
