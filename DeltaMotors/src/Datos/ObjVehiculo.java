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
    private int kilometroActual;//concexion con reportes por km
    
    
    //contructor
    public ObjVehiculo() {
        
        this.id = 0;
        this.placa = "";
        this.marca = "";
        this.modelo = "";
        this.anio = 0;
        this.kilometroActual= 0;
    }
    
       
    public ObjVehiculo(int idVehiculo, String placa, String marca, String modelo, int anio, int kilometrajeActual) {
    this.id = idVehiculo;
    this.placa = placa;
    this.marca = marca;
    this.modelo = modelo;
    this.anio = anio;
    this.kilometroActual= kilometrajeActual;
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
    
    

 
    //para archivo .txt
    public String aTextoArchivo() {
        return id + ";" + placa + ";" + marca + ";" + modelo + ";" + anio + ";" + kilometroActual;
    }
    
}
