package co.com.ceiba.domain.reglasnegocio;

import java.time.LocalDateTime;

import co.com.ceiba.domain.modelo.Vehiculo;

public class GenerarCobro {

    public double calcularCobro(Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida) {
        return ReglaCobro.crear(vehiculo).calcularCobro(fechaIngreso, fechaSalida);
    }

}
