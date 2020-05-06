package com.agatone.edun.estructuras;

public class DinamicArray {
    private int[] arreglo;
    private int size;
    private int capacity;


    public DinamicArray(){
        arreglo=new int[2];
        size=0;
        capacity=2;
    }

    public void insert(int i){
        if(size>=capacity)
            aumentarArreglo();
        arreglo[size]=i;
        size++;
    }

    public void aumentarArreglo(){
        int[] recurso=new int [capacity*2];
        for(int j=0;j<capacity;j++){
            recurso[j]=arreglo[j];
        }
        arreglo=recurso;
        capacity*=2;

    };

    public int getInt(int i){
        if(i>=size|i<0)
            throw new IndexOutOfBoundsException();

        return arreglo[i];
    }

    public void setInt(int i, int value){
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

    public int size(){
        return size;
    }
}
