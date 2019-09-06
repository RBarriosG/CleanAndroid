package co.com.ceiba.domain.interactor;

import java.util.List;

import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

public class CasoDeUsoListarVehiculosParqueados {

    private RepositorioHistorial repositorioHistorial;

    public CasoDeUsoListarVehiculosParqueados(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
    }

    public List<Historial> ejecutar(){
        return repositorioHistorial.listarVehiculosParqueados();
    }

}
