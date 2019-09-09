package co.com.ceiba.clean.viewmodel;

import androidx.lifecycle.LiveData;
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
    public ParqueadoViewModel(CasoDeUsoObtenerVehiculoParqueado casoDeUsoObtenerVehiculoParqueado,
                              CasoDeUsoListarVehiculosParqueados casoDeUsoListarVehiculosParqueados,
                              CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial,
                              CasoDeUsoIngresarVehiculo casoDeUsoIngresarVehiculo) {
        this.casoDeUsoObtenerVehiculoParqueado = casoDeUsoObtenerVehiculoParqueado;
        this.casoDeUsoListarVehiculosParqueados = casoDeUsoListarVehiculosParqueados;
        this.casoDeUsoActualizarHistorial = casoDeUsoActualizarHistorial;
        this.casoDeUsoIngresarVehiculo = casoDeUsoIngresarVehiculo;
    }

    public LiveData<List<Historial>> listarParqueados(){
        return (LiveData<List<Historial>>) casoDeUsoListarVehiculosParqueados.ejecutar();
    }

    public void ingresarVehiculo(Historial historial){
        casoDeUsoIngresarVehiculo.ejecutar(historial);
    }

    public void actualizarHistorial(Historial historial){
        casoDeUsoActualizarHistorial.ejecutar(historial.getVehiculo().getPlaca(), LocalDateTime.now());
    }

    public Optional<Historial> buscarVehiculoParqueado(String placa){
        return Optional.of(casoDeUsoObtenerVehiculoParqueado.ejecutar(placa));
    }
}