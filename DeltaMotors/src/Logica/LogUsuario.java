/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Estructuras;

import Datos.ObjUsuarios;
import java.util.Scanner;
/**
 *
 * @author triamus
 */
public class LogUsuario {
    public static Estructuras Almacen = new Estructuras();
    
    public static int usuarioRepetido(String nuevoUsuario){
         for (ObjUsuarios repeticion : Estructuras.listaUsuarios) {
             if (repeticion.getUsuario() != null && repeticion.getUsuario().equals(nuevoUsuario)) {
                 return 1;
             }
         }
         return -1;
    }
    
   


    // Método que procesa el registro completo una vez validado
    public static void registrarNuevoUsuario(int id, String usuario, int opcionRol, String password) {
        ObjUsuarios nuevoUsuario = new ObjUsuarios();
        nuevoUsuario.setId(id);
        nuevoUsuario.setUsuario(usuario);

        if (opcionRol == 1) {
            nuevoUsuario.setRol("operador");
        } else if (opcionRol == 2) {
            nuevoUsuario.setRol("Admin");
        }

        nuevoUsuario.setContrasena(password);
        nuevoUsuario.setEstado("Activo");

        Almacen.agregarUsuarios(nuevoUsuario);
    }
}

