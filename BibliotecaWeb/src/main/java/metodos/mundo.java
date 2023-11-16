/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodos;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;


// Clase que contiene métodos relacionados con la autenticación de usuarios
public class mundo {
    
    // Método para verificar si un usuario con la cédula dada ya está registrado
    public static boolean usuarioIngresar(int cedula, ArrayList<DatosUsuarios> UsuariosRegistrados) throws IOException {
        for (DatosUsuarios usuario : UsuariosRegistrados) {
            if (cedula == usuario.getCedula()) {
                return false; // El usuario con la cédula especificada ya está registrado
            }
        }
        return true; // El usuario con la cédula especificada no está registrado
    }
    
    // Método para verificar el ingreso de un usuario por cédula y contraseña
    public static String ingresar(int cedula, String contrasena, ArrayList<DatosUsuarios> UsuariosRegistrados) throws IOException {
        for (DatosUsuarios usuario : UsuariosRegistrados) {
            if (cedula == usuario.getCedula() && usuario.getContrasena().equals(contrasena)) {
                return usuario.getNombre(); // Las credenciales son correctas, retorna el nombre del usuario
            }
        }
        return "no"; // Las credenciales son incorrectas o el usuario no existe
    }
}
