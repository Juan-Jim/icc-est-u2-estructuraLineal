package controllers;

import java.util.NoSuchElementException;
import models.NodeGeneric;
import models.Persona;

public class QueueG<T extends Persona> {

    private NodeGeneric<T> primero;
    private NodeGeneric<T> ultimo;
    private int size;

    public QueueG() {
        this.primero = null;
        this.ultimo = null;
        this.size = 0;
    }

    public void add(T value) {
        NodeGeneric<T> nuevo = new NodeGeneric<>(value);
        if (isEmpty()) {
            primero = ultimo = nuevo;
        } else {
            ultimo.setNext(nuevo);
            ultimo = nuevo;
        }
        size++;
    }

    public T remove() {
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");
        T aux = primero.getValue();
        primero = primero.getNext();
        if (primero == null) ultimo = null;
        size--;
        return aux;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("La cola está vacía");
        return primero.getValue();
    }

    public boolean isEmpty() {
        return primero == null;
    }

    public int size() {
        return size;
    }

    public void printCola() {
        NodeGeneric<T> aux = primero;
        while (aux != null) {
            System.out.print(aux.getValue() + " | ");
            aux = aux.getNext();
        }
        System.out.println();
    }

    public T findByName(String nombre) {
        NodeGeneric<T> aux = primero;
        while (aux != null) {
            if (aux.getValue().getNombre().equalsIgnoreCase(nombre)) {
                return aux.getValue();
            }
            aux = aux.getNext();
        }
        return null;
    }

    public T deleteByName(String nombre) {
        NodeGeneric<T> aux = primero;
        NodeGeneric<T> prev = null;

        while (aux != null) {
            if (aux.getValue().getNombre().equalsIgnoreCase(nombre)) {
                if (prev == null) {
                    
                    primero = aux.getNext();
                    if (primero == null) ultimo = null;
                } else {
                    prev.setNext(aux.getNext());
                    if (aux == ultimo) {
                        ultimo = prev;
                    }
                }
                size--;
                return aux.getValue();
            }
            prev = aux;
            aux = aux.getNext();
        }

        return null; 
    }
}
