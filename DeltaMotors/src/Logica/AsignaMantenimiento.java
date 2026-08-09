/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import static Logica.Usuarios.leer;
import Datos.Estructuras;
import Datos.ObjVehiculo;
import Datos.ObjAsignacionMantenimiento;
import Datos.ObjMantenimiento;
import static Logica.Usuarios.Almacen;
import java.util.ArrayList;
/**
 *
 * @author triamus
 */
public class AsignaMantenimiento {
 
    
    public static void gestionAsignacion(){
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|       ASIGNACION MANTENIMIENTOS      |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-4) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Ingresar ");
            System.out.println("2. Modificar");
            System.out.println("3. ELiminar"); //TAREA -- id Identificación
            System.out.println("4. Buscar");
            System.out.println("5. Regresar");
            
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
            case 1: addAsignacion();
                
                break;
            case 2:;
                break;
            case 3:;
                
                break;
            case 4: ;
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
    // metodos para vehiculos
    public static int buscarPlaca(String placa){
       
        ArrayList<ObjVehiculo> misVehiculos= new ArrayList<>();
        misVehiculos = Almacen.listarVehiculos();
        int id;
        
        for (int i = 0; i < misVehiculos.size(); i++) {
            ObjVehiculo v = new ObjVehiculo();
            v = misVehiculos.get(i);
            
            if(v.getPlaca().equals(placa)){
                id = v.getId();
                return id;
            }
        }
        return -1;
    }
    
    public static int obtenerEstadoVehi(String placa){
        
        ArrayList<ObjVehiculo> misVehiculos = new ArrayList<>();
        misVehiculos = Almacen.listarVehiculos();
        
        for (int i = 0; i <misVehiculos.size(); i++) {
            ObjVehiculo v = misVehiculos.get(i);
            if (v.getPlaca().equals(placa) && v.getEstado().equals("Inactivo")) {
                return 1;//true
            }
        }
        
        return -1;//false
   }
    
    public static int kmActual(String placa){
        ArrayList<ObjVehiculo> miVehi = new ArrayList<>();
        miVehi = Almacen.listarVehiculos();
        
        int kmActual = 0;
        for (int i = 0; i < miVehi.size(); i++) {
            ObjVehiculo v = new ObjVehiculo();
            v = miVehi.get(i);
            if(v.getPlaca().equals(placa))
              kmActual = v.getKilometroActual();
            return kmActual;
        }
        return -1;
    }
    
    //metodos para mantenimeintos gestion
    public static String obtenerMantenimiento(int id){
        ArrayList<ObjMantenimiento> misMante = new ArrayList<>();
        misMante = Almacen.listarMantenimiento();
        
        String nombre;
        for (int i = 0; i < misMante.size(); i++) {
            ObjMantenimiento m = misMante.get(i);
            if (m.getId() == id) {
               nombre = m.getNombre();
               return nombre;
            }
        }
        return "No se encontro";
    }
    
    public static void mostrarMantenimientos() {
    System.out.println("=======================================");
    System.out.println("|      MANTENIMIENTOS DISPONIBLES     |");
    System.out.println("=======================================");
    
    // 1. Obtener y mostrar la lista de mantenimientos
    ArrayList<ObjMantenimiento> listaMaint = Almacen.listarMantenimiento();
    
    for (int i = 0; i < listaMaint.size(); i++) {
        ObjMantenimiento m = listaMaint.get(i);
        
        if (m.getEstado().equals("Activo")) {
            System.out.println("ID: " + m.getId() + " - Nombre: " + m.getNombre());
        }
    }
    
    }
    
    public static int obtenerExistenciaId(int id){
        ArrayList<ObjMantenimiento> misMante = new ArrayList<>();
        misMante = Almacen.listarMantenimiento();
        
        for (int i = 0; i < misMante.size(); i++) {
            ObjMantenimiento m = new ObjMantenimiento();
            m = misMante.get(i);
            
            
            if(m.getId() == id){
                
                return 1;
            }
        }
        return -1;
    }
    
    public static String devolverNombre(int id){
        ArrayList<ObjMantenimiento> misMante = new ArrayList<>();
        misMante = Almacen.listarMantenimiento();
        
        String nombre = "";
        for (int i = 0; i < misMante.size(); i++) {
            ObjMantenimiento m = new ObjMantenimiento();
            m = misMante.get(i);
            
            
            if(m.getId() == id){
                nombre = m.getNombre();
            return nombre;
        }
        
    }
    return "Error";
}
    
