package co.com.ceiba.clean.di.modulo;

import javax.inject.Singleton;

import co.com.ceiba.domain.interactor.CasoDeUsoListarHistoriales;
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

}
