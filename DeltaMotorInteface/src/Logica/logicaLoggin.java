/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Estructuras;
import Datos.objUsuarios;
/**
 *
 * @author triamus
 */
public class logicaLoggin {
    public boolean  tipoRol(String nombre){
        Estructuras est = new Estructuras();
        est.leerArchivoUsuarios();
        
        for (int i = 0; i < est.getListaUsuarios().size(); i++) {
            objUsuarios u = est.getListaUsuarios().get(i);
            if (u.getNombre().equalsIgnoreCase(nombre) && u.getRol().equalsIgnoreCase("Administrador")) {
                return true;
            }
        }
        return false;
    }
    public boolean existenciaUser(String nombre,String contrasena){
        
        Estructuras est = new Estructuras();
        est.leerArchivoUsuarios();
        
        for (int i = 0; i < est.getListaUsuarios().size(); i++) {
            objUsuarios u = est.getListaUsuarios().get(i);
            
            if((u.getNombre().equalsIgnoreCase(nombre)) && u.getContrasena().equals(contrasena)){
                return true;
            }
        }
        return false;
    }
}
