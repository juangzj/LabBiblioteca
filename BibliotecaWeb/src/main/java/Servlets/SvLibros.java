/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import com.umariana.bibliotecaweb.Libros;
import com.umariana.bibliotecaweb.ListaDobleEnlace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



@WebServlet(name = "SvLibros", urlPatterns = {"/SvLibros"})
@MultipartConfig
public class SvLibros extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //pedir los parametros necesarios
        String tituloLibro = request.getParameter("titulo");
        System.out.println("Título del libro a eliminar: " + tituloLibro);
        //crear variable de la clase lista doblemente enlazada
        ListaDobleEnlace lista = new ListaDobleEnlace();
        ServletContext context=getServletContext();
        //deserializar la lista 
        lista = ListaDobleEnlace.deserializarLista(context);
        //si la lista no tiene objetos crear la lista nuevamente
        if(lista==null){
         lista=new ListaDobleEnlace();
        }
        //utilizar el metodo para eliminar libro
        lista.eliminarLibro(tituloLibro);
        //serializar la lista con los nuevos cambios
        lista.serializarLista(context);

        // Luego, puedes enviar una respuesta de confirmación al cliente si se ha eliminado el libro.
         response.sendRedirect("login.jsp"); 
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         //conseguir los datos del libro 
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String anio = request.getParameter("anio");
        String usuario=request.getParameter("usuario");
        String cedula=request.getParameter("cedula");
        String pres = request.getParameter("prestamo");
        String prestamo = "Disponible";
        
        //establecer el estado del libro si es prestado o no
        if (pres != null && pres.equals("true")) {
            prestamo = "Prestado";
        }
        //logica para cargar la imagen por partes
        Part filePart = request.getPart("foto");

        String fileName = filePart.getSubmittedFileName();

        String uploadDirectory = getServletContext().getRealPath("img");
        
        String filePath = uploadDirectory + File.separator + fileName;
        
         try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(filePath)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        }
        System.out.println(titulo+autor+anio+fileName);
        //crear la lista de la clase lista doblemente enlazada
        ListaDobleEnlace lista = new ListaDobleEnlace();
        ServletContext context=getServletContext();
         //deserializar la lista
        lista = ListaDobleEnlace.deserializarLista(context);
        //si la lista no tiene objetos crear la lista nuevamente
        if(lista==null){
         lista=new ListaDobleEnlace();
        }
         //logica para agregar el libro en una posicion segun si hay libros ya agregados
        String alert=null;
        if(lista.existen(titulo)){
             Libros libro=new Libros(titulo,autor,anio,fileName, prestamo, cedula);
             lista.agregarAlPrincipio(libro);
             lista.serializarLista(context);
        }else {
             //esto sucede en caso de no cumplirse la condicion del if y la variable alert es error
           alert="error"; 
        }

        //redirigir a la pagina y enviar la variable alert
        response.sendRedirect("login.jsp?usuario="+usuario+"&alert="+alert+"&cedula="+cedula);

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



}
