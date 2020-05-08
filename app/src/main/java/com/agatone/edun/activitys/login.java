package com.agatone.edun.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Clases.Usuario;
import com.agatone.edun.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class login extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Button btContinuar;
    private Button btCambioRegistro;
    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login);

        btContinuar     = findViewById ( R.id.btcontinuar);
        btCambioRegistro = findViewById ( R.id.btregistro);

        final Intent cambio2 = new Intent ( this, registro.class );
        final Intent cambio1 = new Intent ( this, opciones.class );
        btCambioRegistro.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v){
                login.this.startActivity ( cambio2 );
                login.this.finish ();
            }
        });
        btContinuar.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                rq = Volley.newRequestQueue (getApplicationContext ());
                IniciarSesión();
                startActivity ( cambio1 );
                login.this.finish ();
            }
        } );

    }
    @Override
    public void onErrorResponse ( VolleyError error ) {
        Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_SHORT).show ();
    }
    @Override
    public void onResponse ( JSONObject response ) {
        Usuario user = new Usuario ();
        Toast.makeText ( getApplicationContext (),"Bienvenido", Toast.LENGTH_SHORT ).show ();
        JSONArray jsona=response.optJSONArray ( "datos" );
        user.setUsuario ("Usuario");
        user.setContraseña ("Contraseña");
    }
    private void IniciarSesión(){
        String Usuario1      =((EditText)findViewById ( R.id.CajaUsuario )).getText ().toString ();
        String Contraseña1      =((EditText)findViewById ( R.id.CajaContraseña )).getText ().toString ();
        String url="https://edun-proyectodb.000webhostapp.com/index.php?Usuario"+Usuario1+"&Contraseña="+Contraseña1;
        jrq = new JsonObjectRequest ( Request.Method.GET,url, null,this,this );
        rq.add(jrq);
    }
}