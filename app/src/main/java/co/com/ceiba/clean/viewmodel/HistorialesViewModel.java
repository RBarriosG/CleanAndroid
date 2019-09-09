package co.com.ceiba.clean.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import co.com.ceiba.clean.di.componente.ComponenteDeCasoDeUso;
import co.com.ceiba.clean.di.componente.DaggerComponenteDeCasoDeUso;
import co.com.ceiba.domain.interactor.CasoDeUsoListarHistoriales;
import co.com.ceiba.domain.modelo.Historial;

public class HistorialesViewModel extends ViewModel {

    private CasoDeUsoListarHistoriales casoDeUsoListarHistoriales;

    private ComponenteDeCasoDeUso componenteDeCasoDeUso;

    @Inject
    public HistorialesViewModel(CasoDeUsoListarHistoriales casoDeUsoListarHistoriales) {
        componenteDeCasoDeUso = DaggerComponenteDeCasoDeUso.create();
        this.casoDeUsoListarHistoriales = casoDeUsoListarHistoriales;
    }

    public List<Historial> listarHistoriales(){
        return casoDeUsoListarHistoriales.ejecutar();
    }

}