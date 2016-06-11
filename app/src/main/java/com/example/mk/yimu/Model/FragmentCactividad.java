package com.example.mk.yimu.Model;


import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mk.yimu.Interface.ActividadService;
import com.example.mk.yimu.LoginActivity;
import com.example.mk.yimu.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentCactividad.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCactividad#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCactividad extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentCactividad() {


        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentCactividad newInstance() {
        FragmentCactividad fragment = new FragmentCactividad();
        Bundle args = new Bundle();
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.f_crearactividad, container, false);
        final List<String>deportes= new ArrayList<>();
        for (Deporte deportess: LoginActivity.deportes_disponibles){
            deportes.add(deportess.getNombre());
        }


        final EditText nombre1 = (EditText) view.getRootView().findViewById(R.id.nombredeporte);
        nombre1.setInputType(InputType.TYPE_NULL);
        final ArrayAdapter<String> spinner = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, deportes);
        nombre1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Selecciona deporte")
                        .setAdapter(spinner, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                nombre1.setText(deportes.get(which).toString());
                                dialog.dismiss();
                            }
                        }).create().show();

            }
        });
        Button boton = (Button) view.getRootView().findViewById(R.id.registrar);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText deportet = (EditText) view.getRootView().findViewById(R.id.nombredeporte);
                EditText fechat = (EditText) view.getRootView().findViewById(R.id.date);
                EditText horat = (EditText) view.getRootView().findViewById(R.id.hora);
                EditText max_personast = (EditText) view.getRootView().findViewById(R.id.max_personas);
                EditText plazas_disponiblest = (EditText) view.getRootView().findViewById(R.id.plazas_libres);

                EditText lugar1= (EditText) view.getRootView().findViewById(R.id.lugar);
                String deporte = deportet.getText().toString();
                String fecha = fechat.getText().toString();
                String hora = horat.getText().toString();
                String lugar = lugar1.getText().toString();
                int estado = 1;
                int max_personas = Integer.parseInt(max_personast.getText().toString());
                int plazas_disponibles = Integer.parseInt(plazas_disponiblest.getText().toString());


                RestClient restClient =new RestClient();
                Retrofit retrofit=restClient.getRetrofit();
                ActividadService service = retrofit.create(ActividadService.class);
                final Call<String> respuesta = service.CrearActividad(deporte, "2016-05-17", "10:00:00", Usuario.id1, 1, max_personas, plazas_disponibles,lugar,"Principiante");
                Toast.makeText(getContext(), "Actividad Creada", Toast.LENGTH_SHORT).show();
                respuesta.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("ERROR",""+t.getMessage());

                    }
                });
            }
        });

        // TODO: Rename method, update argument and hook method into UI event
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void onFragmentInteraction(Uri uri) {
        //you can leave it empty
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
