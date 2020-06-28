package com.agatone.edun.activitys.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.agatone.edun.R;

public class BuscarDialog extends AppCompatDialogFragment {

    private EditText nombre_archivo;
    private BuscarDialogListener listener;

    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();

        View view=inflater.inflate(R.layout.layout_seacrh,null);

        builder.setView(view)
                .setTitle("Busqueda de archivo")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombreArchivo=nombre_archivo.getText().toString();
                        listener.applyText(nombreArchivo);
                    }
                });

        nombre_archivo=view.findViewById(R.id.nombre_archivo);




        return builder.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener=(BuscarDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+" must implement ExampleDialogListener");
        }
    }

    public interface BuscarDialogListener{
        void applyText(String a);
    }
}
