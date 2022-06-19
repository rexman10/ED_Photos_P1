/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utilidades;

/**
 *
 * @author rsgar
 * @param <E>
 */
public class DoubleNodeList<E> {
    
    private E content;
    private DoubleNodeList<E> next;
    private DoubleNodeList<E> previous;
    
    public DoubleNodeList(){
        next = null;
        previous = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public DoubleNodeList<E> getNext() {
        return next;
    }

    public void setNext(DoubleNodeList<E> next) {
        this.next = next;
    }
    
    public DoubleNodeList<E> getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNodeList<E> previous) {
        this.previous = previous;
    }
    
}
