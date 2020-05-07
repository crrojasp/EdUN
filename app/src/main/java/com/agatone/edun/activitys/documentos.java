package com.agatone.edun.activitys;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.R;

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
    }
}
