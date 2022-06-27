package com.mycompany.modelo;

import java.io.Serializable;

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
    
    
}
