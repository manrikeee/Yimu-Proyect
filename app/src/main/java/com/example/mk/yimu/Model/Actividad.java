package com.example.mk.yimu.Model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Mk on 26/04/2016.
 */
public class Actividad {
    int id;
    int deporte;
    String deporte1;

    Date fecha;
    String hora;
    int id_usuario;
    int id_pista;
    int max_personas;
    int plazas_disponibles;
    int estado;
    String lugar;

    public Actividad(int id, int deporte, Date fecha, Time hora, int id_usuario, int id_pista, int max_personas, int plazas_disponibles, int estado,String deporte1,String lugar) {
        this.id = id;
        this.deporte = deporte;
        this.fecha = fecha;
        this.hora=String.valueOf(hora);
        this.id_usuario = id_usuario;
        this.id_pista = id_pista;
        this.max_personas = max_personas;
        this.plazas_disponibles = plazas_disponibles;
        this.estado = estado;
        this.deporte1=deporte1;
        this.lugar=lugar;
    }

    public Actividad() {
    }

    public String getDeporte1() {
        return deporte1;
    }

    public void setDeporte1(String deporte1) {
        this.deporte1 = deporte1;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeporte() {
        return deporte;
    }

    public void setDeporte(int deporte) {
        this.deporte = deporte;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora.toString();
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_pista() {
        return id_pista;
    }

    public void setId_pista(int id_pista) {
        this.id_pista = id_pista;
    }

    public int getMax_personas() {
        return max_personas;
    }

    public void setMax_personas(int max_personas) {
        this.max_personas = max_personas;
    }

    public int getPlazas_disponibles() {
        return plazas_disponibles;
    }

    public void setPlazas_disponibles(int plazas_disponibles) {
        this.plazas_disponibles = plazas_disponibles;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
