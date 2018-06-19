package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cdi.practica.jefaturapoliciacentral.MainActivity;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Agente;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Predenuncia;
import com.cdi.practica.jefaturapoliciacentral.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Sergio on 18/04/2018.
 */

public class AdapterAgeDialog extends RecyclerView.Adapter<AdapterAge.AgeViewHolder>{

    private DatabaseReference refEmgPen,refEmgEsp,refEmgSel;
    private Context context;
    private Dialog dialog;

    public static class AgeViewHolder extends RecyclerView.ViewHolder {
        ImageView card;
        TextView id;
        TextView nombre;

        AgeViewHolder(View itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.textoId);
            nombre = (TextView)itemView.findViewById(R.id.textoNombre);
            card = (ImageView) itemView.findViewById(R.id.card_agente);
        }
    }

    ArrayList<Agente> item;

    public AdapterAgeDialog(ArrayList<Agente> item, Dialog dialog){
        this.item = item;
        this.dialog = dialog;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public AdapterAge.AgeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_agente, viewGroup, false);
        AdapterAge.AgeViewHolder pvh = new AdapterAge.AgeViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AdapterAge.AgeViewHolder ageViewHolder, final int i) {
        ageViewHolder.id.setText(item.get(i).getId());
        ageViewHolder.nombre.setText(item.get(i).getApellidos()+", "+item.get(i).getNombre());

        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refEmgPen = database.getReference("emergencias").child("pendientes");
        refEmgEsp = database.getReference("emergencias").child("espera");
        refEmgSel = database.getReference("emergencias").child("seleccionada");


        ageViewHolder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                refEmgSel.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Emergencia e;
                        String hora = dataSnapshot.child("hora").getValue(String.class);
                        String idUsuario = dataSnapshot.child("idUsuario").getValue(String.class);
                        String ubicacion = dataSnapshot.child("ubicacion").getValue(String.class);
                        e = new Emergencia(idUsuario,ubicacion,hora);
                        refEmgEsp.child(item.get(i).getId()).push().setValue(e);
                        borrarEmergenciaPendiente(e);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                dialog.dismiss();
                context=view.getContext();
                Toast.makeText(view.getContext(),"Predenuncia asignada a "+item.get(i).getApellidos()+", "+item.get(i).getNombre(),Toast.LENGTH_LONG).show();

            }
        });

    }

    private void borrarEmergenciaPendiente(final Emergencia e){
        refEmgPen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Emergencia emergencia = snapshot.getValue(Emergencia.class);
                    if(e.getHora().equals(emergencia.getHora())&&e.getIdUsuario().equals(emergencia.getIdUsuario())&&e.getUbicacion().equals(emergencia.getUbicacion())){
                        refEmgPen.child(snapshot.getKey()).removeValue();
                        //Toast.makeText(context,snapshot.getKey(),Toast.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

