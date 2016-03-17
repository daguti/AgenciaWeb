/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.servlet.tableLoaders;

import agencia.modelo.Hotel;
import agencia.modelo.ReservaHotel;
import agencia.modelo.ReservaVuelo;
import agencia.modelo.Reservas;
import agencia.modelo.Vuelo;
import agencia.persistencia.AgenciaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "ReservasTableLoader", urlPatterns = {"/ReservasTableLoader"})
public class ReservasTableLoader extends HttpServlet {

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
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
      AgenciaDAO dao = new AgenciaDAO();
      if(request.getParameter("detail") != null) {
        Reservas reserva = dao.getReservasByRef(Integer.valueOf(request.getParameter("refRes")));
        putSesVars(reserva, request);
        request.getRequestDispatcher("/detalleReserva.jsp").forward(request, response);
        response.flushBuffer();
      } else {
        String data = dao.getReservasOutputByUserName(name);
        System.out.println("DATOS ---> " +data);
        response.setContentType("text/plain");
        System.out.println("Respuesta = " + data);
        response.getWriter().write(data);
      }
    } finally {
      out.close();
    }
  }
  
  private void putSesVars(Reservas reserva, HttpServletRequest request) {
    HttpSession session = request.getSession();
    Double preTot = 0.0;
    if(reserva.getReservaVueloList().size() > 0) {
      String vu = "";
      for(ReservaVuelo res : reserva.getReservaVueloList()) {
        Vuelo vuelo = res.getVuelo();
        session.setAttribute("origen"+vu, vuelo.getAeroOrig().getNombre()); 
        session.setAttribute("destino"+vu, vuelo.getAeroDest().getNombre()); 
        session.setAttribute("fecha"+vu, vuelo.getFecVuelo()); 
        session.setAttribute("hora"+vu, vuelo.getHoraSalida());
        session.setAttribute("duracion"+vu, vuelo.getDuracion()); 
        session.setAttribute("numEco"+vu, res.getNumPlazasEco());
        session.setAttribute("numTur"+vu, res.getNumPlazasTur()); 
        session.setAttribute("numBuss"+vu, res.getNumPlazasBuss());
        session.setAttribute("preTur"+vu, vuelo.getPreTur()); 
        session.setAttribute("preEco"+vu, vuelo.getPreEco());
        session.setAttribute("preBuss"+vu, vuelo.getPreBuss());
        preTot += res.getPreTot();
        vu = "Vu";
      }
    }
    if(reserva.getReservHotel() != null) {
      ReservaHotel res = reserva.getReservHotel();
      Hotel hotel = res.getHotel();
      
      session.setAttribute("nombreH", hotel.getNombre());
      session.setAttribute("destinoH", hotel.getCiudad());
      session.setAttribute("categoriaH", hotel.getCategoria()); 
      session.setAttribute("habIndH", res.getNumHabInd());
      session.setAttribute("habDobH", res.getNumHabDob());
      session.setAttribute("habSupH", res.getNumHabSup());
      session.setAttribute("preIndH", hotel.getPreInd());
      session.setAttribute("preDobH", hotel.getPreDob());
      session.setAttribute("preSupH", hotel.getPreSup());
      session.setAttribute("fecEntradaH", res.getFecEnt());
      session.setAttribute("fecOutH", res.getFecSal());
      preTot = res.getPreTot();
    }
    session.setAttribute("refRes", request.getParameter("refRes"));
    session.setAttribute("total", preTot);
    Date resDat = reserva.getFecReserva();
    Date salDat = session.getAttribute("fecha") != null ? (Date)session.getAttribute("fecha") : (Date)session.getAttribute("fecEntradaH");
    long diffInMillies = salDat.getTime() - resDat.getTime();
    TimeUnit timeUnit = TimeUnit.DAYS;
    if(timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS) >= 7) {
      session.setAttribute("borrable", "1");
    } else {
      session.setAttribute("borrable", null);
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
