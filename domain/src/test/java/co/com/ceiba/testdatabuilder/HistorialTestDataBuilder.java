package co.com.ceiba.testdatabuilder;

import java.time.LocalDateTime;

import co.com.ceiba.domain.modelo.Historial;
import co.com.ceiba.domain.modelo.Vehiculo;

public class HistorialTestDataBuilder {

    private static final Vehiculo VEHICULO_CARRO_PRUEBA = new VehiculoTestDataBuilder().build();
    private static final LocalDateTime FECHA_INGRESO_PRUEBA = LocalDateTime.of(2019, 8, 28, 7, 0);

    private Vehiculo vehiculo;

    private LocalDateTime fechaIngreso;

    public HistorialTestDataBuilder() {
        this.vehiculo = VEHICULO_CARRO_PRUEBA;
        this.fechaIngreso = FECHA_INGRESO_PRUEBA;
    }

    public HistorialTestDataBuilder conVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        return this;
    }

    public HistorialTestDataBuilder conFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
        return this;
    }

    public Historial build() {
        return new Historial(this.vehiculo, this.fechaIngreso);
    }


}
