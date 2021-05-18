package co.com.aquiles.facturacion.domain.factura;

import co.com.aquiles.facturacion.domain.factura.values.*;
import co.com.sofka.domain.generic.Entity;

public class Cliente extends Entity<Cedula> {
    private final Nombre nombre;
    private final Direccion direccion;

    public Cliente(Cedula entityId, Nombre nombre, Direccion direccion) {
        super(entityId);
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Nombre nombre() {
        return nombre;
    }

    public Direccion direccion() {
        return direccion;
    }
}
