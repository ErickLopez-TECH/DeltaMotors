/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import java.util.Scanner;
import Logica.Usuarios;



import static Logica.Usuarios.Almacen;
import Logica.GestionMantenimiento;
import Logica.AsignaMantenimiento;
import Logica.BoletaCombustible;
import Logica.BoletaTaller;
import Logica.Usuarios;
import Logica.Vehiculos;

import static Datos.Estructuras.actualizarTodosLosContadores;
import static Logica.Login.loggin;

import static Logica.MenuReportes.Reportes;

/**
 *
 * @author triamus
 */
public class main {
     static Scanner leer = new Scanner(System.in);
     
    /**
     * @param args the command line arguments
     */
    
     public static void nuevosArchivos(){
        Almacen.crearArchivo("Usuarios");
        Almacen.crearArchivo("Vehiculos");
        Almacen.crearArchivo("Mantenimientos");
        Almacen.crearArchivo("AsignacionMante");
        Almacen.crearArchivo("BoletaCombustible");
        Almacen.crearArchivo("BoletaTaller");
       // Almacen.crearArchivo("Reservas");
    }
    
    public static void cargarListas(){
        //--Aqui vamos a poner los metodos que cargan en cada lista
        Almacen.leerArchivoUsuarios();
        Almacen.leerArchivoVehiculos();
        Almacen.leerArchivoMantenimientos();
        Almacen.leerArchivoAsignacion();
        Almacen.leerArchivoBoletaCombustible();
        Almacen.leerArchivoBoletaTaller();
    }
     
    public static void menuAdmin(){
        AsignaMantenimiento LAsignacion = new AsignaMantenimiento();
        BoletaCombustible LCombus = new BoletaCombustible();
        BoletaTaller LTaller = new BoletaTaller();
        GestionMantenimiento LMante = new GestionMantenimiento();
        Usuarios LUser = new Usuarios();
        Vehiculos LVehi = new Vehiculos();
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|             DELTA MOTORS             |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-9) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Gestion Usuarios ");
            System.out.println("2. Gestion Vehiculos");
            System.out.println("3. Gestion Mantenimientos"); 
            System.out.println("4. Asignacion Mantenimiento");
            System.out.println("5. Boleta del Taller");
            System.out.println("6. Boleta Combustible");
            System.out.println("7. Reportes DIA/KM");
            System.out.println("8. Cerrar seccion");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
            case 1: LUser.gestionUsuarios(); ;
                
                break;
            case 2: LVehi.gestionVaehiculos();
                break;
            case 3: LMante.gestionMantenimientos();
                break;
            case 4: LAsignacion.gestionAsignacion();
                break;
            case 5: LTaller.boletaTaller();
                break;
            case 6: LCombus.BoletaCombus();
                
                break;
            case 7: Reportes();
                
                break;
            
        }
            
        } while (opcion !=8);
        
        int confirmar =0;
        do {            
        if(opcion == 8){
            System.out.println("Estas seguro que deseas cerrar seccion?");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.print("Opcion: ");
            confirmar = leer.nextInt();
        }
        
            if ((confirmar !=1)&&(confirmar!=2)) {
                System.out.println("");
                System.out.println("[!] Digite una opcion valida");
            }
            
            switch (confirmar) {
                case 1:System.out.println("\n[✅]¡Acceso Concedido! Te esperamos la proxima.");
                     System.exit(0);
                    break;
                case 2: System.out.println("\n[✅]¡Restableciendo area de trabajo! ");
                      menuAdmin();
                    
            }
            
        } while ((opcion !=1)&&(opcion!=2));
        
    }
    
    public static void menuOperador(){
        
        AsignaMantenimiento LAsignacion = new AsignaMantenimiento();
        BoletaCombustible LCombus = new BoletaCombustible();
        BoletaTaller LTaller = new BoletaTaller();
        GestionMantenimiento LMante = new GestionMantenimiento();
        Usuarios LUser = new Usuarios();
        Vehiculos LVehi = new Vehiculos();
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|             DELTA MOTORS             |");
            System.out.println("----------------------------------------");
            System.out.println("Ingrese una opcion (1-9) luego presione ");
            System.out.println("la tecla enter.");
            System.out.println("");
            System.out.println("1. Gestion Vehiculos");
            System.out.println("2. Asignacion Mantenimiento");
            System.out.println("3. Boleta del Taller");
            System.out.println("4. Boleta Combustible");
            System.out.println("5. Reportes DIA/KM");
            System.out.println("6. Cerrar seccion");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
            case 1:LVehi.gestionVaehiculos(); ;
                
                break;
            case 2: LAsignacion.gestionAsignacion();
                break;
            case 3: LTaller.boletaTaller();
                break;
            case 4: LCombus.BoletaCombus();
                break;
            case 5: Reportes();
                break;
            
        }
            
        } while (opcion !=6);
        
        int confirmar =0;
        do {            
        if(opcion == 6){
            System.out.println("Estas seguro que deseas cerrar seccion?");
            System.out.println("1. Si");
            System.out.println("2. No");
            System.out.print("Opcion: ");
            confirmar = leer.nextInt();
        }
        
            if ((confirmar !=1)&&(confirmar!=2)) {
                System.out.println("");
                System.out.println("[!] Digite una opcion valida");
            }
            
            switch (confirmar) {
                case 1:System.out.println("\n[✅]¡Acceso Concedido! Te esperamos la proxima.");
                     System.exit(0);
                    break;
                case 2: System.out.println("\n[✅]¡Restableciendo area de trabajo! ");
                      menuOperador();
                    
            }
            
        } while ((opcion !=1)&&(opcion!=2));
        
    }
    
    
    
    public static void main(String[] args) {
        
        // TODO code application logic here
        nuevosArchivos();
        cargarListas();
        actualizarTodosLosContadores();
        /*Logica.Vehiculos.actualizarKilometrajesAutomaticamente();*/
        Logica.Login.loggin();
        
        //menuAdmin();
    }
}
