package com.recreu.recreu.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.recreu.recreu.Modelos.Actividad;
import com.recreu.recreu.Modelos.Usuario;

import cl.recreu.recreu.taller_android_bd.R;


public class detalleActividad extends Fragment implements View.OnClickListener {

    private TextView cajaTitutlo;
    private TextView cajaCuerpo;
    private TextView cajaRequisitos;
    private Actividad actividad;
    private boolean participando;
    private boolean disponibilidad;
    private int resultadoConsulta;
    private Button botonPC;
    private int IdUsuario_Actividad;
    private Usuario usuario;

    public detalleActividad(Actividad act, Usuario usu) {
        this.actividad=act;
        this.usuario=usu;
    }

    public detalleActividad(Actividad act) {
        this.actividad=act;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detalle_actividad, container, false);
    }

    /*** Método que se llama una vez que se ha restaurado el estado del fragmento */
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {

        cajaTitutlo = ((TextView) getView().findViewById(R.id.TextViewTitulo)); //  -> estoy seteando
        cajaTitutlo.setText(actividad.getTitulo());

        cajaCuerpo = ((TextView) getView().findViewById(R.id.TextViewCuerpo)); //  -> estoy seteando
        cajaCuerpo.setText(actividad.getCuerpo());

        cajaRequisitos = ((TextView) getView().findViewById(R.id.TextViewRequisitos)); //  -> estoy seteando
        cajaRequisitos.setText(actividad.getRequerimientos());

        botonPC = ((Button) getView().findViewById(R.id.botonPC));


        // REALIZAR CONSULTA A MYSQL USUARIO_ACTIVIDAD QUE RETORNE BOOLEANO SI USUARIO PARTICIPA O NO DE ID ACTIVIDAD ( ej con false )
        // BUSCAR  Usuario.getID() Actividad.getID() juntos;

        participando=true;

        if ( participando == true){
            botonPC.setText("CANCELAR PARTICIPACIÓN");
        }else{

            // REALIZAR UNA CONSULTA TIPO COUNT() QUE RETORNE EL TOTAL DE PARTICIPANTES EN LA ACTIVIDAD
            // ( ejemplo actividad sin participantes
            resultadoConsulta=0;

            if(actividad.getMaximoPersonas() > resultadoConsulta){
                botonPC.setText("PARTICIPAR");
            }else{
                botonPC.setText("SIN CUPOS");
                botonPC.setClickable(false);
            }


        }




        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

  //      int ideUsuario = Usuario.getId();

        if ( participando == true){
            //CANCELAR PARTICIPACIÓN
        }else{

            // POST : AGREGAR FILA A USUARIO_ACTIVIDAD con Usuario.getID() y Actividad.getID()

        }
    }



}