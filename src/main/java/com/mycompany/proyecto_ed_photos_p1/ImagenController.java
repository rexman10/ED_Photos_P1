/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyecto_ed_photos_p1;

import com.mycompany.modelo.Album;
import com.mycompany.modelo.Camara;
import com.mycompany.modelo.Imagen;
import com.mycompany.utilidades.ArrayList;

import static com.mycompany.proyecto_ed_photos_p1.App.*;
import com.mycompany.utilidades.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
//import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
/**
 * FXML Controller class
 *
 * @author diegomartinez
 */
public class ImagenController implements Initializable {


    @FXML
    private Button cancelar;
    @FXML
    private ImageView previewImagen;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtLugar;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<Album> comboAlbum;
    @FXML
    private TextField txtModeloC;
    @FXML
    private TextField txtMarcaC;
    @FXML
    private TableView<Imagen> tablaPersonas;
    @FXML
    private Label titulo;
    @FXML
    private Button botonGuardar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        llenarCombo();
 
        
    }    
    
    //DIEGO 
    public void llenarCombo() {
        
        for(Album a:albunes){
            comboAlbum.getItems().add(a);
            
        }
    }
    
    @FXML
    private void elegirImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar archivo");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen
        if (imgFile != null) {
            try{
                Image image = new Image("file:" + imgFile.getAbsolutePath());
                System.out.println(imgFile.getAbsolutePath());
                previewImagen.setImage(image);
                //copiar la imagen
                
                Path from = Paths.get(imgFile.toURI());
                Path to = Paths.get("src/main/resources/com/mycompany/proyecto_ed_photos_p1/imagenes/"+ imgFile.getName());
                Files.copy(from, to);
                
            }catch(FileNotFoundException fe){
                System.out.println("FILE NOT FOUND Exception:" + fe.getMessage()); 
                
            }catch(FileAlreadyExistsException fe){
                System.out.println("FILE AlreadyExistsException:" + fe.getMessage()); 
                App.mostrarAlerta(Alert.AlertType.ERROR, "Foto repetida");
                
            }catch(Exception e){
                System.out.println("Exception:" + e); 
            }            
        }
    }

    @FXML
    private void switchToMenu(ActionEvent event) throws IOException {
        App.setRoot("principalMenu");
    }

    @FXML
    private void guardarImagen(ActionEvent event) throws IOException {
        System.out.println("///// PROCESO PARA GUARDAR IMAGEN //////////");
        
        //PROCESO PARA OBTENER RUTA FOTO
        Image im= previewImagen.getImage();
        String url= im.getUrl();    
        File file = new File(url);
        String foto = file.getName();
        String ruta="imagenes/"+foto;
        System.out.println(ruta);
        
         
        
        String desc= txtDescription.getText(); //string descripcion
        String lugar= txtLugar.getText(); //string lugar
        LocalDate fecha= date.getValue(); //string fecha
        
        
        //PROCESO PARA CREAR CAMARA DE LA IMAGEN
        String modelo= txtModeloC.getText(); //string modelo de la camara
        String marca= txtMarcaC.getText(); //string marca de la camara
        Camara camara= new Camara(modelo,marca);//CAMARA CREADA
        
        Imagen imagen= new Imagen(ruta,desc,lugar,camara,fecha);
        imagen.setNombre(foto);
        comboAlbum.getValue().agregarImagen(imagen);
        System.out.println("IMAGEN AGREGADA AL ALBUM: "+comboAlbum.getValue());
        App.mostrarAlerta(Alert.AlertType.INFORMATION, "Nueva imagen agregada exitosamente al albúm: "+comboAlbum.getValue());
        App.setRoot("principalMenu");
        
    }

    public void llenarCampos(Imagen imagen, Album album) {
        titulo.setText("Editar Imagen");
        txtDescription.setText(imagen.getDescription());
        txtLugar.setText(imagen.getLugar());
        date.setValue(imagen.getFecha_tomada());
        comboAlbum.setValue(album);
        txtModeloC.setText(imagen.getCamara().getModelo());
        txtMarcaC.setText(imagen.getCamara().getMarca());
        
        //CARGAR FOTO ORIGINAL DE LA IMAGEN
         InputStream input = null;
         try {
            String fileName = imagen.getPath();//armar la ruta de la foto
            
            //abrir el stream de la imagen de la persona
            input = App.class.getResource(fileName).openStream();

            //crear la imagen 
            Image image = new Image(input, 1000, 1000, false, false);
            previewImagen.setImage(image);

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
        
        //GUARDAMOS EL ALBUM ESCOGIDO ORIGINALMENTE
        Album alOriginal=comboAlbum.getValue();
        
        
        //NUEVA FUNCION DEL BOTON GUARDAR EN EDITAR IMAGEN
        botonGuardar.setOnAction(event -> { 
            /*
            List<Imagen> listaOriginal=alOriginal.getContenido();
            List<Imagen> lista=listaOriginal;
            
            int n=0;
            for(Imagen im:listaOriginal){
                if(im.getPath().equals(imagen.getPath())){
                    lista.remove(n);
                }
            }*/
            
            
            //CODIGO DE GUARDAR LOS DATOS EDITADOS DE UNA IMAGEN
            imagen.setDescription(txtDescription.getText());
            imagen.setLugar(txtLugar.getText());
            imagen.setFecha_tomada(date.getValue());
            Camara camara=new Camara(txtModeloC.getText(),txtMarcaC.getText());
            imagen.setCamara(camara);
            
            //SI EL ALBUM ES DISTINTO, LO ELIMINAMOS DEL ALBUM ORIGINAL
            if(!alOriginal.getNombre().equals(comboAlbum.getValue().getNombre())){
                
                List<Imagen> listaOriginal=alOriginal.getContenido();
                List<Imagen> lista=listaOriginal;

                int n=0;
                for(Imagen im:listaOriginal){
                    if(im.getNombre().equals(imagen.getNombre())){
                        lista.remove(n);
                    }
                    n++;
                }
                
                alOriginal.setContenido(lista);
            }
            System.out.println("ELIMINADO DEL ALBUM ORIGINAL");
            

            //PROCESO PARA OBTENER RUTA FOTO
            
            Image im= previewImagen.getImage();
            //System.out.println(im);
            String url= im.getUrl();   
            System.out.println(url);
            if(url!=null){
                File file = new File(url);
                String foto = file.getName(); //NOMBRE DE LA IMAGEN
                String ruta="imagenes/"+foto;  //RUTA DE LA IMAGEN
                //System.out.println(ruta);

                imagen.setNombre(foto);
                imagen.setPath(ruta);
            }
            /*
            File file = new File(url);
            String foto = file.getName(); //NOMBRE DE LA IMAGEN
            String ruta="imagenes/"+foto;  //RUTA DE LA IMAGEN
            //System.out.println(ruta);
            
            imagen.setNombre(foto);
            imagen.setPath(ruta);*/
            
             //SI EL ALBUM ES DISTINTO, LO AÑADIMOS AL NUEVO ALBUM
            if(!alOriginal.getNombre().equals(comboAlbum.getValue().getNombre())){
                comboAlbum.getValue().agregarImagen(imagen);
            }
            
            App.mostrarAlerta(Alert.AlertType.INFORMATION, "Imagen editada exitosamente");
            try {
                App.setRoot("principalMenu");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            
        });
        
        
        
        
    }


}
