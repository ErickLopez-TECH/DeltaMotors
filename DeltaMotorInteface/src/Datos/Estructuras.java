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
import javax.swing.JOptionPane;
/**
 *
 * @author triamus
 */
public class Estructuras {
    
   private ArrayList<objUsuarios> listaUsuarios;
   private ArrayList<objVehiculo> listaVehiculo;
   private ArrayList<objGestionMante> listaMante;
   private ArrayList<objAsignacionMante> listaAsignacionesMante;
   private ArrayList<objBoletaCombus> listaBoletaCombus ;
   private ArrayList<objMecanicos> listaMecanicos;
   private ArrayList<objBoletaTaller> listaBoletaTaller;
   
   
   //inicializamos la lista
   public Estructuras(){
       this.listaUsuarios = new ArrayList<>();
       this.listaVehiculo = new ArrayList<>();
       this.listaMante = new ArrayList<>();
       this.listaAsignacionesMante = new ArrayList<>();
       this.listaBoletaCombus = new ArrayList<>();
       this.listaMecanicos = new ArrayList<>();
       this.listaBoletaTaller = new ArrayList<>();
   }
   
   
/*---------------------------------------------------------------------------
   Funciones getter, setter y demas funciones del listaUsuarios
 -------------------------------------------------------------------------  */
   
   public ArrayList<objUsuarios> getListaUsuarios(){
       return listaUsuarios;
   }

   public void setListaUsuarios(ArrayList<objUsuarios> listaUsuarios) {
       this.listaUsuarios = listaUsuarios;
   }

