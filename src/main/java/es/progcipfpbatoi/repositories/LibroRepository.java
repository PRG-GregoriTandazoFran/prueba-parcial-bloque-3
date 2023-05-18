package es.progcipfpbatoi.repositories;

import es.progcipfpbatoi.dao.libro.LibroDAO;
import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;

import java.util.ArrayList;

public class LibroRepository {
    private LibroDAO libroDAO;

    public LibroRepository(LibroDAO libroDAO) {
        this.libroDAO = libroDAO;
    }

    public void save(Libro libro) throws DatabaseErrorException {
        this.libroDAO.save( libro );
    }

    public void remove(Libro libro) {
        this.libroDAO.remove( libro );
    }

    public ArrayList<Libro> findAll() {
        return this.libroDAO.findAll();
    }

    public Libro findByTitle(String title) throws DatabaseErrorException {
        return this.libroDAO.findByTitle( title );
    }

    public Libro getByTitle(String title) throws NotFoundException, DatabaseErrorException {
        return this.libroDAO.getByTitle( title );
    }
}
