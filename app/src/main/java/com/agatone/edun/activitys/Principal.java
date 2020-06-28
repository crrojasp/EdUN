package com.agatone.edun.activitys;

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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.Clases.archivo;
import com.agatone.edun.R;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Principal extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    Button bt_Crear_Evento, bt_Fecha, bt_Hora, ftp, subir;
    EditText et_fecha, et_hora, Event;
    TextView guardado_fecha;
    int ano, mes, dia, hora, minutos;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_principal );
        guardado_fecha = findViewById ( R.id.fecha_txt_guardado );
        final Intent cambio5 = new Intent ( this, Archivos_de_apoyo.class );
        final Intent cambio6 = new Intent ( this, Subir_Archivo_De_Apoyo.class );
        bt_Crear_Evento = findViewById ( R.id.bt_crear_evento );
        bt_Fecha = findViewById ( R.id.bt_fecha );
        bt_Hora = findViewById ( R.id.bot_hora );
        et_fecha = findViewById ( R.id.Caja_fecha );
        et_hora = findViewById ( R.id.Caja_hora );
        Event = findViewById ( R.id.Event_EditText );

        bt_Hora.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                //hora    =  ( );
            }
        } );

        ftp.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                Principal.this.startActivity ( cambio5 );
                Principal.this.finish ();
            }
        } );
        subir.setOnClickListener ( new View.OnClickListener () {
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
                        et_fecha.setText ( String.format ( "%d/%d/%d", dayOfMonth, month + 1, year ) );
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
                        et_hora.setText ( String.format ( "%d : %d", hourOfDay, minute ) );
                    }
                }, hora, minutos, false );
                timePickerDialog.show ();
            }
        } );
        final String anno=ano+"";
        bt_Crear_Evento.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                guardado_fecha.setText ( String.format ( "%s %d %d %d %d %d", Event, minutos, hora, dia, mes, ano ) );
                guardar_evento ( "" );
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
}