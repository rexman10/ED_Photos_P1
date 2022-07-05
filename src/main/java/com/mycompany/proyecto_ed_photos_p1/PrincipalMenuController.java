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
import com.mycompany.utilidades.List;

import java.io.InputStream;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author alex_
 */
public class PrincipalMenuController implements Initializable {

    @FXML
    private Button brnAgregarImg;
    @FXML
    private Button btnEditarImg;
    @FXML
    private Button btnEliminarAlbum;
    @FXML
    private Button btnEliminarAlbun;
    @FXML
    private Button btnEliminarImg;
    @FXML
    private Button btnExitFullScreen;
    @FXML
    private Button btnNextPhoto;
    @FXML
    private Button btnNuevoAlbun;
    @FXML
    private Button btnPrevPhoto;
    @FXML
    private ComboBox<Album> cbAlbum;
    @FXML
    private RadioButton ebLove;
    @FXML
    private ImageView ivFullScreen;
    @FXML
    private Label lbCamara;
    @FXML
    private Label lbEtiquetas;
    @FXML
    private Label lbLugar;
    @FXML
    private Label lbPersonas;
    @FXML
    private Label lbPhotoName;
    @FXML
    private Pane pFullview;
    @FXML
    private RadioButton rbFullView;
    @FXML
    private RadioButton rbGridView;
    @FXML
    private RadioButton rbLike;
    @FXML
    private RadioButton rbSad;
    @FXML
    private ToggleGroup reaction;
    @FXML
    private ScrollPane spGridView;
    @FXML
    private ToggleGroup vista;
    private TilePane galeria;
    
    private Imagen currentImagen = null;
    @FXML
    private ToggleGroup filtro;
    @FXML
    private VBox filtroSimple;
    @FXML
    private ComboBox<String> cbParametros1;
    @FXML
    private VBox filtroCompuesto;
    @FXML
    private ComboBox<String> cbParametros2;
    @FXML
    private Button btnFiltroSimple;
    @FXML
    private Button btnFiltroCompuesto;
    @FXML
    private RadioButton rbFiltroSimple;
    @FXML
    private RadioButton rbFiltroCompuesto;
    @FXML
    private Button btnFiltrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarAlbumes();
        galeria = createTilePane();
        spGridView.setContent(galeria);
        if (rbGridView.isSelected()) {
            spGridView.setVisible(true);
            pFullview.setVisible(false);
        } else {
            spGridView.setVisible(false);
            pFullview.setVisible(true);
        }
        mostrarAlbum();
        
    }    

    @FXML
    private void setVistaGrid(ActionEvent event) {
        spGridView.setVisible(true);
        pFullview.setVisible(false);
        vista.selectToggle(rbGridView);
    }

    @FXML
    private void setVistaFull(ActionEvent event) {
        spGridView.setVisible(false);
        pFullview.setVisible(true);
        vista.selectToggle(rbFullView);
    }
    
        @FXML
    private void setFiltroSimple(ActionEvent event) {
        cbParametros2.setDisable(true);
        btnFiltroCompuesto.setDisable(true);
    }

    @FXML
    private void setFiltroCompuesto(ActionEvent event) {
        cbParametros2.setDisable(false);
        btnFiltroCompuesto.setDisable(false);
    }

    @FXML
    private void filtrarImagenes(ActionEvent event) {
        System.out.println("nada de momento");
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

    private TilePane createTilePane() {
        TilePane tilePane = new TilePane();
        tilePane.setAlignment(Pos.CENTER);
        tilePane.setPadding(new Insets(15, 15, 15, 15));
        tilePane.setVgap(30);
        tilePane.setHgap(20);
        return tilePane;
    }

    private Pane crearVistaImagen(Imagen imagen){
        InputStream input = null;
        VBox recuadro = new VBox();
        Label titulo = new Label(imagen.getNombre());
        try {
            String fileName = imagen.getPath();
            System.out.println(fileName);
            input = App.class.getResource(fileName).openStream();
            //crear la imagen 
            Image nuevo = new Image(input, 100, 100, true, false);
            ImageView view = new  ImageView(nuevo);
            System.out.println("imagen "+imagen.getPath()+" cargada");
            view.setFitHeight(150);
            view.setPreserveRatio(true);
            recuadro.getChildren().addAll(view,titulo);
            recuadro.setOnMouseClicked(event -> {
            currentImagen = imagen;
            System.out.println(currentImagen.getNombre());
        });
        } catch (Exception ex) {
            ex.getMessage();
        }  
        return recuadro;
    }

    /*@FXML
    private void mostrarAlbum() {
        List<Imagen> imagenes = cbAlbum.getValue().getContenido(); 
        galeria.getChildren().clear();
        for (Imagen img : imagenes) {
            Pane vista = crearVistaImagen(img);
            galeria.getChildren().addAll(vista);
        }
    }*/

    @FXML
    public void mostrarAlbum(){
        galeria.getChildren().clear();

        if (!cbAlbum.getValue().getContenido().isEmpty()) {
            for (Imagen imagen : cbAlbum.getValue().getContenido()) {
                Pane tile = crearVistaImagen(imagen);
                galeria.getChildren().addAll(tile);

            }
        }
    }

    /*public void mostrarDefault(){
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
            //hbGridContent.getChildren().addAll(recuadro);
        }
    }*/

}
