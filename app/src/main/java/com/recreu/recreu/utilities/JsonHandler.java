package com.recreu.recreu.utilities;

import android.util.Log;
import com.recreu.recreu.Modelos.Actividad;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonHandler {





    // Recibo un JSONArray en forma de String y devuelve un String[] con los actores
    public Actividad[] getActividades(String actividades) {
        try {
            JSONArray ja = new JSONArray(actividades);
            Actividad[] arrayActividades = new Actividad[ja.length()];
            Actividad actividad;


            for (int i = 0; i < ja.length(); i++) {
                JSONObject jsonActividad = ja.getJSONObject(i);

          //      String dato = jsonActividad.getString("fechaInicio");
         //       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         //       Date fecha_inicio = (Date) sdf.parse(dato);

     //            dato = row.getString("duracionEstimada");
      //           sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       //          long ms = sdf.parse(dato).getTime();
     //            Time duracion_estimada = new Time(ms);

                String dato=jsonActividad.getString("ubicacionActividadX");
                float x=Float.parseFloat(dato);

                dato=jsonActividad.getString("ubicacionActividadY");
                float y=Float.parseFloat(dato);

                JSONObject jsoncategoria = new JSONObject(jsonActividad.getString("tipo"));
                dato= jsoncategoria.getString("tipoId");
                int id_tipo=Integer.parseInt(dato);

                dato= jsonActividad.getString("actividadId");
                int ide_actividad=Integer.parseInt(dato);

               // dato= row.getString("personasMaximas");             DESCOMENTAR ESTO CUANDOS SE ACTUALICE EL .WAR
               // int cupos=Integer.parseInt(dato);

                // lo que obtengo del JSON lo instancio como nuevaactividad y lo agrego al array que sera retornado
                actividad = new Actividad(jsonActividad.getString("tituloActividad"),jsonActividad.getString("cuerpoActividad"),jsonActividad.getString("requerimientosActividad"),null,null,x,y,id_tipo,ide_actividad,666);

                //actividad = new Actividad(row.getString("tituloActividad"),row.getString("cuerpoActividad"),row.getString("requerimientosActividad"),null,null,x,y,null,ide_actividad,cupos);
                arrayActividades[i] = actividad;

            }
            return arrayActividades;


        } catch (JSONException e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
     //   } catch (ParseException e) {
       //     e.printStackTrace();
        }
        return null;
    }





   /* public JSONArray getJsonActividad(Actividad actividad) {
        try {
            JSONArray ja = new JSONArray().put(actividad);
            return ja;
        } catch (Exception e) {
            Log.e("ERROR", this.getClass().toString() + " " + e.toString());
        }
        return null;
    }*/

}