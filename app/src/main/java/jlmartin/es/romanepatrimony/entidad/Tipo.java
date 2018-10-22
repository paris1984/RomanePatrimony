package jlmartin.es.romanepatrimony.entidad;

public class Tipo {
    private long id;
    private String descripcion;

    public Tipo() {
    }

    public Tipo(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
