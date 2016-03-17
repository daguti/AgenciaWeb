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
public class Hotel implements Serializable{
    int refHotel;
    String nombre;
    int categoria;
    String descripcion;
    String ciudad;
    String direccion;
    int habInd;
    int habDob;
    int habSup;
    Double preInd;
    Double preDob;
    Double preSup;
    Set<ReservaHotel> reservasList;
    CompaniaHotelera compañia;
    
    public Hotel() {
    }

    public Hotel(String nombre, int categoria, String descripcion, String ciudad, 
                 String direccion, int habInd, int habDob, int habSup, 
                 Double preInd, Double preDob, Double preSup, CompaniaHotelera compañia,
                 Set<ReservaHotel> reservasList) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.habInd = habInd;
        this.habDob = habDob;
        this.habSup = habSup;
        this.preInd = preInd;
        this.preDob = preDob;
        this.preSup = preSup;
        this.compañia = compañia;
        this.reservasList = reservasList;
    }

    public CompaniaHotelera getCompañia() {
        return compañia;
    }

    public void setCompañia(CompaniaHotelera compañia) {
        this.compañia = compañia;
    }

    public Set<ReservaHotel> getReservasList() {
        return reservasList;
    }

    public void setReservasList(Set<ReservaHotel> reservasList) {
        this.reservasList = reservasList;
    }

    public int getRefHotel() {
        return refHotel;
    }

    public void setRefHotel(int refHotel) {
        this.refHotel = refHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getHabInd() {
        return habInd;
    }

    public void setHabInd(int habInd) {
        this.habInd = habInd;
    }

    public int getHabDob() {
        return habDob;
    }

    public void setHabDob(int habDob) {
        this.habDob = habDob;
    }

    public int getHabSup() {
        return habSup;
    }

    public void setHabSup(int habSup) {
        this.habSup = habSup;
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
