package CERFID;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CERFID cerfid = new CERFID();


        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n===== Menu Principal =====");
            System.out.println("1. Registrar Terapeuta");
            System.out.println("2. Registrar Paciente");
            System.out.println("3. Programar Cita");
            System.out.println("4. Verificar Historia Clinica");
            System.out.println("5. Reporte de Citas");
            System.out.println("0. Salir");
            System.out.print("\n seleccionar opción : ");

            if (scan.hasNextInt()) {
                opcion = scan.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.print("DNI del Terapeuta: ");
                        String numDNI = scan.next();
                        System.out.print("Nombre del Terapeuta: ");
                        String nombre = scan.next();
                        System.out.print("Apellido del Terapeuta: ");
                        String apellidos = scan.next();
                        System.out.print("Codigo de Trabajador: ");
                        String codigoTrabajador = scan.next();
                        cerfid.agregarpersonas(new Terapeutas(numDNI, nombre, apellidos, codigoTrabajador));
                        break;
                    case 2:
                        System.out.print("DNI del Paciente: ");
                        numDNI = scan.next();
                        System.out.print("Nombre del Paciente: ");
                        nombre = scan.next();
                        System.out.print("Apellido del Paciente: ");
                        apellidos = scan.next();
                        scan.nextLine();
                        System.out.print("Prescrición Medica: ");
                        String prescripcionMedica = scan.nextLine();
                        System.out.print("Número de sesiones: ");
                        int noSesiones = scan.nextInt();
                        cerfid.agregarpersonas(new Paciente(numDNI, nombre, apellidos, noSesiones, prescripcionMedica));

                        break;
                    case 3:
                        System.out.print("Fecha y hora de la cita (ej. 2024-11-09 10:15)");
                        scan.nextLine();
                        String fechaHora = scan.nextLine();
                        System.out.print("Ingresar el DNi del paciente");
                        numDNI = scan.next();
                        Personas personaEncontrada = cerfid.buscarPorDNI(numDNI);

                        if (personaEncontrada instanceof Paciente) {
                            System.out.println("Paciente encontrado: " + personaEncontrada.getNombre() + " " + personaEncontrada.getApellido());

                            List<Terapeutas> terapeutasDisponibles = cerfid.obtenerTerapeutas();
                            if (terapeutasDisponibles.isEmpty()) {
                                System.out.println("No hay terapeutas disponibles");
                                break;
                            }
                            System.out.println("===== Lista de Terapeutas =====");
                            for (int i = 0; i < terapeutasDisponibles.size(); i++) {
                                Terapeutas t = terapeutasDisponibles.get(i);
                                System.out.println(i + " " + t);
                            }
                            System.out.println("Seleccione el DNI del Terapeuta: ");

                            int seleccionarTerapeuta = scan.nextInt();
                            if (seleccionarTerapeuta >= 0 && seleccionarTerapeuta < terapeutasDisponibles.size()) {
                                Terapeutas terapeutas = terapeutasDisponibles.get(seleccionarTerapeuta);
                                String nombreTerapeuta = terapeutas.getNombre() +" "+ terapeutas.getApellido();
                                Citas citas = new Citas(numDNI, personaEncontrada.getNombre(), personaEncontrada.getApellido(), fechaHora,nombreTerapeuta);
                                cerfid.agregarCita(citas);
                            }

                        } else {
                            System.out.println("Error");
                        }
                        break;

                    case 4:

                        List<Paciente> listaPacientesRegistrados = cerfid.obtenerPacientes();
                        if (listaPacientesRegistrados.isEmpty()) {
                            System.out.println("No hay pacientes registrados");
                            break;
                        }
                        System.out.println("===== Lista de Pacientes =====");
                        for (int i = 0; i < listaPacientesRegistrados.size(); i++) {
                            Paciente p = listaPacientesRegistrados.get(i);
                            System.out.println(p);

                        }
                        System.out.println("Ingrese el DNI del paciente: ");
                        numDNI = scan.next();
                        Personas pacienteSeleccionado = cerfid.buscarPorDNI(numDNI);

                        if (pacienteSeleccionado instanceof Paciente) {
                            Paciente pac = new Paciente(pacienteSeleccionado.getNumDNI(), pacienteSeleccionado.getNombre(), pacienteSeleccionado.getApellido(), ((Paciente) pacienteSeleccionado).getNoSesiones(), ((Paciente) pacienteSeleccionado).getPrescripcionMedica());
                            System.out.println("Detalles del Paciente");
                            System.out.println("Nombre: " + pacienteSeleccionado.getNombre());
                            System.out.println("Apellido: " + pacienteSeleccionado.getApellido());
                            System.out.println("Prescripción Médica: " + ((Paciente) pacienteSeleccionado).getPrescripcionMedica());
                            System.out.println("Número de Sesiones: " + ((Paciente) pacienteSeleccionado).getNoSesiones());
                        } else {
                            System.out.println("Error");
                        }
                        break;
                    case 5:
                        System.out.println("Ingresa el DNI del paciente para ver los detalles de la cita: ");
                        numDNI = scan.next();
                        Personas busquedaPaciente = cerfid.buscarPorDNI(numDNI);
                        if (busquedaPaciente instanceof Paciente) {
                            Paciente paciente = (Paciente) busquedaPaciente;
                            System.out.println("Paciente: " + paciente.getNombre() + " " + paciente.getApellido());
                        }
                        boolean tienesCitas = false;
                        System.out.println("Total de citas: " + cerfid.obtenerCitas().size());
                        for (Citas citas : cerfid.obtenerCitas()) {
                            if (citas.getNumDNI().equals(busquedaPaciente.getNumDNI())){
                                System.out.println("===== Citas Registradas =====");
                                System.out.println("Fecha y hora: "+ citas.getFechaHora());
                                System.out.println("DNI del paciente: "+ citas.getNumDNI());
                                System.out.println("Nombre del Paciente"+ citas.getNombre() +" "+ citas.getApellido());
                                System.out.println("Terapeuta Asignado: "+ citas.getTerapeutaAsignado());
                                tienesCitas = true;
                            }

                        }
                        break;
                    case 0:
                        System.out.println("Cerrando el program.....");
                        break;
                    default:
                        System.out.println("Opción marcada es invalida. Intenta nuevamente");
                }

            }

        }
    }


}
