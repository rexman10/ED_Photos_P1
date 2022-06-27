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
import com.mycompany.modelo.Imagen;
import com.mycompany.utilidades.ArrayList;
import com.mycompany.utilidades.EmptyFieldException;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    @FXML
    private Button brnAgregarImg;
    @FXML
    private Button btnEditarImg;
    @FXML
    private Button btnEliminarImg;
    @FXML
    private Button btnNuevoAlbun;
    @FXML
    private Button btnEliminarAlbun;
    @FXML
    private Button btnEliminarAlbum;
    @FXML
    private Pane pFullview;
    @FXML
    private FlowPane fpGridView;
    @FXML
    private Label lbEtiquetas;
    @FXML
    private ToggleGroup reaction;
    @FXML
    private ComboBox<Album> cbAlbum;
    @FXML
    private Label lbLugar;
    @FXML
    private RadioButton rbLike;
    @FXML
    private RadioButton ebLove;
    @FXML
    private RadioButton rbSad;
    @FXML
    private Label lbPersonas;
    @FXML
    private Label lbCamara;
    @FXML
    private HBox hbGridContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarDefault();
        actualizarAlbumes();
        mostrarAlbum();
        if (rbGridView.isSelected()) {
            fpGridView.setVisible(true);
            hbGridContent.setVisible(true);
            pFullview.setVisible(false);
        } else {
            hbGridContent.setVisible(false);
            fpGridView.setVisible(false);
            pFullview.setVisible(true);
        }
        
    }    

    @FXML
    private void setVistaGrid(ActionEvent event) {
        fpGridView.setVisible(true);
        pFullview.setVisible(false);
        vista.selectToggle(rbGridView);
    }

    @FXML
    private void setVistaFull(ActionEvent event) {
        fpGridView.setVisible(false);
        pFullview.setVisible(true);
        vista.selectToggle(rbFullView);
    }

    @FXML
    private void crearAlbum(ActionEvent event){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearAlbum.fxml"));
            CrearAlbumController ct = new CrearAlbumController();
            fxmlLoader.setController(ct);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Nuevo Album");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarAlbumes(){
        if (!App.albunes.isEmpty()) {
            for (Album album : App.albunes) {
                System.out.println(album.getNombre());
                cbAlbum.getItems().add(album);
            }
        cbAlbum.setValue(App.albunes.getLast());
        }
    }

    public void mostrarAlbum(){
        hbGridContent.getChildren().clear();
        if (!cbAlbum.getValue().getContenido().isEmpty()) {
            for (Imagen imagen : cbAlbum.getValue().getContenido()) {
                System.out.println(imagen.getPath());
                //Image nuevo = new Image(img.getPath());
                Label recuadro = new Label();
                recuadro.setText(imagen.getNombre());
                //ImageView view = new  ImageView(nuevo);
                //view.setFitHeight(80);
                //view.setPreserveRatio(true);
                //recuadro.setGraphic(view);
                hbGridContent.getChildren().addAll(recuadro);
            }
        }
    }

    public void mostrarDefault(){
    cbAlbum.setValue(App.albunes.get(0));
        for (Imagen img : App.albunes.get(0).getContenido()) {
            System.out.println(img.getPath());
            //Image nuevo = new Image(img.getPath());
            Label recuadro = new Label();
            recuadro.setText(img.getNombre());
            //ImageView view = new  ImageView(nuevo);
            //view.setFitHeight(80);
            //view.setPreserveRatio(true);
            //recuadro.setGraphic(view);
            hbGridContent.getChildren().addAll(recuadro);
        }
    }
    
}
