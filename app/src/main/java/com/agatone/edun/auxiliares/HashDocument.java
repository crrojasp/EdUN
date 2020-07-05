package com.agatone.edun.auxiliares;

import com.agatone.edun.estructuras.HashTableEventos.DinamicArrayEventos;
import com.agatone.edun.estructuras.HashTableEventos.HashTableEvents;
import com.agatone.edun.estructuras.HashTableUsuarios.DinamicArrayUsers;
import com.agatone.edun.estructuras.HashTableUsuarios.HashTableUsers;
import com.agatone.edun.estructuras.DinamicArray;
import com.agatone.edun.estructuras.Hash.HashTable;

public class HashDocument {
    //fillDocument se usa para saber si ya hay algun dato en la tabla hash


    public static HashTable names;
    public static DinamicArray dinamico;
    public static boolean fillDocument =false;
    public  static DinamicArray de_usuario_actual=new DinamicArray();

    public static boolean fillUsers=false;
    public static HashTableUsers tablaUsuarios;
    public static DinamicArrayUsers dinamicUsuarios;

    public static  boolean fillEvents=false;
    public static HashTableEvents tablaEventos;
    public static DinamicArrayEventos dinamicEventos;

    public HashDocument(){

    }


}
