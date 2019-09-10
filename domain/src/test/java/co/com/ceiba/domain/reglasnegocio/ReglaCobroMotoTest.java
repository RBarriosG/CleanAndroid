package co.com.ceiba.domain.reglasnegocio;

import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.interactor.CasoDeUsoActualizarHistorial;
import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.modelo.Vehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;
import co.com.ceiba.testdatabuilder.HistorialTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class ReglaCobroMotoTest {

    private static final LocalDateTime FECHA_INGRESO_DIA_HABIL = LocalDateTime.of(2019, 8, 28, 7, 0);
    private static final LocalDateTime FECHA_SALIDA_PLUS_3_HORAS = FECHA_INGRESO_DIA_HABIL.plusHours(3);
    private static final LocalDateTime FECHA_SALIDA_PLUS_12_HORAS = FECHA_INGRESO_DIA_HABIL.plusHours(12);
    private static final LocalDateTime FECHA_SALIDA_MAS_DE_UN_DIA = FECHA_INGRESO_DIA_HABIL.plusDays(1).plusHours(5);
    private static final LocalDateTime FECHA_MENOS_DE_UNA_HORA = FECHA_INGRESO_DIA_HABIL.plusMinutes(20);

    private static final int CILINDRAJE_MENOR_A_500 = 400;
    private static final int CILINDRAJE_MAYOR_A_500 = 600;

    @Test
    public void calcularCobroMenosDe9HorasCilindrajeMenorA500Test() {
        //arrange
        Vehiculo moto = new VehiculoTestDataBuilder().conTipo(TipoVehiculo.MOTO).conCilindraje(CILINDRAJE_MENOR_A_500).build();
        Historial historial = new HistorialTestDataBuilder().conVehiculo(moto).conFechaIngreso(FECHA_INGRESO_DIA_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(moto.getPlaca())).thenReturn(Optional.of(historial));
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        //act
        double precio = casoDeUsoActualizarHistorial.ejecutar(moto.getPlaca(), FECHA_SALIDA_PLUS_3_HORAS);

        //assert
        double precioEsperado = 1500;
        assertEquals(precioEsperado, precio, 0.001);
    }

    @Test
    public void calcularCobroMasDe9YMenorAUnDiaYCilindrajeMayorA500Test() {
        //arrange
        Vehiculo moto = new VehiculoTestDataBuilder().conTipo(TipoVehiculo.MOTO).conCilindraje(CILINDRAJE_MAYOR_A_500).build();
        Historial historial = new HistorialTestDataBuilder().conVehiculo(moto).conFechaIngreso(FECHA_INGRESO_DIA_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(moto.getPlaca())).thenReturn(Optional.of(historial));
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        //act
        double precio = casoDeUsoActualizarHistorial.ejecutar(moto.getPlaca(), FECHA_SALIDA_PLUS_12_HORAS);

        //assert
        double precioEsperado = 6000;
        assertEquals(precioEsperado, precio, 0.001);
    }

    @Test
    public void calcularCobroMasDeUnDiaCilindrajeMayorA500Test() {
        //arrange
        Vehiculo moto = new VehiculoTestDataBuilder().conTipo(TipoVehiculo.MOTO).conCilindraje(CILINDRAJE_MAYOR_A_500).build();
        Historial historial = new HistorialTestDataBuilder().conVehiculo(moto).conFechaIngreso(FECHA_INGRESO_DIA_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(moto.getPlaca())).thenReturn(Optional.of(historial));
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        //act
        double precio = casoDeUsoActualizarHistorial.ejecutar(moto.getPlaca(), FECHA_SALIDA_MAS_DE_UN_DIA);

        //assert
        double precioEsperado = 8500;
        assertEquals(precioEsperado, precio, 0.001);
    }

    @Test
    public void calcularCobroMenosDeUnaHoraCilindrajeMenorA500Test() {
        //arrange
        Vehiculo moto = new VehiculoTestDataBuilder().conTipo(TipoVehiculo.MOTO).conCilindraje(CILINDRAJE_MENOR_A_500).build();
        Historial historial = new HistorialTestDataBuilder().conVehiculo(moto).conFechaIngreso(FECHA_INGRESO_DIA_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(moto.getPlaca())).thenReturn(Optional.of(historial));
        CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial = new CasoDeUsoActualizarHistorial(repositorioHistorial);

        //act
        double precio = casoDeUsoActualizarHistorial.ejecutar(moto.getPlaca(), FECHA_MENOS_DE_UNA_HORA);

        //assert
        double precioEsperado = 500;
        assertEquals(precioEsperado, precio, 0.001);
    }


}
