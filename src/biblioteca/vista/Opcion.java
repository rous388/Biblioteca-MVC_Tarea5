package biblioteca.vista;

public enum Opcion {
    SALIR(0,"Salir"),
    INSERTAR_USUARIO(1,"Insertar usuario"),
    BORRAR_USUARIO(2, "Borrar usuario"),
    MOSTRAR_USUARIOS(3, "Mostrar usuarios"),
    INSERTAR_LIBRO(4,"Insertar libro"),
    BORRAR_LIBRO(5, "Borrar libro"),
    MOSTRAR_LIBROS(6,"Mostrar libros"),
    NUEVO_PRESTAMO(7,"Nuevo préstamo"),
    DEVOLVER_PRESTAMO(8, "Devolver préstamo"),
    MOSTRAR_TODOS_PRESTAMOS(9, "Mostrar todos los préstamos"),
    MOSTRAR_PRESTAMOS_USUARIO(10, "Mostrar préstamos de un usuario");

    private final int codigo;
    private final String texto;

    Opcion (int codigo, String texto){
        this.codigo = codigo;
        this.texto = texto;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTexto() {
        return texto;
    }

    public static Opcion porCodigo(int codigo){
        for (Opcion o : values()) {
            if (o.codigo == codigo) return o;
        }
        return null;
    }
}
