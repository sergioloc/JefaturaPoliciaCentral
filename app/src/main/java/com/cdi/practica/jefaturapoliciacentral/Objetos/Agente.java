package com.cdi.practica.jefaturapoliciacentral.Objetos;

/**
 * Created by Sergio on 17/04/2018.
 */

public class Agente {
    private String id;
    private String nombre;
    private String apellidos;

    public Agente() {
    }

    public Agente(String id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
