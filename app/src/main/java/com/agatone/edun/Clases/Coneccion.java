package com.agatone.edun.Clases;
import org.apache.commons.net.ftp.FTPClient;

public interface Coneccion {

    public static String host = "http://estructuras.atwebpages.com";
    FTPClient cliente = new FTPClient();
    String username = "3407620";
    String pass = "12345_Unal";
    int port = 21;



}


