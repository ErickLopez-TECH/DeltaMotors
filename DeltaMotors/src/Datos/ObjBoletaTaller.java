/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.util.Date;

/**
 *
 * @author triamus
 */
public class ObjBoletaTaller {
    
    private int id;//listo
    private int idMantenimiento;//dependencia asignacion mante listo
    private String nombreMantenimeinto;//listo
    private String placaVehiculo;//conexion con obj vehiculo listo
    private String modeloVehiculo;//listo
    private String marcaVehiculo;//listo
    private double kilometrajeIngreso;//listo
    private Date fecha;
    private String nombreMecanico; //listo
    
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

    public Date getFecha() {
        return fecha;
    }

    public double getKilometrajeIngreso() {
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

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }
    
    
    

    //-------------------------------------------------
    //------------------setters------------------------
    //-------------------------------------------------
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public void setKilometrajeIngreso(double kilometrajeIngreso) {
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

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }
    
   
     
    
    
    
}
