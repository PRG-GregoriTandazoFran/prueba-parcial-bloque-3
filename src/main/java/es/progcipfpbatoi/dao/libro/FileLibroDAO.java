package es.progcipfpbatoi.dao.libro;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.dto.NivelEducativo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileLibroDAO implements LibroDAO {
    private static final String DATABASE_FILE     = "resources/database/libros.txt";
    private final static int    TITULO            = 0;
    private final static int    AUTOR             = 1;
    private final static int    FECHA_PUBLICACION = 2;
    private final static int    NIF_EDITORIAL     = 3;
    private final static int    TIPO              = 4;
    private final static int    NIVEL_EDUCATIVO   = 5;

    private final static String NO_ACADEMIC = "NO_ACADEMICO";

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
    public ArrayList<Libro> findAll() {
        try {
            ArrayList<Libro> libroArrayList = new ArrayList<>();
            try ( BufferedReader bufferedReader = getReader() ) {
                do {
                    String register = bufferedReader.readLine();
                    if ( register == null ) {
                        return libroArrayList;
                    }
                    libroArrayList.add( getLibroFromRegister( register ) );
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

    private Libro getLibroFromRegister(String register) {
        String[] fields = register.split( FIELD_SEPARATOR );
        if ( fields[TIPO].equalsIgnoreCase( NO_ACADEMIC ) ) {
            //NO ACADEMICO
            String    titulo    = fields[TITULO];
            String    autor     = fields[AUTOR];
            LocalDate fecha     = LocalDate.parse( fields[FECHA_PUBLICACION], DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) );
            Editorial editorial = new Editorial( fields[NIF_EDITORIAL] );
            return new Libro( titulo, autor, fecha, editorial );
        } else {
            //LIBRO ACADEMICO
            String         titulo         = fields[TITULO];
            String         autor          = fields[AUTOR];
            LocalDate      fecha          = LocalDate.parse( fields[FECHA_PUBLICACION], DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) );
            Editorial      editorial      = new Editorial( fields[NIF_EDITORIAL] );
            NivelEducativo nivelEducativo = NivelEducativo.valueOf( fields[NIVEL_EDUCATIVO] );
        }
return null;

    }


    @Override
    public ArrayList<Libro> findAllAvailables() {
        return null;
    }
}
