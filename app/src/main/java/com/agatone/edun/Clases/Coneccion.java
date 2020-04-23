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

    public static String url="sftp://3407620@f25-preview.awardspace.net:221/";
    FTPClient cliente;
    String username;
    String

    public Coneccion(){


    };
    public FTPClient getCliente() {

        return cliente;
    }



    public FTPClient conect(){
        cliente.connect();

    }
}
