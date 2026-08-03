/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.Estructuras;
import Datos.ObjUsuarios;
import  Logica.LogUsuario;
import java.util.ArrayList;

public class LogLoggin {

    
    
    

   
    static {
        ObjUsuarios adminPorDefecto = new ObjUsuarios();
        adminPorDefecto.setUsuario("admin");
        adminPorDefecto.setContrasena("1234");
        adminPorDefecto.setRol("Admin");
        adminPorDefecto.setEstado("Activo");
        
        // Llamada explícita a la clase y su método de almacenamiento
        LogUsuario.Almacen.agregarUsuarios(adminPorDefecto);
    }
    
    

    
    public static void agregarUsuarios(ObjUsuarios usuario) {
    if (usuario != null) {
        Estructuras.contadorUserId++;
        usuario.setId(Estructuras.contadorUserId);
        LogUsuario.Almacen.agregarUsuarios(usuario);
    }
}

    public static int existenciaUser(String user){
        for (ObjUsuarios u : Estructuras.listaUsuarios) {
            if(u != null && u.getUsuario() != null && u.getUsuario().equals(user)){
                return 1;
            }
        }
        return -1;
    }

    // Método completo de validación de Login (Verifica usuario y contraseña)
    public static int validarLogin(String user, String pass) {
        for (ObjUsuarios u : Estructuras.listaUsuarios) {
            if (u.getUsuario()!= null && u.getUsuario().equals(user) && u.getContrasena().equals(pass)) {
                
                return 1; //correctas
            }
        }
        return -1; // Contraseña incorrecta o usuario no encontrado
    }
    
    public static String obtenerRol(String rol){
        for (ObjUsuarios u : Estructuras.listaUsuarios) {
            if(u.getUsuario() != null && u.getUsuario().equals(rol)){
                return u.getRol();
            }
        }
        return "Sin rol";
    }
    
    public static String obtenerEstado(String estado){
        for (ObjUsuarios u : Estructuras.listaUsuarios) {
            if(u.getUsuario().equals(estado)){
                return u.getEstado();
            }
        }
        return "null";
    }
}
