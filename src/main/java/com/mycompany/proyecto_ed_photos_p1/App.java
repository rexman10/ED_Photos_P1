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
    public void showPerPerson(String persona, Album a) {
        Map<String, ArrayList<Imagen>> imagePerP = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            if (i.getPersonas().contains(persona)) {
                boolean gotKey = imagePerP.containsKey(persona);
                if (gotKey) {
                    imagePerP.get(persona).addLast(i);
                } else {
                    ArrayList<Imagen> newList = new ArrayList<Imagen>();
                    newList.addLast(i);
                    imagePerP.put(persona, newList);
                }
            }
        }
    }
    
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
    
    
    public void showPersonIn(String persona, String place, Album a) {
        Map<String, ArrayList<Imagen>> imagePerP = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            if (i.getPersonas().contains(persona) && i.getLugar().equals(place)) {
                boolean gotKey = imagePerP.containsKey(persona);
                if (gotKey) {
                    imagePerP.get(persona).addLast(i);
                } else {
                    ArrayList<Imagen> newList = new ArrayList<Imagen>();
                    newList.addLast(i);
                    imagePerP.put(persona, newList);
                }
            }
        }
    }

    //Busqueda Compleja
    public static void showPeople(List<String> p, Album a) {
        List<Imagen> l = a.getContenido();
        List<Imagen> fotos = new ArrayList<>();

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

    public static void showPeopleIn(List<String> p, String place, Album a) {
        List<Imagen> l = a.getContenido();
        List<Imagen> fotos = new ArrayList<>();

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
    public void showPerHashtag (String hashtag, Album a) {
        Map<String, ArrayList<Imagen>> imageHashtag = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            if (i.getKeywords().contains(hashtag)) {
                boolean gotKey = imageHashtag.containsKey(hashtag);
                if (gotKey) {
                    imageHashtag.get(hashtag).addLast(i);
                } else {
                    ArrayList<Imagen> newList = new ArrayList<Imagen>();
                    newList.addLast(i);
                    imageHashtag.put(hashtag, newList);
                }
            }
        }
    }
    
    //Búsqueda por Descripción
    public void showPerDescription(String searchD, Album a) {
        Map<String, ArrayList<Imagen>> imagePerD = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            if (i.getDescription().contains(searchD)) {
                boolean gotKey = imagePerD.containsKey(searchD);
                if (gotKey) {
                    imagePerD.get(searchD).addLast(i);
                } else {
                    ArrayList<Imagen> newList = new ArrayList<Imagen>();
                    newList.addLast(i);
                    imagePerD.put(searchD, newList);
                }
            }
        }
    }
    
    //Búsqueda por Reacciones
    public void showPerReaction(String searchD, Album a) {
        Map<String, ArrayList<Imagen>> imagePerP = new TreeMap<>();

        for (Imagen i : a.getContenido()) {
            if (i.getDescription().contains(searchD)) {
                boolean gotKey = imagePerP.containsKey(searchD);
                if (gotKey) {
                    imagePerP.get(searchD).addLast(i);
                } else {
                    ArrayList<Imagen> newList = new ArrayList<Imagen>();
                    newList.addLast(i);
                    imagePerP.put(searchD, newList);
                }
            }
        }
    }
    
    //Búsqueda por Camara o Modelo de Cámara
    
    

    public static void main(String[] args){
        cargarBaseDatos();
        launch(args);
    }

}