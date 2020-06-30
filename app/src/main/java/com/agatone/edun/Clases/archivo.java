package com.agatone.edun.Clases;

import android.net.Uri;

import com.agatone.edun.auxiliares.UsuarioActual;

public class archivo {
    protected static int idOp=0;

    protected int id;
    protected int dueno;

    protected String nombre;
    protected String autor="";


    protected String tipo;
    protected String filepath;
    protected static String remotePath= "";
    protected static Uri uri;

    public archivo (String nombre, String autor, String tipo){
        this(nombre,autor, UsuarioActual.usuario.getId(),tipo);
    }

    public archivo(String nombre,String autor,String tipo, Uri uri){
        this.nombre=nombre;
        this.autor=autor;
        this.tipo=tipo;
        this.uri=uri;
        this.id=UsuarioActual.usuario.getId();
    }

    public archivo(String nombre,String tipo, Uri uri){
        this(nombre,"Unknown",tipo,uri);
    }

    public archivo (String nombre,  int dueno){
        this(nombre,"autorDesconocido",dueno,"pdf");

    }
    public archivo(int id, String nombre, String autor, int dueno, String tipo) {
        this.id=id;
        this.nombre = nombre;
        this.autor = autor;
        this.dueno = dueno;
        this.tipo = tipo;
        if(idOp<=id){
            idOp=id+1;

        }else{
            idOp++;
        }

    }
    public archivo(String nombre, String autor, int dueno, String tipo) {
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

    public int getDueno() {
        return this.dueno;
    }

    public void setDueno(int dueno) {
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

    public static Uri getUri() {
        return uri;
    }

    public static void setUri(Uri uri) {
        archivo.uri = uri;
    }
}