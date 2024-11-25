package CERFID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CERFID {
    private List<Personas> listaPersonasRegistradas;
    private List<Citas> listaCitas;


    public CERFID() {
        this.listaPersonasRegistradas = new ArrayList<>();
        this.listaCitas = new ArrayList<>();
    }


    public void agregarpersonas(Personas personas) {
        this.listaPersonasRegistradas.add(personas);
    }

    public void agregarCita(Citas citas) {
        this.listaCitas.add(citas);
    }

    public List<Citas> obtenerCitas() {
        return Collections.unmodifiableList(listaCitas);
    }


    public Personas buscarPorDNI(String numDNI) {
        for (Personas persona : listaPersonasRegistradas) {
            if (persona.getNumDNI().equals(numDNI)) {
                return persona;
            }
        }

        for (Personas persona : listaPersonasRegistradas) {
            if (persona.getNumDNI().equals(numDNI)) {
                return persona;
            }
        }

        return null;
    }

    public List<Terapeutas> obtenerTerapeutas() {
        List<Terapeutas> terapeutasDisponibles = new ArrayList<>();
        for (Personas persona : listaPersonasRegistradas) {
            // Verificamos si la persona es un terapeuta
            if (persona instanceof Terapeutas) {
                // Cast a Terapeutas y lo agregamos a la lista
                terapeutasDisponibles.add((Terapeutas) persona);
            }
        }
        return terapeutasDisponibles;
    }


    public List<Paciente> obtenerPacientes() {
        List<Paciente> pacientesDisponibles = new ArrayList<>();
        for (Personas persona : listaPersonasRegistradas) {
            // Verificamos si la persona es un paciente
            if (persona instanceof Paciente) {
                // Cast a Paciente y lo agregamos a la lista
                pacientesDisponibles.add((Paciente) persona);
            }
        }
        return pacientesDisponibles;
    }

}



