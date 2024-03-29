package co.com.ceiba.clean.di.modulo;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ModuloDelSistema {

    private final Application application;

    public ModuloDelSistema(Application application){
        this.application = application;
    }

    @Provides
    Context provideContext(){
        return application;
    }

}
