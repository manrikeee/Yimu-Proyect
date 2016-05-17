package com.example.mk.yimu.Model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mk.yimu.R;

import java.util.List;

public class AdapterRecView extends RecyclerView.Adapter<AdapterRecView.ItemViewHolder>{
    List<String> horas;

    public AdapterRecView(List<String> horas) {
        this.horas = horas;
    }

    @Override
    public AdapterRecView.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ite, viewGroup, false);
        ItemViewHolder pvh = new ItemViewHolder(v);
        return pvh;

    }

    @Override
    public void onBindViewHolder(AdapterRecView.ItemViewHolder holder, int position) {
        holder.time.setText(horas.get(position).toString());

    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    @Override
    public int getItemCount() {
        return horas.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView time;

        ImageView reloj;

        ItemViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.horacita);


        }
    }

}

