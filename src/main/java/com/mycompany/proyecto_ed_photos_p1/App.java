package com.mycompany.proyecto_ed_photos_p1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
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
import java.time.Month;
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
        /*
        Camara cam1 = new Camara("J25","Cannon");
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha1 = LocalDate.now();
        //String a = fecha1.format(f1);

        Album album1 = new Album("Coleccion de Juan", "Recopilacion de fotos");       
        albunes.addLast(album1);
        
        LocalDate fIm1 = LocalDate.of(2021, Month.JULY, 10);
        Imagen im1 = new Imagen("imagenes/j-01.jpg","descripcion corta","Cuenca",cam1,fIm1);
        im1.setNombre("j-01");
        List<String> pIm1 = new ArrayList();
        pIm1.addLast("John Guadalupe");
        pIm1.addLast("Rosa Rosas");
        pIm1.addLast("Juan Guadalupe");
        im1.setPersonas(pIm1);
        
        LocalDate fIm2 = LocalDate.of(2019, Month.FEBRUARY, 18);
        Imagen im2 = new Imagen("imagenes/j-02.jpg","descripcion corta","Romareda",cam1,fIm2);
        im2.setNombre("j-02");
        List<String> pIm2 = new ArrayList();
        pIm2.addLast("Steven Escobar");
        pIm2.addLast("Santiago Lindao");
        pIm2.addLast("Steve Taranto");
        pIm2.addLast("Juan Guadalupe");
        pIm2.addLast("Carlos Mata");
        im2.setPersonas(pIm2);
        
        LocalDate fIm3 = LocalDate.of(2019, Month.OCTOBER, 28);
        Imagen im3 = new Imagen("imagenes/j-03.jpg","descripcion corta","Guayaquil",cam1,fIm3);
        im3.setNombre("j-03");
        List<String> pIm3 = new ArrayList();
        pIm3.addLast("Carlos Mata");
        pIm3.addLast("Diego Naht");
        pIm3.addLast("Steve Taranto");
        pIm3.addLast("Juan Guadalupe");        
        im3.setPersonas(pIm3);
        
        LocalDate fIm4 = LocalDate.of(2019, Month.JUNE, 12);
        Imagen im4 = new Imagen("imagenes/j-04.jpg","descripcion corta","Guayaquil",cam1,fIm4);
        im4.setNombre("j-04");
        List<String> pIm4 = new ArrayList();
        pIm4.addLast("Carlos Mata");
        pIm4.addLast("Santiago Lindao");
        pIm4.addLast("Steven Escobar");
        pIm4.addLast("Cristopher Espic");        
        pIm4.addLast("Juan Guadalupe");
        pIm4.addLast("Marco Manrique");
        im4.setPersonas(pIm4);
        
        LocalDate fIm5 = LocalDate.of(2020, Month.DECEMBER, 23);
        Imagen im5 = new Imagen("imagenes/j-05.jpg","descripcion corta","Guayaquil",cam1,fIm5);
        im5.setNombre("j-05");
        
        LocalDate fIm6 = LocalDate.of(2019, Month.AUGUST, 16);
        Imagen im6 = new Imagen("imagenes/j-06.jpg","descripcion corta","Guayaquil",cam1,fIm6);
        im6.setNombre("j-06");
        
        LocalDate fIm7 = LocalDate.of(2019, Month.MARCH, 10);
        Imagen im7 = new Imagen("imagenes/j-07.jpg","descripcion corta","Atlanta",cam1,fIm7);
        im7.setNombre("j-07");
        List<String> pIm7 = new ArrayList();
        pIm7.addLast("John Guadalupe");
        pIm7.addLast("Juan Guadalupe");
        pIm7.addLast("Rosa Rosas");
        pIm7.addLast("Andrea Guadalupe");        
        im7.setPersonas(pIm7);     
        
        LocalDate fIm9 = LocalDate.of(2021, Month.AUGUST, 15);
        Imagen im9 = new Imagen("imagenes/j-09.jpg","descripcion corta","Guayaquil",cam1,fIm9);
        im9.setNombre("j-09");
        List<String> pIm9 = new ArrayList();
        pIm9.addLast("Juan Guadalupe");    
        im9.setPersonas(pIm9);
        
        LocalDate fIm10 = LocalDate.of(2022, Month.JUNE, 17);
        Imagen im10 = new Imagen("imagenes/j-10.jpg","descripcion corta","Guayaquil",cam1,fIm10);
        im10.setNombre("j-10");
        
        LocalDate fIm11 = LocalDate.of(2021, Month.NOVEMBER, 16);
        Imagen im11 = new Imagen("imagenes/j-11.jpg","descripcion corta","Multitud",cam1,fIm11);
        im11.setNombre("j-11");
        List<String> pIm11 = new ArrayList();
        pIm11.addLast("Steven Escobar");
        pIm11.addLast("Santiago Lindao");
        pIm11.addLast("Steve Taranto");
        pIm11.addLast("Juan Guadalupe");
        pIm11.addLast("Carlos Mata");
        im11.setPersonas(pIm11);


        
        album1.agregarImagen(im1);
        album1.agregarImagen(im2);
        album1.agregarImagen(im3);
        album1.agregarImagen(im4);
        album1.agregarImagen(im5);
        album1.agregarImagen(im6);
        album1.agregarImagen(im7);
        album1.agregarImagen(im9);
        album1.agregarImagen(im10);
        album1.agregarImagen(im11);

        
        Camara cam2 = new Camara("H95","Cannon");
        Camara cam5 = new Camara("L9000","Sony");
        
        LocalDate date1 = LocalDate.of(2019, Month.DECEMBER, 24);
        Imagen imd1 = new Imagen("imagenes/d-imagen-1.jpg","salida familiar","Guayaquil",cam2,date1);
        imd1.setNombre("d-imagen-1");
        imd1.setReaccion("Me gusta");
        
        LocalDate date2 = LocalDate.of(2020, Month.AUGUST, 9);
        Imagen imd2 = new Imagen("imagenes/d-imagen-2.jpg","primera reunion","Vista Sol",cam5,date2);
        imd2.setNombre("d-imagen-2");
        
        LocalDate date3 = LocalDate.of(2021, Month.AUGUST, 8);
        Imagen imd3 = new Imagen("imagenes/d-imagen-3.jpg","fiesta tia","Milagro",cam2,date3);
        imd3.setNombre("d-imagen-3");
        
        LocalDate date4 = LocalDate.of(2020, Month.FEBRUARY, 16);
        Imagen imd4 = new Imagen("imagenes/d-imagen-4.jpg","fiesta graduacion 2020","Hilton Colon",cam5,date4);
        imd4.setNombre("d-imagen-4");
        
        LocalDate date5 = LocalDate.of(2022, Month.MAY, 21);
        Imagen imd5 = new Imagen("imagenes/d-imagen-5.jpg","cumpleaños isabella","Porto",cam2,date5);
        imd5.setNombre("d-imagen-5");
        
        LocalDate date6 = LocalDate.of(2020, Month.NOVEMBER, 22);
        Imagen imd6 = new Imagen("imagenes/d-imagen-6.jpg","cumpleaños michael 2020","Vista Sol",cam5,date6);
        imd6.setNombre("d-imagen-6");
        
        LocalDate date7 = LocalDate.of(2020, Month.AUGUST, 31);
        Imagen imd7 = new Imagen("imagenes/d-imagen-7.jpg","cumpleaños papa 2020","Ciudad Celeste",cam2,date7);
        imd7.setNombre("d-imagen-7");
        
        LocalDate date8 = LocalDate.of(2021, Month.SEPTEMBER, 15);
        Imagen imd8 = new Imagen("imagenes/d-imagen-8.jpg","viaje a quito por visa 2021","Quito",cam5,date8);
        imd8.setNombre("d-imagen-8");
        
        LocalDate date9 = LocalDate.of(2007, Month.JUNE, 13);
        Imagen imd9 = new Imagen("imagenes/d-imagen-9.jpg","recuerdo de mama y yo","Milagro",cam2,date9);
        imd9.setNombre("d-imagen-9");
        
        List<String> personas=new ArrayList();
        personas.addLast("Diego Martinez");
        personas.addLast("Michael Velastegui");
        personas.addLast("Mario Moreira");
        personas.addLast("Isabella Vergara");
        imd5.setPersonas(personas);
        
        List<String> personas2=new ArrayList();
        personas2.addLast("Diego Martinez");
        personas2.addLast("Jorge Martinez");
        personas2.addLast("Andrea Martinez");
        imd1.setPersonas(personas2);
        
        List<String> personas3=new ArrayList();
        personas3.addLast("Diego Martinez");
        personas3.addLast("Arianna Correa");
        personas3.addLast("Michael Velastegui");
        imd2.setPersonas(personas3);
        
        List<String> personas4=new ArrayList();
        personas4.addLast("Diego Martinez");
        personas4.addLast("Isabella Vergara");
        imd4.setPersonas(personas4);
        
        List<String> personas5=new ArrayList();
        personas5.addLast("Diego Martinez");
        personas5.addLast("Andrea Martinez");
        personas5.addLast("Edith Mendia");
        imd3.setPersonas(personas5);
        
        List<String> personas6=new ArrayList();
        personas6.addLast("Diego Martinez");
        personas6.addLast("Arianna Correa");
        imd6.setPersonas(personas6);
        
        List<String> personas7=new ArrayList();
        personas7.addLast("Diego Martinez");
        personas7.addLast("Andrea Martinez");
        personas7.addLast("Jorge Martinez");
        imd7.setPersonas(personas7);
        
        List<String> personas8=new ArrayList();
        personas8.addLast("Diego Martinez");
        personas8.addLast("Margarita Mendia");
        imd8.setPersonas(personas8);
        
        List<String> personas9=new ArrayList();
        personas9.addLast("Diego Martinez");
        personas9.addLast("Margarita Mendia");
        imd9.setPersonas(personas9);
        
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
        
        LocalDate fecha2 = LocalDate.of(2022,Month.JUNE,20);
        LocalDate fecha3 = LocalDate.of(2005,Month.DECEMBER,24);
        LocalDate fecha4 = LocalDate.of(2017,Month.APRIL,15);
        LocalDate fecha5 = LocalDate.of(2022,Month.MARCH,01);
        LocalDate fecha6 = LocalDate.of(2017,Month.APRIL,22);
        LocalDate fecha7 = LocalDate.of(2021,Month.NOVEMBER,06);
        LocalDate fecha8 = LocalDate.of(2018,Month.MAY,30);
        LocalDate fecha9 = LocalDate.of(2019,Month.JULY,07);
        
        Camara cam3 = new Camara("M200","Canon");
        Camara cam4 = new Camara("H980","Nikon");
        
        Imagen imC1 = new Imagen("imagenes/picMiguel.png","sesion de fotos Miguel","Guayaquil",cam3,fecha2);
        imC1.setNombre("picMiguel");
        Imagen imC2 = new Imagen("imagenes/picSiblings.jpg","foto Navidad","Guayaquil",cam3,fecha3);
        imC2.setNombre("picSiblings");
        Imagen imC3 = new Imagen("imagenes/pedrera.jpg","paisaje pedrera","La Pedrera-Casa Milà",cam3,fecha4);
        imC3.setNombre("pedrera");
        Imagen imC4 = new Imagen("imagenes/picBday.jpg","Cumple 20 Mei","Guayaquil",cam4,fecha5);
        imC4.setNombre("picBday");
        Imagen imC5 = new Imagen("imagenes/fotoParque.jpg","Viaje en Barcelona","Parque del Laberinto de Horta",cam4,fecha6);
        imC5.setNombre("fotoParque");        
        Imagen imC6 = new Imagen("imagenes/fotoParque2.jpg","Viaje en Europa","Barcelona España",cam4,fecha6);
        imC6.setNombre("fotoParque2");        
        Imagen imC7 = new Imagen("imagenes/picFood.jpg","Desayuno familiar","La Veredita",cam4,fecha7);
        imC7.setNombre("picFood");  
        Imagen imC8 = new Imagen("imagenes/venecia.jpg","Viaje en Italia","Venecia",cam3,fecha8);
        imC8.setNombre("Venecia");        
        Imagen imC9 = new Imagen("imagenes/italia.jpg","","Fontana di Trevi",cam4,fecha9);
        imC9.setNombre("Italia");          
        
        List<String> ppl=new ArrayList();
        ppl.addLast("Miguel Chang");
        imC1.setPersonas(ppl);
        
        List<String> ppl2=new ArrayList();
        ppl2.addLast("Kevin Chang");
        ppl2.addLast("Joseph Chang");
        ppl2.addLast("Meiyin Chang");
        ppl2.addLast("Su lin Chang");
        imC2.setPersonas(ppl2);
        
        List<String> ppl3=new ArrayList();
        ppl3.addLast("Meiyin Chang");
        ppl3.addLast("Miguel Chang");
        imC4.setPersonas(ppl3);
        
        List<String> ppl4=new ArrayList();
        ppl4.addLast("Vanessa Rizzo");
        ppl4.addLast("Meiyin Chang");
        imC5.setPersonas(ppl4);
        
        List<String> ppl5=new ArrayList();
        ppl5.addLast("Meiyin Chang");
        ppl5.addLast("Su lin Chang");
        ppl5.addLast("Vanessa Rizzo");
        imC8.setPersonas(ppl5);
        
        imC1.setReaccion("Me gusta");
        imC2.setReaccion("Me gusta");
        imC3.setReaccion("Me gusta");
        imC4.setReaccion("Me encanta");
        imC5.setReaccion("Me encanta");
        imC6.setReaccion("Me encanta");
        imC7.setReaccion("Me entristece");
        imC8.setReaccion("Me gusta");
        imC9.setReaccion("Me gusta");
        
        Album albumMei = new Album("Album de Mei", "Cosas random");        
        albumMei.agregarImagen(imC1);
        albumMei.agregarImagen(imC2);
        albumMei.agregarImagen(imC3);
        albumMei.agregarImagen(imC4);
        albumMei.agregarImagen(imC5);
        albumMei.agregarImagen(imC6);
        albumMei.agregarImagen(imC7);
        albumMei.agregarImagen(imC8);
        albumMei.agregarImagen(imC9);
        albunes.addLast(albumMei);
        
        
        Album album3 = new Album("Todas las fotos", "Galeria de fotos");
        //albunes.addLast(album3);
        for(Album al:albunes){
            List<Imagen> i=al.getContenido();
            for(Imagen im:i){
                album3.agregarImagen(im);
            }
        }
        albunes.addLast(album3);
        
        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream("serializados/listadoAlbumes.ser"))) {
            ou.writeObject(App.albunes);
        } catch (IOException e) {
            e.getMessage();
        }

        try (ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream("serializados/listadoCamaras.ser"))) {
            ou.writeObject(App.listadoCamaras);
        } catch (IOException e) {
            e.getMessage();
        }
        */
        albunes = Album.cargarAlbunes("serializados/listadoAlbumes.ser");

        listadoCamaras = Camara.cargarCamaras("serializados/listadoCamaras.ser");

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