/*Consola: es una parte de Vista que se encarga de mostrar el menú, pedir datos por teclado
 y devolver esos datos en forma de objetos (Libro, Usuario, etc..,). Aquí solo se habla con el usuario,
ni se guardan datos ni se llama a Modelo.*/

package biblioteca.vista;
import biblioteca.modelo.dominio.*;
import java.time.LocalDate;

public class Consola {
    public void comenzar(){
        System.out.println("Comienza Consola");
    }

    public void terminar(){
        System.out.println("Termina Consola");
    }
    //imprimir las opciones con un Enum, así está centralizado y no se repiten números.
    public void mostrarMenu() {
        System.out.println("-- Menú Biblioteca --");
        for (Opcion o: Opcion.values()) {
            System.out.println(o.getCodigo() + " - " + o.getTexto());//por ejemplo: código 1 y texto "insrtar usuario"
        }
    }
    //pide un número y lo convierte en una opción real del enum. Y si no lo encuentra lanza la excepción.
    public Opcion leerOpcion(){
        System.out.print("Opción: ");
        int cod = Entrada.entero();
        Opcion op = Opcion.porCodigo(cod);
        if (op == null){
            throw new IllegalArgumentException("Opción no válida.");
        }
        return op;
    }

    public Usuario nuevoUsuario(boolean paraBuscar) {
        if (paraBuscar) {
            //Se piden solo el ID, creando un usuario "clave". Después, Vista/Controlador lo usa para buscar.
            System.out.print("ID (8 números + letra): ");
            String id = Entrada.cadena();
            return new Usuario(id);
        }

        System.out.print("ID (8 números + letra): ");
        String id = Entrada.cadena();

        System.out.print("Nombre: ");
        String nombre = Entrada.cadena();

        System.out.print("Email: ");
        String email = Entrada.cadena();

        Direccion d = nuevaDireccion();
        return new Usuario(id, nombre, email, d);//después de pedir todos los datos, devuelve un usuario comprleto.
    }

    public Libro nuevoLibro(boolean buscar) {
        if (buscar) {
            System.out.print("ISBN (13 números): ");
            String isbn = Entrada.cadena();
            return new Libro(isbn); // libro "clave" para buscar
        }

        System.out.print("ISBN (13 números): ");
        String isbn = Entrada.cadena();

        System.out.print("Título: ");
        String titulo = Entrada.cadena();

        System.out.print("Año: ");
        int anio = Entrada.entero();

        Categoria categoria = leerCategoria();

        System.out.print("Unidades disponibles: ");
        int unidades = Entrada.entero();

        Libro libro = new Libro(isbn, titulo, anio, categoria, unidades);

        Autor[] autores = nuevoAutor(); //creo un array de autores
        for (Autor a : autores) {
            libro.addAutor(a);
        }
        return libro;//se crea un libro completo
    }
    //1 libro tiene entre 1 y MAX_AUTORES autores.
        public Autor[] nuevoAutor() {
            int n;
            do {
                System.out.print("Número de autores (1 a " + Libro.MAX_AUTORES + "): ");
                //se preguntan cuántos autores
                n = Entrada.entero();
            } while (n < 1 || n > Libro.MAX_AUTORES);
            //se crea un array del tamaño (1 y MAX_AUTORES)
            Autor[] autores = new Autor[n];
            //se piden los datos de cada autor y devuelve al final un array.
            for (int i = 0; i < n; i++) {
                System.out.println("Autor " + (i + 1));

                System.out.print("Nombre: ");
                String nombre = Entrada.cadena();

                System.out.print("Apellidos: ");
                String apellidos = Entrada.cadena();

                System.out.print("Nacionalidad: ");
                String nacionalidad = Entrada.cadena();

                autores[i] = new Autor(nombre, apellidos, nacionalidad);//array de autores
            }
            return autores;
        }

        public Direccion nuevaDireccion() {
            System.out.print("Vía: ");
            String via = Entrada.cadena();

            System.out.print("Número: ");
            String numero = Entrada.cadena();

            System.out.print("CP: ");
            String cp = Entrada.cadena();

            System.out.print("Localidad: ");
            String localidad = Entrada.cadena();

            return new Direccion(via, numero, cp, localidad);
        }

        public Categoria leerCategoria() {
            System.out.println("Categorías:");
            for (Categoria c : Categoria.values()) {
                System.out.println("- " + c);
            }
            System.out.print("Categoría: ");
            String texto = Entrada.cadena().toUpperCase();
            return Categoria.valueOf(texto);
        }

        /*devuelve la fecha del sistema, se usa en prestar libro para la fecha de inicio,
        y en devolver libro como fecha de devolución*/

        public LocalDate leerFecha() {
            return LocalDate.now();
        }

        public void pausa() {
            System.out.println("Pulsa Enter para continuar...");
            Entrada.cadena();
        }
    }
