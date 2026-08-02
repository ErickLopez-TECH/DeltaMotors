/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author triamus
 */
public class ObjBoletaCombustible {
    
    private String id;
    private String placaVehiculo; //conexion con vehiculos
    private int kmActual;
    private double cantidadCombustible;
    private String tipoCombustible;
    private String fecha;

    public ObjBoletaCombustible(){
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getKmActual() {
        return kmActual;
    }

    public void setKmActual(int kmActual) {
        this.kmActual = kmActual;
    }

     public String getFecha() {
        return fecha;
    }
     
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

     public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

   
    
    
    
}
