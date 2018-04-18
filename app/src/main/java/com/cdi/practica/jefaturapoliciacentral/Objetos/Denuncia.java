package com.cdi.practica.jefaturapoliciacentral.Objetos;

/**
 * Created by Sergio on 18/04/2018.
 */

public class Denuncia {
    private String nombre;
    private String apellidos;
    private String dni;
    private String sexo;
    private String telefono;
    private String email;
    private String nacimiento;
    private String nacionalidad;
    private String domicilio;
    private String tipo;
    private String ubicacion;
    private String hora;
    private String descipcion;

    public Denuncia() {
    }

    public Denuncia(String nombre, String apellidos, String dni, String sexo, String telefono,
                    String email, String nacimiento, String nacionalidad, String domicilio,
                    String tipo, String ubicacion, String hora, String descipcion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.sexo = sexo;
        this.telefono = telefono;
        this.email = email;
        this.nacimiento = nacimiento;
        this.nacionalidad = nacionalidad;
        this.domicilio = domicilio;
        this.tipo = tipo;
        this.ubicacion = ubicacion;
        this.hora = hora;
        this.descipcion = descipcion;
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

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }
}
