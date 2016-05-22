package com.recreu.recreu.Modelos;

import java.util.Date;
import java.sql.Time;

/**
 * Created by ginebra on 14-05-16.
 */
public class Actividad {

    private String titulo;
    private String cuerpo;
    private String requerimientos_actividad;
    private Float ubicacion_actividad_x;
    private Float ubicacion_actividad_y;
    private Date fecha_inicio;
    private Time duracion_estimada;
    private Integer actividadId;
    private Boolean es_activo;
    private Integer tipo_id;
    private Integer maximoPersonas;



    public Actividad(String tit, String cuer, String req, Date fecha,Time tiemp, Float x, Float y, Integer tipo, Integer ide, Integer max){
        titulo=tit;
        cuerpo=cuer;
        requerimientos_actividad=req;
        fecha_inicio=fecha;
        duracion_estimada=tiemp;
        es_activo=true;
        ubicacion_actividad_x=x;
        ubicacion_actividad_y=y;
        tipo_id=tipo;
        actividadId=ide;
        maximoPersonas=max;

    }


    public String getTitulo() {
        return titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public String getRequerimientos() { return requerimientos_actividad;  }

    public Float getX() { return ubicacion_actividad_x;  }

    public Float getY() { return ubicacion_actividad_y;  }

    public Date getFechaInicio() { return fecha_inicio;  }

    public Time getDuracion() {
        return duracion_estimada;
    }

    public Integer getActividadId() {
        return actividadId;
    }

    public boolean getActivod() {
        return es_activo;
    }

    public Integer getTipo() {
        return tipo_id;
    }

    public Integer getMaximoPersonas() { return maximoPersonas; }




}
