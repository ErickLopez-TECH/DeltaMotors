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
public class AsignaMantenimiento {
 
    
    public  void gestionAsignacion(){
        
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
            case 2: modificarAsignacion();
                break;
            case 3:eliminarAsigancion();
                
                break;
            case 4: consultarAsignaciones();
                
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
    
    public static double kmActual(String placa){
    ArrayList<ObjVehiculo> miVehi = Almacen.listarVehiculos();
    
    for (int i = 0; i < miVehi.size(); i++) {
        ObjVehiculo v = miVehi.get(i);
        // Usamos trim() en ambos lados para evitar errores por espacios en blanco accidentales
        if(v.getPlaca() != null && v.getPlaca().trim().equalsIgnoreCase(placa.trim())) {
            return v.getKilometroActual();
        }
    }
    return 0.0; // Si no encuentra la placa, retorna 0 en lugar de -1 para que no rompa el cálculo
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
    
    /*public static int tipoFrecuencia(String ){
        ArrayList<ObjAsignacionMantenimiento> miMante = new ArrayList<>();
        miMante = Almacen.listarAsignacion();
        
        for (int i = 0; i < miMante.size(); i++) {
            ObjAsignacionMantenimiento a = new ObjAsignacionMantenimiento();
            a = miMante.get(i);
            
            if()
        }
    }*/
    
     public static int buscarAsignacion(){
        System.out.println("===================================");
        System.out.println("|          BUSCAR ASIGNACION      |");
        System.out.println("===================================");
        System.out.println("");
        leer.nextLine();
        System.out.print("Digite id mantenimiento: ");
        int idMante = leer.nextInt();
        int indice = -1;
        
        //--Una nueva lista para trabajar localmente(cliente)
         ArrayList<ObjAsignacionMantenimiento> miAsignacion= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         miAsignacion = Almacen.listarAsignacion();
         //--Recorrer con for
         for (int i = 0; i< miAsignacion.size();i++) {
             ObjAsignacionMantenimiento A = new ObjAsignacionMantenimiento();
             A = miAsignacion.get(i);
             if (A.getId() == idMante) {
                 indice = i;
                 break;
             }
             
        }
         return indice;
    }
     
      //usar probto
   
    
    public static int siguienteAsignacionID(){
        
        int resultado = 1;
        //nueva lista para trabajar localmente(reservaciones)
        ArrayList<ObjAsignacionMantenimiento>  misAsig = Almacen.listarAsignacion();
        for (int i = 0; i <misAsig.size(); i++) {
            if (resultado < misAsig.get(i).getId()) {
                resultado = misAsig.get(i).getId() +1;
            }
        }
        
        return resultado;
        
    } 
    
   public static  void eliminarAsigancion(){
       int indice = buscarAsignacion();
       
       if(indice == -1){
           System.out.println("[!]No se encontro la asignacion");
       }else{
           ArrayList<ObjAsignacionMantenimiento> miAsigna = new ArrayList<>();
           miAsigna = Almacen.listarAsignacion();
           
            System.out.println("La asignacion para el vehiculo placa: "+ miAsigna.get(indice).getPlacaVehiculo());
            System.out.println("El mantenimiento: "+ miAsigna.get(indice).getNombreMantenimiento());
            System.out.println("");
            
            ObjAsignacionMantenimiento asigna = new ObjAsignacionMantenimiento();
            asigna = miAsigna.get(indice);
            
            Almacen.eliminarAsignacion(indice);
            Almacen.escribeArchivoAsignacion();
            System.out.println("[✅] Borrado exitoso");
       }
   }
    
     
   public  void modificarAsignacion() {
    int indice = buscarAsignacion();

    if (indice == -1) {
        System.out.println("[!] No se encontró la asignación");
    } else {
        ArrayList<ObjAsignacionMantenimiento> miAsigna = Almacen.listarAsignacion();
        ObjAsignacionMantenimiento a = miAsigna.get(indice);

        System.out.println("La asignación para el vehículo placa: " + a.getPlacaVehiculo());
        System.out.println("El mantenimiento: " + a.getNombreMantenimiento());

        int opcion = 0;
        do {
            System.out.println("\n¿Deseas modificar el tipo de mantenimiento (km o tiempo)?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Opcion: ");
            opcion = leer.nextInt();
        } while (opcion != 1 && opcion != 2);

        if (opcion == 1) {
            do {
                System.out.println("\nDigite el periodo de mantenimiento:");
                System.out.println("1. Por Kilómetros");
                System.out.println("2. Por días");
                System.out.print("Opcion: ");
                opcion = leer.nextInt();
            } while (opcion != 1 && opcion != 2);

            String periodicidad = periodicidad(opcion);
            a.setTipoPeriodo(periodicidad);

            if (opcion == 1) {
                System.out.print("Digite la cantidad de KM: ");
                double km = leer.nextDouble();
                a.setNumPeriodicidad(km);
                // Si cambia a KM, es recomendable limpiar fechas
                a.setIngreso(null);
                a.setVencimiento(null);
            } else {
                // Lógica de fechas igual a la de addAsignacion
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                formato.setLenient(false);
                leer.nextLine(); // Limpiar buffer después del nextInt()

                Date ingreso = null;
                int dias = 0;
                int fechaValida = 0;

                do {
                    try {
                        System.out.print("Digite la fecha de asignación (dd/mm/aaaa): ");
                        String fechaIn = leer.nextLine();
                        ingreso = formato.parse(fechaIn);

                        System.out.print("Digite el valor límite en días: ");
                        dias = leer.nextInt();
                        leer.nextLine(); // Limpiar buffer

                        Calendar miCalendario = Calendar.getInstance();
                        miCalendario.setTime(ingreso);
                        miCalendario.add(Calendar.DAY_OF_YEAR, dias);

                        a.setIngreso(ingreso);
                        a.setVencimiento(miCalendario.getTime());
                        a.setNumPeriodicidad(dias);
                        
                        System.out.println("Nueva fecha de vencimiento: " + formato.format(a.getVencimiento()));
                        fechaValida = 1;
                    } catch (ParseException e) {
                        System.out.println("[!] Fecha inválida, intente de nuevo.");
                        fechaValida =0;
                    }
                } while (fechaValida ==0);
            }

            Almacen.modificarAsigancion(indice, a);
            Almacen.escribeArchivoAsignacion();
            System.out.println("[✅] Modificación exitosa");
        }
    }
}
    
   public  void consultarAsignaciones() {
    ArrayList<ObjAsignacionMantenimiento> lista = new ArrayList<>();
    lista = Almacen.listarAsignacion();

    System.out.println("=================================================");
    System.out.println("|         Asignacion de Mantenimiento           |");
    System.out.println("=================================================");
    System.out.println("");

    if (lista.size() == 0) {
        System.out.println("[!] No hay asignaciones registradas actualmente.");
    } else {
        // 1. Valores mínimos iniciales (deben ser al menos del tamaño de la palabra del encabezado)
        int maxId = 2;       // Tamaño de "ID"
        int maxPlaca = 5;    // Tamaño de "Placa"
        int maxMante = 13;   // Tamaño de "Mantenimiento"
        int maxPeriodo = 7;  // Tamaño de "Periodo"
        int maxValor = 5;    // Tamaño de "Valor"

        // 2. Recorremos la lista para encontrar los textos más largos
        for (int i = 0; i < lista.size(); i++) {
            ObjAsignacionMantenimiento a = lista.get(i);
            
            // Convertimos el ID y el valor a String para medir sus caracteres
            if (String.valueOf(a.getId()).length() > maxId) {
                maxId = String.valueOf(a.getId()).length();
            }
            if (a.getPlacaVehiculo().length() > maxPlaca) {
                maxPlaca = a.getPlacaVehiculo().length();
            }
            if (a.getNombreMantenimiento().length() > maxMante) {
                maxMante = a.getNombreMantenimiento().length();
            }
            if (a.getTipoPeriodo().length() > maxPeriodo) {
                maxPeriodo = a.getTipoPeriodo().length();
            }
        }

        // 3. Creamos un formato dinámico que sirve tanto para el encabezado como para las filas
        // Ejemplo: "%-4s | %-8s | %-15s | %-10s | %-8s\n"
        String formato = "%%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds\n";
        formato = String.format(formato, maxId, maxPlaca, maxMante, maxPeriodo, maxValor,maxValor);

        
        System.out.printf(formato, "ID", "Placa", "Mantenimiento", "Periodo", "Valor","KM");
        
        
        System.out.println("-------------------------------------------------------------------");

        
        for (int i = 0; i < lista.size(); i++) {
            ObjAsignacionMantenimiento a = lista.get(i);
            
            System.out.printf(formato,
                a.getId(),
                a.getPlacaVehiculo(),
                a.getNombreMantenimiento(),
                a.getTipoPeriodo(),
                String.format("%.2f", a.getNumPeriodicidad()),
                a.getKmUltimo()
            );
        }
    }
    System.out.println("-------------------------------------------------");
}
   
    public void addAsignacion(){
        
        
        ObjAsignacionMantenimiento nuevaAsigna = new ObjAsignacionMantenimiento();
        
        
        
        int idAsignacion = siguienteAsignacionID();
        System.out.println("El id asignado es: "+ idAsignacion);
        nuevaAsigna.setId(idAsignacion);
        
        leer.nextLine();
        System.out.println("Digite la placa del vehiculo que desea asignar el mantenimiento");
        System.out.print("Placa: ");
        String placa = leer.nextLine();
        
       
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
            
            double numPeriodicidad = 0;
           
            
            if(opcion == 1){
                System.out.println("Digite el valor limite del periodo "+ periodicidad);
                System.out.print("Valor: ");
                numPeriodicidad = leer.nextDouble();
                
                
                
                
                
                
                
                
            }else{
                
                leer.nextLine();
                        //Definir el formato de fecha(15/08/2026)
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                formato.setLenient(false);//portillo, que rechaza fechas invalidas

                Date ingreso = null;
                Date salida = null;
                int dias = 0;
                int fechaValida = 0;
                do {                    
                    try {
                         System.out.println("");
                         System.out.println("Digite la fecha de asigancion del mantenimiento (dd/mm/aaaa): ");
                         String fechaIn = leer.nextLine();
                         ingreso = formato.parse(fechaIn);

                         System.out.println("Digite el valor limite del periodo "+ periodicidad);
                         System.out.print("Valor: ");
                         dias = leer.nextInt();



                         //--variable calendario para manejra calculo de fechas
                         Calendar miCalendario = Calendar.getInstance();
                         miCalendario.setTime(ingreso);
                         //sumamos dis a  la fecha inicial
                         miCalendario.add(Calendar.DAY_OF_YEAR, dias);

                         //asignar fecha salida
                         salida = miCalendario.getTime();
                         System.out.println("Fecha de salida" + formato.format(salida));
                         fechaValida = 1;

                     } catch (ParseException e) {
                         System.out.println(e.toString());
                         fechaValida =0;
                     }
               } while (fechaValida ==0);
                nuevaAsigna.setIngreso(ingreso);
                nuevaAsigna.setVencimiento(salida);
            }
            
            nuevaAsigna.setNumPeriodicidad(numPeriodicidad);
            
            double kmActual = kmActual(placa);
            nuevaAsigna.setKmUltimo(kmActual);
            
            
                
            Almacen.agregrarAsignacionMante(nuevaAsigna);
            Almacen.escribeArchivoAsignacion();
            
        }
        
            }
            
        
        
    }
    
}
