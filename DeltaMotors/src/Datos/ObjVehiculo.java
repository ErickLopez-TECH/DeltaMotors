/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author triamus
 */
public class ObjVehiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private String estado;
    private int kilometroActual;//concexion con reportes por km
    private String tipoVehiculo;
    private String combustible;
    
    //contructor
    public ObjVehiculo() {
        
        this.id = 0;
        this.placa = "";
        this.marca = "";
        this.modelo = "";
        this.anio = 0;
        this.estado = "";
        this.kilometroActual= 0;
        this.tipoVehiculo = "";
        this.combustible = "";
    }
    
       
    
    
    //-------------------------------------------------
    //------------------getters------------------------
    //-------------------------------------------------
    public int getAnio() {    
        return anio;
    }

    public int getId() {
        return id;
    }

    public int getKilometroActual() {
        return kilometroActual;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getEstado() {
        return estado;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public String getCombustible() {
        return combustible;
    }
    
    
    
    
    
    //-------------------------------------------------
    //------------------setters------------------------
    //-------------------------------------------------

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKilometroActual(int kilometroActual) {
        this.kilometroActual = kilometroActual;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }
    
    
}
