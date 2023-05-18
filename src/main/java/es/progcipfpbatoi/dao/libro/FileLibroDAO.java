package es.progcipfpbatoi.dao.libro;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.dto.LibroAcademico;
import es.progcipfpbatoi.dto.NivelEducativo;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.exceptions.NotFoundException;

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
    public static final  String ACADEMICO       = "ACADEMICO";
    public static final  String NO_ACADEMICO    = "NO_ACADEMICO";

    private File file;

    public FileLibroDAO() {
        this.file = new File( DATABASE_FILE );
    }

    @Override
    public void save(Libro libro) throws DatabaseErrorException {
        try {
            if ( findByTitle( libro.getTitulo() ) == null ) {
                append( libro );
            } else {
                update( libro );
            }
        } catch ( IOException ex ) {
            throw new DatabaseErrorException( ex.getMessage() );
        }

    }

    public Libro getByTitle(String title) throws NotFoundException, DatabaseErrorException {
        try ( FileReader fileReader = new FileReader( this.file );
              BufferedReader bufferedReader = new BufferedReader( fileReader ) ) {

            do {
                String register = bufferedReader.readLine();
                if ( register == null ) {
                    throw new NotFoundException( "Libro no encontrado" );
                } else if ( !register.isBlank() ) {
                    Libro libro = getLibroFromRegister( register );
                    if ( libro.getTitulo().equalsIgnoreCase( title ) ) {
                        return libro;
                    }
                }
            } while ( true );
        } catch ( IOException e ) {
            e.printStackTrace();
            throw new DatabaseErrorException( "Ocurrió un error en el acceso a la base de datos" );
        }

    }

    private void append(Libro libro) throws IOException {
        try ( BufferedWriter bufferedWriter = getWriter( true ) ) {
            bufferedWriter.write( getRegisterFromLibro( libro ) );
            bufferedWriter.newLine();
        }
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

    public String getRegisterFromLibro(Libro libro) {
        String register = libro.getTitulo() + FIELD_SEPARATOR + libro.getAutor() + FIELD_SEPARATOR + libro.getFechaPublicacion() + FIELD_SEPARATOR + libro.getEditorial();
        if ( libro.getClass().equals( LibroAcademico.class ) ) {
            return register + FIELD_SEPARATOR + ACADEMICO + FIELD_SEPARATOR + ((LibroAcademico) libro).getNivelEducativo();
        }
        return register + FIELD_SEPARATOR + NO_ACADEMICO;
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

    @Override
    public Libro findByTitle(String title) throws DatabaseErrorException {
        try {
            return getByTitle( title );
        } catch ( NotFoundException ex ) {
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
