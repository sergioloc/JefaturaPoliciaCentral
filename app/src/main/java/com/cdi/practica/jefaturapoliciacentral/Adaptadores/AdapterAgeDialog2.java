package com.cdi.practica.jefaturapoliciacentral.Adaptadores;

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

public class AdapterAgeDialog2 extends RecyclerView.Adapter<AdapterAge.AgeViewHolder>{

    private DatabaseReference refPrePen,refPreEsp,refPreSel;
    private Context context;

    public static class AgeViewHolder2 extends RecyclerView.ViewHolder {
        ImageView card;
        TextView id;
        TextView nombre;

        AgeViewHolder2(View itemView) {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.textoId);
            nombre = (TextView)itemView.findViewById(R.id.textoNombre);
            card = (ImageView) itemView.findViewById(R.id.card_agente);
        }
    }

    ArrayList<Agente> item;

    public AdapterAgeDialog2(ArrayList<Agente> item){
        this.item = item;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public AdapterAge.AgeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_agente_dialog, viewGroup, false);
        AdapterAge.AgeViewHolder pvh = new AdapterAge.AgeViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(AdapterAge.AgeViewHolder ageViewHolder, final int i) {
        ageViewHolder.id.setText(item.get(i).getId());
        ageViewHolder.nombre.setText(item.get(i).getApellidos()+", "+item.get(i).getNombre());

        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        refPrePen = database.getReference("predenuncias").child("pendientes");
        refPreEsp = database.getReference("predenuncias").child("espera");
        refPreSel = database.getReference("predenuncias").child("seleccionada");


        ageViewHolder.nombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                refPreSel.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Predenuncia p;
                        String tipo = dataSnapshot.child("tipo").getValue(String.class);
                        String nombre = dataSnapshot.child("nombre").getValue(String.class);
                        String apellidos = dataSnapshot.child("apellidos").getValue(String.class);
                        String dni = dataSnapshot.child("dni").getValue(String.class);
                        String ubicacion = dataSnapshot.child("ubicacion").getValue(String.class);
                        String hora = dataSnapshot.child("hora").getValue(String.class);
                        p = new Predenuncia(tipo,nombre,apellidos,dni,ubicacion,hora);
                        refPreEsp.child(item.get(i).getId()).push().setValue(p);
                        borrarPredenunciaPendiente(p);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
                context=view.getContext();
                Toast.makeText(view.getContext(),"Predenuncia asignada a "+item.get(i).getApellidos()+", "+item.get(i).getNombre(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void borrarPredenunciaPendiente(final Predenuncia p){
        refPrePen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Predenuncia pre = snapshot.getValue(Predenuncia.class);
                    if(pre.getTipo().equals(p.getTipo()) && pre.getNombre().equals(p.getNombre()) && pre.getApellidos().equals(p.getApellidos()) &&
                            pre.getDni().equals(p.getDni()) && pre.getUbicacion().equals(p.getUbicacion()) && pre.getHora().equals(p.getHora())){
                        refPrePen.child(snapshot.getKey()).removeValue();
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

