package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Precio implements ValueObject<Double> {
    private final Double precio;

    public Precio(Double precio) {
        if (precio <= 0) {
            new IllegalArgumentException("el precio nopuede ser menor a o igual a 0");
        }
        this.precio = Objects.requireNonNull(precio);
    }

    @Override
    public Double value() {
        return precio;
    }
}
