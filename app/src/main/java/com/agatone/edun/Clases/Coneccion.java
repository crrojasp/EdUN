package com.agatone.edun.Clases;
import android.os.AsyncTask;
import android.widget.Toast;

import com.agatone.edun.MainActivity;

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

public class Coneccion extends AsyncTask<Void,Void,Boolean> {

    public static String host="estructuras.atwebpages.com";
    private FTPClient cliente=new FTPClient();
    private String username="3407620";
    private String pass="12345_Unal";
    private int port=21;




















    public Coneccion(){


    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        boolean j=false;
        try {
            cliente.connect(host,port);
            j=cliente.login(username,pass);
            cliente.logout();
            return j;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return j;


        }



    }



