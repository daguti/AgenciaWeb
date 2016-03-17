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
public class Usuario implements Serializable{
    int refUsuario;
    String nombre;
    String apellidos;
    String direccion;
    String poblacion;
    int CP;
    String pais;
    String userName;
    String pass;
    int userType; //0-Admin, 1-Admin. Aerea, 2-Admin Hotel, 3- Client
    String refCompañia;
    String correo;
    
    public Usuario() {
    }
    //Admin
    public Usuario(int refUsuario, String nombre, String apellidos, 
                   String userName, String pass, int userType) {
        this.refUsuario = refUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.userName = userName;
        this.pass = pass;
        this.userType = userType; 
   }

    public Usuario(int refUsuario, String nombre, String apellidos, String direccion, String pais, String poblacion, int CP, String userName, String pass, String correo,String refCompañia,int userType) {
        this.refUsuario = refUsuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.CP = CP;
        this.pais = pais;
        this.userName = userName;
        this.pass = pass;
        this.userType = userType;
        this.refCompañia = refCompañia;
        this.correo = correo;
    }
    
    //Client
    public Usuario(String nombre, String apellidos, String direccion, 
                   String poblacion, int CP, String pais, String userName, 
                   String pass, String correo, int userType) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.CP = CP;
        this.pais = pais;
        this.userName = userName;
        this.pass = pass;
        this.correo = correo;
        this.userType = userType;
    }
    
    //Admin Aerea u Hotelera
    public Usuario(String nombre, String apellidos, String userName, String pass, 
                   String refCompañia,int userType) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.userName = userName;
        this.pass = pass;
        this.userType = userType;
        this.refCompañia = refCompañia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public int getCP() {
        return CP;
    }

    public void setCP(int CP) {
        this.CP = CP;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getRefCompañia() {
        return refCompañia;
    }

    public void setRefCompañia(String refCompañia) {
        this.refCompañia = refCompañia;
    }
    
    
    public int getRefUsuario() {
        return refUsuario;
    }

    public void setRefUsuario(int refUsuario) {
        this.refUsuario = refUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
