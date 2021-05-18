package co.com.aquiles.facturacion.domain.factura.command;

import co.com.aquiles.facturacion.domain.factura.values.FacturaId;
import co.com.sofka.domain.generic.Command;

public class RealizarPago implements Command {
    private final FacturaId facturaId;

    public RealizarPago(FacturaId facturaId) {
        this.facturaId = facturaId;
    }

    public FacturaId facturaId() {
        return facturaId;
    }
}
