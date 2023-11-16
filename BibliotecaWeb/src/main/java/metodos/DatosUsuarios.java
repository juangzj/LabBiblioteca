package metodos;

import java.io.Serializable;


// Clase que representa los datos de usuarios
public class DatosUsuarios implements Serializable {
    
    // Atributos de la clase
    private int cedula;         // Número de cédula del usuario
    private String nombre;      // Nombre del usuario
    private String contrasena;  // Contraseña del usuario

    // Constructor con parámetros
    public DatosUsuarios(int cedula, String nombre, String contrasena) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    // Constructor sin parámetros
    public DatosUsuarios() {
    }

    // Métodos getter y setter para acceder a los atributos de la clase

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}