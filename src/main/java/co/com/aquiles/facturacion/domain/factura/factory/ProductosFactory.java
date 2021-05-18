package co.com.aquiles.facturacion.domain.factura.factory;

import co.com.aquiles.facturacion.domain.factura.Producto;
import co.com.aquiles.facturacion.domain.factura.values.Cantidad;
import co.com.aquiles.facturacion.domain.factura.values.Precio;
import co.com.aquiles.facturacion.domain.factura.values.ProductoId;

import java.util.HashSet;
import java.util.Set;

public class ProductosFactory {
    private Set<Producto> productos;

    private ProductosFactory() {
        productos = new HashSet<>();
    }

    public static ProductosFactory builder() {
        return new ProductosFactory();
    }

    public ProductosFactory nuevoProducto(ProductoId productoId, Precio precio, Cantidad cantidad) {
        productos.add(new Producto(productoId, precio, cantidad));
        return this;
    }

    public Set<Producto> productos() {
        return productos;
    }
}
