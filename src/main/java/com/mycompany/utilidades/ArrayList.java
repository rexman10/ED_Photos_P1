
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
    
    public void concat(E[] e) throws ConcatException {
        if (e != null) {
            if ((this.size() + e.length) > this.capacity) {
                this.addCapacity();
            }
            for (int i = this.size(), j = 0; i < (this.size() + e.length) - 1 && j < e.length; i++, j++) {
                this.elements[i] = e[j];
            }

        } else {
            throw new ConcatException("");
        }
    }
    
    
    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = e;
            return true;
        } else if (isFull()) {
            addCapacity();
        }
        // desplazamiento o bit shifting 
        for (int i = effectiveSize - 1; i >=0; i--) {
            elements[i+1] = elements[i]; 
        }
        elements[0] = e;
        effectiveSize++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        
        if (e == null){
            return false;
            
        } else if(isEmpty()){
            elements[effectiveSize++] = e;
            return true;
           
        } else {
            
            if(isFull()) addCapacity();
            
            elements[effectiveSize++] = e;
            return true;
        }
        
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()) throw new EmptyListException();
        
        E e = elements[0];
        
        for(int i = 0; i<effectiveSize-2; i++){
            elements[i] = elements[i+1];
        }
        
        elements[--effectiveSize] = null;
            
        return e;
    }

    @Override
    public E removeLast() {
        if(isEmpty()) throw new EmptyListException();
        
        E e = elements[effectiveSize-1];
        elements[--effectiveSize] = null;
        
        return e;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public E remove(int index) {
        if(isEmpty()) throw new EmptyListException();
        if(index > effectiveSize-1 || index < -1) throw new IndexOutOfBoundsException("Indice fuera de rango.");
        if(index == 0) return removeFirst();
        if(index == -1 || index == effectiveSize-1) return removeLast();
        
        E e = elements[index];
        
        for(int i = index; i<effectiveSize-2; i++){
            elements[i] = elements[i+1];
        }
        
        elements[--effectiveSize] = null;
        return e;
    }

    @Override
    public E get(int index) {
        
        if(isEmpty()) throw new EmptyListException();
        if(index > effectiveSize-1 || index < -1) throw new IndexOutOfBoundsException("Indice fuera de rango.");
        if(index == 0) return elements[0];
        if(index == -1 || index == effectiveSize-1) return elements[effectiveSize-1];
        
        return elements[index];
        
    }
    
    @Override
    public E set(int index, E e){
        E nuevo = get(index);
        elements[index] = e;
        return nuevo;
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
