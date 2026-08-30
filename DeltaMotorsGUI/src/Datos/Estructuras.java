/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.util.ArrayList;
//---Bibliotecas para trabajr con archivos .txt
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/*
io/IO: I= INPUT. O= OUTPUT
Excepcion: manejar o controlar datos anomalos
*/
/**
 *
 * @author triamus
 */
public class Estructuras {

    public static ArrayList<ObjUsuarios> listaUsuarios = new ArrayList<>();
    public static ArrayList<ObjVehiculo> listaVehiculos = new ArrayList<>();
    static ArrayList<ObjMantenimiento> listaMantenimiento = new ArrayList<>();
    static ArrayList<ObjBoletaTaller> listaBoletasTaller = new ArrayList<>();
    static ArrayList<ObjBoletaCombustible> listaBoletaCombustible = new ArrayList<>();
    public static ArrayList<ObjAsignacionMantenimiento> listaAsignaciones = new ArrayList<>();
   public static int contadorUserId = 0;
    private static int contadorVehiculoId = 0;
    private static int contadorMantenimientos =0;
    private static int contadorAsignaciones = 0;
    
    
    public Estructuras() {
// 2. Leer los datos y cargarlos en las ArrayList de la memoria RAM
    crearArchivo("Usuarios");
    crearArchivo("Vehiculos");
    leerArchivoUsuarios();
    leerArchivoVehiculos();
   // leerArchivoMantenimientos();
   // leerArchivoAsignacion();
    //leerArchivoBoletaCombustible();
    //leerArchivoBoletaTaller();

    // 3. Actualizar los contadores de IDs en base a lo que se leyó
    actualizarTodosLosContadores();
    }
    
    public static void actualizarTodosLosContadores() {
    actualizarContadorUsuarios();
    actualizarContadorVehiculos();
    actualizarContadorMantenimientos();
    actualizarContadorAsignaciones();
}

public static void actualizarContadorUsuarios() {
    int maxId = 0;
    for (int i = 0; i < listaUsuarios.size(); i++) {
        ObjUsuarios u = listaUsuarios.get(i);
        if (u.getId() > maxId) {
            maxId = u.getId();
        }
    }
    contadorUserId = maxId;
}

public static void actualizarContadorVehiculos() {
    int maxId = 0;
    for (int i = 0; i < listaVehiculos.size(); i++) {
        ObjVehiculo v = listaVehiculos.get(i);
        if (v.getId() > maxId) {
            maxId = v.getId();
        }
    }
    contadorVehiculoId = maxId;
}

public static void actualizarContadorMantenimientos() {
    int maxId = 0;
    for (int i = 0; i < listaMantenimiento.size(); i++) {
        ObjMantenimiento m = listaMantenimiento.get(i);
        if (m.getId() > maxId) {
            maxId = m.getId();
        }
    }
    contadorMantenimientos = maxId;
}

public static void actualizarContadorAsignaciones() {
    int maxId = 0;
    for (int i = 0; i < listaAsignaciones.size(); i++) {
        ObjAsignacionMantenimiento a = listaAsignaciones.get(i);
        if (a.getId() > maxId) {
            maxId = a.getId();
        }
    }
    contadorAsignaciones = maxId;
}
    //--Metodos para gestionar con archivos
                            //Nombre del archivo, sin extension
    public void crearArchivo(String nombre){
    File miArchivo = new File(nombre + ".txt");
    String verde = "\u001B[92m";
    String reset = "\u001B[0m";
    
    try {
        if (miArchivo.createNewFile()) {
            // Solo imprime si el archivo NO existía y se acaba de crear
            System.out.println("-----------------------------------");
            System.out.println("|   ARCHIVO " + nombre + " CREADO |");
            System.out.println("-----------------------------------");
        }
        // Si ya existe, no hace nada en la consola y pasa desapercibido cleanly
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
        System.out.println(e.toString());
    }
}
    
