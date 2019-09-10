package co.com.ceiba.domain.reglasnegocio;

import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionIngresoPlacaVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionMaximoCupoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionVehiculoYaEstaEnParqueadero;
import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.modelo.Vehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;
import co.com.ceiba.testdatabuilder.HistorialTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class ReglaIngresarVehiculoTest {

    private static final LocalDateTime FECHA_INGRESO_HABIL = LocalDateTime.of(2019, 8, 29, 7, 25);
    private static final LocalDateTime FECHA_INGRESO_NO_HABIL = LocalDateTime.of(2019, 8, 26, 7, 25);

    private static final String CUPO_MAXIMO_DE_CARROS = "Cupo maximo de carros";
    private static final String CUPO_MAXIMO_DE_MOTOS = "Cupo maximo de motos";
    private static final String DIA_NO_HABIL_PARA_INGRESO = "Dia no habil para ingreso";
    private static final String VEHICULO_YA_ESTA_EN_EL_PARQUEADERO = "Vehiculo ya está en el parqueadero";

    private static final String PLACA_COMIENZA_CON_A = "ADF675";
    private static final int CILINDRAJE_200 = 200;

    @Test
    public void validarMaximoCupoCarrosTest() {
        //arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.contarVehiculosParqueadosPorTipo(TipoVehiculo.CARRO)).thenReturn(20L);
        ReglaIngresarVehiculo reglaIngresarVehiculo = new ReglaIngresarVehiculo(repositorioHistorial);
        //act
        try {
            reglaIngresarVehiculo.validar(vehiculo, FECHA_INGRESO_HABIL);
        } catch (ExcepcionMaximoCupoVehiculo e) {
            //assert
            assertEquals(CUPO_MAXIMO_DE_CARROS, e.getMessage());
        }

    }

    @Test
    public void validarMaximoCupoMotosTest() {
        //arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conTipo(TipoVehiculo.MOTO).conCilindraje(CILINDRAJE_200).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.contarVehiculosParqueadosPorTipo(TipoVehiculo.MOTO)).thenReturn(10L);
        ReglaIngresarVehiculo reglaIngresarVehiculo = new ReglaIngresarVehiculo(repositorioHistorial);
        //act
        try {
            reglaIngresarVehiculo.validar(vehiculo, FECHA_INGRESO_HABIL);
        } catch (ExcepcionMaximoCupoVehiculo e) {
            //assert
            assertEquals(CUPO_MAXIMO_DE_MOTOS, e.getMessage());
        }

    }

    @Test
    public void validarPlacaComienzaConATest() {
        //arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA_COMIENZA_CON_A).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        ReglaIngresarVehiculo reglaIngresarVehiculo = new ReglaIngresarVehiculo(repositorioHistorial);
        //act
        try {
            reglaIngresarVehiculo.validar(vehiculo, FECHA_INGRESO_NO_HABIL);
        } catch (ExcepcionIngresoPlacaVehiculo e) {
            //assert
            assertEquals(DIA_NO_HABIL_PARA_INGRESO, e.getMessage());
        }
    }

    @Test
    public void validarVehiculoEnParqueaderoTest() {
        //arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        Historial historial = new HistorialTestDataBuilder().conVehiculo(vehiculo).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.obtenerVehiculoParqueado(vehiculo.getPlaca())).thenReturn(Optional.of(historial));
        ReglaIngresarVehiculo reglaIngresarVehiculo = new ReglaIngresarVehiculo(repositorioHistorial);
        //act
        try {
            reglaIngresarVehiculo.validar(vehiculo, FECHA_INGRESO_HABIL);
        } catch (ExcepcionVehiculoYaEstaEnParqueadero e) {
            //assert
            assertEquals(VEHICULO_YA_ESTA_EN_EL_PARQUEADERO, e.getMessage());
        }
    }

}
