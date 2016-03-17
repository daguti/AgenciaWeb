/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agencia.modelo;

import java.util.Date;

/**
 *
 * @author ESa10969
 */
public class Viaje {
  String hotelNom;
  int categoria;
  String origen;
  String destino;
  Date fechaSalida;
  Date fechaVuelta;
  Double precio = 0.0;
  int refIda;
  int refVuelta;

  public Viaje() {
    
  }
  public Viaje(String hotelNom, int categoria, String origen, String destino, Date fechaSalida, Date fechaVuelta) {
    this.hotelNom = hotelNom;
    this.categoria = categoria;
    this.origen = origen;
    this.destino = destino;
    this.fechaSalida = fechaSalida;
    this.fechaVuelta = fechaVuelta;
  }

  public int getRefIda() {
    return refIda;
  }

  public void setRefIda(int refIda) {
    this.refIda = refIda;
  }

  public int getRefVuelta() {
    return refVuelta;
  }

  public void setRefVuelta(int refVuelta) {
    this.refVuelta = refVuelta;
  }

  
  
  public String getHotelNom() {
    return hotelNom;
  }

  public void setHotelNom(String hotelNom) {
    this.hotelNom = hotelNom;
  }

  public int getCategoria() {
    return categoria;
  }

  public void setCategoria(int categoria) {
    this.categoria = categoria;
  }

  public String getOrigen() {
    return origen;
  }

  public void setOrigen(String origen) {
    this.origen = origen;
  }

  public String getDestino() {
    return destino;
  }

  public void setDestino(String destino) {
    this.destino = destino;
  }

  public Date getFechaSalida() {
    return fechaSalida;
  }

  public void setFechaSalida(Date fechaSalida) {
    this.fechaSalida = fechaSalida;
  }

  public Date getFechaVuelta() {
    return fechaVuelta;
  }

  public void setFechaVuelta(Date fechaVuelta) {
    this.fechaVuelta = fechaVuelta;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }
  
  
}
