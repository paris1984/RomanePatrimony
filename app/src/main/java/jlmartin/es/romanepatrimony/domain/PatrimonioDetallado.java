package jlmartin.es.romanepatrimony.domain;

public class PatrimonioDetallado {
    private String otraDenominacion;
    private String tipo;
    private String descripcion;
    private String datosHistoricos;

    public PatrimonioDetallado(String otraDenominacion, String tipo, String descripcion, String datosHistoricos) {
        this.otraDenominacion = otraDenominacion;
        this.tipo = tipo;

        this.descripcion = descripcion;
        this.datosHistoricos = datosHistoricos;
    }

    public String getOtraDenominacion() {
        return otraDenominacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDatosHistoricos() {
        return datosHistoricos;
    }
}
