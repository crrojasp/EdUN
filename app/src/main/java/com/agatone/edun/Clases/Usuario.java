package com.agatone.edun.Clases;

public class Usuario {
    private int id;
    private String nombres;
    private String apellidos;
    private char tipo;//los tipos pueden ser: '1'(estudiante), '2'(profesor), '3' ambas
    private String Usuario;
    private String Contraseña;


    public Usuario (){
        this.Usuario=getUsuario ();
        this.Contraseña=getContraseña ();
    }

    


    public String getUsuario () {
        return Usuario;
    }

    public void setUsuario ( String usuario ) {
        Usuario = usuario;
    }

    public String getContraseña () {
        return Contraseña;
    }

    public void setContraseña ( String contraseña ) {
        Contraseña = contraseña;
    }
}