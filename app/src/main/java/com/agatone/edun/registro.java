package com.agatone.edun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Clases.Coneccion;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registro );
        Button botonregistro        = findViewById ( R.id.btregistrar );
        final Intent cambio4 = new Intent ( this, MainActivity.class );


        botonregistro.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                ejecutarservicio ();
                registro.this.startActivity ( cambio4 );
                registro.this.finish ();
            }
        } );
    }































    //aqui comienza lo que Cristian edito

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }

    public void ejecutarservicio( ){
        JsonObjectRequest json;
        RequestQueue request=Volley.newRequestQueue(getApplicationContext());
        String url;

        String Tipo = "indefinido";
        RadioButton profesor = findViewById ( R.id.Profesor_radiobt );
        RadioButton estudiante =findViewById ( R.id.Estudiante_radiobt );
        if(profesor.isChecked ()){
            Tipo="Profesor";
        }else if(estudiante.isChecked ()){
            Tipo="Estudiante";
        }




        String UsuarioDB      = ((EditText)findViewById ( R.id.Usuario_registro )).getText ().toString ();
        String NombresDB      = ((EditText)findViewById ( R.id.Nombres_Registro )).getText().toString();
        String ApellidosDB    = ((EditText)findViewById ( R.id.Apellidos_Registro )).getText().toString();
        String ClaveDB        = ((EditText)findViewById ( R.id.Contraseña_Registro )).getText().toString();



        url="http://"+ Coneccion.host+"/Registro?Usuario="+"&nombre="+UsuarioDB+"&Tipo="+
                Tipo+"&Nombres="+NombresDB+"&Apellidos="+ApellidosDB+"&Contrasena="+ClaveDB;
        url=url.replace( " ","%20");
        json=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        request.add(json);





                //aqui termina





















        StringRequest st = new StringRequest ( Request.Method.POST, url, new Response.Listener<String> () {
            @Override
            public void onResponse ( String response ) {
                Toast.makeText ( getApplicationContext (),"Operación Exitosa", Toast.LENGTH_SHORT ).show ();
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse ( VolleyError error ) {
                Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_LONG).show ();
            }
        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                HashMap<String,String>parametros = new HashMap<> ();
                String Tipo = "indefinido";
                RadioButton profesor = findViewById ( R.id.Profesor_radiobt );
                RadioButton estudiante =findViewById ( R.id.Estudiante_radiobt );
                if(profesor.isChecked ()){
                    Tipo="Profesor";
                }else if(estudiante.isChecked ()){
                    Tipo="Estudiante";
                }
                String UsuarioDB      = ((EditText)findViewById ( R.id.Usuario_registro )).getText ().toString ();
                String NombresDB      = ((EditText)findViewById ( R.id.Apellidos_Registro )).getText().toString();
                String ApellidosDB    = ((EditText)findViewById ( R.id.Apellidos_Registro )).getText().toString();
                String ClaveDB        = ((EditText)findViewById ( R.id.Contraseña_Registro )).getText().toString();
                parametros.put  ( "Tipo", Tipo );
                parametros.put  ( "Usuario", UsuarioDB );
                parametros.put  ( "Nombres",NombresDB);
                parametros.put  ( "Apellidos",ApellidosDB);
                parametros.put  ( "Contrasena",ClaveDB);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue ( this );
        requestQueue.add ( st );
    }

    
}
