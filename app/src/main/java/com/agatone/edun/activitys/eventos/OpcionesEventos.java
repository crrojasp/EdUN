package com.agatone.edun.activitys.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agatone.edun.Clases.Evento;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.auxiliares.HashDocument;
import com.agatone.edun.auxiliares.UsuarioActual;
import com.agatone.edun.estructuras.HashTableEventos.DinamicArrayEventos;
import com.agatone.edun.estructuras.HashTableEventos.HashTableEvents;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpcionesEventos extends AppCompatActivity {

    private Button crearEventoBt;
    private Button ListaEventosBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_eventos);

        inicializar();
    }


    //inicializacion de componentes graficos
    private void inicializar(){

        crearEventoBt=findViewById(R.id.CrearBt);
        ListaEventosBt =findViewById(R.id.ListaEventosBt);

    }


    /**
     * CONTROLADORES DE LOS BOTONES
     */
    public void crearListener(View view) {

        if(UsuarioActual.usuario.getTipo()=='\1'){

            Toast.makeText(getApplicationContext(),"No tiene los permisos para crear eventos nuevos",Toast.LENGTH_LONG).show();
        }else{
                Intent intent =new Intent(OpcionesEventos.this,Principal.class);
                OpcionesEventos.this.startActivity(intent);


        }
    }

    public void ListaEventosListener(View view) {
        if(UsuarioActual.usuario.getTipo()=='\2'){

            Toast.makeText(getApplicationContext(),"No tiene los permisos para observar eventos",Toast.LENGTH_LONG).show();
        }else{
            //final Calendar calendar =Calendar.getInstance ();
            if(!HashDocument.fillEvents){
                FillEvents();
            }else{
                Intent intent =new Intent(OpcionesEventos.this, ListaEventos.class);
                OpcionesEventos.this.startActivity(intent);

            }

        }
    }

    public void MisEventosListener(View view) {
        DinamicArrayEventos array=new DinamicArrayEventos();
        RequestQueue request;
        JsonObjectRequest jeison;
        HashTableEvents hash;
        request= Volley.newRequestQueue(getApplicationContext());

        //final int pos=po;

        String url=null;
        url="http://"+ Coneccion.host+"/eventos/ListarEventosEnlazados.php?id="+UsuarioActual.usuario.getId();
        url=url.replace(" ","%20");

        Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT).show();

        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Evento event;
                HashTableEvents hash;
                JSONArray json;
                DinamicArrayEventos filling;



                json=response.optJSONArray("eventos");
                filling=new DinamicArrayEventos();
                hash=new HashTableEvents(50);
                try {
                    for(int i=0;i<json.length();i++){

                        int ano;
                        int mes;
                        int dia;
                        String fecha;

                        int hora;
                        int minuto;
                        String time;

                        String nombreEvento;
                        String mensaje;
                        int idEvento;

                        String nombreCreador;
                        int idCreador;


                        JSONObject jsonObject;

                        jsonObject=json.getJSONObject(i);


                        nombreCreador=jsonObject.optString("creador");
                        idCreador=jsonObject.optInt("id_creador");

                        fecha=jsonObject.optString("fecha");
                        time=jsonObject.optString("hora");

                        nombreEvento=jsonObject.optString("evento");
                        idEvento=jsonObject.optInt("id_evento");
                        mensaje=jsonObject.optString("mensaje");

                        boolean v=true;

                        int begin=0;
                        int end=0;

                        String values[]=new String[3];
                        int r=0;

                        for(int j=0;j<=fecha.length();j++){
                            if(j==(fecha.length())||fecha.charAt(j)=='-'){
                                values[r]=fecha.substring(begin,j);
                                r++;
                                begin=j+1;
                            }

                        }

                        ano=Integer.parseInt(values[0]);
                        mes=Integer.parseInt(values[1]);
                        dia=Integer.parseInt(values[2]);


                        values=new String[2];
                        r=0;
                        begin=0;
                        for(int j=0;j<=time.length()&&r<2;j++){
                            if(time.charAt(j)==':'){
                                values[r]=time.substring(begin,j);
                                r++;
                                begin=j+1;

                            }
                            //ystem.out.println(time.charAt(j));
                        }

                        hora=Integer.parseInt(values[0]);
                        minuto=Integer.parseInt(values[1]);

                        event=new Evento(nombreEvento,mensaje,nombreCreador,dia,mes,ano,hora,minuto,idEvento,idCreador);

                        Toast.makeText(getApplicationContext(),(event==null)+"",Toast.LENGTH_SHORT).show();
                        filling.insertarEvento(event);
                        hash.insert(event);

                    }

                    Toast.makeText(getApplicationContext(),filling.getSize()+"",Toast.LENGTH_SHORT).show();

                    HashDocument.dinamicMio=filling;
                    HashDocument.tablaMisEventos=hash;
                    HashDocument.fillMEvents =true;

                    Intent intent =new Intent(OpcionesEventos.this, MisEventos.class);
                    OpcionesEventos.this.startActivity(intent);
                    OpcionesEventos.this.finish();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error al hacer la busqueda en la base de datos",Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jeison);

    }

    public void regresar(View view) {
        OpcionesEventos.this.finish();
    }


    public void FillEvents(){
        DinamicArrayEventos array=new DinamicArrayEventos();
        RequestQueue request;
        JsonObjectRequest jeison;
        HashTableEvents hash;
        request= Volley.newRequestQueue(getApplicationContext());

        //final int pos=po;

        String url=null;
        url="http://"+ Coneccion.host+"/eventos/ListarEventos.php";
        url=url.replace(" ","%20");

        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Evento event;
                HashTableEvents hash;
                JSONArray json;
                DinamicArrayEventos filling;



                json=response.optJSONArray("eventos");
                filling=new DinamicArrayEventos();
                hash=new HashTableEvents(50);
                try {
                    for(int i=0;i<json.length();i++){

                        int ano;
                        int mes;
                        int dia;
                        String fecha;

                        int hora;
                        int minuto;
                        String time;

                        String nombreEvento;
                        String mensaje;
                        int idEvento;

                        String nombreCreador;
                        int idCreador;


                        JSONObject jsonObject;

                        jsonObject=json.getJSONObject(i);


                        nombreCreador=jsonObject.optString("creador");
                        idCreador=jsonObject.optInt("id_creador");

                        fecha=jsonObject.optString("fecha");
                        time=jsonObject.optString("hora");

                        nombreEvento=jsonObject.optString("evento");
                        idEvento=jsonObject.optInt("id_evento");
                        mensaje=jsonObject.optString("mensaje");

                        boolean v=true;

                        int begin=0;
                        int end=0;

                        String values[]=new String[3];
                        int r=0;

                        for(int j=0;j<=fecha.length();j++){
                            if(j==(fecha.length())||fecha.charAt(j)=='-'){
                                values[r]=fecha.substring(begin,j);
                                r++;
                                begin=j+1;
                            }

                        }

                        ano=Integer.parseInt(values[0]);
                        mes=Integer.parseInt(values[1]);
                        dia=Integer.parseInt(values[2]);


                        values=new String[2];
                        r=0;
                        begin=0;
                        for(int j=0;j<=time.length()&&r<2;j++){
                            if(time.charAt(j)==':'){
                                values[r]=time.substring(begin,j);
                                r++;
                                begin=j+1;

                            }
                            //ystem.out.println(time.charAt(j));
                        }

                        hora=Integer.parseInt(values[0]);
                        minuto=Integer.parseInt(values[1]);

                        event=new Evento(nombreEvento,mensaje,nombreCreador,dia,mes,ano,hora,minuto,idEvento,idCreador);

                        Toast.makeText(getApplicationContext(),(event==null)+"",Toast.LENGTH_SHORT).show();
                        filling.insertarEvento(event);
                        hash.insert(event);

                    }

                    Toast.makeText(getApplicationContext(),filling.getSize()+"",Toast.LENGTH_SHORT).show();

                    HashDocument.dinamicEventos=filling;
                    HashDocument.tablaEventos=hash;
                    HashDocument.fillDocument =true;

                    Intent intent =new Intent(OpcionesEventos.this, ListaEventos.class);
                    OpcionesEventos.this.startActivity(intent);
                    OpcionesEventos.this.finish();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error al hacer la busqueda en la base de datos",Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jeison);
    }


}
