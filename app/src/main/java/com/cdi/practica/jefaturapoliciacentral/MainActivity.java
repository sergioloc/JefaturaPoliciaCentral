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
import android.widget.Toast;

import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterPre;
import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterRV;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Predenuncia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager,viewPager2;
    private View view;
    //Firebase
    private FirebaseDatabase database;
    private FirebaseUser user;
    private DatabaseReference refPre, refUsu;
    private ArrayList pre1, pre2, pre3;
    private RecyclerView rvPre;
    private AdapterPre adapterPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFirebase();
        initViewPager();
        init();
        cargarPredenuncias();
    }

    private void initFirebase(){
        database = FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        refPre = database.getReference("predenuncias").child("nueva");
        refUsu = database.getReference("users");
    }
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.vp_vertical_ntb);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 5; //Numero de paginas
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
                    viewEmg();
                }else if(position==1){ //Predenuncias
                    viewPre();
                }else if(position==2){ //Denuncias
                    view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_denuncias, null, false);
                }else if(position==3){ //Agentes
                    view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_agentes, null, false);
                }else if(position==4){ //Usuarios
                    view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_usuarios, null, false);
                }

                container.addView(view);
                return view;
            }
        });
    }
    private void init(){
        final String[] colors = getResources().getStringArray(R.array.vertical_ntb);
        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_vertical);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_emergencia),
                        Color.parseColor(colors[0])
                ).title("Heart")
                        .badgeTitle("NTB")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_predenuncia),
                        Color.parseColor(colors[1])
                ).title("Cup")
                        .badgeTitle("with")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_denuncia),
                        Color.parseColor(colors[2])
                ).title("Diploma")
                        .badgeTitle("state")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.ic_police),
                        Color.parseColor(colors[3])
                ).title("Flag")
                        .badgeTitle("icon")
                        .build()
        );
        models.add(
                new NavigationTabBar.Model.Builder(
                        getResources().getDrawable(R.drawable.icon_user),
                        Color.parseColor(colors[4])
                ).title("Medal")
                        .badgeTitle("777")
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


    private void viewEmg(){
        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_emegencias, null, false);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rvEmg);
        ArrayList emg = new ArrayList();
        emg.add(new Emergencia("Atentado","Manazanares","22:45"));
        emg.add(new Emergencia("Violación","Sol","16:00"));
        emg.add(new Emergencia("Agresión","Tetuan","02:05"));
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        AdapterRV adapterRV = new AdapterRV(emg);
        rv.setAdapter(adapterRV);
    }

    private void viewPre(){
        // View
        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_predenuncias, null, false);
        final TextView textoPre = (TextView) view.findViewById(R.id.textoPre);
        // Tabs
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("1"));
        tabs.addTab(tabs.newTab().setText("2"));
        tabs.addTab(tabs.newTab().setText("3"));
        // RecyclerView
        rvPre = (RecyclerView) view.findViewById(R.id.rvPre);
        rvPre.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvPre.setLayoutManager(llm);
        // ArrayList
        pre1 = new ArrayList();
        pre2 = new ArrayList();
        pre3 = new ArrayList();

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
                        }
                        rvPre.setAdapter(adapterPre);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {}

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {}
                }
        );
    }

    private void cargarPredenuncias(){
        refPre.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Predenuncia predenuncia = snapshot.getValue(Predenuncia.class);
                    if(predenuncia.getTipo().equals("Agresión")||predenuncia.getTipo().equals("Abuso")||predenuncia.getTipo().equals("Homicidio")||predenuncia.getTipo().equals("Amenaza")){
                        pre1.add(predenuncia);
                    }else if(predenuncia.getTipo().equals("Hurto")||predenuncia.getTipo().equals("Robo")||predenuncia.getTipo().equals("Lesión")||predenuncia.getTipo().equals("Acoso")){
                        pre2.add(predenuncia);
                    }else if(predenuncia.getTipo().equals("Fraude")||predenuncia.getTipo().equals("Estafa")||predenuncia.getTipo().equals("Difamación")||predenuncia.getTipo().equals("Injuria")){
                        pre3.add(predenuncia);
                    }
                }
                adapterPre.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
