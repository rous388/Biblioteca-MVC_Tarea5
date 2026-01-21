package biblioteca.modelo.dominio;

public class Autor {
    private String nombre;
    private String apellidos;
    private String nacionalidad;

    public Autor (String nombre, String apellidos, String nacionalidad){
        setNombre(nombre);
        setApellidos(apellidos);
        setNacionalidad(nacionalidad);
    }
    //copia profunda para en Modelo mostrar el listado.
    public Autor(Autor a){
        if (a == null){
            throw new IllegalArgumentException("Autor null");
        }
        this.nombre=a.nombre;
        this.apellidos=a.apellidos;
        this.nacionalidad=a.nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        if(nombre == null){
            throw new IllegalArgumentException("No puede ser null el nombre.");
        }

        if (nombre.isEmpty()){
        throw new IllegalArgumentException("No puede estar el nombre vacío.");
        }

        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos){
        if(apellidos == null){
            throw new IllegalArgumentException("No pueden ser null los apellidos");
        }

        if (apellidos.isEmpty()){
            throw new IllegalArgumentException("No pueden estar los apellidos vacíos.");
        }

        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad){
        if(nacionalidad == null){
            throw new IllegalArgumentException("No puede ser null la nacionalidad ");
        }

        if (nacionalidad.isEmpty()){
            throw new IllegalArgumentException("No puede estar la nacionalidad vacía.");
        }


        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " (" + nacionalidad + ")";
    }

}
