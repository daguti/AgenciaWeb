/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.modelo;

/**
 *
 * @author David
 */
public class OfertaVuelo {
    int refOfertaVuelo;
    int refVuelo;
    Double preEco;
    Double preBuss;
    Double preTur;

    public OfertaVuelo(int refVuelo, Double preEco, Double preBuss, Double preTur) {
        this.refVuelo = refVuelo;
        this.preEco = preEco;
        this.preBuss = preBuss;
        this.preTur = preTur;
    }

    public int getRefOfertaVuelo() {
        return refOfertaVuelo;
    }

    public void setRefOfertaVuelo(int refOfertaVuelo) {
        this.refOfertaVuelo = refOfertaVuelo;
    }

    
    public int getRefVuelo() {
        return refVuelo;
    }

    public void setRefVuelo(int refVuelo) {
        this.refVuelo = refVuelo;
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

    public Double getPreTur() {
        return preTur;
    }

    public void setPreTur(Double preTur) {
        this.preTur = preTur;
    }
    
    
}
