package co.com.ceiba.data.conversor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConversorLocalDateTime {

    private ConversorLocalDateTime(){}

    private static DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime aDato(String datoString){
        return datoString == null ? null : LocalDateTime.parse(datoString, formater);
    }

    public static String aString(LocalDateTime dato){
        return dato == null ? null : dato.format(formater);
    }

}
