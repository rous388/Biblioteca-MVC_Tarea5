//Controlador: es un intermediario (manda órdenes a Modelo)

package biblioteca.controlador;

import biblioteca.modelo.Modelo;
import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Prestamo;
import biblioteca.modelo.dominio.Usuario;
import biblioteca.vista.Vista;

import java.time.LocalDate;

public class Controlador {
    private static final int CAPACIDAD = 50;

    private final Modelo modelo;
    private final Vista vista;

    public Controlador (Modelo modelo, Vista vista){
        if (modelo==null){
            throw new IllegalArgumentException("EL modelo no puede ser null.");
        }
        if (vista == null) {
            throw new IllegalArgumentException("La vista no puede ser null.");
        }
        this.modelo = modelo;
        this.vista = vista;
        //En el modelo MVC, la vista necesita saber a qué controlador tiene que pedeirle las acciones.
        this.vista.setControlador(this);
    }
    public void comenzar(){
        modelo.comenzar(CAPACIDAD);//es importante que modelo esté antes que vista en comenzar.Porque es el que crea los datos.
        vista.comenzar();//y este arranca el menú.
    }
    public void terminar(){
        vista.terminar();
        modelo.terminar();
        System.out.println("Termina Controlador");
    }

    //Libros
    public boolean alta (Libro libro){
        return modelo.altaLibro(libro);
    }

    public boolean bajaLibro(Libro libro){
        return modelo.bajaLibro(libro);
    }

    public Libro buscarLibro(Libro libro){
        return modelo.buscarLibro(libro);
    }

    public Libro[] listadoLibros(){
        return modelo.listadoLibros();
    }

    //Usuarios
    public boolean altaUsuario(Usuario usuario){
        return modelo.altaUsuario(usuario);
    }
    public boolean bajaUsuario (Usuario usuario){
        return modelo.bajaUsuario(usuario);
    }
    public Usuario buscarUsuario (Usuario usuario){
        return modelo.buscarUsuario(usuario);
    }
    public Usuario[] listadoUsuarios(){
        return modelo.listadoUsuarios();
    }

    //Préstamos
    public boolean prestarLibro (Libro libro, Usuario usuario, LocalDate fecha){
        return modelo.prestarLibro(libro, usuario, fecha);
    }

    public boolean devolverLibro (Libro libro, Usuario usuario, LocalDate fechadevolucion){
        return modelo.devolverLibro(libro, usuario, fechadevolucion);
    }

    public Prestamo[] listadoPrestamos (){
        return modelo.listadoPrestamos();
    }

    public Prestamo[] listadoPrestamosUsuario(Usuario usuario){
        return modelo.listadoPrestamosUsuario(usuario);
    }
}
