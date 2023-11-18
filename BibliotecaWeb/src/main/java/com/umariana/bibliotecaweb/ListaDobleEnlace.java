package com.umariana.bibliotecaweb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.servlet.ServletContext;

// Clase ListaDobleEnlace que implementa Serializable
public class ListaDobleEnlace implements Serializable {

    NodoDobleEnlace cabeza;
    NodoDobleEnlace cola;

    // Clase interna para representar nodos de la lista doblemente enlazada
    class NodoDobleEnlace implements Serializable {

        Libros libro;
        NodoDobleEnlace siguiente;
        NodoDobleEnlace anterior;

        // Constructor de la clase NodoDobleEnlace
        public NodoDobleEnlace(Libros libro) {
            this.libro = libro;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    // Constructor de la clase ListaDobleEnlace
    public ListaDobleEnlace() {
        this.cabeza = null;
        this.cola = null;
    }

    // Método para agregar un libro al final de la lista
    public void agregarAlFinal(Libros libro) {
        // Crea un nuevo nodo con el libro proporcionado
        NodoDobleEnlace nuevoNodo = new NodoDobleEnlace(libro);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza y la cola.
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            // Configura los enlaces del nuevo nodo y actualiza la cola
            nuevoNodo.anterior = cola;
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
    }

// Método para agregar un libro al principio de la lista
    public void agregarAlPrincipio(Libros libro) {
        // Crea un nuevo nodo con el libro proporcionado
        NodoDobleEnlace nuevoNodo = new NodoDobleEnlace(libro);

        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza y la cola.
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            // Configura los enlaces del nuevo nodo y actualiza la cabeza
            nuevoNodo.siguiente = cabeza; // El nuevo nodo apunta al nodo actual de la cabeza.
            cabeza.anterior = nuevoNodo;  // El nodo actual de la cabeza apunta al nuevo nodo.
            cabeza = nuevoNodo;          // El nuevo nodo se convierte en la nueva cabeza.
        }
    }
   
