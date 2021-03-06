package jlmartin.es.romanepatrimony.entity;

public class Municipio {
    private Long id;
    private String nombre;
    private Integer provincia_id;
    private Double latitud,longitud;

    public Municipio() {
    }

    public Municipio(String nombre, Integer provincia_id, Double latitud, Double longitud) {
        this.nombre = nombre;
        this.provincia_id = provincia_id;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getProvincia_id() {
        return provincia_id;
    }

    public Double getLatitud() {
        return latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public int getId() {
        return id.intValue();
    }

    public long setId(Long id) {
        this.id = id;
        return id;
    }

    @Override
    public String toString() {
        return "Municipio{" +
                "nombre='" + nombre + '\'' +
                ", provincia_id=" + provincia_id +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
