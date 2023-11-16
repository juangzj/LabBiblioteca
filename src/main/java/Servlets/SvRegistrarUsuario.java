/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

/**
 * imports
 */
import com.umariana.bibliotecaweb.DatosUsuarios;
import com.umariana.bibliotecaweb.Gestion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvRegistrarUsuario", urlPatterns = {"/SvRegistrarUsuario"})
public class SvRegistrarUsuario extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
    }

    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //solicitar datos del usuario a registrar
            ServletContext context = getServletContext();
            String nombre=request.getParameter("nombre"); 
            String cedula=request.getParameter("cedula");          
            String contraseña=request.getParameter("contrasena");
            
            // crear la contenedora donde se almacenan los usuarios
            ArrayList<DatosUsuarios> usuarios= new ArrayList<>() ;
            
            try {
                //deserializar el archivo de los usuarios serializados
                usuarios = Gestion.deserializacion(context);
                
                //catch para posibles errores al no encontrar archivos o clases
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SvRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SvRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //logica para no permitir cedulas iguales al registrar usuario
            String alert="registro";
            for (DatosUsuarios usuario: usuarios){
                if (cedula.equals(usuario.getCedula())){
                    alert="registroerror";
                }
            }
            //crear el nuevo usuario con sus datos 
            DatosUsuarios us=new DatosUsuarios(cedula,nombre , contraseña);
            
            //agregar el usuario registrado a la contenedora
            usuarios.add(us);
            //serializar esta informacion en el archivo
            Gestion.serializacion(usuarios, context);
            
            //redirigir a la pagina segun las variables
            response.sendRedirect("index.jsp?alert="+alert);
    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}