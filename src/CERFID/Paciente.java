package CERFID;

class Paciente extends Personas {
    private int noSesiones;
    private String prescripcionMedica;

    public Paciente(String numDNI, String nombre, String apellido) {
        super(numDNI, nombre, apellido);
    }

}
