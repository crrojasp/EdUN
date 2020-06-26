package com.agatone.edun.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Clases.Usuario;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.activitys.registro.registro1;
import com.agatone.edun.auxiliares.UsuarioActual;
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

public class login extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Button btContinuar;
    private Button btCambioRegistro;
    RequestQueue rq;
    JsonRequest jrq;

    EditText user;
    EditText password;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login);

        btContinuar     = findViewById ( R.id.btcontinuar);
        btCambioRegistro = findViewById ( R.id.btregistro);
        user=(EditText)findViewById ( R.id.CajaUsuario );
        password=(EditText)findViewById ( R.id.CajaContraseña );


        final Intent cambio2 = new Intent ( this, registro1.class );




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

            }
        } );

    }

    private void IniciarSesión(){

        String Usuario1=user.getText().toString() ;
        String Contraseña1=password.getText().toString();



        if(Usuario1.length()==0&&Contraseña1.length()==0){
            Toast.makeText(getApplicationContext(),"Falta el Nombre de usuario y la contrasena",Toast.LENGTH_SHORT).show();
        }else if(Usuario1.length()==0){
            Toast.makeText(getApplicationContext(),"Falta el Nombre de usuario",Toast.LENGTH_SHORT).show();
        }else if(Contraseña1.length()==0){
            Toast.makeText(getApplicationContext(),"Falta la contrasena",Toast.LENGTH_SHORT).show();
        }else {
            String url = "http://"+ Coneccion.host +"/inicioSesion.php?Usuario=" + Usuario1 + "&Contraseña=" + Contraseña1;
            jrq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            rq.add(jrq);
        }
    }
    @Override
    public void onErrorResponse ( VolleyError error ) {
        Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_SHORT).show ();
    }
    @Override
    public void onResponse ( JSONObject response ) {
        int id;
        String nombre;
        String apellido;
        char tipo;
        String usuario;
        try {
            JSONArray json=response.optJSONArray("usuario");

            JSONObject jsonObject=json.getJSONObject(0);
            id=jsonObject.optInt("id");
            nombre=jsonObject.optString("nombre");
            apellido=jsonObject.optString("apellido");
            tipo=jsonObject.optString("tipo").charAt(0);
            usuario=user.getText().toString();

            Usuario User = new Usuario (nombre, apellido, tipo, usuario);
            User.setId(id);

            if(User.getId()==-1){
                Toast.makeText ( getApplicationContext (),"No se encontro el usuario", Toast.LENGTH_SHORT ).show ();
            }else if(User.getId()==-2){
                Toast.makeText ( getApplicationContext (),"error al conectar con la base", Toast.LENGTH_SHORT ).show ();
            }else{
                //Toast.makeText ( getApplicationContext (),"Bienvenido", Toast.LENGTH_SHORT ).show ();

                UsuarioActual.usuario=User;
                Intent cambio1 = new Intent ( getApplicationContext(), opciones.class );
                startActivity ( cambio1 );
                login.this.finish ();

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}