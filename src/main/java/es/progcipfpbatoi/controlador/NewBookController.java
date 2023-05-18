package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.dto.NivelEducativo;
import es.progcipfpbatoi.repositories.EditorialRepository;
import es.progcipfpbatoi.repositories.LibroRepository;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewBookController implements Initializable {

    private LibroRepository     libroRepository;
    private EditorialRepository editorialRepository;

    public NewBookController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public NewBookController(LibroRepository libroRepository, EditorialRepository editorialRepository) {
        this.libroRepository     = libroRepository;
        this.editorialRepository = editorialRepository;
    }

    @FXML
    private ComboBox<String> tipoLibro;
    @FXML
    private ComboBox<String> nivelAcademico;
    @FXML
    private TextField        nombre;
    @FXML
    private ComboBox<String> editorial;

    @FXML
    private void confirmar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoLibro.setItems( FXCollections.observableArrayList( "Academico", "No academico" ) );
        nivelAcademico.setItems( FXCollections.observableArrayList( "1","2" ) );
        editorial.setItems( FXCollections.observableArrayList( "1", "2" ) );
    }
}
