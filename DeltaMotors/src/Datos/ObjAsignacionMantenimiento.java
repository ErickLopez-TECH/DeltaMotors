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
public class ObjAsignacionMantenimiento {
    private int id;
    private String PlacaVehiculo; //referencia al obj vehiculo
    private String nombreMantenimiento; //referencia al obj mantenimiento
    private String tipoPeriodo; //km/dias---> modificar
    private double numPeriodicidad;//---> modificar
    private double kmUltimo;//----> modificar
    private Date ingreso;//agregar escribir
    private Date vencimiento;
    
    public ObjAsignacionMantenimiento(){
        this.id = 0;
        this.PlacaVehiculo = "";
        this.nombreMantenimiento = "";
        this.tipoPeriodo = "";
        this.numPeriodicidad = 0;
        this.kmUltimo =0;
        
       
    }
    
    //---4.Metodos acciones

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getPlacaVehiculo() {
        return PlacaVehiculo;
    }
    
    public void setPlacaVehiculo(String placaVehiculo) {
        this.PlacaVehiculo = placaVehiculo;
    }

    public String getNombreMantenimiento() {
        return nombreMantenimiento;
    }
    
    public void setNombreMantenimiento(String nombreMantenimiento) {
        this.nombreMantenimiento= nombreMantenimiento;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public double getNumPeriodicidad() {
        return numPeriodicidad;
    }

    public void setNumPeriodicidad(double frecuencia) {
        this.numPeriodicidad = frecuencia;
    }

    public double getKmUltimo() {
        return kmUltimo;
    }

    public void setKmUltimo(double kmUltimo) {
        this.kmUltimo = kmUltimo;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }
    
    

    
}
