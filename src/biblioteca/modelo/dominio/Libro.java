package biblioteca.modelo.dominio;

import java.util.Arrays;
import java.util.Objects;
import java.lang.String;

public class Libro {

    public static final String ISBN_PATTERN = "\\d{13}"; //13 números sin guiones
    public static final int MAX_AUTORES = 3;//lo inicializo a 3 autores, como dice el ejercicio.

    private String isbn;
    private String titulo;
    private int anio;
    private Categoria categoria;
    private int unidadesDisponibles;

    private final Autor[] autores;
    private int numAutores;

    public Libro(String isbn, String titulo, int anio, Categoria categoria, int unidadesDisponibles) {
        this.autores = new Autor[MAX_AUTORES];
        this.numAutores = 0;

        setIsbn(isbn);
        setTitulo(titulo);
        setAnio(anio);
        setCategoria(categoria);
        setUnidadesDisponibles(unidadesDisponibles);
    }
//reacondicionamos el constructor copia, haciendo una copia profunda de autores.
    public Libro(Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("No se puede crear un libro null.");
        }
        this.isbn=libro.isbn;
        this.titulo=libro.titulo;
        this.anio=libro.anio;
        this.categoria=libro.categoria;
        this.unidadesDisponibles=libro.unidadesDisponibles;

        this.autores = new Autor[MAX_AUTORES];
        this.numAutores = libro.numAutores;

        for (int i=0;i<numAutores;i++){
            this.autores[i] = new Autor(libro.autores[i]);
        }
    }
    //se crea un libro con campos vacíos excepto el isbn y asi no romper las validaciones.
    public Libro (String isbn){
        setIsbn(isbn);
        this.titulo="";
        this.anio = 0;
        this.categoria=Categoria.OTROS;
        this.unidadesDisponibles=0;
        this.autores=new Autor[MAX_AUTORES];
        this.numAutores=0;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (isbn == null || !isbn.matches(ISBN_PATTERN)) {
            throw new IllegalArgumentException("ISBN no válido.");
        }
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("título no puede ser null ni estar vacío.");
        }
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        if (anio <= 0) {
            throw new IllegalArgumentException("El año ha de ser positivo");
        }
        this.anio = anio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría del libro no puede ser null");
        }
        this.categoria = categoria;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        if (unidadesDisponibles < 0) {
            throw new IllegalArgumentException("Las unidades disponibles deben ser mayores a 0");
        }
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public boolean addAutor(Autor autor) {
        if (autor == null) {
            throw new IllegalArgumentException("El autor no puede ser null.");
        }
        if (numAutores >= MAX_AUTORES) {
            return false;//el máximo ha sido alcanzado y no se pueden añadir más autores.
        }
        autores[numAutores++] = new Autor(autor);//guardamos la copia para este ejercicio 5
        return true;
    }
//para poder devolver en getter de autores una copia profunda.
    public Autor[] getAutores() {
        Autor[] copia = new Autor[numAutores];
        for (int i = 0; i < numAutores; i++) {
            copia[i] = new Autor(autores[i]);
        }
        return copia;
    }

    public String autoresComoCadena() {
        if (numAutores == 0) {
            return "(sin autores)";
        } //si no hay autores se devuelve sin autores.
        StringBuilder sb = new StringBuilder(); //Creamos una caja para ir construyendo poco a poco el texto.
        for (int i = 0; i < numAutores; i++) { //recorremos solo los autores que existen.
            sb.append(autores[i]); //añade el autro actual al texto.
            if (i < numAutores - 1) sb.append(", "); //se añade una coma entre autores si no es el último.
        }
        return sb.toString();
    }

    public void tomarPrestado() {
        if (unidadesDisponibles <= 0) {
            throw new IllegalArgumentException("No hay unidades disponibles");
        }
        unidadesDisponibles--;
    }

    public void devolverUnidad() {
        unidadesDisponibles++;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }

    @Override
    public String toString() {
        return "----- LIBRO -----\n" +
                "ISBN: " + isbn + "\n" +
                "Título: " + titulo + "\n" +
                "Año: " + anio + "\n" +
                "Categoría: " + categoria + "\n" +
                "Unidades disponibles: " + unidadesDisponibles + "\n" +
                "Autores: " + autoresComoCadena() + "\n";
    }

}

