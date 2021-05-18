package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Direccion implements ValueObject<String> {
    private final String direccion;

    public Direccion(String direccion) {
        this.direccion = Objects.requireNonNull(direccion);
        if (direccion.isBlank()) {
            throw new IllegalArgumentException("La dirección no puede esta vacio");
        }
    }

    @Override
    public String value() {
        return direccion;
    }
}
