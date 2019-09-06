package co.com.ceiba.data.entidad;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface HistorialDao {

    @Query("SELECT * FROM historial WHERE fechaSalida IS NOT NULL GROUP BY placa")
    List<HistorialEntity> listarHistoriales();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void ingresarVehiculo(HistorialEntity historialEntity);

    @Update
    void actualizarHistorial(HistorialEntity historialEntity);

    @Query("SELECT * FROM historial WHERE fechaSalida IS NULL GROUP BY placa")
    List<HistorialEntity> listarVehiculosParqueados();

    @Query("SELECT * FROM historial WHERE fechaSalida IS NULL AND placa LIKE :placa")
    HistorialEntity obtenerVehiculoParqueado(String placa);

    @Query("SELECT * FROM historial WHERE fechaSalida IS NULL AND tipo LIKE :tipo")
    long contarVehiculosParqueadosPorTipo(String tipo);

}
