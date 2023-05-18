package es.progcipfpbatoi.controlador;

import es.progcipfpbatoi.dto.Editorial;
import es.progcipfpbatoi.dto.Libro;
import es.progcipfpbatoi.dto.LibroAcademico;
import es.progcipfpbatoi.dto.NivelEducativo;
import es.progcipfpbatoi.exceptions.DatabaseErrorException;
import es.progcipfpbatoi.repositories.EditorialRepository;
import es.progcipfpbatoi.repositories.LibroRepository;
import es.progcipfpbatoi.util.AlertMessages;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
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
    private TextField        titulo;
    @FXML
    private TextField        autor;
    @FXML
    private ComboBox<String> editorial;

    @FXML
    private void confirmar(ActionEvent event) {
        if ( !isAnyValueInBlank() ) {

            try {
                if ( tipoLibro.getSelectionModel().getSelectedItem().equalsIgnoreCase( "No academico" ) ) {
                    this.libroRepository.save( new Libro( titulo.getText(), autor.getText(), LocalDate.now(), new Editorial( editorial.getSelectionModel().getSelectedItem() ) ) );
                } else {
                    this.libroRepository.save( new LibroAcademico( titulo.getText(), autor.getText(), LocalDate.now(), new Editorial( editorial.getSelectionModel().getSelectedItem() ), NivelEducativo.parse( nivelAcademico.getSelectionModel().getSelectedItem() ) ) );
                }
                AlertMessages.mostrarAlertWarning( "Libro guardado con éxito!" );
                titulo.setText( "" );
                autor.setText( "" );
                tipoLibro.getSelectionModel().clearSelection();
                nivelAcademico.getSelectionModel().clearSelection();
                editorial.getSelectionModel().clearSelection();
            } catch ( DatabaseErrorException e ) {
                throw new RuntimeException( e );
            }
        }
    }


    private boolean isAnyValueInBlank() {
        if ( tipoLibro.getSelectionModel().getSelectedItem() == null ) {
            AlertMessages.mostrarAlertError( "Debes seleccionar un tipo de libro" );
            return true;
        }
        if ( nivelAcademico.getSelectionModel().getSelectedItem() == null ) {
            AlertMessages.mostrarAlertError( "Debes seleccionar un tipo de nivel académico" );
            return true;
        }
        if ( titulo.getText().isBlank() ) {
            AlertMessages.mostrarAlertError( "Tiene que tener un titulo" );
            return true;
        }
        if ( autor.getText().isBlank() ) {
            AlertMessages.mostrarAlertError( "Debes poner un autor " );
            return true;
        }
        if ( editorial.getSelectionModel().getSelectedItem() == null ) {
            AlertMessages.mostrarAlertError( "Debes seleccionar una editorial " );
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tipoLibro.setItems( FXCollections.observableArrayList( "Academico", "No academico" ) );
        nivelAcademico.setItems( FXCollections.observableArrayList( NivelEducativo.getAllValuesInString() ) );
        editorial.setItems( FXCollections.observableArrayList( this.editorialRepository.getAllNifs() ) );
    }
}
