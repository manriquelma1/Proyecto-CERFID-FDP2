package CERFID;

public class Paciente extends Personas {
    private int noSesiones;
    private String prescripcionMedica;

    public Paciente(String numDNI, String nombre, String apellido) {
        super(numDNI, nombre, apellido);
    }

    public int getNoSesiones() {
        return noSesiones;
    }

    public String getPrescripcionMedica() {
        return prescripcionMedica;
    }
}
