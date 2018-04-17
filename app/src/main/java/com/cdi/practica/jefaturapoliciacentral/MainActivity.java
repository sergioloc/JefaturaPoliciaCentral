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

import com.cdi.practica.jefaturapoliciacentral.Adaptadores.AdapterRV;
import com.cdi.practica.jefaturapoliciacentral.Objetos.Emergencia;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager,viewPager2;
    private View view,view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVP();
        init();
    }

    private void initVP() {
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
                    initEmg();
                }else if(position==1){ //Predenuncias
                    initPre();
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

    private void initEmg(){
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

    private void initPre(){

        view = LayoutInflater.from(getBaseContext()).inflate(R.layout.vp_predenuncias, null, false);
        final TextView textoPre = (TextView) view.findViewById(R.id.textoPre);

        //Tabs
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("1"));
        tabs.addTab(tabs.newTab().setText("2"));
        tabs.addTab(tabs.newTab().setText("3"));

        //RecyclerView
        final RecyclerView rvPre = (RecyclerView) view.findViewById(R.id.rvPre);
        rvPre.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvPre.setLayoutManager(llm);

        //Listas de pruebas
        final ArrayList pre1 = new ArrayList();
        final ArrayList pre2 = new ArrayList();
        final ArrayList pre3 = new ArrayList();
        pre1.add(new Emergencia("Amenaza","c/ azul","12:15"));
        pre1.add(new Emergencia("Homicidio","c/ verde","07:00"));
        pre2.add(new Emergencia("Hurto","c/ morado","14:45"));
        pre3.add(new Emergencia("Estafa","c/ blanco","06:05"));

        textoPre.setText("Predenuncias nivel 1");
        AdapterRV adapterPre = new AdapterRV(pre1);
        rvPre.setAdapter(adapterPre);


        tabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        AdapterRV adapterPre = null;
                        int pos = tab.getPosition();
                        if(pos==0){
                            textoPre.setText("Predenuncias nivel 1");
                            adapterPre = new AdapterRV(pre1);
                        }else if (pos==1){
                            textoPre.setText("Predenuncias nivel 2");
                             adapterPre = new AdapterRV(pre2);
                        }else if (pos==2){
                            textoPre.setText("Predenuncias nivel 3");
                            adapterPre = new AdapterRV(pre3);
                        }
                        rvPre.setAdapter(adapterPre);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        // ...
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        // ...
                    }
                }
        );
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
}
