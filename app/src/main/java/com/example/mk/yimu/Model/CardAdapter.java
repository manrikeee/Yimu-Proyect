package com.example.mk.yimu.Model;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mk.yimu.Interface.ActividadService;
import com.example.mk.yimu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.widget.Toast.LENGTH_LONG;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.EventoViewHolder> implements View.OnClickListener  {


    private List<Actividad> items;

    public CardAdapter(List<Actividad> items) {
        this.items = items;
    }

    //private Context context;

    /*public MyAdapter(Context c) {
        this.context = c;
        mDataset = new ArrayList();
    }

    public void add(Item i) {
        mDataset.add(i);
        notifyItemInserted(mDataset.indexOf(i));
    }
    public void remove(Item item) {
        int position = mDataset.indexOf(item);

        if(position != -1) {
            mDataset.remove(position);
            notifyItemRemoved(position);
        }
    }*/

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EventoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);
        EventoViewHolder evh = new EventoViewHolder(v);


        return evh;
    }

    @Override
    public void onBindViewHolder(final EventoViewHolder viewHolder, final int i) {
        //viewHolder.imagen.setImageResource(R.drawable.img1);
        viewHolder.nombre.setText(String.valueOf(items.get(i).getDeporte1()));
        viewHolder.lugar.setText(String.valueOf(items.get(i).getLugar()));
        viewHolder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.get(i).getPlazas_disponibles() > 0) {
                    MostrarDialogo(viewHolder, i);

                } else {
                    Toast t = Toast.makeText(viewHolder.boton.getContext(), "La actividad completa", LENGTH_LONG);
                    t.show();
                }

            }
        });
        insertarImagenes(viewHolder);
        mostrarCapacidad(viewHolder, i);


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void insertarImagenes(EventoViewHolder viewHolder) {
        if (viewHolder.nombre.getText().equals("Fútbol")) {
            Context context = viewHolder.imagen.getContext();
            Picasso.with(context)
                    .load(R.drawable.futbol)
                    .resize(200, 200)
                    .centerCrop()
                    .into(viewHolder.imagen);
        }
        if (viewHolder.nombre.getText().equals("Padel")) {
            Context context = viewHolder.imagen.getContext();
            Picasso.with(context)
                    .load(R.drawable.padel)
                    .resize(200, 200)
                    .centerCrop()
                    .into(viewHolder.imagen);
        }
        if (viewHolder.nombre.getText().equals("Tenis")) {
            Context context = viewHolder.imagen.getContext();
            Picasso.with(context)
                    .load(R.drawable.tenis)
                    .resize(200, 200)
                    .centerCrop()
                    .into(viewHolder.imagen);
        }
    }

    public void mostrarCapacidad(EventoViewHolder viewHolder,int i){
        int max=items.get(i).getMax_personas();
        int disponibles=items.get(i).getPlazas_disponibles();
        viewHolder.capacidad.setText(String.valueOf(viewHolder.capacidad.getText() +" "+(max -disponibles)+" / "+max));


    }

    public void SumarPlaza(int i){
        RestClient restClient = new RestClient();
        Retrofit retrofit = restClient.getRetrofit();


        ActividadService servicio = retrofit.create(ActividadService.class);
        Call<Integer> respuesta = servicio.SumarPlaza(i);

        respuesta.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

                FMostrarActividades.allEvents();
                Toast t = Toast.makeText(FMostrarActividades.card.getContext(), "Registrado en actividad", Toast.LENGTH_SHORT);
                t.show();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast tq = Toast.makeText(FMostrarActividades.card.getContext(), "Error al registrarme", Toast.LENGTH_SHORT);
                tq.show();

            }
        });

    }

    public void MostrarDialogo(final EventoViewHolder viewHolder,final int i){

        AlertDialog.Builder dialogo1;
        dialogo1 = new AlertDialog.Builder(viewHolder.boton.getContext());
        dialogo1.setTitle("Registrar");
        dialogo1.setMessage("¿Desea registrarse en esta actividad?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar(viewHolder,i);
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar(viewHolder);
            }
        });
        dialogo1.show();
    }

    public void aceptar(EventoViewHolder viewHolder,int i) {
        SumarPlaza(items.get(i).getId());

    }

    public void cancelar(EventoViewHolder viewHolder) {
        Toast t = Toast.makeText(viewHolder.boton.getContext(), "Cancelado", Toast.LENGTH_SHORT);
        t.show();

    }

    public static class EventoViewHolder extends RecyclerView.ViewHolder {

        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre, capacidad, lugar;
        Button boton;
        CardView cv;

        public EventoViewHolder(View v) {
            super(v);
            cv = (CardView) itemView.findViewById(R.id.card_view);
            imagen = (ImageView) v.findViewById(R.id.card_image);
            nombre = (TextView) v.findViewById(R.id.card_title);
            capacidad = (TextView) v.findViewById(R.id.capacidad);
            lugar = (TextView) v.findViewById(R.id.lugar);
            boton = (Button) v.findViewById(R.id.participar);


        }
    }
    }



