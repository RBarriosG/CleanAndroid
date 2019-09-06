package co.com.ceiba.domain.reglasnegocio;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class ReglaFecha {

    public boolean esDiaHabil(LocalDateTime fechaIngreso) {
        return !esLunes(fechaIngreso) && !esDomingo(fechaIngreso);
    }

    private boolean esDomingo(LocalDateTime fechaIngreso) {
        return fechaIngreso.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private boolean esLunes(LocalDateTime fechaIngreso) {
        return fechaIngreso.getDayOfWeek() == DayOfWeek.MONDAY;
    }

}
