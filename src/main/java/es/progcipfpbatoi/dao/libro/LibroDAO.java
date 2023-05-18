package es.progcipfpbatoi.dao.libro;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;

import java.util.ArrayList;

public interface LibroDAO {
    void save(Libro libro);

    void remove(Libro libro);

    ArrayList<Libro> findAll();

    ArrayList<Libro> findAllAvailables();
}
