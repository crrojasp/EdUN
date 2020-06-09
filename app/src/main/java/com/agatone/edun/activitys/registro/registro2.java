package com.agatone.edun.activitys.registro;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.agatone.edun.R;

public class registro2 extends AppCompatActivity {

    private EditText password;
    private EditText confirmPassword;
    private Button continuar;
    private ImageButton regresar;
    private TextView passText;
    private TextView cPassText;
    private TextView allText;

    String ps;
    String cPs;


    /**
     * zona de intents
     */
    final Intent reg1 =new Intent(this,registro1.class);
    final Intent reg3=new Intent(this,registro3.class);


    protected void onCreate ( Bundle savedInstanceState ) {

        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registro2);





        /**
         *  inicializacion de elementos graficos y variables
         *  **/

        password=(EditText) findViewById(R.id.password);
        confirmPassword=(EditText) findViewById(R.id.confirmPassword);
        continuar=(Button)findViewById(R.id.continuar);
        regresar=(ImageButton)findViewById(R.id.regresar);
        passText=(TextView) findViewById(R.id.passText);
        cPassText=(TextView)findViewById(R.id.cPassText);
        allText=(TextView) findViewById(R.id.allText);



        /**
         * controlador de botones
         * * regresar
         * * continuar
         */

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registro2.this.startActivity(reg1);
                registro2.this.finish();
            }
        });

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
    }

    public void validar(){
        ps=password.getText().toString();
        cPs=confirmPassword.getText().toString();

        if(areEqual(ps,cPs)){
            cont(ps);
        }
    }


    private boolean areEqual(String ps,String cPs){
        cPassText.setText("");
        passText.setText("");
        allText.setText("");

        boolean v=true;
        if(ps.length()==0){
            passText.setText("Falta contrasena");
            v=false;
        }
        if(cPs.length()==0&&v){
            cPassText.setText("Falta repetir la contrasena");
            v=false;
        }
        if(!ps.equals(cPs)&&v){
            allText.setText("las contrasenas no coinciden");
            v=false;
        }
        return v;
    }


    private void cont(String ps){
        UserRegistro.user.setContraseña(ps);
        registro2.this.startActivity(reg3);
        registro2.this.finish();
    }
}
