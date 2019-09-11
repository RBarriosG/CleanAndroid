package co.com.ceiba.domain.reglasnegocio;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

class ReglaCupoMoto {

    private static final int MAXIMO_CUPO_MOTO = 10;
    private RepositorioHistorial repositorioHistorial;

    ReglaCupoMoto(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
    }

    boolean validar() {
        long motosParqueadas = repositorioHistorial.contarVehiculosParqueadosPorTipo(TipoVehiculo.MOTO);
        return motosParqueadas < MAXIMO_CUPO_MOTO;
    }

}
