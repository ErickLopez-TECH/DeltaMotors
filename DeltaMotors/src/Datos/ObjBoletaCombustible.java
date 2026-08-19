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
public class ObjBoletaCombustible {
    
    private int id;
    private String placaVehiculo; //conexion con vehiculos
    private double kmActual;
    private double cantidadCombustible;
    private double cantidadKWH;
    private String tipoCombustible;
    private Date fecha;

    public ObjBoletaCombustible(){
        this.id = 0;
        this.placaVehiculo = "";
        this.kmActual = 0;
        this.cantidadCombustible = 0;
        this.cantidadKWH =0;
        this.tipoCombustible ="";
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCantidadKWH(double cantidadKWH) {
        this.cantidadKWH = cantidadKWH;
    }

    public double getCantidadKWH() {
        return cantidadKWH;
    }
    
    

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public double getCantidadCombustible() {
        return cantidadCombustible;
    }

    public void setCantidadCombustible(double cantidadCombustible) {
        this.cantidadCombustible = cantidadCombustible;
    }

    public double getKmActual() {
        return kmActual;
    }

    public void setKmActual(double kmActual) {
        this.kmActual = kmActual;
    }

     public Date getFecha() {
        return fecha;
    }
     
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

     public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

   
    
    
    
}
