package co.com.aquiles.facturacion.domain.factura.command;

import co.com.aquiles.facturacion.domain.factura.values.Cantidad;
import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.aquiles.facturacion.domain.factura.values.Precio;
import co.com.aquiles.facturacion.domain.factura.values.ProductoId;
import co.com.sofka.domain.generic.Command;

import java.util.Map;

public class AsignarProductos implements Command {
    private final FacturaId facturaId;
    private final Map<ProductoId, Precio> precios;
    private final Map<ProductoId, Cantidad> cantidades;

    public AsignarProductos(FacturaId facturaId, Map<ProductoId, Precio> precios, Map<ProductoId, Cantidad> cantidades) {
        this.facturaId = facturaId;
        this.precios = precios;
        this.cantidades = cantidades;
    }

    public FacturaId facturaId() {
        return facturaId;
    }

    public Map<ProductoId, Precio> precios() {
        return precios;
    }

    public Map<ProductoId, Cantidad> cantidades() {
        return cantidades;
    }
}
