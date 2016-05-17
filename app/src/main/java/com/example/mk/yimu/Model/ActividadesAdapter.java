package com.example.mk.yimu.Model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mk.yimu.R;

import java.util.List;

/**
 * Created by Mk on 26/04/2016.
 */
public class ActividadesAdapter extends RecyclerView.Adapter<ActividadesAdapter.ActividadViewHolder> {
    private List<Actividad> items;

    public static class ActividadViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public ImageView imagen;
        public TextView nombre;
        public TextView visitas;

        public ActividadViewHolder(View v) {
            super(v);
            //imagen = (ImageView) v.findViewById(R.id.imagen);
            nombre = (TextView) v.findViewById(R.id.nombre);
            //visitas = (TextView) v.findViewById(R.id.visitas);
        }
    }

    public ActividadesAdapter(List<Actividad> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ActividadViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview, viewGroup, false);
        return new ActividadViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ActividadViewHolder viewHolder, int i) {
        viewHolder.imagen.setImageResource(items.get(i).getDeporte());
        viewHolder.nombre.setText("Dia: "+items.get(i).getFecha().toString());
        viewHolder.visitas.setText(("Hora: "+items.get(i).getHora().toString()));
    }
}
