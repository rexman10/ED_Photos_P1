package com.mycompany.modelo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.mycompany.utilidades.ArrayList;
import com.mycompany.utilidades.List;

public class Camara implements Serializable{
    private String modelo;
    private String marca;

    public Camara(String modelo, String marca){
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public static ArrayList<Camara> cargarCamaras(String ruta) {
        ArrayList<Camara> resultado = new ArrayList<>();
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ruta))) {
            resultado = (ArrayList<Camara>) oi.readObject();
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
