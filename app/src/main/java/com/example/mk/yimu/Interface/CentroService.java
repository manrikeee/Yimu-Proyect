package com.example.mk.yimu.Interface;

import com.example.mk.yimu.Model.Centro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mk on 14/04/2016.
 */
public interface CentroService {
    @GET("/webservice/obtener_centros.php")
    Call<List<Centro>> getCentro(@Query("localidad") String localidad) ;

    }

