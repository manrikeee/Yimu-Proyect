package com.example.mk.yimu.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mk on 14/04/2016.
 */
public class Centro {
    static int id1;
    int estado;
        @SerializedName("id")
        int id;
        @SerializedName("nombre")
        String nombre;
        @SerializedName("localidad")
        String localidad;
        @SerializedName("direccion")
        String direccion;

    public Centro() {
    }



        public Centro(int estado, int id, String nombre, String localidad, String direccion) {
            this.estado = estado;
            this.id = id;
            this.nombre = nombre;
            this.localidad = localidad;
            this.direccion = direccion;
            id1=id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
            id1=id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getLocalidad() {
            return localidad;
        }

        public void setLocalidad(String localidad) {
            this.localidad = localidad;
        }

        public int getEstado() {
            return estado;
        }

        public void setEstado(int estado) {
            this.estado = estado;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

    @Override
    public String toString() {
        return this.nombre;
    }
}
