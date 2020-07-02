package com.agatone.edun.auxiliares;

import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.activitys.eventos.HashTableEst.DinamicArrayEvents;
import com.agatone.edun.activitys.eventos.HashTableEst.HashTableEst;
import com.agatone.edun.estructuras.DinamicArray;
import com.agatone.edun.estructuras.Hash.HashTable;
import com.agatone.edun.adapters.*;

public class HashDocument {

    public static archivosAdapter archivosAdapter;
    public static RecyclerView recyclerView;

    //documentos
    public static boolean fullDocumentos =false;
    public static HashTable names;
    public static DinamicArray dinamico;



    //eventos
    public static DinamicArrayEvents user;
    public static HashTableEst users;
    public static boolean fullEventos=false;


    public HashDocument(){

    }

    public HashTable llenarHash(){
        return null;

    }
}
