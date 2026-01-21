/*Decide qué operación se ejecuta según la opción del menú. Es el "cerebro" del menú,
No guarda datos, no usa arrays, no hace lógica de negocio*/

package biblioteca.vista;

import biblioteca.controlador.Controlador;
import biblioteca.modelo.dominio.*;

public class Vista {

    private Controlador controlador;//ejecuta. Sin controlador, vista no puede hacer nada.
    private Consola consola;//muestra menú, pide datos, pausas

    //Constructor, en el momento que se crea la Vista, automáticamente se crea su Consola.
    public Vista() {
        consola = new Consola();
    }

    //Guardo la referencia del controlador.
    public void setControlador(Controlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("El controlador no puede ser null.");
        }
        this.controlador = controlador;
    }

    public void comenzar() {
        consola.comenzar();
        Opcion opcion;
        do {
            try {
                consola.mostrarMenu();
                opcion = consola.leerOpcion();
                ejecutarOpcion(opcion);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                consola.pausa();//se hace pausa para que el usuario vea el error.
                opcion = null;
            }
        } while (opcion != Opcion.SALIR);//el menú vuelve a salir
    }

    private void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_USUARIO -> insertarUsuario();
            case BORRAR_USUARIO -> borrarUsuario();
            case MOSTRAR_USUARIOS -> mostrarUsuarios();
            case INSERTAR_LIBRO -> insertarLibro();
            case BORRAR_LIBRO -> borrarLibro();
            case MOSTRAR_LIBROS -> mostrarLibros();
            case NUEVO_PRESTAMO -> nuevoPrestamo();
            case DEVOLVER_PRESTAMO -> devolverPrestamo();
            case MOSTRAR_TODOS_PRESTAMOS -> mostrarPrestamos();
            case MOSTRAR_PRESTAMOS_USUARIO -> mostrarPrestamosUsuario();
            case SALIR -> controlador.terminar();
        }
    }

    public void terminar() {
        consola.terminar();
        System.out.println("Termina Vista");
    }

    // USUARIOS
    private void insertarUsuario() {
        Usuario u = consola.nuevoUsuario(false);//Consola pide los datos y se construye un usuario completo(nuevoUsuario)
        controlador.altaUsuario(u); //Vista lo pasa al Contolador
        System.out.println("Usuario insertado."); //Se muestra el mensaje
        consola.pausa();//y pausa.
    }

    private void borrarUsuario() {
        Usuario u = consola.nuevoUsuario(true);//solo se va a pedir ID (usuario"clave")
        boolean ok = controlador.bajaUsuario(u);//controlador lo intenta borrar
        System.out.println(ok ? "Usuario eliminado." : "Usuario no encontrado.");
        consola.pausa();
    }

    private void mostrarUsuarios() {
        Usuario[] lista = controlador.listadoUsuarios();//pide la lista al controlador

        if (lista.length == 0) {
            System.out.println("No hay usuarios.");//si está vacía lo dice
        } else {
            for (Usuario u : lista) {
                System.out.println(u);//si hay, los imprime
            }
        }
        consola.pausa();//y pausa
    }

    // LIBROS

    private void insertarLibro() {
        Libro l = consola.nuevoLibro(false);
        controlador.alta(l);
        System.out.println("Libro insertado.");
        consola.pausa();
    }

    private void borrarLibro() {
        Libro l = consola.nuevoLibro(true);
        boolean ok = controlador.bajaLibro(l);
        System.out.println(ok ? "Libro eliminado." : "Libro no encontrado.");
        consola.pausa();
    }

    private void mostrarLibros() {
        Libro[] lista = controlador.listadoLibros();

        if (lista.length == 0) {
            System.out.println("No hay libros.");
        } else {
            for (Libro l : lista) {
                System.out.println(l);
            }
        }
        consola.pausa();
    }

    // PRÉSTAMOS

    private void nuevoPrestamo() {
        Libro l = consola.nuevoLibro(true);//Se pide por Consola ISBN
        Usuario u = consola.nuevoUsuario(true);//Se pide por Consola ID

        controlador.prestarLibro(l, u, consola.leerFecha());//Vista llama al Controlador, y este manda al Modelo.
        System.out.println("Préstamo realizado.");
        consola.pausa();
    }

    private void devolverPrestamo() {
        Libro l = consola.nuevoLibro(true);
        Usuario u = consola.nuevoUsuario(true);

        boolean ok = controlador.devolverLibro(l, u, consola.leerFecha());
        System.out.println(ok ? "Devolución realizada." : "No se encontró préstamo.");
        consola.pausa();
    }

    private void mostrarPrestamos() {
        Prestamo[] lista = controlador.listadoPrestamos();

        if (lista.length == 0) {
            System.out.println("No hay préstamos.");
        } else {
            for (Prestamo p : lista) {
                System.out.println(p);
            }
        }
        consola.pausa();
    }

    private void mostrarPrestamosUsuario() {
        Usuario u = consola.nuevoUsuario(true);
        Prestamo[] lista = controlador.listadoPrestamosUsuario(u);

        if (lista.length == 0) {
            System.out.println("No hay préstamos para ese usuario.");
        } else {
            for (Prestamo p : lista) {
                System.out.println(p);
            }
        }
        consola.pausa();
    }
}
