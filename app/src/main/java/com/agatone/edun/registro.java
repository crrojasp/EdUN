package com.agatone.edun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registro );
        Button botonregistro        = findViewById ( R.id.btregistrar );
        final Intent cambio4 = new Intent ( this, MainActivity.class );


        botonregistro.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                ejecutarservicio ("https://edun-proyectodb.000webhostapp.com/registro.php");
                registro.this.startActivity ( cambio4 );
                registro.this.finish ();
            }
        } );
    }
    public void ejecutarservicio(String url ){
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

                String UsuarioDB      =((EditText)findViewById ( R.id.Usuario_registro )).getText ().toString ();
                String NombresDB      =((EditText)findViewById ( R.id.Apellidos_Registro )).getText().toString();
                String ApellidosDB    =((EditText)findViewById ( R.id.Apellidos_Registro )).getText().toString();
                String ClaveDB        =((EditText)findViewById ( R.id.Contraseña_Registro )).getText().toString();

                parametros.put ( "Usuario", UsuarioDB );
                parametros.put ( "Nombres",NombresDB);
                parametros.put ( "Apellidos",ApellidosDB);
                parametros.put ( "Contraseña",ClaveDB);
                return parametros;

            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue ( this );
        requestQueue.add ( st );
    }
}
