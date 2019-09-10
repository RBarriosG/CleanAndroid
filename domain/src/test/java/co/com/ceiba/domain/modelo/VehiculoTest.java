package co.com.ceiba.domain.modelo;

import org.junit.Test;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionValorObligaorio;
import co.com.ceiba.domain.excepcion.ExepcionLongitudValor;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class VehiculoTest {

    private static final String LA_PLACA_ES_OBLIGATORIA = "La placa es obligatoria";
    private static final String LA_PLACA_DEBE_TENER_MINIMO_LETRAS = "La placa debe tener minimo letras";
    private static final String EL_CILINDRAJE_ES_OBLIGATORIO = "El cilindraje es obligatorio";


    @Test
    public void validarPlacaNullObligatoriaTest() {
        //arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
        vehiculoTestDataBuilder.conPlaca(null);
        //act
        try {
            vehiculoTestDataBuilder.build();
            fail();
        } catch (ExcepcionValorObligaorio e) {
            //assert
            assertEquals(LA_PLACA_ES_OBLIGATORIA, e.getMessage());
        }
    }

    @Test
    public void validarPlacaVaciaObligatoriaTest() {
        //arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
        vehiculoTestDataBuilder.conPlaca("");
        //act
        try {
            vehiculoTestDataBuilder.build();
            fail();
        } catch (ExepcionLongitudValor e) {
            //assert
            assertEquals(LA_PLACA_DEBE_TENER_MINIMO_LETRAS, e.getMessage());
        }
    }

    @Test
    public void validarCilindrajeTipoMotoTest() {
        //arrange
        VehiculoTestDataBuilder vehiculoTestDataBuilder = new VehiculoTestDataBuilder();
        vehiculoTestDataBuilder.conTipo(TipoVehiculo.MOTO)
                .conCilindraje(0);
        //act
        try {
            vehiculoTestDataBuilder.build();
            fail();
        } catch (ExcepcionValorObligaorio e) {
            //assert
            assertEquals(EL_CILINDRAJE_ES_OBLIGATORIO, e.getMessage());
        }
    }

}
