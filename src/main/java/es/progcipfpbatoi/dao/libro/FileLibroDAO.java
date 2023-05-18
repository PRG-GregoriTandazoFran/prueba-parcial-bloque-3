package es.progcipfpbatoi.dao.libro;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;

import java.io.File;
import java.util.ArrayList;

public class FileLibroDAO implements LibroDAO {
    private static final String DATABASE_FILE     = "resources/database/libros.txt";
    private final static int    TITULO            = 0;
    private final static int    AUTOR             = 2;
    private final static int    FECHA_PUBLICACION = 3;
    private final static int    NIF_EDITORIAL     = 4;
    private final static int    TIPO              = 5;
    private final static int    NIVEL_EDUCATIVO   = 6;

    private static final String FIELD_SEPARATOR = ";";

    private File file;

    public FileLibroDAO() {
        this.file = new File( DATABASE_FILE );
    }

    @Override
    public void save(Libro libro) {

    }

    @Override
    public void remove(Libro libro) {

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
