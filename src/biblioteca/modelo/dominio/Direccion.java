package biblioteca.modelo.dominio;

public class Direccion {
    public static final String CP_PATTERN = "\\d{5}";  //un dígito exactamente 5 veces.
    private String via;
    private String numero;
    private String cp;
    private String localidad;

    public Direccion (String via, String numero, String cp, String localidad){
        setVia(via);
        setNumero(numero);
        setCp(cp);
        setLocalidad(localidad);
    }
    //creamos un constructor copia para que podamos copiar bien cuando pedimos listarPrestamo en Modelo.
    public Direccion(Direccion d){
        if(d == null){
            throw new IllegalArgumentException("Dirección null.");
        }
        this.via=d.via;
        this.numero=d.numero;
        this.cp=d.cp;
        this.localidad=d.localidad;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        if (via == null || via.isEmpty()){
            throw new IllegalArgumentException("La vía no puede ser null o vacía.");
        }
        this.via = via;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero == null || numero.isEmpty()){
            throw new IllegalArgumentException("El número no puede ser null o vacío.");
        }
        this.numero = numero;
    }

    public String getCp() {return cp;
    }

    public void setCp(String cp) {
        if (cp == null || !cp.matches(CP_PATTERN)) {
            throw new IllegalArgumentException("Código postal no válido.");
        }
            this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        if (localidad == null || localidad.isEmpty()){
            throw new IllegalArgumentException("La localidad no puede ser null o vacía.");
        }
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return via + " " + numero + ", " + cp + " " + localidad;
    }

}
