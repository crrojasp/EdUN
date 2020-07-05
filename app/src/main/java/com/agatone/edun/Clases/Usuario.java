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

    public Usuario(String Nombres, String Apellidos, char tipo, String Usuario){

        this(Nombres,Apellidos,Usuario);
        this.tipo=tipo;

    }

    public Usuario(String Nombres, String Apellidos,  String Usuario){
        this.nombres=Nombres;
        this.apellidos=Apellidos;
        this.Usuario=Usuario;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
