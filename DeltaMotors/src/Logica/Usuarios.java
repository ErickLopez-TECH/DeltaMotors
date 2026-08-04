/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import Datos.Estructuras;
import Datos.ObjUsuarios;

import Datos.ObjUsuarios;
import java.util.ArrayList;
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
            System.out.println("|             GESTION USUARIOS o         |");
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
            case 3:  ;
                
                break;
            case 4: consultarUsuario();
                
                break;
            case 5: return;
            
        }
            
        } while (opcion !=5);
        
        
    }
    
  public static int usuarioRepetido(String nuevoUsuario){
         for (ObjUsuarios repeticion : Estructuras.listaUsuarios) {
             if (repeticion.getUsuario() != null && repeticion.getUsuario().equals(nuevoUsuario)) {
                 return 1;
             }
         }
         return -1;
    }
  
  
  public static String devolverRol(int rol){
      String rolStr ="";
      if(rol == 1){
          rolStr = "Operador";
      }
      if(rol == 2){
          rolStr= "Admin";
      }
        return rolStr; 
  }
  
  public static String devolverEstado(int estado){
      String estadoStr = "";
      if(estado == 1){
          estadoStr = "Activo";
      }
      if (estado == 2) {
          estadoStr = "Inactivo";
      }
      return estadoStr;
  }
  
  public int buscarUsuario(){
        System.out.println("===================================");
        System.out.println("|           BUSCAR USUARIO       |");
        System.out.println("===================================");
        System.out.println("");
        System.out.println("Digite la cedula del usuario ");
        String cedula = leer.nextLine();
        int indice = -1;
        
        //--Una nueva lista para trabajar localmente(cliente)
         ArrayList<ObjUsuarios> misUsuarios= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         misUsuarios = Almacen.consultarUsuarios();
         //--Recorrer con for
         for (int i = 0; i< misUsuarios.size();i++) {
             ObjUsuarios usuario = new ObjUsuarios();
             usuario = misUsuarios.get(i);
             if (usuario.getCedula().equals(cedula)) {
                 indice = i;
                 break;
             }
             
        }
         return indice;
    }
    
  public static void addUsuario(){
        System.out.println("=======================================");
        System.out.println("|           REGISTRAR USUARIOS add       |");
        System.out.println("=======================================");
        System.out.println("");
        ObjUsuarios nuevoUsuario = new ObjUsuarios();
        
        
        leer.nextLine();
        System.out.print("Digite el Usuario: ");
        String usuario = leer.nextLine();
        nuevoUsuario.setUsuario(usuario);
        
        //Validacion de repetidos
        if (Logica.LogUsuario.usuarioRepetido(usuario) == 1) {
            System.out.println("[!] Error: El usuario ya está registrado. Intente con otro.");
            return;
        }
        
        System.out.println("Digite la cedula: ");
        String cedula = leer.nextLine();
        nuevoUsuario.setCedula(cedula);

        // Si pasa la validación, continúa pidiendo el resto con normalidad
        System.out.print("Digite la contrasena: ");
        String password = leer.nextLine();
        nuevoUsuario.setContrasena(password);
        
        

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
        String rol = devolverRol(opcionRol);
        nuevoUsuario.setRol(rol);
        
        leer.nextLine(); // Limpiar buffer
    
        String estado = "Activo";//inactivo/activo
        nuevoUsuario.setEstado(estado);

    // Guardado final
    Almacen.agregarUsuarios(nuevoUsuario);
    System.out.println("[✔] ¡Usuario registrado con éxito!");

    }
    
  public static void consultarUsuario(){
      
        System.out.println("===================================");
        System.out.println("|           LISTA USUARIOS        |");
        System.out.println("===================================");
        System.out.println("");
        
        //--Una nueva lista para trabajar localmente(cliente)
         ArrayList<ObjUsuarios> misUsuarios= new ArrayList<>();
         //llenamo sla lista con una copia d ela original
         misUsuarios = Almacen.consultarUsuarios();
         //--Recorrer con for
         for (int i = 0; i< misUsuarios.size();i++) {
             ObjUsuarios usuario = new ObjUsuarios();
             usuario = misUsuarios.get(i);
             System.out.println("Id Cliente: " + usuario.getId() );
             System.out.println("Usuario: " + usuario.getUsuario());
             System.out.println("Cedula: "+ usuario.getCedula());
             System.out.println("Rol: " +  usuario.getRol());
             System.out.println("Estado: " + usuario.getEstado());
             System.out.println("");
             System.out.println("-------------------------------------");
             System.out.println("");
        }
    }
  
   public void modificarUsuario(){
        int indice = buscarUsuario();
        if (indice == -1) {
            System.out.println("No se encontro el usuario");
            
        }else{
            ArrayList<ObjUsuarios> misUsuarios= new ArrayList<>();
            misUsuarios = Almacen.consultarUsuarios();
            System.out.println("Usuario: " + misUsuarios.get(indice).getUsuario());//ve el indice y luego get cedula
            System.out.println("Cedula: " + misUsuarios.get(indice).getCedula());
            ObjUsuarios usuario = new ObjUsuarios();
            usuario = misUsuarios.get(indice);
            
            int opcion;
            do {                
                System.out.println("Deseas modificar el estado? ");
                System.out.println("1. Si");
                System.out.println("2. NO");
                opcion = leer.nextInt();
                
            } while ((opcion !=1) && (opcion != 2));
            
            int estado = 0;
            if(opcion == 1){
                System.out.println("Digite el estado: ");
                System.out.println("1. Activo");
                System.out.println("2. Inactivo");
                estado = leer.nextInt();
            }
            
            String estadoStr = devolverEstado(estado);
            usuario.setEstado(estadoStr);
            
            opcion = 0;
            do {                
                System.out.println("Deseas modificar el rol? ");
                System.out.println("1. Si");
                System.out.println("2. NO");
                opcion = leer.nextInt();
                
            } while ((opcion !=1) && (opcion != 2));
            
            estado = 0;
            if(opcion == 1){
                System.out.println("Digite el Rol: ");
                System.out.println("1. Operador");
                System.out.println("2. Admin");
                estado = leer.nextInt();
            }
            


           
             
            Almacen.modificarUsuario(indice, usuario);
        }
    }
    
  
  
  }
   


