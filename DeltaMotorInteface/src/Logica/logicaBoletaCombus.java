/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.Estructuras;
import Datos.objBoletaCombus;
import Datos.objVehiculo;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author triamus
 */
public class logicaBoletaCombus {
    
    /*
    1.Obtener datos del tipo de motor para solamente mostrar la lista correspondiente de si es gasolina o kwh
    2.pasar parametros de modificar,eliminar,lista y ingresar
    */
    private Estructuras est;

    public logicaBoletaCombus() {
        
        this.est = new Estructuras();
    }
    
    
    //selecciona el tipo de motor para mostarrle explicitamente las opciones
    //Ejemplo: motor de combustion interna le muestra el el cmb solo Super o Regular por ser las subdivisiones
    public int opcionesCombus(String placa){
        logicaVehiculo logicaV = new logicaVehiculo();
        
        for (objVehiculo v : logicaV.obtenerListaVehiculos()) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                
                
                if(v.getTipoMotor().equalsIgnoreCase("Combustion Interna")){
                    return 1;
                }
                
                if(v.getTipoMotor().equalsIgnoreCase("Electrico")){
                    return 2;
                }
                
                if(v.getTipoMotor().equalsIgnoreCase("Hibrido")){
                    return 3;
                }
            }
        }
        
        return 0;
        
    }
    
    
    //gaurdar boleta
    public boolean registrarBoletaCombus(String placa,String tipoCombus,double cantidadC,double cantidadKWH,
            double km,Date fecha){
        
        objBoletaCombus nuevaBoleta = new objBoletaCombus(0, placa, km, tipoCombus, cantidadC, cantidadKWH, fecha);
        
        est.agregarBoletaCombus(nuevaBoleta);
        return true;
    }
    
    //actualiza el km Asiganciones para amntener todo actual
   public boolean actualizarKmAsigancion(String placa, double kmActual){
       return est.actualizarKmVehiculoAsigna(placa, kmActual);
   }
    
   
   //obtener lista
   public ArrayList<objBoletaCombus> obtenerListaBoleta() {
        est.leerArchivoBoletaCombus();
        return est.getListaBoletaCombus();
    }
   
   public boolean eliminarBoletaCombus(int idBoleta){
       
       return est.eliminarBoletaCombus(idBoleta);
   }
   
   
}
