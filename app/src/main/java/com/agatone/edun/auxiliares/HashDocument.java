package com.agatone.edun.auxiliares;

import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.activitys.eventos.HashTableEst.DinamicArrayEvents;
import com.agatone.edun.activitys.eventos.HashTableEst.HashTableEst;
import com.agatone.edun.estructuras.DinamicArray;
import com.agatone.edun.estructuras.Hash.HashTable;
import com.agatone.edun.adapters.*;

public class HashDocument {
    //fillDocument se usa para saber si ya hay algun dato en la tabla hash


    public static HashTable names;
    public static DinamicArray dinamico;
    public static boolean fillDocument =false;
    public  static DinamicArray de_usuario_actual=new DinamicArray();

    public static boolean fillUsers=false;
    public static HashTableEst tablaUsuarios;
    public static DinamicArrayEvents dinamicEventos;



    public HashDocument(){

    }


}
