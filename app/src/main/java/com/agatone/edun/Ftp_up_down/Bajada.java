package com.agatone.edun.Ftp_up_down;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.agatone.edun.Clases.archivo;

import org.apache.commons.net.ftp.FTP;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


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
                JSONArray json = response.optJSONArray("documento");
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
    *
    *
    *
*/
public class Bajada  extends AsyncTask<archivo,Void,Boolean> implements Coneccion {
    String path;
    public Context context;//solo por una prueba, despues eliminar



    public Bajada(String path){
        this.path=path;

    }

    public Bajada(){
        this(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());//<<<<<<<<<<<<<<<<<<<<<<<<<<<< CAMBIAR
    }


    //
    @Override
    protected Boolean doInBackground(archivo... archivos) {
        //se carga el nombre del archivo




        boolean down=false;
        String url=null;
        FileOutputStream stream=null;

        try {

            for (archivo arc: archivos) {


                cliente.connect(host,port); //estos valores se encuentran en la interfaz Coneccion
                cliente.login( username,pass);

                cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!

                cliente.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);

                cliente.setFileTransferMode(FTP.BINARY_FILE_TYPE);

                cliente.changeWorkingDirectory("estructuras.atwebpages.com/archivo/");

                //BufferedOutputStream buffOut = new BufferedOutputStream(new FileOutputStream(this.path+arc.getNombre()+"."+arc.getTipo()));
                //Toast.makeText ( context,"como que si llego",Toast.LENGTH_LONG).show ();



                OutputStream out = new FileOutputStream(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()+"/hola.pdf");
                //stream=new FileOutputStream(this.path+arc.getNombre()+"."+arc.getTipo());


                cliente.retrieveFile(arc.getNombre()+"."+arc.getTipo(),out);
                out.close();
                cliente.disconnect();


            }
        } catch (IOException e) {
            Looper.prepare();
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
            Looper.loop();
        }
















        //insert es para verificar que se halla bajado el archivo
        return down;
    }

}