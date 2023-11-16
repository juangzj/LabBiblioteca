/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

/**
 * imports
 */
import com.umariana.bibliotecaweb.ListaDobleEnlace;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "SvEditar", urlPatterns = {"/SvEditar"})
@MultipartConfig
public class SvEditar extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         //pedir los datos del usuario por el metodo get para redirigir
        String buscar=request.getParameter("busqueda");
        String usuario=request.getParameter("usuario");
        String cedula=request.getParameter("cedula");
        response.sendRedirect("login.jsp?busqueda="+buscar+"&usuario="+usuario+"&cedula="+cedula);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //pedir datos del libro mediante el metodo post que maneja mas privacidad
       String usuario=request.getParameter("usuario");
       String cedula=request.getParameter("cedula");
       String titulo=request.getParameter("titulo");
       String autor=request.getParameter("autor");
       String anio=request.getParameter("anio");
       String foto=request.getParameter("verificacionFotos");
       String est=request.getParameter("estado");
       
       //crear variable con la clase lista doble enlace
       ListaDobleEnlace lista = new ListaDobleEnlace();
       ServletContext context=getServletContext();
       
       //deserializar la lista 
       lista = ListaDobleEnlace.deserializarLista(context);
       if(lista==null){
         lista=new ListaDobleEnlace();
        }
        //Analizar caso a editar
       if(autor!=null){
           lista.editarAutor(titulo, autor);
       }else if(anio!=null){
           lista.editarAnio(titulo, anio);
       }else if(foto!=null){
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
           lista.editarFoto(titulo, fileName);
       }else if(est!=null){
           String estado=request.getParameter("prestamo");
            String prestamo = "Disponible";
                if (estado != null && estado.equals("true")) {
                prestamo = "Prestado";
            }    
           lista.editarPrestamo(titulo, prestamo);
       }
       //serializar la lista del libro, se necesita enviar el context para que funcione el metodo
       lista.serializarLista(context);
       response.sendRedirect("login.jsp?usuario="+usuario+"&cedula="+cedula);//redirige
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
