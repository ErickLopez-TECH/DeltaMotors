/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjVehiculo;
import static Logica.Usuarios.leer;
import static Logica.Usuarios.Almacen;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author triamus
 */
public class Vehiculos {
    static String[] marcas = { "Toyota","Hyundai","Nissan","Kia","Honda","Mitsubishi","Suzuki",
    "Mazda","Ford","Chevrolet"};
    
    
    
    
    static String[][] datosModelos = new String[100][2];
    static int contadorModelos =0;
    
    public static void Arraymarcas(){
       // marcas[0]= "Toyota";
    }


    
    public void modelo(){
        //modelo toyota
      //  modelos[0]= "Hilux";
    }
    
    public void gestionVaehiculos(){
        
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
            System.out.println("4. Consultar");
            System.out.println("5. Regresar");
            
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
            case 1: ingresarVehiculo();
                
                break;
            case 2: modificarVehiculo();
                break;
            case 3:borrarVehiculo();
                
                break;
            case 4: consultarVehiculo();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
    public static int placaRepetida(String vehiculoNuevo){
        
     ArrayList<ObjVehiculo> misVehiculos= new ArrayList<>();
     misVehiculos = Almacen.listarVehiculos();
     
        for (int i = 0; i <misVehiculos.size(); i++) {
            if(misVehiculos.get(i).getPlaca().equals(vehiculoNuevo)){
                return 1;
            }
        }
    return -1;
    
}
    
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
        String[] modeloHyundai = {"Tucson","Elantra","Santa Fe","Creta","Accent"};
        return modeloHyundai[opcionModelo -1];
    }
    
    if(opcionMarca ==3){
        String[] modeloNissan={"Frontier", "Sentra","Kicks","X-Trail","Versa"};
        return modeloNissan[opcionModelo -1];
    }
    if (opcionMarca ==4) {
        String[] modeloKia = {"Sportage","Rio","Seltos","Sorento","Picanto"};
        return modeloKia[opcionModelo -1];
    }
    
    if (opcionMarca ==5) {
        String[] modeloHonda = {"CR-V","Civic","HR-V","Pilot","Fit"};
        return modeloHonda[opcionModelo -1];
    }
    
    if(opcionMarca ==6){
        String[] modeloMitsu ={"Montero","L200","ASX","Outlander","Mirage"};
        return modeloMitsu[opcionModelo-1];
    }
    
    if (opcionMarca ==7) {
        String[] modeloSuzu ={"Vitara","Swift","Jimny","S-Cross","Ertiga"};
        return modeloSuzu[opcionModelo -1];
    }
    
    if (opcionMarca == 8) {
        String[] modeloMaz = {"CX-5","Mazda 3","CX-30","BT-50","Mazda 2"};
        return modeloMaz[opcionModelo-1];
    }
    
    if (opcionMarca ==9) {
        String[] modeloFord = {"Ranger","Escape","Explorer","Edge","F-150"};
        return modeloFord[opcionModelo -1];
    }
    
    
    if (opcionMarca ==10) {
        String[] modeloChev={"Tracker","Colorado","Tahoe","Captiva","Onix"};
        return modeloChev[opcionModelo-1];
    }

    return "Genérico";
}    
   
    public static String obtenerTipoVehiculo(int tipo){
    String[] tipoVehiculo = {"Electrico","Combustion","Hibrido"};
    return tipoVehiculo[tipo -1];
}
    
    public static String obtenerTipoCombustible(int tipo){
    String[] tipoCombustible= {"Gasolina","Diesel"};
    return tipoCombustible[tipo -1];
}
    
     public static int buscarPlaca(){
        System.out.println("===================================");
        System.out.println("|           BUSCAR PLACA          |");
        System.out.println("===================================");
        System.out.println("");
        leer.nextLine();
        System.out.println("Digite la placa del vehiculo");
        String placa = leer.nextLine();
        int indice = -1;
        
        //--Una nueva lista para trabajar localmente(cliente)
         ArrayList<ObjVehiculo> misVehiculos= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         misVehiculos = Almacen.listarVehiculos();
         //--Recorrer con for
         for (int i = 0; i< misVehiculos.size();i++) {
             ObjVehiculo vehiculo = new ObjVehiculo();
             vehiculo = misVehiculos.get(i);
             if (vehiculo.getPlaca().equals(placa)) {
                 indice = i;
                 break;
             }
             
        }
         return indice;
    }
  
    
   
    public static String estadoVehiculo(int estado){
        String estadoStr = "";
        if(estado == 1){
            estadoStr = "Activo";
        }
        
        if(estado == 2){
            estadoStr = "Mantenimiento";
        }
        
        if(estado == 3){
            estadoStr = "Inactivo";
        }
        return estadoStr;
    }
    
   

    

    
 


