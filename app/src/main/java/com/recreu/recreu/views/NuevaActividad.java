package com.recreu.recreu.views;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.recreu.recreu.Modelos.Actividad;
import com.recreu.recreu.Modelos.Usuario;
import com.recreu.recreu.controllers.HttpPost;
import com.recreu.recreu.utilities.SystemUtilities;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import cl.recreu.recreu.taller_android_bd.R;


public class NuevaActividad extends Fragment implements View.OnClickListener {
   // private Actividad actividad;
    private View vistaActividad;
    private  Button agregarActividad;
    private EditText titulo, cuerpo, requisitos,personitas;
    private EditText fecha;
    private Date fechainicio;
    private  String duracion,strgFecha,strgDuracion;
   // private int mYear, mMonth, mDay;
    private  int maximoPersonas;
    private  float x,y;
    private final String URL_GET = "http://10.0.2.2:8080/javaee/actividades";
    private Context c;
    private Usuario usuario;



    public NuevaActividad(Usuario usu) {
        this.usuario=usu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vistaActividad = inflater.inflate(R.layout.nueva_actividad, container, false);
        titulo = (EditText) vistaActividad.findViewById(R.id.titulo);
        cuerpo = (EditText) vistaActividad.findViewById(R.id.descripcion);
        requisitos =(EditText) vistaActividad.findViewById(R.id.requisitos);
        //fecha =(EditText) vistaActividad.findViewById(R.id.fecha);

        personitas  =(EditText)vistaActividad.findViewById(R.id.cupo) ;
        agregarActividad = (Button) vistaActividad.findViewById(R.id.botonAgregarActividad);
        agregarActividad.setOnClickListener(this);

       // duracion = vistaActividad.findViewById(R.id.duracion); CAMBIAR MODO INGRESO DURACION
        duracion = "02:00:10-03:00";
        strgDuracion=duracion.toString();

        EditText personitas  =(EditText)vistaActividad.findViewById(R.id.cupo) ;
        return vistaActividad;
    }



    @Override
    public void onClick(View v) {
//
//
// INGRESAR AQUI COORDENADAS MAPA
        x= 123;
        y=456;

        String cupos = personitas.getText().toString();
        maximoPersonas = Integer.parseInt(cupos);

        Calendar cal = Calendar.getInstance(); // toma hora de ahora :(
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strgFecha = sdf.format(cal.getTime());
        System.out.println("stringfecha : " +strgFecha);

            // creo objeto con atributos ingresados en la vista NuevaActividad
           //actividad = new Actividad(titulo.getText().toString(),cuerpo.getText().toString(),requisitos.getText().toString(),fechainicio ,null, x,y,0,0,maximoPersonas);

            if ( titulo.getText().toString().length() == 0  ){
               Toast.makeText(c, " Debe ingresar un titulo ", Toast.LENGTH_SHORT).show();
            }
            if ( cuerpo.getText().toString().length() == 0  ){
                Toast.makeText(c, " Debe ingresar una descripción ", Toast.LENGTH_SHORT).show();
             }
            if ( requisitos.getText().toString().length() == 0  ){
                Toast.makeText(c, " Debe ingresar uno o mas requisitos ", Toast.LENGTH_SHORT).show();
             }
            if ( maximoPersonas  <1 ){
                 Toast.makeText(c, " Debe ingresar cupos ", Toast.LENGTH_SHORT).show();
             }


 // actualizar WAR y AGREGARLE MAXIMO PERSONAAS al json
        String nuevaActividad = "{\"cuerpoActividad\":\""+cuerpo.getText().toString()+
                //"\",\"duracionEstimada\":\""+strgDuracion+
                "\",\"duracionEstimada\":\" "+strgDuracion+" \" ,\"fechaInicio\":\"2016-05-02T08:15:03-03:00\"," +
              //  "\",\"fechaInicio\":\""+strgFecha+
                "\"requerimientosActividad\":\""+requisitos.getText().toString()+
                "\",\"tipo\":{ \"tipoId\":2}"+  // esto es el tipo ID de la actividad,, y escribendo esto me acabo de dar cuenta que tengo que mostrarle una lista a elegir al usuaio :C
                ",\"tituloActividad\":\""+titulo.getText().toString()+
               "\",\"organizador\":{ \"usuarioId\":4}"+
              //  "\",\"organizador\":{ \""+usuario.getId()+"\":4}"+  VA ESTA LINEA EN JSON, pero falata usuario
                ", \"ubicacionActividadX\":"+x+
                ",\"ubicacionActividadY\":"+y+"}";


       //  System.out.println(nuevaActividad);

            try {
                SystemUtilities su = new SystemUtilities(getActivity().getApplicationContext());
                if (su.isNetworkAvailable()) {
                    try {
                        AsyncTask resp = new HttpPost(getActivity().getApplicationContext()).execute(nuevaActividad,URL_GET);
                        Toast.makeText(getActivity(), " Agregando Actividad ...   ", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
            }

    }








}