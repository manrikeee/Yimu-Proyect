package com.example.mk.yimu.Interface;

import com.example.mk.yimu.Model.Actividad;
import com.example.mk.yimu.Model.UsuarioActividad;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mk on 08/04/2016.
 */
public interface ActividadService {
    @GET("/webservice/CrearActividad.php")
    Call<String> CrearActividad(@Query("deporte") String deporte, @Query("fecha") String fecha, @Query("hora") String hora, @Query("id_pista") String id_pista, @Query("id_usuario") String usuario, @Query("max_personas") String max_personas, @Query("plazas_disponibles") String plazas_disponibles, @Query("estado") String estado);
    @GET("/webservice/CrearActividad.php")
    Call<String> CrearActividad1(String s, @Query("deporte") String deporte, @Query("fecha") String fecha, @Query("hora") String hora, @Query("id_usuario") String usuario, @Query("max_personas") String max_personas, @Query("plazas_disponibles") String plazas_disponibles, @Query("estado") String estado);

    @GET("/webservice/obtener_actividades.php")
    Call<List<Actividad>> getActividades();

    @GET("/webservice/actualizar_plazas.php")
    Call<Integer> SumarPlaza(@Query("id") int id);

    @GET("/webservice/insertar_actividad_usuario.php")
    Call<Integer> setActividad_usuario(@Query("id_usuario") int id_usuario, @Query("id_actividad") int actividad_usuario);

    @GET("/webservice/getUsuariosActividad.php")
    Call<List<Integer>> getActividad_usuario(@Query("id_usuario") int actividad);

    @GET("/webservice/getUsuariosActividad.php")
    Call<List<UsuarioActividad>> getUsuariosInscritos(@Query("id_actividad") int id_actividad);

}


