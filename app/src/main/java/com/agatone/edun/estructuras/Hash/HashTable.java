package com.agatone.edun.estructuras.Hash;

import com.agatone.edun.Clases.archivo;


public class HashTable {
    private int tamHash;
    private List[] tablaHash;

    public HashTable(int tamHash) {
        this.tamHash = tamHash;
        tablaHash = new List[tamHash];
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
        tablaHash[key].insert(arc);
    }

    public List find_name(String name){
        List lista=new List();
        int key=Key(name);
        Node nodo=tablaHash[key].getHead();
        for(int i=0;i<tablaHash[key].getCount();i++){
            if(name.equals(nodo.getArc().getNombre())){
                lista.insert(nodo.getArc());
            }
            nodo=nodo.getNext();
        }

        return lista;
    };
}
