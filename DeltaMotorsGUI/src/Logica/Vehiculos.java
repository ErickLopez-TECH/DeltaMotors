/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjVehiculo;
import static Logica.Usuarios.Almacen;
import java.util.ArrayList;

/**
 *
 * @author triamus
 */
public class Vehiculos {
    
    // Arreglo base de marcas para poblar los ComboBox de la interfaz
    public static String[] marcas = { 
        "Toyota", "Hyundai", "Nissan", "Kia", "Honda", 
        "Mitsubishi", "Suzuki", "Mazda", "Ford", "Chevrolet" 
    };
    
    static String[][] datosModelos = new String[100][2];
    static int contadorModelos = 0;

    /**
     * Valida si una placa ya se encuentra registrada en el sistema.
     * @param placaNueva Placa a evaluar.
     * @return 1 si existe (repetida), -1 si está disponible.
     */
    public static int placaRepetida(String placaNueva) {
        ArrayList<ObjVehiculo> misVehiculos = Almacen.listarVehiculos();
        
        for (int i = 0; i < misVehiculos.size(); i++) {
            if (misVehiculos.get(i).getPlaca().equalsIgnoreCase(placaNueva)) {
                return 1; // Encontrada / Repetida
            }
        }
        return -1; // No existe
    }
    
    /**
     * Retorna los modelos correspondientes en formato de arreglo según la marca seleccionada.
     * Útil para llenar el JComboBox de modelos de forma dinámica en la interfaz.
     */
    public static String[] obtenerModelosPorMarca(int opcionMarca) {
        switch (opcionMarca) {
            case 1:  return new String[]{"Hilux", "Corolla Cross", "Yaris", "Prado", "Raize"};
            case 2:  return new String[]{"Tucson", "Elantra", "Santa Fe", "Creta", "Accent"};
            case 3:  return new String[]{"Frontier", "Sentra", "Kicks", "X-Trail", "Versa"};
            case 4:  return new String[]{"Sportage", "Rio", "Seltos", "Sorento", "Picanto"};
            case 5:  return new String[]{"CR-V", "Civic", "HR-V", "Pilot", "Fit"};
            case 6:  return new String[]{"Montero", "L200", "ASX", "Outlander", "Mirage"};
            case 7:  return new String[]{"Vitara", "Swift", "Jimny", "S-Cross", "Ertiga"};
            case 8:  return new String[]{"CX-5", "Mazda 3", "CX-30", "BT-50", "Mazda 2"};
            case 9:  return new String[]{"Ranger", "Escape", "Explorer", "Edge", "F-150"};
            case 10: return new String[]{"Tracker", "Colorado", "Tahoe", "Captiva", "Onix"};
            default: return new String[]{};
        }
    }

    /**
     * Devuelve el texto del tipo de vehículo según la opción numérica del combo.
     */
    public static String obtenerTipoVehiculo(int tipo) {
        String[] tipoVehiculo = {"Electrico", "Combustion", "Hibrido"};
        if (tipo >= 1 && tipo <= tipoVehiculo.length) {
            return tipoVehiculo[tipo - 1];
        }
        return "Desconocido";
    }
    
    /**
     * Devuelve el texto del tipo de combustible según la opción.
     */
    public static String obtenerTipoCombustible(int tipo) {
        String[] tipoCombustible = {"Gasolina", "Diesel"};
        if (tipo >= 1 && tipo <= tipoCombustible.length) {
            return tipoCombustible[tipo - 1];
        }
        return "Gasolina";
    }
    
    /**
     * Convierte el código numérico del estado en texto legible para el objeto.
     */
    public static String estadoVehiculo(int estado) {
        switch (estado) {
            case 1:  return "Activo";
            case 2:  return "Mantenimiento";
            case 3:  return "Inactivo";
            default: return "Activo";
        }
    }
    
    public void guardarVehiculo(ObjVehiculo vehiculoRecibido){
        Almacen.agregarVehiculo(vehiculoRecibido);
        Almacen.escribeArchivoVehiculos();
        
    }
}