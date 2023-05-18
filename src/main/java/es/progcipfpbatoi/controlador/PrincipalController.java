package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.repositories.EditorialRepository;
import es.progcipfpbatoi.repositories.LibroRepository;
import es.progcipfpbatoi.util.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.AccessibleAction;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    private LibroRepository     libroRepository;
    private EditorialRepository editorialRepository;

    public PrincipalController(LibroRepository libroRepository, EditorialRepository editorialRepository) {
        this.libroRepository     = libroRepository;
        this.editorialRepository = editorialRepository;
    }

    @FXML
    private ListView<Libro> listViewLibros;

    @FXML
    private void eliminar(MouseEvent event) {
        if ( listViewLibros.getSelectionModel().getSelectedItem() == null ) {
            AlertMessages.mostrarAlertError( "Tienes que escoger un libro" );
        }
        Libro libroSelected = listViewLibros.getSelectionModel().getSelectedItem();
        this.libroRepository.remove( libroSelected );
        listViewLibros.setItems( getData() );
    }

    @FXML
    private void goNewBook(ActionEvent event) {
        try {
            NewBookController newBookController = new NewBookController( this.libroRepository, this.editorialRepository );
            ChangeScene.change( event, newBookController, "/vistas/new_book.fxml" );
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
    }

    private ObservableList<Libro> getData() {
        try {
            return FXCollections.observableArrayList( libroRepository.findAllWithEditoriales() );
        } catch ( DatabaseErrorException ex ) {
            AlertMessages.mostrarAlertError( ex.getMessage() );
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewLibros.setItems( getData() );
    }
}
