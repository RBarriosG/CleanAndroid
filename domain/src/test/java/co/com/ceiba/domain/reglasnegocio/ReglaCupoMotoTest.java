package co.com.ceiba.domain.reglasnegocio;

import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class ReglaCupoMotoTest {

    @Test
    public void cupoDisponibleTest() {
        //arrange
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.contarVehiculosParqueadosPorTipo(TipoVehiculo.MOTO)).thenReturn(5L);
        ReglaCupoMoto reglaCupoMoto = new ReglaCupoMoto(repositorioHistorial);
        //act
        boolean cupoDisponible = reglaCupoMoto.validar();
        //assert
        assertTrue(cupoDisponible);
    }

    @Test
    public void cupoNoDisponibleTest() {
        //arrange
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.contarVehiculosParqueadosPorTipo(TipoVehiculo.MOTO)).thenReturn(10L);
        ReglaCupoMoto reglaCupoMoto = new ReglaCupoMoto(repositorioHistorial);
        //act
        boolean cupoDisponible = reglaCupoMoto.validar();
        //assert
        assertFalse(cupoDisponible);
    }

}
