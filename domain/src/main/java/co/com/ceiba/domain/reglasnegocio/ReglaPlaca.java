package co.com.ceiba.domain.reglasnegocio;

class ReglaPlaca {

    private static final String REGLAS_PLACA = "a";

    boolean empiezaPorA(String placa) {
        return placa.toLowerCase().startsWith(REGLAS_PLACA);
    }

}
