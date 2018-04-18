package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Usuario;
import com.cdi.practica.jefaturapoliciacentral.R;

import java.util.ArrayList;

/**
 * Created by Sergio on 17/04/2018.
 */

public class AdapterUsu extends RecyclerView.Adapter<AdapterUsu.UsuViewHolder>{

    public static class UsuViewHolder extends RecyclerView.ViewHolder {
        TextView dni;
        TextView nombre;

        UsuViewHolder(View itemView) {
            super(itemView);
            dni = (TextView)itemView.findViewById(R.id.textoId);
            nombre = (TextView)itemView.findViewById(R.id.textoNombre);
        }
    }

    ArrayList<Usuario> item;

    public AdapterUsu(ArrayList<Usuario> item){
        this.item = item;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public UsuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_usuario, viewGroup, false);
        UsuViewHolder pvh = new UsuViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(UsuViewHolder ageViewHolder, int i) {
        ageViewHolder.dni.setText(item.get(i).getDni());
        ageViewHolder.nombre.setText(item.get(i).getApellidos()+", "+item.get(i).getNombre());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}


