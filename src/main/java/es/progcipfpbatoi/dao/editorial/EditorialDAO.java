package es.progcipfpbatoi.dao.editorial;

import es.progcipfpbatoi.dto.Editorial;

import java.util.ArrayList;

public interface EditorialDAO {
    void save(Editorial editorial);

    void remove(Editorial editorial);

    ArrayList<Editorial> findAll();

    ArrayList<Editorial> findAllAvailables();
}
