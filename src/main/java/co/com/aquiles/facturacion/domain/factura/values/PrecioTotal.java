package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class PrecioTotal implements ValueObject<Double> {
    private final Double PrecioTotal;

    public PrecioTotal(Double precioTotal) {
        PrecioTotal = Objects.requireNonNull(precioTotal);
    }

    @Override
    public Double value() {
        return PrecioTotal;
    }
}
