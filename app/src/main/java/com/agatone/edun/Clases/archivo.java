package com.agatone.edun.Clases;

public class archivo {
    protected static int idOp=0;

    protected int id;
    protected String nombre;
    protected String autor;
    protected String dueno;
    protected String tipo;
    protected String filepath;
    protected static String remotePath= "";

    public archivo (String nombre,  String dueno){
        this(nombre,"autor desconocido",dueno,"tipo no especificado");
    }

    public archivo(String nombre, String autor, String dueno, String tipo) {
        id=idOp;
        idOp++;
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

    public static int getIdOp() {
        return idOp;
    }

    public static void setIdOp(int idOp) {
        archivo.idOp = idOp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public static String getRemotePath() {
        return remotePath;
    }

    public static void setRemotePath(String remotePath) {
        archivo.remotePath = remotePath;
    }
}