   public void escribeArchivoUsuarios() {
       try {
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
               pw.println(linea);
           }
           pw.close();
           escritor.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al escribir el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }

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
               if (segmento.length >= 5 && !segmento[0].equals("")) {
                   objUsuarios miUsuario = new objUsuarios();
                   miUsuario.setId(Integer.parseInt(segmento[0].trim()));
                   miUsuario.setNombre(segmento[1].trim());
                   miUsuario.setCedula(segmento[2].trim());
                   miUsuario.setContrasena(segmento[3].trim()); 
                   miUsuario.setRol(segmento[4].trim());       
                   miUsuario.setEstado(segmento[5].trim());    
                   
                   listaUsuarios.add(miUsuario);
               }
               linea = lector.readLine();
           }
           lector.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }

   public int generarIdUsuarioAutomatico() {
       leerArchivoUsuarios();
       if (listaUsuarios.isEmpty()) {
           return 1; 
       }
       
       int mayorId = 0;
       for (int i = 0; i < listaUsuarios.size(); i++) {
           objUsuarios U = listaUsuarios.get(i);
           if (U.getId()> mayorId) {
               mayorId = U.getId();
           }
       }
       return mayorId + 1; 
   }

   public void agregarUsuarios(objUsuarios usuario) {
       int idCalculado = generarIdUsuarioAutomatico();
       usuario.setId(idCalculado);
       
       listaUsuarios.add(usuario);
       escribeArchivoUsuarios(); 
   }
  
  /*---------------------------------------------------------------------------
   Funciones getter, setter y demas funciones del listaVehiculos
 -------------------------------------------------------------------------  */
  
   public ArrayList<objVehiculo> getListaVehiculo(){
       return listaVehiculo;
   }

   public void setListaVehiculo(ArrayList<objVehiculo> listaVehiculo) {
       this.listaVehiculo = listaVehiculo;
   }

   public boolean actualizarKilometrajeVehiculo(String placa, double nuevoKm) {
       leerArchivoVehiculo(); 
       boolean encontrado = false;
       
       for (objVehiculo v : listaVehiculo) {
           if (v.getPlaca().equalsIgnoreCase(placa.trim())) {
               v.setKilometraje(nuevoKm);
               encontrado = true;
               break;
           }
       }
       
       if (encontrado) {
           escribeArchivoVehiculo(); 
           return true;
       }
       return false;
   }

   public void escribeArchivoVehiculo() {
       try {
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
                              
               pw.println(linea); 
           }
           pw.close();
           escritor.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al escribir el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void leerArchivoVehiculo() {
       listaVehiculo.clear();
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

   public int generarIdVehiculoAutomatico() {
       leerArchivoVehiculo();
       if (listaVehiculo.isEmpty()) {
           return 1; 
       }
       
       int mayorId = 0;
       for (int i = 0; i < listaVehiculo.size(); i++) {
           objVehiculo v = listaVehiculo.get(i);
           if (v.getId()> mayorId) {
               mayorId = v.getId();
           }
       }
       return mayorId + 1; 
   }

   public void agregarVehiculo(objVehiculo vehiculo) {
       int idCalculado = generarIdVehiculoAutomatico();
       vehiculo.setId(idCalculado);
       
       listaVehiculo.add(vehiculo);
       escribeArchivoVehiculo(); 
   }
   
   /*---------------------------------------------------------------------------
   Funciones getter, setter y demas funciones del listaMante
 -------------------------------------------------------------------------  */
  
   public ArrayList<objGestionMante> getListaMantes(){
       return listaMante;
   }

   public void setListaMante(ArrayList<objGestionMante> listaMante) {
       this.listaMante = listaMante;
   }

   public boolean eliminarManteArchivo(int idMante) {
       leerArchivoMante(); 
       
       ArrayList<objGestionMante> lista = getListaMantes();
       boolean encontrado = false;
       
       for (int i = 0; i < lista.size(); i++) {
           if (lista.get(i).getId() == idMante) {
               lista.remove(i);
               encontrado = true;
               break;
           }
       }
       
       if (encontrado) {
           setListaMante(lista);
           escribeArchivoMante(); 
           return true;
       }
       
       return false;
   }

   public void escribeArchivoMante() {
       try {
           FileWriter escritor = new FileWriter("GestionMante.txt");
           PrintWriter pw = new PrintWriter(escritor);
           
           for (int i = 0; i < listaMante.size(); i++) {
               objGestionMante m = listaMante.get(i);
               String linea = m.getId() + ";" +
                              m.getNombre()+ ";" +
                              m.getEstado()+ ";" ;
                              
               pw.println(linea); 
           }
           pw.close();
           escritor.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al escribir el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void leerArchivoMante() {
       listaMante.clear();
       try {
           File archivo = new File("GestionMante.txt");
           if (!archivo.exists()) {
               return; 
           }
           FileReader miArchivo = new FileReader(archivo);
           BufferedReader lector = new BufferedReader(miArchivo);
           String linea = lector.readLine();
           
           while (linea != null) {
               String[] segmento = linea.split(";");
               if (segmento.length >= 3 && !segmento[0].equals("")) {
                   objGestionMante m= new objGestionMante();
                   m.setId(Integer.parseInt(segmento[0].trim()));
                   m.setNombre(segmento[1].trim());
                   m.setEstado(segmento[2].trim());
                      
                   listaMante.add(m);
               }
               linea = lector.readLine();
           }
           lector.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public int generarIdManteAutomatico() {
       leerArchivoMante();
       if (listaMante.isEmpty()) {
           return 1; 
       }
       
       int mayorId = 0;
       for (int i = 0; i < listaMante.size(); i++) {
           objGestionMante m = listaMante.get(i);
           if (m.getId()> mayorId) {
               mayorId = m.getId();
           }
       }
       return mayorId + 1; 
   }

   public void agregarMante(objGestionMante mante) {
       leerArchivoMante();
       int idCalculado = generarIdManteAutomatico();
       mante.setId(idCalculado);
       
       listaMante.add(mante);
       escribeArchivoMante(); 
   }

   /*---------------------------------------------------------------------------
   Funciones getter, setter y demas funciones del listaAsiganciones
 -------------------------------------------------------------------------  */
  
   public ArrayList<objAsignacionMante> getListaAsignacionesMante(){
       return listaAsignacionesMante;
   }

   public void setListaAsignacionesMante(ArrayList<objAsignacionMante> listaAsignacionesMante) {
      this.listaAsignacionesMante = listaAsignacionesMante;
  }

   public boolean modificarAsigna(int id, String tipoPeriodo, double periodo, double km) {
       leerArchivoAsignacionMante(); 
       boolean encontrado = false;
       
       for (objAsignacionMante a : listaAsignacionesMante) {
           if (a.getId() == id) {
               a.setTipoPeriodo(tipoPeriodo);
               a.setKmUltimo(km);
               a.setNumPeriodicidad(periodo);
              
               encontrado = true;
               break;
           }
       }
       
       if (encontrado) {
           escribeArchivoAsignacionMante(); 
           return true;
       }
       return false;
   }

   public void escribeArchivoAsignacionMante() {
       try {
           FileWriter escritor = new FileWriter("AsignacionMante.txt");
           PrintWriter pw = new PrintWriter(escritor);
           
           java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
           
           for (int i = 0; i < listaAsignacionesMante.size(); i++) {
               objAsignacionMante a = listaAsignacionesMante.get(i);
               
               String fechaIngresoStr = (a.getIngreso() != null) ? sdf.format(a.getIngreso()) : "";
               String fechaVencimientoStr = (a.getVencimiento() != null) ? sdf.format(a.getVencimiento()) : "";
               
               String linea = a.getId() + ";" +
                              a.getPlacaVehiculo() + ";" +
                              a.getNombreMantenimiento() + ";" +
                              a.getTipoPeriodo() + ";" +
                              a.getNumPeriodicidad() + ";" +
                              a.getKmUltimo() + ";" +
                              fechaIngresoStr + ";" +
                              fechaVencimientoStr + ";";
               pw.println(linea);
           }
           pw.close();
           escritor.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al escribir el archivo de asignaciones de mantenimiento", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void leerArchivoAsignacionMante() {
       listaAsignacionesMante.clear();
       try {
           File archivo = new File("AsignacionMante.txt");
           if (!archivo.exists()) {
               return; 
           }
           FileReader miArchivo = new FileReader(archivo);
           BufferedReader lector = new BufferedReader(miArchivo);
           String linea = lector.readLine();
           
           java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
           
           while (linea != null) {
               String[] segmento = linea.split(";");
               if (segmento.length >= 6 && !segmento[0].equals("")) {
                   objAsignacionMante a = new objAsignacionMante();
                   a.setId(Integer.parseInt(segmento[0].trim()));
                   a.setPlacaVehiculo(segmento[1].trim());
                   a.setNombreMantenimiento(segmento[2].trim());
                   a.setTipoPeriodo(segmento[3].trim());
                   a.setNumPeriodicidad(Double.parseDouble(segmento[4].trim()));
                   a.setKmUltimo(Double.parseDouble(segmento[5].trim()));
                   
                   try {
                       if (segmento.length > 6 && !segmento[6].trim().isEmpty()) {
                           a.setIngreso(sdf.parse(segmento[6].trim()));
                       }
                       if (segmento.length > 7 && !segmento[7].trim().isEmpty()) {
                           a.setVencimiento(sdf.parse(segmento[7].trim()));
                       }
                   } catch (java.text.ParseException e) {
                       System.err.println("Error al parsear fechas: " + e.getMessage());
                   }
                   
                   listaAsignacionesMante.add(a);
               }
               linea = lector.readLine();
           }
           lector.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al leer el archivo de asignaciones", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public int generarIdAsignacionManteAutomatico() {
       leerArchivoAsignacionMante();
       if (listaAsignacionesMante.isEmpty()) {
           return 1;
       }
       
       int mayorId = 0;
       for (int i = 0; i < listaAsignacionesMante.size(); i++) {
           objAsignacionMante a = listaAsignacionesMante.get(i);
           if (a.getId() > mayorId) {
               mayorId = a.getId();
           }
       }
       return mayorId + 1;
   }

   public void agregarAsignacionMante(objAsignacionMante asignacion) {
       leerArchivoAsignacionMante();
       int idCalculado = generarIdAsignacionManteAutomatico();
       asignacion.setId(idCalculado);
       
       listaAsignacionesMante.add(asignacion);
       escribeArchivoAsignacionMante(); 
   }
   
   public boolean eliminarAsignacionManteArchivo(int idAsignacion) {
       leerArchivoAsignacionMante(); 
       ArrayList<objAsignacionMante> lista = getListaAsignacionesMante();
       boolean encontrado = false;
       
       for (int i = 0; i < lista.size(); i++) {
           if (lista.get(i).getId() == idAsignacion) {
               lista.remove(i);
               encontrado = true;
               break;
           }
       }
       
       if (encontrado) {
           setListaAsignacionesMante(lista);
           escribeArchivoAsignacionMante(); 
           return true;
       }
       
       return false;
   }
  
   /*---------------------------------------------------------------------------
   Funciones getter, setter y demas funciones del listaBoletaCombus
 -------------------------------------------------------------------------  */
  
   public ArrayList<objBoletaCombus> getListaBoletaCombus() {
       return listaBoletaCombus;
   }

   public void setListaBoletaCombus(ArrayList<objBoletaCombus> listaBoletaCombus) {
       this.listaBoletaCombus = listaBoletaCombus;
   }

   public void escribeArchivoBoletaCombus() {
       try {
           FileWriter escritor = new FileWriter("BoletaCombus.txt");
           PrintWriter pw = new PrintWriter(escritor);
           
           java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
           
           for (int i = 0; i < listaBoletaCombus.size(); i++) {
               objBoletaCombus b = listaBoletaCombus.get(i);
               
               String fechaDispensado = (b.getFecha()!= null) ? sdf.format(b.getFecha()) : "";
               
               String linea = b.getId() + ";" +
                              b.getPlacaVehiculo() + ";" +
                              b.getKmActual() + ";" +
                              b.getCombustible()+ ";" +
                              b.getCantidadCombustible()+ ";" +
                              b.getCantidadKWH()+ ";"+
                              fechaDispensado + ";";
               pw.println(linea);
           }
           pw.close();
           escritor.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al escribir el archivo de Boleta Combus", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void leerArchivoBoletaCombus() {
       listaBoletaCombus.clear();
       try {
           File archivo = new File("BoletaCombus.txt");
           if (!archivo.exists()) {
               return; 
           }
           FileReader miArchivo = new FileReader(archivo);
           BufferedReader lector = new BufferedReader(miArchivo);
           String linea = lector.readLine();
           
           java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
           
           while (linea != null) {
               String[] segmento = linea.split(";");
               if (segmento.length >= 5 && !segmento[0].equals("")) {
                   objBoletaCombus b = new objBoletaCombus();
                   b.setId(Integer.parseInt(segmento[0].trim()));
                   b.setPlacaVehiculo(segmento[1].trim());
                   b.setKmActual(Double.parseDouble(segmento[2].trim()));
                   b.setCombustible(segmento[3].trim());
                   b.setCantidadCombustible(Double.parseDouble(segmento[4].trim()));
                   b.setCantidadKWH(Double.parseDouble(segmento[5].trim()));
                   
                   try {
                       if (segmento.length > 6 && !segmento[6].trim().isEmpty()) {
                           b.setFecha(sdf.parse(segmento[6].trim()));
                       }
                   } catch (java.text.ParseException e) {
                       System.err.println("Error al parsear fechas: " + e.getMessage());
                   }
                   
                   listaBoletaCombus.add(b);
               }
               linea = lector.readLine();
           }
           lector.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al leer el archivo de asignaciones", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public int generarIdBoletaCombus() {
       leerArchivoBoletaCombus();
       if (listaBoletaCombus.isEmpty()) {
           return 1;
       }
       
       int mayorId = 0;
       for (int i = 0; i < listaBoletaCombus.size(); i++) {
           objBoletaCombus a = listaBoletaCombus.get(i);
           if (a.getId() > mayorId) {
               mayorId = a.getId();
           }
       }
       return mayorId + 1;
   }

   public void agregarBoletaCombus(objBoletaCombus boleta) {
       leerArchivoBoletaCombus();
       int idCalculado = generarIdBoletaCombus();
       boleta.setId(idCalculado);
       
       listaBoletaCombus.add(boleta);
       escribeArchivoBoletaCombus(); 
   }
   
   public boolean eliminarBoletaCombus(int idBoleta) {
       leerArchivoBoletaCombus(); 
       ArrayList<objBoletaCombus> lista = getListaBoletaCombus();
       boolean encontrado = false;
       
       for (int i = 0; i < lista.size(); i++) {
           if (lista.get(i).getId() == idBoleta) {
               lista.remove(i);
               encontrado = true;
               break;
           }
       }
       
       if (encontrado) {
           setListaBoletaCombus(lista);
           escribeArchivoBoletaCombus(); 
           return true;
       }
       
       return false;
   }
  
   /*---------------------------------------------------------------------------
   Funciones getter, setter y demas funciones del listaMecanicos
 -------------------------------------------------------------------------  */
  
   public ArrayList<objMecanicos> getListaMecanicos() {
       return listaMecanicos;
   }

   public void setListaMecanicos(ArrayList<objMecanicos> listaMecanicos) {
       this.listaMecanicos = listaMecanicos;
   }

   public void escribeArchivoMecanicos() {
       try {
           FileWriter escritor = new FileWriter("Mecanicos.txt");
           PrintWriter pw = new PrintWriter(escritor);
           
           for (int i = 0; i < listaMecanicos.size(); i++) {
               objMecanicos m = listaMecanicos.get(i);
               
               String linea = m.getId() + ";" +
                              m.getNombre()+ ";" +     
                              m.getCedula() + ";";
               pw.println(linea);
           }
           pw.close();
           escritor.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al escribir el archivo de Mecanicos", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void leerArchivoMecanicos() {
       listaMecanicos.clear();
       try {
           File archivo = new File("Mecanicos.txt");
           if (!archivo.exists()) {
               return; 
           }
           FileReader miArchivo = new FileReader(archivo);
           BufferedReader lector = new BufferedReader(miArchivo);
           String linea = lector.readLine();
           
           while (linea != null) {
               String[] segmento = linea.split(";");
               if (segmento.length >= 3 && !segmento[0].equals("")) {
                   objMecanicos m = new objMecanicos();
                   m.setId(Integer.parseInt(segmento[0].trim()));
                   m.setNombre(segmento[1].trim());
                   m.setCedula(segmento[2].trim());
                   
                   listaMecanicos.add(m);
               }
               linea = lector.readLine();
           }
           lector.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al leer el archivo de mecanicos", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public int generarIdMecanicos() {
       leerArchivoMecanicos();
       if (listaMecanicos.isEmpty()) {
           return 1;
       }
       
       int mayorId = 0;
       for (int i = 0; i < listaMecanicos.size(); i++) {
           objMecanicos m = listaMecanicos.get(i);
           if (m.getId() > mayorId) {
               mayorId = m.getId();
           }
       }
       return mayorId + 1;
   }

   public void agregarMecanico(objMecanicos mecanicos) {
       leerArchivoMecanicos();
       int idCalculado = generarIdMecanicos();
       mecanicos.setId(idCalculado);
       
       listaMecanicos.add(mecanicos);
       escribeArchivoMecanicos(); 
   }
   
   public boolean eliminarMecanico(int id) {
       leerArchivoMecanicos(); 
       ArrayList<objMecanicos> lista = getListaMecanicos();
       boolean encontrado = false;
       
       for (int i = 0; i < lista.size(); i++) {
           if (lista.get(i).getId() == id) {
               lista.remove(i);
               encontrado = true;
               break;
           }
       }
       
       if (encontrado) {
           setListaMecanicos(lista);
           escribeArchivoMecanicos(); 
           return true;
       }
       
       return false;
   }
  
   /*---------------------------------------------------------------------------
   Funciones getter, setter y demas funciones del listaBoletaTaller
 -------------------------------------------------------------------------  */
  
   public ArrayList<objBoletaTaller> getListaBoletaTaller() {
       return listaBoletaTaller;
   }

   public void setListaBoletaTaller(ArrayList<objBoletaTaller> listaBoletaTaller) {
       this.listaBoletaTaller = listaBoletaTaller;
   }

   public void escribeArchivoBoletaTaller() {
       try {
           FileWriter escritor = new FileWriter("BoletaTaller.txt");
           PrintWriter pw = new PrintWriter(escritor);
           
           java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
           
           for (int i = 0; i < listaBoletaTaller.size(); i++) {
               objBoletaTaller bt = listaBoletaTaller.get(i);
               
               String fechaStr = (bt.getFecha() != null) ? sdf.format(bt.getFecha()) : "";
               
               String linea = bt.getId() + ";" +
                              bt.getIdMantenimiento() + ";" +
                              bt.getIdMecanico() + ";" +
                              bt.getPlacaVehiculo() + ";" +
                              bt.getNombreMantenimeinto() + ";" +
                              bt.getMarcaVehiculo() + ";" +
                              bt.getModeloVehiculo() + ";" +
                              bt.getKilometrajeIngreso() + ";" +
                              fechaStr + ";" +
                              bt.getNombreMecanico() + ";";
               pw.println(linea);
           }
           pw.close();
           escritor.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al escribir el archivo de Boleta Taller", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }

   public void leerArchivoBoletaTaller() {
       listaBoletaTaller.clear();
       try {
           File archivo = new File("BoletaTaller.txt");
           if (!archivo.exists()) {
               return; 
           }
           FileReader miArchivo = new FileReader(archivo);
           BufferedReader lector = new BufferedReader(miArchivo);
           String linea = lector.readLine();
           
           java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
           
           while (linea != null) {
               String[] segmento = linea.split(";");
               if (segmento.length >= 8 && !segmento[0].equals("")) {
                   objBoletaTaller bt = new objBoletaTaller();
                   bt.setId(Integer.parseInt(segmento[0].trim()));
                   bt.setIdMantenimiento(Integer.parseInt(segmento[1].trim()));
                   bt.setIdMecanico(Integer.parseInt(segmento[2].trim()));
                   bt.setPlacaVehiculo(segmento[3].trim());
                   bt.setNombreMantenimeinto(segmento[4].trim());
                   bt.setMarcaVehiculo(segmento[5].trim());
                   bt.setModeloVehiculo(segmento[6].trim());
                   bt.setKilometrajeIngreso(Double.parseDouble(segmento[7].trim()));
                   
                   try {
                       if (segmento.length > 8 && !segmento[8].trim().isEmpty()) {
                           bt.setFecha(sdf.parse(segmento[8].trim()));
                       }
                   } catch (java.text.ParseException e) {
                       System.err.println("Error al parsear fechas en Boleta Taller: " + e.getMessage());
                   }
                   
                   if (segmento.length > 9) {
                       bt.setNombreMecanico(segmento[9].trim());
                   }
                   
                   listaBoletaTaller.add(bt);
               }
               linea = lector.readLine();
           }
           lector.close();
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al leer el archivo de Boleta Taller", "Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }

   public int generarIdBoletaTaller() {
       leerArchivoBoletaTaller();
       if (listaBoletaTaller.isEmpty()) {
           return 1;
       }
       
       int mayorId = 0;
       for (int i = 0; i < listaBoletaTaller.size(); i++) {
           objBoletaTaller bt = listaBoletaTaller.get(i);
           if (bt.getId() > mayorId) {
               mayorId = bt.getId();
           }
       }
       return mayorId + 1;
   }

   public void agregarBoletaTaller(objBoletaTaller boletaTaller) {
       leerArchivoBoletaTaller();
       int idCalculado = generarIdBoletaTaller();
       boletaTaller.setId(idCalculado);
       
       listaBoletaTaller.add(boletaTaller);
       escribeArchivoBoletaTaller(); 
   }

   public boolean eliminarBoletaTaller(int id) {
       leerArchivoTallerInternal(id); // método de apoyo o lógica directa abajo
       leerArchivoBoletaTaller();
       ArrayList<objBoletaTaller> lista = getListaBoletaTaller();
       boolean encontrado = false;
       
       for (int i = 0; i < lista.size(); i++) {
           if (lista.get(i).getId() == id) {
               lista.remove(i);
               encontrado = true;
               break;
           }
       }
       
       if (encontrado) {
           setListaBoletaTaller(lista);
           escribeArchivoBoletaTaller(); 
           return true;
       }
       
       return false;
   }
   
   private void leerArchivoTallerInternal(int id) {
       // Método auxiliar interno para asegurar lectura previa si se requiere
       leerArchivoBoletaTaller();
   }

   // Utilidades generales de archivos compartidas
   public void crearArchivo(String nombre){
       File miArchivo = new File(nombre + ".txt");
       try {
           if(miArchivo.createNewFile())
               System.out.println("Archivo: "+ nombre);
       } catch (IOException e) {
           JOptionPane.showMessageDialog(null, "Error al crear el archivo","Atencion", JOptionPane.ERROR_MESSAGE);
       }
   }
   
   public void limpiarArchivo(String nombre) {
        try {
            PrintWriter miEscritor = new PrintWriter(nombre + ".txt");
            miEscritor.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar el archivo", "Atencion", JOptionPane.ERROR_MESSAGE);
        }
    }
}