    public static int estadoInactivoMante(int id){
        
        ArrayList<ObjMantenimiento> misMante = new ArrayList<>();
        misMante = Almacen.listarMantenimiento();
        
        for (int i = 0; i <misMante.size(); i++) {
            ObjMantenimiento m = misMante.get(i);
            if ((m.getId() == id)&&(m.getEstado().equals("Inactivo"))) {
                return 1;//true
            }
        }
        return -1;
        
       
    }

    
    //asignacion de mantenimeinto
    public static String periodicidad(int opcion){
        String tipoPerido ="";
        if(opcion == 1){
            tipoPerido = "Kilometros";
        }
        if(opcion == 2){
            tipoPerido = "Dias";
        }
        return tipoPerido;
    }
    
    public static int mantenimientoRepetido(String placa, String nombreMante){
        ArrayList<ObjAsignacionMantenimiento> miAsignacion = new ArrayList<>();
        miAsignacion = Almacen.listarAsignacion();
        
        for (int i = 0; i < miAsignacion.size(); i++) {
            ObjAsignacionMantenimiento asigna = new ObjAsignacionMantenimiento();
            asigna = miAsignacion.get(i);
            if((asigna.getPlacaVehiculo().equals(placa)) && (asigna.getNombreMantenimiento().equals(nombreMante))){
                return 1;
            }
            
        }
        return -1;
    }
    
    public static void consultar(){
         System.out.println("===================================");
        System.out.println("|    Asignacion de Mantenimiento  |");
        System.out.println("===================================");
        System.out.println("");
    }
    
    public static void addAsignacion(){
        
        System.out.println("===================================");
        System.out.println("|    Asignacion de Mantenimiento  |");
        System.out.println("===================================");
        System.out.println("");
        ObjAsignacionMantenimiento nuevaAsigna = new ObjAsignacionMantenimiento();
        
        leer.nextLine();
        System.out.println("Digite la placa del vehiculo que desea asignar el mantenimiento");
        System.out.print("Placa: ");
        String placa = leer.nextLine();
        
        int idVehiculo = buscarPlaca(placa);
        if (buscarPlaca(placa)==-1) {
            System.out.println("No existe");
            return;
        }
        
        if (obtenerEstadoVehi(placa) == 1) {
            System.err.println("[!] Su vehiculo esta inactivo");
        }else{
            nuevaAsigna.setPlacaVehiculo(placa);
            
            int id;
            do {                
            mostrarMantenimientos();
            System.out.println("Digite el id del mantenimiento");
            System.out.print("Id: ");
            id = leer.nextInt();
            
                if (obtenerExistenciaId(id) != 1) {
                    System.out.println("");
                    System.out.println("[!] Digite un id existente");
                    System.out.println("Mostrando lista....");
                    System.out.println("");
                }
            }while(obtenerExistenciaId(id) != 1);
            
            
            
            if (estadoInactivoMante(id) == 1) {
                System.out.println("[!] El mantenimiento esta inactivo");
            }else{
                String nombreMante =devolverNombre(id);
                //System.out.println("Uff paso"); 
                if(mantenimientoRepetido(placa, nombreMante) == 1){
                    System.out.println("[!] Este mantenimiento ya esta registrado");
                    return;
                }
                
                nuevaAsigna.setNombreMantenimiento(nombreMante);
                
                
                int opcion;
            do {                
            System.out.println("Digite el periodo de mantenimiento");
            System.out.println("1.Por Kilometros");
            System.out.println("2.Por dias");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
            
            if((opcion != 1) && (opcion !=2)){
                System.out.println("");
                System.out.println("[!] Digite una opcion valida");
                System.out.println("");
            }
            }while((opcion != 1) && (opcion !=2));
            
            String periodicidad = periodicidad(opcion);
            nuevaAsigna.setTipoPeriodo(periodicidad);
            
            System.out.println("Digite el valor limite del periodo "+ periodicidad);
            System.out.print("Valor: ");
            double numPeriodicidad = leer.nextDouble();
            nuevaAsigna.setNumPeriodicidad(numPeriodicidad);
            
            int kmActual = kmActual(placa);
            nuevaAsigna.setKmUltimo(kmActual);
                System.out.println(kmActual);
            
                //falta fecha modular
            Almacen.agregrarAsignacionMante(nuevaAsigna);
        }
        
            }
            
        
        
    }
    
}
