package com.agatone.edun.Ftp_up_down;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.Toast;

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.auxiliares.UsuarioActual;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.net.ftp.FTP;
import org.json.JSONArray;
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
public class Subida  extends AsyncTask<archivo, Void, Boolean> implements Coneccion, Response.Listener<JSONArray>,Response.ErrorListener {

    private Context context;
    private archivo arc;

    public Subida(Context cont,archivo arc){
        this.context=cont;
        this.arc=arc;
    }

   public void setContext(Context context){
        this.context=context;
   }

    @Override
    protected Boolean doInBackground(archivo... archivos) {





        boolean insert=false;
        String url;

        //con el booleano insert se pretende definir si si se subio el archivo al host remoto
        for (archivo arc:archivos) {

            Uri file=arc.getUri();
            String id=String.valueOf(arc.getId());


            String name=arc.getNombre();

            try {

                FileInputStream fis=(FileInputStream) context.getContentResolver().openInputStream(file);

                //Toast.makeText ( context,"jerga", Toast.LENGTH_SHORT ).show ();
                cliente.connect(host,port);
                //estos valores se encuentran en la interfaz Coneccion

                cliente.login( username,pass);
                cliente.enterLocalPassiveMode(); // IMPORTANTE!!!!
                cliente.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
                cliente.setFileTransferMode(FTP.BINARY_FILE_TYPE);
                cliente.changeWorkingDirectory("estructuras.atwebpages.com/archivo/");

                insert = cliente.storeFile(name+"."+arc.getTipo(),fis);




                cliente.logout();
                cliente.disconnect();

                return insert;



            }catch(IOException e){
                Looper.prepare();
                Toast.makeText ( context,e.toString()+'\n'+"El programa a sufrido un error",Toast.LENGTH_LONG).show ();
                Looper.loop();
                //return insert;
            }



        }



        return insert;
    }


    @Override
    protected void onPostExecute(Boolean aBoolean) {
        if(aBoolean){
            String url;
            RequestQueue request;
            JsonArrayRequest jeison;
            request=Volley.newRequestQueue(context);

            url="http://"+Coneccion.host+"/Documentos/subirArchivoABase.php?nombre="+arc.getNombre()+"&autor="+
                    arc.getAutor()+"&dueno="+ UsuarioActual.usuario.getId() +"&tipo="+arc.getTipo();
            Toast.makeText(context,url,Toast.LENGTH_SHORT).show();
            url=url.replace( " ","%20");
            jeison=new JsonArrayRequest(Request.Method.GET,url,null,this,this);
            request.add(jeison);

        }else{
            Toast.makeText(context,"No se subio el archivo",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {


        Toast.makeText ( context,"El programa a sufrido un error"+error.toString(),Toast.LENGTH_LONG).show ();

    }

    @Override
    public void onResponse(JSONArray response) {
        Toast.makeText ( context,"Se ha cargado el nuevo registro correctamente",Toast.LENGTH_LONG).show ();
    }
}