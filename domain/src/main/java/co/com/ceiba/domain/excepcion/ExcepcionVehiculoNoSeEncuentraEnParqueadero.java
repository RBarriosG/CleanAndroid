package co.com.ceiba.domain.excepcion;

public class ExcepcionVehiculoNoSeEncuentraEnParqueadero extends RuntimeException {
    public ExcepcionVehiculoNoSeEncuentraEnParqueadero(String mensaje) {
        super(mensaje);
    }
}
