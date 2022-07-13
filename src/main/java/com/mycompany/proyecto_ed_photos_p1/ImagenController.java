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
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
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
    private TableView<String> tablaPersonas;
    @FXML
    private TableColumn<String, String> colNombres;
    @FXML
    private TableColumn<String, Void> colOpciones;
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
        Camara nuevo = new Camara(modelo,marca);//CAMARA CREADA
        System.out.println(encontrarCamara(nuevo));
        if (encontrarCamara(nuevo) == -1){
            App.listadoCamaras.addLast(nuevo);
            Imagen imagen= new Imagen(ruta,desc,lugar,nuevo,fecha);
        } 
        Imagen imagen= new Imagen(ruta,desc,lugar,App.listadoCamaras.get(encontrarCamara(nuevo)),fecha);
        imagen.setNombre(foto);
        
        //PROCESO PARA GUARDAR LAS PERSONAS DE LA FOTO
        List<String> listaPersonas= new ArrayList();
        for(String p:tablaPersonas.getItems()){
            listaPersonas.addLast(p);
        }
        imagen.setPersonas(listaPersonas);
        
        //CONDICION PARA GUARDAR LA IMAGEN EN EL ABUM "Todas las fotos"
        //comboAlbum.getValue().agregarImagen(imagen);
        if(comboAlbum.getValue().getNombre().equals("Todas las fotos")){
            comboAlbum.getValue().agregarImagen(imagen);
        }else{
            comboAlbum.getValue().agregarImagen(imagen);
            for(Album a:albunes){
                if(a.getNombre().equals("Todas las fotos")){
                    a.agregarImagen(imagen);
                }
            }
        }
        
        
        System.out.println("IMAGEN AGREGADA AL ALBUM: "+comboAlbum.getValue());
        App.mostrarAlerta(Alert.AlertType.INFORMATION, "Nueva imagen agregada exitosamente al albúm: "+comboAlbum.getValue());
        App.setRoot("principalMenu");
        System.out.println("tamanio: "+App.listadoCamaras.size());
        for (Camara camara : App.listadoCamaras){
            System.out.println(camara.getMarca() + " - " + camara.getModelo());
        }
        
        
        
    }
    
    public static int encontrarCamara(Camara cam){
        int indice = 0;
        for (Camara camara : App.listadoCamaras){
            if (cam.getModelo().equals(camara.getModelo()) && cam.getMarca().equals(camara.getMarca())){
                return indice;
            }
            indice++;
        }
        return -1;           
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
            
            //GUARDAR LOS DATOS DE LA LISTA DE PERSONAS AL ATRIBUTO PERSONAS DE LA IMAGEN
            List<String> listaActualizada= new ArrayList();
            for(String p:tablaPersonas.getItems()){
                listaActualizada.addLast(p);
            }
            
            imagen.setPersonas(listaActualizada); 
            
            App.mostrarAlerta(Alert.AlertType.INFORMATION, "Imagen editada exitosamente");
            try {
                App.setRoot("principalMenu");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            
            
            
        });
        
        if(!imagen.getPersonas().isEmpty()){
            
            colNombres.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
            List<String> personas=imagen.getPersonas();
            
            
            for(String p:personas){
                tablaPersonas.getItems().add(p);
            }
            agregarOpciones();
            
        } 
        
        
    }
    
     private void agregarOpciones() {
         Callback<TableColumn<String, Void>, TableCell<String, Void>> cellFactory = new Callback<TableColumn<String, Void>, TableCell<String, Void>>() {
            @Override
            public TableCell<String, Void> call(final TableColumn<String, Void> param) {
                TableCell<String, Void> cell = new TableCell<String, Void>() {
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //System.out.println("ENTRO AL ELSE");
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el concurso de la fila
                            String c = getTableView().getItems().get(getIndex());
                            
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(e ->editarPersona(c)); //FUNCION PARA CADA BOTON
                            
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e ->eliminarPersona(c));  //FUNCION PARA CADA BOTON
                            
                            //se agregan botones al hbox
                            hbOpciones.getChildren().add(btnEd);
                            hbOpciones.getChildren().add(btnEl);
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                            
                        }
                    }
                };
                return cell;
            }
        };
        colOpciones.setCellFactory(cellFactory);
         
     }
     
     private void editarPersona(String a) {
         
         System.out.println("Editar persona: "+a);
         //System.out.println(imagen.getNombre());
         
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("persona.fxml"));//no tiene el controlador especificado
            PersonaController ct = new PersonaController();
            
            fxmlLoader.setController(ct);//se asigna el controlador
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            ct.llenarCampos(a);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            String p=PersonaController.persona;
            PersonaController.persona=null;
            
            
            //List<String> listaPersonas=imagen.getPersonas();
            //List<String> listaPersonas=tablaPersonas.getItems();
            List<String> nuevasPersonas= new ArrayList();
            //VERIFICAMOS SI EL NOMBRE "NUEVO" Y EL ASIGNADO ANTERIORMENTE NO SON IGUALES
            if(!a.equals(p)){
                for(String person:tablaPersonas.getItems()){
                    
                    if(!person.equals(a)){
                        nuevasPersonas.addLast(person);
                    }
                    if(person.equals(a)){
                        System.out.println("NOMBRE ANTIGUO: "+a);
                        System.out.println("NOMBRE NUEVO: "+p);
                        nuevasPersonas.addLast(p);
                    }
                    
                }
                
                //VACIAMOS LA TABLA DE PERSONAS
                tablaPersonas.getItems().clear();
                //LLENAMOS NUEVAMENTE LA TABLA CON LA LISTA DE PERSONAS ACTUALIZADAS
                colNombres.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
                for(String person:nuevasPersonas){
                    tablaPersonas.getItems().add(person);
                }
                //imagen.setPersonas(nuevasPersonas);
                
                App.mostrarAlerta(Alert.AlertType.INFORMATION,"Persona editada con exito");
                
            }


            }catch (IOException ex) {
                ex.printStackTrace();
            }
         
         
         
     }
     
     private void eliminarPersona(String a) {
         System.out.println("Eliminar persona: "+a);
         //System.out.println(imagen.getNombre());
         
         int n=App.confirmacion("¿Desea eliminar la persona seleccionada?");
            if(n!=0){
                List<String> nuevasPersonas= new ArrayList();
                for(String person:tablaPersonas.getItems()){
                    if(!person.equals(a)){
                         nuevasPersonas.addLast(person);
                    }    
                }
                //VACIAMOS LA TABLA DE PERSONAS
                tablaPersonas.getItems().clear();
                //LLENAMOS NUEVAMENTE LA TABLA CON LA LISTA DE PERSONAS ACTUALIZADAS
                colNombres.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
                for(String person:nuevasPersonas){
                    tablaPersonas.getItems().add(person);
                }
                //imagen.setPersonas(nuevasPersonas);

                App.mostrarAlerta(Alert.AlertType.INFORMATION,"Persona eliminada con exito");
            
            }
         
            /*
         //List<String> listaPersonas=imagen.getPersonas();
         List<String> nuevasPersonas= new ArrayList();
         for(String person:tablaPersonas.getItems()){
             if(!person.equals(a)){
                  nuevasPersonas.addLast(person);
             }    
         }
         //VACIAMOS LA TABLA DE PERSONAS
         tablaPersonas.getItems().clear();
         //LLENAMOS NUEVAMENTE LA TABLA CON LA LISTA DE PERSONAS ACTUALIZADAS
         colNombres.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
         for(String person:nuevasPersonas){
             tablaPersonas.getItems().add(person);
         }
         //imagen.setPersonas(nuevasPersonas);
         
         App.mostrarAlerta(Alert.AlertType.INFORMATION,"Persona eliminada con exito");
         */
         
         
     }
    
    @FXML
    private void agregarPersona(ActionEvent event) {
        
        
         try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("persona.fxml"));//no tiene el controlador especificado
            PersonaController ct = new PersonaController();

            fxmlLoader.setController(ct);//se asigna el controlador
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            String p=PersonaController.persona;
            PersonaController.persona=null;
            System.out.println("PERSONA SUPUESTAMENTE AÑADIDA: "+p);
            aggPerson(p);
            

            }catch (IOException ex) {
                ex.printStackTrace();
            }

    }
    
    private void aggPerson(String person) {
        if(!person.isEmpty()){
            
            //CONDICION PARA VISUALIZAR LOS NOMBRES DE LAS PERSONAS
            colNombres.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
            
            tablaPersonas.getItems().add(person);
            
            agregarOpciones();
 
            App.mostrarAlerta(Alert.AlertType.INFORMATION,"Persona añadida con exito");
            
            
            
        }
        
    }


}
