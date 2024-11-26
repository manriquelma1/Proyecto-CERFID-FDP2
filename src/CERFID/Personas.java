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

    public void setNumDNI(String numDNI) {
        this.numDNI = numDNI;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    @Override
    public String toString() {
        return
                "DNI: '" + numDNI + '\'' +
                        ", Nombre: '" + nombre + '\'' +
                        ", Apellido: '" + Apellido + '\'';
    }
}

