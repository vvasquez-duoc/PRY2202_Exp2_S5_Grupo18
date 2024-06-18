package com.duoc.biblioteca.app;

import static com.duoc.biblioteca.app.App.biblioteca;
import static com.duoc.biblioteca.app.App.librosPrestados;
import static com.duoc.biblioteca.app.App.menuUsuario;
import com.duoc.biblioteca.entidades.libros.Libros;
import com.duoc.biblioteca.entidades.usuarios.Usuarios;
import com.duoc.biblioteca.manejadores.LibroNoEncontrado;
import com.duoc.biblioteca.manejadores.LibroYaPrestado;
import com.duoc.biblioteca.utilitarios.Utilitarios;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppLibros {
    static void prestaLibro(Usuarios usuario){
        Scanner teclado = new Scanner(System.in);
        String RUT = usuario.getRut();
        int isbn = 0;
        
        try{
            System.out.println("INTRODUZCA EL ISBN DEL LIBRO:");
            isbn = teclado.nextInt();
            if(biblioteca.prestarLibro(isbn)){
                System.out.println("EL LIBRO HA SIDO PRESTADO");
                librosPrestados.put(isbn, RUT);
            }
        }catch(LibroNoEncontrado e){
            System.out.println("ERROR: "+e.getMessage());
        }catch(LibroYaPrestado e){
            System.out.println("ERROR: "+e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("ERROR: EL ISBN INGRESADO NO ES UN NUMERO");
        }finally{
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine(); teclado.nextLine();
            Utilitarios.limpiaPantalla();
            menuUsuario(usuario);
        }
    }
    
    static void devuelveLibro(Usuarios usuario){
        Scanner teclado = new Scanner(System.in);
        String RUT = usuario.getRut();
        int isbn = 0;
        
        try{
            System.out.println("INTRODUZCA EL ISBN DEL LIBRO:");
            isbn = teclado.nextInt();
            if(biblioteca.devolverLibro(isbn)){
                System.out.println("EL LIBRO HA SIDO DEVUELTO");
                librosPrestados.remove(isbn);
            }
        }catch(LibroNoEncontrado e){
            System.out.println("ERROR: "+e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("ERROR: EL ISBN INGRESADO NO ES UN NUMERO");
        }finally{
            System.out.println("");
            System.out.println("Presione ENTER para continuar...");
            teclado.nextLine(); teclado.nextLine();
            Utilitarios.limpiaPantalla();
            menuUsuario(usuario);
        }
    }
    
    static void mostrarLibrosYaPrestados(Usuarios usuario){
        Scanner teclado = new Scanner(System.in);
        boolean hayLibrosPrestados = false;
        String RUT = usuario.getRut();
        int isbn = 0;
        
        System.out.println("LIBROS PRESTADOS AL USUARIO: ");
        System.out.println("RUT   : "+Utilitarios.formatoRut(RUT));
        System.out.println("NOMBRE: "+usuario.getNombreCompleto());
        System.out.println("");
        for(int clave : librosPrestados.keySet()){
            isbn = clave;
            String rutUsuario = librosPrestados.get(clave);
            if(rutUsuario.equalsIgnoreCase(RUT)){
                hayLibrosPrestados = true;
                try{
                    Libros auxLibro = biblioteca.buscarLibro(isbn);
                    System.out.println("ISBN  : "+auxLibro.getIsbn());
                    System.out.println("TITULO: "+auxLibro.getTitulo());
                    System.out.println("AUTOR : "+auxLibro.getAutor());
                    System.out.println("--------------------------");
                }catch(LibroNoEncontrado e){
                    System.out.println("ERROR: "+e.getMessage());
                }
            }
        }
        if(!hayLibrosPrestados){
            System.out.println("NO HAY LIBROS PRESTADOS");
        }
        System.out.println("");
        System.out.println("Presione ENTER para continuar...");
        teclado.nextLine();
        Utilitarios.limpiaPantalla();
        menuUsuario(usuario);
    }
}
