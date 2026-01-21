package biblioteca.modelo.negocio;

import biblioteca.modelo.dominio.Usuario;

public class Usuarios {

    private final Usuario[] usuarios;
    private int numUsuarios;

    public Usuarios (int capacidad){
        if(capacidad <= 0){
            throw new IllegalArgumentException("La capacidad dese ser mayor que 0.");
        }
        usuarios = new Usuario[capacidad];
        numUsuarios=0;
    }

    public void alta (Usuario usuario){
        if(usuario == null){
            throw new IllegalArgumentException("El usuario no puede ser null");
        }
        for(int i=0; i < numUsuarios; i++) {
            if (usuarios[i].getId().equals(usuario.getId())) {
                throw new IllegalArgumentException("Ya existe un usuario con ese ID");
            }
        }
        if (numUsuarios >= usuarios.length) {
            throw new IllegalArgumentException("No hay espacio para más usuarios");
            }

        usuarios[numUsuarios] = new Usuario(usuario);
        numUsuarios++;
    }

    public boolean baja (Usuario usuario){
        if(usuario == null){
            return false;
        }
        for (int i=0; i<numUsuarios; i++){
            if(usuarios[i].equals(usuario)){
                for (int j = i; j < numUsuarios - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                /*cuando se copian los usuarios de la posición de la derecha a la izquierda, al final queda uno duplicado,
                se borra.*/
                usuarios[numUsuarios-1]=null;
                numUsuarios --;
                return true;
            }
        }
        return false;
    }

    public Usuario buscar(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i].equals(usuario)) {
                return new Usuario(usuarios[i]);
            }
        }
        return null;
    }

    public Usuario buscarPorId(String id){
        if(id == null){
            return null;
        }
        for (int i=0; i<numUsuarios;i++){
            if(usuarios[i].getId().equals(id)){
                return usuarios[i];//referencia real, si no no puedo prestar un libro, porque me pone que no hay disponibiliada de unidades.
            }
        }
        return null;
    }

    public Usuario[] todos(){
        //se crea un nuevo array del tamaño justo
        Usuario[] copia = new Usuario[numUsuarios];
        //se copian los usuarios uno a uno.
        for (int i=0; i < numUsuarios; i++){
            copia[i] = new Usuario(usuarios [i]);
        }
        //se devuelve la copia con todos los usuarios, limpio, sin null, y sin acceder al original.
        return copia;
    }
}
