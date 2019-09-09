package co.com.ceiba.clean;

import android.content.Context;

import co.com.ceiba.clean.viewmodel.ViewModelFactory;
import co.com.ceiba.data.dao.HistorialImplementacionLocal;
import co.com.ceiba.domain.interactor.CasoDeUsoActualizarHistorial;
import co.com.ceiba.domain.interactor.CasoDeUsoIngresarVehiculo;
import co.com.ceiba.domain.interactor.CasoDeUsoListarHistoriales;
import co.com.ceiba.domain.interactor.CasoDeUsoListarVehiculosParqueados;
import co.com.ceiba.domain.interactor.CasoDeUsoObtenerVehiculoParqueado;

public class Injeccion {

    public static ViewModelFactory provideVieModelFactory(Context context){
        return new ViewModelFactory(
                new CasoDeUsoListarHistoriales(new HistorialImplementacionLocal(context)),
                new CasoDeUsoIngresarVehiculo(new HistorialImplementacionLocal(context)),
                new CasoDeUsoActualizarHistorial(new HistorialImplementacionLocal(context)),
                new CasoDeUsoListarVehiculosParqueados(new HistorialImplementacionLocal(context)),
                new CasoDeUsoObtenerVehiculoParqueado(new HistorialImplementacionLocal(context)));
    }

}
