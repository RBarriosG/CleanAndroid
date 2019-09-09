package co.com.ceiba.clean.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import co.com.ceiba.domain.interactor.CasoDeUsoActualizarHistorial;
import co.com.ceiba.domain.interactor.CasoDeUsoIngresarVehiculo;
import co.com.ceiba.domain.interactor.CasoDeUsoListarHistoriales;
import co.com.ceiba.domain.interactor.CasoDeUsoListarVehiculosParqueados;
import co.com.ceiba.domain.interactor.CasoDeUsoObtenerVehiculoParqueado;

@Singleton
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final CasoDeUsoListarHistoriales casoDeUsoListarHistoriales;

    private final CasoDeUsoIngresarVehiculo casoDeUsoIngresarVehiculo;

    private final CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial;

    private final CasoDeUsoListarVehiculosParqueados casoDeUsoListarVehiculosParqueados;

    private final CasoDeUsoObtenerVehiculoParqueado casoDeUsoObtenerVehiculoParqueado;

    @Inject
    public ViewModelFactory(CasoDeUsoListarHistoriales casoDeUsoListarHistoriales,
                            CasoDeUsoIngresarVehiculo casoDeUsoIngresarVehiculo,
                            CasoDeUsoActualizarHistorial casoDeUsoActualizarHistorial,
                            CasoDeUsoListarVehiculosParqueados casoDeUsoListarVehiculosParqueados,
                            CasoDeUsoObtenerVehiculoParqueado casoDeUsoObtenerVehiculoParqueado) {
        this.casoDeUsoListarHistoriales = casoDeUsoListarHistoriales;
        this.casoDeUsoIngresarVehiculo = casoDeUsoIngresarVehiculo;
        this.casoDeUsoActualizarHistorial = casoDeUsoActualizarHistorial;
        this.casoDeUsoListarVehiculosParqueados = casoDeUsoListarVehiculosParqueados;
        this.casoDeUsoObtenerVehiculoParqueado = casoDeUsoObtenerVehiculoParqueado;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HistorialesViewModel.class)){
            return (T) new HistorialesViewModel(casoDeUsoListarHistoriales);
        }
        if (modelClass.isAssignableFrom(ParqueadoViewModel.class)){
            return (T) new ParqueadoViewModel(casoDeUsoObtenerVehiculoParqueado,
                    casoDeUsoListarVehiculosParqueados,
                    casoDeUsoActualizarHistorial,
                    casoDeUsoIngresarVehiculo);
        }
        throw new IllegalArgumentException("ViewModel desconocido");
    }
}
