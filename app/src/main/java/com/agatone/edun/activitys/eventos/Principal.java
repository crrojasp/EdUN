package com.agatone.edun.activitys.eventos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Clases.Usuario;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.activitys.Archivos_de_apoyo;
import com.agatone.edun.activitys.Subir_Archivo_De_Apoyo;
import com.agatone.edun.auxiliares.UsuarioActual;
import com.agatone.edun.estructuras.Hash.DinamicArray;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Principal extends AppCompatActivity{

    Button bt_Crear_Evento;


    EditText eventEditText;
    EditText mensaje;


    TextView fechaTx;
    TextView horaTx;

    Calendar fecha;
    DatePicker datePicker;
    
    int ano;
    int mes;
    int dia;
    int hora;
    int minutos;



    
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_principal );

        final Intent cambio5 = new Intent ( this, Archivos_de_apoyo.class );
        final Intent cambio6 = new Intent ( this, Subir_Archivo_De_Apoyo.class );

        inicializar();

        /**
         * CONTROLADORES PARA LOS BOTONES
         */

    }


    public void inicializar(){

        fechaTx =findViewById(R.id.Fecha);
        horaTx=findViewById(R.id.hora);

        mensaje=findViewById(R.id.mensajes);
        eventEditText=findViewById(R.id.Event_EditText);

    }




    public void fechaClick(View view) {
        Calendar calendario=Calendar.getInstance();

        dia = calendario.get ( Calendar.DAY_OF_WEEK_IN_MONTH );
        mes = calendario.get ( Calendar.MONTH );
        ano = calendario.get ( Calendar.YEAR );

        DatePickerDialog datePickerDialog = new DatePickerDialog ( Principal.this, new DatePickerDialog.OnDateSetListener () {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                fechaTx.setText(year+"/"+month+"/"+dayOfMonth);
            }
        }, ano, mes, dia );
        datePickerDialog.show ();


    }

    public void horaClick(View view) {

        TimePickerDialog timePickerDialog=new TimePickerDialog(
                Principal.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        horaTx.setText(hourOfDay+":"+minute);
                        hora=hourOfDay;
                        minutos=minute;
                    }
                },
                hora,
                minutos,
                true
        );
        timePickerDialog.show();
    }

    public void crearEventoListener(View view) {
        boolean v=verificar();
        if(v){
            crearEvento();
        }
        //Toast.makeText(getApplicationContext(),v+"",Toast.LENGTH_SHORT).show();
    }

    public boolean verificar(){
        boolean v=true;
        //se revisa que se halla escogido alguna fecha
        String fecha=fechaTx.getText().toString();
        if(fecha.length()==0){
            v=false;
            Toast.makeText(getApplicationContext(),"Porfavor seleccione una fecha",Toast.LENGTH_SHORT).show();
        }

        //se revisa que se halla escogido alguna hora
        String hora=horaTx.getText().toString();
        if(hora.length()==0){
            v=false;
            Toast.makeText(getApplicationContext(),"Porfavor seleccione una hora",Toast.LENGTH_SHORT).show();
        }

        //se revisa si tiene nombre el evento
        String nombre=eventEditText.getText().toString();
        if(nombre.length()==0){
            v=false;
            Toast.makeText(getApplicationContext(),"Porfavor dele un nombre al evento",Toast.LENGTH_SHORT).show();
        }

        String mensajes=mensaje.getText().toString();
        if(mensajes.length()==0){
            Toast.makeText(getApplicationContext(),"es preferible proporcionar informacion extra para el evento",Toast.LENGTH_SHORT).show();
        }else{
            if(mensaje.length()>240){
                mensaje.setText("");
                Toast.makeText(getApplicationContext(),"debe tener una longitud menor a 240 caracteres",Toast.LENGTH_SHORT).show();
                v=false;
            }
        }

        return v;
    }


    public void crearEvento(){

        String nombre=eventEditText.getText().toString();
        String mensajes=mensaje.getText().toString();

        DinamicArray array=new DinamicArray();
        RequestQueue request;
        JsonObjectRequest jeison;

        request= Volley.newRequestQueue(getApplicationContext());
        String url=null;
        if(mensaje.length()==0){
            url="http://"+ Coneccion.host+"/eventos/eventos.php?ano="+ano+"&mes="+mes+"&dia="+dia+"&hora="+hora+"&minuto="+minutos+"&nombre="+nombre+"&id_c="+ UsuarioActual.usuario.getId();
        }else{
            url="http://"+ Coneccion.host+"/eventos/eventos.php?ano="+ano+"&mes="+mes+"&dia="+dia+"&hora="+hora+"&minuto="+minutos+"&nombre="+nombre+"&mensaje="+mensajes+"&id_c="+ UsuarioActual.usuario.getId();
        }

        url=url.replace(" ","%20");
        //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();


        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray json;


                json=response.optJSONArray("val");

                try {
                    int val;

                    String valu=json.getString(0);

                    val=Integer.parseInt(valu);

                    Toast.makeText(getApplicationContext(),val+"",Toast.LENGTH_SHORT).show();
                    if(val==0)
                        Toast.makeText(getApplicationContext(),"No se creo el evento nuevo",Toast.LENGTH_SHORT).show();
                    else if(val==1)
                        Toast.makeText(getApplicationContext(),"creado nuevo evento",Toast.LENGTH_SHORT).show();




                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error al enlistar, intentelo de nuevo mas tarde",Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jeison);
    };


}