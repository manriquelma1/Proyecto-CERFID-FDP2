package CERFID;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CERFID cerfid = new CERFID();

        int opcion = -1;

        while (opcion != 0) {
            try {
                System.out.println("\n===== Menú Principal =====");
                System.out.println("1. Registrar Terapeuta");
                System.out.println("2. Registrar Paciente");
                System.out.println("3. Programar Cita");
                System.out.println("4. Verificar Historia Clínica");
                System.out.println("5. Reporte de Citas");
                System.out.println("0. Salir");
                System.out.print("\nSeleccionar opción: ");

                opcion = scan.nextInt();

                switch (opcion) {
                    case 1:
                        try {
                            System.out.print("DNI del Terapeuta: ");
                            String numDNI = scan.next();

                            // Verificar si ya existe
                            if (cerfid.buscarPorDNI(numDNI) != null) {
                                System.out.println("Error: El terapeuta con DNI " + numDNI + " ya se encuentra registrado.");
                                break;
                            }

                            System.out.print("Nombre del Terapeuta: ");
                            String nombre = scan.next();
                            System.out.print("Apellido del Terapeuta: ");
                            String apellidos = scan.next();
                            System.out.print("Código de Trabajador: ");
                            String codigoTrabajador = scan.next();

                            Terapeutas terapeuta = new Terapeutas(numDNI, nombre, apellidos, codigoTrabajador);
                            cerfid.agregarpersonas(terapeuta);
                            System.out.println("Terapeuta registrado exitosamente.");
                        } catch (Exception e) {
                            System.out.println("Error al registrar terapeuta: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("DNI del Paciente: ");
                            String numDNI = scan.next();

                            // Verificar si ya existe
                            if (cerfid.buscarPorDNI(numDNI) != null) {
                                System.out.println("Error: El paciente con DNI " + numDNI + " ya se encuentra registrado.");
                                break;
                            }

                            System.out.print("Nombre del Paciente: ");
                            String nombre = scan.next();
                            System.out.print("Apellido del Paciente: ");
                            String apellidos = scan.next();
                            scan.nextLine(); // Consumir el salto de línea
                            System.out.print("Prescripción Médica: ");
                            String prescripcionMedica = scan.nextLine();
                            System.out.print("Número de Sesiones: ");
                            int noSesiones = scan.nextInt();

                            Paciente paciente = new Paciente(numDNI, nombre, apellidos, noSesiones, prescripcionMedica);
                            cerfid.agregarpersonas(paciente);
                            System.out.println("Paciente registrado exitosamente.");
                        } catch (Exception e) {
                            System.out.println("Error al registrar paciente: " + e.getMessage());
                        }
                        break;


                    case 3:
                        try {
                            System.out.print("Fecha y hora de la cita (ej. 2024-11-09 10:15): ");
                            scan.nextLine(); // Consumir el salto de línea
                            String fechaHora = scan.nextLine();

                            // Validar formato de fecha y hora
                            LocalDateTime fechaCita;
                            try {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                fechaCita = LocalDateTime.parse(fechaHora, formatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Error: Formato de fecha y hora no válido. Use 'yyyy-MM-dd HH:mm'.");
                                break;
                            }

                            System.out.print("Ingrese el DNI del paciente (8 dígitos): ");
                            String numDNI = scan.next();

                            // Validar formato del DNI
                            if (!numDNI.matches("\\d{8}")) {
                                System.out.println("Error: El DNI debe contener exactamente 8 dígitos.");
                                break;
                            }

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
                                    System.out.println(i + ": " + t);
                                }

                                System.out.print("Seleccione el número del terapeuta: ");
                                int seleccionarTerapeuta;
                                if (scan.hasNextInt()) {
                                    seleccionarTerapeuta = scan.nextInt();
                                } else {
                                    System.out.println("Error: Debe ingresar un número válido.");
                                    scan.next(); // Consumir entrada inválida
                                    break;
                                }

                                // Validar que el número seleccionado esté dentro de los límites de la lista
                                if (seleccionarTerapeuta < 0 || seleccionarTerapeuta >= terapeutasDisponibles.size()) {
                                    System.out.println("Error: Selección fuera de rango.");
                                    break;
                                }

                                Terapeutas terapeuta = terapeutasDisponibles.get(seleccionarTerapeuta);
                                String nombreTerapeuta = terapeuta.getNombre() + " " + terapeuta.getApellido();
                                Citas cita = new Citas(numDNI, personaEncontrada.getNombre(), personaEncontrada.getApellido(), fechaHora, nombreTerapeuta);
                                cerfid.agregarCita(cita);

                                System.out.println("Cita programada exitosamente con el terapeuta " + nombreTerapeuta + " para " + fechaHora);
                            } else {
                                System.out.println("Error: Paciente no encontrado.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error al programar cita: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;


                    case 4:
                        try {
                            List<Paciente> listaPacientesRegistrados = cerfid.obtenerPacientes();
                            if (listaPacientesRegistrados.isEmpty()) {
                                System.out.println("No hay pacientes registrados.");
                                break;
                            }

                            System.out.println("===== Lista de Pacientes =====");
                            for (Paciente p : listaPacientesRegistrados) {
                                System.out.println(p);
                            }

                            System.out.print("Ingrese el DNI del paciente: ");
                            String numDNI = scan.next();
                            Personas pacienteSeleccionado = cerfid.buscarPorDNI(numDNI);

                            if (pacienteSeleccionado instanceof Paciente) {
                                Paciente pac = (Paciente) pacienteSeleccionado;
                                System.out.println("\n===== Detalles del Paciente =====");
                                System.out.println("Nombre: " + pac.getNombre());
                                System.out.println("Apellido: " + pac.getApellido());
                                System.out.println("Prescripción Médica: " + pac.getPrescripcionMedica());
                                System.out.println("Número de Sesiones: " + pac.getNoSesiones());

                            } else {
                                System.out.println("No se encontró un paciente con el DNI proporcionado.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error al verificar la historia clínica: " + e.getMessage());
                        }
                        break;

                    case 5:
                        try {
                            System.out.print("Ingrese el DNI del paciente para ver los detalles de las citas: ");
                            String numDNI = scan.next();
                            Personas busquedaPaciente = cerfid.buscarPorDNI(numDNI);

                            if (busquedaPaciente instanceof Paciente) {
                                System.out.println("Paciente: " + busquedaPaciente.getNombre() + " " + busquedaPaciente.getApellido());
                                boolean tieneCitas = false;

                                System.out.println("\n===== Citas Registradas =====");
                                for (Citas citas : cerfid.obtenerCitas()) {
                                    if (citas.getNumDNI().equals(busquedaPaciente.getNumDNI())) {
                                        System.out.println("Fecha y hora: " + citas.getFechaHora());
                                        System.out.println("DNI del paciente: " + citas.getNumDNI());
                                        System.out.println("Nombre del Paciente: " + citas.getNombre() + " " + citas.getApellido());
                                        System.out.println("Terapeuta Asignado: " + citas.getTerapeutaAsignado());
                                        System.out.println("==================================================");
                                        System.out.println("==================================================");
                                        tieneCitas = true;
                                    }
                                }

                                if (!tieneCitas) {
                                    System.out.println("No hay citas registradas para este paciente.");
                                }
                            } else {
                                System.out.println("No se encontró un paciente con el DNI proporcionado.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error al generar el reporte de citas: " + e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Cerrando el programa...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error en la entrada. Por favor, intente nuevamente.");
                scan.nextLine();
            }
        }
    }
}


