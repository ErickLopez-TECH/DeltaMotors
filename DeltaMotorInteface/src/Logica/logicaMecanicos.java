/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Estructuras;
import Datos.objMecanicos;
import java.util.ArrayList;
/**
 *
 * @author triamus
 */
public class logicaMecanicos {
    private Estructuras est;
    public logicaMecanicos() {
        this.est = new Estructuras();
    }
    
    public ArrayList<objMecanicos> obtenerListaMecanicos() {
       
        est.leerArchivoMecanicos();
        return est.getListaMecanicos();
}
    
    public boolean mecanicoRepetido(String cedula){
        est.leerArchivoMecanicos();
        
        for (int i = 0; i < obtenerListaMecanicos().size(); i++) {
            objMecanicos m = obtenerListaMecanicos().get(i);
            
            if(m.getCedula().equalsIgnoreCase(cedula)){
               return true;
            }
        }
        return false;
    }
    
    public boolean registrarMecanico(String nombre,String cedula){
        objMecanicos nuevoMecanico = new objMecanicos(0, nombre, cedula);
        
        //estructura de guardado
        est.agregarMecanico(nuevoMecanico);
        return true;
    }
    
    public boolean eliminarMecanico(int id){
        return est.eliminarMecanico(id);
    }
    
    
}
