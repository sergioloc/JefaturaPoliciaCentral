package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
 * Created by sergi on 18/04/2018.
 */

public class AdapterAgeDialog extends RecyclerView.Adapter<AdapterAge.AgeViewHolder>{

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

    public AdapterAgeDialog(ArrayList<Agente> item){
        this.item = item;
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
        final DatabaseReference refEmgPen = database.getReference("emergencias").child("pendientes");
        final DatabaseReference refEmgAct = database.getReference("emergencias").child("activas");
        final DatabaseReference refEmgSel = database.getReference("emergencias").child("seleccionada");


        ageViewHolder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refEmgSel.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Emergencia e = null;
                        String hora = dataSnapshot.child("hora").getValue(String.class);
                        String idUsuario = dataSnapshot.child("idUsuario").getValue(String.class);
                        String ubicacion = dataSnapshot.child("ubicacion").getValue(String.class);
                        e = new Emergencia(idUsuario,ubicacion,hora);
                        refEmgAct.child(item.get(i).getId()).setValue(e);

                        //refEmgSel.removeValue();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                Toast.makeText(view.getContext(),"hey",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

