package com.agatone.edun.activitys.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agatone.edun.R;
import com.agatone.edun.auxiliares.UsuarioActual;

public class OpcionesEventos extends AppCompatActivity {

    private Button crearEventoBt;
    private Button misEventosBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_eventos);

        inicializar();
    }


    //inicializacion de componentes graficos
    private void inicializar(){

        crearEventoBt=findViewById(R.id.CrearBt);
        misEventosBt=findViewById(R.id.misEventosBt);

    }


    /**
     * CONTROLADORES DE LOS BOTONES
     */
    public void crearListener(View view) {

        if(UsuarioActual.usuario.getTipo()=='1'){

            Toast.makeText(getApplicationContext(),"No tiene los permisos para crear eventos nuevos",Toast.LENGTH_LONG).show();
        }else{

            Intent intent =new Intent(OpcionesEventos.this,Principal.class);
            OpcionesEventos.this.startActivity(intent);

        }
    }

    public void misEventosListener(View view) {
        if(UsuarioActual.usuario.getTipo()=='2'){

            Toast.makeText(getApplicationContext(),"No tiene los permisos para observar eventos",Toast.LENGTH_LONG).show();
        }else{
            /*
            Intent intent =new Intent(OpcionesEventos.this,Principal.class);
            OpcionesEventos.this.startActivity(intent);
            */
        }
    }
}
