package com.agatone.edun.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.R;
import com.agatone.edun.adapters.archivosAdapter;
import com.agatone.edun.estructuras.DinamicArray;

public class documentos extends AppCompatActivity {

    private ImageButton listaDocumentos,misDocumentos,permisoDocumentos,regresar;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);


        listaDocumentos=(ImageButton) findViewById(R.id.listaDocumentos);
        regresar=(ImageButton) findViewById(R.id.regresar);
        misDocumentos=(ImageButton) findViewById(R.id.misDocumentos);
        permisoDocumentos=(ImageButton) findViewById(R.id.permisoDocumentos);
        recycler=(RecyclerView) findViewById(R.id.recycler);

        recycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recycler.setHasFixedSize(true);

        final Intent cambio= new Intent ( this, opciones.class );

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentos.this.startActivity ( cambio );
                documentos.this.finish ();
            }
        });



        listaDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DinamicArray array=new DinamicArray();
                Toast.makeText(getApplicationContext(),array.getSize(),Toast.LENGTH_SHORT).show();/*array.size()*/
                array.fill(getApplicationContext(),array);
                archivosAdapter archivosAdapter=new archivosAdapter(array,getApplicationContext());
                recycler.setAdapter(archivosAdapter);

            }
        });
    }
}
