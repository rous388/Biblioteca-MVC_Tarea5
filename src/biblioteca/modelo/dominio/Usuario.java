package biblioteca.modelo.dominio;

import java.util.Objects;

public class Usuario {
    public static final String ID_PATTERN = "^\\d{8}[A-Za-z]$";
    public static final String EMAIL_BASIC = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private String id;
    private String nombre;
    private String email;
    private Direccion direccion;

    public Usuario (String id, String nombre, String email, Direccion direccion){
        setId (id);
        setNombre (nombre);
        setEmail (email);
        setDireccion (direccion);
    }
    //Constructor copia en usuario(copia profunda)
    public Usuario (Usuario usuario){
        if (usuario == null) {
            throw new IllegalArgumentException("No se puede crear un usuario null.");
        }
        this.id = usuario.id;
        this.nombre = usuario.nombre;
        this.email = usuario.email;
        this.direccion = new Direccion(usuario.direccion);//copia, para que no se pueda modificar la dirección desde fuera.
    }
    //Crear un usuario con campos vacíos salvo DNI, y así no romper validaciones.
    public Usuario(String id){
        setId(id);
        this.nombre = "";
        this.email = "";
        this.direccion = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || !id.matches(ID_PATTERN)) {
            throw new IllegalArgumentException("El id no es válido.");
        }

        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()){
            throw new IllegalArgumentException("El nombre no puede ser null o vacío");
        }
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches(EMAIL_BASIC)) {
            throw new IllegalArgumentException("El email no es válido.");
        }
            this.email = email;
    }
    //asi devolvemos en getter también la copia.
    public Direccion getDireccion() {
        return (direccion==null) ? null : new Direccion(direccion);
    }
    //se guarda la copia
    public void setDireccion(Direccion direccion) {
        if (direccion==null){
            throw new IllegalArgumentException("La dirección no puede ser null.");
        }
        this.direccion = new Direccion(direccion);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "----- USUARIO -----\n" +
                "ID: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Email: " + email + "\n" +
                "Dirección: " + direccion + "\n";
    }
}