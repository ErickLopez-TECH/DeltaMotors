/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author triamus
 */
public class objMecanicos {
    
    private int id;
    private String nombre;
    private String cedula;
    

    public objMecanicos() {
    }

    public objMecanicos(int id, String nombre, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
    }
    
    
    //getters

    public String getCedula() {
        return cedula;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    //setters

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
