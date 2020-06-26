package com.agatone.edun.activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.Clases.Usuario;
import com.agatone.edun.Clases.archivo;
import com.agatone.edun.Clases.fillArray;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.Ftp_up_down.Subida;
import com.agatone.edun.R;
import com.agatone.edun.adapters.algo;
import com.agatone.edun.adapters.archivosAdapter;
import com.agatone.edun.auxiliares.HashDocument;
import com.agatone.edun.auxiliares.UsuarioActual;
import com.agatone.edun.auxiliares.prueba;
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

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

public class documentos extends AppCompatActivity  {

    private ImageButton listaDocumentos,misDocumentos,permisoDocumentos,regresar,subir;
    private RecyclerView recycler;
    private DinamicArray archivosArray;

    private static boolean complete[]=new boolean[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);

        subir=(ImageButton)findViewById(R.id.upload);
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
                listarD();
            }
        });

        misDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listarM();
            }
        });

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirArchivo();
            }
        });



        Toast.makeText(getApplicationContext(),HashDocument.v+"",Toast.LENGTH_SHORT).show();
        if(HashDocument.v){

            Toast.makeText(getApplicationContext(),"hola mundo",Toast.LENGTH_SHORT).show();
            archivosAdapter archivos=new archivosAdapter(HashDocument.dinamico,getApplicationContext());
            recycler.setAdapter(archivos);

        }else{
            listarD();

        }
    }








    private void listarM(){
        DinamicArray  array=new DinamicArray();
        RequestQueue request;
        JsonObjectRequest jeison;
        final HashTable[] val=new HashTable[1];
        //final boolean complete[]=new boolean[2];
        complete[0]=false;
        complete[1]=false;



        request= Volley.newRequestQueue(getApplicationContext());
        String url=null;

        
        url="http://"+ Coneccion.host+"/Documentos/ListarMisArchivos.php?id="+UsuarioActual.usuario.getId();
        Toast.makeText(getApplicationContext(), url,Toast.LENGTH_SHORT).show();
        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                archivo arc=null;




                JSONArray json=response.optJSONArray("documento");
                DinamicArray filling=new DinamicArray();

                /**
                 * Implementacion con tabla hash
                 * *aun es una implementacion temprana y por el momento es imposible acceder a los datos desde fuera del Response
                 */
                HashTable hash=new HashTable(100);


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
                        hash.insert(arc);

                    }


                    Intent intent=new Intent(documentos.this,documentos.class);



                    HashDocument.v=true;
                    HashDocument.dinamico=filling;
                    documentos.this.startActivity(intent);
                    documentos.this.finish();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),/*"error al hacer la busqueda en la base de datos  "+*/ error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jeison);

    };



    private void listarD(){
        DinamicArray  array=new DinamicArray();
        RequestQueue request;
        JsonObjectRequest jeison;
        final HashTable[] val=new HashTable[1];
        //final boolean complete[]=new boolean[2];
        complete[0]=false;
        complete[1]=false;



        request= Volley.newRequestQueue(getApplicationContext());
        String url=null;
        url="http://"+ Coneccion.host+"/Documentos/listarArchivos.php";
        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                archivo arc=null;
                JSONArray json=response.optJSONArray("documento");
                DinamicArray filling=new DinamicArray();

                /**
                 * Implementacion con tabla hash
                 * *aun es una implementacion temprana y por el momento es imposible acceder a los datos desde fuera del Response
                 */
                HashTable hash=new HashTable(100);


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
                        hash.insert(arc);
                    }
                    archivosAdapter archivosAdapter=new archivosAdapter(filling,getApplicationContext());
                    recycler.setAdapter(archivosAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_SHORT).show();
                    complete[0]=true;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error al hacer la busqueda en la base de datos",Toast.LENGTH_SHORT).show();
            }
        });






        request.add(jeison);
        fillArray fill=new fillArray(getApplicationContext(),array);
        fill.fill();
        array=fill.getArreglo();


        String s=String.valueOf(array.getSize());
        //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();





    }

    private void subirArchivo(){
            mostrarOpciones();
    }

    private void mostrarOpciones(){
        final CharSequence[] opciones={"Subir Archivo","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Escoge una");
        builder.setItems(opciones, new DialogInterface.OnClickListener()  {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(opciones[which].equals("Cancelar")){
                    dialog.dismiss();
                }else{
                    Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    //Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("*/*");
                    startActivityForResult(intent.createChooser(intent,"Seleccione"),10);

                }
            }

        });
        builder.show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==10){
            Toast.makeText(getApplicationContext(),data.getData().getQuery(),Toast.LENGTH_SHORT).show();
            archivo archivo[]=new archivo[1];
            archivo[0]=new archivo(20,"federico","cvarlos","federiciano","pdf");
            archivo[0].setUri(data.getData());
            Subida subida=new Subida(getApplicationContext());
            subida.execute(archivo);
        }
    }
}
