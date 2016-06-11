package com.example.mk.yimu.Model;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mk.yimu.Interface.CentroService;
import com.example.mk.yimu.Interface.DeportesService;
import com.example.mk.yimu.Interface.EspacioHorarioService;
import com.example.mk.yimu.Interface.PistaService;
import com.example.mk.yimu.R;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAPista.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAPista#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAPista extends Fragment implements FSeleccionHora.OnFragmentInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static TextView nombre1, nombrecentro, nombredeporte, nombrepista, hora,fecha;

    static ArrayList<Centro> centros;
    static ArrayList<Deporte> deportes;
    static  ArrayList<Espacio> espacios;
    static ArrayList<Espacio_Horario> horarios;
    static ArrayList<Espacio_Reserva> reservas;
    static ArrayList<String> spinnercentros;
    static ArrayList<String> spinnerdeportes;
    static ArrayList<String> spinnerpistas;
    static String localidad = "";
    static ProgressDialog mProgressDialog;
    static  int id_centro, id_deporte, id_espacio;
    View view;
    static Date date;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentAPista() {
    }

    public static FragmentAPista newInstance() {
        FragmentAPista fragment = new FragmentAPista();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        final String[] lugares = {"Granada", "Almeria"};
        nombre1.setInputType(InputType.TYPE_NULL);
        final ArrayAdapter<String> spinner = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, lugares);
        nombre1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(getContext())
                        .setTitle("Selecciona deporte")
                        .setAdapter(spinner, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                nombre1.setText(lugares[which].toString());
                                localidad = nombre1.getText().toString();
                                ObtenerCentros();
                                dialog.dismiss();
                            }
                        }).create().show();

            }


        });

        nombrecentro.setInputType(InputType.TYPE_NULL);
        nombrecentro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                spinnercentros = new ArrayList<String>();
                final ArrayAdapter<String> spinner2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinnercentros);
                spinner2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                for (int i = 0; i < centros.size(); i++) {
                    spinnercentros.add(centros.get(i).getNombre());
                }
                new AlertDialog.Builder(getContext())
                        .setTitle("Selecciona centro")
                        .setAdapter(spinner2, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                nombrecentro.setText(centros.get(which).getNombre().toString());
                                id_centro = centros.get(which).getId();
                                ObtenerDeportes();
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });

        nombredeporte.setInputType(InputType.TYPE_NULL);

        nombredeporte.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                spinnerdeportes = new ArrayList<String>();
                final ArrayAdapter<String> spinner3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerdeportes);
                spinner3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                for (int i = 0; i < deportes.size(); i++) {
                    spinnerdeportes.add(deportes.get(i).getNombre());
                }
                new AlertDialog.Builder(getContext())
                        .setTitle("Selecciona Deporte")
                        .setAdapter(spinner3, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                nombredeporte.setText(deportes.get(which).getNombre().toString());
                                id_deporte = deportes.get(which).getId();
                                ObtenerPistas();
                                dialog.dismiss();
                            }
                        }).create().show();

            }
        });

        nombrepista.setInputType(InputType.TYPE_NULL);
        nombrepista.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                spinnerpistas = new ArrayList<String>();
                final ArrayAdapter<String> spinner4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, spinnerpistas);
                spinner4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                for (int i = 0; i < espacios.size(); i++) {
                    spinnerpistas.add(espacios.get(i).getNombre());
                }
                new AlertDialog.Builder(getContext())
                        .setTitle("Selecciona Espacio")
                        .setAdapter(spinner4, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                nombrepista.setText(espacios.get(which).getNombre().toString());
                                id_espacio = espacios.get(which).getId();

                                dialog.dismiss();
                            }
                        }).create().show();



            }
        });

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new FSeleccionHora();
                Bundle arguments=new Bundle();
                arguments.putParcelableArrayList("reservas", reservas);
                fragment.setArguments(arguments);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment1, fragment)
                        .commit();

            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.f_alquilarpista, container, false);
        nombre1 = (EditText) view.findViewById(R.id.nombrelugar);
        nombrecentro = (EditText) view.findViewById(R.id.nombrecentro);
        nombredeporte = (EditText) view.findViewById(R.id.nombredeporte);
        nombrepista = (EditText) view.findViewById(R.id.nombrepista);
        hora = (EditText) view.findViewById(R.id.hora);
        fecha = (EditText) view.findViewById(R.id.date);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    public static void ObtenerCentros() {
        mProgressDialog = new ProgressDialog(nombre1.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Obteniendo centros...");
        mProgressDialog.show();

        centros = new ArrayList<>();
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();
        CentroService service = retrofit.create(CentroService.class);
        Call<List<Centro>> respuesta = service.getCentro(localidad);


        respuesta.enqueue(new Callback<List<Centro>>() {
            @Override
            public void onResponse(Call<List<Centro>> call, Response<List<Centro>> response) {
                centros = (ArrayList<Centro>) response.body();
                mProgressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Centro>> call, Throwable t) {


            }
        });
    }

    public void ObtenerDeportes() {
        mProgressDialog = new ProgressDialog(nombre1.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Obteniendo deportes...");
        mProgressDialog.show();

        deportes = new ArrayList<>();
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();
        DeportesService service = retrofit.create(DeportesService.class);
        Call<List<Deporte>> respuesta1 = service.getDeporte(id_centro);


        respuesta1.enqueue(new Callback<List<Deporte>>() {

            @Override
            public void onResponse(Call<List<Deporte>> call, Response<List<Deporte>> response) {
                deportes = (ArrayList<Deporte>) response.body();
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Deporte>> call, Throwable t) {

            }
        });
    }

    public void  ObtenerPistas() {
        mProgressDialog = new ProgressDialog(nombre1.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Obteniendo Pistas...");
        mProgressDialog.show();

        espacios = new ArrayList<>();
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();
        PistaService service = retrofit.create(PistaService.class);
        Call<List<Espacio>> respuesta2 = service.getPistas(id_centro, id_deporte);
        respuesta2.enqueue(new Callback<List<Espacio>>() {

            @Override
            public void onResponse(Call<List<Espacio>> call, Response<List<Espacio>> response) {
                espacios = (ArrayList<Espacio>) response.body();
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Espacio>> call, Throwable t) {

            }
        });

    }

    public static void  ObtenerHorario()  {

        Date date= Date.valueOf(fecha.getText().toString());
        horarios=new ArrayList<>();

        mProgressDialog = new ProgressDialog(nombre1.getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Cargando Horarios...");
        mProgressDialog.show();
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();
        EspacioHorarioService service = retrofit.create(EspacioHorarioService.class);
        Call<List<Espacio_Horario>> respuesta2 = service.getHorarios(id_centro, id_espacio,getDaySemana(date));
        respuesta2.enqueue(new Callback<List<Espacio_Horario>>() {
            @Override
            public void onResponse(Call<List<Espacio_Horario>> call, Response<List<Espacio_Horario>> response) {
                horarios= (ArrayList<Espacio_Horario>) response.body();
                ObtenerReservas();
            }

            @Override
            public void onFailure(Call<List<Espacio_Horario>> call, Throwable t) {
                System.out.println("ERROR: "+t.toString());

            }
        });
    }
    public static void ObtenerReservas() {
        reservas=new ArrayList<>();

        Date date= Date.valueOf(fecha.getText().toString());
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();
        EspacioHorarioService service = retrofit.create(EspacioHorarioService.class);
        int dia=getDaySemana(date);
        Call<List<Espacio_Reserva>> respuesta2 = service.getReservas(id_espacio,date);
        respuesta2.enqueue(new Callback<List<Espacio_Reserva>>() {
            @Override
            public void onResponse(Call<List<Espacio_Reserva>> call, Response<List<Espacio_Reserva>> response) {
                reservas = (ArrayList<Espacio_Reserva>) response.body();
                mProgressDialog.dismiss();
                Log.d("RESERVAS",""+reservas.toString());
            }

            @Override
            public void onFailure(Call<List<Espacio_Reserva>> call, Throwable t) {
                System.out.println("ERROR : "+t.toString());
                mProgressDialog.dismiss();

            }
        });
    }


    public Time crearRango(Time time,int bloque) {
        String myTime = time.toString();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        java.util.Date d = null;
        try {
            d = df.parse(myTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MINUTE, bloque);
        Time time1=new Time(cal.getTime().getTime());
        return time1;

    }
    public Date  ConvertStringtoDate(String d){
    /*
        java.util.Date date = null;
        try {
            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    */
        java.sql.Date sql= java.sql.Date.valueOf(d);


        return sql;

    }


    public static int getDaySemana(Date d){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(Calendar.DAY_OF_WEEK);
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