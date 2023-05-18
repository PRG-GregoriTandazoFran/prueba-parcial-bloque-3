package es.progcipfpbatoi.dto;

import java.util.Objects;

public class Editorial {

    private String nif;
    private String nombre;

    public Editorial(String nif, String nombre) {
        this.nif    = nif;
        this.nombre = nombre;
    }

    public Editorial(String nif) {
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Editorial editorial = (Editorial) o;
        return Objects.equals( nif, editorial.nif );
    }

    @Override
    public int hashCode() {
        return Objects.hash( nif );
    }
}