/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

import Datos.ObjUsuarios;
import Logica.LogLoggin;
import java.util.Scanner;

public class Loggin {
    
    
    public static void loggin(){
        int exito = -1;
        int contador = 1;
        
        do {            
            
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("==============================");
        System.out.println("|     SISTEMA DELTA MOTORS   |");
        System.out.println("====================+=========");
        
        
        System.out.print("\nIngrese usuario: ");
        String userIngresado = scanner.nextLine();

        System.out.print("Ingrese contraseña: ");
        String passIngresada = scanner.nextLine();

       
        int resultadoExistencia = LogLoggin.existenciaUser(userIngresado);
        int intentosRestantes = 3 - contador;
        if (resultadoExistencia == 1) {
            // 2. Si existe, validamos si la contraseña es correcta
            exito = LogLoggin.validarLogin(userIngresado, passIngresada);
            String rol = LogLoggin.obtenerRol(userIngresado);
            String estado = LogLoggin.obtenerEstado(userIngresado);
            if ((exito == 1) && (estado.equals("Activo"))) {
                
                System.out.println("\n[✅]¡Acceso Concedido! Bienvenido al sistema.");
                if(rol.equals("Admin")){
                    main.menuAdmin();
                }else{
                    main.menuOperador();
                }
            }
            else {
                System.out.println("\n[!]Error: Contraseña incorrecta.");
                System.out.println("Intentos Restantes: "+ intentosRestantes);
                contador++;
            }
        } else {
            System.out.println("\n[!]Error: El usuario ingresado no existe.");
            System.out.println("Intentos Restantes: "+ intentosRestantes);
            contador++;
        }
        
        if(contador == 3){
            System.out.println("\n[!]Despues de este intento fallido el sistema se cerrara");
        }
        
    } while (exito == -1 && contador <4);
    }
}
