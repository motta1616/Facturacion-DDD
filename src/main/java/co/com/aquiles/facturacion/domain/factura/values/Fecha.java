package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.ValueObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fecha implements ValueObject<String> {
    private final String fecha;

    public Fecha() {
        LocalDateTime fechaHora = LocalDateTime.now();
        String patron = "yyyy/MM/dd hh:mm:ss";
        this.fecha = fechaHora.format(DateTimeFormatter.ofPattern(patron));
    }

    @Override
    public String value() {
        return fecha;
    }
}
