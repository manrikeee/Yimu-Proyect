package com.example.mk.yimu.Model;

/**
 * Created by Mk on 18/05/2016.
 */
public class UsuarioActividad {

    public String nombre, localidad, password, email, tipo;
    public int id ;
    public String edad;
    public int estado ;

    public UsuarioActividad(String nombre, String localidad, String password, String email, String tipo, int id, String edad, int estado) {
        this.nombre = nombre;
        this.localidad = localidad;
        this.password = password;
        this.email = email;
        this.tipo = tipo;
        this.id = id;
        this.edad = edad;
        this.estado = estado;
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
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
