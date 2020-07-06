package com.agatone.edun.activitys.eventos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.agatone.edun.R;
import com.agatone.edun.adapters.eventosMiosAdapter;
import com.agatone.edun.auxiliares.HashDocument;

public class eventosEnlazados extends AppCompatActivity {

    private RecyclerView recycler;
    private ImageButton regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos_enlazados);

        inicializar();
    }

    public void inicializar(){
        recycler=findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        recycler.setHasFixedSize(true);

        regresar=findViewById(R.id.regresar);

        eventosMiosAdapter eventosAdapter= new eventosMiosAdapter(HashDocument.dinamicMio,getApplicationContext(),this);
        recycler.setAdapter(eventosAdapter);
    };

    public void regresar(View view) {
        Intent intent=new Intent(eventosEnlazados.this,OpcionesEventos.class);
        eventosEnlazados.this.startActivity(intent);
        eventosEnlazados.this.finish();
    }
}
