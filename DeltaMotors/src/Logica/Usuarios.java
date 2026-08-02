/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Estructuras;

import Datos.ObjUsuarios;
import java.util.Scanner;

/**
 *
 * @author triamus
 */
public class Usuarios {
    
    static Scanner leer = new Scanner(System.in);
    
    public static Estructuras Almacen = new Estructuras();
    
    
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
            case 1: ingresarUsuarios();
                
                break;
            case 2: 
                break;
            case 3: Usuarios.ingresarUsuarios();
                
                break;
            case 4: consultarUsuarios();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
    
    
    public  static void ingresarUsuarios(){
        
        System.out.println("=======================================");
        System.out.println("|           REGISTRAR USUARIOS         |");
        System.out.println("=======================================");
        System.out.println("");
        
         int id;
         String usuario;
         String rol;
         String password;
         String estado;
         ObjUsuarios nuevoUsuario = new ObjUsuarios();
        
       
        
        leer.nextLine();
        
        System.out.println("Digite el Usuario: ");
        usuario= leer.nextLine();//atomizarcion en nombre,apellido y demas
        nuevoUsuario.setUsuario(usuario);
        
        if(Estructuras.usuarioRepetido(usuario) == 1){
            System.out.println("[!] Usuario repetido");
            return;
        }
        
        int opcionRol = 0;
        do {            
            System.out.println("Digite la opcion del rol: ");
            System.out.println("1. Operador");
            System.out.println("2. Administrador");
            opcionRol = leer.nextInt();
            
            if((opcionRol != 1) && (opcionRol !=2)){
                System.out.println("[!] Estimado Usuario digite una opsion correcta");
            }
        } while ((opcionRol != 1) && (opcionRol !=2));
        
        if(opcionRol == 1){
            rol = "operador";
            nuevoUsuario.setRol(rol);
        }
        
        if(opcionRol == 2){
            rol = "Admin";
            nuevoUsuario.setRol(rol);
        }
        
        leer.nextLine();
        System.out.println("Digite la contrasena: ");
        password = leer.nextLine();
        nuevoUsuario.setContrasena(password);
        
        
        estado = "Activo";
        nuevoUsuario.setEstado(estado);
        
        Almacen.agregarUsuarios(nuevoUsuario);
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
    
    /*
    public void modificarUsuario(){
        ObjUsuarios consultarUsuario = new ObjUsuarios();
        System.out.println("--------------------------------------");
        System.out.println("|           MODIFICAR USUARIOS        |");
        System.out.println("---------------------------------------");
        System.out.println("");
        
        
        
    }
    
    public void editarUsuario(){
        ObjUsuarios consultarUsuario = new ObjUsuarios();
        System.out.println("--------------------------------------");
        System.out.println("|            EDITAR USUARIOS         |");
        System.out.println("--------------------------------------");
        System.out.println("");
        
        
        
    }*/
}
