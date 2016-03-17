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
import agencia.modelo.Usuario;
import agencia.modelo.Vuelo;
import java.util.List;

/**
 *
 * @author David
 */
public interface ItfzGestionDAO {
    public CompaniaAerea getCompañíaID(String compañia);
    public CompaniaAerea getCompañíaName(String refComp);
    public CompaniaHotelera getHoteleraByRef(String refComp);
    public CompaniaHotelera getHoteleraByCompania(String compania);
    public Avion getAvion(int refAvion);
    public Hotel getHotel(String nombre);
    public Aeropuerto getAero(String nombre);
    public Aeropuerto getAeroByCod(String COD);
    public String getCompForTable(String compania);
    public List<Aeropuerto> getAeroForTable(String filtros);
    public List<Vuelo> getVuelosGestion(String filtro);
    public List<Avion> getAviones(String filtro);
    public List<Hotel> getHotelesGestion(String filtro);
    public void storeAvion(Avion avion);
    public int storeVuelo(Vuelo vuelo);
    public void storeAdmin(Usuario user);
    public void storeHotel(Hotel hotel, boolean update);
    public void storeAeropuerto(Aeropuerto aero);
    public void storeCompaniaAerea(CompaniaAerea compAerea);
    public void storeCompaniaHotelera(CompaniaHotelera compHotelera);
    public void storeOfertaVuelo(OfertaVuelo ofertaVuelo);
    public void storeOfertaHotel(OfertaHotel ofertaHotel);
    public List<Usuario> getAdministradores(String filtro);
    public void saveOrUpdateUser(Usuario user);
    public List<Vuelo> getVuelosInventario(String compania);
    public List<DisponibilidadHotel> getDisponiHotelesInventario(String compania);
    public Hotel getHotelByRef(int refHotel);
    public void deleteUser(Usuario user);
    public void deleteAeropuerto(Aeropuerto aero);
    public void deleteAvion(Avion avion);
    public void deleteCompaniaHotelera(CompaniaHotelera hotelera);
    public void deleteComapniaAerea(CompaniaAerea aerea);
    public void deleteHotel(Hotel hotel);
    public Vuelo getVueloByRef(int refVuelo);
}
