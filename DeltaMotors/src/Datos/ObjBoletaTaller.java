/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author triamus
 */
public class ObjBoletaTaller {
    
    private int id;
    private int idMantenimiento;//dependencia asignacion mante
    private String nombreMantenimeinto;
    private String placaVehiculo;//conexion con obj vehiculo
    private int kilometrajeIngreso;
    private String fecha;
    private String nombreMecanico; //depende de usaurios con rol operador 10
    
    public ObjBoletaTaller(){
        
    }

    //-------------------------------------------------
    //------------------getters------------------------
    //-------------------------------------------------
    public int getId() {
        return id;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public String getFecha() {
        return fecha;
    }

    public int getKilometrajeIngreso() {
        return kilometrajeIngreso;
    }

    public String getNombreMantenimeinto() {
        return nombreMantenimeinto;
    }

    public String getNombreMecanico() {
        return nombreMecanico;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    //-------------------------------------------------
    //------------------setters------------------------
    //-------------------------------------------------
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public void setKilometrajeIngreso(int kilometrajeIngreso) {
        this.kilometrajeIngreso = kilometrajeIngreso;
    }

    public void setNombreMantenimeinto(String nombreMantenimeinto) {
        this.nombreMantenimeinto = nombreMantenimeinto;
    }

    public void setNombreMecanico(String nombreMecanico) {
        this.nombreMecanico = nombreMecanico;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }
    
   
     
    
    
    
}
