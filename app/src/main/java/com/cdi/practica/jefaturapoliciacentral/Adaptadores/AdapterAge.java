package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdi.practica.jefaturapoliciacentral.Objetos.Agente;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Predenuncia;
import com.cdi.practica.jefaturapoliciacentral.R;

import java.util.ArrayList;

/**
 * Created by Sergio on 17/04/2018.
 */

public class AdapterAge extends RecyclerView.Adapter<AdapterAge.AgeViewHolder>{

    public static class AgeViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView nombre;

        AgeViewHolder(View itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.textoId);
            nombre = (TextView)itemView.findViewById(R.id.textoNombre);
        }
    }

    ArrayList<Agente> item;

    public AdapterAge(ArrayList<Agente> item){
        this.item = item;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public AgeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_agente, viewGroup, false);
        AgeViewHolder pvh = new AgeViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AgeViewHolder ageViewHolder, int i) {
        ageViewHolder.id.setText(item.get(i).getId());
        ageViewHolder.nombre.setText(item.get(i).getApellidos()+", "+item.get(i).getNombre());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

