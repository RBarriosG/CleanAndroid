package co.com.ceiba.data.async;

import android.os.AsyncTask;

import co.com.ceiba.data.entidad.HistorialDao;
import co.com.ceiba.data.entidad.HistorialEntity;

public class ActualizarVehiculoAsyncTask extends AsyncTask<HistorialEntity, Void, Void> {

    private HistorialDao historialDao;

    public ActualizarVehiculoAsyncTask(HistorialDao historialDao){
        this.historialDao = historialDao;
    }

    @Override
    protected Void doInBackground(HistorialEntity... historialEntities) {
        historialDao.actualizarHistorial(historialEntities[0]);
        return null;
    }
}
