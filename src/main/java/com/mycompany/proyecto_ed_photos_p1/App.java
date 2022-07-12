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
import java.util.Optional;
import java.util.TreeMap;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static ArrayList<Album> albunes = new ArrayList<>();
    public static List<Camara> listadoCamaras = new ArrayList<>();


    
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

    static Parent loadFXML(String fxml) throws IOException {
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
        Camara cam1 = new Camara("J25","Cannon");
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
        /*
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
        */
        
        Camara cam2 = new Camara("H95","Cannon");
        Imagen imd1 = new Imagen("imagenes/d-imagen-1.jpg","descripcion corta","Guayaquil",cam2,fecha1);
        imd1.setNombre("d-imagen-1");
        Imagen imd2 = new Imagen("imagenes/d-imagen-2.jpg","descripcion corta","Vista Sol",cam2,fecha1);
        imd2.setNombre("d-imagen-2");
        Imagen imd3 = new Imagen("imagenes/d-imagen-3.jpg","descripcion corta","Milagro",cam2,fecha1);
        imd3.setNombre("d-imagen-3");
        Imagen imd4 = new Imagen("imagenes/d-imagen-4.jpg","descripcion corta","Hilton Colon",cam2,fecha1);
        imd4.setNombre("d-imagen-4");
        Imagen imd5 = new Imagen("imagenes/d-imagen-5.jpg","descripcion corta","Porto",cam2,fecha1);
        imd5.setNombre("d-imagen-5");
        Imagen imd6 = new Imagen("imagenes/d-imagen-6.jpg","descripcion corta","Vista Sol",cam2,fecha1);
        imd6.setNombre("d-imagen-6");
        Imagen imd7 = new Imagen("imagenes/d-imagen-7.jpg","descripcion corta","Ciudad Celeste",cam2,fecha1);
        imd7.setNombre("d-imagen-7");
        Imagen imd8 = new Imagen("imagenes/d-imagen-8.jpg","descripcion corta","Quito",cam2,fecha1);
        imd8.setNombre("d-imagen-8");
        Imagen imd9 = new Imagen("imagenes/d-imagen-9.jpg","descripcion corta","Milagro",cam2,fecha1);
        imd9.setNombre("d-imagen-9");
        
        List<String> personas=new ArrayList();
        personas.addLast("Diego Martinez");
        personas.addLast("Michael Velastegui");
        personas.addLast("Mario Moreira");
        personas.addLast("Isabella Vergara");
        imd5.setPersonas(personas);
        
        listadoCamaras.addLast(cam1);
        listadoCamaras.addLast(cam2);
        
        Album album2 = new Album("Favoritas Diego", "Fotos favoritas de Diego Martinez");
        albunes.addLast(album2);
        album2.agregarImagen(imd1);
        album2.agregarImagen(imd2);
        album2.agregarImagen(imd3);
        album2.agregarImagen(imd4);
        album2.agregarImagen(imd5);
        album2.agregarImagen(imd6);
        album2.agregarImagen(imd7);
        album2.agregarImagen(imd8);
        album2.agregarImagen(imd9);
        

    }
    
    
    public static int confirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("CONFIRMACION");
        alert.setHeaderText("ELIMINAR");
        alert.setContentText(mensaje);
        Optional<ButtonType> result=alert.showAndWait();
        int n=0;
        if(result.get()==ButtonType.OK){
            n++;
        }

        return n;
    }
    
    
    public static void main(String[] args){
        cargarBaseDatos();
        launch(args);
    }

}