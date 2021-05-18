package co.com.aquiles.facturacion.domain.factura.command;

import co.com.aquiles.facturacion.domain.factura.values.Cedula;
import co.com.aquiles.facturacion.domain.factura.values.Direccion;
import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.aquiles.facturacion.domain.factura.values.Nombre;
import co.com.sofka.domain.generic.Command;

public class AsignarCliente implements Command {
    private final FacturaId facturaId;
    private final Cedula cedula;
    private final Nombre nombre;
    private final Direccion direccion;

    public AsignarCliente(FacturaId facturaId, Cedula cedula, Nombre nombre, Direccion direccion) {
        this.facturaId = facturaId;
        this.cedula = cedula;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public FacturaId facturaId() {
        return facturaId;
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
