package com.example.mk.yimu.Model;

/**
 * Created by Mk on 31/03/2016.
 */
public  class Usuario {

    public String nombre, localidad, password, email, tipo;
    public int id = 0;
    public String edad;
    public int estado = 0;
    public static String nombre1, email1;
    public static int id1;

    public Usuario() {
    }

    public Usuario(String nombre, String localidad, String password, String email, int id, String tipo, String edad) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.password = password;
        this.email = email;
        this.id = id;
        this.tipo = tipo;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        nombre1=nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        email1=email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        id1=id;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
