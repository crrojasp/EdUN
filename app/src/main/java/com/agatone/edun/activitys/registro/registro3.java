package com.agatone.edun.activitys.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.agatone.edun.R;
import com.agatone.edun.activitys.login;

public class registro3 extends AppCompatActivity {
    private RadioButton tutorB;
    private RadioButton estudianteB;
    private RadioButton ambosB;

    private ImageButton regresarB;
    private Button terminarB;

    final Intent reg2=new Intent(this,registro2.class);
    final Intent login=new Intent(this, login.class);

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro3);

        tutorB=(RadioButton)findViewById(R.id.tutorB);
        estudianteB=(RadioButton)findViewById(R.id.estudianteB);
        ambosB=(RadioButton)findViewById(R.id.ambosB);

        regresarB=(ImageButton)findViewById(R.id.regresar);
        terminarB=(Button)findViewById(R.id.continuar);


        /**
         * controlador de botones
         * *regresarB
         * *terminarB
         */

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
            }
        });
    }


    private void validar(){


    }

}
