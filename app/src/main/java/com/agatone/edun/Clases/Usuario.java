package com.agatone.edun.Clases;

public class Usuario {
    private String Usuario,Contraseña;


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