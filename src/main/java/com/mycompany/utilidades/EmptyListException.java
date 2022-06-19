
package com.mycompany.utilidades;

public class EmptyListException extends RuntimeException {
    
    public EmptyListException(){
        super("La lista está vacía.");
    }
    
}
