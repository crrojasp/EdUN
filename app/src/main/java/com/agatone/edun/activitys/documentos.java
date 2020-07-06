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

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.activitys.Dialogs.BuscarDialog;
import com.agatone.edun.activitys.eventos.OpcionesEventos;
import com.agatone.edun.adapters.archivosAdapter;
import com.agatone.edun.auxiliares.ArchivoSubirBajar;
import com.agatone.edun.auxiliares.HashDocument;
import com.agatone.edun.auxiliares.UsuarioActual;
import com.agatone.edun.estructuras.Hash.DinamicArray;
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

public class documentos extends AppCompatActivity implements BuscarDialog.BuscarDialogListener {

    private ImageButton listaDocumentos,misDocumentos,permisoDocumentos,regresar,subir;
    private RecyclerView recycler;
    private DinamicArray archivosArray;

    //private static boolean complete[]=new boolean[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);

        subir=(ImageButton)findViewById(R.id.upload);
        listaDocumentos=(ImageButton) findViewById(R.id.listaDocumentos);
        regresar=(ImageButton) findViewById(R.id.regresar);
        misDocumentos=(ImageButton) findViewById(R.id.misDocumentos);
        //permisoDocumentos=(ImageButton) findViewById(R.id.permisoDocumentos);
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


        //llenado de recycler view
        archivosAdapter archivosAdapter=new archivosAdapter(HashDocument.dinamico,getApplicationContext(),this);
        recycler.setAdapter(archivosAdapter);

    }


    /**
     * VARIAS FUNCIONES DE LISTADO
     *
     * ListarM funciona para listar todos los archivos a nombre del usuario actual
     * ListarD sirve para listar todos los documentos disponibles
     */

    private void listarM(){

        archivosAdapter archivosAdapter=new archivosAdapter(HashDocument.de_usuario_actual,getApplicationContext(),this);
        recycler.setAdapter(archivosAdapter);
    };



    private void listarD(){

        //solo es necesario cargar el arreglo que ya se ha consultado con anterioridad

        archivosAdapter archivosAdapter=new archivosAdapter(HashDocument.dinamico,getApplicationContext(),this);
        recycler.setAdapter(archivosAdapter);
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



            //consulta de extension del archivo en cuestion
            String r= getContentResolver().getType(data.getData());
            r=getExtension(r);


            //cargar los datos a valores estaticos para luego poder ser manipulados en posteriores activitys
            ArchivoSubirBajar.type=r;
            ArchivoSubirBajar.uri=data.getData();



            ArchivoSubirBajar.archivo[0]=new archivo("holasa",r,data.getData());







            //iniciar la nueva actividad

             Intent intent=new Intent(documentos.this,NuevoDocumento.class);
             documentos.this.startActivity(intent);


            //documentos.this.finish();


        }
    }

    //la funcion "getExtension" nos da la extension del uri que se nos da al seleccionar un archivo
    private String getExtension(String url) {
        return url.substring(url.lastIndexOf("/")).replace("/","");
    }

    /**
     * EJECUCION DEL BOTON DE BUSQUEDA
     *
     *
     */
    public void search(View view) {
        BuscarDialog buscar=new BuscarDialog();

        buscar.show(getSupportFragmentManager(),"Dialog");

    }

    @Override
    public void applyText(String a) {
        Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
        DinamicArray busqueda=HashDocument.names.find_name(a);


        archivosAdapter adapter=new archivosAdapter(busqueda,getApplicationContext(),this);
        recycler.setAdapter(adapter);
    }

    public void recargar(View view) {

            DinamicArray array=new DinamicArray();
            RequestQueue request;
            JsonObjectRequest jeison;
            HashTable hash;
            request= Volley.newRequestQueue(getApplicationContext());
            String url=null;
            url="http://"+ Coneccion.host+"/Documentos/listarArchivos.php?id="+ UsuarioActual.usuario.getId();
            url=url.replace(" ","%20");
            Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();

            jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    archivo arc;
                    HashTable hash;
                    JSONArray json;
                    DinamicArray filling;

                    int id_act=UsuarioActual.usuario.getId();

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
                            if(dueno==id_act){
                                HashDocument.de_usuario_actual.insertarArchivo(arc);
                            }
                        }

                        HashDocument.dinamico=filling;
                        HashDocument.names=hash;
                        HashDocument.fillDocument =true;

                        Intent documentos = new Intent ( documentos.this, documentos.class );

                        documentos.this.startActivity(documentos);
                        documentos.this.finish();



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


    }
}
