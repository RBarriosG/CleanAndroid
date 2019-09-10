package co.com.ceiba.domain.reglasnegocio;

import org.junit.Test;
import org.mockito.Mockito;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class ReglaCupoCarroTest {

    @Test
    public void cupoDisponibleTest() {
        //arrange
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.contarVehiculosParqueadosPorTipo(TipoVehiculo.CARRO)).thenReturn(15L);
        ReglaCupoCarro reglaCupoCarro = new ReglaCupoCarro(repositorioHistorial);
        //act
        boolean cupoDisponible = reglaCupoCarro.validar();
        //assert
        assertTrue(cupoDisponible);
    }

    @Test
    public void cupoNoDisponibleTest() {
        //arrange
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.contarVehiculosParqueadosPorTipo(TipoVehiculo.CARRO)).thenReturn(20L);
        ReglaCupoCarro politicaCupoCarro = new ReglaCupoCarro(repositorioHistorial);
        //act
        boolean cupoDisponible = politicaCupoCarro.validar();
        //assert
        assertFalse(cupoDisponible);
    }

}
