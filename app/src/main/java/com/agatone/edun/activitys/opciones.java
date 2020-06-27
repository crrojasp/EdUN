package com.agatone.edun.activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.Ftp_up_down.Bajada;
import com.agatone.edun.R;
import com.agatone.edun.activitys.documentos;
public class opciones extends AppCompatActivity {

    //zona de inicizlizacion
    private ImageButton returnBt,documentoBt,Eventos;

    //Constantes para revisison de permisos del sistema
    private final int REQUEST_WRITE_EXTERNAL=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView ( R.layout.activity_opciones);


        final Intent cambio = new Intent ( this, login.class );
        final Intent documentos = new Intent ( this, documentos.class );
        final Intent Event = new Intent ( this, Principal.class );

        //seccion de retorno a la pagina anterior
        returnBt=findViewById(R.id.regresar);
        documentoBt=(ImageButton) findViewById(R.id.documentos);
        Eventos = (ImageButton) findViewById ( R.id.eventos);
        Eventos.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                opciones.this.startActivity ( Event );
                opciones.this.finish ();
            }
        } );

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


        //Toast.makeText(getApplicationContext(), Environment.DIRECTORY_DOWNLOADS.toString(),Toast.LENGTH_SHORT).show();
        Bajada bajada=new Bajada(getApplicationContext());
        archivo arc[]=new archivo[1];
        bajada.execute(arc);
        //comprobar si se tienen los permisos de carga y descarga de documentos
        Permisos();



    }


    /**
     * ZONA PARA  EJECUTAR PETICIONES DE PERMISOS
     */
    public void Permisos(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String []{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_WRITE_EXTERNAL);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_WRITE_EXTERNAL){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"Permiso Concedido",Toast.LENGTH_SHORT).show();

            }
            else
                Toast.makeText(getApplicationContext(),"Permiso Denegado",Toast.LENGTH_SHORT).show();

        }
    }
}
