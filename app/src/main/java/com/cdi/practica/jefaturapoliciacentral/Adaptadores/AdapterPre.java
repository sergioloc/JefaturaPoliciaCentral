package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Predenuncia;
import com.cdi.practica.jefaturapoliciacentral.R;

import java.util.ArrayList;

/**
 * Created by Sergio on 17/04/2018.
 */

public class AdapterPre extends RecyclerView.Adapter<AdapterPre.PreViewHolder>{

    public static class PreViewHolder extends RecyclerView.ViewHolder {
        TextView tipo;
        TextView unicacion;
        TextView hora;

        PreViewHolder(View itemView) {
            super(itemView);
            tipo = (TextView)itemView.findViewById(R.id.textoTipo);
            unicacion = (TextView)itemView.findViewById(R.id.textoLugar);
            hora = (TextView)itemView.findViewById(R.id.textoHora);
        }
    }

    ArrayList<Predenuncia> item;

    public AdapterPre(ArrayList<Predenuncia> item){
        this.item = item;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public PreViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_rv, viewGroup, false);
        PreViewHolder pvh = new PreViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PreViewHolder preViewHolder, int i) {
        preViewHolder.tipo.setText(item.get(i).getTipo());
        preViewHolder.unicacion.setText(item.get(i).getUbicacion());
        preViewHolder.hora.setText(item.get(i).getHora());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
