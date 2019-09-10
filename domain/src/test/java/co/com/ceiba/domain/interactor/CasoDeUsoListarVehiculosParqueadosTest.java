package co.com.ceiba.domain.interactor;

import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.modelo.Vehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;
import co.com.ceiba.testdatabuilder.VehiculoTestDataBuilder;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class CasoDeUsoListarVehiculosParqueadosTest {

    private static final String PLACA_GENERAL = "ERF34";
    private static final int TAMANIO_REGISTRO = 12;

    @Test
    public void listarParqueadosTest() {
        //arrange
        RepositorioHistorial repositorioHistorial = Mockito.mock(RepositorioHistorial.class);
        when(repositorioHistorial.listarVehiculosParqueados()).thenReturn(crearParqueados());
        CasoDeUsoListarVehiculosParqueados casoDeUsoListarVehiculosParqueados = new CasoDeUsoListarVehiculosParqueados(repositorioHistorial);
        //act
        List<Historial> historiales = casoDeUsoListarVehiculosParqueados.ejecutar();
        //assert
        assertEquals(TAMANIO_REGISTRO, historiales.size());
    }

    private static List<Historial> crearParqueados() {
        List<Historial> registros = new ArrayList<>();
        for (int i = 0; i < CasoDeUsoListarVehiculosParqueadosTest.TAMANIO_REGISTRO; i++) {
            Vehiculo vehiculo = new VehiculoTestDataBuilder().conPlaca(PLACA_GENERAL + i).build();
            LocalDateTime fechaIngreso = LocalDateTime.of(2019, 8, 29, 8, 20).plusHours(i);
            registros.add(new Historial(vehiculo, fechaIngreso));
        }
        return registros;
    }

}
