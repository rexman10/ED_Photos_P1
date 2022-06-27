package com.mycompany.utilidades;

public class EmptyFieldException extends Exception{
    public EmptyFieldException(){
        super("Verifique que los campos esten llenos.");
    }
}