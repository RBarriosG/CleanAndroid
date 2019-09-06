package co.com.ceiba.data.entidad;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import co.com.ceiba.data.conversor.VehiculoMap;

@Entity(tableName = "historial")
public class HistorialEntity {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    private String fechaIngreso;

    private String fechaSalida;

    @Embedded
    private VehiculoMap vehiculo;

    private double cobro;

    public HistorialEntity(String fechaIngreso, String fechaSalida, VehiculoMap vehiculo, double cobro) {
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.vehiculo = vehiculo;
        this.cobro = cobro;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public VehiculoMap getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculoMap vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getCobro() {
        return cobro;
    }

    public void setCobro(double cobro) {
        this.cobro = cobro;
    }
}
