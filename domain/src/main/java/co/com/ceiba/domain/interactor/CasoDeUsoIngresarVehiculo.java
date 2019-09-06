package co.com.ceiba.domain.interactor;

import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.reglasnegocio.ReglaIngresarVehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

public class CasoDeUsoIngresarVehiculo {

    private final RepositorioHistorial repositorioHistorial;
    private final ReglaIngresarVehiculo reglaIngresarVehiculo;

    public CasoDeUsoIngresarVehiculo(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
        this.reglaIngresarVehiculo = new ReglaIngresarVehiculo(repositorioHistorial);
    }

    public Historial ejecutar(Historial historial){
        reglaIngresarVehiculo.validar(historial.getVehiculo(), historial.getFechaIngreso());
        return repositorioHistorial.ingresarVehiculo(historial);
    }

}
