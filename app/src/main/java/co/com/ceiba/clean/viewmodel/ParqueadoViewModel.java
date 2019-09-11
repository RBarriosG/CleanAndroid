package co.com.ceiba.clean.viewmodel;

import androidx.lifecycle.ViewModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import co.com.ceiba.domain.interactor.CasoDeUsoActualizarHistorial;
import co.com.ceiba.domain.interactor.CasoDeUsoIngresarVehiculo;
import co.com.ceiba.domain.interactor.CasoDeUsoListarVehiculosParqueados;
import co.com.ceiba.domain.interactor.CasoDeUsoObtenerVehiculoParqueado;
import co.com.ceiba.domain.modelo.Historial;

public class ParqueadoViewModel extends ViewModel {

    private final CasoDeUsoObtenerVehiculoParqueado casoDeUsoObtenerVehiculoParqueado;

    private final CasoDeUsoListarVehiculosParqueados casoDeUsoListarVehiculosParqueados;

    private final CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial;

    private final CasoDeUsoIngresarVehiculo casoDeUsoIngresarVehiculo;

    @Inject
    ParqueadoViewModel(CasoDeUsoObtenerVehiculoParqueado casoDeUsoObtenerVehiculoParqueado,
                              CasoDeUsoListarVehiculosParqueados casoDeUsoListarVehiculosParqueados,
                              CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial,
                              CasoDeUsoIngresarVehiculo casoDeUsoIngresarVehiculo) {
        this.casoDeUsoObtenerVehiculoParqueado = casoDeUsoObtenerVehiculoParqueado;
        this.casoDeUsoListarVehiculosParqueados = casoDeUsoListarVehiculosParqueados;
        this.casoDeUsoActualizarHistorial = casoDeUsoActualizarHistorial;
        this.casoDeUsoIngresarVehiculo = casoDeUsoIngresarVehiculo;
    }

    public List<Historial> listarParqueados(){
        return casoDeUsoListarVehiculosParqueados.ejecutar();
    }

    public Historial ingresarVehiculo(Historial historial){
        return casoDeUsoIngresarVehiculo.ejecutar(historial);
    }

    public Optional<Double> actualizarHistorial(Historial historial){
        return Optional.of(casoDeUsoActualizarHistorial.ejecutar(historial.getVehiculo().getPlaca(), LocalDateTime.now()));
    }

    public Optional<Historial> buscarVehiculoParqueado(String placa){
        return Optional.of(casoDeUsoObtenerVehiculoParqueado.ejecutar(placa));
    }
}