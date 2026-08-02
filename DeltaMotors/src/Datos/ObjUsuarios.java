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
    private String rol;
    private String estado;
    
    
    public ObjUsuarios(int id, String nombre, String rol, String contrasena, String estado) {
        this.id = 0;
        
        this.rol = rol;
        this.contrasena = contrasena;
        this.estado = estado;
    }

    public ObjUsuarios() {
        this.id = 0;
        
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
    
    
    
    
}
