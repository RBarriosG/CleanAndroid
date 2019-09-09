package co.com.ceiba.domain.reglasnegocio;

import java.time.LocalDateTime;

import co.com.ceiba.domain.excepcion.ExcepcionIngresoPlacaVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionMaximoCupoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionVehiculoYaEstaEnParqueadero;
import co.com.ceiba.domain.modelo.Vehiculo;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;

public class ReglaIngresarVehiculo {

    private static final String DIA_NO_HABIL_PARA_INGRESO = "Dia no habil para ingreso";
    private static final String VEHICULO_YA_ESTA_EN_EL_PARQUEADERO = "Vehiculo ya está en el parqueadero";
    private static final String CUPO_MAXIMO_DE_CARROS = "Cupo maximo de carros";
    private static final String CUPO_MAXIMO_DE_MOTOS = "Cupo maximo de motos";

    private ReglaCupoCarro reglaCupoCarro;
    private ReglaCupoMoto reglaCupoMoto;
    private ReglaPlaca reglaPlaca;
    private ReglaFecha reglaFecha;
    private RepositorioHistorial repositorioHistorial;

    public ReglaIngresarVehiculo(RepositorioHistorial repositorioHistorial) {
        this.repositorioHistorial = repositorioHistorial;
        this.reglaCupoCarro = new ReglaCupoCarro(repositorioHistorial);
        this.reglaCupoMoto = new ReglaCupoMoto(repositorioHistorial);
        this.reglaPlaca = new ReglaPlaca();
        this.reglaFecha = new ReglaFecha();
    }

    public void validar(Vehiculo vehiculo, LocalDateTime fechaIngreso) {
        if (!validarIngresoPlacaDiaHabil(vehiculo.getPlaca(), fechaIngreso)) {
            throw new ExcepcionIngresoPlacaVehiculo(DIA_NO_HABIL_PARA_INGRESO);
        } else if (yaEstaEnParqueadero(vehiculo.getPlaca())) {
            throw new ExcepcionVehiculoYaEstaEnParqueadero(VEHICULO_YA_ESTA_EN_EL_PARQUEADERO);
        } else if (!reglaCupoCarro.validar()) {
            throw new ExcepcionMaximoCupoVehiculo(CUPO_MAXIMO_DE_CARROS);
        } else if (!reglaCupoMoto.validar()) {
            throw new ExcepcionMaximoCupoVehiculo(CUPO_MAXIMO_DE_MOTOS);
        }
    }

    private boolean yaEstaEnParqueadero(String placa) {
        return this.repositorioHistorial.obtenerVehiculoParqueado(placa).isPresent();
    }

    private boolean validarIngresoPlacaDiaHabil(String placa, LocalDateTime fechaIngreso) {
        return reglaFecha.esDiaHabil(fechaIngreso) || !reglaPlaca.empiezaPorA(placa);
    }

}
