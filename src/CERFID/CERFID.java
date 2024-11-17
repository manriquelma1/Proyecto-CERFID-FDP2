package CERFID;

import java.util.ArrayList;
import java.util.List;

public class CERFID {
    private List<Personas> listaPersonasRegistradas;

    public CERFID() {
        this.listaPersonasRegistradas = new ArrayList<>();
    }

    public void agregarpersonas(Personas personas) {
        this.listaPersonasRegistradas.add(personas);
    }

    public void imprimirListaTrabajadores() {
        for (Personas personas : listaPersonasRegistradas) {
            System.out.println(personas);
        }
    }
}