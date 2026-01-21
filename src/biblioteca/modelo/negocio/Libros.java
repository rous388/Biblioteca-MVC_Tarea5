package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Libro;

public class Libros {
    private final Libro[] libros;
    private int numLibros;

    public Libros (int capacidad){
        if (capacidad <= 0){
            throw new IllegalArgumentException("La capacidad debe ser mayor que 0.");
        }
        this.libros = new Libro [capacidad];
        this.numLibros = 0;
    }
    public void alta (Libro libro){
        if (libro == null){
            throw new IllegalArgumentException("El libro no puede ser null.");
        }
        for (int i = 0; i < numLibros; i++) {
            if (libros[i].getIsbn().equals(libro.getIsbn())) {
                throw new IllegalArgumentException("Ya existe un libro con ese ISBN.");
            }
        }

        //Comprobar que hay espacio
        if (numLibros >= libros.length){ //libros.length es la capacidad
            throw new IllegalArgumentException("No hay espacio para más libros");
        }
        //Añadir un libro
        libros[numLibros] = new Libro(libro);
        numLibros ++;
    }

    public boolean baja(Libro libro) {
        if (libro == null) {
            return false;
        }
        for (int i = 0; i < numLibros; i++) {
            if (libros[i].getIsbn().equals(libro.getIsbn())) {
                // si encuentra que es el mismo libro, se mueve a la izquierda desde esa posición.
                for (int j = i; j < numLibros - 1; j++) {
                    libros[j] = libros[j + 1];
                }

                libros[numLibros - 1] = null; // limpiar último
                numLibros--;

                return true;
            }
        }
        return false;
    }

    public Libro buscar(Libro libro) {
        if (libro == null) {
            return null;
        }
        for (int i = 0; i < numLibros; i++) {
            if (libros[i].equals(libro)) {
                return new Libro(libros[i]);//aqui prefiero que me devuelva la copia.
            }
        }
        return null;
    }

    public Libro buscarPorIsbn(String isbn){
        if (isbn == null){
            return null;
        }
        for (int i=0; i<numLibros; i++){
            if (libros[i].getIsbn().equals(isbn)){
                return libros[i];//tengo que referenciarlo a libro real, si no, no me funcionan las unidades Disponibles cuando lo presto.
            }
        }
        return null;
    }
    public Libro[] todos(){
        Libro[] copia = new Libro[numLibros];
        for (int i = 0; i < numLibros; i++) {
            copia[i] = new Libro(libros[i]);//aquí si que prefiero  que me devuelva la copia
        }
        return copia;
    }
}