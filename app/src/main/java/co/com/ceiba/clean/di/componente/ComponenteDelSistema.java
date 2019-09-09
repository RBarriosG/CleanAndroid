package co.com.ceiba.clean.di.componente;

import javax.inject.Singleton;

import co.com.ceiba.clean.activity.MainActivity;
import co.com.ceiba.clean.di.modulo.ModuloDelSistema;
import dagger.Component;

@Singleton
@Component(modules = {ModuloDelSistema.class})
public interface ComponenteDelSistema {

    void inject(MainActivity activity);

}
