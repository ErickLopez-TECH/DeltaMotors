/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.objVehiculo;
import Datos.Estructuras;
import java.util.ArrayList;
/**
 *
 * @author triamus
 */
public class logicaVehiculo {
    
   /* private int id;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private String combustible;
    private double kilometraje;
    private int anio;
    private String estado;*/
    public boolean registrarVehiculo( String placa,String marca, String modelo,String tipoMotor,
        String combustible, double kilometraje,int anio,String estado){
        
        //objeto de configuracion
        objVehiculo nuevoVehiculo = new objVehiculo(0, placa, marca, modelo, tipoMotor, combustible, kilometraje, anio, estado);
        
        //estructura de guardado
        Estructuras est = new Estructuras();
        est.agregarVehiculo(nuevoVehiculo);
        
        return true;
    }
    
    public boolean existenciaVehiculo(String placa){
        Estructuras est = new Estructuras();
        est.leerArchivoVehiculo();
        
        for (int i = 0; i <est.getListaVehiculo().size(); i++) {
            objVehiculo v = est.getListaVehiculo().get(i);
            
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return true;
            }
        }
        return false;
        
    }
    
    public ArrayList<objVehiculo> obtenerListaVehiculos() {
        Estructuras est = new Estructuras();
        est.leerArchivoVehiculo();
        return est.getListaVehiculo();
}
}


