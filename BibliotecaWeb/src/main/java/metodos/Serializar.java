package metodos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.servlet.ServletContext;

// Clase que proporciona métodos para la serialización y deserialización de objetos
public class Serializar {
    
    // Método para serializar una lista de usuarios registrados y guardarla en un archivo
    public static void serializacion(ArrayList<DatosUsuarios> UsuariosRegistrados, ServletContext context) throws FileNotFoundException, IOException {
        // Definir la ruta del archivo de serialización
        String path = "/data/UsuariosRegistrados.ser";        
        String Rpath = context.getRealPath(path);
        System.out.println("Ruta: " + Rpath);
        
        File file = new File(Rpath);
        
        try (ObjectOutputStream escribirFichero = new ObjectOutputStream(new FileOutputStream(file))) {
            // Serializa y escribe la lista de usuarios en el archivo
            escribirFichero.writeObject(UsuariosRegistrados);
            escribirFichero.close();
            System.out.println("Archivo escrito");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println("No se pudo escribir el archivo");
        }
    }
    
    // Método para deserializar una lista de usuarios registrados desde un archivo
    public static ArrayList<DatosUsuarios> deserializacion(ServletContext context) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<DatosUsuarios> UsuariosRegistrados = new ArrayList<>();
        
        // Definir la ruta del archivo de deserialización
        String path = "/data/UsuariosRegistrados.ser";        
        String Rpath = context.getRealPath(path);
        File archivo = new File(Rpath);
        
        try (ObjectInputStream leerFichero = new ObjectInputStream(new FileInputStream(archivo))) {
            // Deserializa y lee la lista de usuarios desde el archivo
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