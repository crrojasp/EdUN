package com.agatone.edun.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agatone.edun.Clases.Evento;
import com.agatone.edun.Clases.archivo;
import com.agatone.edun.Ftp_up_down.Bajada;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.activitys.documentos;
import com.agatone.edun.activitys.opciones;
import com.agatone.edun.auxiliares.HashDocument;
import com.agatone.edun.auxiliares.UsuarioActual;
import com.agatone.edun.estructuras.DinamicArray;
import com.agatone.edun.estructuras.Hash.HashTable;
import com.agatone.edun.estructuras.HashTableEventos.DinamicArrayEventos;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class eventosAdapter extends RecyclerView.Adapter<eventosAdapter.archivosHolder>  {
    DinamicArrayEventos listEventos;
    Context context;
    Activity activity;





    public eventosAdapter(DinamicArrayEventos archivos, Context context, Activity activity){
        listEventos =archivos;
        this.context=context;
        this.activity=activity;
    }



    public class archivosHolder extends RecyclerView.ViewHolder{
        //text

        TextView nombreTxt;
        TextView creadorTxt;
        TextView fechaTxt;
        TextView id_e;
        TextView id_c;

        //Buttons
        Button opciones;





        public archivosHolder(View itemView, final Context context){

            super(itemView);

            nombreTxt= itemView.findViewById(R.id.nombreTxt);
            creadorTxt=itemView.findViewById(R.id.creadorTxt);
            fechaTxt=itemView.findViewById(R.id.fechaTxt);

            opciones=itemView.findViewById(R.id.button2);

            //algunos componentes graficos que se encuentran ocultos
            id_e=itemView.findViewById(R.id.id_evento);
            id_c=itemView.findViewById(R.id.id_creador);

            final int id=Integer.parseInt(id_e.getText().toString());

            opciones.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mostrarOpciones(id);
                }
            });

        }

    }

    private void mostrarOpciones(int id_e){
        final CharSequence[] opciones={"Informacion extra","apuntarse","Eliminar","Cancelar"};
        final AlertDialog.Builder builder=new AlertDialog.Builder(activity);


        final int ide=id_e;

        builder.setTitle("seleccion alguna accion");
        builder.setItems(opciones, new DialogInterface.OnClickListener()  {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(opciones[which].equals("Cancelar")){
                    dialog.dismiss();
                }else if(opciones[which].equals("Informacion extra")){


                }else if(opciones[which].equals("Eliminar")){

                    Toast.makeText(context,"Eliminar",Toast.LENGTH_SHORT).show();

                }else if(opciones[which].equals("apuntarse")){
                    Inscribirse(ide);

                }
            }

        });

        builder.show();
    }

    @NonNull
    @Override
    public archivosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.eventos_lista,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                ,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new archivosHolder(vista,context);
    }

    @Override
    public void onBindViewHolder(@NonNull archivosHolder holder, int position) {
        try{
            Evento event=listEventos.getEvento(position);

            holder.fechaTxt.setText((event.getAno()
                    + "/"+event.getMes()
                    +"/"+event.getDia()
                    +"   "+event.getHora()
                    +":"+event.getMinuto()));

            holder.creadorTxt.setText(event.getCreador());
            holder.nombreTxt.setText(event.getEventoNb());


        }catch(IndexOutOfBoundsException e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public int getItemCount() {
        return listEventos.getSize();
    }


    public void Inscribirse( int id_c){
        DinamicArray array=new DinamicArray();
        RequestQueue request;
        JsonObjectRequest jeison;

        request= Volley.newRequestQueue(context);
        String url=null;

        url="http://"+ Coneccion.host+"/eventos/alistarEvento.php?id="+UsuarioActual.usuario.getId()+"&id_c="+id_c;
        url=url.replace(" ","%20");


        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                JSONArray json;


                json=response.optJSONArray("val");

                try {
                    int val;

                    JSONObject jsonObject;

                    jsonObject=json.getJSONObject(0);
                    val=jsonObject.optInt("id");

                    if(val==0)
                        Toast.makeText(context,"no se pudo enlistar en el evento",Toast.LENGTH_SHORT).show();
                    else if(val==1)
                        Toast.makeText(context,"Enlistado exitosamente",Toast.LENGTH_SHORT).show();




                } catch (JSONException e) {
                    Toast.makeText(context,e.toString() ,Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"error al enlistar, intentelo de nuevo mas tarde",Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jeison);
    };
}
