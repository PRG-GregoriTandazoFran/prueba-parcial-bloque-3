package es.progcipfpbatoi.dto;

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
}