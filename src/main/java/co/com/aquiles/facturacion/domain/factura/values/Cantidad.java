package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Cantidad implements ValueObject<Integer> {
    private final Integer Cantidad;

    public Cantidad(Integer cantidad) {
        if (cantidad < 1) {
            throw new IllegalArgumentException("debes tener al menos un producto");
        }
        Cantidad = Objects.requireNonNull(cantidad);
    }

    @Override
    public Integer value() {
        return Cantidad;
    }
}
