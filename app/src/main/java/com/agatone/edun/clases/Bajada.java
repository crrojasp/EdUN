package com.agatone.edun.Clases;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.FileOutputStream;





    /*RequestQueue request;
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
           }*/

public class Bajada  extends AsyncTask<archivo,Void,Boolean> /*implements  Coneccion*/{
    String path;



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


        for (archivo arc: archivos) {
            /*try {
                cliente.connect(host,port); //estos valores se encuentran en la interfaz Coneccion
                cliente.login( username,pass);
                cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!
                cliente.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
                cliente.setFileTransferMode(FTP.BINARY_FILE_TYPE);
                cliente.changeWorkingDirectory("/archivo/");


                stream=new FileOutputStream(this.path);

                cliente.retrieveFile("/archivo/"+arc.getNombre()+"."+arc.getTipo(),stream);
                stream.close();
                cliente.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            }*/
        }

















        //insert es para verificar que se halla bajado el archivo
        return down;
    }

}