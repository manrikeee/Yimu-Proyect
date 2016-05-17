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
}
