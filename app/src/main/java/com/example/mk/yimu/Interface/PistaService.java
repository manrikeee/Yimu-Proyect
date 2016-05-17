package com.example.mk.yimu.Interface;

import com.example.mk.yimu.Model.Espacio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mk on 08/04/2016.
 */
public interface PistaService {

    @GET("/webservice/obtener_nombre_pistas.php")
    Call<List<Espacio>>  getPistas(@Query("id") int id, @Query("id_deporte") int id_deporte);


}
