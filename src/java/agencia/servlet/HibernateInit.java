/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 * @author Anabel
 */
public class HibernateInit implements ServletContextListener {

    // Este metodo captura el evento al arrancar la aplicacion
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext aplicacion = sce.getServletContext();
        HibernateUtil util = new HibernateUtil();
        System.out.println(">>>><< Initioooooo <<<<<<<<<<<<<<<<<<<<<<<");
    }

    // Este metodo captura el evento al detener la aplicacion
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Se va a parar la aplicacion <<<<<<<<<<<<<<<<<<<<<<<");
    }
}
