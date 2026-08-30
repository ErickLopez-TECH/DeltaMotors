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
public class objAsignacionMante {
    
    private int id;
    private String PlacaVehiculo; //referencia al obj vehiculo
    private String nombreMantenimiento; //referencia al obj mantenimiento
    private String tipoPeriodo; //km/dias---> modificar
    private double numPeriodicidad;//---> modificar
    private double kmUltimo;//----> modificar
    private Date ingreso;//agregar escribir
    private Date vencimiento;

    public objAsignacionMante() {
    }

    public objAsignacionMante(int id, String PlacaVehiculo, String nombreMantenimiento, String tipoPeriodo, double numPeriodicidad, double kmUltimo, Date ingreso, Date vencimiento) {
        this.id = id;
        this.PlacaVehiculo = PlacaVehiculo;
        this.nombreMantenimiento = nombreMantenimiento;
        this.tipoPeriodo = tipoPeriodo;
        this.numPeriodicidad = numPeriodicidad;
        this.kmUltimo = kmUltimo;
        this.ingreso = ingreso;
        this.vencimiento = vencimiento;
    }
    
    //getters

    public int getId() {
        return id;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public double getKmUltimo() {
        return kmUltimo;
    }

    public String getNombreMantenimiento() {
        return nombreMantenimiento;
    }

    public double getNumPeriodicidad() {
        return numPeriodicidad;
    }

    public String getPlacaVehiculo() {
        return PlacaVehiculo;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public Date getVencimiento() {
        return vencimiento;
    }
    
    //setters

    public void setId(int id) {
        this.id = id;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public void setKmUltimo(double kmUltimo) {
        this.kmUltimo = kmUltimo;
    }

    public void setNombreMantenimiento(String nombreMantenimiento) {
        this.nombreMantenimiento = nombreMantenimiento;
    }

    public void setNumPeriodicidad(double numPeriodicidad) {
        this.numPeriodicidad = numPeriodicidad;
    }

    public void setPlacaVehiculo(String PlacaVehiculo) {
        this.PlacaVehiculo = PlacaVehiculo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
    
}
