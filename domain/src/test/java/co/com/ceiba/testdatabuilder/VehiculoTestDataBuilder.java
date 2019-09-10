package co.com.ceiba.testdatabuilder;

import co.com.ceiba.domain.enumeracion.TipoVehiculo;
import co.com.ceiba.domain.modelo.Vehiculo;

public class VehiculoTestDataBuilder {

    private static final String PLACA_V = "EDC345";
    private static final TipoVehiculo TIPO_V = TipoVehiculo.CARRO;
    private static final int CILINDRAJE_V = 0;

    private String placa;
    private TipoVehiculo tipo;
    private int cilindraje;

    public VehiculoTestDataBuilder() {
        this.placa = PLACA_V;
        this.tipo = TIPO_V;
        this.cilindraje = CILINDRAJE_V;
    }

    public VehiculoTestDataBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    public VehiculoTestDataBuilder conTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
        return this;
    }

    public VehiculoTestDataBuilder conCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
        return this;
    }

    public Vehiculo build() {
        return new Vehiculo(this.placa, this.cilindraje, this.tipo);
    }

}
