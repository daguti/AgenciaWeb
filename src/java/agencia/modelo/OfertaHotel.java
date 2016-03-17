/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.modelo;

import java.util.Date;

/**
 *
 * @author David
 */
public class OfertaHotel {
    int refOfertaHotel;
    int refHotel;
    Date fecIni;
    Date fecFin;
    Double preInd;
    Double preDob;
    Double preSup;

    public OfertaHotel(int refHotel, Date fecIni, Date fecFin, Double preInd, Double preDob, Double preSup) {
        this.refHotel = refHotel;
        this.fecIni = fecIni;
        this.fecFin = fecFin;
        this.preInd = preInd;
        this.preDob = preDob;
        this.preSup = preSup;
    }

    public int getRefOfertaHotel() {
        return refOfertaHotel;
    }

    public void setRefOfertaHotel(int refOfertaHotel) {
        this.refOfertaHotel = refOfertaHotel;
    }

    
    public int getRefHotel() {
        return refHotel;
    }

    public void setRefHotel(int refHotel) {
        this.refHotel = refHotel;
    }

    public Date getFecIni() {
        return fecIni;
    }

    public void setFecIni(Date fecIni) {
        this.fecIni = fecIni;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    public Double getPreInd() {
        return preInd;
    }

    public void setPreInd(Double preInd) {
        this.preInd = preInd;
    }

    public Double getPreDob() {
        return preDob;
    }

    public void setPreDob(Double preDob) {
        this.preDob = preDob;
    }

    public Double getPreSup() {
        return preSup;
    }

    public void setPreSup(Double preSup) {
        this.preSup = preSup;
    }
    
    
}
