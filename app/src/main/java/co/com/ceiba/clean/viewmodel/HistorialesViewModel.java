package co.com.ceiba.clean.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.com.ceiba.domain.interactor.CasoDeUsoListarHistoriales;
import co.com.ceiba.domain.modelo.Historial;

public class HistorialesViewModel extends ViewModel {

    private CasoDeUsoListarHistoriales casoDeUsoListarHistoriales;

    public HistorialesViewModel(CasoDeUsoListarHistoriales casoDeUsoListarHistoriales) {
        this.casoDeUsoListarHistoriales = casoDeUsoListarHistoriales;
    }

    public LiveData<List<Historial>> listarHistoriales(){
        return (LiveData) casoDeUsoListarHistoriales.ejecutar();
    }

}