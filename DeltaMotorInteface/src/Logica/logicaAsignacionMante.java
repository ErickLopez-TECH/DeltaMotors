package Logica;

import Datos.Estructuras;
import Datos.objAsignacionMante;
import Datos.objVehiculo;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class logicaAsignacionMante {

    private Estructuras estructuras;

    public logicaAsignacionMante() {
        this.estructuras = new Estructuras();
    }

    /**
     * Valida si ya existe una asignación para el mismo vehículo con el mismo mantenimiento.
     */
    public boolean existeAsignacion(String placaVehiculo, String nombreMantenimiento) {
        estructuras.leerArchivoAsignacionMante();
        ArrayList<objAsignacionMante> lista = estructuras.getListaAsignacionesMante();
        
        for (objAsignacionMante a : lista) {
            if (a.getPlacaVehiculo().equalsIgnoreCase(placaVehiculo.trim()) && 
                a.getNombreMantenimiento().equalsIgnoreCase(nombreMantenimiento.trim())) {
                return true; // Ya existe esta combinación
            }
        }
        return false;
    }

    /**
     * Registra una nueva asignación validando que no esté duplicada.
     */
    public boolean registrarAsignacion(String placaVehiculo, String nombreMantenimiento, String tipoPeriodo, 
                                       double numPeriodicidad, double kmUltimo, Date ingreso, Date vencimiento) {
        try {
            // Validación de duplicados por placa y mantenimiento
            if (existeAsignacion(placaVehiculo, nombreMantenimiento)) {
                return false; // Retorna falso indicando que ya existe
            }

            objAsignacionMante nuevaAsignacion = new objAsignacionMante(
                0, // El ID se calcula automáticamente en Estructuras
                placaVehiculo, 
                nombreMantenimiento, 
                tipoPeriodo, 
                numPeriodicidad, 
                kmUltimo, 
                ingreso, 
                vencimiento
            );
            
            estructuras.agregarAsignacionMante(nuevaAsignacion);
            return true;
        } catch (Exception e) {
            System.err.println("Error en la capa lógica al registrar asignación: " + e.getMessage());
            return false;
        }
    }

    /**
     * Modifica una asignación existente en la lista y actualiza el archivo.
     */
    public boolean modificarAsignacion(int id, String tipoPeriodo, double periodicidad, double km) {
    return estructuras.modificarAsigna(id, tipoPeriodo, periodicidad, km);
}

    public ArrayList<objAsignacionMante> obtenerListaAsignaciones() {
        estructuras.leerArchivoAsignacionMante();
        return estructuras.getListaAsignacionesMante();
    }

    public boolean eliminarAsignacion(int id) {
        return estructuras.eliminarAsignacionManteArchivo(id);
    }
    
   
    
    //Directamente a reportes de dias
    public ArrayList listaFiltradaDias() {
    ArrayList listaFiltrada = new ArrayList<>();
    
    //ajusta fecha para las 23:59 sin dejar nada por fuera del dia de hoy
    Calendar cal = Calendar.getInstance();
    cal.set(Calendar.HOUR_OF_DAY, 23);
    cal.set(Calendar.MINUTE, 59);
    cal.set(Calendar.SECOND, 59);
    cal.set(Calendar.MILLISECOND, 999);
    Date fechaHoy = cal.getTime();
    
    
    
    
    for (objAsignacionMante mante : obtenerListaAsignaciones()) {
        if (mante.getTipoPeriodo().equalsIgnoreCase("Dias")) {
            
            long diferenciaMilisegundos = fechaHoy.getTime() - mante.getVencimiento().getTime();
            long diasPasados = diferenciaMilisegundos / (1000*60*60*24);
            
            if (diasPasados < 0) {
                diasPasados = 0;
            }
            
            
            //formato de fecha sencilla
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            
            String fechaConsultaStr = sdf.format(fechaHoy);
            String fechaVencimientoStr = sdf.format(mante.getVencimiento());
            
            //comprobar si fecha es <= a la de hoy
            if (mante.getVencimiento() != null && !mante.getVencimiento().after(fechaHoy)) {
                Object[] fila ={
                    mante.getPlacaVehiculo(),
                    mante.getNombreMantenimiento(),
                    fechaConsultaStr,
                    fechaVencimientoStr,
                    diasPasados
                };
                listaFiltrada.add(fila);
            }
            
        }
    }
    
    return listaFiltrada;
}
    
    
}