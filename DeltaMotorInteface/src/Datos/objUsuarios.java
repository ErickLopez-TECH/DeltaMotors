/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author triamus
 */
public class objUsuarios {
    
    private int id;
    private String nombre;
    private String cedula;
    private String contrasena;
    private String rol;
    private String estado;
    
    public objUsuarios(int id, String nombre,String cedula,String contrasena,String rol, String estado){
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.contrasena = contrasena;
        this.rol = rol; 
        this.estado = estado;
    }
    
    public objUsuarios(){
        this.id = 0;
        this.nombre = "";
       this.cedula ="";
        this.contrasena = "";
        this.rol = ""; 
    }
    
    //getter

    public String getContrasena() {
        return contrasena;
    }

    public String getCedula() {
        return cedula;
    }

   

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public String getEstado() {
        return estado;
    }
    
    
    
    //setters

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
}
