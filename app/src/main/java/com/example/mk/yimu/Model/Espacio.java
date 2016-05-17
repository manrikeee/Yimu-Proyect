package com.example.mk.yimu.Model;

/**
 * Created by Mk on 08/04/2016.
 */
public class Espacio {
    int id;
    String nombre;
    int estado;
    int id_centro;
    int minutos_bloque;
    int bloques_min;
    int bloques_max;


    public Espacio() {
    }

    public Espacio(int id, String nombre, int estado, int id_centro) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.id_centro = id_centro;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_centro() {
        return id_centro;
    }

    public int getMinutos_bloque() {
        return minutos_bloque;
    }

    public void setMinutos_bloque(int minutos_bloque) {
        this.minutos_bloque = minutos_bloque;
    }

    public int getBloques_min() {
        return bloques_min;
    }

    public void setBloques_min(int bloques_min) {
        this.bloques_min = bloques_min;
    }

    public int getBloques_max() {
        return bloques_max;
    }

    public void setBloques_max(int bloques_max) {
        this.bloques_max = bloques_max;
    }

    public void setId_centro(int id_centro) {
        this.id_centro = id_centro;
    }
}
