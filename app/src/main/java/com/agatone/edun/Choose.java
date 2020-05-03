package com.agatone.edun;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Choose extends AppCompatActivity {

    //zona de inicizlizacion
    private ImageButton returnBt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_choose );


        final Intent cambio2 = new Intent ( this, MainActivity.class );

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
