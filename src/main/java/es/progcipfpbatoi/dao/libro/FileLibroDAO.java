package es.progcipfpbatoi.dao.libro;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.dto.LibroAcademico;
import es.progcipfpbatoi.dto.NivelEducativo;

import java.io.*;
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
        updateOrRemove( libro, false );
    }

    private void update(Libro libro) {
        updateOrRemove( libro, true );
    }

    private void updateOrRemove(Libro tarea, boolean update) {
        ArrayList<Libro> libroArrayList = findAll();
        try ( BufferedWriter bufferedWriter = getWriter( false ) ) {
            for ( Libro libroItem : libroArrayList ) {
                if ( !libroItem.equals( tarea ) ) {
                    bufferedWriter.write( getRegisterFromLibro( libroItem ) );
                    bufferedWriter.newLine();
                } else if ( update ) {
                    bufferedWriter.write( getRegisterFromLibro( tarea ) );
                    bufferedWriter.newLine();
                }
            }
        } catch ( IOException ex ) {
            ex.printStackTrace();
        }
    }

    private BufferedWriter getWriter(boolean append) throws IOException {
        return new BufferedWriter( new FileWriter( file, append ) );
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
        String[]  fields    = register.split( FIELD_SEPARATOR );
        String    titulo    = fields[ TITULO ];
        String    autor     = fields[ AUTOR ];
        LocalDate fecha     = LocalDate.parse( fields[ FECHA_PUBLICACION ], DateTimeFormatter.ofPattern( "yyyy-MM-dd" ) );
        Editorial editorial = new Editorial( fields[ NIF_EDITORIAL ] );
        if ( fields[ TIPO ].equalsIgnoreCase( NO_ACADEMIC ) ) {
            //NO ACADEMICO
            return new Libro( titulo, autor, fecha, editorial );
        }
        //LIBRO ACADEMICO
        NivelEducativo nivelEducativo = NivelEducativo.parse( fields[ NIVEL_EDUCATIVO ] );
        return new LibroAcademico( titulo, autor, fecha, editorial, nivelEducativo );
    }
}
