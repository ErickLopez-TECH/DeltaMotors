/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import static Logica.Usuarios.leer;
import Datos.ObjAsignacionMantenimiento;
import static Logica.Usuarios.Almacen;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author triamus
 */
public class MenuReportes {
 
    
    
    
    public static void Reportes(){
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|          REPORTES DEL VEHICULO       |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-3) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Por Fechas");
            System.out.println("2. Por Kilometros");
            System.out.println("3. Regresar");
           
            
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
            case 1: reportesFechas();
                
                break;
            case 2: reportesKm();
                break;
            
            case 3: return;
            
        }
            
        } while (opcion !=3);
        
        
    }
    
    //muestra solo km
   public static ArrayList<ObjAsignacionMantenimiento> muestraKM(){
        ArrayList<ObjAsignacionMantenimiento> listaCompleta = Almacen.listarAsignacion();
        ArrayList<ObjAsignacionMantenimiento> listaKm = new ArrayList<>();
        
        for (int i = 0; i < listaCompleta.size(); i++) {
            ObjAsignacionMantenimiento asignacion = listaCompleta.get(i);
            double kmActual = listaCompleta.get(i).getKmUltimo();
            double kmProximo = listaCompleta.get(i).getNumPeriodicidad();
            double diferencia = kmActual - kmProximo;
            
            if (asignacion.getTipoPeriodo().equalsIgnoreCase("Kilometros") && diferencia >=0) {
                listaKm.add(asignacion);
            }
        }
        
        return listaKm;
    }
   
   //funcion para mostarr fechas en loo de reportes 
   public static ArrayList<ObjAsignacionMantenimiento> muestraFecha() {
    ArrayList<ObjAsignacionMantenimiento> listaCompleta = Almacen.listarAsignacion();
    ArrayList<ObjAsignacionMantenimiento> listaFecha = new ArrayList<>();
    
    try {
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yy");
        Date fechaHoy = simpleDate.parse(simpleDate.format(new Date()));

        for (int i = 0; i < listaCompleta.size(); i++) {
            ObjAsignacionMantenimiento asignacion = listaCompleta.get(i);
            
            // Validamos que tenga fecha de vencimiento 
            if (asignacion.getTipoPeriodo().equals("Dias") && asignacion.getVencimiento() != null ) {
                
                Date fechaVencimiento = simpleDate.parse(simpleDate.format(asignacion.getVencimiento()));
                
                // Comparamos: si la fecha de hoy es igual o despues al vencimiento (diferencia >= 0)
                // Usamos compareTo: si hoy.compareTo(vencimiento) >= 0 significa que ya se pasó o es hoy.
                int comparacion = fechaHoy.compareTo(fechaVencimiento);
                
                if (comparacion >= 0) {
                    listaFecha.add(asignacion);
                }
            }
        }
    } catch (ParseException e) {
        System.out.println("[!] Error al procesar las fechas: " + e.getMessage());
    }
    
    return listaFecha;
}
   
   
        
 
   
   
            
            
    public static void reportesKm() {
    
    ArrayList<ObjAsignacionMantenimiento> lista = muestraKM();
    
    System.out.println("=======================================================================================");
    System.out.println("|                         LISTA DE REPORTES POR KILOMETROS                            |");
    System.out.println("=======================================================================================");
    System.out.println("");

    
    if (lista.size() == 0) {
        System.out.println("[!] No hay reportes registrados.");
    } else {
        // 1. Definimos los anchos mínimos iniciales basados en los títulos de los encabezados
        int maxPlaca = "Vehiculo".length();       // Mínimo 8
        int maxNombre = "Mantenimiento".length(); // Mínimo 13
        int maxActual = "Actual".length();        // Mínimo 6
        int maxProximo = "Proximo".length();      // Mínimo 7
        int maxDiferencia = "Diferencia".length();// Mínimo 10

        // 2. Recorremos la lista para encontrar los textos y números más largos
        for (int i = 0; i < lista.size(); i++) {
            ObjAsignacionMantenimiento a = lista.get(i);
            
            if (a.getPlacaVehiculo().length() > maxPlaca) {
                maxPlaca = a.getPlacaVehiculo().length();
            }
            if (a.getNombreMantenimiento().length() > maxNombre) {
                maxNombre = a.getNombreMantenimiento().length();
            }
            
            
            String sActual = String.format("%.2f", a.getKmUltimo());
            if (sActual.length() > maxActual) {
                maxActual = sActual.length();
            }
            
            String sProximo = String.format("%.2f", a.getNumPeriodicidad());
            if (sProximo.length() > maxProximo) {
                maxProximo = sProximo.length();
            }
            
            double diferencia = a.getKmUltimo() - a.getNumPeriodicidad();
            String sDiferencia = String.format("%.2f", diferencia);
            if (sDiferencia.length() > maxDiferencia) {
                maxDiferencia = sDiferencia.length();
            }
        }

        
        String formato = "%%-%ds | %%-%ds | %%%ds | %%%ds | %%%ds\n";
        formato = String.format(formato, maxPlaca, maxNombre, maxActual, maxProximo, maxDiferencia);

        // Imprimir Encabezado
        System.out.printf(formato, "Vehiculo", "Mantenimiento", "Actual", "Proximo", "Diferencia");
        
        // Línea divisora dinámica basada en la suma de los anchos de las columnas
        int totalAncho = maxPlaca + maxNombre + maxActual + maxProximo + maxDiferencia + 16;
        for (int j = 0; j < totalAncho; j++) {
            System.out.print("-");
        }
        System.out.println();

        //  Imprimir Filas con los datos formateados
        for (int i = 0; i < lista.size(); i++) {
            ObjAsignacionMantenimiento a = lista.get(i);
            
            double kmActual = a.getKmUltimo();
            double kmProximo = a.getNumPeriodicidad();
            double diferencia = kmActual - kmProximo;
            
            System.out.printf(formato,
                a.getPlacaVehiculo(),
                a.getNombreMantenimiento(),
                String.format("%.2f", kmActual),
                String.format("%.2f", kmProximo),
                String.format("%.2f", diferencia)
            );
        }
    }
    System.out.println("---------------------------------------------------------------------------------------");

    }
    
    public static void reportesFechas() {
    ArrayList<ObjAsignacionMantenimiento> lista = muestraFecha();
    SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yy");
    
    System.out.println("=======================================================================================");
    System.out.println("|                         LISTA DE REPORTES POR FECHAS                                |");
    System.out.println("=======================================================================================");
    System.out.println("");

    if (lista.size() == 0) {
        System.out.println("[!] No hay reportes registrados.");
    } else {
        // 1. Definimos anchos mínimos basados en títulos
        int maxPlaca = "Vehiculo".length();
        int maxNombre = "Mantenimiento".length();
        int maxIngreso = "Ingreso".length();
        int maxVencimiento = "Vencimiento".length();
        int maxDias = "Dias".length(); // Nueva columna para la diferencia

        // 2. Recorremos para encontrar los anchos máximos
        try {
            Date hoy = simpleDate.parse(simpleDate.format(new Date()));
            for (int i = 0; i < lista.size(); i++) {
                ObjAsignacionMantenimiento a = lista.get(i);
                
                if (a.getPlacaVehiculo().length() > maxPlaca){
                    maxPlaca = a.getPlacaVehiculo().length();
                }
                if (a.getNombreMantenimiento().length() > maxNombre){
                    maxNombre = a.getNombreMantenimiento().length();
                }
                
                
               
                
            }
        } catch (ParseException e) { /* Manejo de error */ }

        // 3. Definimos formato dinámico
        String formato = "%%-%ds | %%-%ds | %%-%ds | %%-%ds | %%%ds\n";
        formato = String.format(formato, maxPlaca, maxNombre, maxIngreso, maxVencimiento, maxDias);

        // Imprimir encabezado
        System.out.printf(formato, "Vehiculo", "Mantenimiento", "Ingreso", "Vencimiento", "Dias");
        
        // Línea divisora dinámica (Suma de anchos + separadores)
        int totalAncho = maxPlaca + maxNombre + maxIngreso + maxVencimiento + maxDias + 16;
        for (int j = 0; j < totalAncho; j++) System.out.print("-");
        System.out.println();

        // Imprimir filas
        try {
            Date hoy = simpleDate.parse(simpleDate.format(new Date()));
            for (int i = 0; i < lista.size(); i++) {
                ObjAsignacionMantenimiento a = lista.get(i);
                Date venci = simpleDate.parse(simpleDate.format(a.getVencimiento()));
                long dias = (venci.getTime() - hoy.getTime()) / (1000 * 60 * 60 * 24);
                dias = dias * -1;
                
                System.out.printf(formato,
                    a.getPlacaVehiculo(),
                    a.getNombreMantenimiento(),
                    simpleDate.format(a.getIngreso()),
                    simpleDate.format(a.getVencimiento()),
                    String.valueOf(dias)
                );
            }
        } catch (ParseException e) { /* Manejo de error */ }
    }
    System.out.println("---------------------------------------------------------------------------------------");
}
}
