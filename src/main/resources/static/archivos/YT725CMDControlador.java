/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Markitos
 */
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        //Define la ruta
            String url = null;
            String edificio = null;
            String contenido = null;
            boolean edif = false;
            boolean cont = false;
            String ruta = request.getParameter("enviar");
            switch (ruta){
                
                //Entrará en este case cuando venga de elegir JSP o JSTL
                case "Elegir":
                    
                    //Define JSP o JSTL 
                        String modo = request.getParameter("modo");
                        if(modo.equals("on")){
                            modo = "JSTL";
                        }
                        sesion.setAttribute("ambas", "no");
                        sesion.removeAttribute("cuotaContenido");
                        sesion.removeAttribute("cuotaEdificio");
                        sesion.removeAttribute("cuotaTotal");
                        sesion.setAttribute("modo", modo);
                    
                    //Si es JSTL te permitirá elegir idioma
                        if(modo.equals("JSP")){
                            url = "JSP/TipoSeguro.jsp";
                        } else if (modo.equals("JSTL")){
                             url = "JSTL/IdiomaJSTL.jsp";
                        }
                    break;
                    
                //Entrará en este case cuando venga de elegir poliza
                case "Enviar":
                    edificio = request.getParameter("edificio");
                    contenido = request.getParameter("contenido");
                    //Si ambas han sido activadas se mostrará la poliza de contenido despues de la de edificio
                        if(edificio != null) {
                             edif = true;
                        }
                    
                        if(contenido != null) {
                            cont = true;
                        }
                        sesion.setAttribute("ambas", "no");
                        
                        if(edif && cont) {
                           sesion.setAttribute("ambas", "si");
                        }
                    
                    //Define la ruta que se tomará según la poliza
                        switch(String.valueOf(sesion.getAttribute("modo"))){
                            case "JSP":
                                if(edif) {
                                    url = "JSP/PolizaEdificio.jsp";
                                } else if(cont){
                                    url = "JSP/PolizaContenido.jsp";
                                } else {
                                    url = "JSP/TipoSeguro.jsp?error=Seleccione un tipo de seguro";
                                }
                                break;
                            case "JSTL":
                                if(edif) {
                                    url = "JSTL/PolizaEdificiosJSTL.jsp";
                                } else if(cont){
                                    url = "JSTL/PolizaContenidoJSTL.jsp";
                                } else {
                                    url = "JSTL/TipoSeguroJSTL.jsp";
                                    request.setAttribute("error", "Seleccione un tipo de seguro");
                                }
                                break;    
                        }  
                    break;
                case "Send":
                    edificio = request.getParameter("edificio");
                    contenido = request.getParameter("contenido");
                    //Si ambas han sido activadas se mostrará la poliza de contenido despues de la de edificio
                        if(edificio != null) {
                             edif = true;
                        }
                    
                        if(contenido != null) {
                            cont = true;
                        }
                        sesion.setAttribute("ambas", "no");
                        
                        if(edif && cont) {
                           sesion.setAttribute("ambas", "si");
                        }
                    
                    //Define la ruta que se tomará según la poliza
                        switch(String.valueOf(sesion.getAttribute("modo"))){
                            case "JSP":
                                if(edif) {
                                    url = "JSP/PolizaEdificios.jsp";
                                } else if(cont){
                                    url = "JSP/PolizaContenido.jsp";
                                } else {
                                    url = "JSP/TipoSeguro.jsp?error=Seleccione un tipo de seguro";
                                }
                                break;
                            case "JSTL":
                                if(edif) {
                                    url = "JSTL/PolizaEdificiosJSTL.jsp";
                                } else if(cont){
                                    url = "JSTL/PolizaContenidoJSTL.jsp";
                                } else {
                                    url = "JSTL/TipoSeguroJSTL.jsp";
                                    request.setAttribute("error", "Seleccione un tipo de seguro");
                                }
                                break;    
                        }  
                    break;
                case "Enviar/Send":
                    sesion.setAttribute("idioma", request.getParameter("idioma"));
                    url = "JSTL/TipoSeguroJSTL.jsp";
                    break;
            }
            request.getRequestDispatcher(url).forward(request, response);
            processRequest(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
