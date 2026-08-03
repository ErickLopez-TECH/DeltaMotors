/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Datos.Estructuras;
import Datos.ObjUsuarios;
import java.util.Scanner;
import Logica.LogUsuario;
/**
 *
 * @author triamus
 */
public class Usuario {
    public static Scanner leer = new Scanner(System.in);
    
    public static void gestionUsuarios(){
        
        int opcion;
        do {            
            System.out.println("----------------------------------------");
            System.out.println("|             GESTION USUARIOS         |");
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
            case 1: addUsuario();
                
                break;
            case 2: 
                break;
            case 3: ;
                
                break;
            case 4: consultarTodosLosUsuarios();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
    
    public static void addUsuario(){
        System.out.println("=======================================");
        System.out.println("|           REGISTRAR USUARIOS         |");
        System.out.println("=======================================");
        System.out.println("");
        
        leer.nextLine(); // Limpiar buffer

    System.out.print("Digite el Usuario: ");
    String usuario = leer.nextLine();

    // --- INTERCEPCIÓN TEMPRANA ---
    if (Logica.LogUsuario.usuarioRepetido(usuario) == 1) {
        System.out.println("[!] Error: El usuario ya está registrado. Intente con otro.");
        return;
    }

    // Si pasa la validación, continúa pidiendo el resto con normalidad
    System.out.print("Digite el ID: ");
    int id = leer.nextInt();

    int opcionRol = 0;
    do {
        System.out.println("Digite la opcion del rol: ");
        System.out.println("1. Operador");
        System.out.println("2. Administrador");
        opcionRol = leer.nextInt();

        if ((opcionRol != 1) && (opcionRol != 2)) {
            System.out.println("[!] Estimado Usuario digite una opcion correcta");
        }
    } while ((opcionRol != 1) && (opcionRol != 2));

    leer.nextLine(); // Limpiar buffer
    System.out.print("Digite la contrasena: ");
    String password = leer.nextLine();

    // Guardado final
    Logica.LogUsuario.registrarNuevoUsuario(id, usuario, opcionRol, password);
    System.out.println("[✔] ¡Usuario registrado con éxito!");

    }
    
    public static void consultarTodosLosUsuarios() {
    System.out.println("\n=== LISTA DE USUARIOS REGISTRADOS ===");
    
    // Verificamos si la lista está vacía
    if (Estructuras.listaUsuarios.isEmpty()) {
        System.out.println("[!] No hay usuarios registrados en el sistema.");
        return;
    }

    // Recorremos la lista mostrando los datos de cada usuario
    for (ObjUsuarios u : Estructuras.listaUsuarios) {
        if (u != null) {
            System.out.println("ID: " + u.getId());
            System.out.println("Usuario: " + u.getUsuario());
            System.out.println("Rol: " + u.getRol());
            System.out.println("Estado: " + u.getEstado());
            System.out.println("----------------------------------");
        }
    }
}
    
    public static void consultarUsuarios() {
    System.out.println("=================================");
    System.out.println("|       CONSULTAR USUARIOS      |");
    System.out.println("=================================");
    System.out.println("");
    
    leer.nextLine();
    System.out.print("Digite el usuario a buscar: ");
    String userBuscado = leer.nextLine();
    

    // Llamamos al método buscar dentro de tu objeto Almacen (Estructuras)
    ObjUsuarios usuarioEncontrado = Estructuras.buscarUsuarioPorId(userBuscado);

    if (usuarioEncontrado != null) {
        System.out.println("\n---[!] ¡Usuario Encontrado! ---");
        System.out.printf("%-10s%-20s%-10s%-10s\n ", "ID","Usuario","Rol","Estado");
        System.out.printf("%-10s%-20s%-10s%-10s\n ",usuarioEncontrado.getId()
        ,usuarioEncontrado.getUsuario()
        ,usuarioEncontrado.getRol()
        ,usuarioEncontrado.getEstado());
        
        
        
    } else {
        System.out.println("\n[!] No se encontró ningún usuario con el ID " + userBuscado);
    }
}
    
}
