package com.agatone.edun.estructuras.HashTableEventos;

import com.agatone.edun.Clases.Evento;
import com.agatone.edun.estructuras.HashTableUsuarios.DinamicArrayUsers;


public class HashTableEvents {
    private int tamHash;
    private DinamicArrayEventos[] tablaHash;
    private int countItem=0;

    public HashTableEvents ( int tamHash ) {
        this.tamHash = tamHash;
        tablaHash = new DinamicArrayEventos[tamHash];
        for(int i=0;i<tamHash;i++){
            tablaHash[i]=new DinamicArrayEventos();

        }

    }

    public int Key(String cadena) {
        int value = 0;
        for (int i = 0; i < cadena.length(); i++) {
            value = (value * 5) + cadena.charAt(i);
        }
        return value % tamHash;
    }

    public void insert ( Evento evento ) {
        int key = Key ( evento.getEventoNb () );
        tablaHash[key].insertarEvento ( evento );
        countItem++;
    }

    public DinamicArrayEventos find_name(String name){
        DinamicArrayEventos r=tablaHash[Key(name)];
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

