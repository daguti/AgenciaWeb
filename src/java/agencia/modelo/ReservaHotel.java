/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author David
 */
public class ReservaHotel implements Serializable{
    int refReserva;
    Hotel hotel;
    int numHabInd;
    int numHabDob;
    int numHabSup;
    Date fecEnt;
    Date fecSal;
    double preTot;
    Reservas reserva;
    
    public ReservaHotel() {
    }

    public ReservaHotel(Hotel hotel, int numHabInd, int numHabDob, 
                        int numHabSup, double preTot, Date fecEnt, Date fecSal) {
        this.hotel      = hotel;
        this.numHabInd  = numHabInd;
        this.numHabDob  = numHabDob;
        this.numHabSup  = numHabSup;
        this.preTot     = preTot;
        this.fecEnt     = fecEnt;
        this.fecSal     = fecSal;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Reservas getReserva() {
        return reserva;
    }

    public void setReserva(Reservas reserva) {
        this.reserva = reserva;
    }

    
    public Date getFecEnt() {
        return fecEnt;
    }

    public void setFecEnt(Date fecEnt) {
        this.fecEnt = fecEnt;
    }

    public Date getFecSal() {
        return fecSal;
    }

    public void setFecSal(Date fecSal) {
        this.fecSal = fecSal;
    }

    
    public int getRefReserva() {
        return refReserva;
    }

    public void setRefReserva(int refReserva) {
        this.refReserva = refReserva;
    }

    public int getNumHabInd() {
        return numHabInd;
    }

    public void setNumHabInd(int numHabInd) {
        this.numHabInd = numHabInd;
    }

    public int getNumHabDob() {
        return numHabDob;
    }

    public void setNumHabDob(int numHabDob) {
        this.numHabDob = numHabDob;
    }

    public int getNumHabSup() {
        return numHabSup;
    }

    public void setNumHabSup(int numHabSup) {
        this.numHabSup = numHabSup;
    }

    public double getPreTot() {
        return preTot;
    }

    public void setPreTot(double preTot) {
        this.preTot = preTot;
    }
    
    
}
