package CERFID;

public class Paciente extends Personas {
    private int noSesiones;
    private String prescripcionMedica;

    public Paciente(String numDNI, String nombre, String apellido, int noSesiones, String prescripcionMedica) {
        super(numDNI, nombre, apellido);
        this.noSesiones = noSesiones;
        this.prescripcionMedica = prescripcionMedica;
    }

    public int getNoSesiones() {
        return noSesiones;
    }

    public void setNoSesiones(int noSesiones) {
        this.noSesiones = noSesiones;
    }

    public String getPrescripcionMedica() {
        return prescripcionMedica;
    }

    public void setPrescripcionMedica(String prescripcionMedica) {
        this.prescripcionMedica = prescripcionMedica;
    }

    @Override
    public String toString() {
        return "Paciente|" +super.toString()+
                ",noSesiones=" + noSesiones +
                ", prescripcionMedica='" + prescripcionMedica + '\'' +
                '|';
    }
}

