package com.agatone.edun.auxiliares;

import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.estructuras.DinamicArray;
import com.agatone.edun.estructuras.Hash.HashTable;
import com.agatone.edun.adapters.*;

public class HashDocument {
    //v se usa para saber si ya hay algun dato en la tabla hash
    public static boolean v=false;
    public static HashTable names;
    public static archivosAdapter archivosAdapter;
    public static RecyclerView recyclerView;
    public static DinamicArray dinamico;
    public HashDocument(){

    }


}
