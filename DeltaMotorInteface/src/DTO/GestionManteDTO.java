/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO; // O el paquete que hayas creado exclusivamente para transferencia

/**
 *
 * @author triamus
 */
public class GestionManteDTO {
    
    private int id;
    private String nombre;
    private String estado;

    // Constructor vacío por si se necesita instanciar sin datos previos
    public GestionManteDTO() {
    }

    // Constructor con los campos exactos que la interfaz necesita mostrar
    public GestionManteDTO(int id, String nombre, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }
}