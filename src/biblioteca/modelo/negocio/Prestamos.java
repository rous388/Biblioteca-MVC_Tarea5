package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Libro;
import biblioteca.modelo.dominio.Prestamo;
import biblioteca.modelo.dominio.Usuario;

import java.time.LocalDate;

public class Prestamos {
   private Prestamo[] prestamos;
   private int numPrestamos;

   public Prestamos (int capacidad){
       if (capacidad <= 0) {
           throw new IllegalArgumentException("La capacidad debe ser mayor a 0.");
       }
       this.prestamos = new Prestamo[capacidad];
       this.numPrestamos = 0;
   }

   public void prestar (Libro libro, Usuario usuario, LocalDate fecha) {
       //comprobar que existan, o por lo menos que no sean null.
       if (libro == null || usuario == null || fecha==null) {
           throw new IllegalArgumentException("Los datos no pueden ser null");
       }
       //comprobar que el libro tiene unidades disponibles
       if (libro.getUnidadesDisponibles() <= 0) {
           throw new IllegalArgumentException("No hay unidades disponibles para prestar libros");
       }
       //comprobar espacio para nuevo préstamo
       if (numPrestamos >= prestamos.length) {
           throw new IllegalArgumentException("No hay espacio para más Préstamos");
           }

       //crear nuevo préstamo
       Prestamo p = new Prestamo (libro, usuario, fecha);
       //añadir nuevo préstamo
       prestamos[numPrestamos] = p;
       numPrestamos++;

       libro.tomarPrestado();
   }
   public boolean devolver (Libro libro, Usuario usuario, LocalDate fechaDevolucion) {
       if (libro == null || usuario == null || fechaDevolucion == null) {
           throw new IllegalArgumentException("Datos inválidos para devolver.");
       }
       //buscar el preéstamo
       for (int i = 0; i < numPrestamos; i++) {
           Prestamo p = prestamos[i];

           boolean mismolibro = p.getLibro().getIsbn().equals(libro.getIsbn());
           boolean mismousuario = p.getUsuario().getId().equals(usuario.getId());
           //Si coincide mismo libro y mismo usuario y aún no está devuelto, lo marco como devuelto.
           if (mismolibro && mismousuario && !p.isDevuelto()) {
               p.marcarDevuelto(fechaDevolucion);
               libro.devolverUnidad();
               return true;
           }
       }
       return false;
   }
   public Prestamo[] prestamosUsuario (Usuario usuario){
       if (usuario == null){
           return new Prestamo [0]; //devuelvo un array vacío.
       }
       int contador = 0; //para saber cómo de grande es el array.
       for(int i=0; i < numPrestamos; i++){
           if(prestamos[i].getUsuario().equals(usuario)){
               contador++;//este contador me dice cuántos préstamos tiene ese usuario
           }
       }
       //creo el array nuevo con el número de préstamos que tiene ese usuario.
       Prestamo[] resultado = new Prestamo[contador];
       int k = 0;
       for (int i = 0; i < numPrestamos; i++) {
           if (prestamos[i].getUsuario().equals(usuario)) {
               resultado[k] = new Prestamo (prestamos[i]); //copia
               k++;
           }
       }

       return resultado; //devuelve el array final.
   }

    public Prestamo[] historial() {
        Prestamo[] copia = new Prestamo[numPrestamos];
        for (int i = 0; i < numPrestamos; i++) {
            copia[i] = new Prestamo(prestamos[i]);
        }
        return copia;
    }
}
