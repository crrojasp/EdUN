package com.agatone.edun;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Clases.Bajada;
import com.agatone.edun.Clases.Coneccion;
import com.agatone.edun.Clases.archivo;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Button btContinuar1;
    private Button btCambioRegistro;



    //este boton es solo para probar si se realiza bien la conexion con el servidor externo
    private Button ftp;

    private Button subir;


    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );









        //colocare otro boton que sirva de ejemplo para la subida de archivos
        //aqui creo la parte del recibidor de clicks del boton de prueba ftp
        ftp=findViewById(R.id.ftp);
        subir=findViewById(R.id.subir);





        ftp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
            }
        });

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarOpciones();
            }
        });










        btContinuar1     = findViewById ( R.id.btContinuar);
        btCambioRegistro = findViewById ( R.id.btregistro);

        final Intent cambio1 = new Intent ( this, Principal.class );
        final Intent cambio2 = new Intent ( this, registro.class );

        btCambioRegistro.setOnClickListener ( new View.OnClickListener () {
            @Override
                public void onClick(View view){
                    MainActivity.this.startActivity ( cambio2 );
                    MainActivity.this.finish ();
                }
            }
        );
        btContinuar1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view){

                rq = Volley.newRequestQueue (getApplicationContext ());
                IniciarSesión();

                MainActivity.this.startActivity ( cambio1 );
                MainActivity.this.finish ();

            }
            } );
    }



    /*
    @Override
    public void onErrorResponse ( VolleyError error ) {
        Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_LONG).show ();
    }
    @Override
    public void onResponse ( JSONObject response ) {

        Usuario user = new Usuario ("Carlos","123456789");



        Toast.makeText ( getApplicationContext (),"Bienvenido", Toast.LENGTH_SHORT ).show ();
        JSONArray jsona=response.optJSONArray ( "datos" );

        user.setUsuario ("Usuario");

        user.setContraseña ("Contraseña");
    }*/
    private void IniciarSesión(){
        String Usuario1      =((EditText)findViewById ( R.id.CajaUsuario )).getText ().toString ();
        String Contraseña1      =((EditText)findViewById ( R.id.CajaContraseña )).getText ().toString ();
        String url="https://edun-proyectodb.000webhostapp.com/index.php?Usuario"+Usuario1+"&Contraseña="+Contraseña1;
        jrq = new JsonObjectRequest ( Request.Method.GET,url, null,this,this );
        rq.add(jrq);
    }




















    private void mostrarOpciones(){
        final CharSequence[] opciones={"Subir Archivo","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Escoge una");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
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
        archivo arc[]=new archivo[1];
        switch(requestCode){
            case 10:


                RequestQueue request;
                JsonObjectRequest jeison;
                int id;
                request=Volley.newRequestQueue(getApplicationContext());

                boolean insert=false;
                String url=null;
                FileOutputStream stream=null;

                url="http://"+ Coneccion.host+"/bajarNombreArchivoDeBase.php?id="+0;
                jeison=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
                request.add(jeison);













                //subida.execute(arc);
                //Toast.makeText ( getApplicationContext (),path.toString(), Toast.LENGTH_SHORT ).show ();
                break;

        }
    }

    @Override
    public void onResponse(JSONObject response) {
        archivo archivo[]=new archivo[1];//el tamano se puede cambiar a una variable si en algun momentop se quiere programar la descarga demas d eun archivo
        String nombre,autor, tipo;




        JSONArray jsona=response.optJSONArray ( "usuario" );
        JSONObject json=null;
        try {
            json=jsona.getJSONObject(0);


            nombre=json.optString("nombreArchivo");
            autor=json.optString("autorArchivo");
            tipo=json.optString("tipo");

            archivo[0]=new archivo(nombre,autor,tipo);

            Bajada bajada=new Bajada();

            bajada.context=getApplicationContext();
            bajada.execute(archivo);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}





