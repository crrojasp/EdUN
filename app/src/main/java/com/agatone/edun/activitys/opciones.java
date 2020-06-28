package com.agatone.edun.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.Clases.fillArray;
import com.agatone.edun.Ftp_up_down.Bajada;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.activitys.documentos;
import com.agatone.edun.adapters.archivosAdapter;
import com.agatone.edun.auxiliares.HashDocument;
import com.agatone.edun.auxiliares.UsuarioActual;
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

public class opciones extends AppCompatActivity {

    //zona de inicizlizacion
    private ImageButton returnBt,documentoBt;

    //Constantes para revisison de permisos del sistema
    private final int REQUEST_WRITE_EXTERNAL=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_opciones);


        final Intent cambio = new Intent ( this, login.class );


        //seccion de retorno a la pagina anterior
        returnBt=findViewById(R.id.regresar);
        documentoBt=(ImageButton) findViewById(R.id.documentos);

        returnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(cambio);
            }
        });


        documentoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LlenarHashTable();


            }
        });



        //comprobar si se tienen los permisos de carga y descarga de documentos
        Permisos();



    }


    /**
     * ZONA DE LLENADO DE LA TABLA HASH
     * primero se hace la consulta de todos los archivos dentro de la base de datos, se llena el recycler view y se carga la tabla hash
     */
    private void LlenarHashTable(){
        if(!HashDocument.v){
            DinamicArray array=new DinamicArray();
            RequestQueue request;
            JsonObjectRequest jeison;
            HashTable hash;
            request= Volley.newRequestQueue(getApplicationContext());
            String url=null;
            url="http://"+ Coneccion.host+"/Documentos/listarArchivos.php?id="+UsuarioActual.usuario.getId();
            url=url.replace(" ","%20");
            Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();

            jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    archivo arc;
                    HashTable hash;
                    JSONArray json;
                    DinamicArray filling;

                    json=response.optJSONArray("documento");
                    filling=new DinamicArray();
                    hash=new HashTable(100);
                    try {
                        for(int i=0;i<json.length();i++){

                            int id,dueno;
                            String nombre,autor,tipo;
                            JSONObject jsonObject;

                            jsonObject=json.getJSONObject(i);
                            id=jsonObject.optInt("id");
                            nombre=jsonObject.optString("nombre");
                            autor=jsonObject.optString("autor");
                            dueno=jsonObject.optInt("dueno");
                            tipo=jsonObject.optString("tipo");

                            arc=new archivo(id,nombre,autor,dueno,tipo);
                            filling.insertarArchivo(arc);
                            hash.insert(arc);
                        }

                        HashDocument.dinamico=filling;
                        HashDocument.names=hash;
                        HashDocument.v=true;

                        Intent documentos = new Intent ( opciones.this, documentos.class );

                        opciones.this.startActivity(documentos);
                        opciones.this.finish();



                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_SHORT).show();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"error al hacer la busqueda en la base de datos",Toast.LENGTH_SHORT).show();
                }
            });
            request.add(jeison);
        }else{
            Intent documentos = new Intent ( opciones.this, documentos.class );

            opciones.this.startActivity(documentos);
            opciones.this.finish();

        }



    }




    /**
     * ZONA PARA  EJECUTAR PETICIONES DE PERMISOS
     */
    public void Permisos(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE_EXTERNAL);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_WRITE_EXTERNAL){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Permiso Concedido",Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(getApplicationContext(),"Permiso Denegado",Toast.LENGTH_SHORT).show();

        }
    }
}
