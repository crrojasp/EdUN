package com.agatone.edun.activitys.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class registro3 extends AppCompatActivity {
    private RadioButton tutorB;
    private RadioButton estudianteB;
    private RadioButton ambosB;

    private ImageButton regresarB;
    private Button terminarB;

    private RadioGroup radio;



    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro3);

        tutorB=(RadioButton)findViewById(R.id.tutorB);
        estudianteB=(RadioButton)findViewById(R.id.estudianteB);
        ambosB=(RadioButton)findViewById(R.id.ambosB);

        regresarB=(ImageButton)findViewById(R.id.regresar);
        terminarB=(Button)findViewById(R.id.terminar);

        radio=(RadioGroup)findViewById(R.id.group);



        /**
         * controlador de botones
         * *regresarB
         * *terminarB
         * *tutorB
         * *estudianteB
         * *ambosB
         */

        final Intent reg2=new Intent(this,registro2.class);

        regresarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro3.this.startActivity(reg2);
                registro3.this.finish();
            }
        });

        terminarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
                Toast.makeText(getApplicationContext(),"No se selecciono ninguno, porfavor seleccr",Toast.LENGTH_SHORT);
            }
        });

        tutorB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio.clearCheck();
                tutorB.setChecked(true);
            }
        });

        estudianteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio.clearCheck();
                estudianteB.setChecked(true);
            }
        });

        ambosB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radio.clearCheck();
                ambosB.setChecked(true);
            }
        });

    }


    private void validar(){
        char opcion=' ';
        byte total=0;
        if(tutorB.isChecked()){
            opcion='1';
            total++;
        }
        if(estudianteB.isChecked()){
            opcion='2';
            total++;
        }
        if(ambosB.isChecked()){
            opcion='3';
            total++;
        }


        if(total==1){
            registrar(opcion);
            Toast.makeText(getApplicationContext(),"No se selecciono ninguno, porfavor seleccione uno antes de continuar",Toast.LENGTH_SHORT);
        }else if(total==0){
            Toast.makeText(getApplicationContext(),"No se selecciono ninguno, porfavor seleccione uno antes de continuar",Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(getApplicationContext(),"Se escogio mas de un argumento",Toast.LENGTH_SHORT);
        }
    }

    private void registrar(char opt){
        UserRegistro.user.setTipo(opt);
        RequestQueue request;
        JsonObjectRequest jeison;

        request= Volley.newRequestQueue(getApplicationContext());
        String url;
        url="http://"+ Coneccion.host+"/inicioRegistro/registro.php?usuario="+UserRegistro.user.getUsuario()+"&&nombres="+UserRegistro.user.getNombres()+
                "&&apellidos="+UserRegistro.user.getApellidos()+"&&tipo="+opt+"&&password="+UserRegistro.user.getContraseña();
        url=url.replace(" ","%20");
        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String result;
                int res;
                try {
                    JSONArray json=response.optJSONArray("val");


                    result=json.getString(0);
                    res=Integer.parseInt(result);

                    if(res==1){
                        Toast.makeText(getApplicationContext(),"se creo el usuario nuevo con exito",Toast.LENGTH_SHORT).show();
                    }else if(res==0||res==-1){
                        Toast.makeText(getApplicationContext(),"Error al insertar el usuario nuevo",Toast.LENGTH_SHORT).show();
                    }else if(res==-2){
                        Toast.makeText(getApplicationContext(),"Error al pasar los argumentos, intentelo de nuevo porfavor",Toast.LENGTH_SHORT).show();
                    }

                    final Intent login=new Intent(registro3.this, login.class);
                    registro3.this.startActivity(login);
                    registro3.this.finish();

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

}
