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
import com.mycompany.utilidades.DoubleLinkedList;
import com.mycompany.utilidades.List;

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
        Imagen im1 = new Imagen("src/main/resources/imagenes/img001.jpg","descripcion corta","Guayaquil",cam1,fecha1);
        im1.setNombre("img001");
        System.out.println(fecha1);
        System.out.println(a);
        Album album1 = new Album("Album1", "visita a cascadas 2019");
        album1.agregarImagen(im1);
        albunes.addLast(album1);
    }

    public static void main(String[] args){
        cargarBaseDatos();
        launch(args);
    }

}