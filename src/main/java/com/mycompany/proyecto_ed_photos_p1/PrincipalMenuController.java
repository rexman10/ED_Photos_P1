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
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private HBox filtroSimple;
    @FXML
    private ComboBox<String> cbParametros1;
    @FXML
    private HBox filtroCompuesto;
    @FXML
    private ComboBox<String> cbParametros2;
    @FXML
    private TextField txtParametro1;
    @FXML
    private TextField txtParametro2;
    @FXML
    private RadioButton rbFiltroSimple;
    @FXML
    private RadioButton rbFiltroCompuesto;
    @FXML
    private Button btnFiltrar;
    @FXML
    private Label lbFecha;
    
    private CircularDoubleLinkedList<Imagen> imagenesAlbum= new CircularDoubleLinkedList();
    private CircularDoubleLinkedList<Imagen> imagesDisplayed = new CircularDoubleLinkedList();
    
    @FXML
    private ListView<String> listaComentarios;
    @FXML
    private Button btAdministrarCamaras;
    @FXML
    private ListView<String> lviewPersonas;

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
        llenarComboP1();
    }    
    
    @FXML
    private void switchToAdminCamaras(ActionEvent event) throws IOException  {
        App.setRoot("tablaCamaras");
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
        
        
        if(!imagesDisplayed.isEmpty()){
            if(currentImagen==null){
                Imagen imagen=imagesDisplayed.get(0);
                setearImageView(imagen.getPath());
                setearDatosInterfaz(imagen);
            }
             if(currentImagen!=null){
                boolean esFoto=false;
                
                
                
                while(esFoto==false){
                    Iterator<Imagen> it=imagesDisplayed.iterator();
                    
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
        else if (!cbAlbum.getValue().getContenido().isEmpty()) {
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
        txtParametro2.setDisable(true);
        cbParametros1.getItems().clear();
        txtParametro1.clear();
        txtParametro2.clear();
        String s = filtro.getSelectedToggle().toString();
        String t = rbFiltroSimple.toString();
        if (s.equals(t)) {
            cbParametros1.getItems().add("Personas");
            cbParametros1.getItems().add("Lugar");
            cbParametros1.getItems().add("Fecha");
            cbParametros1.getItems().add("Camara");
            cbParametros1.getItems().add("Hashtags");
            cbParametros1.getItems().add("Descripcion");
            cbParametros1.getItems().add("Reaccion");
        }
    }

    @FXML
    private void setFiltroCompuesto(ActionEvent event) {
        cbParametros2.setDisable(false);
        txtParametro2.setDisable(false);
        cbParametros1.getItems().clear();
        txtParametro1.clear();
        txtParametro2.clear();
        cbParametros1.getItems().add("Personas");
        cbParametros1.getItems().add("Lugar");
    }

    @FXML
    private void llenarCompuesta(ActionEvent event) {
        String s = cbParametros1.getSelectionModel().getSelectedItem();
        cbParametros2.getItems().clear();
        if (s.equals("Personas")) {
            cbParametros2.getItems().add("Lugar");
        } else {
            cbParametros2.getItems().add("Personas");
        }
    }

    @FXML
    private void filtrarImagenes(ActionEvent event) {
        Album a = cbAlbum.getSelectionModel().getSelectedItem();
        if (rbFiltroSimple.isSelected()) {
            String s = cbParametros1.getSelectionModel().getSelectedItem();
            String t = txtParametro1.getText();
            if (s.equals("Personas")) {
                showPeople(toLista(t), a);
            } else if (s.equals("Hashtags")) {
                showPerHashtags(toLista(t), a);
            } else if (s.equals("Descripcion")) {
                showPerDescription(toLista2(t), a);
            } else if (s.equals("Lugar")) {
                showPerPlace(t, a);
            } else if (s.equals("Fecha")) {
                showPerDate(t, a);
            } else if (s.equals("Camara")) {
                showPerCamara(t, a);
            } else if (s.equals("Reaccion")) {
                showPerReaction(t, a);
            }
        } else {
            String s = cbParametros1.getSelectionModel().getSelectedItem();
            String t1 = txtParametro1.getText();
            String t2 = txtParametro2.getText();
            if (s.equals("Personas")) {
                showPeopleIn(toLista(t1), t2, a);
            } else if (s.equals("Lugar")) {
                showPeopleIn(toLista(t2), t1, a);
            }
        }
    }
    
    private List<String> toLista(String text) {
        List<String> lista = new ArrayList<>();
        if (text.contains("-")) {
            String[] parts = text.split("-");
            for (String p : parts) {
                lista.addLast(p);
            }
        }else {
            lista.addLast(text);
        }
        return lista;
    }

    private List<String> toLista2(String text) {
        List<String> lista = new ArrayList<>();
        if (text.contains(" ")) {
            String[] parts = text.split(" ");
            for (String p : parts) {
                lista.addLast(p);
            }
        } else {
            lista.addLast(text);
        }
        return lista;
    }
    
    public void llenarComboP1() {
        cbParametros1.getItems().add("Personas");
        cbParametros1.getItems().add("Lugar");
        cbParametros1.getItems().add("Fecha");
        cbParametros1.getItems().add("Camara");
        cbParametros1.getItems().add("Hashtags");
        cbParametros1.getItems().add("Descripcion");
        cbParametros1.getItems().add("Reaccion");
    }
    
   private void displayImages(CircularDoubleLinkedList<Imagen> images) {
        imagesDisplayed = images;
        
        if(images==null){
            System.out.println("LA LISTA DE IMAGENES DISPLAYED ESTA VACIA ");
        }
        
        else{
            for (Imagen i : images) {
                Pane imageView = crearVistaImagen(i);
                galeria.getChildren().addAll(imageView);
            }
        }
        /*
        for (Imagen i : images) {
            Pane imageView = crearVistaImagen(i);
            galeria.getChildren().addAll(imageView);
        }*/
    }

    //Busqueda Simple
    public void showPerPlace(String place, Album a) {
        Map<String, CircularDoubleLinkedList<Imagen>> imagePerPl = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            if (i.getLugar().equals(place)) {
                boolean gotKey = imagePerPl.containsKey(place);
                if (gotKey) {
                    imagePerPl.get(place).addLast(i);
                    
                } else {
                    CircularDoubleLinkedList<Imagen> newList = new CircularDoubleLinkedList<Imagen>();
                    newList.addLast(i);
                    imagePerPl.put(place, newList);
                }
            }
        }
        galeria.getChildren().clear();
        displayImages(imagePerPl.get(place));
    }

    public void showPeople(List<String> p, Album a) {
        List<Imagen> l = a.getContenido();
        CircularDoubleLinkedList<Imagen> fotos = new CircularDoubleLinkedList<>();
        for (Imagen i : l) {
            int numPer = 0;
            for (String persona : p ) {
                if (i.getPersonas().contains(persona)) {
                    numPer++;
                }
                if (numPer == p.size()) {
                    fotos.addLast(i);
                }
            }
        }
        galeria.getChildren().clear();
        displayImages(fotos);
    }

    //Busqueda Compleja
    public void showPeopleIn(List<String> p, String place, Album a) {
        List<Imagen> l = a.getContenido();
        CircularDoubleLinkedList<Imagen> fotos = new CircularDoubleLinkedList<>();

        for (Imagen i : l) {
            int numPer = 0;
            for (String persona : p ) {
                if (i.getPersonas().contains(persona)&& i.getLugar().equals(place)) {
                    numPer++;
                }
                if (numPer == p.size()) {
                    fotos.addLast(i);
                }
            }
        }
        galeria.getChildren().clear();
        displayImages(fotos);
    }

    //Búsqueda por Hashtag(s)
    public void showPerHashtags(List<String> lHashtag, Album a) {
        List<Imagen> l = a.getContenido();
        CircularDoubleLinkedList<Imagen> fotos = new CircularDoubleLinkedList<>();

        for (Imagen i : l) {
            int numHash = 0;
            for (String keyword : lHashtag) {
                if (i.getKeywords().contains(keyword)) {
                    numHash++;
                }
                if (numHash == lHashtag.size()) {
                    fotos.addLast(i);
                }
            }
        }
        galeria.getChildren().clear();
        displayImages(fotos);
    }

    //Búsqueda por Descripción
    public void showPerDescription(List<String> searchD, Album a) {
        List<Imagen> l = a.getContenido();
        CircularDoubleLinkedList<Imagen> fotos = new CircularDoubleLinkedList<>();

        int numDesc = 0;
        for (Imagen i : l) {
            System.out.println(i.getDescription());
            int numHash = 0;
            for (String word : searchD) {
                if (i.getDescription().contains(word)) {
                    System.out.println(i.getDescription());
                    numHash++;
                    //System.out.println(i.getDescription().contains(word));
                }
                if (numHash == searchD.size()) {
                    fotos.addLast(i);
                }
            }
        }
        galeria.getChildren().clear();
        displayImages(fotos);
    }

    //Búsqueda por Reacciones
    public void showPerReaction(String searchR, Album a) {
        Map<String, CircularDoubleLinkedList<Imagen>> imagePerR = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            if (i.getReaccion().equals(searchR)) {
                boolean gotKey = imagePerR.containsKey(searchR);
                if (gotKey) {
                    imagePerR.get(searchR).addLast(i);
                } else {
                    CircularDoubleLinkedList<Imagen> newList = new CircularDoubleLinkedList<Imagen>();
                    newList.addLast(i);
                    imagePerR.put(searchR, newList);
                }
            }
        }
        galeria.getChildren().clear();
        displayImages(imagePerR.get(searchR));
    }

    //Búsqueda por Marca o Modelo de Cámara
    public void showPerCamara(String searchC, Album a) {
        Map<String, CircularDoubleLinkedList<Imagen>> imagePerCam = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            String marca = i.getCamara().getMarca();
            String modelo = i.getCamara().getModelo();
            if (marca.equals(searchC) || modelo.equals(searchC)) {
                boolean gotKey = imagePerCam.containsKey(searchC);
                if (gotKey) {
                    imagePerCam.get(searchC).addLast(i);
                    //System.out.println("NUEVO   "+i.getNombre());
                } else {
                    CircularDoubleLinkedList<Imagen> newList = new CircularDoubleLinkedList<Imagen>();
                    newList.addLast(i);
                    //System.out.println("VIEJO   "+i.getNombre());
                    imagePerCam.put(searchC, newList);
                }
            }
        }
        galeria.getChildren().clear();
        displayImages(imagePerCam.get(searchC));
    }

    //Busqueda por fecha year-month-day
    public void showPerDate(String date, Album a) {
        Map<String, CircularDoubleLinkedList<Imagen>> imagePerDate = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            String fechaFoto = i.getFecha_tomada().toString();
            if (fechaFoto.equals(date)) {
                boolean gotKey = imagePerDate.containsKey(date);
                if (gotKey) {
                    imagePerDate.get(date).addLast(i);
                    //System.out.println("NUEVO "+i.getFecha_tomada());
                } else {
                    CircularDoubleLinkedList<Imagen> newList = new CircularDoubleLinkedList<Imagen>();
                    newList.addLast(i);
                    imagePerDate.put(date, newList);
                }
            }
        }
        galeria.getChildren().clear();
        displayImages(imagePerDate.get(date));
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
    
    @FXML
    public void edicionAlbum(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("crearAlbum.fxml"));
            CrearAlbumController ct = new CrearAlbumController();
            fxmlLoader.setController(ct);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Edicion Album");
            ct.llenarCampos(cbAlbum.getValue());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
        
    @FXML
    public void eliminarAlbum(){
        Album seleccionado = cbAlbum.getValue();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Eliminar un album");
        alert.setHeaderText("Notificacion");
        alert.setContentText("Esta seguro que desea eliminar el album " + seleccionado.getNombre()+"?");
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int indice = App.albunes.indexOf(seleccionado);
            App.albunes.remove(indice);
            System.out.println(App.albunes);
            actualizarAlbumes();
        }

    }
    
    public void actualizarAlbumes(){
        if (!App.albunes.isEmpty()) {
            cbAlbum.getItems().clear();
            for (Album album : App.albunes) {
                //System.out.println(album.getNombre());
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
            //System.out.println(fileName);
            input = App.class.getResource(fileName).openStream();
            //crear la imagen 
            Image nuevo = new Image(input, 100, 100, true, false);
            ImageView view = new  ImageView(nuevo);
            //System.out.println("imagen "+imagen.getPath()+" cargada");
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
        
        listaComentarios.getItems().clear();
        lbEtiquetas.setText("");
        lviewPersonas.getItems().clear();
        
        currentImagen = imagen;
        System.out.println(currentImagen.getNombre());
        lbLugar.setText(imagen.getLugar());
        lbCamara.setText(imagen.getCamara().getMarca()+" - "+imagen.getCamara().getModelo());
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
        
        if(!imagen.getComments().isEmpty()){
            for(String c: imagen.getComments()){
                listaComentarios.getItems().add(c);
            }
        }
        
        if(!imagen.getPersonas().isEmpty()){
            for(String p:imagen.getPersonas()){
                lviewPersonas.getItems().add(p);
            }
        }
        
        if(!imagen.getKeywords().isEmpty()){
            String texto="";
            for(String p: imagen.getKeywords()){
                texto+="#"+p;
            } 
            lbEtiquetas.setText(texto);
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
        rbFiltroSimple.setSelected(true);
        txtParametro1.setText("");
        txtParametro2.setText("");
        
        if(imagesDisplayed==null){
            System.out.println("IMAGES DISPLAYED ESTA VACIA");
        }
        else{
            imagesDisplayed.clear();
        }
        //imagesDisplayed.clear();
        
        rbLike.setSelected(false);
        rbLove.setSelected(false);
        rbSad.setSelected(false);
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
        if(!imagesDisplayed.isEmpty()){
            ListIterator<Imagen> it= imagesDisplayed.listIterator();
        
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
        }else {
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
        /*
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
        setearImageView(nuevaImagen.getPath());*/
    }

    @FXML
    private void fotoSiguiente() {
        
        if(!imagesDisplayed.isEmpty()){
            ListIterator<Imagen> it= imagesDisplayed.listIterator();
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
        }else {
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
        /*
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
        */
    }
    
    private void resetCurrentImagen(){
        currentImagen=null;
        lbLugar.setText("");
        lbCamara.setText("");
        lbFecha.setText("");
        listaComentarios.getItems().clear();
        lbEtiquetas.setText("");
        lviewPersonas.getItems().clear();
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

    @FXML
    private void agregarComentario() {
        if(currentImagen==null){
            App.mostrarAlerta(Alert.AlertType.ERROR, "No se ha seleccionado ninguna foto");
        }else{
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("comentario.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            //aggPremio();
            
            String com=ComentarioController.comment;
            ComentarioController.comment=null;
            aggComment(com);
            
           
            
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    private void aggComment(String com){
        if(!com.isEmpty()){
            listaComentarios.getItems().add(com);
            App.mostrarAlerta(Alert.AlertType.INFORMATION,"Comentario añadido con exito");
            List<String> comentarios=currentImagen.getComments();
            comentarios.addLast(com);
            currentImagen.setComments(comentarios);
        }
   
    }
    
    @FXML
    private void agregarEtiqueta() {
        if(currentImagen==null){
            App.mostrarAlerta(Alert.AlertType.ERROR, "No se ha seleccionado ninguna foto");
        }else{
            try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("etiqueta.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            //aggPremio();
            
            String key=EtiquetaController.keyword;
            EtiquetaController.keyword=null;
            aggKeyword(key);
            
           
            
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
    }
    
    private void aggKeyword(String key){
        if(!key.isEmpty()){
            String texto=lbEtiquetas.getText();
            texto+="#"+key;
            lbEtiquetas.setText(texto);
            
            
            
            //listaComentarios.getItems().add(com);
            App.mostrarAlerta(Alert.AlertType.INFORMATION,"Etiqueta añadido con éxito");
            List<String> etiquetas=currentImagen.getKeywords();
            etiquetas.addLast(key);
            currentImagen.setKeywords(etiquetas);
        }
   
    }

}
