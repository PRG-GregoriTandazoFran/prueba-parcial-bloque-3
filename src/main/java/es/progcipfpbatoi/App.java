package es.progcipfpbatoi;

import es.progcipfpbatoi.controlador.ChangeScene;
import es.progcipfpbatoi.controlador.PrincipalController;
import es.progcipfpbatoi.dao.editorial.EditorialDAO;
import es.progcipfpbatoi.dao.editorial.FileEditorialDAO;
import es.progcipfpbatoi.dao.libro.FileLibroDAO;
import es.progcipfpbatoi.repositories.EditorialRepository;
import es.progcipfpbatoi.repositories.LibroRepository;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FileLibroDAO        fileLibroDAO        = new FileLibroDAO();
        FileEditorialDAO    fileEditorialDAO    = new FileEditorialDAO();
        LibroRepository     libroRepository     = new LibroRepository( fileLibroDAO, fileEditorialDAO );
        EditorialRepository editorialRepository = new EditorialRepository( fileEditorialDAO );
        PrincipalController principalController = new PrincipalController( libroRepository, editorialRepository );
        // Muestra de la escena principal.
        ChangeScene.change( stage, principalController, "/vistas/principal.fxml" );
    }

    public static void main(String[] args) {
        launch();
    }
}