    //metodo para limiar archivo
    public void limpiarArchivo(String nombre){
        try {
            PrintWriter miEscritor = new PrintWriter(nombre + ".txt");
            System.out.println("Archivo Limpio");
        } catch (IOException e) {//captura el error si falla el intento
            JOptionPane.showMessageDialog(null, "Error al gaurdar el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }
    
    //--Metodo para excribir el archivo clientes
    public void escribeArchivoUsuarios(){
        //--Antes de escribir limpiamos el archivo
        //System.out.println("Limpiando el archivo Usuarios");
        limpiarArchivo("Usuarios");
        
        try {
            
           // System.out.println("Entrando en el Try");
            FileWriter escritor = new FileWriter("Usuarios.txt", true);//true == modo lectura
            //---variable para armar una linea de escritura
            String linea = null;
            //--Recorrer lsita de cliente
            int i = 0;
            for (i = 0; i < listaUsuarios.size(); i++) {
                ObjUsuarios miUsuario = listaUsuarios.get(i);
                
                linea = String.valueOf(miUsuario.getId()) +";"+
                        miUsuario.getUsuario()            +";"+
                        miUsuario.getContrasena()         +";"+
                        miUsuario.getCedula()             +";"+
                        miUsuario.getRol()                +";"+
                        miUsuario.getEstado()             +";\n"; 
                        
                        escritor.write(linea);
            }
              //  System.out.println("Escribiendo la linea: "+ i);
            
            
            escritor.write(10);//toma ese 10 y lo tranforma en comandos de cierre de linea
            escritor.close();
        } catch (IOException e) {//captura el error si falla el intento
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
        System.out.println("--------------------------------------------");
    }
    
    //--Estructura para leer los datos en el archivo(llenar lista clientes)
    public void leerArchivoUsuarios(){
        try {
            //--apertura del archivo --Fisica
            FileReader miArchivo = new FileReader("Usuarios.txt");
            //--Cargar en la memoria RAM ese archivo para leerlo
            BufferedReader lector = new BufferedReader(miArchivo);
            //--variable para cargar las lineas del texto
            String linea = lector.readLine();//se aposiciona en la primera linea
            //--variable controlar los segmentos de texto -vector/array simples
            String segmento[];
            
            while (linea != null) {                
                //--Dividir "Linea" en cada separador ";" 
                //cada sub segmento se incuye en las variables del objetos
                segmento = linea.split(";");
                if(!segmento[0].equals("")){
                    ObjUsuarios miUsuarios = new ObjUsuarios();
                    miUsuarios.setId(Integer.parseInt(segmento[0]));
                    miUsuarios.setUsuario(segmento[1]);
                    miUsuarios.setContrasena(segmento[2]);
                    miUsuarios.setCedula(segmento[3]);
                    miUsuarios.setRol(segmento[4]);
                    miUsuarios.setEstado(segmento[5]);
                    listaUsuarios.add(miUsuarios);
                   // System.out.println("leeido");
                }
                linea = lector.readLine();//pasar a la siguiente linea
                
            }
            
        } catch (IOException e) {//captura el error si falla el intento
            JOptionPane.showMessageDialog(null, "Error al leer el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        
        }
    }
    
    
    /*---------------------------------------------------------------
                             metodo de vehiculos de leer y escribit
    -------------------------------------------------------------*/
    
    public void escribeArchivoVehiculos(){
        //--Antes de escribir limpiamos el archivo
        //System.out.println("Limpiando el archivo Vehiculos");
        limpiarArchivo("Vehiculos");
        
        try {
            
            //System.out.println("Entrando en el Try");
            FileWriter escritor = new FileWriter("Vehiculos.txt", true);//true == modo lectura
            //---variable para armar una linea de escritura
            String linea = null;
            //--Recorrer lsita de cliente
            int i = 0;
            for (i = 0; i < listaVehiculos.size(); i++) {
                ObjVehiculo miVehiculo = listaVehiculos.get(i);
                
    
                linea = String.valueOf(miVehiculo.getId()) +";"+
                        miVehiculo.getPlaca()              +";"+
                        miVehiculo.getMarca()              +";"+
                        miVehiculo.getModelo()             +";"+
                        String.valueOf(miVehiculo.getAnio())               +";"+
                        miVehiculo.getEstado()             +";"+
                        String.valueOf(miVehiculo.getKilometroActual())    +";"+
                        miVehiculo.getTipoVehiculo()       +";"+
                        miVehiculo.getCombustible()        +";\n"; 
                        
                        escritor.write(linea);
            }
               // System.out.println("Escribiendo la linea: "+ i);
            
            
            escritor.write(10);//toma ese 10 y lo tranforma en comandos de cierre de linea
            escritor.close();
        } catch (IOException e) {//captura el error si falla el intento
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
        System.out.println("--------------------------------------------");
    }
    
    //--Estructura para leer los datos en el archivo(llenar lista clientes)
    public void leerArchivoVehiculos(){
        try {
            //--apertura del archivo --Fisica
            FileReader miArchivo = new FileReader("Vehiculos.txt");
            //--Cargar en la memoria RAM ese archivo para leerlo
            BufferedReader lector = new BufferedReader(miArchivo);
            //--variable para cargar las lineas del texto
            String linea = lector.readLine();//se aposiciona en la primera linea
            //--variable controlar los segmentos de texto -vector/array simples
            String segmento[];
            
            while (linea != null) {                
                //--Dividir "Linea" en cada separador ";" 
                //cada sub segmento se incuye en las variables del objetos
                segmento = linea.split(";");
                if(!segmento[0].equals("")){
                    ObjVehiculo miVehiculo = new ObjVehiculo();
                    miVehiculo.setId(Integer.parseInt(segmento[0]));
                    miVehiculo.setPlaca(segmento[1]);
                    miVehiculo.setMarca(segmento[2]);
                    miVehiculo.setModelo(segmento[3]);
                    miVehiculo.setAnio(Integer.parseInt(segmento[4]));
                    miVehiculo.setEstado(segmento[5]);
                    miVehiculo.setKilometroActual(Double.parseDouble(segmento[6]));
                    miVehiculo.setTipoVehiculo(segmento[7]);
                    miVehiculo.setCombustible(segmento[8]);

                    
                    listaVehiculos.add(miVehiculo);
                    //System.out.println("leeido");
                }
                linea = lector.readLine();//pasar a la siguiente linea
                
            }
            
        } catch (IOException e) {//captura el error si falla el intento
            JOptionPane.showMessageDialog(null, "Error al leer el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        
        }
    }
    
    /*---------------------------------------------------
    |--------------Metodos gestion mante leer y escribir---|
    ----------------------------------------------------*/
    public void escribeArchivoMantenimientos(){
        //--Antes de escribir limpiamos el archivo
       // System.out.println("Limpiando el archivo Vehiculos");
        limpiarArchivo("Mantenimientos");
        
        try {
            
          //  System.out.println("Entrando en el Try");
            FileWriter escritor = new FileWriter("Mantenimientos.txt", true);//true == modo lectura
            //---variable para armar una linea de escritura
            String linea = null;
            //--Recorrer lsita de cliente
            int i = 0;
            for (i = 0; i < listaMantenimiento.size(); i++) {
                ObjMantenimiento miMante = listaMantenimiento.get(i);
                
    
                linea = String.valueOf(miMante.getId())    +";"+
                        miMante.getNombre()                +";"+
                        miMante.getEstado()                +";\n"; 
                        
                        escritor.write(linea);
            }
               // System.out.println("Escribiendo la linea: "+ i);
            
            
            escritor.write(10);//toma ese 10 y lo tranforma en comandos de cierre de linea
            escritor.close();
        } catch (IOException e) {//captura el error si falla el intento
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
        System.out.println("--------------------------------------------");
    }
    
    //--Estructura para leer los datos en el archivo(llenar lista clientes)
    public void leerArchivoMantenimientos(){
        try {
            //--apertura del archivo --Fisica
            FileReader miArchivo = new FileReader("Mantenimientos.txt");
            //--Cargar en la memoria RAM ese archivo para leerlo
            BufferedReader lector = new BufferedReader(miArchivo);
            //--variable para cargar las lineas del texto
            String linea = lector.readLine();//se aposiciona en la primera linea
            //--variable controlar los segmentos de texto -vector/array simples
            String segmento[];
            
            while (linea != null) {                
                //--Dividir "Linea" en cada separador ";" 
                //cada sub segmento se incuye en las variables del objetos
                segmento = linea.split(";");
                if(!segmento[0].equals("")){
                    ObjMantenimiento miMantenimiento = new ObjMantenimiento();
                    miMantenimiento.setId(Integer.parseInt(segmento[0]));
                    miMantenimiento.setNombre(segmento[1]);
                    miMantenimiento.setEstado(segmento[2]);
                    
                    listaMantenimiento.add(miMantenimiento);
                   // System.out.println("leeido");
                }
                linea = lector.readLine();//pasar a la siguiente linea
                
            }
            
        } catch (IOException e) {//captura el error si falla el intento
            JOptionPane.showMessageDialog(null, "Error al leer el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        
        }
    }
  
    
     /*---------------------------------------------------
    |--------------Metodos Asignacion mante leer y escribir---|
    ----------------------------------------------------*/
/*---------------------------------------------------
    |--------------Metodos Asignacion mante leer y escribir---|
    ----------------------------------------------------*/
    public void escribeArchivoAsignacion(){
        //--Antes de escribir limpiamos el archivo
       // System.out.println("Limpiando el archivo AsignacionMante");
        limpiarArchivo("AsignacionMante");
        
        try {
            System.out.println("Entrando en el Try");
            FileWriter escritor = new FileWriter("AsignacionMante.txt", true);
            String linea = null;
            
            // Creamos el formato para convertir la fecha a texto plano (dd/MM/yyyy)
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
               
            int i = 0;
            for (i = 0; i < listaAsignaciones.size(); i++) {
                ObjAsignacionMantenimiento miAsignacion = listaAsignaciones.get(i);
                
                // Convertimos la fecha de ingreso a String 
                String fechaAsignada = "";
                if (miAsignacion.getIngreso() != null) {
                    fechaAsignada = formatoFecha.format(miAsignacion.getIngreso());
                }
                
                // Convertimos la fecha de vencimiento a String 
                String fechaPosterior = "";
                if (miAsignacion.getVencimiento() != null) {
                    fechaPosterior = formatoFecha.format(miAsignacion.getVencimiento());
                }
    
                
                linea = String.valueOf(miAsignacion.getId())    + ";" +
                        miAsignacion.getPlacaVehiculo()         + ";" +
                        miAsignacion.getNombreMantenimiento()   + ";" +
                        miAsignacion.getTipoPeriodo()           + ";" +
                        miAsignacion.getNumPeriodicidad()       + ";" +
                        miAsignacion.getKmUltimo()              + ";" +
                        fechaAsignada                           + ";" +
                        fechaPosterior                          + ";\n"; 
                        
                escritor.write(linea);
            }
            //System.out.println("Escribiendo la linea: "+ i);
            
            escritor.write(10);
            escritor.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
        System.out.println("--------------------------------------------");
    }
    
    //--Estructura para leer los datos en el archivo
    public void leerArchivoAsignacion(){
        try {
            FileReader miArchivo = new FileReader("AsignacionMante.txt");
            BufferedReader lector = new BufferedReader(miArchivo);
            String linea = lector.readLine();
            String segmento[];
            
            while (linea != null) {                
                segmento = linea.split(";");
                if(!segmento[0].equals("")){
                    ObjAsignacionMantenimiento miAsignacion = new ObjAsignacionMantenimiento();
                    miAsignacion.setId(Integer.parseInt(segmento[0].trim()));
                    miAsignacion.setPlacaVehiculo(segmento[1].trim());
                    miAsignacion.setNombreMantenimiento(segmento[2].trim());
                    miAsignacion.setTipoPeriodo(segmento[3].trim());
                    
                    float valorPeriodo = Float.parseFloat(segmento[4].trim());
                    miAsignacion.setNumPeriodicidad((int) valorPeriodo);
                    
                    float valorKm = Float.parseFloat(segmento[5].trim());
                    miAsignacion.setKmUltimo((int) valorKm);
                    
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    formato.setLenient(false);

                    try {
                        //  Lee la fecha de ingreso solo si no está vacía
                        if(segmento.length > 6 && !segmento[6].trim().isEmpty()) {
                            Date fechaParsed = formato.parse(segmento[6].trim());
                            miAsignacion.setIngreso(fechaParsed);
                        }
                        
                        //  Lee la fecha de vencimiento solo si no está vacía
                        if(segmento.length > 7 && !segmento[7].trim().isEmpty()) {
                            Date fechaPosterior = formato.parse(segmento[7].trim());
                            miAsignacion.setVencimiento(fechaPosterior);
                        }
                    } catch (ParseException e) {
                        System.out.println("[!] Error al convertir la fecha desde el archivo: " + e.getMessage());
                    }
                    
                    listaAsignaciones.add(miAsignacion);
                   // System.out.println("leido");
                }
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        } catch (NumberFormatException e) {
            System.out.println("[!] Error de formato numérico: " + e.getMessage());
        }
    }
    /*---------------------------------------------------
    |--------------Metodos BoletaCombustible leer y escribir---|
    ----------------------------------------------------*/
   public void escribeArchivoBoletaCombus(){
        //--Antes de escribir limpiamos el archivo
       // System.out.println("Limpiando el archivo Combustible");
        limpiarArchivo("BoletaCombustible");
        
        try {
           // System.out.println("Entrando en el Try");
            FileWriter escritor = new FileWriter("BoletaCombustible.txt", true);
            String linea = null;
            
            // Creamos el formato para convertir la fecha a texto plano (dd/MM/yyyy)
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            
            int i = 0;
            for (i = 0; i < listaBoletaCombustible.size(); i++) {
                ObjBoletaCombustible miCombustible = listaBoletaCombustible.get(i);
                
                
                // Convertimos la fecha a String formateado de manera segura
                String fechaTexto = "";
                if (miCombustible.getFecha() != null) {
                    fechaTexto = formatoFecha.format(miCombustible.getFecha());
                }
    
                linea = String.valueOf(miCombustible.getId())    + ";" +
                        miCombustible.getPlacaVehiculo()         + ";" +
                        miCombustible.getKmActual()              + ";" +
                        miCombustible.getCantidadCombustible()   + ";" +
                        miCombustible.getCantidadKWH()           + ";" +
                        miCombustible.getTipoCombustible()       + ";" +
                        fechaTexto                               + ";\n"; 
                        
                escritor.write(linea);
            }
           // System.out.println("Escribiendo la linea: "+ i);
            
            escritor.write(10);
            escritor.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al escribir el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
        System.out.println("--------------------------------------------");
    }
    //--Estructura para leer los datos en el archivo(llenar lista clientes)
   public void leerArchivoBoletaCombustible(){
        try {
            FileReader miArchivo = new FileReader("BoletaCombustible.txt");
            BufferedReader lector = new BufferedReader(miArchivo);
            String linea = lector.readLine();
            String segmento[];
            
            while (linea != null) {                
                segmento = linea.split(";");
                if(!segmento[0].equals("")){
                    ObjBoletaCombustible miBoleta= new ObjBoletaCombustible();
                    miBoleta.setId(Integer.parseInt(segmento[0].trim()));
                    miBoleta.setPlacaVehiculo(segmento[1].trim());
                    
                    //  CORRECCIÓN: Leer como double para evitar el fallo con decimales (".0")
                    double valorKm = Double.parseDouble(segmento[2].trim());
                    miBoleta.setKmActual(valorKm); // Si tu setKmActual recibe double, o usa (int)valorKm si es entero
                    
                    double valorCantidaCombus = Double.parseDouble(segmento[3].trim());
                    miBoleta.setCantidadCombustible(valorCantidaCombus);

                    double valorCantidaKwH = Double.parseDouble(segmento[4].trim());
                    miBoleta.setCantidadKWH(valorCantidaKwH);
                    
                    miBoleta.setTipoCombustible(segmento[5].trim());
                    
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    formato.setLenient(false);

                    try {
                        Date fechaParsed = formato.parse(segmento[6].trim());
                        miBoleta.setFecha(fechaParsed);
                    } catch (ParseException e) {
                        System.out.println("[!] Error al convertir la fecha desde el archivo.");
                    }
                    
                    listaBoletaCombustible.add(miBoleta);
                   // System.out.println("leido");
                }
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        } catch (NumberFormatException e) {
            System.out.println("[!] Error de formato numérico: " + e.getMessage());
        }
    }
   
   /*-----------------------------------------------------
   ----------------LEER Y ESCRIBIR DE BOLETA TALLER
   
   -----------------------------------------------------*/
   public void escribeArchivoBoletaTaller(){
    //System.out.println("Limpiando el archivo BoletaTaller");
    limpiarArchivo("BoletaTaller");
    
    try {
        //System.out.println("Entrando en el Try");
        FileWriter escritor = new FileWriter("BoletaTaller.txt", true);
        String linea = null;
        
        // Formato estándar de fecha para que se guarde como texto plano (dd/MM/yyyy)
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        int i = 0;
        for (i = 0; i < listaBoletasTaller.size(); i++) {
            ObjBoletaTaller miBoleta = listaBoletasTaller.get(i);
            
            // Conversión 
            String fechaTexto = "";
            if (miBoleta.getFecha() != null) {
                fechaTexto = formatoFecha.format(miBoleta.getFecha());
            }

            
            linea = String.valueOf(miBoleta.getId())               + ";" +
                    miBoleta.getIdMantenimiento()                  + ";" +
                    miBoleta.getNombreMantenimeinto()              + ";" +
                    miBoleta.getPlacaVehiculo()                    + ";" +
                    miBoleta.getModeloVehiculo()                   + ";" +
                    miBoleta.getMarcaVehiculo()                    + ";" +
                    miBoleta.getKilometrajeIngreso()               + ";" +
                    fechaTexto                                     + ";" +
                    miBoleta.getNombreMecanico()                   + ";\n"; 
                    
            escritor.write(linea);
        }
        //System.out.println("Escribiendo la linea: "+ i);
        
        escritor.write(10);
        escritor.close();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al escribir el archivo",
                "Atencion", JOptionPane.ERROR_MESSAGE);
        System.out.println(e.toString());
    }
    System.out.println("--------------------------------------------");
}
   
   public void leerArchivoBoletaTaller(){
    try {
        FileReader miArchivo = new FileReader("BoletaTaller.txt");
        BufferedReader lector = new BufferedReader(miArchivo);
        String linea = lector.readLine();
        String segmento[];
        
        while (linea != null) {                
            segmento = linea.split(";");
            if(!segmento[0].equals("")){
                ObjBoletaTaller miBoleta = new ObjBoletaTaller();
                
                
                miBoleta.setId(Integer.parseInt(segmento[0].trim()));
                miBoleta.setIdMantenimiento(Integer.parseInt(segmento[1].trim()));
                miBoleta.setNombreMantenimeinto(segmento[2].trim());
                miBoleta.setPlacaVehiculo(segmento[3].trim());
                miBoleta.setModeloVehiculo(segmento[4].trim());
                miBoleta.setMarcaVehiculo(segmento[5].trim());
                
                // Uso de double por si el kilometraje trae decimales tipo ".0" desde el archivo
                double valorKm = Double.parseDouble(segmento[6].trim());
                miBoleta.setKilometrajeIngreso(valorKm);
                
                // Conversión  de la fecha
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                formato.setLenient(false);

                try {
                    if(segmento.length > 7 && !segmento[7].trim().isEmpty()) {
                        Date fechaParsed = formato.parse(segmento[7].trim());
                        miBoleta.setFecha(fechaParsed);
                    }
                } catch (ParseException e) {
                    System.out.println("[!] Error al convertir la fecha desde el archivo: " + e.getMessage());
                }
                
                miBoleta.setNombreMecanico(segmento[8].trim());
                
                listaBoletasTaller.add(miBoleta);
               // System.out.println("leido");
            }
            linea = lector.readLine();
        }
        lector.close();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
        System.out.println(e.toString());
    } catch (NumberFormatException e) {
        System.out.println("[!] Error de formato numérico: " + e.getMessage());
    }
}
   
    /*---------------------------------------------------
    |--------------Metodos usuarios----------------------|
    ----------------------------------------------------*/
    
  
    public void agregarUsuarios(ObjUsuarios usuario){
        this.contadorUserId++;
        usuario.setId(contadorUserId);
        listaUsuarios.add(usuario);
        
    }
    
    public void modificarUsuario(int indice, ObjUsuarios usuario ){
        listaUsuarios.set(indice, usuario);
    }
    
    public void eliminarCliente(int indice){
        listaUsuarios.remove(indice);
    }
    
    public ArrayList<ObjUsuarios> consultarUsuarios(){
        
        return new ArrayList<ObjUsuarios>(listaUsuarios);
    }
    
    
    
     
    /*---------------------------------------------------
    |--------------Metodos vehiculos----------------------|
    ----------------------------------------------------*/
    
    public void agregarVehiculo(ObjVehiculo vehiculo){
        this.contadorVehiculoId++;
        vehiculo.setId(contadorVehiculoId);
        listaVehiculos.add(vehiculo);
        
    }
    
    public void modificarVehiculo(int indice, ObjVehiculo vehiculo ){
        listaVehiculos.set(indice, vehiculo);
    }
    
    public void eliminarVehiculo(int indice){
        listaVehiculos.remove(indice);
    }
    
    public ArrayList<ObjVehiculo> listarVehiculos(){
        
        return new ArrayList<ObjVehiculo>(listaVehiculos);
    }
    
    
    
   /*---------------------------------------------------
    |--------------Metodos mantenimeintos---------------|
    ----------------------------------------------------*/
     public void agregarMantenimiento(ObjMantenimiento manteniminto){
         this.contadorMantenimientos++;
         manteniminto.setId(contadorMantenimientos);
         listaMantenimiento.add(manteniminto);
     }
     
     public void modificarMantenimiento(int indice, ObjMantenimiento mante){
         listaMantenimiento.set(indice, mante);
     }
     
     public void eliminarMantenimiento(int indice){
         listaMantenimiento.remove(indice);
     }
     
     public ArrayList<ObjMantenimiento> listarMantenimiento(){
         return new ArrayList<>(listaMantenimiento);
     }
     
     /*---------------------------------------------------
    |--------------Metodos asignar mantenimeintos--------|
    ----------------------------------------------------*/
     public void agregrarAsignacionMante(ObjAsignacionMantenimiento Asignacion){
         this.contadorAsignaciones++;
         Asignacion.setId(contadorAsignaciones);
         listaAsignaciones.add(Asignacion);
     }
     
     public void modificarAsigancion(int indice, ObjAsignacionMantenimiento Asigancion){
         listaAsignaciones.set(indice, Asigancion);
     }
     
     public void eliminarAsignacion(int indice){
         listaAsignaciones.remove(indice);
     }
     
     public ArrayList<ObjAsignacionMantenimiento> listarAsignacion(){
         return new ArrayList<>(listaAsignaciones);
     }
    
      /*---------------------------------------------------
    |--------------Metodos Boleta combustible--------|
    ----------------------------------------------------*/
     public void agregrarBoletaCombus(ObjBoletaCombustible boletaCombustible){
         listaBoletaCombustible.add(boletaCombustible);
     }
     
     public void eliminarBoletaCombus(int indice){
         listaBoletaCombustible.remove(indice);
     }
     
     public void modificarBoletaCombus(int indice, ObjBoletaCombustible miBoleta){
         listaBoletaCombustible.set(indice, miBoleta);
     }
     
     public ArrayList<ObjBoletaCombustible> listarBoletaCombus(){
         return new ArrayList<>(listaBoletaCombustible);
     }
     
      /*---------------------------------------------------
    |--------------Metodos Boleta Taller--------|
    ----------------------------------------------------*/
     public void agregrarBoletaTaller(ObjBoletaTaller boletaTaller){
         listaBoletasTaller.add(boletaTaller);
     }
     
     public void eliminarBoletaTaller(int indice){
         listaBoletasTaller.remove(indice);
     }
     
     public void modificarBoletaTaller(int indice, ObjBoletaTaller miBoleta){
         listaBoletasTaller.set(indice, miBoleta);
     }
     
     public ArrayList<ObjBoletaTaller> listarBoletaller(){
         return new ArrayList<>(listaBoletasTaller);
     }
     
     
}
