/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.objGestionMante;
import Datos.Estructuras;
import java.util.ArrayList;
/**
 *
 * @author triamus
 */
public class logicaGestionMante {
    
    
    public boolean registrarMante(String nombre,String estado){
        
        objGestionMante nuevoMante = new objGestionMante(0, nombre, estado);
        
        Estructuras est = new Estructuras();
        est.agregarMante(nuevoMante);
        
        return true;
    }
    
    public boolean existenciaMante(String nombre){
        Estructuras est = new Estructuras();
        est.leerArchivoMante();
        
        for (int i = 0; i < est.getListaMantes().size(); i++) {
            objGestionMante mante = est.getListaMantes().get(i);
            
            if(mante.getNombre().equalsIgnoreCase(nombre)){
                return true;
            }
        }
        return false;
    }
    
    
    
    public boolean eliminarMante(int idMante) {
    
    Estructuras est = new Estructuras();
    return est.eliminarManteArchivo(idMante);
}
    
    // Método para que la interfaz pida la lista sin tocar "Estructuras" directamente
    public ArrayList<objGestionMante> obtenerListaMante() {
        Estructuras est = new Estructuras();
        est.leerArchivoMante();
        return est.getListaMantes();
    }
    
    public void actualizarYGuardarLista(ArrayList<objGestionMante> listaModificada) {
        Estructuras est = new Estructuras();
        
        //contructor set
        est.setListaMante(listaModificada); 
        
        // Y luego manda a escribir el archivo físico
        est.escribeArchivoMante();
    }
    
    public boolean modificarMante(int idBuscado, String nuevoNombre, String nuevoEstado) {
    Estructuras est = new Estructuras();
    
    // 1. Leemos el archivo para cargar la lista actual
    est.leerArchivoMante(); 
    ArrayList<objGestionMante> lista = est.getListaMantes();
    
    boolean modificado = false;
    
    // 2. Buscamos el elemento por su ID 
    for (int i = 0; i < lista.size(); i++) {
        objGestionMante item = lista.get(i);
        
        if (item.getId() == idBuscado) {
            item.setNombre(nuevoNombre);
            item.setEstado(nuevoEstado);
            modificado = true;
            break; 
        }
    }
    
    // 3. Si se modificó, guardamos la lista actualizada en el archivo
    if (modificado) {
        est.setListaMante(lista);
        est.escribeArchivoMante();
    }
    
    return modificado;
}
    
    
    public ArrayList<objGestionMante> filtrarMante(String filtro) {
    // 1. Obtenemos la lista completa (la lógica se encarga de leer el archivo)
    ArrayList<objGestionMante> listaTotal = obtenerListaMante();
    ArrayList<objGestionMante> listaFiltrada = new ArrayList<>();
    
    //lista vacia
    if (filtro == null) {
        filtro = "";
    }
    String filtroLower = filtro.toLowerCase().trim();
    
    // 2. Recorremos y filtramos en la capa lógica
    for (objGestionMante mante : listaTotal) {
        String id = String.valueOf(mante.getId());
        String nombre = mante.getNombre().toLowerCase();
        String estado = mante.getEstado().toLowerCase();
        
        if (id.contains(filtroLower) || nombre.contains(filtroLower) || estado.contains(filtroLower)) {
            listaFiltrada.add(mante);
        }
    }
    
    // 3. Devolvemos la lista ya filtrada a la interfaz
    return listaFiltrada;
}
    
    

}
