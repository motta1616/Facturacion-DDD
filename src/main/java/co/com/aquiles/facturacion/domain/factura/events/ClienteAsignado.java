package co.com.aquiles.facturacion.domain.factura.events;

import co.com.aquiles.facturacion.domain.factura.values.Cedula;
import co.com.aquiles.facturacion.domain.factura.values.Direccion;
import co.com.aquiles.facturacion.domain.factura.values.Nombre;
import co.com.sofka.domain.generic.DomainEvent;

public class ClienteAsignado extends DomainEvent {
    private final Cedula cedula;
    private final Nombre nombre;
    private final Direccion direccion;

    public ClienteAsignado(Cedula cedula, Nombre nombre, Direccion direccion) {
        super("facturacion.factura.clienteasignado");
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Cedula cedula() {
        return cedula;
    }

    public Nombre nombre() {
        return nombre;
    }

    public Direccion direccion() {
        return direccion;
    }
}
