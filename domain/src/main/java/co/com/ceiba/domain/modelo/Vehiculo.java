package co.com.ceiba.domain.modelo;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.excepcion.ExcepcionValorObligaorio;
import co.com.ceiba.domain.excepcion.ExepcionLongitudValor;

public class Vehiculo {

    private final String LA_PLACA_ES_OBLIGATORIA = "La placa es obligatoria";
    private final String LA_PLACA_DEBE_TENER_MINIMO_LETRAS = "La placa debe tener minimo letras";
    private final String EL_CILINDRAJE_ES_OBLIGATORIO = "El cilindraje es obligatorio";

    private String placa;

    private int cilindraje;

    private TipoVehiculo tipo;

    public Vehiculo(String placa, int cilindraje, TipoVehiculo tipo) {
        validarPlaca(placa);
        validarCilindraje(cilindraje, tipo);
        this.placa = placa;
        this.cilindraje = cilindraje;
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", cilindraje=" + cilindraje +
                ", tipo=" + tipo +
                '}';
    }

    private void validarPlaca(String placa) {
        validadorObligatorio(placa);
        validadorNoVacio(placa);
        validadorPlacaSinLetras(placa);
    }

    private void validarCilindraje(int cilindraje, TipoVehiculo tipo) {
        if (tipo == TipoVehiculo.MOTO && cilindraje <= 0) {
            throw new ExcepcionValorObligaorio(EL_CILINDRAJE_ES_OBLIGATORIO);
        }
    }

    private void validadorObligatorio(Object valor) {
        if (valor == null) {
            throw new ExcepcionValorObligaorio(LA_PLACA_ES_OBLIGATORIA);
        }
    }

    private void validadorNoVacio(String valor) {
        if (valor.trim().isEmpty()) {
            throw new ExepcionLongitudValor(LA_PLACA_DEBE_TENER_MINIMO_LETRAS);
        }
    }

    private void validadorPlacaSinLetras(String valor){
        try{
            Integer.parseInt(valor);
            throw new ExepcionLongitudValor(LA_PLACA_DEBE_TENER_MINIMO_LETRAS);
        } catch (NumberFormatException nf){
            return;
        }
    }

}
