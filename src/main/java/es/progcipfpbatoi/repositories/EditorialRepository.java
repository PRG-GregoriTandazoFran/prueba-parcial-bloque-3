package es.progcipfpbatoi.repositories;

import es.progcipfpbatoi.dao.editorial.EditorialDAO;
import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;

import java.util.ArrayList;

public class EditorialRepository {
    private EditorialDAO editorialDAO;

    public EditorialRepository(EditorialDAO editorialDAO) {
        this.editorialDAO = editorialDAO;
    }


    public void save(Editorial editorial) {
        this.editorialDAO.save( editorial );
    }

    public void remove(Editorial editorial) {
        this.remove( editorial );
    }

    public ArrayList<Editorial> findAll() {
        return this.editorialDAO.findAll();
    }

    public Editorial findByNif(String nif) throws DatabaseErrorException {
        return this.editorialDAO.findByNif( nif );
    }

    public Editorial getByNif(String nif) throws NotFoundException, DatabaseErrorException {
        return this.editorialDAO.getByNif( nif );
    }
}
