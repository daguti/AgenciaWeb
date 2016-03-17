/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.servlet.users;

import agencia.modelo.Usuario;
import agencia.persistencia.GestionDAO;
import agencia.persistencia.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "userStorage", urlPatterns = {"/userStorage"})
public class UserStorage extends HttpServlet { 
 
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
    UserDAO dao = new UserDAO();
    GestionDAO daG = new GestionDAO();
    Usuario user;
    try {
      /* TODO output your page here. You may use following sample code. */
      if(request.getParameter("delete") != null) {
        user = dao.buscarPorUsername(request.getParameter("username"));
        daG.deleteUser(user);
      } else if(request.getParameter("update") != null) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        user = dao.buscarPorUsername(name);
      } else if(request.getParameter("admin") != null) {
        user = dao.buscarPorUsername(request.getParameter("usernameOld"));
        if(user == null) user = new Usuario();
        user.setUserType(Integer.valueOf(request.getParameter("userType")));
        user.setRefCompañia(daG.getCompañíaID(request.getParameter("compName")).getRefCompañia());
      } else {
        user = new Usuario();
        user.setUserType(3);
      }
      if(request.getParameter("delete") == null) {
        user.setUserName(request.getParameter("username") == null ? request.getParameter("usernameOld") : request.getParameter("username"));
        user.setPass(request.getParameter("password"));
        user.setNombre(request.getParameter("nombre"));
        user.setApellidos(request.getParameter("apellidos"));
        user.setDireccion(request.getParameter("direccion"));
        user.setPoblacion(request.getParameter("poblacion"));
        user.setPais(request.getParameter("pais"));
        user.setCorreo(request.getParameter("email"));
        daG.saveOrUpdateUser(user);
      }
      if(request.getParameter("admin") != null) {
        request.getRequestDispatcher("/gestionAdminComp.jsp").forward(request, response);
      } else {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
      }
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
