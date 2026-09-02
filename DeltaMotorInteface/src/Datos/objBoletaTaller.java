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
public class objBoletaTaller {
    
    private int id;//listo
    private int idMantenimiento;//dependencia asignacion mante listo
    private int idMecanico;
    private String placaVehiculo;//conexion con obj vehiculo listo
    private String nombreMantenimeinto;//listo
    private String marcaVehiculo;//listo
    private String modeloVehiculo;//listo
    private double kilometrajeIngreso;//listo
    private Date fecha;
    private String nombreMecanico; //dependencia de mecanicos

    public objBoletaTaller() {
    }

    public objBoletaTaller(int id, int idMantenimiento, int idMecanico, String placaVehiculo, String nombreMantenimeinto, String marcaVehiculo, String modeloVehiculo, double kilometrajeIngreso, Date fecha, String nombreMecanico) {
        this.id = id;
        this.idMantenimiento = idMantenimiento;
        this.idMecanico = idMecanico;
        this.placaVehiculo = placaVehiculo;
        this.nombreMantenimeinto = nombreMantenimeinto;
        this.marcaVehiculo = marcaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.kilometrajeIngreso = kilometrajeIngreso;
        this.fecha = fecha;
        this.nombreMecanico = nombreMecanico;
    }
    
    //getters

    public Date getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public int getIdMecanico() {
        return idMecanico;
    }

    public double getKilometrajeIngreso() {
        return kilometrajeIngreso;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
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
    
    //setters

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public void setIdMecanico(int idMecanico) {
        this.idMecanico = idMecanico;
    }

    public void setKilometrajeIngreso(double kilometrajeIngreso) {
        this.kilometrajeIngreso = kilometrajeIngreso;
    }

    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
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
