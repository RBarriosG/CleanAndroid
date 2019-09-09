package co.com.ceiba.clean.di.componente;

import javax.inject.Singleton;

import co.com.ceiba.clean.di.modulo.ModuloDeUsoDeCasos;
import co.com.ceiba.clean.di.modulo.ModuloDelSistema;
import co.com.ceiba.clean.viewmodel.HistorialesViewModel;
import co.com.ceiba.clean.viewmodel.ParqueadoViewModel;
import dagger.Component;

@Singleton
@Component(modules = {ModuloDelSistema.class, ModuloDeUsoDeCasos.class})
public interface ComponenteDeCasoDeUso {

    void inject(HistorialesViewModel historialesViewModel);

    void inject(ParqueadoViewModel parqueadoViewModel);
}
