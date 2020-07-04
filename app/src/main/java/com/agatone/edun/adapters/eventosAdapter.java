package com.agatone.edun.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.Ftp_up_down.Bajada;
import com.agatone.edun.R;
import com.agatone.edun.auxiliares.HashDocument;
import com.agatone.edun.estructuras.DinamicArray;

public class eventosAdapter extends RecyclerView.Adapter<eventosAdapter.archivosHolder>  {
    DinamicArray listArchivos;
    Context context;
    Activity activity;

    //TextView
    TextView id;


    public eventosAdapter(DinamicArray archivos, Context context, Activity activity){
        listArchivos=archivos;
        this.context=context;
        this.activity=activity;
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
                    String nom=nombre.getText().toString();
                    String tipo=null;
                    int idd=Integer.parseInt(id.getText().toString());
                    DinamicArray dinamic=HashDocument.names.find_name(nom);

                    for(int i=0;i<dinamic.getSize();i++){
                        if(idd==dinamic.getArchivo(i).getId())
                            tipo=dinamic.getArchivo(i).getTipo();
                    }



                    mostrarOpciones(nom,tipo);
                    final CharSequence[] opciones={"Subir Archivo","Cancelar"};

                }
            });
        }

    }

    private void mostrarOpciones(String nombre,String tipo){
        final CharSequence[] opciones={"Eliminar","Descargar","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        final String nom=nombre+"."+tipo;


        builder.setTitle("Escoge una accion para el archivo '"+nombre+"'");
        builder.setItems(opciones, new DialogInterface.OnClickListener()  {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(opciones[which].equals("Cancelar")){
                    dialog.dismiss();
                }else if(opciones[which].equals("Descargar")){
                    Toast.makeText(context,"Descargarndo... espere un momento",Toast.LENGTH_SHORT).show();

                    String nuevo[]=new String[1];
                    nuevo[0]=nom;

                    Bajada bajada =new Bajada(context);
                    bajada.execute(nuevo);



                }else if(opciones[which].equals("Eliminar")){
                    Toast.makeText(context,"Eliminar",Toast.LENGTH_SHORT).show();
                }
            }

        });

        builder.show();
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
