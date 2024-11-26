package CERFID;

import java.util.List;

public class Citas extends Personas {
    private String fechaHora;
    private String TerapeutaAsignado;

    public Citas(String numDNI, String nombre, String apellido, String fechaHora, String terapeutaAsignado) {
        super(numDNI, nombre, apellido);
        this.fechaHora = fechaHora;
        TerapeutaAsignado = terapeutaAsignado;
    }

    public String getTerapeutaAsignado() {
        return TerapeutaAsignado;
    }

    public void setTerapeutaAsignado(String terapeutaAsignado) {
        TerapeutaAsignado = terapeutaAsignado;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Citas{" +
                "fechaHora='" + fechaHora + '\'' +
                ", TerapeutaAsignado='" + TerapeutaAsignado + '\'' +
                '}';
    }
}
}
