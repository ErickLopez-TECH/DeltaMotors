/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Logica;

import java.util.Scanner;

/**
 *
 * @author triamus
 */
public class DeltaMotors {
    
    static Scanner leer = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    
    public static void menuPrincipla(){
        
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
            System.out.println("3. Empleados"); //TAREA -- id Identificación
            System.out.println("4. Reservaciones");
            System.out.println("5. Check-In / Check-Out");
            System.out.println("6. Reportes");
            System.out.println("9. Salir");
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
            case 1: Usuarios.gestionUsuarios();
                
                break;
            case 2: Vehiculos.gestionVaehiculos();
                break;
            case 3: Usuarios.ingresarUsuarios();
                
                break;
            case 4: Usuarios.consultarUsuarios();
                
                break;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        menuPrincipla();
    }
    
}
