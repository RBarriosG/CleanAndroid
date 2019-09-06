package co.com.ceiba.domain.reglasnegocio;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

public class ReglaCupoCarro {

    private static final int MAXIMO_CUPO_CARRO = 20;
    private RepositorioHistorial repositorioHistorial;

    public ReglaCupoCarro(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
    }

    public boolean validar() {
        long carrosParqueados = repositorioHistorial.contarVehiculosParqueadosPorTipo(TipoVehiculo.CARRO);
        return carrosParqueados < MAXIMO_CUPO_CARRO;
    }

}
