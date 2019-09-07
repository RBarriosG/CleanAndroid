package co.com.ceiba.data.dao;

import android.app.Application;

import java.util.List;
import java.util.Optional;

import co.com.ceiba.data.async.ActualizarVehiculoAsyncTask;
import co.com.ceiba.data.async.IngresarVehiculoAsyncTask;
import co.com.ceiba.data.conversor.ConversorHistorial;
import co.com.ceiba.data.conversor.ConversorTipoVehiculo;
import co.com.ceiba.data.db.BaseDeDatosParqueadero;
import co.com.ceiba.data.entidad.HistorialEntity;
import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.repositorio.RepositorioHistorial;


public class HistorialImplementacionLocal implements RepositorioHistorial {

    private final HistorialDao historialDao;

    public HistorialImplementacionLocal(Application application) {
        BaseDeDatosParqueadero bd = BaseDeDatosParqueadero.getInstance(application);
        this.historialDao = bd.historialDao();
    }

    @Override
    public List<Historial> listarHistoriales() {
       return ConversorHistorial.convertirADominio(historialDao.listarHistoriales().getValue());
    }

    @Override
    public Historial ingresarVehiculo(Historial historial) {
        new IngresarVehiculoAsyncTask(historialDao).execute(ConversorHistorial.convertirAEntidad(historial));
        return historial;
    }

    @Override
    public double actualizarHistorial(Historial historial) {
        new ActualizarVehiculoAsyncTask(historialDao).execute(ConversorHistorial.convertirAEntidad(historial));
        return historial.getCobro();
    }

    @Override
    public List<Historial> listarVehiculosParqueados() {
        return ConversorHistorial.convertirADominio(historialDao.listarVehiculosParqueados().getValue());
    }

    @Override
    public Optional<Historial> obtenerVehiculoParqueado(String placa) {
        HistorialEntity entity = historialDao.obtenerVehiculoParqueado(placa).getValue();
        return entity == null ? Optional.<Historial>empty() : Optional.of(ConversorHistorial.convertirADominio(entity));
    }

    @Override
    public long contarVehiculosParqueadosPorTipo(TipoVehiculo tipo) {
        String tipoV = ConversorTipoVehiculo.aString(tipo);
        return historialDao.contarVehiculosParqueadosPorTipo(tipoV);
    }

}
