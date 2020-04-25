package com.agatone.edun.Clases;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import org.apache.commons.net.ftp.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



/*
    *para ejecutar o instanciar esta clase como un hilo aparte, se debe usar la siguiente sentencia..
    *
    * primero se debe buscar si el archivo existe en la base de datos mysql, para ello se hace una consulta
    * Json a un php que se encarga de la consulta, esto se hace con el siguiente codigo
    *
    *
    *
    *
    * RequestQueue request;
        JsonObjectRequest jeison;
        int id;
        request=Volley.newRequestQueue(getContext());

        boolean insert=false;
        String url=null;
        FileOutputStream stream=null;

        url=Coneccion.host+"/bajarNombreArchivoDeBase.php?id="+id;
        jeison=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(jeison);

         @Override
            public void onErrorResponse(VolleyError error) {
                //para validar errores en esta parte

            }

            @Override
            public void onResponse(JSONObject response) {
                JSONArray json = response.optJSONArray("usuario");
                JSONObject jsonObject = null;


                try {
                    jsonObject = json.getJSONObject(0);


                    //la variable s representa el nombre del documento dentro de la base de datos,

                    String s[]=new String [2];
                    s[1]=jsonObject.optString("nombreArchivo");
                    s[2]=jsonObject.optString("tipo");

                    Bajada bajada=new Bajada();
                    bajada.execute(s);

                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
            *
            *
            adicionalmente si se quiere cambiar la ruta de guardado del documento descargado, antes de
            ejecutarlo, cambiar la ruta con el metodo setPath de la clase Bajada
    *
    *
    *

*/
public class Bajada  extends AsyncTask<String,Void,Boolean> implements  Coneccion{
    String path;



    public Bajada(String path){
       this.path=path;

    }

    public Bajada(){
        this("esta parte hay que cambiarla");//<<<<<<<<<<<<<<<<<<<<<<<<<<<< CAMBIAR
    }


    //
    @Override
    protected Boolean doInBackground(String... strings) {
        //se carga el nombre del archivo
        String nombre=strings[0];
        String tipo=strings[1];
        boolean down=false;
        String url=null;
        FileOutputStream stream=null;








        try {
            cliente.connect(host,port); //estos valores se encuentran en la interfaz Coneccion
            cliente.login( username,pass);
            cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!
            cliente.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
            cliente.setFileTransferMode(FTP.BINARY_FILE_TYPE);
            cliente.changeWorkingDirectory("/archivo/");


            stream=new FileOutputStream(this.path);

            cliente.retrieveFile("/archivo/"+nombre+"."+tipo,stream);
            stream.close();
            cliente.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }











        //insert es para verificar que se halla bajado el archivo
        return down;
    }

}
