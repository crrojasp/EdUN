package com.agatone.edun.Clases;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.PrintCommandListener;

import org.apache.commons.net.ftp.*;





import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.util.TrustManagerUtils;




import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

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
