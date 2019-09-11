package co.com.ceiba.domain.interactor;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

import co.com.ceiba.domain.excepcion.ExcepcionVehiculoNoSeEncuentraEnParqueadero;
import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;
import co.com.ceiba.testdatabuilder.HistorialTestDataBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

public class CasoDeUsoObtenerVehiculoParqueadoTest {

    private static final String VEHICULO_NO_ESTA_EN_PARQUEADERO = "Vehiculo no esta en parqueadero";

    @Test
    public void buscarVehiculoTest() {
        //arrange
        Historial historial = new HistorialTestDataBuilder().build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(notNull())).thenReturn(Optional.of(historial));
        CasoDeUsoObtenerVehiculoParqueado casoDeUsoObtenerVehiculoParqueado = new CasoDeUsoObtenerVehiculoParqueado(repositorioHistorial);
        //act
        Historial historialExperado = casoDeUsoObtenerVehiculoParqueado.ejecutar(historial.getVehiculo().getPlaca());
        //assert
        assertNotNull(historialExperado);
    }

    @Test
    public void vehiculoNoEncontradoTest() {
        //arrange
        Historial historial = new HistorialTestDataBuilder().build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(notNull())).thenReturn(Optional.empty());
        CasoDeUsoObtenerVehiculoParqueado casoDeUsoObtenerVehiculoParqueado = new CasoDeUsoObtenerVehiculoParqueado(repositorioHistorial);
        //act
        try {
            casoDeUsoObtenerVehiculoParqueado.ejecutar(historial.getVehiculo().getPlaca());
            fail();
        } catch (ExcepcionVehiculoNoSeEncuentraEnParqueadero e) {
            //assert
            assertEquals(VEHICULO_NO_ESTA_EN_PARQUEADERO, e.getMessage());
        }
    }

}
