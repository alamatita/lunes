package com.recreu.recreu.views;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
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
    private View vistaActividad;
    private Button agregarActividad, botonFecha, botonHoraInicio;
    private EditText titulo, cuerpo, requisitos, personitas;
    private EditText duracion;
    private EditText fecha;
    private EditText horainicio;
    private String strgFecha, strgDuracion;
    private int mYear, mMonth, mDay;
    private int maximoPersonas;
    private String fechainicio;
    private float x, y;
    private Spinner spiner;
    private final String URL_POST = "http://10.0.2.2:8080/javaee/actividades";
    private Context c;
    private Usuario usuario;


    public NuevaActividad(Usuario usu) {
        this.usuario = usu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vistaActividad = inflater.inflate(R.layout.nueva_actividad, container, false);
        titulo = (EditText) vistaActividad.findViewById(R.id.titulo);
        cuerpo = (EditText) vistaActividad.findViewById(R.id.descripcion);
        requisitos = (EditText) vistaActividad.findViewById(R.id.requisitos);
        personitas = (EditText) vistaActividad.findViewById(R.id.cupo);
        duracion = (EditText) vistaActividad.findViewById(R.id.duracion);

        fecha = (EditText) vistaActividad.findViewById(R.id.fechainicio);
        botonFecha = (Button) vistaActividad.findViewById(R.id.botonFecha);
        botonFecha.setOnClickListener(this);

        Spinner spiner = (Spinner) vistaActividad.findViewById(R.id.spinner);  // CATEGORIAUS


        horainicio = (EditText) vistaActividad.findViewById(R.id.horainicio);

        botonHoraInicio = (Button) vistaActividad.findViewById(R.id.botonHoraInicio);
        botonHoraInicio.setOnClickListener(this);

        agregarActividad = (Button) vistaActividad.findViewById(R.id.botonAgregarActividad);
        agregarActividad.setOnClickListener(this);


        //  fechainicio=(DatePicker)vistaActividad.findViewById(R.id.fechainicio);

        personitas = (EditText) vistaActividad.findViewById(R.id.cupo);
        return vistaActividad;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.botonAgregarActividad:
                x = 123;
                y = 456;

                String cupos = personitas.getText().toString();
                maximoPersonas = Integer.parseInt(cupos);

               //System.out.println("FECHITA CALENDARIO : " + fecha.getText().toString());


                strgDuracion = "" + duracion.getText().toString() + ":00-03:00";
                strgDuracion = "01:30:00";
                if (titulo.getText().toString().length() == 0) {
                    Toast.makeText(c, " Debe ingresar un titulo ", Toast.LENGTH_SHORT).show();
                }
                if (cuerpo.getText().toString().length() == 0) {
                    Toast.makeText(c, " Debe ingresar una descripción ", Toast.LENGTH_SHORT).show();
                }
                if (requisitos.getText().toString().length() == 0) {
                    Toast.makeText(c, " Debe ingresar uno o mas requisitos ", Toast.LENGTH_SHORT).show();
                }
                if (maximoPersonas < 1) {
                    Toast.makeText(c, " Debe ingresar cupos ", Toast.LENGTH_SHORT).show();
                }

                String Fechafinal=""+fecha.getText().toString()+"T"+horainicio.getText().toString()+":00-03:00";

                // AGREGARLE MAXIMO PERSONAAS al json
                String nuevaActividad = "{\"cuerpoActividad\":\"" + cuerpo.getText().toString() +
                        //"\",\"duracionEstimada\":\""+strgDuracion+
                        "\",\"duracionEstimada\":\" " + strgDuracion + "\" ,\"fechaInicio\":\"" + Fechafinal + "\"," +
                        //  "\",\"fechaInicio\":\""+strgFecha+
                        "\"requerimientosActividad\":\"" + requisitos.getText().toString() +
                        "\",\"tipo\":{ \"tipoId\":2}" +
                        ",\"tituloActividad\":\"" + titulo.getText().toString() +
                        "\",\"organizador\":{ \"usuarioId\":"+usuario.getUsuarioId()+"}" +
                        // "\",\"organizador\":{ \""+usuario.getUsuarioId()+"\"}"+ //               PONER ESTA LINEA CUANDO SE INGRESE LOGIN
                        ", \"ubicacionActividadX\":" + x +
                        ",\"ubicacionActividadY\":" + y + "}";

                try {
                    SystemUtilities su = new SystemUtilities(getActivity().getApplicationContext());
                    if (su.isNetworkAvailable()) {
                        try {
                            AsyncTask resp = new HttpPost(getActivity().getApplicationContext()).execute(nuevaActividad, URL_POST);
                            Toast.makeText(getActivity(), " Agregando Actividad ...   ", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                }


                break;

            case R.id.botonFecha:
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String mes;
                        if (monthOfYear < 9) mes = "0" + (monthOfYear + 1);
                        else mes = "" + (monthOfYear + 1);
                        String dia;
                        if (dayOfMonth < 9) dia = "0" + (dayOfMonth + 1);
                        else dia = "" + (dayOfMonth + 1);

                        fecha.setText(year + "-" + mes + "-" + dia);

                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;


            case R.id.botonHoraInicio:
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        if (selectedHour < 9)
                            horainicio.setText("0" + selectedHour + ":" + selectedMinute);

                        if (selectedMinute < 9)
                            horainicio.setText(selectedHour + ":" + "0" + selectedMinute);

                        if (selectedHour < 9 && selectedMinute < 9)
                            horainicio.setText("0" + selectedHour + ":" + "0" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Selecciona hora Inicio");
                mTimePicker.show();
                break;

        }
    }
}



