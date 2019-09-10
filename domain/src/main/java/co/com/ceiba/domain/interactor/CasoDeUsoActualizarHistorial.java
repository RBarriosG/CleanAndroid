package co.com.ceiba.domain.interactor;

import java.time.LocalDateTime;

import co.com.ceiba.domain.excepcion.ExcepcionVehiculoNoSeEncuentraEnParqueadero;
import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.reglasnegocio.GenerarCobro;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

public class CasoDeUsoActualizarHistorial {

    private static final String VEHICULO_NO_ESTA_EN_PARQUEADERO = "Vehiculo no está en parqueadero";

    private RepositorioHistorial repositorioHistorial;
    private GenerarCobro generarCobro;

    public CasoDeUsoActualizarHistorial(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
        this.generarCobro = new GenerarCobro();
    }

    public double ejecutar(String placa, LocalDateTime fechaSalida) {
        Historial historial = repositorioHistorial.obtenerVehiculoParqueado(placa)
                .orElseThrow(() -> new ExcepcionVehiculoNoSeEncuentraEnParqueadero(VEHICULO_NO_ESTA_EN_PARQUEADERO));
        double cobro = calcularCobro(historial, fechaSalida);
        historial.setFechaSalida(fechaSalida);
        historial.setCobro(cobro);
        repositorioHistorial.actualizarHistorial(historial);
        return cobro;
    }

    private double calcularCobro(Historial historial, LocalDateTime fechaSalida) {
        return generarCobro.calcularCobro(historial.getVehiculo(), historial.getFechaIngreso(), fechaSalida);
    }

}
