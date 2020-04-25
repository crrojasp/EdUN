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
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;



/*
 *para ejecutar o instanciar esta clase como un hilo aparte, se debe usar la siguiente sentencia..
 *Subida subida= new Subida(getContext());
 * subida.execute(archivos arc[])
 * NOTAS: se le debe pasar el contexto de la aplicacion al objeto para que pueda hacer la ejecucion del
    php alojado en el servidor, no necesariamente se necesita el getContext, pero necesita el contexto
 *Al hacer el ".execute, es obligatorio pasarle como parametro un arreglo de archivos, que son los que posteriormente subira al servidor"
 *

 */
public class Subida  extends AsyncTask<archivo,Void,Boolean> implements Coneccion, Response.Listener<JSONObject>,Response.ErrorListener {

    private Context context;


    public Subida(Context cont){
        this.context=cont;
    }

    // el String o los Strings que recibira el hilo sera la direccion local del archivo que se quiere subir al host
    @Override
    protected Boolean doInBackground(archivo... archivos) {

        RequestQueue request;
        JsonObjectRequest jeison;
        boolean insert=false;
        String url;

        //con el booleano insert se pretende definir si si se subio el archivo al host remoto
        for (archivo arc:archivos) {


            request=Volley.newRequestQueue(context);
            String file=arc.getFilepath();//el hilo recibira los datos para la subida de archivos
            String id=String.valueOf(arc.getId());


            String name=arc.getNombre();

            try {

                FileInputStream fis=new FileInputStream(file);
                cliente.connect(host,port); //estos valores se encuentran en la interfaz Coneccion

                cliente.login( username,pass);
                cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!
                cliente.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
                cliente.setFileTransferMode(FTP.BINARY_FILE_TYPE);
                cliente.changeWorkingDirectory("/archivo/");
                insert = cliente.storeFile(name,fis);
                cliente.logout();
                cliente.disconnect();
                url=Coneccion.host+"/subirArchivoABase.php?id="+id+"&nombre="+name+"&autor="+
                        arc.getAutor()+"&dueno="+arc.getDueno()+"&tipo="+arc.getTipo();
                jeison=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                request.add(jeison);

            }catch(IOException e){
                return insert;
            }
        }



        return insert;
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
