package jlmartin.es.romanepatrimony;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jlmartin.es.romanepatrimony.entidad.Municipio;
import jlmartin.es.romanepatrimony.sql.SqlCreateDbHelper;

public class MunicipiosSqlCreateDbHelperTest {

    @Test
    public void getMunicipiosTest() throws IOException {


        InputStream input = new FileInputStream("src\\test\\java\\jlmartin\\es\\romanepatrimony\\data_municipios.dat");
        List<Municipio> municipios = SqlCreateDbHelper.creationMunicipios(input);
        Assert.assertNotNull(municipios);
        Assert.assertFalse(municipios.isEmpty());
        for(Municipio municipio:municipios){
            Assert.assertNotNull(municipio.getLatitud());
            Assert.assertNotNull(municipio.getLongitud());
            Assert.assertNotNull(municipio.getNombre());
            Assert.assertNotNull(municipio.getProvincia_id());
        }
    }
}
