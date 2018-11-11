package jlmartin.es.romanepatrimony.domain;

import java.io.InputStream;

public class PatrimonioResumen {

    private String titulo;
    private InputStream imagen;
    private PatrimonioDetallado descripcion;

    public PatrimonioResumen(String titulo, InputStream  imagen) {
        this.titulo = titulo;
        this.imagen = imagen;
    }

    public PatrimonioDetallado getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(PatrimonioDetallado descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public InputStream  getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

}
