package com.duoc.biblioteca.app;

import static com.duoc.biblioteca.app.App.menuAdminUsuario;
import static com.duoc.biblioteca.app.App.menuUsuario;
import static com.duoc.biblioteca.app.App.usuariosRegistrados;
import com.duoc.biblioteca.entidades.usuarios.Usuarios;
import com.duoc.biblioteca.utilitarios.Utilitarios;
import java.util.Scanner;

public class AppUsuarios {
    static void registrarUsuario(){
        Scanner teclado = new Scanner(System.in);
        String RUT;
        String NOMBRE;
        String DIRECCION;
        String COMUNA;
        boolean rutEsValido = true;
        
        do{
            Utilitarios.limpiaPantalla();
            if(rutEsValido == false) System.out.println("** EL RUT INGRESADO NO ES VALIDO **");
            System.out.println("INGRESE RUT DEL USUARIO:");
            System.out.println("* INGRESE RUT SIN PUNTOS NI GUION. EJ: 11222333K");
            RUT = teclado.nextLine();
            rutEsValido = Utilitarios.validaRut(RUT);
        }while(rutEsValido == false);
        
        Utilitarios.limpiaPantalla();
        System.out.println("INGRESE NOMBRE COMPLETO DEL USUARIO:");
        NOMBRE = teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        System.out.println("INGRESE DIRECCION DEL USUARIO:");
        DIRECCION = teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        System.out.println("INGRESE COMUNA DEL DOMICILIO DEL USUARIO:");
        COMUNA = teclado.nextLine();
        
        RUT = RUT.toUpperCase();
        NOMBRE = NOMBRE.toUpperCase();
        DIRECCION = DIRECCION.toUpperCase();
        COMUNA = COMUNA.toUpperCase();

        Usuarios auxUsuario = new Usuarios(RUT, NOMBRE, DIRECCION, COMUNA);
        usuariosRegistrados.put(RUT, auxUsuario);
        
        Utilitarios.limpiaPantalla();
        System.out.println("USUARIO REGISTRADO EXITOSAMENTE!");
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuAdminUsuario();
    }
    
    static void mostrarUsuarios(){
        Scanner teclado = new Scanner(System.in);
        
        if(usuariosRegistrados.isEmpty()){
            System.out.println("");
            System.out.println("NO HAY USUARIOS REGISTRADOS");
            System.out.println("");
        }else{
            for(Usuarios usuario : usuariosRegistrados.values()){
                System.out.println("RUT      : "+Utilitarios.formatoRut(usuario.getRut()));
                System.out.println("NOMBRE   : "+usuario.getNombreCompleto());
                System.out.println("DOMICILIO: "+usuario.getDireccion()+". "+usuario.getComuna());
                System.out.println("--------------------------");
            }
        }
        
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuAdminUsuario();
    }
    
    static void buscarUsuario(){
        Scanner teclado = new Scanner(System.in);
        String RUT;
        Usuarios usuario;
        boolean rutEsValido = true;
        if(usuariosRegistrados.isEmpty()){
            System.out.println("");
            System.out.println("NO HAY USUARIOS REGISTRADOS");
            System.out.println("");
        }else{
            do{
                Utilitarios.limpiaPantalla();
                if(rutEsValido == false) System.out.println("** EL RUT INGRESADO NO ES VALIDO **");
                System.out.println("INGRESE RUT DEL USUARIO:");
                System.out.println("* INGRESE RUT SIN PUNTOS NI GUION. EJ: 11222333K");
                RUT = teclado.nextLine();
                rutEsValido = Utilitarios.validaRut(RUT);
            }while(rutEsValido == false);
            RUT = RUT.toUpperCase();

            if(!usuariosRegistrados.containsKey(RUT)){
                System.out.println("-- USUARIO NO REGISTRADO --");
                System.out.println("");
            }else{
                usuario = usuariosRegistrados.get(RUT);

                System.out.println("-- USUARIO ENCONTRADO --");
                System.out.println("");
                System.out.println("RUT      : "+Utilitarios.formatoRut(usuario.getRut()));
                System.out.println("NOMBRE   : "+usuario.getNombreCompleto());
                System.out.println("DOMICILIO: "+usuario.getDireccion()+". "+usuario.getComuna());
                System.out.println("--------------------------");
            }
        }

        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuAdminUsuario();
    }
    
    static void eliminarUsuario(){
        Scanner teclado = new Scanner(System.in);
        String RUT;
        Usuarios usuario;
        boolean rutEsValido = true;
        if(usuariosRegistrados.isEmpty()){
            System.out.println("");
            System.out.println("NO HAY USUARIOS REGISTRADOS");
            System.out.println("");
        }else{
            do{
                Utilitarios.limpiaPantalla();
                if(rutEsValido == false) System.out.println("** EL RUT INGRESADO NO ES VALIDO **");
                System.out.println("INGRESE RUT DEL USUARIO:");
                System.out.println("* INGRESE RUT SIN PUNTOS NI GUION. EJ: 11222333K");
                RUT = teclado.nextLine();
                rutEsValido = Utilitarios.validaRut(RUT);
            }while(rutEsValido == false);
            RUT = RUT.toUpperCase();

            if(!usuariosRegistrados.containsKey(RUT)){
                System.out.println("-- USUARIO NO REGISTRADO --");
                System.out.println("");
            }else{
                usuariosRegistrados.remove(RUT);
                System.out.println("USUARIO ELIMINADO EXITOSAMENTE!");
            }
        }

        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        
        Utilitarios.limpiaPantalla();
        menuAdminUsuario();
    }
    
    static void loginUsuario(){
        Scanner teclado = new Scanner(System.in);
        String RUT;
        boolean rutEsValido = true;
        
        do{
            Utilitarios.limpiaPantalla();
            if(rutEsValido == false) System.out.println("** EL RUT INGRESADO NO ES VALIDO **");
            System.out.println("INGRESE RUT DEL USUARIO:");
            System.out.println("* INGRESE RUT SIN PUNTOS NI GUION. EJ: 11222333K");
            RUT = teclado.nextLine();
            rutEsValido = Utilitarios.validaRut(RUT);
        }while(rutEsValido == false);
        RUT = RUT.toUpperCase();
        
        if(usuariosRegistrados.containsKey(RUT)){
            Usuarios auxUsuario = usuariosRegistrados.get(RUT);
           
            menuUsuario(auxUsuario);
        }else{
            System.out.println("");
            System.out.println("EL USUARIO NO ESTA INSCRITO EN LA BIBLIOTECA");
            System.out.println("¿QUIERE INSCRIBIR EL USUARIO EN LA BIBLIOTECA? (S/N)");
            String respuesta = teclado.nextLine();
            if(respuesta.equalsIgnoreCase("S")){
                registrarUsuario();
            }else{
                System.out.println("");
                System.out.println("Presione ENTER para continuar...");
                teclado.nextLine();
                Utilitarios.limpiaPantalla();
                menuAdminUsuario();
            }
        }
    }
}
