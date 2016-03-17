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
public class CompaniaHotelera implements Serializable{
    String refCompañia;
    String nombre;
    String pais;
    Set<Hotel> hotelesList;
    
    public CompaniaHotelera() {
    }

    public CompaniaHotelera(String refCompañia, String nombre, String pais, Set<Hotel> hotelesList) {
        this.refCompañia = refCompañia;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Set<Hotel> getHotelesList() {
        return hotelesList;
    }

    public void setHotelesList(Set<Hotel> hotelesList) {
        this.hotelesList = hotelesList;
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