    public String MostrarLista(String cedula) {
    NodoDobleEnlace nodo = this.cabeza;
    String resultado = "";
    String encontrado="no";

    // Iterar sobre la lista de libros y construir una representación HTML de los libros
    while (nodo != null) {
        Libros libro = nodo.libro;
        if(cedula.equals(libro.getDueño())){
            resultado += "<tr>";
            resultado += "<td>" + libro.getTitulo() + "</td>";
            resultado += "<td>" + libro.getAutor() + "</td>";
            resultado += "<td>" + libro.getAnio() + "</td>";
            resultado += "<td>" + libro.getFoto() + "</td>";
            resultado += "<td>" + libro.getPrestado() + "</td>";
            resultado += "<td> <a href=\"#\" type=\"button\" class=\"btn btn-outline-success\" data-bs-toggle=\"modal\" data-bs-target=\"#editModalConfirm\" data-nombre=\"" + libro.getTitulo() + "\" id=\"editarBtn\"><i class=\"fa-solid fa-marker\"></i> </a>";
            resultado += "<a href=\"#\" class=\"btn btn-outline-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-nombre=\"" + libro.getTitulo() + "\"><i class=\"fa fa-eye\"> </i></a>";
            resultado += "<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminar\" data-nombre=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>";
            resultado += "</tr>";
            encontrado="si";
        }
        nodo = nodo.siguiente;

    }
    if (encontrado.equals("no")){
        resultado += "<tr>";
        resultado += "<td>No hay libros</td>";
        resultado += "<td></td>";
        resultado += "<td></td>";
        resultado += "<td></td>";
        resultado += "<td></td>";
        resultado += "<td> <a href=\"#\" type=\"button\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-marker\"></i> </a>";
        resultado += "<a href=\"#\" class=\"btn btn-outline-primary\"><i class=\"fa fa-eye\"> </i></a>";
        resultado += "<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></a></td>";
        resultado += "</tr>";
    }
    return resultado;
}
    // Método para serializar la lista y guardarla en un archivo
    public void serializarLista(ServletContext context) {
        String path = "/data/Libros.ser";
        String Rpath = context.getRealPath(path);
        File archivo = new File(Rpath);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(archivo);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Serializa la lista y la escribe en el archivo
            objectOutputStream.writeObject(this);

            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("La lista doblemente enlazada ha sido serializada y guardada en " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para deserializar la lista desde un archivo
    public static ListaDobleEnlace deserializarLista(ServletContext context) {
        ListaDobleEnlace lista = null;
        String path = "/data/Libros.ser";
        String Rpath = context.getRealPath(path);
        File archivo = new File(Rpath);

        try {
            FileInputStream fileInputStream = new FileInputStream(archivo);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            // Deserializa la lista desde el archivo
            lista = (ListaDobleEnlace) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
            System.out.println("La lista doblemente enlazada ha sido deserializada desde " + archivo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // Método para eliminar un libro de la lista por título
    public void eliminarLibro(String titulo) {
        NodoDobleEnlace actual = cabeza;

        while (actual != null) {
            if (actual.libro.getTitulo().equals(titulo)) {
                if (actual.anterior != null) {
                    actual.anterior.siguiente = actual.siguiente;
                } else {
                    // Si el libro a eliminar es la cabeza, actualiza la cabeza.
                    cabeza = actual.siguiente;
                }

                if (actual.siguiente != null) {
                    actual.siguiente.anterior = actual.anterior;
                } else {
                    // Si el libro a eliminar es la cola, actualiza la cola.
                    cola = actual.anterior;
                }

                // Se elimina de la lista.
                return;
            }
            actual = actual.siguiente;
        }
    }
     // Método para buscar un libro por título y obtener su información
    public Libros encontrado(String titulo) {
    NodoDobleEnlace nodo = this.cabeza;
    Libros resultado = new Libros (); 
    while (nodo != null) {
        Libros libro = nodo.libro;
        if(libro.getTitulo().equals(titulo)){
            return libro;
        }
        nodo = nodo.siguiente;
    }
    return null;
}
     // Método para mostrar una lista de libros en formato HTML basado en una búsqueda
    public String MostrarListaBusqueda(String buscar, String cedula) {
     // Inicializa un nodo al comienzo de la lista
        NodoDobleEnlace nodo = this.cabeza;
        // Inicializa una cadena para almacenar el resultado HTML
        String resultado = "";
        // Variable para controlar si se encontraron coincidencias
        boolean en = false;

        // Iterar sobre la lista de libros y construir una representación HTML de los libros
    while (nodo != null) {
        // Obtiene el libro del nodo actual
            Libros libro = nodo.libro;
        if(cedula.equals(libro.getDueño())){
            // Comprueba si el título o autor del libro coincide con el término de búsqueda
        if(libro.getTitulo().equals(buscar) || libro.getAutor().equals(buscar)){
             // Si hay coincidencia, agrega una fila HTML para el libro a la cadena de resultados
        resultado += "<tr>";
        resultado += "<td>" + libro.getTitulo() + "</td>";
        resultado += "<td>" + libro.getAutor() + "</td>";
        resultado += "<td>" + libro.getAnio() + "</td>";
        resultado += "<td>" + libro.getFoto() + "</td>";
        resultado += "<td>" + libro.getPrestado() + "</td>";
        resultado += "<td> <a href=\"#\" type=\"button\" class=\"btn btn-outline-success\" data-bs-toggle=\"modal\" data-bs-target=\"#editModalConfirm\" data-nombre=\"" + libro.getTitulo() + "\" id=\"editarBtn\" onclick=\"editar(this)\"><i class=\"fa-solid fa-marker\"></i> </a>";
        resultado += "<a href=\"#\" class=\"btn btn-outline-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" data-nombre=\"" + libro.getTitulo() + "\"><i class=\"fa fa-eye\"> </i></a>";
        resultado += "<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#eliminar\" data-nombre=\"" + libro.getTitulo() + "\"><i class=\"fa-solid fa-trash\"></i></a></td>";
        resultado += "</tr>";
       // Marca que se encontraron coincidencias
                en = true;
        }}
       // Avanza al siguiente nodo
            nodo = nodo.siguiente;
    }
    // Si no se encontraron coincidencias, agrega una fila de "No encontramos coincidencias" a la cadena de resultados
    if(!en){
        resultado += "<tr>";
        resultado += "<td>No encontramos coindicencias</td>";
        resultado += "<td></td>";
        resultado += "<td></td>";
        resultado += "<td></td>";
        resultado += "<td></td>";
        resultado += "<td> <a href=\"#\" type=\"button\" class=\"btn btn-outline-success\"><i class=\"fa-solid fa-marker\"></i> </a>";
        resultado += "<a href=\"#\" class=\"btn btn-outline-primary\"><i class=\"fa fa-eye\"> </i></a>";
        resultado += "<a href=\"#\" type=\"button\" class=\"btn btn-outline-danger\"><i class=\"fa-solid fa-trash\"></i></a></td>";
        resultado += "</tr>";

    }
     // Devuelve la cadena HTML resultante
    return resultado;
}
     // Método para verificar si un libro con un título dado existe en la lista

    public boolean existen(String titulo) {
        // Inicializa un nodo al comienzo de la lista
        NodoDobleEnlace nodo = this.cabeza;

        // Itera sobre la lista de libros
        while (nodo != null) {
            // Obtiene el libro del nodo actual
            Libros libro = nodo.libro;

            // Comprueba si el título del libro coincide con el título proporcionado
            if (libro.getTitulo().equals(titulo)) {
                // Si se encuentra un libro con el mismo título, devuelve false
                return false;
            }

            // Avanza al siguiente nodo
            nodo = nodo.siguiente;
        }
        // Si no se encuentra un libro con el título dado, devuelve true
        return true;
    }

// Método para editar el autor de un libro con un título dado
    public void editarAutor(String titulo, String nuevoAutor) {
        // Inicializa un nodo al comienzo de la lista
        NodoDobleEnlace nodo = this.cabeza;
        // Variable para controlar si se ha encontrado el libro a editar
        boolean encontrado = false;

        // Itera sobre la lista de libros
        while (nodo != null && !encontrado) {
            // Obtiene el libro del nodo actual
            Libros libro = nodo.libro;

            // Comprueba si el título del libro coincide con el título proporcionado
            if (libro.getTitulo().equals(titulo)) {
                // Si se encuentra el libro, actualiza su autor con el nuevo autor
                libro.setAutor(nuevoAutor);
                encontrado = true;
            }

            // Avanza al siguiente nodo
            nodo = nodo.siguiente;
        }
    }

// Método para editar el año de un libro con un título dado
    public void editarAnio(String titulo, String nuevoAnio) {
        // Inicializa un nodo al comienzo de la lista
        NodoDobleEnlace nodo = this.cabeza;
        // Variable para controlar si se ha encontrado el libro a editar
        boolean encontrado = false;

        // Itera sobre la lista de libros
        while (nodo != null && !encontrado) {
            // Obtiene el libro del nodo actual
            Libros libro = nodo.libro;

            // Comprueba si el título del libro coincide con el título proporcionado
            if (libro.getTitulo().equals(titulo)) {
                // Si se encuentra el libro, actualiza su año con el nuevo año
                libro.setAnio(nuevoAnio);
                encontrado = true;
            }

            // Avanza al siguiente nodo
            nodo = nodo.siguiente;
        }
    }

// Método para editar la foto de un libro con un título dado
    public void editarFoto(String titulo, String nuevaFoto) {
        // Inicializa un nodo al comienzo de la lista
        NodoDobleEnlace nodo = this.cabeza;
        // Variable para controlar si se ha encontrado el libro a editar
        boolean encontrado = false;

        // Itera sobre la lista de libros
        while (nodo != null && !encontrado) {
            // Obtiene el libro del nodo actual
            Libros libro = nodo.libro;

            // Comprueba si el título del libro coincide con el título proporcionado
            if (libro.getTitulo().equals(titulo)) {
                // Si se encuentra el libro, actualiza su foto con la nueva foto
                libro.setFoto(nuevaFoto);
                encontrado = true;
            }

            // Avanza al siguiente nodo
            nodo = nodo.siguiente;
        }
    }

// Método para editar el estado de préstamo de un libro con un título dado
    public void editarPrestamo(String titulo, String estado) {
        // Inicializa un nodo al comienzo de la lista
        NodoDobleEnlace nodo = this.cabeza;
        // Variable para controlar si se ha encontrado el libro a editar
        boolean encontrado = false;

        // Itera sobre la lista de libros
        while (nodo != null && !encontrado) {
            // Obtiene el libro del nodo actual
            Libros libro = nodo.libro;

            // Comprueba si el título del libro coincide con el título proporcionado
            if (libro.getTitulo().equals(titulo)) {
                // Si se encuentra el libro, actualiza su estado de préstamo con el nuevo estado
                libro.setPrestado(estado);
                encontrado = true;
            }

            // Avanza al siguiente nodo
            nodo = nodo.siguiente;
        }
    }
}