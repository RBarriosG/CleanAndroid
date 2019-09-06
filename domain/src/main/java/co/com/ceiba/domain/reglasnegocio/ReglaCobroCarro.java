package co.com.ceiba.domain.reglasnegocio;

public class ReglaCobroCarro extends ReglaCobro {

    private final double VALOR_HORA = 1000;
    private final double VALOR_DIA = 8000;

    @Override
    public double getValorDia() {
        return VALOR_DIA;
    }

    @Override
    public double getValorHora() {
        return VALOR_HORA;
    }
}
