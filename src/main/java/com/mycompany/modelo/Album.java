package com.mycompany.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.mycompany.modelo.Imagen;
import com.mycompany.utilidades.*;

public class Album implements Serializable {
    private String nombre;
    private String description;
    private List<Imagen> contenido;
    
    public Album(String name, String desc ){
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
    
    @Override
    public String toString(){
        return getNombre();
    }

    public static ArrayList<Album> cargarAlbunes(String ruta) {
        ArrayList<Album> resultado = new ArrayList<>();
        System.out.println("xxxxxxxxxxxxx");
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ruta))) {
            resultado = (ArrayList<Album>) oi.readObject();
            oi.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        } catch (ClassNotFoundException  ex) {
            System.out.println("error class:"+ex.getMessage());
        }
        return resultado;
    }
    
    

}
