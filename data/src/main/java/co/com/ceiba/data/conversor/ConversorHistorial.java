package co.com.ceiba.data.conversor;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.data.entidad.HistorialEntity;
import co.com.ceiba.domain.modelo.Historial;

public class ConversorHistorial {

    private ConversorHistorial() {
    }

    public static HistorialEntity convertirAEntidad(Historial historial) {
        return new HistorialEntity(ConversorLocalDateTime.aString(historial.getFechaIngreso()),
                ConversorLocalDateTime.aString(historial.getFechaSalida()), ConversorVehiculo.aVehiculoMap(historial.getVehiculo()), historial.getCobro());
    }

    public static Historial convertirADominio(HistorialEntity historialEntity) {
        return new Historial(ConversorVehiculo.aVehiculo(historialEntity.getVehiculo()), ConversorLocalDateTime.aDato(historialEntity.getFechaIngreso()),
                ConversorLocalDateTime.aDato(historialEntity.getFechaSalida()), historialEntity.getCobro());
    }

    public static List<Historial> convertirADominio(List<HistorialEntity> historialEntities) {
        if (!historialEntities.isEmpty()) {
            List<Historial> historiales = new ArrayList<>();
            for (int i = 0; i < historialEntities.size(); i++) {
                historiales.add(convertirADominio(historialEntities.get(i)));
            }
            return historiales;
        }
        return new ArrayList<>();
    }

}
