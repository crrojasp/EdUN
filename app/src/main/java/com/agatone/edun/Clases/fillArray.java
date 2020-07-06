package com.agatone.edun.Clases;

import android.content.Context;
import android.widget.Toast;

import com.agatone.edun.estructuras.Hash.DinamicArray;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class fillArray implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private DinamicArray arreglo;
    public fillArray(Context context,DinamicArray arreglo) {
        this.context = context;
        this.arreglo=arreglo;
    }


    public void fill(){

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        archivo arc=null;
        JSONArray json=response.optJSONArray("usuario");
        DinamicArray filling=new DinamicArray();

        try {
            for(int i=0;i<json.length();i++){

                int id,dueno;
                String nombre,autor,tipo;
                JSONObject jsonObject=json.getJSONObject(i);
                id=jsonObject.optInt("id");
                nombre=jsonObject.optString("nombre");
                autor=jsonObject.optString("autor");
                dueno=Integer.parseInt(jsonObject.optString("dueno"));
                tipo=jsonObject.optString("tipo");

                arc=new archivo(id,nombre,autor,dueno,tipo);
                filling.insertarArchivo(arc);

            }

            this.arreglo=filling;
            Toast.makeText(context,String.valueOf(arreglo.getSize()),Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(context,e.toString() ,Toast.LENGTH_SHORT).show();
        }
    }




    public DinamicArray getArreglo() {
        int s=arreglo.getSize()+5;
        String l=String.valueOf(s);
        //Toast.makeText(context,l ,Toast.LENGTH_LONG).show();
        return arreglo;
    }

    public void setArreglo(DinamicArray arreglo) {

        this.arreglo = arreglo;
    }
}
