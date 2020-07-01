package com.agatone.edun.activitys.eventos.HashTableEst;

import com.agatone.edun.Clases.Usuario;
import com.agatone.edun.Clases.archivo;


public class HashTableEst {
    private int tamHash;
    private DinamicArrayEvents[] tablaHash;
    private int countItem=0;

    public HashTableEst(int tamHash) {
        this.tamHash = tamHash;
        tablaHash = new DinamicArrayEvents[tamHash];
        for(int i=0;i<tamHash;i++){
            tablaHash[i]=new DinamicArrayEvents();

        }

    }

    public int Key(String cadena) {
        int value = 0;
        for (int i = 0; i < cadena.length(); i++) {
            value = (value * 5) + cadena.charAt(i);
        }
        return value % tamHash;
    }

    public void insert(Usuario arc){
        int key=Key(arc.getNombres());
        tablaHash[key].insertarArchivo(arc);
        countItem++;
    }

    public DinamicArrayEvents find_name(String name){
        DinamicArrayEvents r=tablaHash[Key(name)];
        try{
            r=r.findByName(name);
        }catch(Exception e){
            //no necesito validar ningun error aqui, solo devolver el arreglo vacio
        }
        return r;
    };

    public int getCountItem() {
        return countItem;
    }
}

