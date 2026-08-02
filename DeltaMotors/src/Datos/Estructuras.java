/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.util.ArrayList;

/**
 *
 * @author triamus
 */
public class Estructuras {

    public static ArrayList<ObjUsuarios> listaUsuarios = new ArrayList<>();
    static ArrayList<ObjVehiculo> listaVehiculos = new ArrayList<>();
    static ArrayList<ObjMantenimiento> listaMantenimeinto = new ArrayList<>();
    static ArrayList<ObjBoletaTaller> listaBoletasTaller = new ArrayList<>();
    static ArrayList<ObjBoletaCombustible> listaBoletaCombustible = new ArrayList<>();
    static ArrayList<ObjAsignacionMantenimiento> listaAsignaciones = new ArrayList<>();
    private static int contadorUserId = 0;
    private static int contadorVehiculoId = 0;
    
    public Estructuras() {
    }
    
    /*---------------------------------------------------
    |--------------Metodos usuarios----------------------|
    ----------------------------------------------------*/
    public void agregarUsuarios(ObjUsuarios usuario){
        this.contadorUserId++;
        usuario.setId(contadorUserId);
        listaUsuarios.add(usuario);
        
    }
    
    public void modificarUsuario(int indice, ObjUsuarios usuario ){
        listaUsuarios.set(indice, usuario);
    }
    
    public void eliminarCliente(int indice){
        listaUsuarios.remove(indice);
    }
    
    public ArrayList<ObjUsuarios> consultarUsuarios(){
        
        return new ArrayList<ObjUsuarios>(listaUsuarios);
    }
    
    public void agregarVehiculo(ObjVehiculo vehiculo){
        this.contadorVehiculoId++;
        vehiculo.setId(contadorUserId);
        listaVehiculos.add(vehiculo);
        
    }
    
    
    
     public static ObjUsuarios buscarUsuarioPorId(String idBuscado) {
    for (ObjUsuarios user : listaUsuarios) { 
        if (user.getUsuario().equals(idBuscado)) {
            return user;
        }
    }
    return null;
}
     
      public static int usuarioRepetido(String nuevoUsuario){
         for (ObjUsuarios repeticion : listaUsuarios) {
             if (repeticion.getUsuario().equals(nuevoUsuario)) {
                 return 1;
             }
         }
         return -1;
    }
      
     public static ObjVehiculo buscarVehiculoPlaca(String placa){
         for(ObjVehiculo vehiculo: listaVehiculos){
             if(vehiculo.getPlaca().equals(placa)){
                 return vehiculo;
             }
         }
         return null;
     }
     
    
}
