/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author triamus
 */
public class ObjAsignacionMantenimiento {
    private int id;
    private String PlacaVehiculo; //referencia al obj vehiculo
    private String nombreMantenimiento; //referencia al obj mantenimiento
    private String tipoPeriodo; //km/dias
    private double numPeriodicidad;
    private float kmUltimo;
    
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

    public float getKmUltimo() {
        return kmUltimo;
    }

    public void setKmUltimo(float kmUltimo) {
        this.kmUltimo = kmUltimo;
    }
    

    
}
