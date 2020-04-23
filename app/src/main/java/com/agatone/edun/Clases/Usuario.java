package com.agatone.edun.Clases;

public class Usuario {
    private String Usuario,Contraseña;


    public Usuario(String usuario, String contraseña){
        this.Usuario=usuario;
        this.Contraseña=contraseña;
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
