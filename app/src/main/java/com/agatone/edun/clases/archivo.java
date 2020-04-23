package com.agatone.edun.Clases;

public class archivo {
    protected static int idOp=0;

    protected int id;
    protected String nombre;
    protected String autor;
    protected String dueno;

    public String getTipo () {
        return tipo;
    }

    protected String tipo;

    public String getReceptor () {
        return receptor;
    }

    protected String receptor;

    public archivo (String nombre,  String dueno){
        this(nombre,"autor desconocido",dueno,"tipo no especificado");
    }

    public archivo(String nombre, String autor, String dueno, String tipo) {
        this.nombre = nombre;
        this.autor = autor;
        this.dueno = dueno;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDueno() {
        return dueno;
    }

    public void setDueno(String dueno) {
        this.dueno = dueno;
    }
    public String setReceptor(){
        return receptor;
    }


}