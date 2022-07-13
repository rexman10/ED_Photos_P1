/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_ed_photos_p1;

import com.mycompany.modelo.Camara;
import com.mycompany.utilidades.List;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class AgregarCamaraController implements Initializable {

    @FXML
    private Label lbTitulo;
    @FXML
    private TextField txtMarca;
    @FXML
    private TextField txtModelo;
    @FXML
    private Button btAgregarCamara;
    @FXML
    private Button btCancelar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void agregarCamara() {
        if (txtMarca.getText().equals("") || txtModelo.getText().equals("")){
            App.mostrarAlerta(Alert.AlertType.ERROR, "Asegurese de llenar los campos");
        }
        else if (!txtMarca.getText().equals("") && txtModelo.getText().equals("")){
            App.mostrarAlerta(Alert.AlertType.ERROR, "Asegurese de llenar los campos");
        }
        else if (txtMarca.getText().equals("") && !txtModelo.getText().equals("")){
            App.mostrarAlerta(Alert.AlertType.ERROR, "Asegurese de llenar los campos");
        }else {
            Camara nueva = new Camara(txtModelo.getText(),txtMarca.getText());
            App.listadoCamaras.addLast(nueva);
            try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream("serializados/listadoCamaras.ser"))) {
                System.out.println("serializando al agregar camaras");
                ou.writeObject(App.listadoCamaras);
            } catch (IOException e) {
                e.getMessage();
            }
            cancelarCamara();                      
        }
    }
    
    @FXML
    public void editarCamara(Camara cam){
        String oldMarca = txtMarca.getText();
        String oldModelo = txtModelo.getText();
            
        //System.out.println(oldMarca);
        //System.out.println(oldModelo);
        btAgregarCamara.setOnAction(e -> {


        
            if (txtMarca.getText().equals(oldMarca) && txtModelo.getText().equals(oldModelo)){
                App.mostrarAlerta(Alert.AlertType.ERROR, "Los datos a modificar son los mismos, Modifiquelos");
            } else {
                cam.setMarca(txtMarca.getText());
                cam.setModelo(txtModelo.getText());
                cancelarCamara();                      
            }
        });
    }

    @FXML
    private void cancelarCamara() {
        Stage stage =(Stage) lbTitulo.getParent().getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void llenarCampos(Camara cam){
        lbTitulo.setText("Editar Camara");
        btAgregarCamara.setText("Editar");
        txtMarca.setText(cam.getMarca());
        txtModelo.setText(cam.getModelo());
    }

}
