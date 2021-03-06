package com.agatone.edun.activitys.Dialogs;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.agatone.edun.R;

public class LoginDialog extends AppCompatDialogFragment {

    private EditText nombreUsuario;
    private EditText contrasenaUsuario;

    private LoginDialogListener listener;


    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_login,null);

        builder.setView(view)
                .setTitle("Iniciar sesion")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Loguear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user=nombreUsuario.getText().toString();
                        String cont=contrasenaUsuario.getText().toString();
                        listener.confirmUser(user,cont);
                    }
                });

        nombreUsuario=view.findViewById(R.id.nombreUsuario);
        contrasenaUsuario=view.findViewById(R.id.contrasenaUsuario);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener=(LoginDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+" must implement ExampleDialogListener");
        }
    }

    public interface LoginDialogListener{
        void confirmUser(String user, String password);
    }
}
