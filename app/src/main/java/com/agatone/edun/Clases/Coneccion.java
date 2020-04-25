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

public interface Coneccion {

    public static String host = "estructuras.atwebpages.com";
    FTPClient cliente = new FTPClient();
    String username = "3407620";
    String pass = "12345_Unal";
    int port = 21;



}


