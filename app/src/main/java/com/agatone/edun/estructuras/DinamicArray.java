package com.agatone.edun.estructuras;

import com.agatone.edun.Clases.archivo;

public class DinamicArray {
    private archivo[] arreglo;
    private int size;
    private int capacity;

    public DinamicArray(){
        arreglo=new archivo[2];
        size=0;
        capacity=2;
    }



    public void insertarArchivo(archivo arc){
        if(size>=capacity)
            aumentarArreglo();
        arreglo[size]=arc;
        size++;
    }



    public void aumentarArreglo(){
        archivo[] recurso=new archivo [capacity*2];
        for(int j=0;j<capacity;j++){
            recurso[j]=arreglo[j];
        }
        arreglo=recurso;
        capacity*=2;
    };


    public archivo getArchivo(int i) throws IndexOutOfBoundsException{
        if(i>=size|i<0)
            throw new IndexOutOfBoundsException();

        return arreglo[i];
    }


    public void setInt(int i, archivo value){
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

    public DinamicArray findByName(String name)throws Exception{
        DinamicArray r=new DinamicArray();
        for(int i=0;i<size;i++){
            if (arreglo[i].getNombre().equals(name)) {
                r.insertarArchivo(arreglo[i]);
            }
        }
        return r;
    }



    public int getSize(){
        return this.size;
    }





}

