package co.com.ceiba.clean.viewmodel.async;

import android.os.AsyncTask;

import co.com.ceiba.domain.interactor.CasoDeUsoIngresarVehiculo;
import co.com.ceiba.domain.modelo.Historial;

public class IngresarVehiculo extends AsyncTask<Historial, Void, Void> {

    private CasoDeUsoIngresarVehiculo casoDeUsoIngresarVehiculo;

    public IngresarVehiculo(CasoDeUsoIngresarVehiculo casoDeUsoIngresarVehiculo) {
        this.casoDeUsoIngresarVehiculo = casoDeUsoIngresarVehiculo;
    }

    @Override
    protected Void doInBackground(Historial... historials) {
        casoDeUsoIngresarVehiculo.ejecutar(historials[0]);
        return null;
    }
}
