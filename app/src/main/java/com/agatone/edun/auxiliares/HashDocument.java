package com.agatone.edun.auxiliares;

import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.activitys.eventos.HashTableEst.DinamicArrayEvents;
import com.agatone.edun.activitys.eventos.HashTableEst.HashTableEst;
import com.agatone.edun.estructuras.DinamicArray;
import com.agatone.edun.estructuras.Hash.HashTable;
import com.agatone.edun.adapters.*;

public class HashDocument {
    public static boolean v=false;
    public static HashTable names;
    public static archivosAdapter archivosAdapter;
    public static RecyclerView recyclerView;
    public static DinamicArray dinamico;

    public static DinamicArrayEvents user;
    public static HashTableEst users;


    public HashDocument(){

    }

    public HashTable llenarHash(){
        return null;

    }
}
