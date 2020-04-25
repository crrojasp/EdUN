package com.agatone.edun;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agatone.edun.Clases.*;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.net.ftp.FTPClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Button btContinuar1;
    private Button btCambioRegistro;



    //este boton es solo para probar si se realiza bien la conexion con el servidor externo
    private Button ftp;


    RequestQueue rq;
    JsonRequest jrq;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );



        //aqui creo la parte del recibidor de clicks del boton de prueba ftp
        ftp=findViewById(R.id.ftp);
        ftp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Con coneccion= new Con();
                coneccion.execute();


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
    @Override
    public void onErrorResponse ( VolleyError error ) {
        Toast.makeText ( getApplicationContext (),error.toString (),Toast.LENGTH_LONG).show ();
    }
    @Override
    public void onResponse ( JSONObject response ) {
<<<<<<< Updated upstream
        Usuario user = new Usuario ("Carlos","123456789");
=======
        Usuario user = new Usuario (null,null);
>>>>>>> Stashed changes
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





    private class Con extends AsyncTask<Void,Void,Void>{
        public  String host="estructuras.atwebpages.com";
        private FTPClient cliente=new FTPClient();
        private String username="3407620";
        private String pass="12345_Unal";
        private int port=21;






















        @Override
        protected Void doInBackground(Void... voids) {
            boolean j=false;
            try {
                cliente.connect(host,port);
                j=cliente.login(username,pass);

                cliente.logout();
                if(j)
                    Log.d("Hols"," Hola mundoooooo\n\n\n\n\n\n\n");
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;


        }


    }
}
