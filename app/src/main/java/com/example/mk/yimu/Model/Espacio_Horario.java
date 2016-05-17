package com.example.mk.yimu.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;

/**
 * Created by Mk on 29/04/2016.
 */
public class Espacio_Horario implements Parcelable{
    int id;
    int id_espacio;
    String hora_apertura;
    String hora_cierre;
    int estado;
    String dia_semana;

    public Espacio_Horario() {
    }

    public Espacio_Horario(int id, int id_espacio, String hora_apertura, String hora_cierre, int estado,String dia_semana) {
        this.id = id;
        this.id_espacio = id_espacio;
        this.hora_apertura = Time.valueOf(hora_apertura).toString();
        this.hora_cierre = Time.valueOf(hora_cierre).toString();
        this.estado = estado;
        this.dia_semana=dia_semana;
    }

    protected Espacio_Horario(Parcel in) {
        id = in.readInt();
        id_espacio = in.readInt();
        estado = in.readInt();
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public static final Creator<Espacio_Horario> CREATOR = new Creator<Espacio_Horario>() {
        @Override
        public Espacio_Horario createFromParcel(Parcel in) {
            return new Espacio_Horario(in);
        }

        @Override
        public Espacio_Horario[] newArray(int size) {
            return new Espacio_Horario[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(String hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public int getId_espacio() {
        return id_espacio;
    }

    public void setId_espacio(int id_espacio) {
        this.id_espacio = id_espacio;
    }

    public String getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(String hora_cierre) {
        this.hora_cierre = hora_cierre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_espacio);
        dest.writeInt(estado);
    }
}
