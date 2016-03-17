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
public class CompaniaAerea implements Serializable{
    String refCompañia;
    String nombre;
    String pais;
    Set<Avion> avionesList;
    
    public CompaniaAerea() {
    }

    public CompaniaAerea(String refCompañia, String nombre, String pais, Set<Avion> avionesList) {
        this.refCompañia = refCompañia;
        this.nombre = nombre;
        this.pais = pais;
        this.avionesList = avionesList;
    }

    public Set<Avion> getAvionesList() {
        return avionesList;
    }

    public void setAvionesList(Set<Avion> avionesList) {
        this.avionesList = avionesList;
    }

    
    public String getRefCompañia() {
        return refCompañia;
    }

    public void setRefCompañia(String refCompañia) {
        this.refCompañia = refCompañia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    
}
