/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author triamus
 */
public class ObjUsuarios {
    
    private int id;//automatico
    private String usuario;
    private String contrasena;
    private String cedula;
    private String rol;
    private String estado;
    
    
    

    public ObjUsuarios() {
        this.id = 0;
        this.usuario ="";
        this.cedula = "";
        this.rol ="";
        this.contrasena = "";
        this.estado = "";
    }
    
    
    //-------------------------------------------------
    //------------------getters------------------------
    //-------------------------------------------------

    public String getContrasena() {
        return contrasena;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getEstado() {
        return estado;
    }

    
    

    public String getRol() {
        return rol;
    }

    public String getCedula() {
        return cedula;
    }
    
    
    
    //-------------------------------------------------
    //------------------setters------------------------
    //-------------------------------------------------

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    
    
    
}
