package com.cdi.practica.jefaturapoliciacentral.Objetos;

/**
 * Created by Sergio López on 19/06/2018.
 */

public class Evento {

    private String nombre;
    private String ubicacion;
    private int numAgentes;

    public Evento(){}

    public Evento(String nombre, String ubicacion, int numAgentes) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.numAgentes = numAgentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getNumAgentes() {
        return numAgentes;
    }

    public void setNumAgentes(int numAgentes) {
        this.numAgentes = numAgentes;
    }
}
