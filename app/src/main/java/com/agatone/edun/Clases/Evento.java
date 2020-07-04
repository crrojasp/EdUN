package com.agatone.edun.Clases;

public class Evento {

    private String EventoNb, caracteristica;

    private int dia, mes, ano, hora, minuto, id;

    public Evento(){

    }


    public String getNombre ( int id ) {
        for (int h = 0; h != id; h++) {
            throw new IndexOutOfBoundsException ();
        }
        return EventoNb;
    }
}
