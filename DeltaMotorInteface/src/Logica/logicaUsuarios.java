/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Estructuras;
import Datos.objUsuarios;
import javax.swing.JOptionPane;
/**
 *
 * @author triamus
 */
public class logicaUsuarios {
    
    
    public boolean registrarUsuario(String nombre,String cedula,  String contrasena, String rol, String estado) {
        // 1. Validación de campos obligatorios
        if (nombre.isEmpty() || contrasena.isEmpty() || cedula.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor complete todos los campos obligatorios, Erick.", "Atención", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // 2. CREAMOS EL OBJETO USANDO EL CONSTRUCTOR CON PARÁMETROS
        // (Le mandamos 0 en el ID porque Estructuras se encarga de calcularlo e inyectarlo de inmediato)
        objUsuarios nuevoUsuario = new objUsuarios(0, nombre, cedula, contrasena, rol,estado);

        // 3. Invocamos estructuras para procesarlo y guardarlo
        Estructuras est = new Estructuras();
        est.agregarUsuarios(nuevoUsuario);

        JOptionPane.showMessageDialog(null, "¡Usuario registrado y guardado con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public boolean existenciaUser(String nombre, String cedula){
        Estructuras est = new Estructuras();
        est.leerArchivoUsuarios();
        
        for (int i = 0; i < est.getListaUsuarios().size(); i++) {
            objUsuarios u = est.getListaUsuarios().get(i);
            
            if(u.getNombre().equalsIgnoreCase(nombre) || u.getCedula().equalsIgnoreCase(cedula)){
                return true;
            }
        }
        return false;
    }
}
