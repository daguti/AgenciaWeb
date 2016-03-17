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
import agencia.modelo.ReservaHotel;
import agencia.modelo.ReservaVuelo;
import agencia.modelo.Reservas;
import agencia.modelo.Usuario;
import agencia.modelo.Viaje;
import agencia.modelo.Vuelo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author David
 */
public class AgenciaDAO implements ItfzAgenciaDAO {
    public static SessionFactory sf;
    public static Session querySession;
    
    @Override
    public String getAeropuertos() {
        // Definicion de variables
        String query;
        Query qry;
        List<Aeropuerto> aeroList;
        String aeroJson ="";
        //Obtenemos la Session
        querySession = getSession();
        //Formamos la query
        query = "Select a from Aeropuerto a";
        
        try {
            qry = querySession.createQuery(query);
            aeroList = qry.list();
            for(Aeropuerto aero : aeroList) {
                aeroJson += "(" + aero.getCodigo() + ")" + aero.getCiudad() + ", ";
            }
            aeroJson = aeroJson.substring(0, aeroJson.lastIndexOf(","));
            return aeroJson;
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
    public String getCiudades() {
        // Definicion de variables
        String query;
        Query qry;
        List<Aeropuerto> aeroList;
        List<Hotel> hotelList;
        
        String aeroJson ="";
        //Obtenemos la Session
        querySession = getSession();
        //Formamos la query
        query = "Select a from Aeropuerto a";
        
        try {
            qry = querySession.createQuery(query);
            aeroList = qry.list();
            for(Aeropuerto aero : aeroList) {
                aeroJson += aero.getCiudad() + ", ";
            }
            query = "Select a from Hotel a";
            qry = querySession.createQuery(query);
            hotelList = qry.list();
            for(Hotel hotel : hotelList) {
                aeroJson += hotel.getCiudad() + ", ";
            }
            aeroJson = aeroJson.substring(0, aeroJson.lastIndexOf(","));
            return aeroJson;
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
    public String getHoteles() {
        // Definicion de variables
        String query;
        Query qry;
        List<Hotel> hotelList;
        String aeroJson ="";
        //Obtenemos la Session
        querySession = getSession();
        //Formamos la query
        query = "Select a from Hotel a";
        
        try {
            qry = querySession.createQuery(query);
            hotelList = qry.list();
            for(Hotel hotel : hotelList) {
                aeroJson += hotel.getNombre() + ", ";
            }
            aeroJson = aeroJson.substring(0, aeroJson.lastIndexOf(","));
            return aeroJson;
        } catch (HibernateException ex) {
            System.out.println("ERROR DE SQL");
            System.out.println(ex);
        } finally {
            querySession.close();
            sf.close();
        }
        return null;
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
    public String getCompañias() {
        // Definicion de variables
        String query;
        Query qry;
        List<CompaniaAerea> aeroList;
        List<CompaniaHotelera> hoteleraList;
        String compJson ="";
        //Obtenemos la Session
        querySession = getSession();
        
        try {
            query = "Select a from CompaniaAerea a";
            qry = querySession.createQuery(query);
            
            aeroList = qry.list();
            
            for(CompaniaAerea aero : aeroList) {
                compJson += aero.getNombre() + ", ";
            }
            query = "Select a from CompaniaHotelera a";
            qry = querySession.createQuery(query);
            
            hoteleraList = qry.list();
            for(CompaniaHotelera hotelera : hoteleraList) {
                compJson += hotelera.getNombre() + ", ";
            }
            compJson = compJson.substring(0, compJson.lastIndexOf(","));
            return compJson;
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
    public String getNombres() {
        // Definicion de variables
        String query;
        Query qry;
        List<Usuario> userList;
        String compJson ="";
        //Obtenemos la Session
        querySession = getSession();
        //Formamos la query (Admin --> 0; Comp Aerea --> 1; Comp Hot --> 2; User --> 3;
        query = "Select a from Usuario a where a.userType = 1 or a.userType = 2";
        
        try {
            qry = querySession.createQuery(query);
            userList = qry.list();
            
            for(Usuario user : userList) {
                compJson += user.getNombre() + ", ";
            }
            compJson = compJson.substring(0, compJson.lastIndexOf(","));
            return compJson;
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
    public String getApellidos() {
        // Definicion de variables
        String query;
        Query qry;
        List<Usuario> userList;
        String compJson ="";
        //Obtenemos la Session
        querySession = getSession();
        //Formamos la query (Admin --> 0; Comp Aerea --> 1; Comp Hot --> 2; User --> 3;
        query = "Select a from Usuario a where a.userType = 1 or a.userType = 2";
        
        try {
            qry = querySession.createQuery(query);
            userList = qry.list();
            
            for(Usuario user : userList) {
                compJson += user.getApellidos() + ", ";
            }
            compJson = compJson.substring(0, compJson.lastIndexOf(","));
            return compJson;
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
    public String getDescAvion() {
        // Definicion de variables
        String query;
        Query qry;
        List<Avion> avionList;
        String compJson ="";
        //Obtenemos la Session
        querySession = getSession();
        //Falta filtrar por compañia usuario
        query = "Select a from Avion a";
        
        try {
            qry = querySession.createQuery(query);
            avionList = qry.list();
            
            for(Avion avion : avionList) {
                compJson += avion.getDescripcion() + ", ";
            }
            compJson = compJson.substring(0, compJson.lastIndexOf(","));
            return compJson;
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
    public List<Vuelo> getVuelosCli(String filtro, boolean ultimaHora) {
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
          if(ultimaHora) {
              query += " where a.fecVuelo > :desde and a.fecVuelo < :hasta";
          }
          qry = querySession.createQuery(query);
          if(ultimaHora) {
              Calendar cal = Calendar.getInstance();
              qry.setDate("desde", cal.getTime());
              cal.add(Calendar.DATE, 7);
              qry.setDate("hasta", cal.getTime());
          }
        }

        vueloList = qry.list();
        for(Vuelo vuelo : vueloList) {
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
        if(!sf.isClosed())sf.close();
    }
    return null;
  }
  private String getVueloQuery(String[] filtros) {
    String query = "Select a from Vuelo a ";
      
      if(filtros[0] != null && !filtros[0].equals("")) {
        query += "and a.aeroOrig = :orig ";
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        query += "and a.aeroDest = :dest ";
      } 
      if(filtros[2] != null && !filtros[2].equals("")) {
        query += "and a.fecVuelo = :fecVuelo ";
      }
      return query;
  }
  private void setVueloQueryFilters(Query qry, String[] filtros) throws ParseException {    
    if(filtros[0] != null && !filtros[0].equals("")) {
      qry.setString("orig", filtros[0]);
    }
    if(filtros[1] != null && !filtros[1].equals("")) {
      qry.setString("dest", filtros[1]);
    }
    if(filtros[2] != null && !filtros[2].equals("")) {
      qry.setDate("fecVuelo", new SimpleDateFormat().parse(filtros[2]));
    }
  }
  @Override
    public List<Hotel> getHotelesCli(String filtro, boolean ultimaHora) {
    // Definicion de variables
    String query;
    Query qry;
    List<Hotel> hotelList;
    //Obtenemos la Session
    querySession = getSession();


    try {
        if(filtro != null) {
          if(ultimaHora){
              return getHotelesViaje(filtro);
          }
          String[] filtros = filtro.split("$$");
          qry = querySession.createQuery(getHotelQuery(filtros));
          setHotelQueryFilters(qry, filtros);
        } else {
          query = "Select a from Hotel a";
          if(ultimaHora){
              return getHotelesViaje(null);
          }
          qry = querySession.createQuery(query);
        }

        hotelList = qry.list();

        return hotelList;
    } catch (HibernateException ex) {
        Logger.getLogger(GestionDAO.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(GestionDAO.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        if(!ultimaHora) {
            querySession.close();
            sf.close();
        }
    }
    return null;
  }
  private String getHotelQuery(String[] filtros) {
    String query = "Select a from Hotel a ";
      
      if(filtros[0] != null && !filtros[0].equals("")) {
        query += "and a.ciudad = :destino ";
      }
      if(filtros[1] != null && !filtros[1].equals("")) {
        query += "and a.refHotel in (select b from DisponibilidadHotel b where (b.insDisp > 0 or "
            + "b.dobDisp > 0 or b.supDisp > 0) and b.fecha > :desde ";
      } 
      if(filtros[2] != null && !filtros[2].equals("")) {
        query += "and b.fecha < = :hasta) ";
      }
      return query;
  }
  private void setHotelQueryFilters(Query qry, String[] filtros) throws ParseException {    
    if(filtros[0] != null && !filtros[0].equals("")) {
      qry.setString("destino", filtros[0]);
    }
    if(filtros[1] != null && !filtros[1].equals("")) {
      qry.setDate("desde", new SimpleDateFormat().parse(filtros[1]));
    }
    if(filtros[2] != null && !filtros[2].equals("")) {
      qry.setDate("hasta", new SimpleDateFormat().parse(filtros[2]));
    }
  }
  
    @Override
  public List<Viaje> getViajes(String filtro) {
    List<Vuelo> vueloList;
    List<Hotel> hotelList;
    List<Viaje> viajesList = new ArrayList<Viaje>();
    Viaje viaje;
    try {
      hotelList = getHotelesViaje(filtro);
      if(!hotelList.isEmpty()) {
        if(filtro != null) {
          String[] filtros = filtro.split("$$");
          for(Hotel hotel : hotelList) {
            int all = 0;
            Double precio = 0.0;
            viaje = new Viaje();
            viaje.setDestino(hotel.getCiudad());
            viaje.setHotelNom(hotel.getNombre());
            viaje.setFechaSalida(new SimpleDateFormat().parse(filtros[2]));
            viaje.setFechaVuelta(new SimpleDateFormat().parse(filtros[3]));
            vueloList = getVuelosViaje(filtros[0], viaje);
            Viaje viaAdd = new Viaje(viaje.getHotelNom(), viaje.getCategoria(), viaje.getOrigen(), 
                                     viaje.getDestino(), viaje.getFechaSalida(), viaje.getFechaVuelta());
            
            getViajesList(vueloList, viaAdd, viaje, viajesList, hotel);
          }
        } else {
          Calendar cal = Calendar.getInstance();
          Date ida = cal.getTime();
          cal.add(Calendar.DATE, 7);
          Date vuelta = cal.getTime();
          for(Hotel hotel : hotelList) {
            viaje = new Viaje();
            viaje.setDestino(hotel.getCiudad());
            viaje.setHotelNom(hotel.getNombre());
            viaje.setFechaSalida(ida);
            viaje.setFechaVuelta(vuelta);
            vueloList = getVuelosViaje(null, viaje); 
            Viaje viaAdd = new Viaje(viaje.getHotelNom(), viaje.getCategoria(), viaje.getOrigen(), 
                                     viaje.getDestino(), viaje.getFechaSalida(), viaje.getFechaVuelta());
            getViajesList(vueloList, viaAdd, viaje, viajesList, hotel);
          }
        }
      }
    } catch (ParseException ex) {
      Logger.getLogger(AgenciaDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return viajesList;
  }
  
  private void getViajesList(List<Vuelo> vueloList, Viaje viaAdd, Viaje viaje, 
                             List<Viaje> viajesList, Hotel hotel) {
    int all = 0;
    Double precio = 0.0;
    
    for(Vuelo vuelo : vueloList) {
      if(all == 2) {
        int diff = Days.daysBetween(new DateTime(viaAdd.getFechaVuelta()), new DateTime(viaAdd.getFechaSalida())).getDays();
        viaAdd.setPrecio(precio + hotel.getPreInd()*diff);
        viajesList.add(viaAdd);
        viaAdd = new Viaje(viaje.getHotelNom(), viaje.getCategoria(), viaje.getOrigen(), 
                           viaje.getDestino(), viaje.getFechaSalida(), viaje.getFechaVuelta());
        all = 0;
        precio = 0.0;
      }

      if(vuelo.getFecVuelo().equals(viaje.getFechaSalida())) {
        viaAdd.setRefIda(vuelo.getRefVuelo());
        precio += vuelo.getPreEco();
        all++;
      } else {
        viaAdd.setRefVuelta(vuelo.getRefVuelo());
        precio += vuelo.getPreEco();
        all++;
      }

    }
  }
    private List<Vuelo> getVuelosViaje(String filtro, Viaje viaje) {
    // Definicion de variables
    String query;
    Query qry;
    List<Vuelo> vueloList;
    //Obtenemos la Session
    querySession = getSession();


    try {
        if(filtro != null) {
          query = "Select a from Vuelo a where a.fecVuelo = :salida ";
          query += "and a.aeroDest = :destino";
          qry = querySession.createQuery(query);
          qry.setDate("salida", viaje.getFechaSalida());
          qry.setString("destino", filtro);
          vueloList = qry.list();
          query = "Select a from Vuelo a where a.fecVuelo = :vuelta ";
          query += "and a.aeroOrig = :destino";
          qry = querySession.createQuery(query);
          qry.setDate("vuelta", viaje.getFechaVuelta());
          qry.setString("destino", filtro);
          vueloList.addAll(qry.list());
        } else {
          query = "Select a from Vuelo a where a.fecVuelo = :salida and a.aeroDest = :destino";
          qry = querySession.createQuery(query);
          qry.setDate("salida", viaje.getFechaSalida());
          qry.setString("destino", viaje.getDestino());
          vueloList = qry.list();
          query = "Select a from Vuelo a where a.fecVuelo = :vuelta and a.aeroOrig = :origen";
          qry = querySession.createQuery(query);
          qry.setDate("vuelta", viaje.getFechaVuelta());
          qry.setString("origen", viaje.getDestino());
          vueloList.addAll(qry.list());
        }
        return vueloList;
    } catch (HibernateException ex) {
        Logger.getLogger(GestionDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        querySession.close();
        sf.close();
    }
    return null;
  }
  
    private List<Hotel> getHotelesViaje(String filtro) {
    // Definicion de variables
    String query;
    Query qry;
    List<Hotel> hotelList;
    //Obtenemos la Session
    querySession = getSession();


    try {
        if(filtro != null) {
          String[] filtros = filtro.split("$$");
          qry = querySession.createQuery(getHotelViajeQuery(filtros));
          setHotelViajeQueryFilters(qry, filtros);
        } else { 
          Calendar cal = Calendar.getInstance();
          query = "Select a from Hotel a where a.refHotel in (select b from DisponibilidadHotel b where (b.indDisp > 0 or "
                  + "b.dobDisp > 0 or b.supDisp > 0) and b.fecha > :desde and b.fecha < :hasta)";
          qry = querySession.createQuery(query);
          qry.setDate("desde", cal.getTime());
          cal.add(Calendar.DATE, 7);
          qry.setDate("hasta", cal.getTime());
        }

        hotelList = qry.list();

        return hotelList;
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
  private String getHotelViajeQuery(String[] filtros) {
    String query = "Select a from Hotel a ";
      
      if(filtros[1] != null && !filtros[1].equals("")) {
        query += "and a.ciudad = :destino ";
      }
      if(filtros[2] != null && !filtros[2].equals("")) {
        query += "and a.refHotel in (select b from DisponibilidadHotel b where (b.insDisp > 0 or "
            + "b.dobDisp > 0 or b.supDisp > 0) and b.fecha > :desde ";
      } 
      if(filtros[3] != null && !filtros[3].equals("")) {
        query += "and b.fecha < = :hasta) ";
      }
      return query;
  }
  private void setHotelViajeQueryFilters(Query qry, String[] filtros) throws ParseException {    
    if(filtros[1] != null && !filtros[1].equals("")) {
      qry.setString("destino", filtros[1]);
    }
    if(filtros[2] != null && !filtros[2].equals("")) {
      qry.setDate("desde", new SimpleDateFormat().parse(filtros[1]));
    }
    if(filtros[3] != null && !filtros[3].equals("")) {
      qry.setDate("hasta", new SimpleDateFormat().parse(filtros[2]));
    }
  }

  @Override
  public boolean storeReserva(Reservas reserva) {
    //Definicion de variables
    Session session = null;
    Transaction transaction = null;
    boolean dispo = true;
    
    try {
        if(reserva.getReservHotel() != null) {
          dispo = compruebaDispoHotel(reserva.getReservHotel());
        }
        if(dispo) {
          if(reserva.getReservaVueloList().size() > 0) {
            restaDispoVuelos(reserva.getReservaVueloList());
          }
          //Obtenemos la Sesion
          session = getSession();
          //Obtenemos la transaccion para la sesion
          transaction = session.getTransaction();
          transaction.begin();
          session.saveOrUpdate(reserva);
          if(reserva.getReservHotel() != null) {
            session.saveOrUpdate(reserva.getReservHotel());
          }
          if(reserva.getReservaVueloList().size() > 0) {
            for(ReservaVuelo vu : reserva.getReservaVueloList()) {
              session.saveOrUpdate(vu);
            }
          }
          transaction.commit();
        }
    } catch (HibernateException ex) {
        if(transaction != null) transaction.rollback();
    } finally {
        if(session != null) session.close();
    }
    return dispo;
  }
  
  private void restaDispoVuelos(Set<ReservaVuelo> resVueloLst) {
    Session session;
    List<Vuelo> vueloLst;
    Transaction transaction = null;
    Query qry;
    String query = "select a from Vuelo a where a.refVuelo = :refVuelo"; 
    session = getSession();
    
    for(ReservaVuelo resVuelo : resVueloLst) {
      qry = session.createQuery(query);
      qry.setInteger("refVuelo", resVuelo.getVuelo().getRefVuelo());
      vueloLst = qry.list();
      if(vueloLst.size() > 0) {
        Vuelo vuelo = vueloLst.get(0);
        Avion av = vuelo.getAvion();
        vuelo.setNumEco(av.getNumEco() - resVuelo.getNumPlazasEco());
        vuelo.setNumTur(av.getNumTur() - resVuelo.getNumPlazasTur());
        vuelo.setNumBuss(av.getNumBuss() - resVuelo.getNumPlazasBuss());
        try {
          transaction = session.getTransaction();
          transaction.begin();
          session.saveOrUpdate(vuelo);
          transaction.commit();
        } catch(HibernateException ex) {
          if(transaction != null) transaction.rollback();
          System.out.println(ex);
        }
      }
    }
  }
  
  private boolean compruebaDispoHotel(ReservaHotel resH) {
    //Variable definition
    boolean dispo = true;
    Session session;
    List<DisponibilidadHotel> dispoLst;
    Transaction transaction = null;
    Query qry;
    String query;
    int numInd, numDob, numSup;
    
    numInd = resH.getNumHabInd();
    numDob = resH.getNumHabDob();
    numSup = resH.getNumHabSup();
    
    session = getSession();
    query = "select a from DisponibilidadHotel a where fecha >= :desde and fecha <= :hasta";
    qry = session.createQuery(query);
    qry.setDate("desde", resH.getFecEnt());
    qry.setDate("hasta", resH.getFecSal());
    dispoLst = qry.list();
    
    for(DisponibilidadHotel dis : dispoLst) {
      if(dis.getIndDisp() < numInd || dis.getDobDisp() < numDob || dis.getSupDisp() < numSup) {
        dispo = false;
        break;
      } else {
        try {
          dis.setIndDisp(dis.getIndDisp() - numInd);
          dis.setDobDisp(dis.getDobDisp() - numDob);
          dis.setSupDisp(dis.getSupDisp() - numSup);
          transaction = session.getTransaction();
          transaction.begin();
          session.saveOrUpdate(dis);
          transaction.commit();
        } catch(HibernateException ex) {
          if(transaction != null) transaction.rollback();
          System.out.println(ex);
        }
      }
    }

    return dispo;
  }
  @Override
  public String getReservasOutputByUserName(String username) {
        // Definicion de variables
        String query;
        Query qry;
        List<Reservas> resList;
        String data = "{ \"reservas\":[";
        //Obtenemos la Session
        querySession = getSession();
        //Falta filtrar por compañia usuario
        query = "Select a from Reservas a where a.username = :username";
        Date salida = null;
        boolean fst = true;
        String destino = "";
        Double preTot = 0.0;
        
        try {
            qry = querySession.createQuery(query);
            qry.setString("username", username);
            resList = qry.list();
            
            for(Reservas res : resList) {
              if(!fst) {
                    data +=",";
              }
              data +="[\"" + res.getRefReserva() + "\", \"" + res.getFecReserva();
                
                if(res.getReservaVueloList().size() > 0) {
                  for(ReservaVuelo vu : res.getReservaVueloList()) {
                    if (fst) {
                      salida = vu.getVuelo().getFecVuelo();
                      destino = vu.getVuelo().getAeroDest().getCiudad();
                    } else if(vu.getVuelo().getFecVuelo().before(salida)){
                      salida = vu.getVuelo().getFecVuelo();
                      destino = vu.getVuelo().getAeroDest().getCiudad();
                    }
                    fst = false;
                    preTot += vu.getPreTot();
                  }
                  data += "\", \"" + salida + "\", \"" + destino;
                   
                } else if(res.getReservHotel() != null) {
                  data += "\", \"" + res.getReservHotel().getFecEnt() + "\", \"" + res.getReservHotel().getHotel().getCiudad();
                }
                data += "\", \"" + preTot + "\"]";
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
  public Reservas getReservasByRef(int ref) {
    String query = "select a from Reservas a where a.refReserva = :ref";
    Query qry;
    List<Reservas> resList;
    
    querySession = getSession();
    try {
      qry = querySession.createQuery(query);
      qry.setInteger("ref", ref);
      resList = qry.list();
      if(resList.size() > 0) return resList.get(0);
    } catch(HibernateException ex) {
      System.out.println(ex);
    }
    return null;
  }

  @Override
  public void borrarReserva(int refRes) {
    Transaction transaction = null;
    Reservas reserva;
    Query qry;
    try {
      querySession = getSession();
      reserva = (Reservas) querySession.createQuery("select a from Reservas a where a.refReserva = :refRes").setInteger("refRes", refRes).list().get(0);
      transaction = querySession.getTransaction();
      transaction.begin();

      if(reserva.getReservaVueloList().size() > 0) {
        for(ReservaVuelo res : reserva.getReservaVueloList()) {
          querySession.delete(res);
        }
      }
      if(reserva.getReservHotel() != null) {
        querySession.delete(reserva.getReservHotel());
      }
      querySession.delete(reserva);
      transaction.commit();
    } catch(HibernateException ex) {
      if(transaction != null) transaction.rollback();
      System.out.println(ex);
    }
    
  }
}

