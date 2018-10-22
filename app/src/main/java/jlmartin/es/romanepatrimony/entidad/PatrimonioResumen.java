package jlmartin.es.romanepatrimony.entidad;

public class PatrimonioResumen {

    private int id;
    private String titulo;
    private int imagen;
    private PatrimonioDescripcionResumen descripcion;

    public PatrimonioResumen(int id,String titulo, int imagen) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.id = id;
    }

    public PatrimonioDescripcionResumen getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(PatrimonioDescripcionResumen descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
