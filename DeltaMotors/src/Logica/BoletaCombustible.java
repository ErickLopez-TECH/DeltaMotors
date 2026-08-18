/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjAsignacionMantenimiento;
import Datos.ObjBoletaCombustible;
import Datos.ObjVehiculo;
import static Logica.Usuarios.Almacen;
import static Logica.Usuarios.leer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author triamus
 */
public class BoletaCombustible {
    
    static String[] combustibles = {"Gasolina Super","Gasolina Regular","Diesel","Electricidad"};
    
     public  void BoletaCombus(){
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|          BOLETA  DE COMBUSTIBLE      |");
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
            case 1: addBoletaCombus();
                
                break;
            case 2: modificarBoleta();
                break;
            case 3:borrarBoleta();
                
                break;
            case 4:consultarBoleta();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
    
    public static int siguienteBoletaID() {
    int resultado = 1;
    ArrayList<ObjBoletaCombustible> misBoletas = Almacen.listarBoletaCombus();
    
    
    for (int i = 0; i < misBoletas.size(); i++) {
        ObjBoletaCombustible boleta = misBoletas.get(i);
        if (resultado <= boleta.getId()) {
            resultado = boleta.getId() + 1;
        }
    }
    
    return resultado;
}
     
     public static int buscarBoleta(){
        System.out.println("===================================");
        System.out.println("|          BUSCAR BOLETA      |");
        System.out.println("===================================");
        System.out.println("");
        leer.nextLine();
        System.out.print("Digite id mantenimiento: ");
        int idMante = leer.nextInt();
        int indice = -1;
        
        //--Una nueva lista para trabajar localmente(cliente)
         ArrayList<ObjBoletaCombustible> miBoleta= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         miBoleta = Almacen.listarBoletaCombus();
         //--Recorrer con for
         for (int i = 0; i< miBoleta.size();i++) {
             ObjBoletaCombustible A = new ObjBoletaCombustible();
             A = miBoleta.get(i);
             if (A.getId() == idMante) {
                 indice = i;
                 break;
             }
             
        }
         return indice;
    }
     
     //menus difrentes para vehiculos con combustible diferentes
    public static int tipoDeCombustionVehiculo(String placa){
        ArrayList<ObjVehiculo> miVehiculo = new ArrayList<>();
        miVehiculo = Almacen.listarVehiculos();
        
        for (int i = 0; i < miVehiculo.size(); i++) {
            ObjVehiculo vehiculo = miVehiculo.get(i);
            if (vehiculo.getPlaca().equals(placa)) {
                if (vehiculo.getTipoVehiculo().equals("Electrico")) {
                    return 1;
                }
                if (vehiculo.getTipoVehiculo().equals("Hibrido")) {
                    return 2;
                }else{
                    return 3;
                }
                
            }
        }
        return -1;
    }
     
    public static int devuelveIndiceAsigancion(String placa){
        int indiceVehiculo = -1;
        ArrayList<ObjAsignacionMantenimiento> listaAsigancion = Almacen.listarAsignacion();
        
        for (int i = 0; i < listaAsigancion.size(); i++) {
            if (listaAsigancion.get(i).getPlacaVehiculo().equals(placa)) {
                indiceVehiculo = i;
                break;
            }
        }
        return indiceVehiculo;
   }
    
    public static int devuelveIndiceVehi(String placa){
        int indiceVehiculo = -1;
        ArrayList<ObjVehiculo> listaVehiculos = Almacen.listarVehiculos();
        
        for (int i = 0; i < listaVehiculos.size(); i++) {
            if (listaVehiculos.get(i).getPlaca().equals(placa)) {
                indiceVehiculo = i;
                break;
            }
        }
        return indiceVehiculo;
   }
    
    public void addBoletaCombus(){
        
        System.out.println("====================================");
        System.out.println("|  INGRESAR BOLETA DE COMBUSTIBLE  |");
        System.out.println("====================================");
        System.out.println("");
        ObjBoletaCombustible nuevaBoleta = new ObjBoletaCombustible();
        
        leer.nextLine();
        System.out.println("Digite la placa del vehiculo: ");
        System.out.print("Placa: ");
        String placa = leer.nextLine();
        
        int indiceAsigancionVehiculo = -1;
        ArrayList<ObjAsignacionMantenimiento> listaAsignaciones = Almacen.listarAsignacion();
        
        for (int i = 0; i < listaAsignaciones.size(); i++) {
            if (listaAsignaciones.get(i).getPlacaVehiculo().equals(placa)) {
                indiceAsigancionVehiculo = i;
                break;
            }
        }
        
        
        
        int id;
        double kmActual;
        double cantidadCombus;
        int opcion = 0;
        int opcionCombus = 0;
        int devuelveTipo = tipoDeCombustionVehiculo(placa);
        
        if (indiceAsigancionVehiculo != -1) {
            ObjAsignacionMantenimiento modificarAsigancionVeh = listaAsignaciones.get(indiceAsigancionVehiculo);
            
            ArrayList<ObjVehiculo> listarVehiculos = new ArrayList<>();
            listarVehiculos = Almacen.listarVehiculos();
            ObjVehiculo modificarVehiculo = new ObjVehiculo();
            int indiceVehiculo = devuelveIndiceVehi(placa);
            modificarVehiculo = listarVehiculos.get(indiceVehiculo);
        
            if(devuelveTipo == 3){

                System.out.println("Vehiculo combustion interna listo para agregar datos");
                nuevaBoleta.setPlacaVehiculo(placa);

                id = siguienteBoletaID();
                System.out.println("Id Asigando: "+ id);
                nuevaBoleta.setId(id);

                System.out.println("Digite el kilometaje actual: ");
                System.out.print("Km: ");
                kmActual = leer.nextDouble();
                //actualizamo km en vehiculo tambien
                nuevaBoleta.setKmActual(kmActual);
                
                modificarAsigancionVeh.setKmUltimo(kmActual);
                
                
                modificarVehiculo.setKilometroActual(kmActual);
                

                System.out.println("Digite la cantidad de Combustible: ");
                System.out.print("Cantidad: ");
                cantidadCombus = leer.nextDouble();
                nuevaBoleta.setCantidadCombustible(cantidadCombus);


                do {                
                    System.out.println("Digite el tipo de combustible dispensado");
                    System.out.println("1. Gasolina Super");
                    System.out.println("2. Gasolina Regular");
                    System.out.println("3. Diesel");
                    opcion = leer.nextInt();

                    if ((opcion < 1) || (opcion > 3)) {
                        System.out.println("[!] Opcion invalida");
                    }
                } while ((opcion < 1) || (opcion > 3));

                int electrico = 0;
                nuevaBoleta.setCantidadKWH(electrico);
                String tipo = combustibles[opcion - 1];
                nuevaBoleta.setTipoCombustible(tipo);
                System.out.println(tipo);

            }

            if(devuelveTipo == 1){
                System.out.println("Vehiculo electrico listo para agregar datos");
                nuevaBoleta.setPlacaVehiculo(placa);

                id = siguienteBoletaID();
                System.out.println("Id Asigando: "+ id);
                nuevaBoleta.setId(id);

                System.out.println("Digite el kilometaje actual: ");
                System.out.print("Km: ");
                kmActual = leer.nextDouble();
                //actualizamo km en vehiculo tambien
                nuevaBoleta.setKmActual(kmActual);
                modificarAsigancionVeh.setKmUltimo(kmActual);
                
                modificarVehiculo.setKilometroActual(kmActual);

                System.out.println("Digite la cantidad de KWH dispensado: ");
                System.out.print("KWH: ");
                cantidadCombus = leer.nextDouble();
                nuevaBoleta.setCantidadKWH(cantidadCombus);

                String tipo = combustibles[3];
                nuevaBoleta.setTipoCombustible(tipo);
                System.out.println(tipo);
            }
        
            if (devuelveTipo == 2) {
                
                System.out.println("Vehiculo Hibrido listo para agregar datos");
                nuevaBoleta.setPlacaVehiculo(placa);

                id = siguienteBoletaID();
                System.out.println("Id Asigando: "+ id);
                nuevaBoleta.setId(id);

                System.out.println("Digite el kilometaje actual: ");
                System.out.print("Km: ");
                kmActual = leer.nextDouble();
                //actualizamo km en vehiculo tambien
                nuevaBoleta.setKmActual(kmActual);
                modificarAsigancionVeh.setKmUltimo(kmActual);
                
                modificarVehiculo.setKilometroActual(kmActual);

                int ingresoGasolina = 0;
                String tipoCombustibleFinal = "";

                System.out.println("Seleccione la opcion correspondiente del tipo que dispenso");
                do {                    
                    System.out.println("Al vehiculo le ingreso gasolina?");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    opcion = leer.nextInt();
                    
                    if ((opcion != 1) && (opcion != 2)) {
                        System.out.println("");
                        System.out.println("[!] Opcion incorrecta");
                    }
                } while ((opcion != 1) && (opcion != 2));
                
                if (opcion == 1) {
                    ingresoGasolina = 1;
                    do {                
                        System.out.println("Digite el tipo de combustible dispensado");
                        System.out.println("1. Gasolina Super");
                        System.out.println("2. Gasolina Regular");
                        System.out.println("3. Diesel");
                        opcionCombus = leer.nextInt();

                        if ((opcionCombus < 1) || (opcionCombus > 3)) {
                            System.out.println("[!] Opcion invalida");
                        }
                    } while ((opcionCombus < 1) || (opcionCombus > 3));

                    System.out.println("Digite la cantidad de Combustible: ");
                    System.out.print("Cantidad: ");
                    cantidadCombus = leer.nextDouble();
                    
                    nuevaBoleta.setCantidadCombustible(cantidadCombus);
                    tipoCombustibleFinal = combustibles[opcionCombus - 1];
                } else {
                    nuevaBoleta.setCantidadCombustible(0);
                }
            
                do {                    
                    System.out.println("Al vehiculo le ingreso electricidad??");
                    System.out.println("1. Si");
                    System.out.println("2. No");
                    opcion = leer.nextInt();
                    
                    if ((opcion != 1) && (opcion != 2)) {
                        System.out.println("");
                        System.out.println("[!] Opcion incorrecta");
                    }
                } while ((opcion != 1) && (opcion != 2));
                
                if (opcion == 1) {
                    System.out.println("Digite la cantidad de KWH: ");
                    System.out.print("KWH: ");
                    cantidadCombus = leer.nextDouble();

                    nuevaBoleta.setCantidadKWH(cantidadCombus);
                    
                    if (ingresoGasolina == 1) {
                        tipoCombustibleFinal += " & Electricidad";
                    } else {
                        tipoCombustibleFinal = "Electricidad";
                    }
                } else {
                    nuevaBoleta.setCantidadKWH(0);
                    if (ingresoGasolina ==0) {
                        tipoCombustibleFinal = "Ninguno";
                    }
                }
                
                nuevaBoleta.setTipoCombustible(tipoCombustibleFinal);
                System.out.println(tipoCombustibleFinal);
            }
            
            leer.nextLine();
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            
            Date ingreso = null;
            int fechaValida = 0; // Bandera para controlar el ciclo
            
            do {
                try {
                    System.out.println("");
                    System.out.println("Digite la fecha que se dispenso (dd/mm/aaaa): ");
                    String fechaDispensado = leer.nextLine();
                    ingreso = formato.parse(fechaDispensado);
                    
                    nuevaBoleta.setFecha(ingreso);
                    System.out.println(ingreso);
                    
                    fechaValida = 1; 
                } catch (ParseException e) {
                    System.out.println("[!] Formato de fecha inválido. Intente de nuevo usando el formato dd/mm/aaaa.");
                    fechaValida = 0; 
                }
            } while (fechaValida == 0);
            
            // Guardamos la boleta y escribimos los cambios actualizados del vehículo en el archivo
            Almacen.agregrarBoletaCombus(nuevaBoleta);
            Almacen.modificarAsigancion(indiceAsigancionVehiculo, modificarAsigancionVeh);
            Almacen.escribeArchivoAsignacion();
            Almacen.escribeArchivoBoletaCombus();
            
            Almacen.modificarVehiculo(indiceVehiculo, modificarVehiculo);
            Almacen.escribeArchivoVehiculos();
            System.out.println("[✅] Boleta guardada y vehículo actualizado exitosamente.");
            
        }//if global
        else{
            System.out.println("[!] No se encontro la placa");
        }
    }
    
    
   public void consultarBoleta() {
    ArrayList<ObjBoletaCombustible> lista = new ArrayList<>();
    lista = Almacen.listarBoletaCombus();

    System.out.println("=================================================");
    System.out.println("|        LISTA DE BOLESTAS DE COMBUSTIBLE       |");
    System.out.println("=================================================");
    System.out.println("");

    if (lista.size() == 0) {
        System.out.println("[!] No hay asignaciones registradas actualmente.");
    } else {
        // 1. Valores mínimos iniciales (deben ser al menos del tamaño de la palabra del encabezado)
        int maxId = 2;       // Tamaño de "ID"
        int maxPlaca = 5;    // Tamaño de "Placa"
        int maxKm = 9;   // Tamaño de "Kilometraje
        int maxCombus = 2;  // Tamaño de "Cantidad combus"
        int maxKwh = 2;
        int maxTipoCombus = 5;    // Tamaño de "Tipo de combsutible"
        int maxFecha = 10; // Tamaño de "frcha"

         // Recorremos para encontrar los anchos 
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaTexto = null ;
        // 2. Recorremos la lista para encontrar los textos más largos
        for (int i = 0; i < lista.size(); i++) {
            ObjBoletaCombustible a = lista.get(i);
            
            // Convertimos el ID y el valor a String para medir sus caracteres
            if (String.valueOf(a.getId()).length() > maxId) {
                maxId = String.valueOf(a.getId()).length();
            }
            if (a.getPlacaVehiculo().length() > maxPlaca) {
                maxPlaca = a.getPlacaVehiculo().length();
            }
            if (String.valueOf(a.getKmActual()).length() > maxKm) {
                maxKm = String.valueOf(a.getKmActual()).length();
            }
            if (String.valueOf(a.getCantidadCombustible()).length() > maxCombus) {
                maxCombus = String.valueOf(a.getCantidadCombustible()).length();
            }
            if (String.valueOf(a.getCantidadKWH()).length() > maxKwh) {
                maxKwh = String.valueOf(a.getCantidadKWH()).length();
            }
            if (a.getTipoCombustible().length() > maxTipoCombus) {
                maxTipoCombus = a.getTipoCombustible().length();
            }
            
            fechaTexto = formatoFecha.format(a.getFecha());
            if (fechaTexto.length() > maxFecha) {
                maxFecha = fechaTexto.length();
            }

        }

        // 3. Creamos un formato dinámico que sirve tanto para el encabezado como para las filas
        // Ejemplo: "%-4s | %-8s | %-15s | %-10s | %-8s\n"
        String formato = "%%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds\n";
        formato = String.format(formato, maxId, maxPlaca, maxKm, maxCombus,maxKwh, maxTipoCombus,maxFecha);

        
        System.out.printf(formato, "ID", "Placa", "KM Actual", "Lit", "KWH", "Tipo","Fecha Dispensada");
        
        
        System.out.println("-------------------------------------------------------------------");

        
        for (int i = 0; i < lista.size(); i++) {
            ObjBoletaCombustible a = lista.get(i);
            
            System.out.printf(formato,
                a.getId(),
                a.getPlacaVehiculo(),
                a.getKmActual(),
                a.getCantidadCombustible(),
                a.getCantidadKWH(),
                a.getTipoCombustible(),
                fechaTexto
               
            );
        }
    }
    System.out.println("-------------------------------------------------");
}
   
   public void borrarBoleta(){
       int indiceBoleta = buscarBoleta();
       if(indiceBoleta == -1){
           System.out.println("");
           System.out.println("[!] Boleta no encontrada");
       }else{
           ArrayList<ObjBoletaCombustible> miBoleta = new ArrayList<>();
           miBoleta = Almacen.listarBoletaCombus();
           System.out.println("Vehiculo Placa: "+ miBoleta.get(indiceBoleta).getPlacaVehiculo());
           
           ObjBoletaCombustible boleta = new ObjBoletaCombustible();
           boleta = miBoleta.get(indiceBoleta);
           
           Almacen.eliminarBoletaCombus(indiceBoleta);
           Almacen.escribeArchivoBoletaCombus();
           System.out.println("[✅] Borrado exitoso");
       }
   }
   
   public void modificarBoleta(){
       System.out.println("====================================");
       System.out.println("|    MODIFICAR BOLETA COMBUSTIBLE  |");
       System.out.println("====================================");
       System.out.println("");
       
       int indice = buscarBoleta();
       
       if (indice == -1) {
           System.out.println("");
           System.out.println("[!] Boleta no encontrada");
       } else {
           ArrayList<ObjBoletaCombustible> miBoletaList = Almacen.listarBoletaCombus();
           ObjBoletaCombustible boleta = miBoletaList.get(indice);
           
           ArrayList<ObjVehiculo> listarVehiculos = new ArrayList<>();
           listarVehiculos = Almacen.listarVehiculos();
           ObjVehiculo modificarVehiculo = new ObjVehiculo();
            
           String placa = boleta.getPlacaVehiculo();
           System.out.println("Vehículo placa: " + placa);
           
           // Buscamos el vehículo asociado para actualizar su kilometraje también si es necesario
           int indiceAsigancionVehiculo = -1;
           ArrayList<ObjAsignacionMantenimiento> listaAsiganciones = Almacen.listarAsignacion();
           for (int i = 0; i < listaAsiganciones.size(); i++) {
               if (listaAsiganciones.get(i).getPlacaVehiculo().equals(placa)) {
                   indiceAsigancionVehiculo = i;
                   break;
               }
           }
           
           if (indiceAsigancionVehiculo == -1) {
               System.out.println("[!] Advertencia: No se encontró el vehículo asociado a la placa.");
               return;
           }
           
           ObjAsignacionMantenimiento modificarAsigancionVeh = listaAsiganciones.get(indiceAsigancionVehiculo);
           int devuelveTipo = tipoDeCombustionVehiculo(placa);
           
           int kmActual;
           double cantidadCombus;
           int opcion = 0;
           int opcionCombus = 0;
           
           // 1. Modificar Kilometraje (Común para todos)
           System.out.println("Digite el nuevo kilometraje actual (Actual: " + boleta.getKmActual() + "): ");
           System.out.print("Km: ");
           kmActual = leer.nextInt();
           boleta.setKmActual(kmActual);
           
           modificarAsigancionVeh.setKmUltimo(kmActual);
           
           int indiceVehiculo = devuelveIndiceVehi(placa);
           modificarVehiculo = listarVehiculos.get(indiceVehiculo);
           modificarVehiculo.setKilometroActual(kmActual);
           
           // 2. Modificación según el tipo de combustión del vehículo
           if (devuelveTipo == 3) { // Combustión Interna
               System.out.println("Vehículo de combustión interna - Actualizando datos:");

               System.out.println("Digite la nueva cantidad de Combustible (Actual: " + boleta.getCantidadCombustible() + "): ");
               System.out.print("Cantidad: ");
               cantidadCombus = leer.nextDouble();
               boleta.setCantidadCombustible(cantidadCombus);

               do {                
                   System.out.println("Digite el tipo de combustible dispensado");
                   System.out.println("1. Gasolina Super");
                   System.out.println("2. Gasolina Regular");
                   System.out.println("3. Diesel");
                   opcion = leer.nextInt();

                   if ((opcion < 1) || (opcion > 3)) {
                       System.out.println("[!] Opcion invalida");
                   }
               } while ((opcion < 1) || (opcion > 3));

               boleta.setCantidadKWH(0);
               String tipo = combustibles[opcion - 1];
               boleta.setTipoCombustible(tipo);
               System.out.println(tipo);

           } else if (devuelveTipo == 1) { // Eléctrico
               System.out.println("Vehículo eléctrico - Actualizando datos:");

               System.out.println("Digite la nueva cantidad de KWH dispensado (Actual: " + boleta.getCantidadKWH() + "): ");
               System.out.print("KWH: ");
               cantidadCombus = leer.nextDouble();
               boleta.setCantidadKWH(cantidadCombus);
               boleta.setCantidadCombustible(0);

               String tipo = combustibles[3];
               boleta.setTipoCombustible(tipo);
               System.out.println(tipo);

           } else if (devuelveTipo == 2) { // Híbrido
               System.out.println("Vehículo Híbrido - Actualizando datos:");

               int ingresoGasolina = 0;
               String tipoCombustibleFinal = "";

               do {                    
                   System.out.println("Al vehiculo le ingresa gasolina?");
                   System.out.println("1. Si");
                   System.out.println("2. No");
                   opcion = leer.nextInt();
                   
                   if ((opcion != 1) && (opcion != 2)) {
                       System.out.println("");
                       System.out.println("[!] Opcion incorrecta");
                   }
               } while ((opcion != 1) && (opcion != 2));
               
               if (opcion == 1) {
                   ingresoGasolina = 1;
                   do {                
                       System.out.println("Digite el tipo de combustible dispensado");
                       System.out.println("1. Gasolina Super");
                       System.out.println("2. Gasolina Regular");
                       System.out.println("3. Diesel");
                       opcionCombus = leer.nextInt();

                       if ((opcionCombus < 1) || (opcionCombus > 3)) {
                           System.out.println("[!] Opcion invalida");
                       }
                   } while ((opcionCombus < 1) || (opcionCombus > 3));

                   System.out.println("Digite la cantidad de Combustible: ");
                   System.out.print("Cantidad: ");
                   cantidadCombus = leer.nextDouble();
                   
                   boleta.setCantidadCombustible(cantidadCombus);
                   tipoCombustibleFinal = combustibles[opcionCombus - 1];
               } else {
                   boleta.setCantidadCombustible(0);
               }
           
               do {                    
                   System.out.println("Al vehiculo le ingresa electricidad?");
                   System.out.println("1. Si");
                   System.out.println("2. No");
                   opcion = leer.nextInt();
                   
                   if ((opcion != 1) && (opcion != 2)) {
                       System.out.println("");
                       System.out.println("[!] Opcion incorrecta");
                   }
               } while ((opcion != 1) && (opcion != 2));
               
               if (opcion == 1) {
                   System.out.println("Digite la cantidad de KWH: ");
                   System.out.print("KWH: ");
                   cantidadCombus = leer.nextDouble();

                   boleta.setCantidadKWH(cantidadCombus);
                   
                   if (ingresoGasolina == 1) {
                       tipoCombustibleFinal += " & Electricidad";
                   } else {
                       tipoCombustibleFinal = "Electricidad";
                   }
               } else {
                   boleta.setCantidadKWH(0);
                   if (ingresoGasolina == 0) {
                       tipoCombustibleFinal = "Ninguno";
                   }
               }
               
               boleta.setTipoCombustible(tipoCombustibleFinal);
               System.out.println(tipoCombustibleFinal);
           }
           
           leer.nextLine(); // Limpiar buffer
           
           // 3. Modificar Fecha
           SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
           formato.setLenient(false);
           
           Date ingreso = null;
           int fechaValida = 0; 
           
           do {
               try {
                   System.out.println("");
                   System.out.println("Digite la nueva fecha que se dispensó (dd/mm/aaaa): ");
                   String fechaDispensado = leer.nextLine();
                   ingreso = formato.parse(fechaDispensado);
                   
                   boleta.setFecha(ingreso);
                   fechaValida = 1; 
               } catch (ParseException e) {
                   System.out.println("[!] Formato de fecha inválido. Intente de nuevo usando el formato dd/mm/aaaa.");
                   fechaValida = 0; 
               }
           } while (fechaValida == 0);
           
           // Guardar cambios en la boleta y actualizar el vehículo en el archivo
           Almacen.modificarBoletaCombus(indice, boleta);
           
           Almacen.modificarAsigancion(indiceAsigancionVehiculo, modificarAsigancionVeh);
           Almacen.escribeArchivoAsignacion();
           
           Almacen.modificarVehiculo(indiceVehiculo,modificarVehiculo);
           Almacen.escribeArchivoVehiculos();
           
           Almacen.escribeArchivoBoletaCombus();
           System.out.println("[✅] Boleta y vehículo modificados exitosamente.");
       }
   }
   
}
