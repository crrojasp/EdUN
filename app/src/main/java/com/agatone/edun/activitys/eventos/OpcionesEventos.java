package com.agatone.edun.activitys.eventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.agatone.edun.Clases.Usuario;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.R;
import com.agatone.edun.activitys.documentos;
import com.agatone.edun.activitys.eventos.HashTableEst.DinamicArrayEvents;
import com.agatone.edun.activitys.eventos.HashTableEst.HashTableEst;
import com.agatone.edun.auxiliares.HashDocument;
import com.agatone.edun.auxiliares.UsuarioActual;
import com.agatone.edun.estructuras.DinamicArray;
import com.agatone.edun.estructuras.Hash.HashTable;
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
            //en el metodo siguiente se lanza la nueva actividad luego de hacer la consulta
            llenarHash(0);

        }
    }

    public void misEventosListener(View view) {
        if(UsuarioActual.usuario.getTipo()=='2'){

            Toast.makeText(getApplicationContext(),"No tiene los permisos para observar eventos",Toast.LENGTH_LONG).show();
        }else{
            /*
            solo sacarla del comentario cuando se halla creado el activity respectivo
            llenarHash(1)
            */
        }
    }


    /**
     * LLENADO DE TABLA HASH DE USUARIOS
     *
     * 0 si es para el intent crearEventos
     * 1 si es para el otro
     */


    public void llenarHash(int i){
        //variables globales al metodo
        RequestQueue request;
        JsonObjectRequest jeison;
        request= Volley.newRequestQueue(getApplicationContext());
        String url;

        final int r=i;

        //inicializacion de variables
        url="http://"+ Coneccion.host+"/inicioRegistro/usuarios.php";
        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //nuevas variables usadas para la consulta
                Usuario user;
                DinamicArrayEvents filling=new DinamicArrayEvents();
                HashTableEst hash=new HashTableEst(100);

                int id;
                String nombre;
                String apellido;
                String usuario;
                char tipo;



                Intent intent=null;

                //definir cual activity debe abrirse
                if(r==0){
                    intent=new Intent(OpcionesEventos.this,Principal.class);
                }else if(r==1){
                    // el otro intent
                    // intent=new Intent(OpcionesEventos.this,Principal.class)
                }


                //se recibe el resultado de la consulta
                JSONArray json=response.optJSONArray("documento");

                try {
                    for(int i=0;i<json.length();i++){


                        JSONObject jsonObject=json.getJSONObject(i);

                        //se llenan las variables con el resultado de la consulta
                        id=jsonObject.optInt("id");
                        nombre=jsonObject.optString("nombres");
                        apellido=jsonObject.optString("apellidos");
                        usuario=jsonObject.optString("usuario");
                        tipo=jsonObject.optString("tipo").charAt(0);

                        //se crea el nuevo usuario para ser guardado
                        user=new Usuario(nombre,apellido,tipo,usuario);
                        user.setId(id);

                        //se le pasan al arreglo dinamico y a la tabla hash
                        filling.insertarArchivo(user);
                        hash.insert(user);

                    }

                    //por ultimo, es necesario poder obtener estos dato, para ello, los cargamos en valores estaticos
                    HashDocument.fullEventos =true;
                    HashDocument.user=filling;
                    HashDocument.users=hash;

                    //en este punto no es necesario terminar con la actividad actual, solo iniciar con la anterior
                    OpcionesEventos.this.startActivity(intent);


                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),e.toString() ,Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),/*"error al hacer la busqueda en la base de datos  "+*/ error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        request.add(jeison);



    }
}
