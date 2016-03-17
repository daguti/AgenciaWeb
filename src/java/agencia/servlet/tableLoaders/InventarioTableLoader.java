/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.servlet.tableLoaders;

import agencia.modelo.DisponibilidadHotel;
import agencia.modelo.Hotel;
import agencia.modelo.Usuario;
import agencia.modelo.Vuelo;
import agencia.persistencia.GestionDAO;
import agencia.persistencia.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "InventarioTableLoader", urlPatterns = {"/InventarioTableLoader"})
public class InventarioTableLoader extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    try {
      GestionDAO dao = new GestionDAO();
      UserDAO userDao = new UserDAO();
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
      Usuario user = userDao.buscarPorUsername(name);
      String data;
      boolean fst = true;
      
      if(request.getParameter("vuelos") != null) {
        List<Vuelo> vueloList = dao.getVuelosInventario(user.getRefCompañia());
        int reserTur = 0, reserEco = 0, reserBuss = 0;
        data = "{ \"vuelos\":[";
        
        for(Vuelo vuelo : vueloList) {
          reserTur = vuelo.getAvion().getNumTur() - vuelo.getNumTur();
          reserEco = vuelo.getAvion().getNumEco() - vuelo.getNumEco();
          reserBuss = vuelo.getAvion().getNumBuss() - vuelo.getNumBuss();
          if(!fst) {
              data +=",";
          }
          data +="[\"" + vuelo.getAeroOrig().getNombre() + "\", \"" + vuelo.getAeroDest().getNombre() + "\", \"" + vuelo.getFecVuelo()
                  + "\", \"" + vuelo.getNumTur() + "\", \"" + reserTur + "\", \"" + vuelo.getPreTur() 
                + "\", \"" + vuelo.getNumEco() + "\", \"" + reserEco + "\", \"" + vuelo.getPreEco() 
                + "\", \"" + vuelo.getNumBuss() + "\", \"" + reserBuss + "\", \"" + vuelo.getPreBuss() + "\"]";
          fst = false;
        }
      } else {
        List<DisponibilidadHotel> dispList = dao.getDisponiHotelesInventario(user.getRefCompañia());
        int reserInd = 0, reserDob = 0, reserSup = 0, preRef = 0;
        Hotel hotel = null;
        data = "{ \"hoteles\":["; 
        for(DisponibilidadHotel dispo : dispList) {
          if(dispo.getRefHotel() != preRef) hotel = dao.getHotelByRef(dispo.getRefHotel());
          reserInd = hotel.getHabInd() - dispo.getIndDisp();
          reserDob = hotel.getHabDob() - dispo.getDobDisp();
          reserSup = hotel.getHabSup() - dispo.getSupDisp();
          if(!fst) {
              data +=",";
          }
          data +="[\"" + hotel.getNombre() + "\", \"" + dispo.getFecha() + "\", \"" + dispo.getIndDisp()
                  + "\", \"" + reserInd + "\", \"" + hotel.getPreInd() + "\", \"" + dispo.getDobDisp()
                + "\", \"" + reserDob + "\", \"" + hotel.getPreDob() + "\", \"" + dispo.getSupDisp()
                + "\", \"" + reserSup + "\", \"" + hotel.getPreSup() + "\"]";
          fst = false;
        }
      }
      data += "]}";
      System.out.println("DATOS ---> " +data);
      response.setContentType("text/plain");
      System.out.println("Respuesta = " + data);
      response.getWriter().write(data);
    } finally {
      out.close();
    }
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
