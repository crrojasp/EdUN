package com.agatone.edun.clases;

import android.os.AsyncTask;

import org.apache.commons.net.ftp.*;

import java.io.IOException;

public class Bajada extends AsyncTask<String,Void,Boolean> {
    @Override
    protected Boolean doInBackground(String... strings) {

        //con el booleano insert se pretende definir si si se subio el archivo al host remoto
        boolean insert=false;
        String file=strings[0];
        try {
            cliente.setBufferSize(512); // Opcional para definir Buffer size en bytes
            cliente.connect("servidor.ftp.com",21); // no el puerto es por defecto, podemos usar client.connect("servidor.ftp.com");
            cliente.login( props.getProperty("pgi20_ftp_user"), props.getProperty("pgi20_ftp_pass") );
            cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!
            cliente.setFileType(FTP.BINARY_FILE_TYPE);
            cliente.changeWorkingDirectory(remote_working_dir_path);
            boolean uploadFile = client.storeFile(remote_filename,fis);
            cliente.logout();
            cliente.disconnect();
        }catch(IOException e){

        }


        return insert;
    }
}
