package CERFID;

public abstract class Personas {
    private String numDNI;
    private String nombre;
    private String Apellido;

    public Personas(String numDNI, String nombre, String apellido) {
        this.numDNI = numDNI;
        this.nombre = nombre;
        Apellido = apellido;
    }

    public String getNumDNI() {
        return numDNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    @Override
    public String toString() {
        return "Personas{" +
                "numDNI='" + numDNI + '\'' +
                ", nombre='" + nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                '}';
    }
}

