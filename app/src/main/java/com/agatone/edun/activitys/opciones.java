package com.agatone.edun.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.R;

public class opciones extends AppCompatActivity {

    //zona de inicizlizacion
    private ImageButton returnBt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_opciones);


        final Intent cambio2 = new Intent ( this, login.class );

        //seccion de retorno a la pagina anterior
        returnBt=findViewById(R.id.regresar);
        returnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cambio2);
            }
        });



    }
}
