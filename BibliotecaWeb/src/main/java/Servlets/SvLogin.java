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
import com.umariana.bibliotecaweb.ListaDobleEnlace;
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


@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            //solicitar datos del usuario
            ServletContext context = getServletContext();
            String cedula=request.getParameter("cedula");          
            String contraseña=request.getParameter("contrasena");
            
            //crear contenedora para guardar los usuarios 
            ArrayList<DatosUsuarios> usuarios= new ArrayList<>() ;
            
            try {
                //deserialiar el array
                usuarios = Gestion.deserializacion(context);
                
                //excepcion en caso de no encontrar el archivo
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SvRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
                //excepcion en caso de no encontrar la clase
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SvRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //variables para enviar por la url 
            String resp="alert=erroringreso";
            String pag="index";
            
             //logica para permitir el ingreso a la interfaz principal con usuario y contraseña
             for (DatosUsuarios usuario: usuarios){
                if (cedula.equals(usuario.getCedula()) && contraseña.equals(usuario.getContrasena())){
                     resp="usuario="+usuario.getNombre()+"&cedula="+usuario.getCedula();pag="login";  
                }
            }
             //redirigir segun la pagina que tome la variable pag
            response.sendRedirect(pag+".jsp?"+resp);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
