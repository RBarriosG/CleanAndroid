package co.com.ceiba.clean.di.modulo;

import javax.inject.Singleton;

import co.com.ceiba.domain.interactor.CasoDeUsoActualizarHistorial;
import co.com.ceiba.domain.interactor.CasoDeUsoIngresarVehiculo;
import co.com.ceiba.domain.interactor.CasoDeUsoListarHistoriales;
import co.com.ceiba.domain.interactor.CasoDeUsoListarVehiculosParqueados;
import co.com.ceiba.domain.interactor.CasoDeUsoObtenerVehiculoParqueado;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;
import dagger.Module;
import dagger.Provides;

@Module
public class ModuloDeUsoDeCasos {

    @Provides
    @Singleton
    CasoDeUsoListarHistoriales provideCasoDeUsoListarHistoriales(RepositorioHistorial repositorioHistorial){
        return new CasoDeUsoListarHistoriales(repositorioHistorial);
    }

    @Provides
    @Singleton
    CasoDeUsoIngresarVehiculo provideCasoDeUsoIngresarVehiculo(RepositorioHistorial repositorioHistorial){
        return new CasoDeUsoIngresarVehiculo(repositorioHistorial);
    }

    @Provides
    @Singleton
    CasoDeUsoActualizarHistorial provideCasoDeUsoActualizarHistorial(RepositorioHistorial repositorioHistorial){
        return new CasoDeUsoActualizarHistorial(repositorioHistorial);
    }

    @Provides
    @Singleton
    CasoDeUsoListarVehiculosParqueados provideCasoDeUsoListarVehiculosParqueados(RepositorioHistorial repositorioHistorial){
        return new CasoDeUsoListarVehiculosParqueados(repositorioHistorial);
    }

    @Provides
    @Singleton
    CasoDeUsoObtenerVehiculoParqueado provideCasoDeUsoObtenerVehiculoParqueado(RepositorioHistorial repositorioHistorial){
        return new CasoDeUsoObtenerVehiculoParqueado(repositorioHistorial);
    }
}
