package com.agatone.edun.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.R;
import com.agatone.edun.estructuras.DinamicArray;

public class archivosAdapter extends RecyclerView.Adapter<archivosAdapter.archivosHolder> implements View.OnClickListener {
    DinamicArray listArchivos;
    Context context;


    //TextView
    TextView id;


    public archivosAdapter (DinamicArray archivos, Context context){
        listArchivos=archivos;
        this.context=context;
    }

    @Override
    public void onClick(View v) {
        TextView text=v.findViewById(R.id.nombreText);
        if(text!=null)
            Toast.makeText(context,"siiiiiiiiiiiiiii"+text.getText().toString(),Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context,"noooo",Toast.LENGTH_SHORT).show();
    }

    public class archivosHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //text

        TextView id,nombre,dueno,autor;

        //Buttons
        ImageButton image;



        @Override
        public void onClick(View v) {

        }

        public archivosHolder(View itemView, final Context context){

            super(itemView);

            id=(TextView) itemView.findViewById(R.id.idtext);
            nombre=(TextView) itemView.findViewById(R.id.nombreText);
            dueno=(TextView) itemView.findViewById(R.id.duenoText);
            autor=(TextView) itemView.findViewById(R.id.autorText);
            image=(ImageButton) itemView.findViewById(R.id.imageButton);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"siiiii"+nombre.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


    @NonNull
    @Override
    public archivosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.archivos_lista,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new archivosHolder(vista,context);
    }

    @Override
    public void onBindViewHolder(@NonNull archivosHolder holder, int position) {
        try{
            holder.id.setText(String.valueOf(listArchivos.getArchivo(position).getId()));
            holder.dueno.setText(String.valueOf(listArchivos.getArchivo(position).getDueno()));
            holder.nombre.setText(listArchivos.getArchivo(position).getNombre());
            holder.autor.setText(listArchivos.getArchivo(position).getAutor());
            String tipo=listArchivos.getArchivo(position).getTipo();





            if(tipo.equals("pdf")){
                holder.image.setBackgroundResource(R.drawable.pdf);
            }else if(tipo.equals("csv")) {
                holder.image.setBackgroundResource(R.drawable.csv);
            }else if(tipo.equals(("doc"))){
                holder.image.setBackgroundResource(R.drawable.docs);
            }else{
                holder.image.setBackgroundResource(R.drawable.unknown);
            }

        }catch(IndexOutOfBoundsException e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public int getItemCount() {
        return listArchivos.getSize();
    }
}
