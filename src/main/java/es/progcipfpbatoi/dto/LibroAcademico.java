package es.progcipfpbatoi.dto;

import java.time.LocalDate;

public class LibroAcademico extends Libro {
    private NivelEducativo nivelEducativo;

    public LibroAcademico(String titulo, String autor, LocalDate fechaPublicacion, Editorial editorial, NivelEducativo nivelEducativo) {
        super( titulo, autor, fechaPublicacion, editorial );
        this.nivelEducativo = nivelEducativo;
    }

    public LibroAcademico(String titulo, String autor, LocalDate fechaPublicacion, NivelEducativo nivelEducativo) {
        super( titulo, autor, fechaPublicacion );
        this.nivelEducativo = nivelEducativo;
    }

    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }
}
