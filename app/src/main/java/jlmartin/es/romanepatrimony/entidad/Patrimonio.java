package jlmartin.es.romanepatrimony.entidad;

public class Patrimonio {
    private int id;
    private String denominacion;
    private String otraDenominacion;
    private int tipo;
    private int municipio;
    private int padre;
    private String descripcion;
    private String bibliografia;
    private String datosHistoricos;

    public Patrimonio(int id, String denominacion, String otraDenominacion, int tipo, int municipio, int padre, String descripcion, String bibliografia, String datosHistoricos) {
        this.id = id;
        this.denominacion = denominacion;
        this.otraDenominacion = otraDenominacion;
        this.tipo = tipo;
        this.municipio = municipio;
        this.padre = padre;
        this.descripcion = descripcion;
        this.bibliografia = bibliografia;
        this.datosHistoricos = datosHistoricos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getOtraDenominacion() {
        return otraDenominacion;
    }

    public void setOtraDenominacion(String otraDenominacion) {
        this.otraDenominacion = otraDenominacion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }

    public int getPadre() {
        return padre;
    }

    public void setPadre(int padre) {
        this.padre = padre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBibliografia() {
        return bibliografia;
    }

    public void setBibliografia(String bibliografia) {
        this.bibliografia = bibliografia;
    }

    public String getDatosHistoricos() {
        return datosHistoricos;
    }

    public void setDatosHistoricos(String datosHistoricos) {
        this.datosHistoricos = datosHistoricos;
    }
}
