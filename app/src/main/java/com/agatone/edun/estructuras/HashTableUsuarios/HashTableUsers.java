package com.agatone.edun.estructuras.HashTableUsuarios;

import com.agatone.edun.Clases.Usuario;


public class HashTableUsers {
    private int tamHash;
    private DinamicArrayUsers[] tablaHash;
    private int countItem=0;

    public HashTableUsers(int tamHash) {
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

    public void insert(Usuario arc){
        int key=Key(arc.getNombres());
        tablaHash[key].insertarArchivo(arc);
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
    };

    public int getCountItem() {
        return countItem;
    }
}

