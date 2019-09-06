package co.com.ceiba.domain.excepcion;

public class ExcepcionMaximoCupoVehiculo extends RuntimeException {
    public ExcepcionMaximoCupoVehiculo(String mensaje) {
        super(mensaje);
    }
}
