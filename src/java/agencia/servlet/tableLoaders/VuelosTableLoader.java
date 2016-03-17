/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.servlet.tableLoaders;

import agencia.modelo.Usuario;
import agencia.modelo.Vuelo;
import agencia.persistencia.AgenciaDAO;
import agencia.persistencia.GestionDAO;
import agencia.persistencia.UserDAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
 * @author David
 */
@WebServlet(name = "VuelosTableLoader", urlPatterns = {"/VuelosTableLoader"})
public class VuelosTableLoader extends HttpServlet {

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
        
        GestionDAO dao = new GestionDAO();
        AgenciaDAO daoCli = new AgenciaDAO();
        List<Vuelo> vueloList;
        boolean fst = true;
        
        request.getSession().setAttribute("result", null);
        if(request.getParameter("filtro") == null && request.getParameter("cliente") == null 
                && request.getParameter("ultimaHora") == null) {
            vueloList = dao.getVuelosGestion((String)request.getSession().getAttribute("filtros"));
            request.getSession().setAttribute("filtros", null);
            response.setContentType("text/html;charset=UTF-8");
            String data = "{ \"vuelos\":[";
            UserDAO userDao = new UserDAO();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName(); //get logged in username
            Usuario user = userDao.buscarPorUsername(name);
            SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
            
            for(Vuelo vuelo : vueloList) {
              if(user != null && user.getRefCompañia() != null && user.getRefCompañia().equals(vuelo.getAvion().getCompania().getRefCompañia())) {
                if(!fst) {
                    data +=",";
                }
                data +="[\"" + vuelo.getRefVuelo() + "\", \"" + vuelo.getAvion().getRefAvion() + "\", \"" 
                      + vuelo.getAeroOrig().getNombre() + "\", \"" + vuelo.getAeroDest().getNombre() + "\", \"" 
                      + vuelo.getFecVuelo() + "\", \"" + vuelo.getHoraSalida() + "\", \"" + vuelo.getDuracion()
                      + "\", \"" + vuelo.getNumTur() + "\", \"" + vuelo.getPreTur() + "\", \"" 
                      + vuelo.getNumEco() + "\", \"" + vuelo.getPreEco() + "\", \"" 
                      + vuelo.getNumBuss() + "\", \"" + vuelo.getPreBuss() + "\"]";
                fst = false;
              } else if(user == null || user.getRefCompañia() == null) {
                if(!fst) {
                    data +=",";
                }
                data +="[\"" + vuelo.getRefVuelo() + "\", \"" + vuelo.getAvion().getRefAvion() + "\", \"" 
                      + vuelo.getAeroOrig().getNombre() + "\", \"" + vuelo.getAeroDest().getNombre() + "\", \"" 
                      + vuelo.getFecVuelo() + "\", \"" + vuelo.getHoraSalida() + "\", \"" + vuelo.getDuracion()
                      + "\", \"" + vuelo.getNumTur() + "\", \"" + vuelo.getPreTur() + "\", \"" 
                      + vuelo.getNumEco() + "\", \"" + vuelo.getPreEco() + "\", \"" 
                      + vuelo.getNumBuss() + "\", \"" + vuelo.getPreBuss() + "\"]";
                fst = false;
              }
            }
            data += "]}";
            System.out.println("DATOS ---> " +data);
            response.setContentType("text/plain");
            System.out.println("Respuesta = " + data);
            response.getWriter().write(data);
        } else if (request.getParameter("cliente") == null && request.getParameter("ultimaHora") == null) {
            String filtro;
            filtro = request.getParameter("filtro"); 
            request.getSession().setAttribute("filtros", filtro);
        } else if(request.getParameter("filtro") == null && request.getParameter("cliente") != null) {
            vueloList = daoCli.getVuelosCli((String)request.getSession().getAttribute("filtros"), false);
            request.getSession().setAttribute("filtros", null);
            response.setContentType("text/html;charset=UTF-8");
            String data = "{ \"vuelosCli\":[";

            for(Vuelo vuelo : vueloList) {
                if(!fst) {
                    data +=",";
                }
                data +="[\"" + vuelo.getAeroOrig().getNombre() + "\", \"" + vuelo.getAeroDest().getNombre() + "\", \"" + vuelo.getFecVuelo() 
                      + "\", \"" + vuelo.getPreEco() + "\"]";
                fst = false;
            }
            data += "]}";
            System.out.println("DATOS ---> " +data);
            response.setContentType("text/plain");
            System.out.println("Respuesta = " + data);
            response.getWriter().write(data);
        } else if(request.getParameter("ultimaHora") == null) {
          String filtro;
          filtro = request.getParameter("filtro");
          request.getSession().setAttribute("filtros", filtro);
        } else if(request.getParameter("filtro") == null && request.getParameter("ultimaHora") != null) {
            vueloList = daoCli.getVuelosCli((String)request.getSession().getAttribute("filtros"), true);
            request.getSession().setAttribute("filtros", null);
            response.setContentType("text/html;charset=UTF-8");
            String data = "{ \"vuelosCli\":[";

            for(Vuelo vuelo : vueloList) {
                if(!fst) {
                    data +=",";
                }
                data +="[\"" + vuelo.getAeroOrig().getNombre() + "\", \"" + vuelo.getAeroDest().getNombre() + "\", \"" + vuelo.getFecVuelo() 
                      + "\", \"" + vuelo.getPreEco() + "\"]";
                fst = false;
            }
            data += "]}";
            System.out.println("DATOS ---> " +data);
            response.setContentType("text/plain");
            System.out.println("Respuesta = " + data);
            response.getWriter().write(data);
        } else {
          String filtro;
          filtro = request.getParameter("filtro");
          request.getSession().setAttribute("filtros", filtro);
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
