package co.com.ceiba.data.conversor;


import co.com.ceiba.domain.enumeracion.TipoVehiculo;

public class ConversorTipoVehiculo {

    private static final String CARRO = "CARRO";
    private static final String MOTO = "MOTO";

    private ConversorTipoVehiculo(){}

    public static String aString(TipoVehiculo tipo){
        return tipo == TipoVehiculo.CARRO ? CARRO : MOTO;
    }

    static TipoVehiculo aTipoVehiculo(String tipo){
        return tipo.equalsIgnoreCase(CARRO) ? TipoVehiculo.CARRO : TipoVehiculo.MOTO;
    }

}
