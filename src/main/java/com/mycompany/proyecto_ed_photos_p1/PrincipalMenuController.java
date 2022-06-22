/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_ed_photos_p1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class PrincipalMenuController implements Initializable {

    @FXML
    private ImageView ivFullScreen;
    @FXML
    private Button btnPrevPhoto;
    @FXML
    private Button btnNextPhoto;
    @FXML
    private Button btnExitFullScreen;
    @FXML
    private Label lbPhotoName;
    @FXML
    private RadioButton rbGridView;
    @FXML
    private ToggleGroup vista;
    @FXML
    private RadioButton rbFullView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ivFullScreen.setVisible(false);
        rbGridView.setVisible(true);
        
    }    
    
}
