package co.com.ceiba.domain.repositorio;

import java.util.List;
import java.util.Optional;

import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.enumeracion.TipoVehiculo;

public interface RepositorioHistorial {

    List<Historial> listarHistoriales();

    Historial ingresarVehiculo(Historial historial);

    double actualizarHistorial(Historial historial);

    List<Historial> listarVehiculosParqueados();

    Optional<Historial> obtenerVehiculoParqueado(String placa);

    long contarVehiculosParqueadosPorTipo(TipoVehiculo tipoVehiculo);

}
