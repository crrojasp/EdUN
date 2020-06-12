package com.agatone.edun.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.Clases.fillArray;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.adapters.archivosAdapter;
import com.agatone.edun.auxiliares.HashDocument;
import com.agatone.edun.estructuras.DinamicArray;
import com.agatone.edun.estructuras.Hash.HashTable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class documentos extends AppCompatActivity  {

    private ImageButton listaDocumentos,misDocumentos,permisoDocumentos,regresar;
    private RecyclerView recycler;
    private DinamicArray archivosArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);


        listaDocumentos=(ImageButton) findViewById(R.id.listaDocumentos);
        regresar=(ImageButton) findViewById(R.id.regresar);
        misDocumentos=(ImageButton) findViewById(R.id.misDocumentos);
        permisoDocumentos=(ImageButton) findViewById(R.id.permisoDocumentos);
        recycler=(RecyclerView) findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recycler.setHasFixedSize(true);

        final Intent cambio= new Intent ( this, opciones.class );

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentos.this.startActivity ( cambio );
                documentos.this.finish ();
            }
        });



        listaDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DinamicArray  array=new DinamicArray();
                RequestQueue request;
                JsonObjectRequest jeison;

                final HashTable[] val=new HashTable[1];

                final boolean complete[]=new boolean[2];
                complete[0]=false;
                complete[1]=false;


                request= Volley.newRequestQueue(getApplicationContext());
                String url=null;
                url="http://"+ Coneccion.host+"/listarArchivos.php";
                jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        archivo arc=null;
                        JSONArray json=response.optJSONArray("documento");
                        DinamicArray filling=new DinamicArray();

                        try {
                            for(int i=0;i<json.length();i++){

                                int id;
                                String nombre,autor,dueno,tipo;
                                JSONObject jsonObject=json.getJSONObject(i);
                                id=jsonObject.optInt("id");
                                nombre=jsonObject.optString("nombre");
                                autor=jsonObject.optString("autor");
                                dueno=jsonObject.optString("dueno");
                                tipo=jsonObject.optString("tipo");

                                arc=new archivo(id,nombre,autor,dueno,tipo);
                                filling.insertarArchivo(arc);

                            }
                            archivosAdapter archivosAdapter=new archivosAdapter(filling,getApplicationContext());
                            recycler.setAdapter(archivosAdapter);


                            val[0]= HashDocument.values;
                            complete[1]=true;

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_SHORT).show();
                            complete[0]=true;
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });


                while(!complete[0]||!complete[1]){
                    //nada solo que espere
                }

                request.add(jeison);
                fillArray fill=new fillArray(getApplicationContext(),array);
                fill.fill();
                array=fill.getArreglo();


                String s=String.valueOf(array.getSize());
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();




            }
        });
    }
}
