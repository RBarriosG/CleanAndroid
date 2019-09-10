package co.com.ceiba.domain.reglasnegocio;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ReglaPlacaTest {

    private static final String PLACA_COMIENZA_CON_A = "ABD123";
    private static final String PLACA_NO_COMIENZA_CON_A = "BDA123";

    @Test
    public void placaComienzaPorATest() {
        //arrange
        ReglaPlaca reglaPlaca = new ReglaPlaca();
        //act
        boolean placaComienzaConA = reglaPlaca.empiezaPorA(PLACA_COMIENZA_CON_A);
        //assert
        assertTrue(placaComienzaConA);
    }

    @Test
    public void placaNoComienzaPorATest() {
        //arrange
        ReglaPlaca reglaPlaca = new ReglaPlaca();
        //act
        boolean placaComienzaConA = reglaPlaca.empiezaPorA(PLACA_NO_COMIENZA_CON_A);
        //assert
        assertFalse(placaComienzaConA);
    }

}
