package com.mycompany.proyecto_ed_photos_p1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Formatter;

import com.mycompany.modelo.Album;
import com.mycompany.modelo.Camara;
import com.mycompany.modelo.Imagen;
import com.mycompany.utilidades.ArrayList;
import com.mycompany.utilidades.CircularDoubleLinkedList;
import com.mycompany.utilidades.DoubleLinkedList;
import com.mycompany.utilidades.List;
import java.util.Map;
import java.util.TreeMap;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static ArrayList<Album> albunes = new ArrayList<>();
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("principalMenu"), 1280, 800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Photo Album");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    static void changeRoot(Parent rootNode) {
        scene.setRoot(rootNode);
    }

    public static void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alert = new Alert(tipo);

        alert.setTitle("Resultado de operacion");
        alert.setHeaderText("Notificacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void cargarBaseDatos(){
        Camara cam1 = new Camara("Cannon","J25");
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha1 = LocalDate.now();
        String a = fecha1.format(f1);
        Imagen im1 = new Imagen("imagenes/img022.jpg","descripcion corta","Guayaquil",cam1,fecha1);
        im1.setNombre("img022");
        Album album1 = new Album("Album1", "visita a cascadas 2019");
        album1.agregarImagen(im1);
        albunes.addLast(album1);
        Imagen im2 = new Imagen("imagenes/img001.jpg","descripcion corta","Guayaquil",cam1,fecha1);
        im2.setNombre("img001");
        Imagen im3 = new Imagen("imagenes/img002.jpg","descripcion corta","Guayaquil",cam1,fecha1);
        im3.setNombre("img002");
        Imagen im4 = new Imagen("imagenes/img019.jpg","descripcion corta","Guayaquil",cam1,fecha1);
        im4.setNombre("img019");
        Imagen im5 = new Imagen("imagenes/img021.jpg","descripcion corta","Guayaquil",cam1,fecha1);
        im5.setNombre("img021");

        album1.agregarImagen(im2);
        album1.agregarImagen(im3);
        album1.agregarImagen(im4);
        album1.agregarImagen(im5);
        album1.agregarImagen(im2);
        album1.agregarImagen(im3);
        album1.agregarImagen(im4);
        album1.agregarImagen(im5);
        album1.agregarImagen(im2);
        album1.agregarImagen(im3);
        album1.agregarImagen(im4);
        album1.agregarImagen(im5);
        album1.agregarImagen(im2);
        album1.agregarImagen(im3);
        album1.agregarImagen(im4);
        album1.agregarImagen(im5);

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
    }

    
    public static void showPeople(List<String> p, Album a) {
        List<Imagen> l = a.getContenido();
        List<Imagen> fotos = new CircularDoubleLinkedList<>();

        for (Imagen i : l) {
            int numPer = 0;
            for (String persona : i.getPersonas()) {
                if (p.contains(persona)) {
                    numPer++;
                }
                if (numPer == p.size()) {
                    fotos.addLast(i);
                }
            }
        }
        //return fotos;
    }

    //Busqueda Compleja
    public static void showPeopleIn(List<String> p, String place, Album a) {
        List<Imagen> l = a.getContenido();
        List<Imagen> fotos = new CircularDoubleLinkedList<>();

        for (Imagen i : l) {
            int numPer = 0;
            for (String persona : i.getPersonas()) {
                if (p.contains(persona) && i.getLugar().equals(place)) {
                    numPer++;
                }
                if (numPer == p.size()) {
                    fotos.addLast(i);
                }
            }
        }
        //return fotos;
    }
    
    //Búsqueda por Hashtag(s)
    
    public void showPerHashtags(List<String> lHashtag, Album a) {
        List<Imagen> l = a.getContenido();
        List<Imagen> fotos = new CircularDoubleLinkedList<>();

        for (Imagen i : l) {
            int numHash = 0;
            for (String keyword : i.getKeywords()) {
                if (lHashtag.contains(keyword)) {
                    numHash++;
                }
                if (numHash == lHashtag.size()) {
                    fotos.addLast(i);
                }
            }
        }
        //return fotos;
    }
   
    //Búsqueda por Descripción
    public void showPerDescription(List<String>searchD, Album a) {
        List<Imagen> l = a.getContenido();
        List<Imagen> fotos = new CircularDoubleLinkedList<>();

        int numDesc = 0;
        for (Imagen i : l) {
            int numHash = 0;
            for (String word: searchD) {
                if (i.getDescription().contains(word)) {
                    numHash++;
                }
                if (numHash == searchD.size()) {
                    fotos.addLast(i);
                }
            }
        }
        //return fotos;
    }
    
    //Búsqueda por Reacciones
    public void showPerReaction(String searchD, Album a) {
        Map<String, CircularDoubleLinkedList<Imagen>> imagePerP = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            if (i.getDescription().contains(searchD)) {
                boolean gotKey = imagePerP.containsKey(searchD);
                if (gotKey) {
                    imagePerP.get(searchD).addLast(i);
                } else {
                    CircularDoubleLinkedList<Imagen> newList = new CircularDoubleLinkedList<Imagen>();
                    newList.addLast(i);
                    imagePerP.put(searchD, newList);
                }
            }
        }
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
                } else {
                    CircularDoubleLinkedList<Imagen> newList = new CircularDoubleLinkedList<Imagen>();
                    newList.addLast(i);
                    imagePerCam.put(searchC, newList);
                }
            }
        }
    }    
    
    //Implementar búsquedas que incluyan fechas: Fotos de enero de 2018 ; 
    //fotos de Andres y Emilio entre Marzo y Julio del 2009; fotos tomadas en Quito en Marzo del 2010.
    
    //Busqueda por fecha year-month-day
    public void showPerDate(String date, Album a) {
        Map<String, CircularDoubleLinkedList<Imagen>> imagePerDate = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            String fechaFoto = i.getFecha_tomada().toString();
            if (fechaFoto.equals(date)) {
                boolean gotKey = imagePerDate.containsKey(date);
                if (gotKey) {
                    imagePerDate.get(date).addLast(i);
                } else {
                    CircularDoubleLinkedList<Imagen> newList = new CircularDoubleLinkedList<Imagen>();
                    newList.addLast(i);
                    imagePerDate.put(date, newList);
                }
            }
        }
    }    
    
    
    public static void main(String[] args){
        cargarBaseDatos();
        launch(args);
    }

}