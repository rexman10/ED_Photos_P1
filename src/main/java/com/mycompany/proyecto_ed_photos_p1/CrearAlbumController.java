/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_ed_photos_p1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.modelo.Album;
import com.mycompany.utilidades.EmptyFieldException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class CrearAlbumController implements Initializable {

    @FXML
    private Label lbTitulo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private Button btnCrearAlbum;
    @FXML
    private Button btnCancelar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void cerrarVentana(){
        Stage stage = (Stage) lbTitulo.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void guardarAlbum() throws IOException {
        if (btnCrearAlbum.isPressed()) {
            System.out.println("presionado guardar");
            
        }
        try{
        String nombre = txtNombre.getText();
        String desc = txtDescripcion.getText();
        Album nuevo = new Album(nombre, desc);

        App.albunes.addLast(nuevo);
        } catch (EmptyFieldException e) {
            App.mostrarAlerta(AlertType.ERROR, "Porfavor asegurese de llenar los campos");
        }
    }

    @FXML
    public void cancelarAlbum(){
        cerrarVentana();
    }
    
}
