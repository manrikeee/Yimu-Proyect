package com.example.mk.yimu.Interface;

import com.example.mk.yimu.Model.Deporte;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mk on 25/04/2016.
 */
public interface DeportesService {
    @GET("/webservice/obtener_deportes_centro.php")
    Call<List<Deporte>> getDeporte(@Query("id") int id);

    @GET("/webservice/obtener_deportes_usuario.php")
    Call<List<Deporte>> getDeporteUsuario(@Query("id") int id);

    @GET("/webservice/obtener_deportes.php")
    Call<List<Deporte>> getDeportes();

    @GET("/webservice/insertar_deporte.php")
    Call <String>setDeporte(@Query("id_usuario") int id_usuario,@Query("id_deporte") int id_deporte,@Query("nivel")String nivel);
}

