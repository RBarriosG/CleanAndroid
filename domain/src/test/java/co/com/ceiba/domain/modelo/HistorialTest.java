package co.com.ceiba.domain.modelo;

import org.junit.Test;

import co.com.ceiba.testdatabuilder.HistorialTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

import static junit.framework.TestCase.assertEquals;

public class HistorialTest {

    private static final Vehiculo VEHICULO_CARRO_PRUEBA = new VehiculoTestDataBuilder().build();

    @Test
    public void crearHistorialTest() {
        //Arrange
        HistorialTestDataBuilder historialTestDataBuilder = new HistorialTestDataBuilder();
        historialTestDataBuilder.conVehiculo(VEHICULO_CARRO_PRUEBA);
        //act
        Historial historial = historialTestDataBuilder.build();
        //assert
        assertEquals(VEHICULO_CARRO_PRUEBA, historial.getVehiculo());
    }

}
