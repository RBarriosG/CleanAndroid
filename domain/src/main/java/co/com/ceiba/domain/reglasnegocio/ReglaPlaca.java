package co.com.ceiba.domain.reglasnegocio;

public class ReglaPlaca {

    private static final String REGLAS_PLACA = "a";

    public boolean empiezaPorA(String placa) {
        return placa.toLowerCase().startsWith(REGLAS_PLACA);
    }

}
