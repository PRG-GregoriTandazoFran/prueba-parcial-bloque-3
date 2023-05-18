package es.progcipfpbatoi.dao.editorial;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.dto.LibroAcademico;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;

import java.io.*;
import java.util.ArrayList;

public class FileEditorialDAO implements EditorialDAO {
    private static final String DATABASE_FILE = "resources/database/editoriales.txt";
    private final static int    NIF           = 0;
    private final static int    NOMBRE        = 1;

    private static final String FIELD_SEPARATOR = ";";

    private File file;

    public FileEditorialDAO() {
        this.file = new File( DATABASE_FILE );
    }

    @Override
    public void save(Editorial editorial) throws DatabaseErrorException {
        try {
            if ( findByNif( editorial.getNif() ) == null ) {
                append( editorial );
            } else {
                update( editorial );
            }
        } catch ( IOException ex ) {
            throw new DatabaseErrorException( ex.getMessage() );
        }
    }

    private void append(Editorial editorial) throws IOException {
        try ( BufferedWriter bufferedWriter = getWriter( true ) ) {
            bufferedWriter.write( getRegisterFromEditorial( editorial ) );
            bufferedWriter.newLine();
        }
    }

    private void update(Editorial editorial) {
        updateOrRemove( editorial, true );
    }

    private void updateOrRemove(Editorial editorial, boolean update) {
        ArrayList<Editorial> libroArrayList = findAll();
        try ( BufferedWriter bufferedWriter = getWriter( false ) ) {
            for ( Editorial editorialItem : libroArrayList ) {
                if ( !editorialItem.equals( editorial ) ) {
                    bufferedWriter.write( getRegisterFromEditorial( editorialItem ) );
                    bufferedWriter.newLine();
                } else if ( update ) {
                    bufferedWriter.write( getRegisterFromEditorial( editorial ) );
                    bufferedWriter.newLine();
                }
            }
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
    }

    public String getRegisterFromEditorial(Editorial editorial) {
        return editorial.getNif() + FIELD_SEPARATOR + editorial.getNombre();
    }

    private BufferedWriter getWriter(boolean append) throws IOException {
        return new BufferedWriter( new FileWriter( file, append ) );
    }

    @Override
    public void remove(Editorial editorial) {
        updateOrRemove( editorial, false );
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