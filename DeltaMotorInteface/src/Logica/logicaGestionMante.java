/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.objGestionMante;
import Datos.Estructuras;
import java.util.ArrayList;
/**
 *
 * @author triamus
 */
public class logicaGestionMante {
    
    
    public boolean registrarMante(String nombre,String estado){
        
        objGestionMante nuevoMante = new objGestionMante(0, nombre, estado);
        
        Estructuras est = new Estructuras();
        est.agregarMante(nuevoMante);
        
        return true;
    }
    
    public boolean existenciaMante(String nombre){
        Estructuras est = new Estructuras();
        est.leerArchivoMante();
        
        for (int i = 0; i < est.getListaMantes().size(); i++) {
            objGestionMante mante = est.getListaMantes().get(i);
            
            if(mante.getNombre().equalsIgnoreCase(nombre)){
                return true;
            }
        }
        return false;
    }
    
    public ArrayList<objGestionMante> obtenerListaVehiculos() {
        Estructuras est = new Estructuras();
        est.leerArchivoMante();
        return est.getListaMantes();
}
}
