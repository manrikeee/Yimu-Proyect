package com.example.mk.yimu.Interface;

import com.example.mk.yimu.Model.Espacio_Horario;
import com.example.mk.yimu.Model.Espacio_Reserva;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mk on 29/04/2016.
 */
public interface EspacioHorarioService {
    @GET("/webservice/obtener_horario_centro.php")
    Call<List<Espacio_Horario>> getHorarios(@Query("id") int id, @Query("id_espacio") int password,@Query ("dia_semana") int dia);


    @GET("/webservice/obtener_reservas.php")
    Call<List<Espacio_Reserva>> getReservas( @Query("id_espacio") int password, @Query("fecha") Date fecha);

}
