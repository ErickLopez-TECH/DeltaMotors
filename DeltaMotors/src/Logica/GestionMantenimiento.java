/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import static Logica.Usuarios.leer;
import static Logica.Usuarios.Almacen;
import Datos.ObjMantenimiento;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author triamus
 */



public class GestionMantenimiento {
    
    
    
    
    
   
    
    public static void gestionMantenimientos(){
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|          GESTION MANTENIMIENTOS      |");
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
            case 1: addMantenimiento();
                
                break;
            case 2: modificarMantenimiento() ;
                break;
            case 3: eliminarMantenimiento();
                
                break;
            case 4: consultarMantenimiento();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    public static int mantenimientoRepetido(String nombre){
        ArrayList<ObjMantenimiento> miMante = new ArrayList<>();
        miMante = Almacen.listarMantenimiento();
        
        for (int i = 0; i < miMante.size(); i++) {
            if (miMante.get(i).getNombre().equals(nombre)) {
                return 1;
            }
            
        }
        return -1;
    }
    public static int buscarID(){
        System.out.println("===================================");
        System.out.println("|            BUSCAR ID             |");
        System.out.println("===================================");
        System.out.println("");
        leer.nextLine();
        System.out.println("Digite el ID del Mantenimiento");
        int mantenimiento = leer.nextInt();
        int indice = -1;
        
        
         ArrayList<ObjMantenimiento> misMantenimiento= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         misMantenimiento = Almacen.listarMantenimiento();
         //--Recorrer con for
         for (int i = 0; i< misMantenimiento.size();i++) {
             ObjMantenimiento mante = new ObjMantenimiento();
             mante= misMantenimiento.get(i);
             if (mante.getId() == mantenimiento) {
                 indice = i;
                 break;
             }
             
        }
         return indice;
    }
    
    public static void addMantenimiento(){
        System.out.println("===================================");
        System.out.println("|      INGRESAR MANTENIMIENTO     |");
        System.out.println("===================================");
        System.out.println("");
        ObjMantenimiento nuevosMantenimientos = new ObjMantenimiento();
        
        leer.nextLine();
        System.out.println("Digite el nombre del mantenimiento: " );
        System.out.print("Digite: ");
        String nombreMante = leer.nextLine();
        
        if(mantenimientoRepetido(nombreMante) == 1){
            System.out.println("[!] No se permiten nombres repetidos");
        }else{
            
        nuevosMantenimientos.setNombre(nombreMante);
        
        String estado = "Activo";
        nuevosMantenimientos.setEstado(estado);
        
   
        Almacen.agregarMantenimiento(nuevosMantenimientos);
        Almacen.escribeArchivoMantenimientos();
        }
        
        
    }
    
    public static void modificarMantenimiento(){
        int indice = buscarID();
        
        if(indice == -1){
            System.out.println("[!] No se encontro un id con el mantenimiento");
        }else{
            ArrayList<ObjMantenimiento> misMante = new ArrayList<>();
            misMante = Almacen.listarMantenimiento();
            System.out.println("ID: " + misMante.get(indice).getId());
            System.out.println("Nombre Mantenimiento: " + misMante.get(indice).getNombre());
            
            ObjMantenimiento modificarMante = new ObjMantenimiento();
            modificarMante = misMante.get(indice);
            
            int opcion =0;
            do {                
                System.out.println("Deseas modificar el nombre: ");
                System.out.println("1. Si");
                System.out.println("2. No");
                opcion = leer.nextInt();
                
                if ((opcion!=1) && (opcion !=2)) {
                    System.out.println("[!] Digite una opcion valida");
                }
            } while ((opcion!=1) && (opcion !=2));
            
            if(opcion == 1){
                leer.nextLine();
                System.out.println("Digite el nuevo nombre del mantenimiento:");
                System.out.print("Datos: ");
                String nombre = leer.nextLine();
                
                if (mantenimientoRepetido(nombre) == 1) {
                    System.out.println("[!] Este mantenimiento ya existe");
                    return;
                }else{
                modificarMante.setNombre(nombre);
                }
            }
            
            do {                
                System.out.println("Deseas modificar el estado: ");
                System.out.println("1. Si");
                System.out.println("2. No");
                opcion = leer.nextInt();
                if ((opcion!=1) && (opcion !=2)) {
                    System.out.println("[!] Digite una opcion valida"); 
                }
            } while ((opcion!=1) && (opcion !=2));
            
            if(opcion == 1){
                do {                    
                    System.out.println("Digite la opcion del estado:");
                    System.out.println("1. Activo");
                    System.out.println("2. Inactivo");
                    System.out.print("Dato: ");
                    opcion = leer.nextInt();
                    
                    if ((opcion != 1) && (opcion !=2)) {
                        System.out.println("[!] Digite una opcion valida");
                    }
                } while ((opcion != 1) && (opcion !=2));
                String estado = Usuarios.devolverEstado(opcion);
                modificarMante.setEstado(estado);
            }
            
            
            Almacen.modificarMantenimiento(indice, modificarMante);
            Almacen.escribeArchivoMantenimientos();
        }
        
        
    }
    
    public static void eliminarMantenimiento(){
        int indice = buscarID();
        
        if (indice == -1) {
            System.out.println("");
            System.out.println("[!] No se encontro el id del Mantenimeinto");
        }else{
            ArrayList<ObjMantenimiento> misMante = new ArrayList<>();
            misMante = Almacen.listarMantenimiento();
            System.out.println("Nombre del Mantenimiento: "+ misMante.get(indice).getNombre());
            Almacen.eliminarMantenimiento(indice);
            Almacen.escribeArchivoMantenimientos();
            JOptionPane.showMessageDialog(null,"MANTENIMIENTO BORRADO","ATENCION",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void consultarMantenimiento(){
      
        System.out.println("===================================");
        System.out.println("|      LISTA MANTENIMIENTOS       |");
        System.out.println("===================================");
        System.out.println("");
        
        //--Una nueva lista para trabajar localmente(cliente)
         ArrayList<ObjMantenimiento> misMantenimientos= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         misMantenimientos = Almacen.listarMantenimiento();
         //--Recorrer con for
         for (int i = 0; i< misMantenimientos.size();i++) {
             ObjMantenimiento mantenimiento = new ObjMantenimiento();
             mantenimiento = misMantenimientos.get(i);
             System.out.println("Id Mantenimiento: " + mantenimiento.getId() );
             System.out.println("Nombre: " + mantenimiento.getNombre());
             System.out.println("Estado: "+ mantenimiento.getEstado());
             
             System.out.println("-------------------------------------");
             System.out.println("");
        }
    }
}
