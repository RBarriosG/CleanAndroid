package co.com.ceiba.domain.reglasnegocio;

import java.time.LocalDateTime;

import co.com.ceiba.domain.modelo.Vehiculo;

public class ReglaCobroMoto extends ReglaCobro {

    private static final double VALOR_HORA = 500;
    private static final double VALOR_DIA = 4000;
    private static final double VALOR_EXCEDENTE_CILINDRAJE = 2000;
    private static final int MAXIMO_CILINDRAJE = 500;
    private Vehiculo vehiculo;

    ReglaCobroMoto(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public double calcularCobro(LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
        double costo = super.calcularCobro(fechaIngreso, fechaSalida);
        return vehiculo.getCilindraje() > MAXIMO_CILINDRAJE ? costo + VALOR_EXCEDENTE_CILINDRAJE : costo;
    }

    @Override
    public double getValorDia() {
        return VALOR_DIA;
    }

    @Override
    public double getValorHora() {
        return VALOR_HORA;
    }
}
