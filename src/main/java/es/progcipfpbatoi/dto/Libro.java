package es.progcipfpbatoi.dto;

import java.time.LocalDate;
import java.util.Objects;

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

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Libro libro = (Libro) o;
        return Objects.equals( titulo, libro.titulo );
    }

    @Override
    public int hashCode() {
        return Objects.hash( titulo );
    }

    @Override
    public String toString() {
        return titulo + '\'' +
                " | " + autor + '\'' +
                " | " + fechaPublicacion +
                " | " + editorial;
    }
}


