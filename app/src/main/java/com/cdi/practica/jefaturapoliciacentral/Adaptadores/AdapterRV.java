package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;
import com.cdi.practica.jefaturapoliciacentral.R;

import java.util.ArrayList;

/**
 * Created by Sergio on 17/04/2018.
 */

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.EmgViewHolder>{

    public static class EmgViewHolder extends RecyclerView.ViewHolder {
        TextView tipo;
        TextView lugar;
        TextView hora;

        EmgViewHolder(View itemView) {
            super(itemView);
            tipo = (TextView)itemView.findViewById(R.id.textoTipo);
            lugar = (TextView)itemView.findViewById(R.id.textoLugar);
            hora = (TextView)itemView.findViewById(R.id.textoHora);
        }
    }

    ArrayList<Emergencia> item;

    public AdapterRV(ArrayList<Emergencia> item){
        this.item = item;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public EmgViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_rv, viewGroup, false);
        EmgViewHolder pvh = new EmgViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(EmgViewHolder emgViewHolder, int i) {
        emgViewHolder.tipo.setText(item.get(i).getTipo());
        emgViewHolder.lugar.setText(item.get(i).getLugar());
        emgViewHolder.hora.setText(item.get(i).getHora());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}