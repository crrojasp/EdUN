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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.activitys.Archivos_de_apoyo;
import com.agatone.edun.activitys.Subir_Archivo_De_Apoyo;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    Button bt_Crear_Evento, bt_Fecha, bt_Hora, bt_ver_eventos;

    EditText et_fecha, et_hora, Event;

    TextView guardado_fecha;

    String event;


    TextView fechaTx;
    TextView horaTx;

    Calendar fecha;
    DatePicker datePicker;
    
    int ano, mes, dia, hora, minutos;



    
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

        fechaTx =findViewById(R.id.     Fecha);
        horaTx=findViewById(R.id.hora);

    }
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult ( requestCode, resultCode, data );
        //archivo arc[] = new archivo[1];
        switch (requestCode) {
            case 10:
                RequestQueue request;
                JsonObjectRequest jeison;
                request = Volley.newRequestQueue ( getApplicationContext () );
                //boolean insert = false;
                String url;
                url = Coneccion.host + "/bajarNombreArchivoDeBase.php?id=" + 0;
                jeison = new JsonObjectRequest ( Request.Method.GET, url, null, this, this );
                request.add ( jeison );
                //subida.execute(arc);
                //Toast.makeText ( getApplicationContext (),path.toString(), Toast.LENGTH_SHORT ).show ();
                break;
            default:
                throw new IllegalStateException ( "Unexpected value: " + requestCode );
        }
    }
    @Override
    public void onErrorResponse ( VolleyError error ) {
    }
    @Override
    public void onResponse ( JSONObject response ) {
    }
    public void guardar_evento(String url ){
        StringRequest st = new StringRequest ( Request.Method.GET, url, new Response.Listener<String> () {
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
            protected Map<String, String> getParams () {
                HashMap<String, String> parametros = new HashMap<> ();
                parametros.put  ( "ano" , ano+"" );
                parametros.put  ( "mes" , mes+"" );
                parametros.put  ( "dia" , dia+"" );
                parametros.put  ( "hora", hora+"");
                parametros.put  ( "minutos", minutos+"" );
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue ( this );
        requestQueue.add ( st );
    }

    public void fechaClick(View view) {
        Calendar calendario=Calendar.getInstance();

        int dia = calendario.get ( Calendar.DAY_OF_WEEK_IN_MONTH );
        int mes = calendario.get ( Calendar.MONTH );
        int ano = calendario.get ( Calendar.YEAR );
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
                    }
                },
                hora,
                minutos,
                true
        );
        timePickerDialog.show();
    }
}