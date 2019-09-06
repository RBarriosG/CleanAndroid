package co.com.ceiba.data.conversor;

public class VehiculoMap {

    private String placa;

    private int cilindraje;

    private String tipo;

    public VehiculoMap(String placa, int cilindraje, String tipo) {
        this.placa = placa;
        this.cilindraje = cilindraje;
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
