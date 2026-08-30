/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author triamus
 */
public class objVehiculo {
    
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private String combustible;
    private double kilometraje;
    private int anio;
    private String estado;
    
    public objVehiculo(int id,String placa,String marca,String modelo,String tipoMotor,String combustible,
            double kilometraje,int anio,String estado){
        
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.combustible = combustible;
        this.kilometraje = kilometraje;
        this.anio = anio;
        this.estado = estado;
        
    }
    
    public objVehiculo(){
        this.id = 0;
        this.placa = "";
        this.marca = "";
        this.modelo = "";
        this.tipoMotor = "";
        this.combustible = "";
        this.kilometraje = 0;
        this.anio = 0;
        this.estado = "";
    }
    
    //getters

    public int getAnio() {
        return anio;
    }

    public String getCombustible() {
        return combustible;
    }

    public String getEstado() {
        return estado;
    }

    public int getId() {
        return id;
    }

    public double getKilometraje() {
        return kilometraje;
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

    public String getTipoMotor() {
        return tipoMotor;
    }
    
    //setters

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKilometraje(double kilometraje) {
        this.kilometraje = kilometraje;
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

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }
    
    
}
