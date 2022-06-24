/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;
import com.mycompany.modelo.Album;
import com.mycompany.modelo.Camara;
import com.mycompany.proyecto_ed_photos_p1.App;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Comparator;

import com.mycompany.utilidades.*;

import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author alex_
 */
public class Imagen implements Serializable, Comparator<Imagen> {
    private final String path = "imagenes/";
    private String description;
    private String lugar;
    private String nombre;
    private Camara camara;
    private List<String> personas;
    private Calendar fecha_tomada;
    private List<Album> storedIn;
    private String reaccion;
    private List<String> comments;
    private List<String> keywords;

    @Override
    public int compare(Imagen i1, Imagen i2) {
        return i1.getFecha_tomada().toString().compareTo(i2.getFecha_tomada().toString());
    }

    public void moveToAlbum(Album albun){
        albun.getContenido().addLast(this);
    }

    public void removeFromAlbum(Album albun){
        App.mostrarAlerta(AlertType.CONFIRMATION, "Desea eliminar la imagen?");
        int indice = albun.getContenido().indexOf(this);
        albun.getContenido().remove(indice);
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Camara getCamara() {
        return camara;
    }

    public void setCamara(Camara camara) {
        this.camara = camara;
    }

    public List<String> getPersonas() {
        return personas;
    }

    public void setPersonas(List<String> personas) {
        this.personas = personas;
    }

    public Calendar getFecha_tomada() {
        return fecha_tomada;
    }

    public void setFecha_tomada(Calendar fecha_tomada) {
        this.fecha_tomada = fecha_tomada;
    }

    public List<Album> getStoredIn() {
        return storedIn;
    }

    public void setStoredIn(List<Album> storedIn) {
        this.storedIn = storedIn;
    }

    public String getReaccion() {
        return reaccion;
    }

    public void setReaccion(String reaccion) {
        this.reaccion = reaccion;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
    
    

}
