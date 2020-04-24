package com.agatone.edun.Clases;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class Coneccion {

    public static String host="f25-preview.awardspace.net";
    private FTPClient cliente;
    private String username="3407620";
    private String pass="12345_Unal";
    private int port=21;

    public Coneccion(){


    };
    public FTPClient getCliente() {

        return cliente;
    }



    public FTPClient conect(){
        try {
            cliente.connect(host,port);
            cliente.login(username,pass);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.cliente;
    }
}