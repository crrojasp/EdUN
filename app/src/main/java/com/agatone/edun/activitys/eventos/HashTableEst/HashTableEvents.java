package com.agatone.edun.activitys.eventos.HashTableEst;

import com.agatone.edun.Clases.Evento;


public class HashTableEvents {
    private int tamHash;
    private DinamicArrayUsers[] tablaHash;
    private int countItem=0;

    public HashTableEvents ( int tamHash ) {
        this.tamHash = tamHash;
        tablaHash = new DinamicArrayUsers[tamHash];
        for(int i=0;i<tamHash;i++){
            tablaHash[i]=new DinamicArrayUsers();

        }

    }

    public int Key(String cadena) {
        int value = 0;
        for (int i = 0; i < cadena.length(); i++) {
            value = (value * 5) + cadena.charAt(i);
        }
        return value % tamHash;
    }

    public void insert ( Evento arc ) {
        String key = Key ( arc.getNombre () );
        tablaHash[id].insertarArchivo ( arc );
        countItem++;
    }

    public DinamicArrayUsers find_name(String name){
        DinamicArrayUsers r=tablaHash[Key(name)];
        try{
            r=r.findByName(name);
        }catch(Exception e){
            //no necesito validar ningun error aqui, solo devolver el arreglo vacio
        }
        return r;
    }

    public int getCountItem() {
        return countItem;
    }
}

