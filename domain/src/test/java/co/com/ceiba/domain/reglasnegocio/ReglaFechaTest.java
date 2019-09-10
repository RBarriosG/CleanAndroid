package co.com.ceiba.domain.reglasnegocio;

import org.junit.Test;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ReglaFechaTest {

    private static final LocalDateTime DIA_HABIL_MARTES = LocalDateTime.of(2019, 8, 27, 10, 0);
    private static final LocalDateTime DIA_NO_HABIL_DOMINGO = LocalDateTime.of(2019, 8, 25, 7, 0);
    private static final LocalDateTime DIA_NO_HABIL_LUNES = LocalDateTime.of(2019, 8, 26, 8, 0);

    @Test
    public void esDiaHabilTest() {
        //arrange
        ReglaFecha reglaFecha = new ReglaFecha();
        //act
        boolean esDiaHabil = reglaFecha.esDiaHabil(DIA_HABIL_MARTES);
        //assert
        assertTrue(esDiaHabil);
    }

    @Test
    public void esDiaNoHabilDomingoTest() {
        //arrange
        ReglaFecha reglaFecha = new ReglaFecha();
        //act
        boolean esDiaHabil = reglaFecha.esDiaHabil(DIA_NO_HABIL_DOMINGO);
        //assert
        assertFalse(esDiaHabil);
    }

    @Test
    public void esDiaNoHabilLunesTest() {
        //arrange
        ReglaFecha reglaFecha = new ReglaFecha();
        //act
        boolean esDiaHabil = reglaFecha.esDiaHabil(DIA_NO_HABIL_LUNES);
        //assert
        assertFalse(esDiaHabil);
    }

}
