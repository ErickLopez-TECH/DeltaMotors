/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Estructuras;
import static Datos.Estructuras.listaVehiculos;
import Datos.ObjVehiculo;
import static Logica.LogUsuario.Almacen;
/**
 *
 * @author triamus
 */
public class LogVehiculo {
    static String[] marcas = new String[10];
    static String[] modelos = new String[100];
    
public static int placaRepetida(String vehiculoNuevo){
    for(ObjVehiculo repetido: listaVehiculos){
        if(repetido.getPlaca().equals(vehiculoNuevo)){
            return 1;
        }
    }
    return -1;
}
    
   
public static void ingresarVehiculo(String placa, String marca, String modelo, int anio, String tipoVehiculo, 
        String combustible) {
    
    ObjVehiculo nuevoVehiculo = new ObjVehiculo();
    
   
    
    nuevoVehiculo.setPlaca(placa);
    nuevoVehiculo.setMarca(marca);
    nuevoVehiculo.setModelo(modelo);
    nuevoVehiculo.setAnio(anio);
    nuevoVehiculo.setTipoVehiculo(tipoVehiculo);
    nuevoVehiculo.setCombustible(combustible);
    nuevoVehiculo.setEstado("Activo");
    nuevoVehiculo.setKilometroActual(0);

   
    Almacen.agregarVehiculo(nuevoVehiculo);
}
}
