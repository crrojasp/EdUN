package com.agatone.edun.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.agatone.edun.Clases.archivo;
import com.agatone.edun.Clases.fillArray;
import com.agatone.edun.Ftp_up_down.Coneccion;
import com.agatone.edun.Ftp_up_down.Subida;
import com.agatone.edun.R;
import com.agatone.edun.adapters.archivosAdapter;
import com.agatone.edun.auxiliares.ArchivoSubirBajar;
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

public class NuevoDocumento extends AppCompatActivity {

    private ImageButton regresar;

    private EditText nombre_documento;
    private EditText autor_documento;

    private RadioGroup radioGroup;
        private RadioButton si;
        private RadioButton no;

    private Button comprobar;
    private Button subir;

    private TextView nombreTxt;
    private TextView autorTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_documento);

        iniciar();


    }

    /**
     * ZONA DE INICIALIZACION DE COMPONENTES GRAFICOS
     */
    private void iniciar(){
        regresar=(ImageButton) findViewById(R.id.regresar);

        nombre_documento=findViewById(R.id.nombre_documento);
        autor_documento=findViewById(R.id.autor_documento);

        radioGroup=findViewById(R.id.radioGroup);
            si=findViewById(R.id.si);
            no=findViewById(R.id.no);

        comprobar=findViewById(R.id.button);
        subir=findViewById(R.id.subir);

        nombreTxt=findViewById(R.id.nombreTxt);
        autorTxt=findViewById(R.id.autorTxt);

        si.setChecked(true);
        if(!ArchivoSubirBajar.esSubible){
            subir.setVisibility(View.INVISIBLE);
        }

    }


    /**
     * ZONA DE BOTONES
     *
     */


    //Controlador para regresar a la activity anterior
    public void regresar(View view) {
        Intent intent= new Intent (NuevoDocumento.this,documentos.class);
        NuevoDocumento.this.startActivity(intent);
        NuevoDocumento.this.finish();
    }

    //Controlador del boton "Button"
    public void comprobar_abrir(View view) {
        if(verificar_campos()){
            String nombre_documento=this.nombre_documento.getText().toString();
            existe_nombre_archivo(nombre_documento);
        }
    }

    //Controlador del RadioButton "no"
    public void no_click(View view) {
        radioGroup.clearCheck();
        no.setChecked(true);
    }

    //Controlador del RadioButton "si"
    public void si_click(View view) {
        radioGroup.clearCheck();
        si.setChecked(true);
    }


    /**
     * FUNCIONES AUXILIARES
     */
    private boolean verificar_campos(){
        boolean v=true;

        nombreTxt.setText("");
        autorTxt.setText("");

        if(nombre_documento.getText().toString().equals("")){
            nombreTxt.setText("Falta el nombre del archivo");
            v=false;
        }

        if(autor_documento.getText().toString().equals("")){
            autorTxt.setText("Falta el nombre del autor, opcional");
        }

        return v;

    }

    private void existe_nombre_archivo(final String nombre_document){
        boolean v=false;



        RequestQueue request;
        JsonObjectRequest jeison;





        request= Volley.newRequestQueue(getApplicationContext());
        String url=null;
        String nombre=nombre_document.replace(" ","%20");
        url="http://"+ Coneccion.host+"/Documentos/existe_archivo.php?nombre_archivo="+nombre;
        jeison=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray json=response.optJSONArray("val");
                    int value=Integer.parseInt(json.getString(0));
                    if(value==0){
                        nombreTxt.setText("Nombre repetido, porfavor dar otra opcion");
                    }else{

                        archivo arc[]=new archivo[1];

                        String nombre=nombre_documento.getText().toString();
                        String tipo=ArchivoSubirBajar.type;
                        String autor=autor_documento.getText().toString();

                        if(autor.equals(""))
                            arc[0]=new archivo(nombre, tipo,ArchivoSubirBajar.uri);
                        else
                            arc[0]=new archivo(nombre,autor,tipo,ArchivoSubirBajar.uri);


                        ArchivoSubirBajar.archivo=arc;

                        subir.setVisibility(View.VISIBLE);
                        ArchivoSubirBajar.esSubible=false;
                    }




                } catch (Exception e) {
                    e.printStackTrace();
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

    private void ejecutese(archivo [] arc){
        Subida subir=new Subida(getApplicationContext(),arc[0]);
        Toast.makeText(getApplicationContext(),"holaaaaa",Toast.LENGTH_LONG).show();
        subir.execute(arc);
        ArchivoSubirBajar.isEjecutable=false;
        NuevoDocumento.this.finish();

    }


    public void subir(View view) {
        Subida subir=new Subida(getApplicationContext(),ArchivoSubirBajar.archivo[0]);
        subir.execute(ArchivoSubirBajar.archivo);
    }


    //cualquier cambio dentro del campo nombre obliga a recomprobar si el nombre es posible
    public void cambiosNombre(View view) {
        subir.setVisibility(View.INVISIBLE);
    }
}
