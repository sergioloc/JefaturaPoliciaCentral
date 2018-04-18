package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdi.practica.jefaturapoliciacentral.Objetos.Denuncia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Usuario;
import com.cdi.practica.jefaturapoliciacentral.R;

import java.util.ArrayList;

/**
 * Created by Sergio on 19/04/2018.
 */

public class AdapterDen extends RecyclerView.Adapter<AdapterDen.DenViewHolder>{

    public static class DenViewHolder extends RecyclerView.ViewHolder {
        TextView tipo;
        TextView dni;
        TextView nombre;

        DenViewHolder(View itemView) {
            super(itemView);
            tipo = (TextView)itemView.findViewById(R.id.textoTipo);
            dni = (TextView)itemView.findViewById(R.id.textoDni);
            nombre = (TextView)itemView.findViewById(R.id.textoNombre);
        }
    }

    ArrayList<Denuncia> item;

    public AdapterDen(ArrayList<Denuncia> item){
        this.item = item;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public DenViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_denuncia, viewGroup, false);
        DenViewHolder pvh = new DenViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DenViewHolder denViewHolder, int i) {
        denViewHolder.tipo.setText(item.get(i).getTipo());
        denViewHolder.dni.setText(item.get(i).getDni());
        denViewHolder.nombre.setText(item.get(i).getApellidos()+", "+item.get(i).getNombre());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}