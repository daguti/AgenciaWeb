/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author David
 */
public class Reservas implements Serializable{
    int refReserva;
    Set<ReservaVuelo> reservaVueloList;
    ReservaHotel reservHotel;
    boolean confirm;
    Date fecReserva;
    String username;
    
    public Reservas() {
    }

    public Reservas(String user, Set<ReservaVuelo> reservaVueloList, ReservaHotel reservHotel, 
                    boolean confirm, Date fecReserva) {
        this.username             = user;
        this.reservaVueloList = reservaVueloList;
        this.reservHotel      = reservHotel;
        this.confirm          = confirm;
        this.fecReserva       = fecReserva;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }
    
    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public Date getFecReserva() {
        return fecReserva;
    }

    public void setFecReserva(Date fecReserva) {
        this.fecReserva = fecReserva;
    }
    
    public int getRefReserva() {
        return refReserva;
    }

    public void setRefReserva(int refReserva) {
        this.refReserva = refReserva;
    }

    public Set<ReservaVuelo> getReservaVueloList() {
        return reservaVueloList;
    }

    public void setReservaVueloList(Set<ReservaVuelo> reservaVueloList) {
        this.reservaVueloList = reservaVueloList;
    }

    public ReservaHotel getReservHotel() {
        return reservHotel;
    }

    public void setReservHotel(ReservaHotel reservHotel) {
        this.reservHotel = reservHotel;
    }
    
}
