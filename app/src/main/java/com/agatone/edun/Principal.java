package com.agatone.edun;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Clases.Coneccion;
import com.agatone.edun.Clases.archivo;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.Calendar;

public class Principal extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    Button Archivos_de_apoyo_Button, Estado_Trabajo_Button, bt_Crear_Evento, bt_Fecha, bt_Hora, ftp, subir;
    RequestQueue rq;
    JsonRequest jrq;
    EditText et_fecha, et_hora;
    int ano, mes, dia, hora, minutos;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_principal );

        ftp = findViewById ( R.id.ftp );
        subir = findViewById ( R.id.subir );
        final Intent cambio5 = new Intent ( this, Archivos_de_apoyo.class );
        final Intent cambio6 = new Intent ( this, Subir_Archivo_De_Apoyo.class );
        bt_Crear_Evento = findViewById ( R.id.bt_crear_evento );
        bt_Fecha = findViewById ( R.id.bt_fecha );
        bt_Hora = findViewById ( R.id.bot_hora );
        et_fecha = findViewById ( R.id.Caja_fecha );
        et_hora = findViewById ( R.id.Caja_hora );
        //bt_Fecha.setOnClickListener ( this );
        //bt_Hora.setOnClickListener ( this );
        //bt_Crear_Evento.setOnClickListener ( this );
        //Archivos_de_apoyo_Button.setOnClickListener ( this );
        //Estado_Trabajo_Button.setOnClickListener ( this );
        bt_Hora.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                //hora    =  ( );
            }
        } );
        bt_Crear_Evento.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {

            }
        } );
        Archivos_de_apoyo_Button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {

                Principal.this.startActivity ( cambio5 );
                Principal.this.finish ();

            }
        } );
        Estado_Trabajo_Button.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {

                Principal.this.startActivity ( cambio6 );
                Principal.this.finish ();

            }
        } );
        bt_Fecha.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                final Calendar calendario = Calendar.getInstance ();
                dia = calendario.get ( Calendar.DAY_OF_WEEK_IN_MONTH );
                mes = calendario.get ( Calendar.MONTH );
                ano = calendario.get ( Calendar.YEAR );

                DatePickerDialog datePickerDialog = new DatePickerDialog ( Principal.this, new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet ( DatePicker view, int year, int month, int dayOfMonth ) {
                        et_fecha.setText ( dayOfMonth + "/" + (month + 1) + "/" + year );
                    }
                }, ano, mes, dia );
                datePickerDialog.show ();
            }
        } );
        bt_Hora.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {

                final Calendar calendario = Calendar.getInstance ();
                hora = calendario.get ( Calendar.HOUR_OF_DAY );
                minutos = calendario.get ( Calendar.MINUTE );

                TimePickerDialog timePickerDialog = new TimePickerDialog ( Principal.this, new TimePickerDialog.OnTimeSetListener () {
                    @Override
                    public void onTimeSet ( TimePicker view, int hourOfDay, int minute ) {
                        et_hora.setText ( hourOfDay + ":" + minute );
                    }
                }, hora, minutos, false );
                timePickerDialog.show ();
            }
        } );

        ftp = findViewById ( R.id.ftp );
        subir = findViewById ( R.id.subir );


        ftp.setOnClickListener ( new View.OnClickListener () {

            @Override
            public void onClick ( View view ) {
            }
        } );

        subir.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                mostrarOpciones ();
            }
        } );
    }

    private void mostrarOpciones () {
        final CharSequence[] opciones = {"Subir Archivo", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "Escoge una" );
        builder.setItems ( opciones, new DialogInterface.OnClickListener () {
            @Override
            public void onClick ( DialogInterface dialog, int which ) {
                if (opciones[which].equals ( "Cancelar" )) {
                    dialog.dismiss ();
                } else {
                    Intent intent = new Intent ( Intent.ACTION_GET_CONTENT );
                    intent.addCategory ( Intent.CATEGORY_OPENABLE );
                    //Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType ( "*/*" );
                    startActivityForResult ( intent.createChooser ( intent, "Seleccione" ), 10 );
                }
            }
        } );
        builder.show ();

    }

    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult ( requestCode, resultCode, data );
        archivo arc[] = new archivo[1];
        switch (requestCode) {
            case 10:


                RequestQueue request;
                JsonObjectRequest jeison;
                int id;
                request = Volley.newRequestQueue ( getApplicationContext () );

                boolean insert = false;
                String url = null;
                FileOutputStream stream = null;

                url = Coneccion.host + "/bajarNombreArchivoDeBase.php?id=" + 0;
                jeison = new JsonObjectRequest ( Request.Method.GET, url, null, this, this );
                request.add ( jeison );


                //subida.execute(arc);
                //Toast.makeText ( getApplicationContext (),path.toString(), Toast.LENGTH_SHORT ).show ();
                break;
        }

    }

    @Override
    public void onErrorResponse ( VolleyError error ) {

    }

    @Override
    public void onResponse ( JSONObject response ) {

    }
}

