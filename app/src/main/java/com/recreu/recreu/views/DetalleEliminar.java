package com.recreu.recreu.views;

import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.recreu.recreu.Modelos.Usuario;
import com.recreu.recreu.controllers.HttpDelete;
import com.recreu.recreu.utilities.SystemUtilities;

import cl.recreu.recreu.taller_android_bd.R;

public class DetalleEliminar extends Fragment implements View.OnClickListener{

    private String url;
    private Usuario usuario;
    public DetalleEliminar(String url, Usuario usu) {
        this.url=url;
        this.usuario=usu;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detalle_eliminar, container, false);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Button but = (Button)getActivity().findViewById(R.id.OKEY);
        but.setOnClickListener(this);
        TextView tex = (TextView)getActivity().findViewById(R.id.segurisimo);
        tex.setText("¿Está seguro de eliminar a "+usuario.getPrimerNombre()+" ?");

        super.onViewStateRestored(savedInstanceState);
    }


    @Override
    public void onClick(View v) {
        SystemUtilities su = new SystemUtilities(getActivity().getApplicationContext());
        if (su.isNetworkAvailable()) {
            try {
                new HttpDelete(getActivity().getApplicationContext()).execute(url + usuario.getUsuarioId());
                wait(2000);
                FragmentTransaction transaccion = getFragmentManager().beginTransaction();
                transaccion.replace(R.id.fragment_container, new EliminarUsuario(), "eliminar");
                new Principal();
                transaccion.addToBackStack(null);
                transaccion.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
