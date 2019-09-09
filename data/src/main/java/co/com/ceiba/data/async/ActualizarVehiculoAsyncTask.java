package co.com.ceiba.data.async;

import android.os.AsyncTask;

import co.com.ceiba.data.dao.HistorialDao;
import co.com.ceiba.data.entidad.HistorialEntity;

public class ActualizarVehiculoAsyncTask extends AsyncTask<HistorialEntity, Void, Void> {

    private HistorialDao historialDao;

    public ActualizarVehiculoAsyncTask(HistorialDao historialDao){
        this.historialDao = historialDao;
    }

    @Override
    protected Void doInBackground(HistorialEntity... historialEntities) {
        historialDao.ingresarVehiculo(historialEntities[0]);
        return null;
    }
}
