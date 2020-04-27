package com.agatone.edun;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Principal extends AppCompatActivity {
    Button Archivos_de_apoyo_Button, Estado_Trabajo_Button,bt_Crear_Evento,bt_Fecha,bt_Hora;
    EditText et_fecha,et_hora;
    int ano,mes,dia,hora,minutos;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_principal );
        Archivos_de_apoyo_Button = findViewById ( R.id.Archivos_de_apoyo );
        Estado_Trabajo_Button = findViewById ( R.id.Estado_Entrega_btm );
        final Intent cambio5 = new Intent ( this, Archivos_de_apoyo.class );
        final Intent cambio6 = new Intent ( this, Subir_Archivo_De_Apoyo.class );
        bt_Crear_Evento = findViewById ( R.id.bt_crear_evento );
        bt_Fecha        = findViewById ( R.id.bt_fecha );
        bt_Hora         = findViewById ( R.id.bot_hora );
        et_fecha        = findViewById ( R.id.Caja_fecha );
        et_hora         = findViewById ( R.id.Caja_hora );
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
                final Calendar calendario= Calendar.getInstance ();
                dia = calendario.get (Calendar.DAY_OF_WEEK_IN_MONTH );
                mes = calendario.get (Calendar.MONTH );
                ano = calendario.get (Calendar.YEAR );

                DatePickerDialog datePickerDialog = new DatePickerDialog ( Principal.this, new DatePickerDialog.OnDateSetListener () {
                    @Override
                    public void onDateSet ( DatePicker view, int year, int month, int dayOfMonth ) {
                        et_fecha.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },ano,mes,dia);
                datePickerDialog.show ();
            }
        } );
        bt_Hora.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {

                final Calendar calendario= Calendar.getInstance ();
                hora = calendario.get (Calendar.HOUR_OF_DAY );
                minutos = calendario.get (Calendar.MINUTE );

                TimePickerDialog timePickerDialog = new TimePickerDialog ( Principal.this, new TimePickerDialog.OnTimeSetListener () {
                    @Override
                    public void onTimeSet ( TimePicker view, int hourOfDay, int minute ) {
                        et_hora.setText ( hourOfDay+":"+minute );
                    }
                },hora,minutos,false);
                timePickerDialog.show ();
            }
        });
    }
}