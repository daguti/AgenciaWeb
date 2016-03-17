/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.servlet.reserva;

import agencia.modelo.Hotel;
import agencia.modelo.ReservaHotel;
import agencia.modelo.ReservaVuelo;
import agencia.modelo.Reservas;
import agencia.modelo.Vuelo; 
import agencia.persistencia.AgenciaDAO;
import agencia.persistencia.GestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "GestionReservas", urlPatterns = {"/GestionReservas"})
public class GestionReservas extends HttpServlet {

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
      if(request.getParameter("refVuelo") != null) {
        GestionDAO dao = new GestionDAO();
        Vuelo vuelo;
        vuelo = dao.getVueloByRef(Integer.valueOf(request.getParameter("refVuelo")));
        restoreSessionVariables(request);
        putVueloSessionAtr(request, vuelo, "");
        request.getSession().setAttribute("vueloIda", request.getParameter("refVuelo"));
        request.getRequestDispatcher("/vuelos.jsp").forward(request, response);
      } else if(request.getParameter("refVueloVuelta") != null) {
        GestionDAO dao = new GestionDAO();
        Vuelo vuelo;
        vuelo = dao.getVueloByRef(Integer.valueOf(request.getParameter("refVueloVuelta")));
        putVueloSessionAtr(request, vuelo, "Vu");
        putVueloOut(response, vuelo);
        request.getSession().setAttribute("vueloVuelta", request.getParameter("refVueloVuelta"));
        response.flushBuffer();
      } else if(request.getParameter("nombreHotel") != null) {
        GestionDAO dao = new GestionDAO();
        Hotel hotel;
        hotel = dao.getHotel(request.getParameter("nomHotel"));
        putHotelSessionAtr(request, hotel);
        putHotelOut(response, hotel);
        request.getSession().setAttribute("nomHotel", request.getParameter("nombreHotel"));
        response.flushBuffer();
      } else if(request.getParameter("nombreHotelV") != null) {
        GestionDAO dao = new GestionDAO();
        Hotel hotel;
        hotel = dao.getHotel(request.getParameter("nombreHotelV"));
        restoreSessionVariables(request);
        putHotelSessionAtr(request, hotel);
        request.getSession().setAttribute("nomHotel", request.getParameter("nombreHotelV"));
        request.getRequestDispatcher("hoteles").forward(request, response);
      } else if(request.getParameter("refVueloA") != null) {
        GestionDAO dao = new GestionDAO();
        Vuelo vuelo;
        vuelo = dao.getVueloByRef(Integer.valueOf(request.getParameter("refVueloA")));
        putVueloSessionAtr(request, vuelo, "");
        putVueloOut(response, vuelo);
        request.getSession().setAttribute("vueloIda", request.getParameter("refVueloA"));
        response.flushBuffer();
      } else if(request.getParameter("cancelar") != null) {
        restoreSessionVariables(request);
        response.getWriter().write("OK");
        response.flushBuffer();
      } else if(request.getParameter("save") != null) {
        putParamsInSesToSave(request);
        response.flushBuffer();
      } else if(request.getParameter("getSes") != null) {
        returnParams(response, request);
        response.flushBuffer();
      } else if(request.getParameter("confirm") != null) {
        if(!saveResData(request)) {
          
        } else {
          
        }
      } else if(request.getParameter("detail") != null) {
        returnParams(response, request);
        //restoreSessionVariables(request);
        response.flushBuffer();
      } else if(request.getParameter("delete") != null) {
        AgenciaDAO dao = new AgenciaDAO();
        dao.borrarReserva(Integer.valueOf(request.getSession().getAttribute("refRes").toString()));
        response.flushBuffer();
      }
    } catch (ParseException ex) {
      Logger.getLogger(GestionReservas.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      out.close();
    }
  }
  
  private boolean saveResData(HttpServletRequest request) throws ParseException {
    Reservas reserva = new Reservas();
    HttpSession session = request.getSession();
    GestionDAO dao = new GestionDAO();
    Set<ReservaVuelo> vueloList = new HashSet<ReservaVuelo>();
    AgenciaDAO aDao = new AgenciaDAO();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String name = auth.getName(); //get logged in username
    
    reserva.setUsername(name);
    
    if(request.getSession().getAttribute("vueloIda") != null) {
      ReservaVuelo resVuelo = new ReservaVuelo();
      Vuelo vuelo = dao.getVueloByRef(Integer.valueOf(session.getAttribute("vueloIda").toString()));
      resVuelo.setVuelo(vuelo);
      resVuelo.setNumPlazasEco(Integer.valueOf(session.getAttribute("numEco").toString()));
      resVuelo.setNumPlazasTur(Integer.valueOf(session.getAttribute("numTur").toString()));
      resVuelo.setNumPlazasBuss(Integer.valueOf(session.getAttribute("numBuss").toString()));
      Double preTot = resVuelo.getNumPlazasEco() * vuelo.getPreEco() + resVuelo.getNumPlazasTur() * vuelo.getPreTur() + 
                      resVuelo.getNumPlazasBuss() * vuelo.getPreBuss();
      resVuelo.setPreTot(preTot);
      resVuelo.setReserva(reserva);
      vueloList.add(resVuelo);
    }
    if(request.getSession().getAttribute("vueloVuelta") != null) {
      ReservaVuelo resVuelo = new ReservaVuelo();
      Vuelo vuelo = dao.getVueloByRef(Integer.valueOf(session.getAttribute("vueloVuelta").toString()));
      resVuelo.setVuelo(vuelo);
      resVuelo.setNumPlazasEco(Integer.valueOf(session.getAttribute("numEcoVu").toString()));
      resVuelo.setNumPlazasTur(Integer.valueOf(session.getAttribute("numTurVu").toString()));
      resVuelo.setNumPlazasBuss(Integer.valueOf(session.getAttribute("numBussVu").toString()));
      Double preTot = resVuelo.getNumPlazasEco() * vuelo.getPreEco() + resVuelo.getNumPlazasTur() * vuelo.getPreTur() + 
                      resVuelo.getNumPlazasBuss() * vuelo.getPreBuss();
      resVuelo.setPreTot(preTot);
      resVuelo.setReserva(reserva);
      vueloList.add(resVuelo);
    }
    if(request.getSession().getAttribute("nomHotel") != null) {
      ReservaHotel resHotel = new ReservaHotel();
      SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
      Hotel hotel = dao.getHotel(session.getAttribute("nomHotel").toString());
      resHotel.setHotel(hotel);
      resHotel.setFecEnt(fmt.parse(session.getAttribute("fecEntradaH").toString()));
      resHotel.setFecSal(fmt.parse(session.getAttribute("fecOutH").toString()));
      resHotel.setNumHabInd(Integer.valueOf(session.getAttribute("numInd").toString()));
      resHotel.setNumHabDob(Integer.valueOf(session.getAttribute("numDob").toString()));
      resHotel.setNumHabSup(Integer.valueOf(session.getAttribute("numSup").toString()));
      Double preTot = resHotel.getNumHabInd() * hotel.getPreInd() + resHotel.getNumHabDob() * hotel.getPreDob() + 
                      resHotel.getNumHabSup() * hotel.getPreSup();
      resHotel.setPreTot(preTot);
      resHotel.setReserva(reserva);
      reserva.setReservHotel(resHotel);
    }
    reserva.setReservaVueloList(vueloList);
    reserva.setFecReserva(Calendar.getInstance().getTime());
    return aDao.storeReserva(reserva);
  }
  
  private void returnParams(HttpServletResponse response, HttpServletRequest request) throws IOException {
    HttpSession session = request.getSession();
    String data;
    response.setContentType("text/plain");
  
    data = session.getAttribute("origen") + "$$" + session.getAttribute("destino") + "$$" + 
           session.getAttribute("fecha") + "$$" + session.getAttribute("hora") + "$$" +
           session.getAttribute("duracion") + "$$" + session.getAttribute("numEco") + "$$" +
           session.getAttribute("numTur") + "$$" + session.getAttribute("numBuss") + "$$" +
           session.getAttribute("preTur") + "$$" + session.getAttribute("preEco") + "$$" +
           session.getAttribute("preBuss") + "$$" + session.getAttribute("origenVu") + "$$" + 
           session.getAttribute("destinoVu") + "$$" + 
           session.getAttribute("fechaVu") + "$$" + session.getAttribute("horaVu") + "$$" +
           session.getAttribute("duracionVu") + "$$" + session.getAttribute("numEcoVu") + "$$" +
           session.getAttribute("numTurVu") + "$$" + session.getAttribute("numBussVu") + "$$" +
           session.getAttribute("preTurVu") + "$$" + session.getAttribute("preEcoVu") + "$$" +
           session.getAttribute("preBussVu") + "$$" + session.getAttribute("nombreH") + "$$" + 
           session.getAttribute("destinoH") + "$$" + session.getAttribute("categoriaH") + "$$" + 
           session.getAttribute("numInd") + "$$" + session.getAttribute("numDob") + "$$" +
           session.getAttribute("numSup") + "$$" + session.getAttribute("preIndH") + "$$" +
           session.getAttribute("preDobH") + "$$" + session.getAttribute("preSupH") + "$$" +
           session.getAttribute("fecEntradaH") + "$$" + session.getAttribute("fecOutH")
           + "$$" + session.getAttribute("total");
    System.out.println("Response --> " + data);
    response.getWriter().write(data);
  }
  private void putParamsInSesToSave(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.setAttribute("fecEntradaH", request.getParameter("fecEntradaH"));
    session.setAttribute("fecOutH", request.getParameter("fecOutH"));
    session.setAttribute("numEco", request.getParameter("numEco"));
    session.setAttribute("numTur", request.getParameter("numTur"));
    session.setAttribute("numBuss", request.getParameter("numBuss"));
    session.setAttribute("numEcoVu", request.getParameter("numEcoVu"));
    session.setAttribute("numTurVu", request.getParameter("numTurVu"));
    session.setAttribute("numBussVu", request.getParameter("numBussVu"));
    session.setAttribute("numInd", request.getParameter("numInd"));
    session.setAttribute("numDob", request.getParameter("numDob"));
    session.setAttribute("numSup", request.getParameter("numSup"));
    session.setAttribute("total", request.getParameter("total"));
  }
  
  private void restoreSessionVariables(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.setAttribute("vueloIda", null);
    session.setAttribute("vueloVuelta", null);
    session.setAttribute("nomHotel", null);
    session.setAttribute("origen", null);
    session.setAttribute("origen", null);
    session.setAttribute("destino", null);
    session.setAttribute("fecha", null);
    session.setAttribute("hora", null);
    session.setAttribute("duracion", null);
    session.setAttribute("numEco", null);
    session.setAttribute("numTur", null);
    session.setAttribute("numBuss", null);
    session.setAttribute("preEco", null);
    session.setAttribute("preTur", null);
    session.setAttribute("preBuss", null);
    session.setAttribute("origenVu", null);
    session.setAttribute("destinoVu", null);
    session.setAttribute("fechaVu", null);
    session.setAttribute("horaVu", null);
    session.setAttribute("duracionVu", null);
    session.setAttribute("numEcoVu", null);
    session.setAttribute("numTurVu", null);
    session.setAttribute("numBussVu", null);
    session.setAttribute("preEcoVu", null);
    session.setAttribute("preTurVu", null);
    session.setAttribute("preBussVu", null);
    session.setAttribute("nombreH", null);
    session.setAttribute("destinoH", null);
    session.setAttribute("categoriaH", null);
    session.setAttribute("habDobH", null);
    session.setAttribute("habIndH", null);
    session.setAttribute("habSupH", null);
    session.setAttribute("preIndH", null);
    session.setAttribute("preDobH", null);
    session.setAttribute("preSupH", null);
    session.setAttribute("total", null);
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
  private void putVueloOut(HttpServletResponse response, Vuelo vuelo) throws IOException {
    response.setContentType("text/plain");
    String data = vuelo.getAeroOrig().getNombre() + "$$" + vuelo.getAeroDest().getNombre()
                 + "$$" + vuelo.getFecVuelo() + "$$" + vuelo.getHoraSalida() + "$$" +
                 vuelo.getDuracion() + "$$" + vuelo.getNumEco() + "$$" + vuelo.getNumTur() + "$$" +
                 vuelo.getNumBuss() + "$$" + vuelo.getPreEco() + "$$" + vuelo.getPreTur() + "$$" +
                 vuelo.getPreBuss();
    System.out.println("Respuesta = " + data);
    response.getWriter().write(data);
  }
  
  private void putVueloSessionAtr(HttpServletRequest request, Vuelo vuelo, String vu) {
    HttpSession session = request.getSession();
    session.setAttribute("origen"+vu, vuelo.getAeroOrig().getNombre());
    session.setAttribute("destino"+vu, vuelo.getAeroDest().getNombre());
    session.setAttribute("fecha"+vu, vuelo.getFecVuelo());
    session.setAttribute("hora"+vu, vuelo.getHoraSalida());
    session.setAttribute("duracion"+vu, vuelo.getDuracion());
    session.setAttribute("numEco"+vu, vuelo.getNumEco());
    session.setAttribute("numTur"+vu, vuelo.getNumTur());
    session.setAttribute("numBuss"+vu, vuelo.getNumBuss());
    session.setAttribute("preEco"+vu, vuelo.getPreEco());
    session.setAttribute("preTur"+vu, vuelo.getPreTur());
    session.setAttribute("preBuss"+vu, vuelo.getPreBuss());
  }
  
  private void putHotelOut(HttpServletResponse response, Hotel hotel) throws IOException {
    response.setContentType("text/plain");
    String data = hotel.getNombre() + "$$" + hotel.getCiudad()
                 + "$$" + hotel.getCategoria() + "$$" + hotel.getHabInd() + "$$" +
                 hotel.getHabDob() + "$$" + hotel.getHabSup() + "$$" + hotel.getPreInd() + "$$" +
                 hotel.getPreDob() + "$$" + hotel.getPreSup();
    System.out.println("Respuesta = " + data);
    response.getWriter().write(data);
  }
  
  private void putHotelSessionAtr(HttpServletRequest request, Hotel hotel) {
    HttpSession session = request.getSession();
    session.setAttribute("nombreH", hotel.getNombre());
    session.setAttribute("destinoH", hotel.getCiudad());
    session.setAttribute("categoriaH", hotel.getCategoria());
    session.setAttribute("habDobH", hotel.getHabDob());
    session.setAttribute("habIndH", hotel.getHabInd());
    session.setAttribute("habSupH", hotel.getHabSup());
    session.setAttribute("preIndH", hotel.getPreInd());
    session.setAttribute("preDobH", hotel.getPreDob());
    session.setAttribute("preSupH", hotel.getPreSup());
  }
}
