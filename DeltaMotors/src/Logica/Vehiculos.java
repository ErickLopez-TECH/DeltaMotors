/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjVehiculo;
import static Logica.Usuarios.Almacen;
import static Logica.DeltaMotors.leer;
import  Datos.Estructuras;

import Datos.ObjUsuarios;

/**
 *
 * @author triamus
 */
public class Vehiculos {
    static String[] marcas = new String[10];
    static String[] modelos = new String[100];
    
    public static void Arraymarcas(){
        marcas[0]= "Toyota";
    }
    
    public void modelo(){
        //modelo toyota
        modelos[0]= "Hilux";
    }
    
    public static void gestionVaehiculos(){
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|             GESTION VEHICULOS         |");
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
            case 1: ingresarVehiculo();
                
                break;
            case 2: ;
                break;
            case 3: Usuarios.ingresarUsuarios();
                
                break;
            case 4: consultarVehiculo();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
    
    
    public static void ingresarVehiculo(){
        ObjVehiculo nuevoVehiculo = new ObjVehiculo();
        System.out.println("---------------------------------------");
        System.out.println("|           REGISTRAR VEHICULO        |");
        System.out.println("---------------------------------------");
        System.out.println("");
        
        
        
        
        
        
        leer.nextLine();
        System.out.println("Digite la placa: ");
        String placa= leer.nextLine();//atomizarcion  en nombre,apellido y demas
        nuevoVehiculo.setPlaca(placa);
        
        int opciones = 0;
        do {            
            System.out.println("Digite la marca disponible en el tablero");
            System.out.println("1.Toyota");
            opciones = leer.nextInt();
        } while (opciones <1);
        
        int opcionesModelos=0;
        do {      
            
            //toyota
        
        if(opciones == 1){
            
            marcas[1] = "toy";
            nuevoVehiculo.setMarca(marcas[1]);
            System.out.println("La Marca Toyota se presentan los siguientes modelos: ");
            System.out.println("Digite un numero: ");
            System.out.println("1.Hilux");
            System.out.println("2.RAV4");
            System.out.println("3.Corolla Cross");
            System.out.println("4.Yaris");
            System.out.println("5.Raize");
            System.out.println("6.Mas opciones");
            opcionesModelos = leer.nextInt();
            
            
        }
        }while(opcionesModelos <1 || opcionesModelos >6);
        
        
        while ((opcionesModelos == 6)) { 
            do {                
            System.out.println("Los suiguientes modelos cargados");
            System.out.println("Digite un numero: ");
            System.out.println("7.Prado");
            System.out.println("8.Elantra");
            System.out.println("9.Tucson");
            System.out.println("10.Sentra");
            System.out.println("11.Frontier");
            System.out.println("12.Otro modelo");
            opcionesModelos = leer.nextInt();
            } while (opcionesModelos <7 || opcionesModelos >12);
            
        } 
        
        if(opcionesModelos == 1){
            nuevoVehiculo.setModelo(modelos[0]);
        }
        
        leer.nextLine();
        if(opcionesModelos == 12){
            System.out.println("Digite el modelo a registrar");
            String modelo = leer.nextLine();
            
        }
        
        Almacen.agregarVehiculo(nuevoVehiculo);
        /*
        System.out.println("Digite la contrasena: ");
        String password = leer.nextLine();
        nuevoUsuario.setContrasena(password);
        
        
        int estado = 1;
        
        Almacen.agregar(nuevoUsuario);*/
    }
    
    public static void consultarVehiculo() {
    System.out.println("=================================");
    System.out.println("|       CONSULTAR Vehiculos     |");
    System.out.println("=================================");
    System.out.println("");

    
    System.out.print("Digite la placa a buscar: ");
    leer.nextLine();
    String userBuscado = leer.nextLine();

    // Llamamos al método buscar dentro de tu estructura
    ObjVehiculo usuarioEncontrado = Estructuras.buscarVehiculoPlaca(userBuscado);

    if (usuarioEncontrado != null) {
        System.out.println("\nn---[!] ¡Vehículo Encontrado! ---");
        System.out.printf("%-10s%-20s%-15s%-15s\n", "ID", "Marca", "Modelo", "Placa");
        System.out.printf("%-10d%-20s%-15s%-15s\n", 
            usuarioEncontrado.getId(),
            usuarioEncontrado.getMarca(),
            usuarioEncontrado.getModelo(),
            usuarioEncontrado.getPlaca()
        );
    } else {
        System.out.println("\n[!] No se encontró ningún vehículo con la placa " + userBuscado);
    }

}
    
    
}
