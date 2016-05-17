package com.example.mk.yimu.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Date;

/**
 * Created by Mk on 06/05/2016.
 */
public class Espacio_Reserva implements Parcelable {
    int id;
    int id_espacio;
    int id_usuario;
    String hora_inicio;
    String hora_fin;
    Date dia;

    public Espacio_Reserva() {
    }

    public Espacio_Reserva(int id, int id_espacio, int id_usuario, String hora_inicio, String hora_fin, Date dia) {
        this.id = id;
        this.id_espacio = id_espacio;
        this.id_usuario = id_usuario;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.dia = dia;
    }

    protected Espacio_Reserva(Parcel in) {
        id = in.readInt();
        id_espacio = in.readInt();
        id_usuario = in.readInt();
        hora_inicio = in.readString();
        hora_fin = in.readString();
    }

    public static final Creator<Espacio_Reserva> CREATOR = new Creator<Espacio_Reserva>() {
        @Override
        public Espacio_Reserva createFromParcel(Parcel in) {
            return new Espacio_Reserva(in);
        }

        @Override
        public Espacio_Reserva[] newArray(int size) {
            return new Espacio_Reserva[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_espacio() {
        return id_espacio;
    }

    public void setId_espacio(int id_espacio) {
        this.id_espacio = id_espacio;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(id_espacio);
        dest.writeInt(id_usuario);
        dest.writeString(hora_inicio);
        dest.writeString(hora_fin);
    }
}
