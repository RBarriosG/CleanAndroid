package co.com.ceiba.domain.interactor;

import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.modelo.Vehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;
import co.com.ceiba.testdatabuilder.HistorialTestDataBuilder;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

public class CasoDeUsoIngresarVehiculoTest {

    private static final LocalDateTime FECHA_INGRESO_HABIL = LocalDateTime.of(2019, 8, 29, 8, 10);

    @Test
    public void ingresarVehiculoTest() {
        //arrange
        Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
        Historial historial = new HistorialTestDataBuilder().conVehiculo(vehiculo).conFechaIngreso(FECHA_INGRESO_HABIL).build();
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.ingresarVehiculo(notNull())).thenReturn(historial);
        CasoDeUsoIngresarVehiculo casoDeUsoIngresarVehiculo = new CasoDeUsoIngresarVehiculo(repositorioHistorial);
        //act
        Historial parqueo = casoDeUsoIngresarVehiculo.ejecutar(historial);
        //assert
        assertNotNull(parqueo);
    }

}
