package com.example.mk.yimu.Model;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mk.yimu.R;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FSeleccionHora.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FSeleccionHora#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FSeleccionHora extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private List<Espacio_Reserva> reservas=  new ArrayList<Espacio_Reserva>();
    private RecyclerView lista;
    private Long horas;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FSeleccionHora() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FSeleccionHora.
     */
    // TODO: Rename and change types and number of parameters
    public static FSeleccionHora newInstance(String param1, String param2) {
        FSeleccionHora fragment = new FSeleccionHora();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle=this.getArguments();
        reservas=bundle.getParcelableArrayList("reservas");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.reservahora, container, false);
        List<String> horas = new ArrayList<>();
        if (FragmentAPista.horarios!=null) {

            String inicio_horario = FragmentAPista.horarios.get(0).getHora_apertura();
            String fin_horario = FragmentAPista.horarios.get(0).getHora_cierre();
            int minutos_bloques = FragmentAPista.espacios.get(0).getMinutos_bloque();
            Calendar cal = Calendar.getInstance();
            Calendar calfin = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            try {
                cal.setTime(sdf.parse(inicio_horario));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                calfin.setTime(sdf.parse(fin_horario));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal2 = cal;
            Calendar calaux = cal2;
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            Time apertura = Time.valueOf(FragmentAPista.horarios.get(0).getHora_apertura().toString());
            Time cierre = Time.valueOf(FragmentAPista.horarios.get(0).getHora_cierre().toString());
            Time inicio = apertura;
            Log.d("ERRUR", "apertura," + apertura.toString() + " cierre : " + cierre.toString());
            ArrayList<String> horas1=new ArrayList<>();

            do {
                horas.add(formatter.format(inicio) + "      -      " + formatter.format(crearRango(inicio, 60)));
                inicio = crearRango(inicio, 60);

            } while (inicio.getTime() < cierre.getTime());


            ArrayList<String> horas_inicio_citas=new ArrayList<>();
            ArrayList<String> horas_fin_citas=new ArrayList<>();
            for (String a:horas){
                horas_inicio_citas.add(a.substring(0,5));
                horas_fin_citas.add(a.substring(18,23));

            }
            Log.d("Tamano"," E:"+ horas_inicio_citas.get(0) + "|"+reservas.get(0).getHora_inicio().substring(0,5));

            Log.i("VALOR","apertura:"+horas_inicio_citas.get(0)+"cierre: +"+horas_fin_citas.get(0));



            for (int i=0; i< horas_inicio_citas.size(); i++){
                    for (Espacio_Reserva reserva: reservas ){

                        if (reserva.getHora_inicio().substring(0,5).equals(horas_inicio_citas.get(i))){
                            Log.e("VALORES","VALOR 1:"+reserva.getHora_inicio().substring(0,5)+ "VALOR 2:"+horas_inicio_citas.get(i) );

                            horas_inicio_citas.remove(i);
                            horas_fin_citas.remove(i);
                        }
                        }
                }
            Log.i("VALOR","citas ("+horas_inicio_citas.get(0));
            horas=new ArrayList<>();

            for (int i=0; i< horas_inicio_citas.size(); i++){
                horas.add(horas_inicio_citas.get(i) + "      -      " + horas_fin_citas.get((i)));

            }
        }else {
            horas.add("No hay citas para mostrar");
        }

        RecyclerView recyclerView = (RecyclerView) view.getRootView().findViewById(R.id.RecView);
        //recyclerView.setHasFixedSize(true);
        LinearLayoutManager llmanager = new LinearLayoutManager(getActivity());
        llmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llmanager);

        AdapterRecView myAdapter = new AdapterRecView( horas);
        recyclerView.setAdapter(myAdapter);
        return view;
    }
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public Time crearRango(Time time,int bloque) {
        String myTime = time.toString();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date d = null;
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

    public String SumarTime(Time time,int periodo){
        Calendar cal=Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MINUTE,periodo);
        String a=cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
        return a;
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
       
        void onFragmentInteraction(Uri uri);
    }



}
