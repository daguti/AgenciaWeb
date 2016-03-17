/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.modelo;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author David
 */
public class Avion implements Serializable{
    int refAvion;
    String descripcion;
    int numTur;
    int numEco;
    int numBuss;
    CompaniaAerea compania;
    Set<Vuelo> vuelosList;

    public Avion() {
    }

    public Avion(String descripcion, int numTur, int numEco, int numBuss, 
                 CompaniaAerea compania, Set<Vuelo> vuelosList) {
        this.descripcion = descripcion;
        this.numTur = numTur;
        this.numEco = numEco;
        this.numBuss = numBuss;
        this.compania = compania;
        this.vuelosList = vuelosList;
    }

    public Set<Vuelo> getVuelosList() {
        return vuelosList;
    }

    public void setVuelosList(Set<Vuelo> vuelosList) {
        this.vuelosList = vuelosList;
    }

    
    public int getRefAvion() {
        return refAvion;
    }

    public void setRefAvion(int refAvion) {
        this.refAvion = refAvion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumTur() {
        return numTur;
    }

    public void setNumTur(int numTur) {
        this.numTur = numTur;
    }

    public int getNumEco() {
        return numEco;
    }

    public void setNumEco(int numEco) {
        this.numEco = numEco;
    }

    public int getNumBuss() {
        return numBuss;
    }

    public void setNumBuss(int numBuss) {
        this.numBuss = numBuss;
    }

    public CompaniaAerea getCompania() {
        return compania;
    }

    public void setCompania(CompaniaAerea compania) {
        this.compania = compania;
    }
    
    
}
