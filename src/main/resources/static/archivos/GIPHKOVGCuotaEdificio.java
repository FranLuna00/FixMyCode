/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.EdificioBean;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Markitos
 */
public class CuotaEdificio extends HttpServlet {

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
            throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
        response.setContentType("text/html;charset=UTF-8");
        //Obejto Edificio
            EdificioBean edificio = new EdificioBean();
            BeanUtils.populate(edificio, request.getParameterMap());
            
        //Cuota
            double cuota = Integer.parseInt(edificio.getValor()) * 0.005;
            
        //Edificio
            switch(edificio.getEdificio()){
                case "piso":
                    cuota = cuota * 0.95;
                    break;
                case "casa":
                    cuota = cuota * 1.00;
                    break;
                case "adosado":
                    cuota = cuota * 1.05;
                    break;
                case "duplex":
                    cuota = cuota * 1.10;
                    break;
                case "chalet":
                    cuota = cuota * 1.20;
                    break;
            }
            
        //Habitaciones
            cuota = cuota + cuota / 100 * edificio.getHabitaciones();
            
        //Fecha
            switch(edificio.getFecha()){
                case "1949":
                    cuota = cuota + cuota * 0.09;
                    break;
                case "1950":
                    cuota = cuota + cuota * 0.06;
                    break;
                case "1991":
                    cuota = cuota + cuota * 0.04;
                    break;
                case "2006":
                    cuota = cuota + cuota * 0.02;
                    break;
                case "2015":
                    cuota = cuota + cuota * 0.01;
                    break;
            }
        
        //Material
            if(edificio.getMaterial().equals("madera")){
                cuota = cuota + cuota * 0.1;
            }
        
        //Mostrar solo dos decimales
            DecimalFormat df = new DecimalFormat("#.00");
            String cuotaFinal = df.format(cuota);
            
        //Define la ruta
            HttpSession sesion = request.getSession(); 
            sesion.setAttribute("cuotaEdificio", cuotaFinal);
            sesion.setAttribute("edificio", cuota);
            String modo = String.valueOf(sesion.getAttribute("modo"));
            String url = null;
            
            if(sesion.getAttribute("cuotaContenido") != null){
                double total = cuota + Double.valueOf(String.valueOf(sesion.getAttribute("contenido")));
                    String cuotaTotal = df.format(total);
                    sesion.setAttribute("cuotaTotal", cuotaTotal);
            }
            
            if(sesion.getAttribute("ambas").equals("si")){
                url = "JSP/PolizaContenido.jsp";
            } else {
                switch(modo){
                    case "JSP":
                        url = "JSP/CuotaJSP.jsp";
                        break;
                    case "JSTL":
                        url = "JSTL/CuotaJSTL.jsp";
                        break;
                }
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
        try {
            processRequest(request, response);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CuotaEdificio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CuotaEdificio.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CuotaEdificio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(CuotaEdificio.class.getName()).log(Level.SEVERE, null, ex);
        }
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
