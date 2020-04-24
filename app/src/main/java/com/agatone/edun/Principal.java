package com.agatone.edun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Principal extends AppCompatActivity {
    private Button Archivos_de_apoyo_Button, Estado_Trabajo_Button;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_principal );

        Archivos_de_apoyo_Button = findViewById ( R.id.Archivos_de_apoyo );
        Estado_Trabajo_Button = findViewById ( R.id.Estado_Entrega_btm );
        final Intent cambio5 = new Intent ( this, Archivos_de_apoyo.class );
        final Intent cambio6 = new Intent ( this, Subir_Archivo_De_Apoyo.class );

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
    }
}
