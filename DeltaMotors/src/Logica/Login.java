/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;
import static Logica.Usuarios.leer;
import static Logica.Usuarios.Almacen;

import Datos.Estructuras;
import Datos.ObjUsuarios;
import java.util.ArrayList;

public class Login {

    public static int existenciaUser(String user, String pass){
        ArrayList<ObjUsuarios> miLista = new ArrayList<>();
        miLista = Almacen.consultarUsuarios();
        
        for (int i = 0; i < miLista.size(); i++) {
            ObjUsuarios u = new ObjUsuarios();
            u = miLista.get(i);
            if((u.getUsuario().equals(user)) && u.getContrasena().equals(pass))
                return 1;
        }
        return -1;
    }
    
     public static String rolUser(String user, String pass){
        ArrayList<ObjUsuarios> miLista = new ArrayList<>();
        miLista = Almacen.consultarUsuarios();
        
        for (int i = 0; i < miLista.size(); i++) {
            ObjUsuarios u = new ObjUsuarios();
            u = miLista.get(i);
            if((u.getUsuario().equals(user)) && u.getContrasena().equals(pass))
                return u.getRol();
        }
        return "Sin rol";
    }
     
     public static String EstadoUser(String user, String pass){
        ArrayList<ObjUsuarios> miLista = new ArrayList<>();
        miLista = Almacen.consultarUsuarios();
        
        for (int i = 0; i < miLista.size(); i++) {
            ObjUsuarios u = new ObjUsuarios();
            u = miLista.get(i);
            if((u.getUsuario().equals(user)) && u.getContrasena().equals(pass))
                return u.getEstado();
        }
        return "Sin estado";
    }
    
    public static void loggin(){
        int intentosFallidos = 0;
        
        do {  
            
            System.out.println("===========================");
            System.out.println("|  △ LOGGIN DELTAMOTORS  |");
            System.out.println("===========================");
            System.out.println("");
            
            System.out.println("Ingrese Usuario");
            System.out.print("User: ");
            String usuario = leer.nextLine();
            
            System.out.println("Ingrese su contrasena");
            System.out.print("Password: ");
            String password = leer.nextLine();
            
            if (EstadoUser(usuario, password).equals("Inactivo")) {
                System.out.println("");
                System.out.println("[!] Su usuario esta inactivo");
                System.out.println("Por favor contactarse con el administrador");
                System.out.println("");
                
                
            }else{
                if(existenciaUser(usuario,password) == 1){
                //System.out.println("ta");
                if (rolUser(usuario, password).equals("Operador")) {
                    System.out.println("\n[✅] Bienvenido Operador\n");
                    Presentacion.main.menuOperador();
                }else{
                    System.out.println("\n[✅] Bienvenido administrador\n");
                    Presentacion.main.menuAdmin();
                }
            }
            }
            
            if(intentosFallidos == 1){
                System.err.println("[!] Ultimo intento para cerrar la aplicacion");
            }
            intentosFallidos++;
            int intentosRestantes = 3 - intentosFallidos;
            System.err.println("[!] Intentos Restantes: "+ intentosRestantes);
            
        } while (intentosFallidos <3);
    }
}

