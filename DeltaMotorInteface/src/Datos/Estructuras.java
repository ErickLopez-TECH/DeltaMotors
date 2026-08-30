/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author triamus
 */
public class Estructuras {
    
   private ArrayList<objUsuarios> listaUsuarios;
   private ArrayList<objVehiculo> listaVehiculo;
   
   //inicializamos la lista
   public Estructuras(){
       this.listaUsuarios = new ArrayList<>();
       this.listaVehiculo = new ArrayList<>();
   }
   
   //devuelve la lista
   public ArrayList<objUsuarios> getListaUsuarios(){
       return listaUsuarios;
   }
   
   public ArrayList<objVehiculo> getListaVehiculo(){
       return listaVehiculo;
   }
   
   public void crearArchivo(String nombre){
       File miArchivo = new File(nombre + ".txt");
       
       try {
           if(miArchivo.createNewFile())
               System.out.println("Archivo: "+ nombre);
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al crear el archivo","Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   // Limpiar archivo
   public void limpiarArchivo(String nombre) {
        try {
            PrintWriter miEscritor = new PrintWriter(nombre + ".txt");
            miEscritor.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
        }
    }
   public void escribeArchivoVehiculo() {
    try {
        // Al abrir el FileWriter sin el 'true', sobrescribe el archivo completo 
        // con la lista actualizada que tiene todos los usuarios (los viejos + el nuevo).
        FileWriter escritor = new FileWriter("Vehiculo.txt");
        PrintWriter pw = new PrintWriter(escritor);
        
        for (int i = 0; i < listaVehiculo.size(); i++) {
            objVehiculo v = listaVehiculo.get(i);
            String linea = v.getId() + ";" +
                           v.getPlaca()+ ";" +
                           v.getMarca()+ ";" +
                           v.getModelo()+ ";" +
                           v.getTipoMotor()+ ";" +
                           v.getCombustible()+ ";" +
                           v.getKilometraje()+ ";" +
                           v.getAnio()+ ";" +
                           v.getEstado()+ ";" ;
                           
            pw.println(linea); // Usar println es más limpio para los saltos de línea
        }
        pw.close();
        escritor.close();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al escribir el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
    }
}
   
   
    public void leerArchivoVehiculo() {
        listaUsuarios.clear();
        try {
            File archivo = new File("Vehiculo.txt");
            if (!archivo.exists()) {
                return; 
            }
            FileReader miArchivo = new FileReader(archivo);
            BufferedReader lector = new BufferedReader(miArchivo);
            String linea = lector.readLine();
            
            while (linea != null) {
                String[] segmento = linea.split(";");
                // Como guardamos 5 datos, validamos que al menos tenga 5 segmentos
                if (segmento.length >= 5 && !segmento[0].equals("")) {
                    objVehiculo v= new objVehiculo();
                    v.setId(Integer.parseInt(segmento[0].trim()));
                    v.setPlaca(segmento[1].trim());
                    v.setMarca(segmento[2].trim());
                    v.setModelo(segmento[3].trim()); 
                    v.setTipoMotor(segmento[4].trim()); 
                    v.setCombustible(segmento[5].trim()); 
                    v.setKilometraje(Double.parseDouble(segmento[6].trim()));
                    v.setAnio(Integer.parseInt(segmento[7].trim()));    
                    v.setEstado(segmento[8].trim());    
                    
                    listaVehiculo.add(v);
                }
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
        }
    }
   // Escribe la lista actual en el archivo de texto plano
public void escribeArchivoUsuarios() {
    try {
        // Al abrir el FileWriter sin el 'true', sobrescribe el archivo completo 
        // con la lista actualizada que tiene todos los usuarios (los viejos + el nuevo).
        FileWriter escritor = new FileWriter("Usuarios.txt");
        PrintWriter pw = new PrintWriter(escritor);
        
        for (int i = 0; i < listaUsuarios.size(); i++) {
            objUsuarios u = listaUsuarios.get(i);
            String linea = u.getId() + ";" +
                           u.getNombre()+ ";" +
                           u.getCedula()+ ";" +
                           u.getContrasena() + ";" +
                           u.getRol()+ ";" +
                           u.getEstado() + ";";
            pw.println(linea); // Usar println es más limpio para los saltos de línea
        }
        pw.close();
        escritor.close();
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al escribir el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
    }
}

    // Lee el archivo de texto y carga los datos al ArrayList
    // Lee el archivo de texto y carga los datos al ArrayList
    public void leerArchivoUsuarios() {
        listaUsuarios.clear();
        try {
            File archivo = new File("Usuarios.txt");
            if (!archivo.exists()) {
                return; 
            }
            FileReader miArchivo = new FileReader(archivo);
            BufferedReader lector = new BufferedReader(miArchivo);
            String linea = lector.readLine();
            
            while (linea != null) {
                String[] segmento = linea.split(";");
                // Como guardamos 5 datos, validamos que al menos tenga 5 segmentos
                if (segmento.length >= 5 && !segmento[0].equals("")) {
                    objUsuarios miUsuario = new objUsuarios();
                    miUsuario.setId(Integer.parseInt(segmento[0].trim()));
                    miUsuario.setNombre(segmento[1].trim());
                    miUsuario.setCedula(segmento[2].trim());
                    miUsuario.setContrasena(segmento[3].trim()); // <--- ¡Leemos la contraseña!
                    miUsuario.setRol(segmento[4].trim());       // <--- ¡Leemos el rol!
                    miUsuario.setEstado(segmento[5].trim());    // <--- ¡Leemos el estado!
                    
                    listaUsuarios.add(miUsuario);
                }
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Método inteligente: Calcula el siguiente ID evaluando como entero el mayor ID existente
    public int generarIdUsuarioAutomatico() {
        leerArchivoUsuarios();
        if (listaUsuarios.isEmpty()) {
            return 1; // Si no hay registros, empieza en 1
        }
        
        int mayorId = 0;
        for (int i = 0; i < listaUsuarios.size(); i++) {
            objUsuarios U = listaUsuarios.get(i);
            if (U.getId()> mayorId) {
                mayorId = U.getId();
            }
        }
        return mayorId + 1; // Devuelve el siguiente número consecutivo
    }

    // Agrega el usuario asignándole el ID automático y guardándolo en el TXT
    public void agregarUsuarios(objUsuarios usuario) {
        int idCalculado = generarIdUsuarioAutomatico();
        usuario.setId(idCalculado);
        
        listaUsuarios.add(usuario);
        escribeArchivoUsuarios(); 
    }
    
     public int generarIdVehiculoAutomatico() {
        leerArchivoVehiculo();
        if (listaVehiculo.isEmpty()) {
            return 1; // Si no hay registros, empieza en 1
        }
        
        int mayorId = 0;
        for (int i = 0; i < listaVehiculo.size(); i++) {
            objVehiculo v = listaVehiculo.get(i);
            if (v.getId()> mayorId) {
                mayorId = v.getId();
            }
        }
        return mayorId + 1; // Devuelve el siguiente número consecutivo
    }

    // Agrega el usuario asignándole el ID automático y guardándolo en el TXT
    public void agregarVehiculo(objVehiculo vehiculo) {
        int idCalculado = generarIdVehiculoAutomatico();
        vehiculo.setId(idCalculado);
        
        listaVehiculo.add(vehiculo);
        escribeArchivoVehiculo(); 
    }
}
