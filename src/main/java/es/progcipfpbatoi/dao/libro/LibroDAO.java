package es.progcipfpbatoi.dao.libro;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;

import java.util.ArrayList;

public interface LibroDAO {
    void save(Libro libro) throws DatabaseErrorException;

    void remove(Libro libro);

    ArrayList<Libro> findAll();

    Libro findByTitle(String title) throws DatabaseErrorException;

    Libro getByTitle(String title) throws NotFoundException, DatabaseErrorException;
}
