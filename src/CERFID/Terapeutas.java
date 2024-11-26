package CERFID;

public class Terapeutas extends Personas {
    private String codigoTrabajador;

    public Terapeutas(String numDNI, String nombre, String apellido, String codigoTrabajador) {
        super(numDNI, nombre, apellido);
        this.codigoTrabajador = codigoTrabajador;
    }

    public String getCodigoTrabajador(){return codigoTrabajador;}

    public void setCodigoTrabajador(String codigoTrabajador) {
        this.codigoTrabajador = codigoTrabajador;
    }

    @Override
    public String toString() {
        return "Terapeutas{" +
                "codigoTrabajador='" + codigoTrabajador + '\'' +
                '}';
    }
}
