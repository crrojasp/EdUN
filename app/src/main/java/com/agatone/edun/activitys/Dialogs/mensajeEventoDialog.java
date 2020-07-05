package com.agatone.edun.activitys.Dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.agatone.edun.R;

public class mensajeEventoDialog extends AppCompatDialogFragment {

    private TextView mensaje;


    private LoginDialog.LoginDialogListener listener;


    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_mensaje_evento,null);

        builder.setView(view)
                .setTitle("mensaje")
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(null,null);

        mensaje=view.findViewById(R.id.Mensaje);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener=(LoginDialog.LoginDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+" must implement ExampleDialogListener");
        }
    }

    public interface LoginDialogListener{
        void confirmUser(String user, String password);
    }
}
