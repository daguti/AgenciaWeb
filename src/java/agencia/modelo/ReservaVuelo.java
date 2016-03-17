/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.modelo;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class ReservaVuelo implements Serializable{
    int refReserva;
    Vuelo vuelo;
    int numPlazasBuss;
    int numPlazasTur;
    int numPlazasEco;
    double preTot;
    Reservas reserva;
    
    public ReservaVuelo() {
    }

    public ReservaVuelo(Vuelo vuelo, int numPlazasBuss, 
                        int numPlazasTur, int numPlazasEco, double preTot) {
        this.vuelo = vuelo;
        this.numPlazasBuss = numPlazasBuss;
        this.numPlazasTur = numPlazasTur;
        this.numPlazasEco = numPlazasEco;
        this.preTot = preTot;
    }

    public Reservas getReserva() {
        return reserva;
    }

    public void setReserva(Reservas reserva) {
        this.reserva = reserva;
    }

    
    public int getRefReserva() {
        return refReserva;
    }

    public void setRefReserva(int refReserva) {
        this.refReserva = refReserva;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public int getNumPlazasBuss() {
        return numPlazasBuss;
    }

    public void setNumPlazasBuss(int numPlazasBuss) {
        this.numPlazasBuss = numPlazasBuss;
    }

    public int getNumPlazasTur() {
        return numPlazasTur;
    }

    public void setNumPlazasTur(int numPlazasTur) {
        this.numPlazasTur = numPlazasTur;
    }

    public int getNumPlazasEco() {
        return numPlazasEco;
    }

    public void setNumPlazasEco(int numPlazasEco) {
        this.numPlazasEco = numPlazasEco;
    }

    public double getPreTot() {
        return preTot;
    }

    public void setPreTot(double preTot) {
        this.preTot = preTot;
    }
    
    
}
