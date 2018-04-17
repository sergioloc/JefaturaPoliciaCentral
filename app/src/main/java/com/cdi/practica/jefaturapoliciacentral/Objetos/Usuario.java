package com.cdi.practica.jefaturapoliciacentral.Objetos;

/**
 * Created by sergi on 17/04/2018.
 */

public class Usuario {

    private String nombre;
    private String apellidos;
    private String dni;
    private String sexo;
    private String telefono;
    private String email;
    private String nacimiento;
    private String nacionalidad;
    private String domicilio;

    public Usuario(){}

    public Usuario(String nombre, String apellidos, String dni, String sexo, String telefono, String email, String nacimiento, String nacionalidad, String domicilio) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.nacimiento = nacimiento;
        this.nacionalidad = nacionalidad;
        this.domicilio = domicilio;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
}

