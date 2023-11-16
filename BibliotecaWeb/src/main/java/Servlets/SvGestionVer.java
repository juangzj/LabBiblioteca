/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

/**
 * imports
 */
import com.umariana.bibliotecaweb.Libros;
import com.umariana.bibliotecaweb.ListaDobleEnlace;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(name = "SvGestionVer", urlPatterns = {"/SvGestionVer"})


public class SvGestionVer extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Do Get ver
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //pedir parametros
        String titulo=request.getParameter("titulo");
        
        //crear variable de la clase lista doblemente enlazada
         ListaDobleEnlace lista = new ListaDobleEnlace();
         
        ServletContext context=getServletContext();
        //deserializar la lista 
        lista = ListaDobleEnlace.deserializarLista(context);
        Libros libro= lista.encontrado(titulo);
                //Verificacion de la variable
                if (libro != null) {

                    //crear modal para visualizar la informacion del libro
                    String modalHtml = "<h2>Titulo: " + libro.getTitulo() + "</h2>"
                            + "<p>Autor: " +libro.getAutor() + "</p>"
                            + "<p>Año publicacion: " + libro.getAnio() + "</p>" 
                            + "<p>Estado: " + libro.getPrestado() + "</p>" 
                            + "<img src='img/" + libro.getFoto() + "' alt='portada' width='100%'/>";
                    response.setContentType("text/html");
                    response.getWriter().write(modalHtml);

                } else {
                    // Maneja el caso en el que no se encuentra el libro
                    response.setContentType("text/plain");
                    response.getWriter().write("Libro no encontrado");
                }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}