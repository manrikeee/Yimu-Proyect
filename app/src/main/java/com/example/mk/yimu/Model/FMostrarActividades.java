package com.example.mk.yimu.Model;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mk.yimu.Interface.ActividadService;
import com.example.mk.yimu.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by ivan on 05/05/2016.
 */
public class FMostrarActividades extends Fragment {
    public static List<Actividad> actividades;
    static RecyclerView card;
    static CardAdapter myadaptador;
    Button participar;
    View view;

    public static void allEvents() {

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        ActividadService servicio = retrofit.create(ActividadService.class);
        Call<List<Actividad>> respuesta = servicio.getActividades();
        actividades = new ArrayList<>();
        respuesta.enqueue(new Callback<List<Actividad>>() {

            @Override
            public void onResponse(Call<List<Actividad>> call, Response<List<Actividad>> response) {
                actividades = response.body();
                myadaptador = new CardAdapter(actividades);
                card.setAdapter(myadaptador);
            }

            @Override
            public void onFailure(Call<List<Actividad>> call, Throwable t) {
                Log.i("allEvents", "ERROR12 : " + t.getMessage());
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.ventana_items, container, false);
        participar= (Button) view.findViewById(R.id.button);

        actividades = new ArrayList<>();
        allEvents();

        card = (RecyclerView)view.getRootView().findViewById(R.id.reciclador);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        card.setLayoutManager(layout);



        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

}
