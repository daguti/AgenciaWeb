/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.persistencia;

import agencia.modelo.Hotel;
import agencia.modelo.Reservas;
import agencia.modelo.Viaje;
import agencia.modelo.Vuelo;
import java.util.List;

/**
 *
 * @author David
 */
public interface ItfzAgenciaDAO {
    public String getAeropuertos();
    public String getCiudades();
    public String getCompañias();
    public String getNombres();
    public String getApellidos();
    public String getHoteles();
    public String getDescAvion();
    public List<Vuelo> getVuelosCli(String filtro, boolean ultimaHora);
    public List<Hotel> getHotelesCli(String filtro, boolean ultimaHora);
    public List<Viaje> getViajes(String filtro);
    public boolean storeReserva(Reservas reserva);
    public String getReservasOutputByUserName(String username);
    public Reservas getReservasByRef(int ref);
    public void borrarReserva(int refRes);
}
