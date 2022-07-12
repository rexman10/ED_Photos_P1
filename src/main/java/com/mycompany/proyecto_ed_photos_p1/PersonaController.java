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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class PersonaController implements Initializable {


    @FXML
    private Label titulo;
    @FXML
    private TextField nombreP;
    @FXML
    private Button guardar;
    @FXML
    private Button cancelar;

    
    public static String persona;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void guardarPersona() {
        String personActual= nombreP.getText();
        persona=personActual;
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
    
    public void llenarCampos(String persona){
        System.out.println("LLENAR CAMPOS DE EDITAR DE LA PERSONA: "+persona);
        titulo.setText("Editar Persona");
        nombreP.setText(persona);
        
        //NUEVA FUNCION DEL BOTON GUARDAR EN EDITAR IMAGEN
        /*guardar.setOnAction(event -> { 
            
        });*/
    }

}
