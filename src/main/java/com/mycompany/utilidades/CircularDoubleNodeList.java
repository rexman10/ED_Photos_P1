/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utilidades;

import java.io.Serializable;

/**
 *
 * @author rsgar
 */
class CircularDoubleNodeList<E> implements Serializable {
    
    private E content;
    private CircularDoubleNodeList<E> next;
    private CircularDoubleNodeList<E> previous;
    
    public CircularDoubleNodeList(){
        next = this;
        previous = this;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public CircularDoubleNodeList<E> getNext() {
        return next;
    }

    public void setNext(CircularDoubleNodeList<E> next) {
        this.next = next;
    }
    
    public CircularDoubleNodeList<E> getPrevious() {
        return previous;
    }

    public void setPrevious(CircularDoubleNodeList<E> previous) {
        this.previous = previous;
    }
    
}