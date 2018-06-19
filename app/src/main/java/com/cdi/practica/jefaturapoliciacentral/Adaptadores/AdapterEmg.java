package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cdi.practica.jefaturapoliciacentral.MainActivity;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Agente;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;
import com.cdi.practica.jefaturapoliciacentral.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Sergio on 17/04/2018.
 */

public class AdapterEmg extends RecyclerView.Adapter<AdapterEmg.EmgViewHolder>{

    private ArrayList agentesList;
    private Context context;

    public static class EmgViewHolder extends RecyclerView.ViewHolder {
        ImageView card;
        TextView idUsuario;
        TextView ubicacion;
        TextView hora;

        EmgViewHolder(View itemView) {
            super(itemView);
            card = (ImageView)itemView.findViewById(R.id.iv_emergencia);
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_emergencia, viewGroup, false);
        EmgViewHolder pvh = new EmgViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final EmgViewHolder emgViewHolder, final int i) {
        emgViewHolder.ubicacion.setText(item.get(i).getUbicacion());
        emgViewHolder.idUsuario.setText(item.get(i).getIdUsuario());
        emgViewHolder.hora.setText(item.get(i).getHora());

        context = emgViewHolder.hora.getContext();

        //Dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_lista_agentes);
        Button cancelar = (Button) dialog.findViewById(R.id.cancelar);
        RecyclerView rv = (RecyclerView) dialog.findViewById(R.id.rvAge);
        agentesList = new ArrayList();
        rv.setHasFixedSize(true);
        LinearLayoutManager llm2 = new LinearLayoutManager(context);
        rv.setLayoutManager(llm2);
        int n = getNumeroAleatorio();
        if(n==0){
            cargarAgentes1();
        }else if(n==1){
            cargarAgentes2();
        }else{
            cargarAgentes3();
        }
        AdapterAgeDialog adapterAge = new AdapterAgeDialog(agentesList,dialog);
        rv.setAdapter(adapterAge);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refEmgSel = database.getReference("emergencias").child("seleccionada");

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        emgViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Emergencia e = new Emergencia(item.get(i).getIdUsuario(),item.get(i).getUbicacion(),item.get(i).getHora());
                refEmgSel.setValue(e);
                dialog.show();
            }
        });

    }

    private void cargarAgentes1(){
        agentesList.add(new Agente("dmA60cSLAGOr7dT7FN7U5L32i4w2","Micheal","Scofield"));
        agentesList.add(new Agente("jtNpdTBzKVNbExwYGpILQk6Fsr62","Thomas","Shelby"));
        agentesList.add(new Agente("Ft2E9tHhbmcKgH1eeZRr3voWxZF3","Marty","McFly"));
    }
    private void cargarAgentes2(){
        agentesList.add(new Agente("wDkuHILstCRVW1CDY6JjM1rUXPD3","Jessica","Jones"));
        agentesList.add(new Agente("RFCF1mNkJLTxxOC94mkHSP3a8Ry1","Wade","Wilson"));
    }
    private void cargarAgentes3(){
        agentesList.add(new Agente("2nXdpDoj0jXdNVXtZQAm8JdpiRv1","Mila","Jovovich"));
        agentesList.add(new Agente("jqGT1UV177ONP51d1q5GB6Pzlcj1","John","McClayne"));
        agentesList.add(new Agente("zCZzjtoVZpPoNqzbRU8Cgo6Bo7V2","Lily","James"));
        agentesList.add(new Agente("0trpRI5UkEdxqXHGOtBIM7jRCzK2","Jamie","Foxx"));
    }

    private int getNumeroAleatorio(){
        return (int) (Math.random() * 3);
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}