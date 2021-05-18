package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Descuento implements ValueObject<Double> {
    private final Double descuento;

    public Descuento(Double descuento) {
        if (descuento < 0) {
            throw new IllegalArgumentException("No puede ser un valor negativo");
        }
        this.descuento = Objects.requireNonNull(descuento);
    }

    public Descuento agregarDescuento(Double descuento) {
        return new Descuento(descuento);
    }


    @Override
    public Double value() {
        return descuento;
    }
}
