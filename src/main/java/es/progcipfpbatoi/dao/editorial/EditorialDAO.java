package es.progcipfpbatoi.dao.editorial;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;

import java.util.ArrayList;

public interface EditorialDAO {
    void save(Editorial editorial) throws DatabaseErrorException ;

    void remove(Editorial editorial);

    ArrayList<Editorial> findAll();

    Editorial findByNif(String nif) throws DatabaseErrorException;

    Editorial getByNif(String nif) throws NotFoundException, DatabaseErrorException;
}
