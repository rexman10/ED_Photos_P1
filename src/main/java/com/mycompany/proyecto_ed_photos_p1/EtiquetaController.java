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
public class EtiquetaController implements Initializable {


    @FXML
    private Button cancelar;
    @FXML
    private TextField txtEtiqueta;
    
    public static String keyword;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void guardarEtiqueta(ActionEvent event) {
        String etiqueta=txtEtiqueta.getText();
        keyword=etiqueta;
        cerrarVentana();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        cerrarVentana();
    }
    
    public void cerrarVentana(){
        Stage stage = (Stage) cancelar.getScene().getWindow();
        stage.close();
        
    }

}
