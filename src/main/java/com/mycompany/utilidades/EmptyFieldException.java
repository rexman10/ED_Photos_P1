package com.mycompany.utilidades;

public class EmptyFieldException extends RuntimeException{
    public EmptyFieldException(){
        super("Verifique que los campos esten llenos.");
    }
}