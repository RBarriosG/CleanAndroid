package co.com.ceiba.domain.interactor;

import java.util.List;

import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

public class CasoDeUsoListarHistoriales {

    private final RepositorioHistorial repositorioHistorial;

    public CasoDeUsoListarHistoriales(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
    }

    public List<Historial> ejecutar(){
        return repositorioHistorial.listarHistoriales();
    }

}
