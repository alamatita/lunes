package com.recreu.recreu.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.recreu.recreu.controllers.HttpDelete;
import com.recreu.recreu.utilities.SystemUtilities;

import cl.recreu.recreu.taller_android_bd.R;

public class EliminarUsuario extends Fragment {
    private EditText idUsuario;
    private String URL_GET="http://10.0.2.2:8080/javaee/usuarios/";

    public EliminarUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eliminar_usuario, container, false);
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
        idUsuario = (EditText) getView().findViewById(R.id.idUsuario);
        super.onViewStateRestored(savedInstanceState);

    }


    public void eliminarUsuario(View view) {
        String num = idUsuario.getText().toString();
        SystemUtilities su = new SystemUtilities(getActivity().getApplicationContext());
        if (su.isNetworkAvailable()) {
            try {
                new HttpDelete(getActivity().getApplicationContext()).execute(URL_GET + num);
                System.out.println("BORRE AL " + num);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}