package com.umariana.bibliotecaweb;

import java.io.Serializable;

/**
 * Definición de la clase DatosUsuarios que implementa Serializable
 * @author JuanJoseGoyes/ JhonBolanos / GermanAndrade / JosepDavid
 */
 
public class DatosUsuarios implements Serializable {
    
    // Variables de instancia privadas
    private String cedula; // Cédula del usuario
    private String nombre; // Nombre del usuario
    private String contraseña; // Contraseña del usuario

    // Constructor con parámetros para inicializar los campos
    public DatosUsuarios(String cedula, String nombre, String contrasena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contraseña = contrasena;
    }

    // Constructor sin parámetros
    public DatosUsuarios() {
    }

    // Getter para obtener la cédula
    public String getCedula() {
        return cedula;
    }

    // Setter para establecer la cédula
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    // Getter para obtener el nombre
    public String getNombre() {
        return nombre;
    }

    // Setter para establecer el nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para obtener la contraseña
    public String getContrasena() {
        return contraseña;
    }

    // Setter para establecer la contraseña
    public void setContrasena(String contrasena) {
        this.contraseña = contrasena;
    }
}