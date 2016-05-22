package com.recreu.recreu.views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;


import cl.recreu.recreu.taller_android_bd.R;

import com.recreu.recreu.MainActivity;
import com.recreu.recreu.controllers.HttpPost;
import com.recreu.recreu.utilities.SystemUtilities;

import java.util.Calendar;

public class CrearUsuario extends AppCompatActivity implements View.OnClickListener{
    private EditText primerNombre;
    private EditText segundoNombre;
    private EditText apellidoPaterno;
    private EditText apellidoMaterno;
    private EditText correo;
    private RadioButton masculino;
    private DatePicker calendario;
    private Button botonOk, botonFecha;
    private int mYear, mMonth, mDay;
    private EditText fecha;
    private String URL_GET = "http://10.0.2.2:8080/javaee/usuarios";
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);
        primerNombre = (EditText) findViewById(R.id.primerNombre);
        segundoNombre = (EditText) findViewById(R.id.segundoNombre);
        apellidoPaterno = (EditText) findViewById(R.id.apellidoPaterno);
        apellidoMaterno = (EditText) findViewById(R.id.apellidoMaterno);
        correo = (EditText) findViewById(R.id.correo);
        masculino = (RadioButton) findViewById(R.id.masculino);
        botonOk = (Button) findViewById(R.id.Ok);
        botonFecha = (Button) findViewById(R.id.botonFechaNacimiento);
        fecha = (EditText)findViewById(R.id.fechaNacimiento);
        password=(EditText)findViewById(R.id.password);
        botonFecha.setOnClickListener(this);

    }

    public void okCrearUsuario(View vista){
        Toast notificacion=Toast.makeText(this,"Creando al Usuario ",Toast.LENGTH_LONG);
        notificacion.show();

        boolean sexo  = masculino.isChecked();

        //  String usuStr= "{\"primerNombre\":\"" + primerNombre.getText().toString()+ "\",\"segundoNombre\":\"" + segundoNombre.getText().toString()+ "\",\"correo\":\"" + segundoNombre.getText().toString()+ ""}";//DEBE MEJORARSE ESTA PARRTE
        String usuStr = "{\"apellidoMaterno\":\""+apellidoMaterno.getText().toString()+
                "\",\"apellidoPaterno\":\""+apellidoPaterno.getText().toString()+
                "\",\"primerNombre\":\""+primerNombre.getText().toString()+
                "\",\"segundoNombre\":\""+segundoNombre.getText().toString()+
                "\",\"correo\":\""+correo.getText().toString()+
                "\",\"password\":\""+password.getText().toString()+
                "\",\"fechaNacimiento\":\""+fecha.getText().toString()+
                "\", \"sexo\":"+sexo+
                ",\"esAdministrador\": false}";
        System.out.println(usuStr);
        try {
            SystemUtilities su = new SystemUtilities(this.getApplicationContext());

            if (su.isNetworkAvailable()) {
                try {
                    AsyncTask resp = new HttpPost(this.getApplicationContext()).execute(usuStr,URL_GET);
                    Toast.makeText(this, "se agregó a ", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            System.out.println("Hola hola: error: "+e.toString());
        }
        finish();


    }

    @Override
    public void onClick(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String mes;
                        if (monthOfYear<9) mes="0"+(monthOfYear+1);
                        else mes= ""+(monthOfYear+1);
                        String dia;
                        if (dayOfMonth<9) dia="0"+(dayOfMonth+1);
                        else dia= ""+(dayOfMonth+1);

                        fecha.setText( year + "-" + mes + "-" + dia);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

}
