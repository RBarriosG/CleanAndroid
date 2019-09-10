package co.com.ceiba.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import co.com.ceiba.data.dao.HistorialDao;
import co.com.ceiba.data.entidad.HistorialEntity;

@Database(entities = {HistorialEntity.class}, version = 1)
public abstract class BaseDeDatosParqueadero extends RoomDatabase {

    private static volatile BaseDeDatosParqueadero INSTANCE;

    public abstract HistorialDao historialDao();

    public static BaseDeDatosParqueadero getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BaseDeDatosParqueadero.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), BaseDeDatosParqueadero.class, "parqueadero.db").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
