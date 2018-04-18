package com.cdi.practica.jefaturapoliciacentral.Objetos;

/**
 * Created by Sergio on 17/04/2018.
 */

public class Predenuncia {

    private String tipo;
    private String ubicacion;
    private String hora;
    private String nombre;
    private String apellidos;
    private String dni;

    public Predenuncia() {
    }

    public Predenuncia(String tipo, String nombre, String apellidos, String dni, String ubicacion, String hora) {
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.hora = hora;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}
