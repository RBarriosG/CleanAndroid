package co.com.ceiba.domain.excepcion;

public class ExcepcionVehiculoYaEstaEnParqueadero extends RuntimeException {
    public ExcepcionVehiculoYaEstaEnParqueadero(String mensaje) {
        super(mensaje);
    }
}
