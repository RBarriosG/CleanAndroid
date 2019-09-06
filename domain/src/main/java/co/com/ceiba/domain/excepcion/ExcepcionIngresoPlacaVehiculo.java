package co.com.ceiba.domain.excepcion;

public class ExcepcionIngresoPlacaVehiculo extends RuntimeException {
    public ExcepcionIngresoPlacaVehiculo(String mensaje) {
        super(mensaje);
    }
}
