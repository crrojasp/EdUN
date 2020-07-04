package com.agatone.edun.activitys.eventos.HashTableEst;

import com.agatone.edun.Clases.Usuario;

public class DinamicArrayUsers {
    private Usuario[] arreglo;
    private int size;
    private int capacity;

    public DinamicArrayUsers(){
        arreglo=new Usuario[2];
        size=0;
        capacity=2;
    }



    public void insertarArchivo(Usuario arc){
        if(size>=capacity)
            aumentarArreglo();
        arreglo[size]=arc;
        size++;
    }



    public void aumentarArreglo(){
        Usuario[] recurso=new Usuario [capacity*2];
        for(int j=0;j<capacity;j++){
            recurso[j]=arreglo[j];
        }
        arreglo=recurso;
        capacity*=2;
    };


    public Usuario getArchivo(int i) throws IndexOutOfBoundsException{
        if(i>=size|i<0)
            throw new IndexOutOfBoundsException();

        return arreglo[i];
    }


    public void setInt(int i, Usuario value){
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

    public DinamicArrayUsers findByName(String name)throws Exception{
        DinamicArrayUsers r=new DinamicArrayUsers();
        for(int i=0;i<size;i++){
            if (arreglo[i].getNombres().equals(name)) {
                r.insertarArchivo(arreglo[i]);
            }
        }
        return r;
    }



    public int getSize(){
        return this.size;
    }





}

