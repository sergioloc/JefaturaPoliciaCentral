package com.cdi.practica.jefaturapoliciacentral;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterAge;
import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterDen;
import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterEve;
import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterPre;
import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterEmg;
import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterUsu;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Agente;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Denuncia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Evento;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Predenuncia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager,viewPager2;
    private View view;
    //Firebase
    private FirebaseDatabase database;
    private FirebaseUser user;
    private DatabaseReference refPre, refUsu, refAge, refEmg, refDen;
    private ArrayList pre1,pre2,pre3,pre4,pre5,agentesList,usuariosList,emergenciasList,denunciasList,eventosList;
    private RecyclerView rvPre, rvAge, rvUsu, rvDen, rvEve;
    private AdapterPre adapterPre;
    private AdapterAge adapterAge;
    private AdapterUsu adapterUsu;
    private AdapterEmg adapterEmg;
    private AdapterDen adapterDen;
    private AdapterEve adapterEve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFirebase();
        initViewPager();
        initMenu();
    }

    /**Inits**/
    private void initFirebase(){
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        refPre = database.getReference("predenuncias").child("pendientes");
        refUsu = database.getReference("usuarios");
        refAge = database.getReference("agentes");
        refEmg = database.getReference("emergencias").child("pendientes");
        refDen = database.getReference("denuncias");
    }
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.vp_vertical_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 6; //Numero de paginas
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {


                if(position==0){ //Emergencias
                    viewEmgergencias();
                }else if(position==1){ //Predenuncias
                    viewPredenuncias();
                }else if(position==2){ //Denuncias
                    viewDenuncias();
                }else if(position==3){ //Agentes
                    viewAgentes();
                }else if(position==4){ //Usuarios
                    viewUsuarios();
                }else if(position==5){ //Eventos
                    viewEventos();
                }


                container.addView(view);
                return view;
            }
        });
    }
    private void initMenu(){
        final String[] colors = getResources().getStringArray(R.array.vertical_ntb);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_vertical);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_emergencia),
                        Color.parseColor(colors[0])
                ).title("Emergencia")
                        .badgeTitle("1")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_predenuncia),
                        Color.parseColor(colors[1])
                ).title("Predenuncia")
                        .badgeTitle("2")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_denuncia),
                        Color.parseColor(colors[2])
                ).title("Denuncia")
                        .badgeTitle("3")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_police),
                        Color.parseColor(colors[3])
                ).title("Agentes")
                        .badgeTitle("4")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_user),
                        Color.parseColor(colors[4])
                ).title("Usuarios")
                        .badgeTitle("5")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_event),
                        Color.parseColor(colors[5])
                ).title("Eventos")
                        .badgeTitle("6")
                        .build()
        );
        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 2);

        navigationTabBar.setTitleMode(NavigationTabBar.TitleMode.ACTIVE);
        navigationTabBar.setBadgeGravity(NavigationTabBar.BadgeGravity.BOTTOM);
        navigationTabBar.setBadgePosition(NavigationTabBar.BadgePosition.CENTER);
        navigationTabBar.setIsBadged(true);
        navigationTabBar.setIsTitled(true);
        navigationTabBar.setIsTinted(true);
        navigationTabBar.setIsBadgeUseTypeface(true);
        navigationTabBar.setBadgeBgColor(Color.RED);
        navigationTabBar.setBadgeTitleColor(Color.WHITE);
        navigationTabBar.setIsSwiped(true);
        navigationTabBar.setBgColor(Color.BLACK);
        navigationTabBar.setBadgeSize(10);
        navigationTabBar.setTitleSize(10);
        navigationTabBar.setIconSizeFraction((float) 0.5);

    }

    /**Views**/
    private void viewEmgergencias(){
        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_emegencias, null, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rvEmg);
        emergenciasList = new ArrayList();
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        adapterEmg = new AdapterEmg(emergenciasList);
        rv.setAdapter(adapterEmg);
        cargarEmergencias();
    }
    private void viewPredenuncias(){
        // View
        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_predenuncias, null, false);
        final TextView textoPre = (TextView) view.findViewById(R.id.textoPre);
        // Tabs
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("1"));
        tabs.addTab(tabs.newTab().setText("2"));
        tabs.addTab(tabs.newTab().setText("3"));
        tabs.addTab(tabs.newTab().setText("4"));
        tabs.addTab(tabs.newTab().setText("5"));
        // RecyclerView
        rvPre = (RecyclerView) view.findViewById(R.id.rvPre);
        rvPre.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvPre.setLayoutManager(llm);
        // ArrayList
        pre1 = new ArrayList();
        pre2 = new ArrayList();
        pre3 = new ArrayList();
        pre4 = new ArrayList();
        pre5 = new ArrayList();

        textoPre.setText("Predenuncias nivel 1");
        adapterPre = new AdapterPre(pre1);
        rvPre.setAdapter(adapterPre);

        tabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int pos = tab.getPosition();
                        if(pos==0){
                            textoPre.setText("Predenuncias nivel 1");
                            adapterPre = new AdapterPre(pre1);
                        }else if (pos==1){
                            textoPre.setText("Predenuncias nivel 2");
                             adapterPre = new AdapterPre(pre2);
                        }else if (pos==2){
                            textoPre.setText("Predenuncias nivel 3");
                            adapterPre = new AdapterPre(pre3);
                        }else if (pos==3){
                            textoPre.setText("Predenuncias nivel 4");
                            adapterPre = new AdapterPre(pre4);
                        }else if (pos==4){
                            textoPre.setText("Predenuncias nivel 5");
                            adapterPre = new AdapterPre(pre5);
                        }
                        rvPre.setAdapter(adapterPre);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}
                }
        );
        cargarPredenuncias();
    }
    private void viewDenuncias(){
        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_denuncias, null, false);
        denunciasList = new ArrayList();
        rvDen = (RecyclerView) view.findViewById(R.id.rvDen);
        rvDen.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvDen.setLayoutManager(llm);
        adapterDen = new AdapterDen(denunciasList);
        rvDen.setAdapter(adapterDen);
        cargarDenuncias();
    }
    private void viewAgentes(){
        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_agentes, null, false);
        agentesList = new ArrayList();
        rvAge = (RecyclerView) view.findViewById(R.id.rvAge);
        rvAge.setHasFixedSize(true);
        LinearLayoutManager llm2 = new LinearLayoutManager(getApplicationContext());
        rvAge.setLayoutManager(llm2);
        adapterAge = new AdapterAge(agentesList);
        rvAge.setAdapter(adapterAge);
        cargarAgentes();
    }
    private void viewUsuarios(){
        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_usuarios, null, false);
        usuariosList = new ArrayList();
        rvUsu = (RecyclerView) view.findViewById(R.id.rvUsu);
        rvUsu.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvUsu.setLayoutManager(llm);
        adapterUsu = new AdapterUsu(usuariosList);
        rvUsu.setAdapter(adapterUsu);
        cargarUsuarios();
    }
    private void viewEventos(){
        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_eventos, null, false);
        eventosList = new ArrayList();
        //Init
        eventosList.add(new Evento("Feria del libro","c/ Alcala 10",50));
        eventosList.add(new Evento("Fiesta","c/ Serrano 1",500));
        rvEve = (RecyclerView) view.findViewById(R.id.rvEve);
        rvEve.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvEve.setLayoutManager(llm);
        adapterEve = new AdapterEve(eventosList);
        rvEve.setAdapter(adapterEve);
    }

    /**BBDD**/
    private void cargarPredenuncias(){
        refPre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pre1.removeAll(pre1);
                pre2.removeAll(pre2);
                pre3.removeAll(pre3);
                pre4.removeAll(pre4);
                pre5.removeAll(pre5);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Predenuncia predenuncia = snapshot.getValue(Predenuncia.class);
                    if(predenuncia.getTipo().equals("Agresión")||predenuncia.getTipo().equals("Homicidio")){
                        pre1.add(predenuncia);
                    }else if(predenuncia.getTipo().equals("Abuso")||predenuncia.getTipo().equals("Robo")||predenuncia.getTipo().equals("Acoso")||predenuncia.getTipo().equals("Amenaza")){
                        pre2.add(predenuncia);
                    }else if(predenuncia.getTipo().equals("Hurto")||predenuncia.getTipo().equals("Lesión")){
                        pre3.add(predenuncia);
                    }else if(predenuncia.getTipo().equals("Fraude")||predenuncia.getTipo().equals("Estafa")){
                        pre4.add(predenuncia);
                    }else if(predenuncia.getTipo().equals("Difamación")||predenuncia.getTipo().equals("Injuria")){
                        pre5.add(predenuncia);
                    }
                }
                adapterPre.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void cargarAgentes(){
        refAge.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                agentesList.removeAll(agentesList);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Agente agente = snapshot.getValue(Agente.class);
                    agentesList.add(agente);
                }
                adapterAge.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void cargarUsuarios(){
        refUsu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuariosList.removeAll(usuariosList);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Usuario usuario = snapshot.child("info").getValue(Usuario.class);
                    usuariosList.add(usuario);
                }
                adapterUsu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void cargarEmergencias(){
        refEmg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                emergenciasList.removeAll(emergenciasList);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Emergencia emergencia = snapshot.getValue(Emergencia.class);
                    emergenciasList.add(emergencia);
                }
                adapterEmg.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void cargarDenuncias(){
        refDen.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                denunciasList.removeAll(denunciasList);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Denuncia denuncia = snapshot.getValue(Denuncia.class);
                    denunciasList.add(denuncia);
                }
                adapterDen.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
