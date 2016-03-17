/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.servlet;

import agencia.modelo.Aeropuerto;
import agencia.modelo.Avion;
import agencia.modelo.CompaniaAerea;
import agencia.modelo.CompaniaHotelera;
import agencia.modelo.Hotel;
import agencia.modelo.OfertaHotel;
import agencia.modelo.OfertaVuelo;
import agencia.modelo.ReservaHotel;
import agencia.modelo.ReservaVuelo;
import agencia.modelo.Vuelo;
import agencia.persistencia.GestionDAO;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ESa10969
 */
@WebServlet(name = "AltasManuales", urlPatterns = {"/altasManuales"})
public class AltasManuales extends HttpServlet {

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
    try {
      GestionDAO dao = new GestionDAO();
      if(request.getParameter("aero") != null) {
        Aeropuerto aero;
        aero = dao.getAeroByCod(request.getParameter("codigoOld"));
        if(aero == null) {
          aero = new Aeropuerto(request.getParameter("codigo"), request.getParameter("nombre"),
                                         request.getParameter("pais"), request.getParameter("ciudad"), 
                                         new HashSet<Vuelo>());
        } else {
          aero.setNombre(request.getParameter("nombre"));
          aero.setCiudad(request.getParameter("ciudad"));
          aero.setPais(request.getParameter("pais"));
        }
        if(request.getParameter("delete") == null) {
          dao.storeAeropuerto(aero);
          request.getRequestDispatcher("/gestionAero.jsp").forward(request, response);
        } else {
          dao.deleteAeropuerto(aero);
        }
      } else if(request.getParameter("avion") != null) {
        Avion av = null;
        if(request.getParameter("refAvion") != null && !request.getParameter("refAvion").equals("")) av = dao.getAvion(Integer.valueOf(request.getParameter("refAvion")));
        if(av == null) {
          av = new Avion(request.getParameter("descripcion"),Integer.valueOf(request.getParameter("numTur")),
                       Integer.valueOf(request.getParameter("numEco")),Integer.valueOf(request.getParameter("numBuss")),
                       dao.getCompañíaID(request.getParameter("compania")), new HashSet<Vuelo>());
        } else {
          av.setDescripcion(request.getParameter("descripcion"));
          av.setNumTur(Integer.valueOf(request.getParameter("numTur")));
          av.setNumBuss(Integer.valueOf(request.getParameter("numBuss")));
          av.setNumEco(Integer.valueOf(request.getParameter("numEco"))); 
          av.setCompania(dao.getCompañíaID(request.getParameter("compania")));
        }
        if(request.getParameter("delete") == null) dao.storeAvion(av);
        else dao.deleteAvion(av);
        request.getRequestDispatcher("/gestionAviones.jsp").forward(request, response);
      } else if(request.getParameter("comp") != null) {
        if(request.getParameter("tipo").equals("1")) {
          CompaniaAerea aerea;
          aerea = dao.getCompañíaName(request.getParameter("refOld"));
          if(aerea == null) {
            aerea = new CompaniaAerea(request.getParameter("ref"), request.getParameter("nombre"),
                                    request.getParameter("pais"), new HashSet<Avion>());
          } else {
            aerea.setNombre(request.getParameter("nombre"));
            aerea.setPais(request.getParameter("pais"));
          }
          if(request.getParameter("delete") == null) dao.storeCompaniaAerea(aerea);
          else dao.deleteComapniaAerea(aerea);
        } else {
          CompaniaHotelera hotelera;
          hotelera = dao.getHoteleraByRef(request.getParameter("refOld"));
          if(hotelera == null) {
            hotelera = new CompaniaHotelera(request.getParameter("ref"), request.getParameter("nombre"),
                                    request.getParameter("pais"), new HashSet<Hotel>());
          } else {
            hotelera.setNombre(request.getParameter("nombre"));
            hotelera.setPais(request.getParameter("pais"));
          }
          if(request.getParameter("delete") == null) dao.storeCompaniaHotelera(hotelera);
          else dao.deleteCompaniaHotelera(hotelera);
        }
        request.getRequestDispatcher("/gestionComp.jsp").forward(request, response);
      } else if(request.getParameter("hotel") != null) {
        DecimalFormat fmt = new DecimalFormat("############,##");
        Hotel hotel;
        boolean update = true;
        hotel = dao.getHotel(request.getParameter("nombreOld"));
        if(hotel == null) {
          update = false;
          hotel = new Hotel(request.getParameter("nombre"), Integer.valueOf(request.getParameter("categoria")),
                                            request.getParameter("descripcion"), request.getParameter("ciudad"), request.getParameter("direccion"),
                                            Integer.valueOf(request.getParameter("numInd")), Integer.valueOf(request.getParameter("numDob")),
                                            Integer.valueOf(request.getParameter("numSup")), fmt.parse(request.getParameter("preInd")).doubleValue(),
                                            fmt.parse(request.getParameter("preDob")).doubleValue(), fmt.parse(request.getParameter("preSup")).doubleValue(),
                                            dao.getHoteleraByCompania(request.getParameter("compania")),new HashSet<ReservaHotel>());
        } else {
          hotel.setCategoria(Integer.valueOf(request.getParameter("categoria")));
          hotel.setDescripcion(request.getParameter("descripcion"));
          hotel.setCiudad(request.getParameter("ciudad"));
          hotel.setDireccion(request.getParameter("direccion"));
          hotel.setHabInd(Integer.valueOf(request.getParameter("numInd")));
          hotel.setHabDob(Integer.valueOf(request.getParameter("numDob")));
          hotel.setHabSup(Integer.valueOf(request.getParameter("numSup")));
          hotel.setPreInd(Double.valueOf(request.getParameter("preInd")));
          hotel.setPreDob(Double.valueOf(request.getParameter("preDob")));
          hotel.setPreSup(Double.valueOf(request.getParameter("preSup")));
          hotel.setCompañia(dao.getHoteleraByCompania(request.getParameter("compania")));
        }
        if(request.getParameter("delete") == null) dao.storeHotel(hotel, update);
        else dao.deleteHotel(hotel);
        request.getRequestDispatcher("/gestionHoteles.jsp").forward(request, response);
      } else if(request.getParameter("ofertaHotel") != null) {
        SimpleDateFormat datFmt = new SimpleDateFormat("dd-MM-yyyy");
        DecimalFormat fmt = new DecimalFormat("############,##");
        dao.storeOfertaHotel(new OfertaHotel(dao.getHotel(request.getParameter("nombre")).getRefHotel(),
                             datFmt.parse(request.getParameter("inicio")), datFmt.parse(request.getParameter("fin")),
                             fmt.parse(request.getParameter("preInd")).doubleValue(), fmt.parse(request.getParameter("preDob")).doubleValue(), 
                             fmt.parse(request.getParameter("preSup")).doubleValue()));
        request.getRequestDispatcher("/gestionHoteles.jsp").forward(request, response);
      } else if(request.getParameter("vuelo") != null) {
        DecimalFormat fmt = new DecimalFormat("############,##");
        SimpleDateFormat datFmt = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat hourFmt = new SimpleDateFormat("HH:mm:ss");
        Avion av = dao.getAvion(Integer.valueOf(request.getParameter("refAvion")));
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");
        Double preTur = fmt.parse(request.getParameter("preTur")).doubleValue();
        Double preBuss = fmt.parse(request.getParameter("preBuss")).doubleValue();
        Double preEco = fmt.parse(request.getParameter("preEco")).doubleValue();
        origen = origen.substring(origen.lastIndexOf(")") + 1).trim();
        destino = destino.substring(destino.lastIndexOf(")") + 1).trim();
        Vuelo vu = null;
        int refVuelo = 0;
        if(request.getParameter("refVuelo") != null) vu = dao.getVueloByRef(Integer.valueOf(request.getParameter("refVuelo")));
        if(vu == null) {
          refVuelo = dao.storeVuelo(new Vuelo(new HashSet<ReservaVuelo>(),av,
                       av.getNumTur(), av.getNumBuss(),av.getNumEco(), datFmt.parse(request.getParameter("fecVuelo")),
                       hourFmt.parse(request.getParameter("horaSalida")).toString(),hourFmt.parse(request.getParameter("duracion")).toString(),
                       preTur, preEco, preBuss, dao.getAero(origen), dao.getAero(destino)));
        } else {
          vu.setAvion(av);
          vu.setNumTur(av.getNumTur());
          vu.setNumEco(av.getNumEco());
          vu.setNumBuss(av.getNumBuss());
          vu.setFecVuelo(datFmt.parse(request.getParameter("fecVuelo")));
          vu.setHoraSalida(hourFmt.parse(request.getParameter("horaSalida")).toString());
          vu.setDuracion(hourFmt.parse(request.getParameter("duracion")).toString());
          vu.setPreTur(preTur);
          vu.setPreBuss(preBuss);
          vu.setPreEco(preEco);
          vu.setAeroOrig(dao.getAero(origen));
          vu.setAeroDest(dao.getAero(destino));
          dao.storeVuelo(vu);
        }
        if(refVuelo != 0 && request.getParameter("ofertaVuelo") != null) {
          dao.storeOfertaVuelo(new OfertaVuelo(refVuelo, preEco, preBuss, preTur));
        }
        request.getRequestDispatcher("/gestionVuelos.jsp").forward(request, response);
      }
      
    } catch (ParseException ex) {
      Logger.getLogger(AltasManuales.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
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
