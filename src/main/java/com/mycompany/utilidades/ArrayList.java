
package com.mycompany.utilidades;

import java.util.Iterator;

public class ArrayList<E> implements List<E>{

    private int effectiveSize = 0;
    private int capacity = 100;
    private E[] elements = null;
    
    public ArrayList(){
        this.elements = (E[]) (new Object[capacity]);
        this.effectiveSize = 0;
    }
    
    private boolean isFull(){
        return effectiveSize==capacity;
    }
    
    private void addCapacity() {
        E[] nuevo =  (E[]) new Object [capacity * 2];
        for (int i=0; i<elements.length; i++) {
            nuevo[i] = elements[i];
        }
        elements = nuevo;
        capacity *= 2;
    }
    
    public void concat(ArrayList<E> newArrayList) {
        // Verificamos que el ArrayList recibido no sea null ...(1)
        if (newArrayList != null) {
            //Recorremos el ArrayList entrante
            for (int i = 0; i < newArrayList.size(); i++) {
                //Si nuestro ArrayList local se llena ...(2)
                if (isFull()) {
                    //(2)...aumentamos su capacidad 
                    addCapacity();
                }
                //Concatenamos al final del arreglo local
                //los elementos del arreglo entrante
                elements[effectiveSize++] = newArrayList.get(i);
            }
        } else {
            // (1)... caso contrario lanzamos una excepción 
            throw new NullPointerException();
        }
    }
    
    
    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        int index = effectiveSize;
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()) throw new EmptyListException();
        
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(this.effectiveSize - 1);
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public E remove(int index) {
        E elementToRemove = null;
        if (this.isEmpty() || index >= this.effectiveSize || index<0) {
            throw new IndexOutOfBoundsException();
        } else {
            elementToRemove = elements[index];
            for (int i = index; i < this.effectiveSize - 1; i++) {
                elements[i] = elements[i + 1];
            }
            this.effectiveSize--;
        }
        return elementToRemove;
    }
    
    @Override
    public E get(int index) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        } else {
            return elements[index];
        }
    }

    public E getLast(){
        if(isEmpty()) throw new EmptyListException();
        return this.get(this.effectiveSize-1);
    }
    
    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public boolean add(E e, int index) {
        if(e == null) return false;
        if(isEmpty()) throw new EmptyListException();
        if(index > effectiveSize-1 || index < -1) throw new IndexOutOfBoundsException("Índice no admitido.");
        if(index == 0) return addFirst(e);
        if(index == -1 || index == effectiveSize-1) return addLast(e);
        
        if(isFull()) addCapacity();
        
        for(int i = effectiveSize-1; i>=index; i--){
           elements[i+1] = elements[i];
        }
        
        elements[index] = e;
        return true;
    }

    @Override
    public void clear() {
        if(isEmpty()) return;
        capacity = effectiveSize;
        elements = (E[]) new Object[capacity];
        effectiveSize = 0;
    }

    @Override
    public int indexOf(E e) {
        if(e == null) return -1;
        if(isEmpty()) throw new EmptyListException();
        if(e == elements[effectiveSize-1]) return effectiveSize-1;
        
        for(int i = 0; i<effectiveSize-1; i++){
            if(elements[i] == e) return i;
        }
        
        return -1;
    }
    
    @Override
    public boolean contains(E e){
        for(int i = 0; i<effectiveSize; i++){
            if(elements[i].equals(e)) return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        String s = "";
        for (int i = 0;i < effectiveSize; i++){
            s += elements[i] + ", ";
        }
        return s;
    }
    
    @Override
    public Iterator<E> iterator() {
        
        Iterator<E> it = new Iterator<E>(){
            
            int cursor = 0;
            
            @Override
            public boolean hasNext(){
                return cursor<effectiveSize;
            }
            
            @Override
            public E next(){
                return elements[cursor++];
            }
            
        };
        return it;
    }
    
}
