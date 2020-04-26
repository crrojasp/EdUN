package com.agatone.edun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Clases.Usuario;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Button btContinuar1;
    private Button btCambioRegistro;
    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

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

                if (rq!=null){

                    MainActivity.this.startActivity ( cambio1 );
                    MainActivity.this.finish ();

                }

            }
            } );
    }
    @Override
    public void onErrorResponse ( VolleyError error ) {
        Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_LONG).show ();
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
