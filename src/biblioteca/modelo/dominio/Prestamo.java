package biblioteca.modelo.dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo {
    private Libro libro;
    private Usuario usuario;
    private LocalDate finicio;
    private LocalDate flimite;
    private boolean devuelto;
    private LocalDate fDevolucion;

    public Prestamo (Libro libro, Usuario usuario, LocalDate finicio){
        if (libro == null) {
            throw new IllegalArgumentException("El libro no puede ser null");
        }
        if (usuario == null){
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        if (finicio == null){
            throw new IllegalArgumentException("La fecha de inicio no puede ser null");
        }
        this.libro = libro;
        this.usuario = usuario;
        this.finicio = finicio;
        this.flimite = finicio.plusDays(15);
        this.devuelto = false;
        this.fDevolucion = null;
    }

    //se añade un constructor copia para poder listar los prestamos como referencias y no los reales en Modelo.
    public Prestamo (Prestamo prestamo){
        if (prestamo == null){
            throw new IllegalArgumentException("Préstamo null.");
        }
        this.libro = new Libro(prestamo.libro);
        this.usuario = new Usuario(prestamo.usuario);
        this.finicio = prestamo.finicio;
        this.flimite = prestamo.flimite;
        this.devuelto = prestamo.devuelto;
        this.fDevolucion = prestamo.fDevolucion;
    }

    public Libro getLibro() {
        return new Libro(libro);
    }//getter que devuelven la copia para proteger el real
    public Usuario getUsuario() {
        return new Usuario(usuario);
    }//getter que devuelven la copia para proteger el real
    public LocalDate getFinicio() {
        return finicio;
    }
    public LocalDate getFlimite() {
        return flimite;
    }
    public boolean isDevuelto() {
        return devuelto;
    }
    public LocalDate getFDevolucion() {return fDevolucion;
    }

    /* diasRetraso:
     - si devuelto -> comparar fDevolucion con flimite
     - si no devuelto -> comparar hoy con flimite */

    public int diasRetraso(){
        LocalDate fechaComparar = (devuelto && fDevolucion != null) ? fDevolucion : LocalDate.now();
        if (!fechaComparar.isAfter(flimite)) {
            return 0;
        }
        return (int) ChronoUnit.DAYS.between(flimite, fechaComparar);
    }
    public boolean estaVencido(){
        return !devuelto && LocalDate.now().isAfter(flimite);
    }

    public void marcarDevuelto (LocalDate fecha) {
        if (fecha == null){
            throw new IllegalArgumentException("La fecha de devolución no puede ser null.");
        }
        if (devuelto) {
            return;
        }
        this.devuelto = true;
        this.fDevolucion = fecha;
//no devuelvo aqui la unidad para evitar duplicados, lo hago en Prestamos.devolver
    }

    @Override
    public String toString() {
        return "----- PRÉSTAMO -----\n" +
                "Libro: " + libro.getTitulo() + " (ISBN: " + libro.getIsbn() + ")\n" +
                "Usuario: " + usuario.getNombre() + " (ID: " + usuario.getId() + ")\n" +
                "Fecha inicio: " + finicio + "\n" +
                "Fecha límite: " + flimite + "\n" +
                "Devuelto: " + (devuelto ? "Sí" : "No") + "\n" +
                (devuelto ? "Fecha devolución: " + fDevolucion + "\n" : "") +
                "Días de retraso: " + diasRetraso() + "\n";
    }

}

