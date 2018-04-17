package com.cdi.practica.jefaturapoliciacentral.Objetos;

/**
 * Created by Sergio on 17/04/2018.
 */

public class Emergencia {
    private String idUsuario;
    private String ubicacion;
    private String hora;

    public Emergencia() {
    }

    public Emergencia(String idUsuario, String ubicacion, String hora) {
        this.idUsuario = idUsuario;
        this.ubicacion = ubicacion;
        this.hora = hora;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


}
