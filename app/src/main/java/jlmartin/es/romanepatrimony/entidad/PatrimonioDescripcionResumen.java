package jlmartin.es.romanepatrimony.entidad;

public class PatrimonioDescripcionResumen {
    private String otraDenominacion;
    private String tipo;
    private String pertenece;
    private String descripcion;

    public PatrimonioDescripcionResumen(String otraDenominacion, String tipo, String pertenece, String descripcion) {
        this.otraDenominacion = otraDenominacion;
        this.tipo = tipo;
        this.pertenece = pertenece;
        this.descripcion = descripcion;
    }

    public String getOtraDenominacion() {
        return otraDenominacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPertenece() {
        return pertenece;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
