package com.agatone.edun.estructuras;

import android.content.Context;

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DinamicArray {
    private archivo[] arreglo;
    private int size;
    private int capacity;

    public DinamicArray(){
        arreglo=new archivo[2];
        size=0;
        capacity=2;
    }

    private class fillArray implements Response.Listener<JSONObject>, Response.ErrorListener {
        Context context;

        public fillArray(Context context) {
            this.context = context;
        }


        public void fill(){
            RequestQueue request;
            JsonObjectRequest jeison;

            request= Volley.newRequestQueue(context);
            String url=null;
            url="http://"+ Coneccion.host+"/listarArchivos.php";
            jeison=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
            request.add(jeison);
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }

        @Override
        public void onResponse(JSONObject response) {
            archivo arc=null;
            JSONArray json=response.optJSONArray("usuario");


            try {
                for(int i=0;i<json.length();i++){
                    int id;
                    String nombre,autor,dueno,tipo;
                    JSONObject jsonObject=json.getJSONObject(i);
                    id=jsonObject.optInt("id");
                    nombre=jsonObject.optString("nombreArchivo");
                    autor=jsonObject.optString("autorArchivo");
                    dueno=jsonObject.optString("duenoArchivo");
                    tipo=jsonObject.optString("tipo");

                    arc=new archivo(id,nombre,autor,dueno,tipo);
                    insert(arc);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };//esta es una inner class con la que se pretende hace la insercion de todos los archivos de la base de datos

    public void fill(Context context){
        fillArray fill=new fillArray(context);
        fill.fill();
    }//ya que fillArray es una clase que posee los metodos para la insercion de todos los datos, en este metodo le hago su respectivo llamado


    public void insert(archivo arc){
        if(size>=capacity)
            aumentarArreglo();
        arreglo[size]=arc;
        size++;
    }

    public void aumentarArreglo(){
        archivo[] recurso=new archivo [capacity*2];
        for(int j=0;j<capacity;j++){
            recurso[j]=arreglo[j];
        }
        arreglo=recurso;
        capacity*=2;

    };

    public archivo getArchivo(int i){
        if(i>=size|i<0)
            throw new IndexOutOfBoundsException();

        return arreglo[i];
    }

    public void setInt(int i, archivo value){
        if(i>=size||i<0)
            throw new IndexOutOfBoundsException();


        if(i==size){//se evalua si la variable se quiere insertar al final del arreglo para ppoder hacer el respectivo aumento del contador "size"
            if(size>=capacity)//al igual que en la funcion "insert", se evalua si nos encontramos apuntando al final del arreglo, si es asi, debemos incrementar el tamano del arreglo
                aumentarArreglo();
            size++;
        }
        arreglo[i]=value;
    }

    public void remove(int i){
        if(i<0||i>=size){
            throw new IndexOutOfBoundsException();
        }
        if(size!=capacity){
            for(int j=i;j<size-2;j++){
                arreglo[j]=arreglo[j+1];
            }
        }
        size--;
    }

    public int size(){
        return size;
    }





}

