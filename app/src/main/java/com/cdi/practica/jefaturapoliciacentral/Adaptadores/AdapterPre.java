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

import com.cdi.practica.jefaturapoliciacentral.Objetos.Agente;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Predenuncia;
import com.cdi.practica.jefaturapoliciacentral.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Sergio on 17/04/2018.
 */

public class AdapterPre extends RecyclerView.Adapter<AdapterPre.PreViewHolder>{

    private ArrayList agentesList;
    private Context context;

    public static class PreViewHolder extends RecyclerView.ViewHolder {
        ImageView card;
        TextView tipo;
        TextView ubicacion;
        TextView hora;

        PreViewHolder(View itemView) {
            super(itemView);
            card = (ImageView)itemView.findViewById(R.id.iv_predenuncia);
            tipo = (TextView)itemView.findViewById(R.id.textoTipo);
            ubicacion = (TextView)itemView.findViewById(R.id.textoLugar);
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_predenuncia, viewGroup, false);
        PreViewHolder pvh = new PreViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PreViewHolder preViewHolder, final int i) {
        preViewHolder.tipo.setText(item.get(i).getTipo());
        preViewHolder.ubicacion.setText(item.get(i).getUbicacion());
        preViewHolder.hora.setText(item.get(i).getHora());

        context = preViewHolder.hora.getContext();

        //Dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_lista_agentes);
        Button cancelar = (Button) dialog.findViewById(R.id.cancelar);
        RecyclerView rv = (RecyclerView) dialog.findViewById(R.id.rvAge);
        agentesList = new ArrayList();
        rv.setHasFixedSize(true);
        LinearLayoutManager llm2 = new LinearLayoutManager(context);
        rv.setLayoutManager(llm2);
        cargarAgentes();
        AdapterAgeDialog2 adapterAge2 = new AdapterAgeDialog2(agentesList,dialog);
        rv.setAdapter(adapterAge2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference refPreSel = database.getReference("predenuncias").child("seleccionada");

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        preViewHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Predenuncia e = new Predenuncia(item.get(i).getTipo(),item.get(i).getNombre(),item.get(i).getApellidos(),item.get(i).getDni(),item.get(i).getUbicacion(),item.get(i).getHora());
                refPreSel.setValue(e);
                dialog.show();
            }
        });
    }

    private void cargarAgentes(){
        //agentesList.add(new Agente("dmA60cSLAGOr7dT7FN7U5L32i4w2","Micheal","Scofield"));
        agentesList.add(new Agente("jtNpdTBzKVNbExwYGpILQk6Fsr62","Thomas","Shelby"));
        agentesList.add(new Agente("Ft2E9tHhbmcKgH1eeZRr3voWxZF3","Marty","McFly"));
        agentesList.add(new Agente("wDkuHILstCRVW1CDY6JjM1rUXPD3","Jessica","Jones"));


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
