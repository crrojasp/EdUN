package com.agatone.edun.estructuras.Hash;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.estructuras.DinamicArray;


public class HashTable {
    private int tamHash;
    private DinamicArray[] tablaHash;
    private int countItem=0;

    public HashTable(int tamHash) {
        this.tamHash = tamHash;
        tablaHash = new DinamicArray[tamHash];
        for(int i=0;i<tamHash;i++){
            tablaHash[i]=new DinamicArray();

        }

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
        countItem++;
    }

    public DinamicArray find_name(String name){
        DinamicArray r=tablaHash[Key(name)];
        r=r.findByName(name);
        return r;
    };

    public int getCountItem() {
        return countItem;
    }
}

