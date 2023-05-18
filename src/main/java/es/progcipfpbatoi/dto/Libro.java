package es.progcipfpbatoi.dto;

import java.time.LocalDate;

public class Libro {
    private String    titulo;
    private String    autor;
    private LocalDate fechaPublicacion;
    private Editorial editorial;

    public Libro(String titulo, String autor, LocalDate fechaPublicacion, Editorial editorial) {
        this( titulo, autor, fechaPublicacion );
        this.editorial = editorial;
    }

    public Libro(String titulo, String autor, LocalDate fechaPublicacion) {
        this.titulo           = titulo;
        this.autor            = autor;
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }
}


