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
public class objBoletaCombus {
    
    private int id;
    private String placaVehiculo; //conexion con vehiculos
    private double kmActual;
    private String Combustible;
    private double cantidadCombustible;
    private double cantidadKWH;
    private Date fecha;

    public objBoletaCombus() {
    }

    public objBoletaCombus(int id, String placaVehiculo, double kmActual, String Combustible, double cantidadCombustible,double cantidadKWH,Date fecha) {
        this.id = id;
        this.placaVehiculo = placaVehiculo;
        this.kmActual = kmActual;
        this.Combustible = Combustible;
        this.cantidadCombustible = cantidadCombustible;
        this.cantidadKWH = cantidadKWH;
        this.fecha = fecha;
    }

    
    
    //getters

    public double getCantidadCombustible() {
        return cantidadCombustible;
    }

    public double getCantidadKWH() {
        return cantidadKWH;
    }

    

    public Date getFecha() {
        return fecha;
    }

    public int getId() {
        return id;
    }

    public double getKmActual() {
        return kmActual;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public String getCombustible() {
        return Combustible;
    }
    
    //setters

    public void setCantidadCombustible(double cantidadCombustible) {
        this.cantidadCombustible = cantidadCombustible;
    }

   

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKmActual(double kmActual) {
        this.kmActual = kmActual;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public void setCombustible(String Combustible) {
        this.Combustible = Combustible;
    }

    public void setCantidadKWH(double cantidadKWH) {
        this.cantidadKWH = cantidadKWH;
    }
    
    
    public boolean eliminarBoleta(String id){
        
        return true;
    }
    
    
}
