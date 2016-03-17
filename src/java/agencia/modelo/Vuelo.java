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
public class Vuelo implements Serializable{
  private static final long serialVersionUID = 1L;
    int refVuelo;
    Avion avion;
    int numTur;
    int numBuss;
    int numEco;
    Date fecVuelo;
    String horaSalida;
    String duracion;
    Double preTur;
    Double preEco;
    Double preBuss;
    Aeropuerto aeroDest;
    Aeropuerto aeroOrig;
    Set<ReservaVuelo> reservasList;
    
    public Vuelo() {
    }
    
    public Vuelo(Set<ReservaVuelo> reservasList, Avion avion, int numTur, int numBuss, int numEco, 
                 Date fecVuelo, String horaSalida, String duracion, Double preTur, Double preEco, Double preBuss, 
                 Aeropuerto aeroDest, Aeropuerto aeroOrig) {
        this.reservasList = reservasList;
        this.avion = avion;
        this.numTur = numTur;
        this.numBuss = numBuss;
        this.numEco = numEco;
        this.fecVuelo = fecVuelo;
        this.preTur = preTur;
        this.preEco = preEco;
        this.preBuss = preBuss;
        this.aeroDest = aeroDest;
        this.aeroOrig = aeroOrig;
        this.horaSalida = horaSalida;
        this.duracion = duracion;
    }

  public String getHoraSalida() {
    return horaSalida;
  }

  public void setHoraSalida(String horaSalida) {
    this.horaSalida = horaSalida;
  }

  public String getDuracion() {
    return duracion;
  }

  public void setDuracion(String duracion) {
    this.duracion = duracion;
  }
    
    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Set<ReservaVuelo> getReservasList() {
        return reservasList;
    }

    public void setReservasList(Set<ReservaVuelo> reservasList) {
        this.reservasList = reservasList;
    }

    public int getRefVuelo() {
        return refVuelo;
    }

    public void setRefVuelo(int refVuelo) {
        this.refVuelo = refVuelo;
    }

    public int getNumTur() {
        return numTur;
    }

    public void setNumTur(int numTur) {
        this.numTur = numTur;
    }

    public int getNumBuss() {
        return numBuss;
    }

    public void setNumBuss(int numBuss) {
        this.numBuss = numBuss;
    }

    public int getNumEco() {
        return numEco;
    }

    public void setNumEco(int numEco) {
        this.numEco = numEco;
    }

    public Date getFecVuelo() {
        return fecVuelo;
    }

    public void setFecVuelo(Date fecVuelo) {
        this.fecVuelo = fecVuelo;
    }

    public Double getPreTur() {
        return preTur;
    }

    public void setPreTur(Double preTur) {
        this.preTur = preTur;
    }

    public Double getPreEco() {
        return preEco;
    }

    public void setPreEco(Double preEco) {
        this.preEco = preEco;
    }

    public Double getPreBuss() {
        return preBuss;
    }

    public void setPreBuss(Double preBuss) {
        this.preBuss = preBuss;
    }

    public Aeropuerto getAeroDest() {
        return aeroDest;
    }

    public void setAeroDest(Aeropuerto aeroDest) {
        this.aeroDest = aeroDest;
    }

    public Aeropuerto getAeroOrig() {
        return aeroOrig;
    }

    public void setAeroOrig(Aeropuerto aeroOrig) {
        this.aeroOrig = aeroOrig;
    }

    
    
    
}
