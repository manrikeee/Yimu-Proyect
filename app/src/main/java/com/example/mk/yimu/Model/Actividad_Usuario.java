package com.example.mk.yimu.Model;

/**
 * Created by Mk on 18/05/2016.
 */
public class Actividad_Usuario {
    int id,id_usuario,id_actividad;

    public Actividad_Usuario(int id, int id_usuario, int id_actividad) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_actividad = id_actividad;
    }

    public Actividad_Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }
}
