package com.agatone.edun.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.R;

public class opciones extends AppCompatActivity {

    //zona de inicizlizacion
    private ImageButton returnBt,documentoBt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_opciones);


        final Intent cambio = new Intent ( this, login.class );
        final Intent documentos = new Intent ( this, documentos.class );

        //seccion de retorno a la pagina anterior
        returnBt=findViewById(R.id.regresar);
        documentoBt=(ImageButton) findViewById(R.id.documentos);

        returnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cambio);
            }
        });


        documentoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opciones.this.startActivity(documentos);
                opciones.this.finish();
            }
        });


    }
}
