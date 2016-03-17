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
public class DisponibilidadHotel implements Serializable{
    int refHotel;
    Date fecha;
    int indDisp;
    int dobDisp;
    int supDisp;

    public DisponibilidadHotel() {
    }

    public DisponibilidadHotel(int refHotel, Date fecha, int indDisp, 
                               int dobDisp, int supDisp) {
        this.refHotel = refHotel;
        this.fecha = fecha;
        this.indDisp = indDisp;
        this.dobDisp = dobDisp;
        this.supDisp = supDisp;
    }

    public int getRefHotel() {
        return refHotel;
    }

    public void setRefHotel(int refHotel) {
        this.refHotel = refHotel;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIndDisp() {
        return indDisp;
    }

    public void setIndDisp(int indDisp) {
        this.indDisp = indDisp;
    }

    public int getDobDisp() {
        return dobDisp;
    }

    public void setDobDisp(int dobDisp) {
        this.dobDisp = dobDisp;
    }

    public int getSupDisp() {
        return supDisp;
    }

    public void setSupDisp(int supDisp) {
        this.supDisp = supDisp;
    }
    
    
}
