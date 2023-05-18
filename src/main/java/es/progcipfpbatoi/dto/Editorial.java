package es.progcipfpbatoi.dto;

import java.util.ArrayList;

public class Editorial {
    private String           nombre;
    private String           nif;
    private ArrayList<Libro> libroArrayList;

    public Editorial(String nombre, String nif) {
        this.nombre         = nombre;
        this.nif            = nif;
        this.libroArrayList = new ArrayList<>();
    }

    public void setLibroArrayList(ArrayList<Libro> libroArrayList) {
        this.libroArrayList = libroArrayList;
    }
}