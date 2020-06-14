package com.agatone.edun.activitys.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Clases.Usuario;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.activitys.login;
import com.agatone.edun.auxiliares.UserRegistro;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class registro1 extends AppCompatActivity {

    private EditText usuario_registro;
    private EditText nombre;
    private EditText apellido;

    private ImageButton regresar;
    private Button continuar;

    private TextView usuarioText;
    private TextView nombreText;
    private TextView apellidoText;






    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registro1);

        //inicializacion de variables
        usuario_registro=(EditText)findViewById(R.id.Usuario_registro);
        nombre=(EditText)findViewById(R.id.Nombres_Registro);
        apellido=(EditText)findViewById(R.id.Apellidos_Registro);

        regresar=(ImageButton)findViewById(R.id.regresar);
        continuar=(Button)findViewById(R.id.terminar);

        usuarioText=(TextView)findViewById(R.id.usuarioText);
        nombreText=(TextView)findViewById(R.id.nombreText);
        apellidoText=(TextView)findViewById(R.id.apellidoText);


        final Intent login = new Intent ( registro1.this, login.class );

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro1.this.startActivity(login);
                registro1.this.finish();
            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primerPaso();
            }
        });
    }



    //zona de validacion de datos
    private void primerPaso(){
        String user=usuario_registro.getText().toString();
        String nom=nombre.getText().toString();
        String ape=apellido.getText().toString();

        if(validate(user,nom,ape)){
            existe_usuario(nom,ape,user);
        }
    }


    private boolean validate(String nom, String user, String ape){
        //el booleano v servira para calcular si faltan espacios por llenar, ya que si llega a faltar uno, automaticamente se volvera falso por lo que no entrara al resto de la funcion
        boolean v=true;

        usuarioText.setText("");
        nombreText.setText("");
        apellidoText.setText("");

        if(user.length()==0){
            usuarioText.setText("Falta nombre de usuario");
            v=false;
        }
        if(nom.length()==0){
            nombreText.setText("Falta el nombre");
            v=false;
        }
        if(ape.length()==0){
            apellidoText.setText("Falta el apellido");
            v=false;
        }
        return v;
    }



    private void existe_usuario(String nom, String user, String ape){
            RequestQueue request;
            JsonObjectRequest jeison;
            UserRegistro.user=new Usuario(nom,ape,user);
            request= Volley.newRequestQueue(getApplicationContext());
            String url;
            url="http://"+ Coneccion.host+"/inicioRegistro/usuarioExiste.php?Usuario="+user;
            jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    final Intent intent=new Intent(registro1.this,registro2.class);
                    String result;
                    int res;


                    Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();

                    try {
                        JSONArray json=response.optJSONArray("exist");


                        result=json.getString(0);
                        //result=json.getJSONObject(0).optString("exist");
                        //result=jsonObject.optString("exist");
                        res=Integer.parseInt(result);

                        if(res>=0){
                            Toast.makeText(getApplicationContext(),"el usuario ya existe",Toast.LENGTH_SHORT).show();

                        }else if(res==-3){


                            startActivity ( intent );
                            registro1.this.finish();
                        }

                        Toast.makeText(getApplicationContext(),"funciono",Toast.LENGTH_SHORT).show();
                    }catch(JSONException e ){
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Vuelva a intentarlo",Toast.LENGTH_SHORT).show();
                }
            });
            request.add(jeison);



    }
























    /*





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
    */

}