/*
    public static void actualizarKilometrajes() {
        // Recorremos la lista de vehículos
        ArrayList<ObjVehiculo> listaVehi = new ArrayList<>();
        listaVehi = Almacen.listarVehiculos();
        ArrayList<ObjAsignacionMantenimiento> listaasigna = new ArrayList<>();
        listaasigna = Almacen.listarAsignacion();
        
        for (int i = 0; i < listaVehi.size(); i++) {
            ObjVehiculo v = new ObjVehiculo();
            v = listaVehi.get(i);
            // Buscamos la asignación correspondiente en la otra lista
            for (int j = 0; j < listaasigna.size(); j++) {
                ObjAsignacionMantenimiento a = new ObjAsignacionMantenimiento();
                a = listaasigna.get(j);
                
                // Si la placa es igual, actualizamos el kilometraje en la asignación
                if (a.getPlacaVehiculo().equals(v.getPlaca())) {
                    a.setKmUltimo(v.getKilometroActual());
                }
            }
        }
    }*/


    
    public void ingresarVehiculo(){
        ObjVehiculo nuevoVehiculo = new ObjVehiculo();
        
        System.out.println("---------------------------------------");
        System.out.println("|           REGISTRAR VEHICULO        |");
        System.out.println("---------------------------------------");
        System.out.println("");
        
        leer.nextLine();
        System.out.println("Digite la placa: ");
        String placa = leer.nextLine();
        
        
        if(placaRepetida(placa)== 1){
            System.out.println("[!]Placa repetida, vuelva a ingresar una nueva");
            System.out.println("");
        }else{
            
            nuevoVehiculo.setPlaca(placa);
            
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
                System.out.println("11. Otra (Digite una nueva)");
                System.out.print("Digite una opción (1-11): ");
                opcionMarca = leer.nextInt();

                if (opcionMarca < 1 || opcionMarca > 11) {
                    System.out.println("[!] Opción inválida. Digite un número entre 1 y 11.");
                }
            } while (opcionMarca < 1 || opcionMarca > 11);
    
             String marca = "";
             leer.nextLine();
             if (opcionMarca == 11) {
                 System.out.println("Digite la marca: ");
                 System.out.print("Marca: ");
                 String nuevaMarca = leer.nextLine();
                 marca = nuevaMarca;
                 nuevoVehiculo.setMarca(marca);
            }else{
                marca= marcas[opcionMarca -1];
                nuevoVehiculo.setMarca(marca);
             }
             
            int opcionCombus = 0;
            do {        
                System.out.println("Digite que tipo de vehiculo segun su motor: ");
                System.out.println("1.Electrico");
                System.out.println("2.Combustion interna");
                System.out.println("3.Hibrido");
                opcionCombus = leer.nextInt();
                
                if ((opcionCombus<1) || (opcionCombus>3)) {
                    System.out.println("[!] Digite una opcion valida");
                }
            } while ((opcionCombus<1) || (opcionCombus>3));

            String tipoMotor = obtenerTipoVehiculo(opcionCombus);
            nuevoVehiculo.setTipoVehiculo(tipoMotor);
            
            String modelo = "";

            if (opcionMarca == 11) {
                leer.nextLine();
                System.out.println("Digite el modelo para la nueva marca " + marca + ": ");
                System.out.print("Modelo: ");
                modelo = leer.nextLine();
                
                if (contadorModelos < datosModelos.length) {
                    datosModelos[contadorModelos][0] = "99";
                    datosModelos[contadorModelos][1] = modelo;
                    contadorModelos++;
                }
                nuevoVehiculo.setModelo(modelo);
            } else {
                int opcionModelo = 0;
                do {            
                    System.out.println("Los modelos para "+ marca);
                    System.out.println("son las siguientes: ");
                    mostrarModelosPorMarca(opcionMarca);
                    opcionModelo = leer.nextInt();
                    
                    if (opcionModelo < 1 || opcionModelo > 5) {
                        System.out.println("[!] Opción inválida. Digite un número entre 1 y 5.");
                    }
                } while ((opcionModelo<1) || (opcionModelo > 5));
                
                modelo = obtenerNombreModelo(opcionMarca, opcionModelo);
                String codigoMarca = String.valueOf(opcionMarca-1);
                
                if (contadorModelos < datosModelos.length) {
                    datosModelos[contadorModelos][0] = codigoMarca;
                    datosModelos[contadorModelos][1] = modelo;
                    contadorModelos++;
                }
                nuevoVehiculo.setModelo(modelo);
            }
            
           String unionCombus = "";
    
    if (opcionCombus == 1) {
        unionCombus = "KWH";
        
    } else if (opcionCombus == 2) {
        int tipoCombustibleOp = 0;
        do {
            System.out.println("Que tipo de combustible usa su vehiculo de " + tipoMotor);
            System.out.println("1. Gasolina");
            System.out.println("2. Diesel");
            System.out.print("Opcion: ");
            tipoCombustibleOp = leer.nextInt();
            
            if (tipoCombustibleOp < 1 || tipoCombustibleOp > 2) {
                System.out.println("[!] Opción inválida. Digite 1 o 2.");
            }
        } while (tipoCombustibleOp < 1 || tipoCombustibleOp > 2);
        
        unionCombus = obtenerTipoCombustible(tipoCombustibleOp);
        
    } else if (opcionCombus == 3) {
        int tipoCombustibleOp = 0;
        do {
            System.out.println("Que tipo de combustible usa su vehiculo " + tipoMotor);
            System.out.println("1. Gasolina");
            System.out.println("2. Diesel");
            System.out.print("Opcion: ");
            tipoCombustibleOp = leer.nextInt();
            
            if (tipoCombustibleOp < 1 || tipoCombustibleOp > 2) {
                System.out.println("[!] Opción inválida. Digite 1 o 2.");
            }
        } while (tipoCombustibleOp < 1 || tipoCombustibleOp > 2);
        
        String combustible = obtenerTipoCombustible(tipoCombustibleOp);
        unionCombus = combustible + " + KWH"; 
    }

    nuevoVehiculo.setCombustible(unionCombus);
        
        System.out.println("Digite el anio del vehiculo: ");
        int anio = leer.nextInt();
        nuevoVehiculo.setAnio(anio);
        
        int kilometraje;
        do {                    
            System.out.println("Deseas agregar un kilometraje: ");
            System.out.println("[!] si digitas 2 quedara por defecto en 0");
            System.out.println("1. Si");
            System.out.println("2. No");
            kilometraje = leer.nextInt();
            
            if ((kilometraje!=1) && (kilometraje!=2)) {
                System.out.println("[!] Digite una opcion valida");
            }
        } while ((kilometraje!=1) && (kilometraje!=2));
        
        if(kilometraje == 2){
            kilometraje = 0;
        }
        if(kilometraje == 1){
            System.out.println("Digite el kilometraje: ");
            kilometraje = leer.nextInt();
        }
        nuevoVehiculo.setKilometroActual(kilometraje);
        
        String estado ="Activo";
        nuevoVehiculo.setEstado(estado);
        
             Almacen.agregarVehiculo(nuevoVehiculo);
             Almacen.escribeArchivoVehiculos();
        }//else
   
    }
    
    public void modificarVehiculo(){
        int indice = buscarPlaca();
        if (indice == -1) {
            System.out.println("No se encontro el vehiculo");
            
        }else{
            ArrayList<ObjVehiculo> misVehiculos= new ArrayList<>();
            misVehiculos = Almacen.listarVehiculos();
            System.out.println("Vehiculo: " + misVehiculos.get(indice).getMarca());//ve el indice y luego get cedula
            System.out.println("Placa: " + misVehiculos.get(indice).getPlaca());
            ObjVehiculo vehiculo = new ObjVehiculo();
            vehiculo = misVehiculos.get(indice);
        
        
            int opcion;
            do {                
                System.out.println("Deseas modificar el kilometraje: ");
                System.out.println("1. Si");
                System.out.println("2. No");
                opcion = leer.nextInt();
                
                if (opcion == 1) {
                    System.out.println("Digite el kilometraje: ");
                    int kilometraje = leer.nextInt();
                    vehiculo.setKilometroActual(kilometraje);
                    
                }
                
                if((opcion != 1) && (opcion !=2)){
                    System.out.println("[!] Vuelva a digitar una opcion valida");
                    System.out.println("");
                }
            } while ((opcion != 1) && (opcion !=2));
            
            
            do {                
                System.out.println("Deseas modificar el estado: ");
                System.out.println("1. Si");
                System.out.println("2. No");
                opcion = leer.nextInt();
                
                if ((opcion != 1) && (opcion !=2)) {
                    System.out.println("[!] Digite una opcion valida");
                }
            } while ((opcion != 1) && (opcion !=2));
            
            if (opcion == 1) {
               
            do {                
                
                    System.out.println("Seleccione el nuevo Estado: ");
                    System.out.println("1. Activo");
                    System.out.println("2. Mantenimiento");
                    System.out.println("3. Inactivo");
                    opcion = leer.nextInt();
                    
                    String estado = estadoVehiculo(opcion);
                    vehiculo.setEstado(estado);
                
            } while ((opcion <1)|| (opcion >3));
            }
            Almacen.modificarVehiculo(indice, vehiculo);
            Almacen.escribeArchivoVehiculos();
            
    }
    }
    
     public void borrarVehiculo(){
        int indice = buscarPlaca();
        if (indice == -1) {
            System.out.println("No se encontro el Vehiculo");
            
        }else{
            ArrayList<ObjVehiculo> misVehiculos= new ArrayList<>();
            misVehiculos = Almacen.listarVehiculos();
            System.out.println("Vehiculo: " + misVehiculos.get(indice).getPlaca());//ve el indice y luego get cedula
            System.out.println("Placa: " + misVehiculos.get(indice).getPlaca());
            Almacen.eliminarVehiculo(indice);
            Almacen.escribeArchivoVehiculos();
            JOptionPane.showMessageDialog(null,"VEHICULO BORRADO","ATENCION",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
     public void consultarVehiculo(){
      
        System.out.println("===================================");
        System.out.println("|           LISTA VEHICULO        |");
        System.out.println("===================================");
        System.out.println("");
        
        //--Una nueva lista para trabajar localmente(cliente)
         ArrayList<ObjVehiculo> misVehiculo= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         misVehiculo = Almacen.listarVehiculos();
         //--Recorrer con for
         for (int i = 0; i< misVehiculo.size();i++) {
             ObjVehiculo vehiculo = new ObjVehiculo();
             vehiculo = misVehiculo.get(i);
             System.out.println("Id vehiculo: " + vehiculo.getId() );
             System.out.println("Placa: " + vehiculo.getPlaca());
             System.out.println("Marca: "+ vehiculo.getMarca());
             System.out.println("Modelo: " +  vehiculo.getModelo());
             System.out.println("Combustible: " + vehiculo.getCombustible());
             System.out.println("Tipo: " + vehiculo.getTipoVehiculo());
             System.out.println("Kilometraje: " + vehiculo.getKilometroActual());
             System.out.println("Anio: " + vehiculo.getAnio());
             System.out.println("Estado: " + vehiculo.getEstado());
             System.out.println("");
             System.out.println("-------------------------------------");
             System.out.println("");
        }
    }
   
    }

