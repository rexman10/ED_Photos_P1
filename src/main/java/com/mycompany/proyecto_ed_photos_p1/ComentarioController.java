/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_ed_photos_p1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class ComentarioController implements Initializable {

    @FXML
    private TextField txtComentario;
    @FXML
    private Button guardar;
    @FXML
    private Button cancelar;
    
    public static String comment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void guardarComentario(ActionEvent event) {
        String commentActual= txtComentario.getText();
        comment=commentActual;
        cerrarVentana();
        
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }
    
     public void cerrarVentana(){
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
        
    }

}
