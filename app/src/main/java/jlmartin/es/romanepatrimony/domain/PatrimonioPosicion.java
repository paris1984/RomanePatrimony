package jlmartin.es.romanepatrimony.domain;

public class PatrimonioPosicion {
    private String titulo;
    private Double latitud;
    private Double longitud;

    public PatrimonioPosicion(String titulo, Double latitud, Double longitud) {
        this.titulo = titulo;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getTitulo() {
        return titulo;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }
}
