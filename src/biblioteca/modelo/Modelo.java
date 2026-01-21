//Modelo: Guarda y gestiona los datos(libros, usuarios y préstamos)
package biblioteca.modelo;

import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Prestamo;
import biblioteca.modelo.dominio.Usuario;
import biblioteca.modelo.negocio.Libros;
import biblioteca.modelo.negocio.Prestamos;
import biblioteca.modelo.negocio.Usuarios;

import java.time.LocalDate;

public class Modelo {

    private Libros libros;
    private Usuarios usuarios;
    private Prestamos prestamos;

    public void comenzar(int capacidad){
        if (capacidad <=0){
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0.");
        }
        libros = new Libros(capacidad);
        usuarios = new Usuarios(capacidad);
        prestamos = new Prestamos(capacidad);
    }

    public void terminar() {
        System.out.println("Termina Modelo");
    }

    // ALTAS

    public boolean altaLibro(Libro libro){
        if (libro == null){
            throw new IllegalArgumentException("El libro no puede ser null");
        }
        libros.alta(libro);
        return true;
    }

    public boolean altaUsuario(Usuario usuario){
        if (usuario == null){
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        usuarios.alta(usuario);
        return true;
    }

    public boolean prestarLibro(Libro libro, Usuario usuario, LocalDate fecha){
        if (libro == null || usuario == null || fecha == null){
            return false;
        }
        ////la única manera con que he conseguido que el Modelo recupere el libro real guardado, que tiene las unidades
        // que yo le ponga. Si no al darme una copia, las unidades siempre eran 0. Y no podía prestar los libros.
        Libro libroReal = libros.buscarPorIsbn(libro.getIsbn());
        Usuario usuarioReal = usuarios.buscarPorId(usuario.getId());

        if (libroReal == null || usuarioReal == null){
            return false;
        }

        prestamos.prestar(libroReal, usuarioReal, fecha);
        return true;
    }

    // BUSCAR

    public Libro buscarLibro(Libro libro){
        if (libro == null){
            return null;
        }
        Libro encontrado = libros.buscar(libro);
        if (encontrado == null) return null;
        return new Libro(encontrado);
    }

    public Usuario buscarUsuario(Usuario usuario){
        if (usuario == null){
            return null;
        }
        Usuario encontrado = usuarios.buscar(usuario);
        if (encontrado == null) return null;
        return new Usuario(encontrado);
    }

    // BAJAS

    public boolean bajaLibro(Libro libro){
        if (libro == null){
            return false;
        }
        return libros.baja(libro);
    }

    public boolean bajaUsuario(Usuario usuario){
        if (usuario == null){
            return false;
        }
        return usuarios.baja(usuario);
    }

    public boolean devolverLibro(Libro libro, Usuario usuario, LocalDate fechaDevolucion){
        if (libro == null || usuario == null || fechaDevolucion == null){
            return false;
        }//la única manera con que he conseguido que el Modelo recupere el libro real guardado, que tiene las unidades
        // que yo le ponga. Si no al darme una copia, las unidades siempre eran 0. Y no podía prestar los libros.
        Libro libroReal = libros.buscarPorIsbn(libro.getIsbn());
        Usuario usuarioReal = usuarios.buscarPorId(usuario.getId());

        if (libroReal == null ||usuarioReal == null){
            return false;
        }
        return prestamos.devolver(libro, usuario, fechaDevolucion);
    }

    // LISTADOS

    public Libro[] listadoLibros(){
        Libro[] lista = libros.todos();
        Libro[] copia = new Libro[lista.length];
        for (int i=0;i<lista.length; i++){
            copia[i]=new Libro(lista[i]);
        }
        return copia;
    }

    public Usuario[] listadoUsuarios(){
        Usuario[] lista = usuarios.todos();
        Usuario[] copia = new Usuario[lista.length];
        for(int i=0;i<lista.length;i++){
            copia[i]= new Usuario(lista[i]);
        }
        return copia;
    }

    public Prestamo[] listadoPrestamos(){
        Prestamo[] lista = prestamos.historial();
        Prestamo[] copia = new Prestamo[lista.length];
        for (int i=0; i<lista.length; i++){
            copia[i] = new Prestamo(lista[i]);
        }
        return copia;
    }

    public Prestamo[] listadoPrestamosUsuario(Usuario usuario){
        if (usuario == null){
            return new Prestamo[0];
        }
        Prestamo[] lista = prestamos.prestamosUsuario(usuario);
        Prestamo[] copia = new Prestamo[lista.length];
        for (int i=0; i<lista.length; i++){
            copia[i] = new Prestamo(lista[i]);
        }
        return copia;
    }
}
