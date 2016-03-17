/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.persistencia;

import agencia.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ESa10969
 */
public class UserDAO implements ItfzUserDAO {
  public static SessionFactory sf;
  public static Session querySession;

  public SessionFactory getSessionFactory() {
    return sf;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sf = sessionFactory;
  }

    /**
     * Se obtiene la conexion a base de datos MySQL
     * @return Objeto conexion
     */
    private Session getSession () {        
        sf = new Configuration().configure().buildSessionFactory();
        return sf.openSession();
    }

  @Override
  public Usuario buscarPorUsername(String username) {
    List<Usuario> users = new ArrayList<Usuario>();
    Query qry;
    String query;
    try {
      querySession = getSession();
      query = "Select a from Usuario a where a.userName = ?";
      qry = querySession.createQuery(query);
      qry.setString(0, username);
      users = qry.list();
    } catch(Exception ex) {
      System.out.println(ex);
    }
    if (users.size() > 0) {
        /*int refUsuario = users.get(0)[0] == null ? 0 : Integer.valueOf(users.get(0)[0].toString());
        String nombre = users.get(0)[1] == null ? null : users.get(0)[1].toString();
        String apellidos = users.get(0)[2] == null ? null : users.get(0)[2].toString();
        String direccion = users.get(0)[3] == null ? null : users.get(0)[3].toString();
        String pais = users.get(0)[4] == null ? null : users.get(0)[4].toString();
        String poblacion = users.get(0)[5] == null ? null : users.get(0)[5].toString();
        int CP = users.get(0)[6] == null ? 0 : Integer.valueOf(users.get(0)[6].toString());
        String userName = users.get(0)[7] == null ? null : users.get(0)[7].toString();
        String pass = users.get(0)[8] == null ? null : users.get(0)[8].toString();
        String correo = users.get(0)[9] == null ? null : users.get(0)[9].toString();
        String refCompañia = users.get(0)[10] == null ? null : users.get(0)[10].toString();
        int userType = users.get(0)[11] == null ? 3 : Integer.valueOf(users.get(0)[11].toString());
        return new Usuario(refUsuario,nombre,apellidos,direccion, pais,poblacion,CP,userName,pass,correo,refCompañia,userType);*/
        return users.get(0);
       } else {
        return null;
    }
  }
}
