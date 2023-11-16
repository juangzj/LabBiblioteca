/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umariana.bibliotecaweb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletContext;
/**
 * Clase Gestion
 * @author JuanJoseGoyes/ JhonBolanos / GermanAndrade / JosepDavid
 */
public class Gestion {
    
    // Método para verificar si un usuario puede ingresar
    public static boolean ingresar(String cedula, String contrasena, ArrayList<DatosUsuarios> UsuariosRegistrados) throws IOException {
        for (DatosUsuarios usuario : UsuariosRegistrados) {
            if (cedula.equals(usuario.getCedula()) && usuario.getContrasena().equals(contrasena)) {
                return true; // Retorna true si la cédula y la contraseña coinciden
            }
        }
        return false; // Retorna false si no se encuentra una coincidencia
    }

    // Método para obtener el nombre de un usuario a partir de su cédula
    public static String darNombre(String cedula, ArrayList<DatosUsuarios> UsuariosRegistrados) throws IOException {
        for (DatosUsuarios usuario : UsuariosRegistrados) {
            if (cedula.equals(usuario.getCedula())) {
                return usuario.getNombre(); // Retorna el nombre si se encuentra la cédula
            }
        }
        return null; // Retorna null si no se encuentra la cédula en la lista de usuarios
    }
    
    /**
     * SERIALIZAR USUARIOS
     */

    // Método para serializar un ArrayList de usuarios
    public static void serializacion(ArrayList<DatosUsuarios> UsuariosRegistrados, ServletContext context)
            throws FileNotFoundException, IOException {
        String path = "/data/UsuariosRegistrados.ser";
        String Rpath = context.getRealPath(path);
        System.out.println("Ruta: " + Rpath);
        File file = new File(Rpath);
        try (ObjectOutputStream escribirFichero = new ObjectOutputStream(new FileOutputStream(file))) {
            escribirFichero.writeObject(UsuariosRegistrados);
            escribirFichero.close();
            System.out.println("Archivo escrito");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo");
        }
    }

    // Método para deserializar un ArrayList de usuarios
    public static ArrayList<DatosUsuarios> deserializacion(ServletContext context)
            throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<DatosUsuarios> UsuariosRegistrados = new ArrayList<>();
        String path = "/data/UsuariosRegistrados.ser";
        String Rpath = context.getRealPath(path);
        File archivo = new File(Rpath);
        try (ObjectInputStream leerFichero = new ObjectInputStream(new FileInputStream(archivo))) {
            UsuariosRegistrados = (ArrayList<DatosUsuarios>) leerFichero.readObject();
            System.out.println("Leído exitosamente");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no fue encontrado");
        } catch (IOException e) {
            System.out.println("No se leyó exitosamente");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró la clase");
        }
        return UsuariosRegistrados;
    }
}