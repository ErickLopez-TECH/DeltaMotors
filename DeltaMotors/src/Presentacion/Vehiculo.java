/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;
import Logica.LogVehiculo;
import Datos.Estructuras;
import Datos.ObjVehiculo;
import static Presentacion.Usuario.leer;
/**
 *
 * @author triamus
 */


public class Vehiculo {
    
    
    public static void gestionVehiculos(){
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|             GESTION VEHICULOS        |");
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
            case 1: ingresarNuevoVehiculo();
                
                break;
            case 2: 
                break;
            case 3: ;
                
                break;
            case 4: consultarVahiculo();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    

public static String obtenerNombreMarca(int opcion) {
    String[] marcas = {"Toyota", "Hyundai", "Nissan", "Kia", "Honda", "Mitsubishi", "Suzuki", "Mazda", "Ford", "Chevrolet"};
    return marcas[opcion - 1];
}

public static String obtenerTipoVehiculo(int tipo){
    String[] tipoVehiculo = {"Electrico","Combustion","Hibrido"};
    return tipoVehiculo[tipo -1];
}

public static String obtenerTipoCombustible(int tipo){
    String[] tipoCombustible= {"Gasolina","Diesel"};
    return tipoCombustible[tipo -1];
}

// Muestra en pantalla los modelos que corresponden a cada marca
public static void mostrarModelosPorMarca(int opcionMarca) {
    switch (opcionMarca) {
        case 1: // Toyota
            System.out.println("1. Hilux\n2. Corolla Cross\n3. Yaris\n4. Prado\n5. Raize");
            break;
        case 2: // Hyundai
            System.out.println("1. Tucson\n2. Elantra\n3. Santa Fe\n4. Creta\n5. Accent");
            break;
        case 3: // Nissan
            System.out.println("1. Frontier\n2. Sentra\n3. Kicks\n4. X-Trail\n5. Versa");
            break;
        case 4: // Kia
            System.out.println("1. Sportage\n2. Rio\n3. Seltos\n4. Sorento\n5. Picanto");
            break;
        case 5: // Honda
            System.out.println("1. CR-V\n2. Civic\n3. HR-V\n4. Pilot\n5. Fit");
            break;
        case 6: // Mitsubishi
            System.out.println("1. Montero\n2. L200\n3. ASX\n4. Outlander\n5. Mirage");
            break;
        case 7: // Suzuki
            System.out.println("1. Vitara\n2. Swift\n3. Jimny\n4. S-Cross\n5. Ertiga");
            break;
        case 8: // Mazda
            System.out.println("1. CX-5\n2. Mazda 3\n3. CX-30\n4. BT-50\n5. Mazda 2");
            break;
        case 9: // Ford
            System.out.println("1. Ranger\n2. Escape\n3. Explorer\n4. Edge\n5. F-150");
            break;
        case 10: // Chevrolet
            System.out.println("1. Tracker\n2. Colorado\n3. Tahoe\n4. Captiva\n5. Onix");
            break;
    }
}


public static String obtenerNombreModelo(int opcionMarca, int opcionModelo) {
    
    if (opcionMarca == 1) {
        String[] modelosToyota = {"Hilux", "Corolla Cross", "Yaris", "Prado", "Raize"};
        return modelosToyota[opcionModelo - 1];
    }
    
    if(opcionMarca == 2){
        String[] modeloHyundai = {};
    }
    
    return "Modelo Genérico";
}    
    
public static void ingresarNuevoVehiculo() {
    System.out.println("=======================================");
    System.out.println("|          INGRESAR VEHÍCULO          |");
    System.out.println("=======================================");
    
    leer.nextLine(); // Limpiar buffer
    System.out.print("Digite la placa: ");
    String placa = leer.nextLine();
    
    if(Logica.LogVehiculo.placaRepetida(placa) == 1){
        System.out.println("[!] Placa existente en el registro");
        return;
    }
    // --- 1. MENÚ DE LAS 10 MARCAS ---
    int opcionMarca = 0;
    do {
        System.out.println("\n--- SELECCIONE LA MARCA ---");
        System.out.println("1. Toyota");
        System.out.println("2. Hyundai");
        System.out.println("3. Nissan");
        System.out.println("4. Kia");
        System.out.println("5. Honda");
        System.out.println("6. Mitsubishi");
        System.out.println("7. Suzuki");
        System.out.println("8. Mazda");
        System.out.println("9. Ford");
        System.out.println("10. Chevrolet");
        System.out.print("Digite una opción (1-10): ");
        opcionMarca = leer.nextInt();
        
        if (opcionMarca < 1 || opcionMarca > 10) {
            System.out.println("[!] Opción inválida. Digite un número entre 1 y 10.");
        }
    } while (opcionMarca < 1 || opcionMarca > 10);
    
    //parametros 
    String marca = obtenerNombreMarca(opcionMarca);

    // --- 2. MENÚ DE MODELOS 
    int opcionModelo = 0;
    do {
        System.out.println("\n--- SELECCIONE EL MODELO PARA: " + marca + " ---");
        mostrarModelosPorMarca(opcionMarca); // Muestra los modelos específicos de esa marca
        System.out.print("Digite la opción del modelo: ");
        opcionModelo = leer.nextInt();
    } while (opcionModelo < 1 || opcionModelo > 5); 

    String modelo = obtenerNombreModelo(opcionMarca, opcionModelo);
    
    System.out.println("Digite el anio del vehiculo: ");
    int anio = leer.nextInt();
    
    
    int opcion = 0;
    do {        
        System.out.println("Digite que tipo de vehiculo segun su motor: ");
        System.out.println("1.Electrico");
        System.out.println("2.Combustion interna");
        System.out.println("3.Hibrido");
        opcion = leer.nextInt();
    } while ((opcion<1)&& (opcion>4));

    String tipoMotor = obtenerTipoVehiculo(opcion);
    
    String electrico ;
    
    int opcionKH = opcion;
    String unionCombus = "";
    String combustible = "";
    if(opcionKH == 1){
        electrico= "KWH";
        unionCombus = electrico;
    }
    
    if(opcion == 2){
        opcion = 0;
        System.out.println("Que tipo de combustible usa su vehiculo de "+ tipoMotor);
        System.out.println("1.Gasolina");
        System.out.println("2.Diesel");
        System.out.print("Opcion: ");
        opcion = leer.nextInt();
        combustible = obtenerTipoCombustible(opcion);
        unionCombus = combustible;
    }
    
    if(opcion == 3){
        opcion = 0;
        System.out.println("Que tipo de combustible usa su vehiculo  "+ tipoMotor);
        System.out.println("1.Gasolina");
        System.out.println("2.Diesel");
        System.out.print("Opcion: ");
        opcion = leer.nextInt();
        combustible = obtenerTipoCombustible(opcion);
        unionCombus = combustible + " KWH";
    }
    
    
    
    // --- capa de logica
    LogVehiculo.ingresarVehiculo(placa, marca, modelo,anio, tipoMotor, unionCombus);
    
    System.out.println("\n[✔] ¡Vehículo registrado con éxito!");
}

/*
    public static void ingresarNuevoVehiculo(){
        System.out.println("---------------------------------------");
        System.out.println("|           REGISTRAR VEHICULO        |");
        System.out.println("---------------------------------------");
        System.out.println("");
        
        leer.nextLine();
        System.out.println("Digite la placa del vehiculo: ");
        String placa = leer.nextLine();
        
        int opciones = 0;
        String marca = null;
        do {            
            System.out.println("Digite la marca disponible en el tablero");
            System.out.println("1.Toyota");
            opciones = leer.nextInt();
            
            if(opciones== 1){
                marca = "Toyota";
            }
        } while (opciones < 1);
        
        int opcionesModelos=0;
        
         String modelo = null;
        do {      
            
            //toyota
       
        if(opciones == 1){
            System.out.println("La Marca Toyota se presentan los siguientes modelos: ");
            System.out.println("Digite un numero: ");
            System.out.println("1.Hilux");
            System.out.println("2.RAV4");
            System.out.println("3.Corolla Cross");
            System.out.println("4.Yaris");
            System.out.println("5.Raize");
            System.out.println("6.Mas opciones");
            opcionesModelos = leer.nextInt();
            
            if(opcionesModelos == 1){
                modelo = "Hilux";
            }
            
            
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
            
            leer.nextLine();
        /*if(opcionesModelos == 12){
            System.out.println("Digite el modelo a registrar");
            String modelo = leer.nextLine();
            
        }*/
        
       /* Logica.LogVehiculo.ingresarVehiculo(placa, marca, modelo);
        System.out.println("[✔] ¡Vehiculo registrado con éxito!");
            
        } 
    }
    */
    public static void consultarVahiculo() {
    System.out.println("=================================");
    System.out.println("|       VEHICULO USUARIOS      |");
    System.out.println("=================================");
    System.out.println("");
    
    leer.nextLine();
    System.out.print("Digite el usuario a buscar: ");
    String vehiculoBuscado = leer.nextLine();
    

    // Llamamos al método buscar dentro de tu objeto Almacen (Estructuras)
    ObjVehiculo vehiculoEncontrado = Estructuras.buscarVehiculoPlaca(vehiculoBuscado);

    if (vehiculoEncontrado != null) {
        System.out.println("\n---[!] ¡Vehiculo Encontrado! ---");
        System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s%-10s%-10s\n ", "Placa","Marca","Modelo","anio","Estado","KM","Motor","Combustible");
        System.out.printf("%-10s%-20s%-10s%-10s%-10s%-10s%-10s%-10s\n ",vehiculoEncontrado.getPlaca()
        ,vehiculoEncontrado.getMarca()
        ,vehiculoEncontrado.getModelo()
        ,vehiculoEncontrado.getAnio()
        ,vehiculoEncontrado.getEstado()
        ,vehiculoEncontrado.getKilometroActual()
        ,vehiculoEncontrado.getTipoVehiculo()
        ,vehiculoEncontrado.getCombustible()
        );
        
        
        
    } else {
        System.out.println("\n[!] No se encontró ningún Vehiculo con la placa " + vehiculoBuscado);
    }
}
    
    
}
