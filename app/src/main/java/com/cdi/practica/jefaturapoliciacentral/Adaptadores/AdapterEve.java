package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdi.practica.jefaturapoliciacentral.Objetos.Denuncia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Evento;
import com.cdi.practica.jefaturapoliciacentral.R;

import java.util.ArrayList;

/**
 * Created by Sergio López on 19/06/2018.
 */

public class AdapterEve extends RecyclerView.Adapter<AdapterEve.EveViewHolder>{

    public static class EveViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        TextView ubicacion;
        TextView numAgentes;

        EveViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.textoNombreEvento);
            ubicacion = (TextView)itemView.findViewById(R.id.textoUbicacion);
            numAgentes = (TextView)itemView.findViewById(R.id.textoNumAgentes);
        }
    }

    ArrayList<Evento> item;

    public AdapterEve(ArrayList<Evento> item){
        this.item = item;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public EveViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_evento, viewGroup, false);
        EveViewHolder evh = new EveViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(EveViewHolder eveViewHolder, int i) {
        eveViewHolder.nombre.setText(item.get(i).getNombre());
        eveViewHolder.ubicacion.setText("Ubicación: "+item.get(i).getUbicacion());
        eveViewHolder.numAgentes.setText("Nº agentes: "+item.get(i).getNumAgentes());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}