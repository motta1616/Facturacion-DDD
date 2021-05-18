package co.com.aquiles.facturacion.domain.factura.values;

import co.com.sofka.domain.generic.Identity;

public class Cedula extends Identity {


    public Cedula(String cedula) {
        super(cedula);
    }

    public static Cedula of(String uid) {
        return new Cedula(uid);
    }

}
