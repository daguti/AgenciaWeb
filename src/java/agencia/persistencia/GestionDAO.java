/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.persistencia;

import agencia.modelo.Aeropuerto;
import agencia.modelo.Avion;
import agencia.modelo.CompaniaAerea;
import agencia.modelo.CompaniaHotelera;
import agencia.modelo.DisponibilidadHotel;
import agencia.modelo.Hotel;
import agencia.modelo.OfertaHotel;
import agencia.modelo.OfertaVuelo;
import agencia.modelo.ReservaHotel;
import agencia.modelo.ReservaVuelo;
import agencia.modelo.Usuario;
import agencia.modelo.Vuelo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author David
 */
public class GestionDAO implements ItfzGestionDAO {
    public static SessionFactory sf;
    public static Session querySession;
    
    
    /**
     * Se obtiene la conexion a base de datos MySQL
     * @return Objeto conexion
     */
    private Session getSession () {        
        sf = new Configuration().configure().buildSessionFactory();
        return sf.openSession();
    }

    @Override
    public String getCompForTable(String compania) {
      // Definicion de variables
        String query;
        Query qry;
        List<CompaniaAerea> aeroList;
        List<CompaniaHotelera> hoteleraList;
        String data = "{ \"companias\":[";
        boolean fst = true;
        //Obtenemos la Session
        querySession = getSession();
        //Formamos la query
        
        try {
            if(compania == null) {
              query = "Select a from CompaniaAerea a";
              qry = querySession.createQuery(query);
            } else {
              query = "Select a from CompaniaAerea a where a.nombre = :compania";
              qry = querySession.createQuery(query);
              qry.setString("compania", compania);
            }
            
            aeroList = qry.list();
            
            for(CompaniaAerea aero : aeroList) {
                if(!fst) {
                    data +=",";
                }
                data +="[\"" + aero.getRefCompañia().replaceAll("Ã±", "ñ") + "\", \"" + aero.getNombre().replaceAll("Ã±", "ñ") + "\", \"" 
                     + aero.getPais().replaceAll("Ã±", "ñ") + "\", \"" + "1" + "\"]";
                fst = false;
            }
            if(compania == null) {
              query = "Select a from CompaniaHotelera a";
              qry = querySession.createQuery(query);
            } else {
              query = "Select a from CompaniaHotelera a where a.nombre = :compania";
              qry = querySession.createQuery(query);
              qry.setString("compania", compania);
            }
            data += ",";
            hoteleraList = qry.list();
            fst = true;
            for(CompaniaHotelera hotelera : hoteleraList) {
                if(!fst) {
                    data +=",";
                }
                data +="[\"" + hotelera.getRefCompañia().replaceAll("Ã±", "ñ") + "\", \"" + hotelera.getNombre().replaceAll("Ã±", "ñ") + "\", \"" 
                     + hotelera.getPais().replaceAll("Ã±", "ñ") + "\", \"" + "2" + "\"]";
                fst = false;
            }
            data += "]}";
            return data;
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    

    @Override
    public CompaniaAerea getCompañíaID(String compañia) {
        // Definicion de variables
        String query;
        Query qry;
        List<CompaniaAerea> aerea;
        //Obtenemos la Session
        querySession = getSession();
        //Falta filtrar por compañia usuario
        query = "Select a from CompaniaAerea a where a.nombre = :compania";
        
        try {
            qry = querySession.createQuery(query);
            qry.setString("compania", compañia);
            aerea = qry.list();
            
            return aerea.get(0);
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    
    @Override
    public Vuelo getVueloByRef(int refVuelo) {
        // Definicion de variables
        String query;
        Query qry;
        List<Vuelo> vuelo;
        //Obtenemos la Session
        querySession = getSession();
        //Falta filtrar por compañia usuario
        query = "Select a from Vuelo a where a.refVuelo = :refVuelo";
        
        try {
            qry = querySession.createQuery(query);
            qry.setInteger("refVuelo", refVuelo);
            vuelo = qry.list();
            
            if(vuelo.size() > 0) {
              Vuelo vu = vuelo.get(0);
              Hibernate.initialize(vu.getAeroDest());
              Hibernate.initialize(vu.getAeroOrig());
              return vu;
            }
            else return null;
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    @Override
    public CompaniaAerea getCompañíaName(String refComp) {
        // Definicion de variables
        String query;
        Query qry;
        List<CompaniaAerea> aerea;
        //Obtenemos la Session
        querySession = getSession();
        //Falta filtrar por compañia usuario
        query = "Select a from CompaniaAerea a where a.refCompañia = :refComp";
        
        try {
            qry = querySession.createQuery(query);
            qry.setString("refComp", refComp);
            aerea = qry.list();
            if(aerea.size() > 0) {
                return aerea.get(0);
            } else {
                return null;
            }
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }

    @Override
    public CompaniaHotelera getHoteleraByRef(String refComp) {
        // Definicion de variables
        String query;
        Query qry;
        List<CompaniaHotelera> hotelera;
        //Obtenemos la Session
        querySession = getSession();
        //Falta filtrar por compañia usuario
        query = "Select a from CompaniaHotelera a where a.refCompañia = :refComp";
        
        try {
            qry = querySession.createQuery(query);
            qry.setString("refComp", refComp);
            hotelera = qry.list();
            
            if(hotelera.size() > 0) return hotelera.get(0);
            else return null;
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    
    @Override
    public CompaniaHotelera getHoteleraByCompania(String compania) {
        // Definicion de variables
        String query;
        Query qry;
        List<CompaniaHotelera> hotelera;
        //Obtenemos la Session
        querySession = getSession();
        //Falta filtrar por compañia usuario
        query = "Select a from CompaniaHotelera a where a.nombre = :compania";
        
        try {
            qry = querySession.createQuery(query);
            qry.setString("compania", compania);
            hotelera = qry.list();
            
            return hotelera.get(0);
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    @Override
    public void storeAvion(Avion avion) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();

            avion.setVuelosList(new HashSet<Vuelo>());
            transaction.begin();
            session.saveOrUpdate(avion);
            transaction.commit();
        } catch (HibernateException ex) {
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }
    
    @Override
    public void storeOfertaVuelo(OfertaVuelo ofertaVuelo) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();

            transaction.begin();
            session.saveOrUpdate(ofertaVuelo);
            transaction.commit();
        } catch (HibernateException ex) {
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }
    
    @Override
    public int storeVuelo(Vuelo vuelo) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();

            vuelo.setReservasList(new HashSet<ReservaVuelo>());
            transaction.begin();
            session.saveOrUpdate(vuelo);
            transaction.commit();
            return Integer.valueOf((session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).toString());
        } catch (HibernateException ex) {
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
        return 0;
    }
    
    @Override
    public void storeHotel(Hotel hotel, boolean update) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();

            hotel.setReservasList(new HashSet<ReservaHotel>());
            transaction.begin();
            session.saveOrUpdate(hotel);
            transaction.commit();
            if(!update) storeDisponibilidad(hotel);
        } catch (HibernateException ex) {
          if(transaction != null) transaction.rollback();
          Logger.getLogger(GestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
          Logger.getLogger(GestionDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
            if(session != null) session.close();
        }
    }
    
    @Override
    public void storeOfertaHotel(OfertaHotel ofertaHotel) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();

            transaction.begin();
            session.saveOrUpdate(ofertaHotel);
            transaction.commit();
        } catch (HibernateException ex) {
          if(transaction != null) transaction.rollback();
          Logger.getLogger(GestionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(session != null) session.close();
        }
    }
    
    private void storeDisponibilidad(Hotel hotel) throws ParseException {
      //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        DisponibilidadHotel dispHot;
        Calendar cal = Calendar.getInstance();
        Date dat = cal.getTime();
        
        try {
            //Obtenemos la Sesion
            session = getSession(); 
            Query qry;
            qry = session.createQuery("select a from Hotel a where a.nombre = :nombre");
            qry.setString("nombre", hotel.getNombre());
            hotel = (Hotel)qry.list().get(0);
            
            while(dat.before(new SimpleDateFormat("dd-MM-yyyy").parse("12-12-2016"))) {
              dispHot = new DisponibilidadHotel();
              dispHot.setRefHotel(hotel.getRefHotel());
              dispHot.setFecha(dat);
              dispHot.setIndDisp(hotel.getHabInd());
              dispHot.setDobDisp(hotel.getHabDob());
              dispHot.setSupDisp(hotel.getHabSup());
              //Obtenemos la transaccion para la sesion
              transaction = session.getTransaction(); 
              transaction.begin();
              session.saveOrUpdate(dispHot);
              transaction.commit();
              cal.add(Calendar.DATE, 1);
              dat = cal.getTime(); 
            }
            
        } catch (HibernateException ex) {
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }
    
    @Override
    public void storeAdmin(Usuario user) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException ex) {
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }
    @Override
    public void storeAeropuerto(Aeropuerto aero) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();
            aero.setVuelosList(new HashSet<Vuelo>());
            transaction.begin();
            session.saveOrUpdate(aero);
            transaction.commit();
        } catch (HibernateException ex) {
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }
    @Override
    public void storeCompaniaAerea(CompaniaAerea compAerea) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(compAerea);
            transaction.commit();
        } catch (HibernateException ex) {
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }
    @Override
    public void storeCompaniaHotelera(CompaniaHotelera compHotelera) {
        //Definicion de variables
    	Session session = null;
        Transaction transaction = null;
        
        try {
            //Obtenemos la Sesion
            session = getSession();
            //Obtenemos la transaccion para la sesion
            transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(compHotelera);
            transaction.commit();
        } catch (HibernateException ex) {
            if(transaction != null) transaction.rollback();
        } finally {
            if(session != null) session.close();
        }
    }
    @Override
    public List<Avion> getAviones(String filtro) {
        // Definicion de variables
        String query;
        Query qry;
        List<Avion> avionList;
        //Obtenemos la Session
        querySession = getSession();
        
        
        try {
            if(filtro != null) {
              String[] filtros = filtro.split("$$");
              
                qry = querySession.createQuery(getAvionQuery(filtros));
                setAvionQueryFilters(qry, filtros);
            } else {
                query = "Select a from Avion a";
                qry = querySession.createQuery(query);
            }
            
            avionList = qry.list();
            for(Avion av : avionList) Hibernate.initialize(av.getCompania());
            return avionList;
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    
    private String getAvionQuery(String[] filtros) {
      String query = "Select a from Avion a where ";
      
      if(filtros[0] != null && !filtros[0].equals("")) {
        query += "a.descripcion = :descripcion ";
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        query += "and a.numTur >= :desdeTur ";
      }
      if(filtros[2] != null && !filtros[2].equals("")) {
        query += "and a.numTur <= :hastaTur ";
      }
      if(filtros[3] != null && !filtros[3].equals("")) {
        query += "and a.numEco >= :desdeEco ";
      }
      if(filtros[4] != null && !filtros[4].equals("")) {
        query += "and a.numEco <= :hastaEco ";
      }
      if(filtros[5] != null && !filtros[5].equals("")) {
        query += "and a.numBuss >= :desdeBuss ";
      }
      if(filtros[6] != null && !filtros[6].equals("")) {
        query += "and a.numBuss <= :hastaBuss";
      }
      return query;
    }
    
    private void setAvionQueryFilters(Query qry, String[] filtros) {
      if(filtros[0] != null && !filtros[0].equals("")) {
        qry.setString("descripcion", filtros[0]);
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        qry.setInteger("desdeTur", Integer.valueOf(filtros[1]));
      }
      if(filtros[2] != null && !filtros[2].equals("")) {
        qry.setInteger("hastaTur", Integer.valueOf(filtros[2]));
      }
      if(filtros[3] != null && !filtros[3].equals("")) {
        qry.setInteger("desdeEco", Integer.valueOf(filtros[3]));
      }
      if(filtros[4] != null && !filtros[4].equals("")) {
        qry.setInteger("hastaEco", Integer.valueOf(filtros[4]));
      }
      if(filtros[5] != null && !filtros[5].equals("")) {
        qry.setInteger("desdeBuss", Integer.valueOf(filtros[5]));
      }
      if(filtros[6] != null && !filtros[6].equals("")) {
        qry.setInteger("hastaBuss", Integer.valueOf(filtros[6]));
      }
    }
    
    @Override
    public List<Hotel> getHotelesGestion(String filtro) {
        // Definicion de variables
        String query;
        Query qry;
        List<Hotel> hotelList;
        //Obtenemos la Session
        querySession = getSession();
        
        
        try {
            if(filtro != null) {
              String[] filtros = filtro.split("$$");
              
                qry = querySession.createQuery(getGestionHotelesQuery(filtros));
                setGestionHotelesQueryFilters(qry, filtros);
            } else {
                query = "Select a from Hotel a";
                qry = querySession.createQuery(query);
            }
            
            hotelList = qry.list();
            for(Hotel ht : hotelList) Hibernate.initialize(ht.getCompañia());
            return hotelList;
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    
    private String getGestionHotelesQuery(String[] filtros) {
      String query = "Select a from Hotel a where ";
      
      if(filtros[0] != null && !filtros[0].equals("")) {
        query += "a.ciudad = :ciudad ";
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        query += "a.nombre = :nombre ";
      }
      
      return query;
    }
    
    private void setGestionHotelesQueryFilters(Query qry, String[] filtros) {
      if(filtros[0] != null && !filtros[0].equals("")) {
        qry.setString("ciudad", filtros[0]);
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        qry.setString("nombre", filtros[1]);
      }
    }
    
    @Override
    public Avion getAvion(int refAvion) {
        // Definicion de variables
        String query;
        Query qry;
        List<Avion> avionList;
        //Obtenemos la Session
        querySession = getSession();
        
        try {
              
            query = "Select a from Avion a where a.refAvion = :refAvion";
            qry = querySession.createQuery(query);
            qry.setInteger("refAvion", refAvion);
            avionList = qry.list();
            
            return avionList.get(0);
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    
    public List<Vuelo> getVuelosInventario(String compania) {
      String query;
      Query qry;
      List<Vuelo> vueloList;
      //Obtenemos la Session
      querySession = getSession();

      try {

          query = "Select a from Vuelo a, Avion b where a.avion.refAvion = b.refAvion and b.compania.refCompañia = :compania";
          qry = querySession.createQuery(query);
          qry.setString("compania", compania);
          vueloList = qry.list();

          for(Vuelo vuelo : vueloList) {
            Hibernate.initialize(vuelo.getAvion());
            Hibernate.initialize(vuelo.getAeroDest());
            Hibernate.initialize(vuelo.getAeroOrig()); 
          }

          return vueloList;
      } catch (HibernateException ex) {
          System.out.println("ERROR DE SQL");
          System.out.println(ex);
      } finally {
          querySession.close();
          sf.close();
      }
      return null;
    }
    
    public List<DisponibilidadHotel> getDisponiHotelesInventario(String compania) {
      String query;
      Query qry;
      List<DisponibilidadHotel> dispList;
      //Obtenemos la Session
      querySession = getSession();

      try {

          query = "Select a from DisponibilidadHotel a, Hotel b where a.refHotel = b.refHotel and b.compañia.refCompañia = :compania";
          qry = querySession.createQuery(query);
          qry.setString("compania", compania);
          dispList = qry.list();

          return dispList;
      } catch (HibernateException ex) {
          System.out.println("ERROR DE SQL");
          System.out.println(ex);
      } finally {
          querySession.close();
          sf.close();
      }
      return null;
    }
    
    @Override
    public Hotel getHotel(String nombre) {
        // Definicion de variables
        String query;
        Query qry;
        List<Hotel> hotelList;
        //Obtenemos la Session
        querySession = getSession();
        
        try {
              
            query = "Select a from Hotel a where a.nombre = :nombre";
            qry = querySession.createQuery(query);
            qry.setString("nombre", nombre);
            hotelList = qry.list();
            
            return hotelList.get(0);
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    
    @Override
    public Aeropuerto getAero(String nombre) {
        // Definicion de variables
        String query;
        Query qry;
        List<Aeropuerto> aeroList;
        //Obtenemos la Session
        querySession = getSession();
        
        
        try {
              
            query = "Select a from Aeropuerto a where a.nombre = :nombre";
            qry = querySession.createQuery(query);
            qry.setString("nombre", nombre);
            aeroList = qry.list();
            if(aeroList.size() > 0) return aeroList.get(0);
            else return null;
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
    @Override
    public Aeropuerto getAeroByCod(String COD) {
        // Definicion de variables
        String query;
        Query qry;
        List<Aeropuerto> aeroList;
        //Obtenemos la Session
        querySession = getSession();
        
        
        try {
              
            query = "Select a from Aeropuerto a where a.codigo = :cod";
            qry = querySession.createQuery(query);
            qry.setString("cod", COD);
            aeroList = qry.list();
            if(aeroList.size() > 0) return aeroList.get(0);
            else return null;
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
    }
  @Override
  public List<Aeropuerto> getAeroForTable(String filtro) {
    // Definicion de variables
    String query;
    Query qry;
    List<Aeropuerto> aeroList;
    //Obtenemos la Session
    querySession = getSession();


    try {
        if(filtro != null) {
          String[] filtros = filtro.split("$$");
            qry = querySession.createQuery(getAeropuertosQuery(filtros));
            setAeropuertoQueryFilters(qry, filtros);
        } else {
            query = "Select a from Aeropuerto a";
            qry = querySession.createQuery(query);
        }

        aeroList = qry.list();

        return aeroList;
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }
    return null;
  }
  
  private String getAeropuertosQuery(String[] filtros) {
    String query = "Select a from Aeropuerto a where ";
      
      if(filtros[0] != null && !filtros[0].equals("")) {
        query += "a.nombre = :nombre ";
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        query += "and a.ciudad = :ciudad ";
      }
      return query;
  }
  private void setAeropuertoQueryFilters(Query qry, String[] filtros) {
      if(filtros[0] != null && !filtros[0].equals("")) {
        qry.setString("nombre", filtros[0]);
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        qry.setString("ciudad", filtros[1]);
      }
    }
  
  @Override
  public List<Vuelo> getVuelosGestion(String filtro) {
    // Definicion de variables
    String query;
    Query qry;
    List<Vuelo> vueloList;
    //Obtenemos la Session
    querySession = getSession();


    try {
        if(filtro != null) {
          String[] filtros = filtro.split("$$");
          qry = querySession.createQuery(getVueloQuery(filtros));
          setVueloQueryFilters(qry, filtros);
        } else {
          query = "Select a from Vuelo a";
          qry = querySession.createQuery(query);
        }

        vueloList = qry.list();
        for(Vuelo vuelo : vueloList) {
          Hibernate.initialize(vuelo.getAvion());
          Hibernate.initialize(vuelo.getAvion().getCompania());
          Hibernate.initialize(vuelo.getAeroDest());
          Hibernate.initialize(vuelo.getAeroOrig());
        }
        return vueloList;
    } catch (HibernateException ex) {
        Logger.getLogger(GestionDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(GestionDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        querySession.close();
        sf.close();
    }
    return null;
  }
  private String getVueloQuery(String[] filtros) {
    String query = "Select a from Vuelo a ";
      
      if(filtros[0] != null && !filtros[0].equals("")) {
        query += "and a.aeroDest = :dest ";
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        query += "and a.aeroOrig = :orig ";
      } 
      if(filtros[2] != null && !filtros[2].equals("")) {
        query += "and a.fecVuelo = :fecVuelo ";
      }
      return query;
  }
  private void setVueloQueryFilters(Query qry, String[] filtros) throws ParseException {
    GestionDAO dao = new GestionDAO();
    
    if(filtros[0] != null && !filtros[0].equals("")) {
      qry.setString("dest", filtros[0]);
    }
    if(filtros[1] != null && !filtros[1].equals("")) {
      qry.setString("orig", filtros[1]);
    }
    if(filtros[2] != null && !filtros[2].equals("")) {

      qry.setDate("fecVuelo", new SimpleDateFormat().parse(filtros[2]));
    }
  }
  
    @Override
   public List<Usuario> getAdministradores(String filtro) {
    // Definicion de variables
    String query;
    Query qry;
    List<Usuario> adminList;
    //Obtenemos la Session
    querySession = getSession();


    try {
        if(filtro != null) {
          String[] filtros = filtro.split("$$");
          qry = querySession.createQuery(getAdminQuery(filtros));
          setAdminQueryFilters(qry, filtros);
        } else {
          query = "Select a from Usuario a where (a.userType = 1 or a.userType = 2)";
          qry = querySession.createQuery(query);
        }

        adminList = qry.list();

        return adminList;
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }
    return null;
  }
  
  private String getAdminQuery(String[] filtros) {
    String query = "Select a from Usuario a where (a.userType = 1 or a.userType = 2) ";
      
      if(filtros[0] != null && !filtros[0].equals("")) {
        query += "and a.nombre = :nombre ";
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        query += "and a.apellidos = :apellidos ";
      } 
      if(filtros[2] != null && !filtros[2].equals("")) {
        query += "and a.refCompania = :refCompania ";
      }
      return query;
  }
  private void setAdminQueryFilters(Query qry, String[] filtros) {
    GestionDAO dao = new GestionDAO();
    
    if(filtros[0] != null && !filtros[0].equals("")) {
      qry.setString("nombre", filtros[0]);
    }
    if(filtros[1] != null && !filtros[1].equals("")) {
      qry.setString("apellidos", filtros[1]);
    }
    if(filtros[2] != null && !filtros[2].equals("")) {

      qry.setString("compania", dao.getCompañíaID(filtros[2]).getRefCompañia());
    }
  }
  
   @Override
  public void saveOrUpdateUser(Usuario user) {
    //Variable definition
    Transaction tr;
    
    querySession = getSession();
    tr = querySession.beginTransaction();
    querySession.saveOrUpdate(user);
    tr.commit();
  }

  @Override
  public Hotel getHotelByRef(int refHotel) {
    // Definicion de variables
    String query;
    Query qry;
    List<Hotel> hotelList;
    //Obtenemos la Session
    querySession = getSession();

    try {

        query = "Select a from Hotel a where a.refHotel = :refHotel";
        qry = querySession.createQuery(query);
        qry.setInteger("refHotel", refHotel);
        hotelList = qry.list();

        return hotelList.get(0);
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }
    return null;
  }
  @Override
  public void deleteUser(Usuario user) {
    //Obtenemos la Session
    querySession = getSession();

    try {
      Transaction trans = querySession.getTransaction();
      trans.begin();
      querySession.delete(user);
      trans.commit();
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }
  }
  @Override
  public void deleteAeropuerto(Aeropuerto aero) {
    //Obtenemos la Session
    querySession = getSession();

    try {
      Transaction trans = querySession.getTransaction();
      trans.begin();
      querySession.delete(aero);
      trans.commit();
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }
  }
  @Override
  public void deleteAvion(Avion avion) {
    //Obtenemos la Session
    querySession = getSession();

    try {
      Transaction trans = querySession.getTransaction();
      trans.begin();
      querySession.delete(avion);
      trans.commit();
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }
  }

  @Override
  public void deleteCompaniaHotelera(CompaniaHotelera hotelera) {
    //Obtenemos la Session
    querySession = getSession();

    try {
      Transaction trans = querySession.getTransaction();
      trans.begin();
      querySession.delete(hotelera);
      trans.commit();
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }
  }

  @Override
  public void deleteComapniaAerea(CompaniaAerea aerea) {
    //Obtenemos la Session
    querySession = getSession();

    try {
      Transaction trans = querySession.getTransaction();
      trans.begin();
      querySession.delete(aerea);
      trans.commit();
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }  
  }

  @Override
  public void deleteHotel(Hotel hotel) {
    //Obtenemos la Session
    querySession = getSession();

    try {
      Transaction trans = querySession.getTransaction();
      trans.begin();
      querySession.delete(hotel);
      trans.commit();
    } catch (HibernateException ex) {
        System.out.println("ERROR DE SQL");
        System.out.println(ex);
    } finally {
        querySession.close();
        sf.close();
    }    }
}

