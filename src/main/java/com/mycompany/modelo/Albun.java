package com.mycompany.modelo;

import java.io.Serializable;

import com.mycompany.modelo.Imagen;
import com.mycompany.utilidades.*;

public class Albun implements Serializable {
    private String nombre;
    private String description;
    private List<Imagen> contenido;
    
    public Albun(String name, String desc ){
        List<Imagen> listado = new CircularDoubleLinkedList<>();
        this.nombre = name;
        this.description = desc;
        this.contenido = listado;
    }

    public void agregarImagen(Imagen imagen){
        this.getContenido().addLast(imagen);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Imagen> getContenido() {
        return contenido;
    }

    public void setContenido(List<Imagen> contenido) {
        this.contenido = contenido;
    }
    
    

}
