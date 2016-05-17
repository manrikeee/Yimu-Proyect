package com.example.mk.yimu.Interface;

import com.example.mk.yimu.Model.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by Mk on 31/03/2016.
 */
public interface UsuarioService {

    @GET("/webservice/login1.php")
    Call<Usuario> getUser(@Query("nombre") String id, @Query("password") String password);


}