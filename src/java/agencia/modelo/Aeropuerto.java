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
public class Aeropuerto implements Serializable{
    String codigo;
    String nombre;
    String pais;
    String ciudad;
    Set<Vuelo> vuelosList;
    
    public Aeropuerto() {
    }

    public Aeropuerto(String codigo, String nombre, String pais, String ciudad, Set<Vuelo> vuelosList) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.vuelosList = vuelosList;
    }

    public Set<Vuelo> getVuelosList() {
        return vuelosList;
    }

    public void setVuelosList(Set<Vuelo> vuelosList) {
        this.vuelosList = vuelosList;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
}
