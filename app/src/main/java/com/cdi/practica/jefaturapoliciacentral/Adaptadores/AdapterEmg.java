package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.cdi.practica.jefaturapoliciacentral.Objetos.Agente;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;
import com.cdi.practica.jefaturapoliciacentral.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Sergio on 17/04/2018.
 */

public class AdapterEmg extends RecyclerView.Adapter<AdapterEmg.EmgViewHolder>{

    public static class EmgViewHolder extends RecyclerView.ViewHolder {
        TextView idUsuario;
        TextView ubicacion;
        TextView hora;

        EmgViewHolder(View itemView) {
            super(itemView);
            idUsuario = (TextView)itemView.findViewById(R.id.textoTipo);
            ubicacion = (TextView)itemView.findViewById(R.id.textoLugar);
            hora = (TextView)itemView.findViewById(R.id.textoHora);
        }
    }

    ArrayList<Emergencia> item;

    public AdapterEmg(ArrayList<Emergencia> item){
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
    public void onBindViewHolder(final EmgViewHolder emgViewHolder, final int i) {
        emgViewHolder.ubicacion.setText(item.get(i).getUbicacion());
        emgViewHolder.idUsuario.setText(item.get(i).getIdUsuario());
        emgViewHolder.hora.setText(item.get(i).getHora());

        Context context = emgViewHolder.hora.getContext();

        //Dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_lista_agentes);
        Button cancelar = (Button) dialog.findViewById(R.id.cancelar);
        RecyclerView rv = (RecyclerView) dialog.findViewById(R.id.rvAge);
        rv.setBackgroundColor(context.getResources().getColor(R.color.red));
        ArrayList agentesList = new ArrayList();
        rv.setHasFixedSize(true);
        LinearLayoutManager llm2 = new LinearLayoutManager(context);
        rv.setLayoutManager(llm2);
        agentesList.add(new Agente("MAD-00009","Tony","Garcia"));
        agentesList.add(new Agente("MAD-00012","Paco","Ruiz"));
        agentesList.add(new Agente("MAD-00035","Sara","Estuche"));
        AdapterAgeDialog adapterAge = new AdapterAgeDialog(agentesList);
        rv.setAdapter(adapterAge);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refEmgSel = database.getReference("emergencias").child("seleccionada");

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        emgViewHolder.ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Emergencia e = new Emergencia(item.get(i).getIdUsuario(),item.get(i).getUbicacion(),item.get(i).getHora());
                refEmgSel.setValue(e);
                dialog.show();
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}