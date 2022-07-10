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
import com.mycompany.utilidades.CircularDoubleLinkedList;
import com.mycompany.utilidades.EmptyFieldException;
import com.mycompany.utilidades.List;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.ListIterator;

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
import javafx.scene.control.Alert;
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
    private RadioButton rbLove;
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
    @FXML
    private Label lbFecha;
    
    private CircularDoubleLinkedList<Imagen> imagenesAlbum= new CircularDoubleLinkedList();

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
        resetCurrentImagen();
        imagenesAlbum.clear();
        
        spGridView.setVisible(true);
        pFullview.setVisible(false);
        vista.selectToggle(rbGridView);
    }

    @FXML
    private void setVistaFull(ActionEvent event) {
        spGridView.setVisible(false);
        pFullview.setVisible(true);
        vista.selectToggle(rbFullView);
        
        if (!cbAlbum.getValue().getContenido().isEmpty()) {
             /* OTRA ALTERNATIVA
            int n=0;
            for (Imagen imagen : cbAlbum.getValue().getContenido()) {
                imagenesAlbum.add(imagen, n);
                n++;
            }
            */
            
            for (Imagen imagen : cbAlbum.getValue().getContenido()) {
                imagenesAlbum.addLast(imagen);
            }
            
            if(currentImagen==null){
                Imagen imagen=imagenesAlbum.get(0);
                setearImageView(imagen.getPath());
                setearDatosInterfaz(imagen);
            } 
            
            if(currentImagen!=null){
                boolean esFoto=false;
                
                
                
                while(esFoto==false){
                    Iterator<Imagen> it=imagenesAlbum.iterator();
                    
                    while(it.hasNext()){
                        Imagen imActual=it.next();
                        if(imActual.getPath().equals(currentImagen.getPath())){
                            setearDatosInterfaz(imActual);
                            setearImageView(imActual.getPath());
                            esFoto=true;
                        }
                    }
                }
                
            }
            
        }
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
                
                setearDatosInterfaz(imagen);
            
        });
        } catch (Exception ex) {
            ex.getMessage();
        }  
        return recuadro;
    }
    
    private void setearDatosInterfaz(Imagen imagen){
        rbLike.setSelected(false);
        rbLove.setSelected(false);
        rbSad.setSelected(false);
        
        currentImagen = imagen;
        System.out.println(currentImagen.getNombre());
        lbLugar.setText(imagen.getLugar());
        lbCamara.setText("Marca: "+imagen.getCamara().getMarca()+" - Modelo: "+imagen.getCamara().getModelo());
        lbFecha.setText(imagen.getFecha_tomada().format(DateTimeFormatter.ISO_DATE));
        
        if(imagen.getReaccion() != null){
            if(imagen.getReaccion().equals("Me gusta")){
                rbLike.setSelected(true);
            }
            if(imagen.getReaccion().equals("Me encanta")){
                rbLove.setSelected(true);
            }
            if(imagen.getReaccion().equals("Me entristece")){
                rbSad.setSelected(true);
            }
        }
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
        resetCurrentImagen();

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
    
    private void setearImageView(String ruta){
        InputStream input = null;
        try {
             String fileName =ruta;//armar la ruta de la foto

             System.out.println("FOTO RUTA"+fileName);


             //abrir el stream de la imagen de la persona
             input = App.class.getResource(fileName).openStream();

             //crear la imagen 
             Image image = new Image(input, 1000, 1000, false, false);
             ivFullScreen.setImage(image);

            } catch (Exception ex) {
                 System.out.println("No se encuentra archivo de imagen:  "+ex);
                } finally {
                if (input != null) {
                    try {
                   input.close();
                } catch (IOException ex) {
                System.out.println("No se pudo cerrar");
                }
            }
        }
    }

    @FXML
    private void crearImagen(ActionEvent event) {
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("imagen.fxml"));//no tiene el controlador especificado
            ImagenController ct = new ImagenController();

            fxmlLoader.setController(ct);//se asigna el controlador
            HBox root = (HBox) fxmlLoader.load();
            App.changeRoot(root);

            }catch (IOException ex) {
                ex.printStackTrace();
            }
    }

    @FXML
    private void editarImagen(ActionEvent event) {
        if(currentImagen==null){
            App.mostrarAlerta(Alert.AlertType.INFORMATION, "No se ha seleccionado ninguna imagen");
        }else{
            
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("imagen.fxml"));//no tiene el controlador especificado
                ImagenController ct = new ImagenController();

                fxmlLoader.setController(ct);//se asigna el controlador
                HBox root = (HBox) fxmlLoader.load();
                ct.llenarCampos(currentImagen, cbAlbum.getValue());
                App.changeRoot(root);

            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void eliminarImagen(ActionEvent event) {
        if(currentImagen==null){
            App.mostrarAlerta(Alert.AlertType.INFORMATION, "No se ha seleccionado ninguna imagen");
        }else{
            int n=App.confirmacion("¿Desea eliminar la imagen seleccionada?");
            if(n!=0){
                List<Imagen> listaOriginal=cbAlbum.getValue().getContenido();
                List<Imagen> lista=listaOriginal;

                int ind=0;
                for(Imagen im:listaOriginal){
                    if(im.getNombre().equals(currentImagen.getNombre())){
                        lista.remove(ind);
                    }
                    ind++;
                }
                
                cbAlbum.getValue().setContenido(lista);
                App.mostrarAlerta(Alert.AlertType.INFORMATION, "Imagen eliminada con éxito del álbum: "+cbAlbum.getValue().getNombre());
                mostrarAlbum();
            }
          
        }
        
    }

    @FXML
    private void fotoAnterior(ActionEvent event) {
        ListIterator<Imagen> it= imagenesAlbum.listIterator();
        
        boolean esFoto=false;
        while(esFoto==false){
            Imagen im=it.previous();
            if(im.getPath().equals(currentImagen.getPath())){
                esFoto=true;
            }
        }
        
        Imagen nuevaImagen=it.previous();
        setearDatosInterfaz(nuevaImagen);
        setearImageView(nuevaImagen.getPath());
    }

    @FXML
    private void fotoSiguiente(ActionEvent event) {
        ListIterator<Imagen> it= imagenesAlbum.listIterator();
        boolean esFoto=false;
        while(esFoto==false){
            Imagen im=it.next();
            if(im.getPath().equals(currentImagen.getPath())){
                esFoto=true;
            }
        }
        
        Imagen nuevaImagen=it.next();
        setearDatosInterfaz(nuevaImagen);
        setearImageView(nuevaImagen.getPath());
    }
    
    private void resetCurrentImagen(){
        currentImagen=null;
        lbLugar.setText("");
        lbCamara.setText("");
        lbFecha.setText("");
    }

    @FXML
    private void setReaction() {
        if(currentImagen==null){
            App.mostrarAlerta(Alert.AlertType.ERROR, "No se ha seleccionado ninguna foto");
        }else{
            
            if(rbLike.isSelected()){
                currentImagen.setReaccion("Me gusta");
            }
            if(rbLove.isSelected()){
                currentImagen.setReaccion("Me encanta");
            }
            if(rbSad.isSelected()){
                currentImagen.setReaccion("Me entristece");
            }
        }
    }

}
