package com.example.mk.yimu.Model;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.mk.yimu.Interface.ActividadService;
import com.example.mk.yimu.MainActivity;
import com.example.mk.yimu.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by manrikeee on 05/05/2016.
 */
public class FMostrarActividades extends Fragment {
    public static List<Actividad> actividades;
    static RecyclerView card;
    static CardAdapter myadaptador;
    Button participar;
    View view;
    static List<UsuarioActividad> usuarios;

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

    public static void getUsuariosInscritos(int id) {

        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        ActividadService servicio = retrofit.create(ActividadService.class);
        Call<List<UsuarioActividad>> respuesta = servicio.getUsuariosInscritos(id);
        usuarios = new ArrayList<>();
        respuesta.enqueue(new Callback<List<UsuarioActividad>>() {


            @Override
            public void onResponse(Call<List<UsuarioActividad>> call, Response<List<UsuarioActividad>> response) {
                usuarios = response.body();
                mostrarUsuarios();

            }

            @Override
            public void onFailure(Call<List<UsuarioActividad>> call, Throwable t) {

                Log.i("allEvents", "ERROR12 : "+t.toString()  );

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.ventana_items, container, false);
        //participar = (Button) view.findViewById(R.id.button);

        actividades = new ArrayList<>();
        allEvents();

        card = (RecyclerView) view.getRootView().findViewById(R.id.reciclador);
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
    public static void mostrarUsuarios() {
        AlertDialog.Builder builder = new AlertDialog.Builder(card.getContext());
        builder.setTitle("Usuarios inscritos");
        ArrayList<String> nombres=new ArrayList<>();

        ListView modeList = new ListView(card.getContext());
        for(UsuarioActividad usuario:usuarios) {
            nombres.add(usuario.getNombre());
        }
        ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(card.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, nombres);
        modeList.setAdapter(modeAdapter);

        builder.setView(modeList);
        final Dialog dialog = builder.create();

        dialog.show();

        Log.i("allEvents", "ERROR12 : "  );
    }
}



