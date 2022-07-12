/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto_ed_photos_p1;

import com.mycompany.modelo.Camara;
import com.mycompany.utilidades.ArrayList;
import com.mycompany.utilidades.List;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author alex_
 */
public class TablaCamarasController implements Initializable {


    @FXML
    private Label lbTitulo;
    @FXML
    private TableView<Camara> tvCamaras;
    @FXML
    private Button btVolver;
    @FXML
    private Button btAgregarCamara;
    @FXML
    private TableColumn<Camara, String> colModelo;
    @FXML
    private TableColumn<Camara, String> colMarca;
    @FXML
    private TableColumn<Camara, Void> colOpc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
        agregarOpciones();
        for (Camara cam : App.listadoCamaras) {
            System.out.println(cam.getMarca() + " - " + cam.getModelo());
            tvCamaras.getItems().add(cam);
        }
        actualizarListaCamaras();
        
    }
    
    @FXML
    void switchToMenu(ActionEvent event) throws IOException {
        App.setRoot("principalMenu");
    }

    @FXML
    private void agregarOpciones() {

        Callback<TableColumn<Camara, Void>, TableCell<Camara, Void>> cellFactory = new Callback<TableColumn<Camara, Void>, TableCell<Camara, Void>>() {
            @Override
            public TableCell<Camara, Void> call(final TableColumn<Camara, Void> param) {
                TableCell<Camara, Void> cell = new TableCell<Camara, Void>() {
                   
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            //hbox para ubicar los botones
                            HBox hbOpciones = new HBox(5);
                            //recuperar el concurso de la fila
                            Camara camara = getTableView().getItems().get(getIndex());
                            //boton editar
                            Button btnEd = new Button("Editar");
                            btnEd.setOnAction(e -> editarCamara(camara));
                            //boton eliminar
                            Button btnEl = new Button("Eliminar");
                            btnEl.setOnAction(e -> eliminarCamara(camara));
                            //se agregan botones al hbox
                            hbOpciones.getChildren().addAll(btnEd,btnEl);
                            //se ubica hbox en la celda
                            setGraphic(hbOpciones);
                        }
                    }
                };
                return cell;
            }
        };

        colOpc.setCellFactory(cellFactory);

    }
    
    @FXML
    private void editarCamara(Camara cam){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("agregarCamara.fxml"));
            AgregarCamaraController ct = new AgregarCamaraController();
            fxmlLoader.setController(ct);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            ct.llenarCampos(cam);
            ct.editarCamara(cam);
            stage.setTitle("Editar Camara");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            actualizarListaCamaras();
        } catch (IOException e) {
            e.printStackTrace();
        }                
    }
    
        
    @FXML
    private void eliminarCamara(Camara cam){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar una camara");
        alert.setHeaderText("Notificacion");
        alert.setContentText("Esta seguro que desea eliminar esta camara?");
    
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            System.out.println(cam);
            int indice = App.listadoCamaras.indexOf(cam);
            App.listadoCamaras.remove(indice);
            actualizarListaCamaras();
        }
    }
    
    @FXML
    private void agregarCamara(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("agregarCamara.fxml"));
            AgregarCamaraController ct = new AgregarCamaraController();
            fxmlLoader.setController(ct);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Nueva Camara");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
            actualizarListaCamaras();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void actualizarListaCamaras() {     
            List<Camara> listado_actualizado = App.listadoCamaras;
            tvCamaras.getItems().clear();
            agregarOpciones();//en este metodo se llenan los botones para cada fila
            for (Camara cam : listado_actualizado) {
                //System.out.println(cam.getMarca() + " - " + cam.getModelo());
                tvCamaras.getItems().add(cam);
            }
    }
    
}
