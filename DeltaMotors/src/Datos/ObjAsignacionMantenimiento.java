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
    private int idVehiculo; //referencia al obj vehiculo
    private int idMantenimiento; //referencia al obj mantenimiento
    private String tipoPeriodo; //km/dias
    private String frecuencia;
    
    public ObjAsignacionMantenimiento(){
        this.idVehiculo = 0;
        this.idMantenimiento = 0;
        this.tipoPeriodo = "";
        this.frecuencia = "";
       
    }
    
    //---4.Metodos acciones
   
    
    public int getIdVehiculo() {
        return idVehiculo;
    }
    
    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }
    
    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public String getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(String tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
    

    
}
