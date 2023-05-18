package es.progcipfpbatoi.dao.editorial;

import es.progcipfpbatoi.dto.Editorial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileEditorialDAO implements EditorialDAO {
    private static final String DATABASE_FILE = "resources/database/editorial.txt";
    private final static int    NIF           = 0;
    private final static int    NOMBRE        = 1;

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
        try {
            ArrayList<Editorial> editorialArrayList = new ArrayList<>();
            try ( BufferedReader bufferedReader = getReader() ) {
                do {
                    String register = bufferedReader.readLine();
                    if ( register == null ) {
                        return editorialArrayList;
                    }
                    editorialArrayList.add( getEditorialFromRegister( register ) );
                } while ( true );
            }
        } catch ( IOException ex ) {
            System.out.println( ex.getMessage() );
            return null;
        }
    }

    private BufferedReader getReader() throws IOException {
        return new BufferedReader( new FileReader( file ) );
    }

    private Editorial getEditorialFromRegister(String register) {
        String[] fields = register.split( FIELD_SEPARATOR );
        String   nombre = fields[NIF];
        String   nif    = fields[NOMBRE];
        return new Editorial( nif, nombre );
    }

    @Override
    public ArrayList<Editorial> findAllAvailables() {
        return null;
    }
}