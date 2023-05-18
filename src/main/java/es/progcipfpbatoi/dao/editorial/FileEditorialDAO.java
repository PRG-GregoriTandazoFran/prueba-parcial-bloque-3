package es.progcipfpbatoi.dao.editorial;

import es.progcipfpbatoi.dto.Editorial;

import java.io.File;
import java.util.ArrayList;

public class FileEditorialDAO implements EditorialDAO {
    private static final String DATABASE_FILE = "resources/database/editorial.txt";
    private final static int    NOMBRE        = 0;
    private final static int    NIF           = 1;

    private static final String FIELD_SEPARATOR = ";";

    private File file;

    public FileEditorialDAO() {
        this.file = new File( DATABASE_FILE );
    }

    @Override
    public void save(Editorial editorial) {

    }

    @Override
    public void remove(Editorial editorial) {

    }

    @Override
    public ArrayList<Editorial> findAll() {
        return null;
    }

    @Override
    public ArrayList<Editorial> findAllAvailables() {
        return null;
    }
}