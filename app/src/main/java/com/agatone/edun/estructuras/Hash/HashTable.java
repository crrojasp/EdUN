package com.agatone.edun.estructuras.Hash;

import android.widget.Toast;

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.estructuras.DinamicArray;


public class HashTable {
    private int tamHash;
    private DinamicArray[] tablaHash;

    public HashTable(int tamHash) {
        this.tamHash = tamHash;
        tablaHash = new DinamicArray[tamHash];
    }

    public int Key(String cadena) {
        int value = 0;
        for (int i = 0; i < cadena.length(); i++) {
            value = (value * 5) + cadena.charAt(i);
        }
        return value % tamHash;
    }

    public void insert(archivo arc){
        int key=Key(arc.getNombre());
        tablaHash[key].insertarArchivo(arc);
    }

    public DinamicArray find_name(String name){
        DinamicArray r=tablaHash[Key(name)];
        try{
            r=r.findByName(name);
        }catch(Exception e){
            //no necesito validar ningun error aqui, solo devolver el arreglo vacio
        }
        return r;
    };



}
