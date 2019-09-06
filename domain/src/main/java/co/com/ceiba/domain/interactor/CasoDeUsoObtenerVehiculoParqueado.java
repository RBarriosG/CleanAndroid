package co.com.ceiba.domain.interactor;

import co.com.ceiba.domain.excepcion.ExcepcionVehiculoNoSeEncuentraEnParqueadero;
import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

public class CasoDeUsoObtenerVehiculoParqueado {

    private static final String VEHICULO_NO_ESTA_EN_PARQUEADERO = "Vehiculo no está en parqueadero";

    private RepositorioHistorial repositorioHistorial;

    public CasoDeUsoObtenerVehiculoParqueado(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
    }

    public Historial ejecutar(String placa){
        if (!repositorioHistorial.obtenerVehiculoParqueado(placa).isPresent()){
            throw new ExcepcionVehiculoNoSeEncuentraEnParqueadero(VEHICULO_NO_ESTA_EN_PARQUEADERO);
        }
        return repositorioHistorial.obtenerVehiculoParqueado(placa).get();
    }

}
