package com.agatone.edun.estructuras;


import android.content.Context;

import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.Clases.archivo;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author cristian
 */



public class Lista{

    private Nodo head;
    private Nodo back;
    private int count=0;
    public Context context;

    public Lista(Context context){
        this.context=context;
        head=null;
    };


    public boolean insert(archivo arc){
        boolean insertado=false;
        Nodo node=new Nodo(arc);
        Nodo previo,actual;


        actual=head;
        previo=null;
        node=head.getNext();
        head.setNext(node);
        if(head.getNext()== node)
            insertado = true;
        if(insertado)
            count++;
        return insertado;
    }

    public boolean delete(int id) {
        boolean del = false;
        Nodo prv, ptr = head;
        while (ptr != null & ptr.getArc().getId() < id) {
            prv = ptr;
            ptr = ptr.getNext();
        }
        if (ptr != null & ptr.getArc().getId() == id) {
            del = true;
            prv = ptr.getNext();
        }
        if (del)
            count--;
        return del;
    }

    public Nodo buscarId(int id){
        Nodo nodo=head;
        while(nodo!=null){
            if(nodo.getArc().getId()==id){
                return nodo;
            }
            nodo=nodo.getNext();
        }
        return null;
    }

    public Nodo buscarPosicion(int pos){
        if(count<=0)
            return null;
        if(count==1)
            return head.getNext();

        Nodo nodo=head;
        int i=0;

        while(i<pos){
            if(nodo==null)
                return null;
            nodo=nodo.getNext();
        }
        return nodo;
    };




    public int getCount(){
        return this.count;
    }







}