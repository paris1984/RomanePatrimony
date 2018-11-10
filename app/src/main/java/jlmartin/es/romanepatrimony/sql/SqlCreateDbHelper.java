package jlmartin.es.romanepatrimony.sql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jlmartin.es.romanepatrimony.entidad.Municipio;
import jlmartin.es.romanepatrimony.entidad.Patrimonio;
import jlmartin.es.romanepatrimony.entidad.Tipo;

public class SqlCreateDbHelper {

    public static final String SEPARADOR_COLUMNAS = ",";
    public static final String SEPARADOR_CAMPOS = "'";

    public static List<Municipio> creationMunicipios(InputStream inputStream) {

        List<Municipio> municipios = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linea;
            while ((linea = br.readLine()) != null) {
                Integer provincia_id = Integer.parseInt(linea.substring(0, linea.indexOf(SEPARADOR_COLUMNAS)));
                linea = linea.substring(linea.indexOf(SEPARADOR_COLUMNAS) + 3);
                String nombre = linea.substring(0, linea.indexOf(SEPARADOR_CAMPOS));
                linea = linea.substring(linea.lastIndexOf(SEPARADOR_CAMPOS) + 3);
                String[] coordenadas = linea.split(", ");
                Double latitud = Double.parseDouble(coordenadas[0]);
                Double longitud = Double.parseDouble(coordenadas[1]);
                Municipio municipio = new Municipio(nombre, provincia_id, latitud, longitud);
                municipios.add(municipio);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return municipios;
    }

    public static List<Tipo> creationTipos(InputStream inputStream){
        List<Tipo> tipos = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linea;
            while ((linea = br.readLine()) != null) {
                tipos.add(new Tipo(linea));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return tipos;
    }

    public static List<Patrimonio> creationPatrimonios(InputStream inputStream) {
        List<Patrimonio> patrimonios = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split("\\|");
                patrimonios.add(new Patrimonio(campos[0],campos[1],campos[2],campos[3],campos[4],campos[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patrimonios;
    }
}
