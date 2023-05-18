package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.repositories.LibroRepository;
import es.progcipfpbatoi.util.AlertMessages;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {

    private LibroRepository libroRepository;

    public PrincipalController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @FXML
    private ListView<Libro> listViewLibros;

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
