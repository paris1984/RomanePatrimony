package jlmartin.es.romanepatrimony;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jlmartin.es.romanepatrimony.entity.Patrimonio;
import jlmartin.es.romanepatrimony.sql.CreateEntity;

public class PatrimoniosSqlCreateDbHelperTest {

    @Test
    public void getMunicipiosTest() throws IOException {


        InputStream input = new FileInputStream("src\\test\\java\\jlmartin\\es\\romanepatrimony\\data_patrimonio.dat");
        List<Patrimonio> patrimonios = CreateEntity.creationPatrimonios(input);
        Assert.assertNotNull(patrimonios);
        Assert.assertFalse(patrimonios.isEmpty());
        for(Patrimonio patrimonio:patrimonios){
            Assert.assertNotNull(patrimonio.getDenominacion());
            Assert.assertNotNull(patrimonio.getOtraDenominacion());
            Assert.assertNotNull(patrimonio.getMunicipio());
            Assert.assertNotNull(patrimonio.getTipo());
            Assert.assertNotNull(patrimonio.getDatosHistoricos());
            Assert.assertNotNull(patrimonio.getDescripcion());
        }
    }
}
