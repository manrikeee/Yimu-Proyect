package com.example.mk.yimu.Model;


import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.mk.yimu.Interface.ActividadService;
import com.example.mk.yimu.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        final String[] deportes = {"Futbol", "Padel", "Baloncesto"};


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
                                nombre1.setText(deportes[which].toString());
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
                EditText fechat = (EditText) view.getRootView().findViewById(R.id.fecha);
                EditText horat = (EditText) view.getRootView().findViewById(R.id.hora);
                EditText max_personast = (EditText) view.getRootView().findViewById(R.id.max_personas);
                EditText plazas_disponiblest = (EditText) view.getRootView().findViewById(R.id.plazas_libres);
                EditText timet = (EditText) view.getRootView().findViewById(R.id.date);
                String deporte = deportet.getText().toString();
                String fecha = deportet.getText().toString();
                String hora = horat.getText().toString();
                String lugar = deportet.getText().toString();
                String estado = "1";
                String max_personas = max_personast.getText().toString();
                String plazas_disponibles = plazas_disponiblest.getText().toString();
                final String BASE_URL = "http://192.168.1.11";
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();
                ActividadService service = retrofit.create(ActividadService.class);
                final Call<String> respuesta = service.CrearActividad(deporte, fecha, hora, "1", "1", max_personas, plazas_disponibles, estado);

                respuesta.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

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
