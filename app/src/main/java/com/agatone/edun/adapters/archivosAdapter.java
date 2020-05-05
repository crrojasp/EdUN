package com.agatone.edun.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.R;
import com.agatone.edun.estructuras.Lista;

public class archivosAdapter extends RecyclerView.Adapter<archivosAdapter.archivosHolder> {
    Lista listArchivos;

    public archivosAdapter (Lista archivos){
        listArchivos=archivos;

    }
    public class archivosHolder extends RecyclerView.ViewHolder{
        TextView id,nombre,dueno,autor;
        ImageButton image;

        public archivosHolder(View itemView){
            super(itemView);
            id=(TextView) itemView.findViewById(R.id.idtext);
            nombre=(TextView) itemView.findViewById(R.id.nombreText);
            dueno=(TextView) itemView.findViewById(R.id.duenoText);
            autor=(TextView) itemView.findViewById(R.id.autorText);
            image=(ImageButton) itemView.findViewById(R.id.imageButton);
        }

    }


    @NonNull
    @Override
    public archivosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.archivos_lista,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new archivosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull archivosHolder holder, int position) {
        holder.id.setText(listArchivos.buscarPosicion(position).getArc().getId());
        holder.dueno.setText(listArchivos.buscarPosicion(position).getArc().getDueno());
        holder.nombre.setText(listArchivos.buscarPosicion(position).getArc().getNombre());
        holder.autor.setText(listArchivos.buscarPosicion(position).getArc().getAutor());
    }

    @Override
    public int getItemCount() {
        return listArchivos.getCount();
    }
}
