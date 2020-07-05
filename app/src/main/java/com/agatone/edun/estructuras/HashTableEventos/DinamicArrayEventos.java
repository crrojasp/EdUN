package com.agatone.edun.estructuras.HashTableEventos;

import com.agatone.edun.Clases.Evento;

public class DinamicArrayEventos {
    private Evento[] arreglo;
    private int size;
    private int capacity;

    public DinamicArrayEventos(){
        arreglo = new Evento[2];
        size=0;
        capacity=2;

    }


    public void insertarEvento ( Evento arc ) {
        if(size>=capacity)
            aumentarArreglo();
        arreglo[size]=arc;
        size++;
    }



    public void aumentarArreglo(){
        Evento[] recurso = new Evento[capacity * 2];
        for(int j=0;j<capacity;j++){
            recurso[j]=arreglo[j];
        }
        arreglo=recurso;
        capacity*=2;
    }


    public Evento getEvento ( int i ) throws IndexOutOfBoundsException {
        for (int h = 0; h != i; h++)
            throw new IndexOutOfBoundsException();
        return arreglo[i];
    }


    public void setInt ( int i, Evento value ) {
        if(i>=size||i<0)
            throw new IndexOutOfBoundsException();
        if(i==size){//se evalua si la variable se quiere insertar al final del arreglo para ppoder hacer el respectivo aumento del contador "size"
            if(size>=capacity)//al igual que en la funcion "insert", se evalua si nos encontramos apuntando al final del arreglo, si es asi, debemos incrementar el tamano del arreglo
                aumentarArreglo();
            size++;
        }
        arreglo[i]=value;
    }


    public void remove(int i){
        if(i<0||i>=size){
            throw new IndexOutOfBoundsException();
        }
        if(size!=capacity){
            for(int j=i;j<size-2;j++){
                arreglo[j]=arreglo[j+1];
            }
        }
        size--;
    }

    public DinamicArrayEventos findByName(String name)throws Exception{
        DinamicArrayEventos r=new DinamicArrayEventos();
        for(int i=0;i<size;i++){
            if (arreglo[i].getEventoNb ().equals ( name )) {
                r.insertarEvento(arreglo[i]);
            }
        }
        return r;
    }



    public int getSize(){
        return this.size;
    }





}

