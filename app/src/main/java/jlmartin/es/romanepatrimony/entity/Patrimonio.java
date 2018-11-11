package jlmartin.es.romanepatrimony.entity;

public class Patrimonio {
    private int id;
    private String denominacion;
    private String otraDenominacion;
    private String tipo;
    private Long tipoNum;
    private String municipio;
    private int municipioNum;
    private String descripcion;
    private String datosHistoricos;

    public Patrimonio( String denominacion, String otraDenominacion, String tipo, String municipio, String descripcion, String datosHistoricos) {
        this.denominacion = denominacion;
        this.otraDenominacion = otraDenominacion;
        this.tipo = tipo;
        this.municipio = municipio;
        this.descripcion = descripcion;
        this.datosHistoricos = datosHistoricos;
    }

    public void setTipoNum(Long tipoNum) {
        this.tipoNum = tipoNum;
    }

    public void setMunicipioNum(int municipioNum) {
        this.municipioNum = municipioNum;
    }

    public int getId() {
        return id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getOtraDenominacion() {
        return otraDenominacion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDatosHistoricos() {
        return datosHistoricos;
    }
}
