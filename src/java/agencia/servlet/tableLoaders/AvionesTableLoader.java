/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.servlet.tableLoaders;

import agencia.modelo.Avion;
import agencia.modelo.Usuario;
import agencia.persistencia.GestionDAO;
import agencia.persistencia.UserDAO;
import java.io.IOException;
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
@WebServlet(name = "AvionesTableLoader", urlPatterns = {"/AvionesTableLoader"})
public class AvionesTableLoader extends HttpServlet {

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
        List<Avion> avionList;
        boolean fst = true;
        UserDAO userDao = new UserDAO();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        Usuario user = userDao.buscarPorUsername(name);
        
        request.getSession().setAttribute("result", null);
        if(request.getParameter("filtro") == null) {
            avionList = dao.getAviones((String)request.getSession().getAttribute("filtros"));
            request.getSession().setAttribute("filtros", null);
            response.setContentType("text/html;charset=UTF-8");
            String data = "{ \"aviones\":[";

            for(Avion avion : avionList) {
              if(avion.getCompania().getRefCompañia().equals(user.getRefCompañia())) {
                if(!fst) {
                    data +=",";
                }
                data +="[\"" + avion.getRefAvion() + "\", \"" + avion.getDescripcion().replaceAll("Ã±", "ñ") + "\", \"" + avion.getNumTur() 
                        + "\", \"" + avion.getNumEco() + "\", \"" + avion.getNumBuss() + "\", \"" + avion.getCompania().getNombre() + "\"]";
                fst = false;
              }
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
