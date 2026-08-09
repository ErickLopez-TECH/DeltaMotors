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
    static ArrayList<ObjAsignacionMantenimiento> listaAsignaciones = new ArrayList<>();
   public static int contadorUserId = 0;
    private static int contadorVehiculoId = 0;
    private static int contadorMantenimientos =0;
    private static int contadorAsignaciones = 0;
    
    public Estructuras() {

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
        File miArchivo = new File(nombre + ".txt");//devuelve nombre + su extension
        try {//intento de hacer una operacion
            if (miArchivo.createNewFile()) {
                System.out.println("-----------------------------------");
                System.out.println("|    ARCHIVO "+nombre+ " CREADO   |");
                System.out.println("-----------------------------------");
            }else{
                System.out.println("----------------------------------");
                System.out.println("|  ARCHIVO "+nombre+" YA EXISTE  |");
                System.out.println("----------------------------------");
            }
        } catch (IOException e) {//captura el error si falla el intento
            JOptionPane.showMessageDialog(null, "Error al gaurdar el archivo",
                    "Atencion", JOptionPane.ERROR_MESSAGE);
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
        System.out.println("Limpiando el archivo Usuarios");
        limpiarArchivo("Usuarios");
        
        try {
            
            System.out.println("Entrando en el Try");
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
                System.out.println("Escribiendo la linea: "+ i);
            
            
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
                    System.out.println("leeido");
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
        System.out.println("Limpiando el archivo Vehiculos");
        limpiarArchivo("Vehiculos");
        
        try {
            
            System.out.println("Entrando en el Try");
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
                System.out.println("Escribiendo la linea: "+ i);
            
            
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
                    miVehiculo.setKilometroActual(Integer.parseInt(segmento[6]));
                    miVehiculo.setTipoVehiculo(segmento[7]);
                    miVehiculo.setCombustible(segmento[8]);

                    
                    listaVehiculos.add(miVehiculo);
                    System.out.println("leeido");
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
        System.out.println("Limpiando el archivo Vehiculos");
        limpiarArchivo("Mantenimientos");
        
        try {
            
            System.out.println("Entrando en el Try");
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
                System.out.println("Escribiendo la linea: "+ i);
            
            
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
                    System.out.println("leeido");
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
         Asignacion.setId(contadorUserId);
         listaAsignaciones.add(Asignacion);
     }
     
     public ArrayList<ObjAsignacionMantenimiento> listarAsignacion(){
         return new ArrayList<>(listaAsignaciones);
     }
    
}
