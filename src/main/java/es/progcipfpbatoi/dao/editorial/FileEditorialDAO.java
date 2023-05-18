package es.progcipfpbatoi.dao.editorial;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;

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

    @Override
    public Editorial findByNif(String nif) throws DatabaseErrorException {
        try {
            return getByNif( nif );
        } catch ( NotFoundException e ) {
            return null;
        }
    }

    @Override
    public Editorial getByNif(String nif) throws NotFoundException, DatabaseErrorException {
        try ( FileReader fileReader = new FileReader( this.file );
              BufferedReader bufferedReader = new BufferedReader( fileReader ) ) {

            do {
                String register = bufferedReader.readLine();
                if ( register == null ) {
                    throw new NotFoundException( "Editorial no encontrada" );
                } else if ( !register.isBlank() ) {
                    Editorial editorial = getEditorialFromRegister( register );
                    if ( editorial.getNif().equalsIgnoreCase( nif ) ) {
                        return editorial;
                    }
                }
            } while ( true );
        } catch ( IOException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ocurrió un error en el acceso a la base de datos" );
        }
    }

    private BufferedReader getReader() throws IOException {
        return new BufferedReader( new FileReader( file ) );
    }

    private Editorial getEditorialFromRegister(String register) {
        String[] fields = register.split( FIELD_SEPARATOR );
        String   nif    = fields[ NIF ];
        String   nombre = fields[ NOMBRE ];
        return new Editorial( nif, nombre );
    }
}