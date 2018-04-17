package com.cdi.practica.jefaturapoliciacentral.Objetos;

/**
 * Created by Sergio on 17/04/2018.
 */

public class Emergencia {
    private String tipo;
    private String lugar;
    private String hora;

    public Emergencia() {
    }

    public Emergencia(String tipo, String lugar, String hora) {
        this.tipo = tipo;
        this.lugar = lugar;
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
