/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Datos.ObjBoletaTaller;
import java.util.ArrayList;
import Datos.ObjAsignacionMantenimiento;
import static Logica.Usuarios.Almacen;
import  Datos.ObjVehiculo;
import static Logica.Usuarios.leer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author triamus
 */
public class BoletaTaller {
    
    public static String[] mecanicos = {
    "Carlos Rodríguez",
    "Juan Pérez",
    "Luis Gómez",
    "Miguel Rojas",
    "José Vargas",
    "Esteban Mora",
    "Andrés Solano",
    "Roberto Arias",
    "Francisco Quirós",
    "Daniel Monge"
};
    
    public void boletaTaller(){
        
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
            System.out.println("4. Consultar");
            System.out.println("5. Regresar");
            
            System.out.println("----------------------------------------");
            System.out.println("");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
            
            switch(opcion){
            case 1: addBoletaTaller();
                
                break;
            case 2: modificarBoleta();
                break;
            case 3:borrarBoleta();
                
                break;
            case 4: consultarBoletaTaller();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
    public static void mostrarMecanicos() {
    System.out.println("====================================");
    System.out.println("|        LISTA DE MECÁNICOS        |");
    System.out.println("====================================");
    
    for (int i = 0; i < mecanicos.length; i++) {
        System.out.println((i + 1) + ". " + mecanicos[i]);
    }
    System.out.println("------------------------------------");
}
    
    
    //siguiente id
   public static int siguienteBoletaID() {
    int resultado = 1;
    ArrayList<ObjBoletaTaller> misBoletas = Almacen.listarBoletaller();
    
    
    for (int i = 0; i < misBoletas.size(); i++) {
        ObjBoletaTaller boleta = misBoletas.get(i);
        if (resultado <= boleta.getId()) {
            resultado = boleta.getId() + 1;
        }
    }
    
    return resultado;
}
   
   public static int buscarCodigoAsigancion(int idMante){
       ArrayList<ObjAsignacionMantenimiento> miAsignacion = new ArrayList<>();
       miAsignacion = Almacen.listarAsignacion();
       
       
       for (int i = 0; i < miAsignacion.size(); i++) {
           ObjAsignacionMantenimiento mante = miAsignacion.get(i);
           if (mante.getId()== idMante) {
               return i;
           }
       }
    return -1;
   }
   
   public static String devolverNombreAsigna(int idMante){
       ArrayList<ObjAsignacionMantenimiento> miAsigana = Almacen.listarAsignacion();
       
       for (int i = 0; i < miAsigana.size(); i++) {
           ObjAsignacionMantenimiento asigna = miAsigana.get(i);
           if(asigna.getId() ==  idMante){
               return asigna.getNombreMantenimiento();
           }
       }
        return "No encontrado";
   }
   
   public static String devolverPlacaAsigna(int idMante){
       ArrayList<ObjAsignacionMantenimiento> miAsigana = Almacen.listarAsignacion();
       
       for (int i = 0; i < miAsigana.size(); i++) {
           ObjAsignacionMantenimiento asigna = miAsigana.get(i);
           if(asigna.getId() ==  idMante){
               return asigna.getPlacaVehiculo();
           }
       }
        return "No encontrado";
   }
   
   //modelo vehiculo basado a su placa
   public static String devolverModeloVehiculo(String placa){
       ArrayList<ObjVehiculo> miVehi = Almacen.listarVehiculos();
       for (int i = 0; i < miVehi.size(); i++) {
           ObjVehiculo vehi = miVehi.get(i);
           
           if (miVehi.get(i).getPlaca().equals(placa)) {
               return miVehi.get(i).getModelo();
           }
       }
        return "No encontrado";
   }
   
   public static String devolverMarcaVehiculo(String placa){
       ArrayList<ObjVehiculo> miVehi = Almacen.listarVehiculos();
       for (int i = 0; i < miVehi.size(); i++) {
           ObjVehiculo vehi = miVehi.get(i);
           
           if (miVehi.get(i).getPlaca().equals(placa)) {
               return miVehi.get(i).getMarca();
           }
       }
        return "No encontrado";
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
   
  
   
   public static String devuelvePlacaVehiculo(int id){
        
        ArrayList<ObjBoletaTaller> listaBoleta = Almacen.listarBoletaller();
        
        for (int i = 0; i < listaBoleta.size(); i++) {
            if (id ==listaBoleta.get(i).getId()) {
                
                return listaBoleta.get(i).getPlacaVehiculo();
            }
        }
        return "No encontrado";
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
   
   
   
    public static int buscarBoleta(){
        System.out.println("===================================");
        System.out.println("|          BUSCAR BOLETA      |");
        System.out.println("===================================");
        System.out.println("");
        leer.nextLine();
        System.out.print("Digite id boleta: ");
        int idMante = leer.nextInt();
        int indice = -1;
        
        //--Una nueva lista para trabajar localmente(cliente)
         ArrayList<ObjBoletaTaller> miBoleta= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         miBoleta = Almacen.listarBoletaller();
         //--Recorrer con for
         for (int i = 0; i< miBoleta.size();i++) {
             ObjBoletaTaller A = new ObjBoletaTaller();
             A = miBoleta.get(i);
             if (A.getId() == idMante) {
                 indice = i;
                 break;
             }
             
        }
         return indice;
    }
     
    
   public void addBoletaTaller(){
       
       
       ObjBoletaTaller miBoleta = new ObjBoletaTaller();
       
       System.out.println("Digite el id del mantenimeinto");
       System.out.print("ID: ");
       int idMante = leer.nextInt();
       
       if(buscarCodigoAsigancion(idMante) ==-1){
           System.out.println("[!] ID no encontrado");
       }else{
           ArrayList<ObjAsignacionMantenimiento> listaAsignacion = Almacen.listarAsignacion();
            ObjAsignacionMantenimiento  modificarAsigancionVeh = new ObjAsignacionMantenimiento();
            
           ArrayList<ObjVehiculo> listarVehiculos = new ArrayList<>();
            listarVehiculos = Almacen.listarVehiculos();
            ObjVehiculo modificarVehiculo = new ObjVehiculo();
           
            
           //extraccion de datos del vehiculo
           int idBoleta = siguienteBoletaID();
           miBoleta.setId(idBoleta);
           miBoleta.setIdMantenimiento(idMante);
           
           String nombreMante = devolverNombreAsigna(idMante);
           miBoleta.setNombreMantenimeinto(nombreMante);
          
           String placaVehi = devolverPlacaAsigna(idMante);
           miBoleta.setPlacaVehiculo(placaVehi);
           
           String modeloVehi = devolverModeloVehiculo(placaVehi);
           miBoleta.setModeloVehiculo(modeloVehi);
           
           String marcaVehiculo = devolverMarcaVehiculo(placaVehi);
           miBoleta.setMarcaVehiculo(marcaVehiculo);
           
           System.out.println("Digite el kilometraje ");
           System.out.print("KM: ");
           double kmVehiculo = leer.nextDouble();
           miBoleta.setKilometrajeIngreso(kmVehiculo);
           
           int indiceAsigancion = devuelveIndiceAsigancion(placaVehi);
           modificarAsigancionVeh  = listaAsignacion.get(indiceAsigancion);
           modificarAsigancionVeh.setKmUltimo(kmVehiculo);
           
           int indiceVehiculo = devuelveIndiceVehi(placaVehi);
           modificarVehiculo = listarVehiculos.get(indiceVehiculo);
           modificarVehiculo.setKilometroActual(kmVehiculo);
           
           int opcion =0;
           do {               
               mostrarMecanicos();
               System.out.println("Digite el mecanico al que asignara");
               System.out.print("Opcion: ");
               opcion = leer.nextInt();
               
               if((opcion<1) || (opcion>10)){
                   System.out.println("");
                   System.out.println("[!] Opcion incorrecta");
               }
           } while ((opcion<1) || (opcion>10));
           
           String nombreMeca = mecanicos[opcion -1];
           miBoleta.setNombreMecanico(nombreMeca);
           
           leer.nextLine();
            
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            
            Date ingreso = null;
            int fechaValida = 0; // Bandera para controlar el ciclo
            
            do {
                try {
                    System.out.println("");
                    System.out.println("Digite la fecha de ingreso (dd/mm/aaaa): ");
                    String fechaDispensado = leer.nextLine();
                    ingreso = formato.parse(fechaDispensado);
                    
                    miBoleta.setFecha(ingreso);
                    System.out.println(ingreso);
                    
                    fechaValida = 1; 
                } catch (ParseException e) {
                    System.out.println("[!] Formato de fecha inválido. Intente de nuevo usando el formato dd/mm/aaaa.");
                    fechaValida = 0; 
                }
            } while (fechaValida == 0);
            
            Almacen.agregrarBoletaTaller(miBoleta);
            Almacen.escribeArchivoBoletaTaller();
            
            Almacen.modificarAsigancion(indiceAsigancion, modificarAsigancionVeh);
            Almacen.escribeArchivoAsignacion();
            
            Almacen.modificarVehiculo(indiceVehiculo, modificarVehiculo);
            Almacen.escribeArchivoVehiculos();
       }
   }
    
   public void consultarBoletaTaller() {
    ArrayList<ObjBoletaTaller> lista = Almacen.listarBoletaller();

    System.out.println("=======================================================================");
    System.out.println("|                 LISTA DE BOLETAS DE TALLER                          |");
    System.out.println("=======================================================================");
    System.out.println("");

    if (lista.size() == 0) {
        System.out.println("[!] No hay boletas de taller registradas actualmente.");
    } else {
        // Inicializamos los anchos mínimos basados en los encabezados
        int maxId = 2, maxIdMante = 10, maxNombre = 13, maxPlaca = 5, maxKm = 2, maxMeca = 8, maxFecha = 5,
        maxType = 2;

        // Instanciamos el formato de fecha para medir los textos limpios (dd/MM/yyyy)
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        // Recorremos para encontrar los anchos 
        for (int i = 0; i < lista.size(); i++) {
            ObjBoletaTaller a = lista.get(i);
            
            if (String.valueOf(a.getId()).length() > maxId) {
                maxId = String.valueOf(a.getId()).length();
            }
            if (String.valueOf(a.getIdMantenimiento()).length() > maxIdMante) {
                maxIdMante = String.valueOf(a.getIdMantenimiento()).length();
            }
            if (a.getNombreMantenimeinto().length() > maxNombre){
                maxNombre = a.getNombreMantenimeinto().length();
            }
            if (a.getPlacaVehiculo().length() > maxPlaca){
                maxPlaca = a.getPlacaVehiculo().length();
            }
            if (String.valueOf(a.getKilometrajeIngreso()).length() > maxKm) {
                maxKm = String.valueOf(a.getKilometrajeIngreso()).length();
            }
            if (a.getNombreMecanico().length() > maxMeca){
                maxMeca = a.getNombreMecanico().length();
            }
            
            //lo usamos para modelo tambien y marca
            if(a.getModeloVehiculo().length() > maxType){
                maxType = a.getModeloVehiculo().length();
            }
            // Evaluamos la fecha ya formateada en texto
            String fechaTexto = formatoFecha.format(a.getFecha());
            if (fechaTexto.length() > maxFecha) {
                maxFecha = fechaTexto.length();
            }
        }

        // Creamos el formato dinámico
        String formato = "%%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds\n";
        formato = String.format(formato, maxId, maxIdMante, maxNombre, maxPlaca, maxKm, maxMeca,maxType,maxType, maxFecha);

        // Imprimir encabezado
        System.out.printf(formato, "ID", "ID Mante.", "Nombre Mante.", "Placa", "KM", "Mecánico","Marca","Modelo" ,"Fecha");
        System.out.println("---------------------------------------------------------------------------------------");

        // Imprimir filas usando la fecha formateada en texto
        for (int i = 0; i < lista.size(); i++) {
            ObjBoletaTaller a = lista.get(i);
            String fechaTexto = formatoFecha.format(a.getFecha());
            
            System.out.printf(formato,
                a.getId(),
                a.getIdMantenimiento(),
                a.getNombreMantenimeinto(),
                a.getPlacaVehiculo(),
                a.getKilometrajeIngreso(),
                a.getNombreMecanico(),
                a.getMarcaVehiculo(),
                a.getModeloVehiculo(),
                fechaTexto
            );
        }
    }
    System.out.println("---------------------------------------------------------------------------------------");
}
   
   public void borrarBoleta(){
       int indiceBoleta = buscarBoleta();
       if(indiceBoleta == -1){
           System.out.println("");
           System.out.println("[!] Boleta no encontrada");
       }else{
           ArrayList<ObjBoletaTaller> miBoleta = new ArrayList<>();
           miBoleta = Almacen.listarBoletaller();
           System.out.println("Vehiculo Placa: "+ miBoleta.get(indiceBoleta).getPlacaVehiculo());
           
           ObjBoletaTaller boleta = new ObjBoletaTaller();
           boleta = miBoleta.get(indiceBoleta);
           
           Almacen.eliminarBoletaTaller(indiceBoleta);
           
           System.out.println("[✅] Borrado exitoso");
       }
   }
   
   public void modificarBoleta(){
       int indiceBoleta = buscarBoleta();
       if (indiceBoleta == -1) {
           System.out.println("[!] Boleta no encontrada");
           
       }else{
           
            
           ArrayList<ObjBoletaTaller> miboleta = new ArrayList<>();
           miboleta = Almacen.listarBoletaller();
           System.out.println("Placa: "+ miboleta.get(indiceBoleta).getPlacaVehiculo());
           
           ArrayList<ObjAsignacionMantenimiento> listaAsiganciones = Almacen.listarAsignacion();
            ObjAsignacionMantenimiento modificarAsigancionVeh = new ObjAsignacionMantenimiento();
            String placaVehiculo = miboleta.get(indiceBoleta).getPlacaVehiculo();
            int indiceAsigancionVehiculo = devuelveIndiceVehi(placaVehiculo);
            modificarAsigancionVeh = listaAsiganciones.get(indiceAsigancionVehiculo);
            
            String placa = miboleta.get(indiceBoleta).getPlacaVehiculo();
            int indiceVehiculo = devuelveIndiceVehi(placa);
            ArrayList<ObjVehiculo> listarVehiculos = new ArrayList<>();
            listarVehiculos = Almacen.listarVehiculos();
            ObjVehiculo modificarVehiculo = new ObjVehiculo();
            modificarVehiculo = listarVehiculos.get(indiceVehiculo);
           
           ObjBoletaTaller modificarBoleta = new ObjBoletaTaller();
           modificarBoleta = miboleta.get(indiceBoleta);
           
           int opcion;
           do {   
               System.out.println("");
               System.out.println("Deseas modificar el mecanico?");
               System.out.println("1. SI");
               System.out.println("2. No");
               System.out.print("Opcion: ");
               opcion = leer.nextInt();
               
               if ((opcion != 1) && (opcion != 2)) {
                   System.out.println("[!] Opcion incorrecta");
               }
           } while ((opcion != 1) && (opcion != 2));
           
           if (opcion == 1) {
                do {               
                    mostrarMecanicos();
                    System.out.println("Digite el mecanico al que asignara");
                    System.out.print("Opcion: ");
                    opcion = leer.nextInt();

                    if((opcion<1) || (opcion>10)){
                        System.out.println("");
                        System.out.println("[!] Opcion incorrecta");
                    }
                } while ((opcion<1) || (opcion>10));

                String nombreMeca = mecanicos[opcion -1];
                modificarBoleta.setNombreMecanico(nombreMeca);
           }
           
           do {   
               System.out.println("");
               System.out.println("Deseas modificar el kilometraje actual?");
               System.out.println("1. SI");
               System.out.println("2. No");
               System.out.print("Opcion: ");
               opcion = leer.nextInt();
               
               if ((opcion != 1) && (opcion != 2)) {
                   System.out.println("[!] Opcion incorrecta");
               }
           } while ((opcion != 1) && (opcion != 2));
           
           double kilometraje;
           
           if (opcion == 1) {
               leer.nextLine();
               System.out.println("Digite el nuevo kilometraje");
               System.out.print("Km: ");
               kilometraje = leer.nextDouble();
               modificarBoleta.setKilometrajeIngreso(kilometraje);
               modificarAsigancionVeh.setKmUltimo(kilometraje);
               modificarVehiculo.setKilometroActual(kilometraje);
           }
           
           do {   
               System.out.println("");
               System.out.println("Deseas modificar la fecha actual?");
               System.out.println("1. SI");
               System.out.println("2. No");
               System.out.print("Opcion: ");
               opcion = leer.nextInt();
               
               if ((opcion != 1) && (opcion != 2)) {
                   System.out.println("[!] Opcion incorrecta");
               }
           } while ((opcion != 1) && (opcion != 2));
           
           
           if(opcion == 1){
                 SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                 formato.setLenient(false);

                 Date ingreso = null;
                 int fechaValida = 0; // Bandera para controlar el ciclo

                 leer.nextLine();
                 do {
                     try {
                         System.out.println("");
                         System.out.println("Digite la fecha de ingreso (dd/mm/aaaa): ");
                         String fechaDispensado = leer.nextLine();
                         ingreso = formato.parse(fechaDispensado);

                         modificarBoleta.setFecha(ingreso);
                         System.out.println(ingreso);

                         fechaValida = 1; 
                     } catch (ParseException e) {
                         System.out.println("[!] Formato de fecha inválido. Intente de nuevo usando el formato dd/mm/aaaa.");
                         fechaValida = 0; 
                     }
                 } while (fechaValida == 0);
           }
   
           Almacen.modificarAsigancion(indiceAsigancionVehiculo, modificarAsigancionVeh);
           Almacen.modificarVehiculo(indiceVehiculo, modificarVehiculo);
           Almacen.modificarBoletaTaller(indiceBoleta, modificarBoleta);
           
           
           Almacen.escribeArchivoAsignacion();
           Almacen.escribeArchivoVehiculos();
           Almacen.escribeArchivoBoletaTaller();
       }//else
   }
    
   
   
}
