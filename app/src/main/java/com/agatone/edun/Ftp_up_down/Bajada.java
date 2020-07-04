package com.agatone.edun.Ftp_up_down;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.widget.Toast;

import com.agatone.edun.Clases.archivo;

import org.apache.commons.net.ftp.FTP;

import java.io.BufferedOutputStream;
import java.io.File;
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
public class Bajada  extends AsyncTask<String,Void,Boolean> implements Coneccion {
    private String path;
    public Context context;//solo por una prueba, despues eliminar

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bajada(String path,Context context){
        this.context=context;
        this.path=path;

    }

    public Bajada(Context context){
        //por defecto se tiene que la carpeta para guardar los archivos, sera la carpeta de descargas
        this(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString(),context);
    }


    //
    @Override
    protected Boolean doInBackground(String... strings) {
        //se carga el nombre del archivo
        String name=strings[0];



        boolean down=false;
        final File file=new File(this.path,name);


        try {
                FileOutputStream fil=new FileOutputStream(file);
                OutputStream out=new BufferedOutputStream(fil);


                Handler handler;
                handler=  new Handler(context.getMainLooper());
                handler.post( new Runnable(){
                    public void run(){

                        Toast.makeText(context, file.getPath(),Toast.LENGTH_LONG).show();
                    }
                });

                cliente.connect(host,port); //estos valores se encuentran en la interfaz Coneccion
                cliente.login( username,pass);

                cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!

                cliente.setFileType(FTP.BINARY_FILE_TYPE);

                //cliente.setFileTransferMode(FTP.BINARY_FILE_TYPE);

                //cliente.changeWorkingDirectory("estructuras.atwebpages.com/archivo/");


                down=cliente.retrieveFile(Coneccion.host+"/archivo/"+name,out);
                out.close();
                cliente.disconnect();



        } catch (IOException e) {
            down= false;
        }

        return down;
        //insert es para verificar que se halla bajado el archivo

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean)
            Toast.makeText(context,"Se descargo el archivo",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"no se descargo el archivo",Toast.LENGTH_SHORT).show();
    }
}

