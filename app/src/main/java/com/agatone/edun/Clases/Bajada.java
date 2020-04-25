package com.agatone.edun.Clases;

import android.os.AsyncTask;

import org.apache.commons.net.ftp.*;

import java.io.FileInputStream;
import java.io.IOException;

public class Bajada  extends AsyncTask<String,Void,Boolean> implements Coneccion {



    // el String o los Strings que recibira el hilo sera la direccion local del archivo que se quiere subir al host
    @Override
    protected Boolean doInBackground(String... strings) {

        //con el booleano insert se pretende definir si si se subio el archivo al host remoto
        boolean insert=false;
        String file=strings[0];//el hilo recibe un arreglo de 2 Strings, el primero sera la direccion local del archivo
        String name=strings[1];// el segundo es el nombre con el que se quiere guardar
        try {
            FileInputStream fis=new FileInputStream(file);
            cliente.connect(host,port); //estos valores se encuentran en la interfaz Coneccion

            cliente.login( username,pass);
            cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!
            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            cliente.changeWorkingDirectory("/");
            insert = cliente.storeFile(name,fis);
            cliente.logout();
            cliente.disconnect();
        }catch(IOException e){

        }
        archivo archivo= new archivo();

        return insert;
    }
}
