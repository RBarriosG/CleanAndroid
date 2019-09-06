package co.com.ceiba.data.conversor;

import co.com.ceiba.domain.modelo.Vehiculo;

public class ConversorVehiculo {

    private ConversorVehiculo() {
    }

    static Vehiculo aVehiculo(VehiculoMap vehiculoMap) {
        return vehiculoMap == null ? null : new Vehiculo(vehiculoMap.getPlaca(),
                vehiculoMap.getCilindraje(), ConversorTipoVehiculo.aTipoVehiculo(vehiculoMap.getTipo()));
    }

    static VehiculoMap aVehiculoMap(Vehiculo vehiculo) {
        return vehiculo == null ? null : new VehiculoMap(vehiculo.getPlaca(), vehiculo.getCilindraje(),
                ConversorTipoVehiculo.aString(vehiculo.getTipo()));
    }

}
