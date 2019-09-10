package co.com.ceiba.domain.reglasnegocio;

import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import co.com.ceiba.domain.excepcion.ExcepcionVehiculoNoSeEncuentraEnParqueadero;
import co.com.ceiba.domain.interactor.CasoDeUsoActualizarHistorial;
import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.modelo.Vehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;
import co.com.ceiba.testdatabuilder.HistorialTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.when;

public class ReglaCobroCarroTest {

    private static final LocalDateTime FECHA_INGRESO_DIA_HABIL = LocalDateTime.of(2019, 8, 28, 7, 0);
    private static final LocalDateTime FECHA_SALIDA_PLUS_3_HORAS = FECHA_INGRESO_DIA_HABIL.plusHours(3);
    private static final LocalDateTime FECHA_SALIDA_PLUS_12_HORAS = FECHA_INGRESO_DIA_HABIL.plusHours(12);
    private static final LocalDateTime FECHA_SALIDA_MAS_DE_UN_DIA = FECHA_INGRESO_DIA_HABIL.plusDays(1).plusHours(5);
    private static final LocalDateTime FECHA_MENOS_DE_UNA_HORA = FECHA_INGRESO_DIA_HABIL.plusMinutes(20);

    private static final String VEHICULO_NO_ESTA_EN_PARQUEADERO = "Vehiculo no está en parqueadero";

    @Test
    public void calcularCobroMenosDe9HorasTest() {
        //arrange
        Vehiculo carro = new VehiculoTestDataBuilder().build();
        Historial historial = new HistorialTestDataBuilder().conFechaIngreso(FECHA_INGRESO_DIA_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(carro.getPlaca())).thenReturn(Optional.of(historial));
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        //act
        double precio = casoDeUsoActualizarHistorial.ejecutar(carro.getPlaca(), FECHA_SALIDA_PLUS_3_HORAS);

        //assert
        double precioEsperado = 3000;
        assertEquals(precioEsperado, precio, 0.001);
    }

    @Test
    public void calcularCobroMasDe9YMenorAUnDiaHorasTest() {
        //arrange
        Vehiculo carro = new VehiculoTestDataBuilder().build();
        Historial historial = new HistorialTestDataBuilder().conFechaIngreso(FECHA_INGRESO_DIA_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(carro.getPlaca())).thenReturn(Optional.of(historial));
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        //act
        double precio = casoDeUsoActualizarHistorial.ejecutar(carro.getPlaca(), FECHA_SALIDA_PLUS_12_HORAS);

        //assert
        double precioEsperado = 8000;
        assertEquals(precioEsperado, precio, 0.001);
    }

    @Test
    public void calcularCobroMasDeUnDiaTest() {
        //arrange
        Vehiculo carro = new VehiculoTestDataBuilder().build();
        Historial historial = new HistorialTestDataBuilder().conFechaIngreso(FECHA_INGRESO_DIA_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(carro.getPlaca())).thenReturn(Optional.of(historial));
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        //act
        double precio = casoDeUsoActualizarHistorial.ejecutar(carro.getPlaca(), FECHA_SALIDA_MAS_DE_UN_DIA);

        //assert
        double precioEsperado = 13000;
        assertEquals(precioEsperado, precio, 0.001);
    }

    @Test
    public void calcularCobroMenosDeUnaHoraTest() {
        //arrange
        Vehiculo carro = new VehiculoTestDataBuilder().build();
        Historial historial = new HistorialTestDataBuilder().conFechaIngreso(FECHA_INGRESO_DIA_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(carro.getPlaca())).thenReturn(Optional.of(historial));
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        //act
        double precio = casoDeUsoActualizarHistorial.ejecutar(carro.getPlaca(), FECHA_MENOS_DE_UNA_HORA);

        //assert
        double precioEsperado = 1000;
        assertEquals(precioEsperado, precio, 0.001);
    }

    @Test
    public void vehiculoNoEncontradoTest() {
        //arrange
        Vehiculo carro = new VehiculoTestDataBuilder().build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        try {
            casoDeUsoActualizarHistorial.ejecutar(carro.getPlaca(), FECHA_SALIDA_PLUS_3_HORAS);
            fail();
        } catch (ExcepcionVehiculoNoSeEncuentraEnParqueadero e) {
            //assert
            assertEquals(VEHICULO_NO_ESTA_EN_PARQUEADERO, e.getMessage());
        }


    }

}
