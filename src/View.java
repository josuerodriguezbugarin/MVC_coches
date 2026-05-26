import java.util.Scanner;
import java.util.List;

/**
 * Clase encargada de la interfaz de usuario del sistema (Vista).
 * Proporciona un menú textual por consola para interactuar con el usuario,
 * capturar sus entradas y mostrar los resultados devueltos por el controlador.
 * * @author Josué
 * @version 3.0
 */
public class View {

    /**
     * Despliega el menú principal interactivo en la consola.
     * Gestiona el bucle de la aplicación, lee las opciones elegidas por el usuario,
     * valida posibles errores de entrada de datos y delega las operaciones
     * de negocio en el controlador.
     */
    public void menu() {
        Controller c = new Controller();
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== GESTIÓN DE PARKING ===");
            System.out.println("1. Agregar Coche");
            System.out.println("2. Quitar Coche");
            System.out.println("3. Avanzar Coche (Metros)");
            System.out.println("4. Cargar Gasolina (Litros)"); // <- NUEVA OPCIÓN
            System.out.println("5. Añadir velocidad");
            System.out.println("6. Mostrar velocidad del coche");
            System.out.println("7. Coches en el parking");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(teclado.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Agregar Coche ---");
                    System.out.print("Introduce el modelo: ");
                    String modelo = teclado.nextLine();
                    System.out.print("Introduce la matricula: ");
                    String matricula = teclado.nextLine();
                    boolean Agregar = c.AgregarCoche(modelo, matricula);
                    if (Agregar) {
                        System.out.println("¡Coche aparcado con éxito!");
                    } else {
                        System.out.println("¡FALLO FATAL!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- Quitar Coche ---");
                    System.out.print("Introduce la matrícula del coche para quitar: ");
                    String matricula2 = teclado.nextLine();
                    Coche cocheQuitado = c.atenderQuitarCoche(matricula2);
                    if (cocheQuitado != null) {
                        System.out.println("Éxito: El coche con matrícula " + matricula2 + " ha sido retirado.");
                    } else {
                        System.out.println("Error: No se encontró ningún coche con la matrícula " + matricula2 + " en el parking.");
                    }
                    break;

                case 3:
                    System.out.println("\n--- Avanzar Coche ---");
                    System.out.print("Introduce la matrícula: ");
                    String matricula3 = teclado.nextLine();
                    System.out.print("Introduce los metros a avanzar: ");
                    int metros = 0;
                    try {
                        metros = Integer.parseInt(teclado.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Cantidad de metros no válida.");
                        break;
                    }

                    Coche avanza = c.atenderAvanzarCoche(matricula3, metros);
                    if (avanza != null) {
                        System.out.printf("¡Avanzado! Kilómetros totales: %.2f km | Gasolina restante: %.2f L\n", avanza.km, avanza.gasolina);
                        if (avanza.gasolina == 0 && avanza.velocidad == 0) {
                            System.out.println("¡AVISO: El coche se ha detenido por falta de combustible!");
                        }
                    } else {
                        System.out.println("El coche con matrícula " + matricula3 + " no está en el parking.");
                    }
                    break;

                case 4: // NUEVO CASO IMPLEMENTADO
                    System.out.println("\n--- Cargar Gasolina ---");
                    System.out.print("Introduce la matrícula: ");
                    String matriculaGas = teclado.nextLine();
                    System.out.print("Introduce los litros a cargar: ");
                    double litros = 0;
                    try {
                        litros = Double.parseDouble(teclado.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Cantidad de litros incorrecta.");
                        break;
                    }
                    Coche repostado = c.atenderCargarGasolina(matriculaGas, litros);
                    if (repostado != null) {
                        System.out.printf("¡Repostaje con éxito! Depósito actual: %.2f litros.\n", repostado.gasolina);
                    } else {
                        System.out.println("El coche con matrícula " + matriculaGas + " no está en el parking.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- Cambiar Velocidad ---");
                    System.out.print("Introduce la matrícula: ");
                    String matricula4 = teclado.nextLine();
                    System.out.print("Introduce la nueva velocidad: ");

                    Integer v = teclado.nextInt();
                    teclado.nextLine(); // Limpia el buffer

                    int velocidadNueva = c.atenderCambiarVelocidad(matricula4, v);
                    if (velocidadNueva != -1) {
                        System.out.println(" Velocidad actualizada con éxito a: " + velocidadNueva + " km/h.");
                    } else {
                        System.out.println(" Error: No se encontró ningún coche con la matrícula " + matricula4);
                    }
                    break;

                case 5 + 1: // Caso 6
                    System.out.println("\n--- Mostrar velocidad del coche ---");
                    System.out.print("Introduce la matrícula del coche: ");
                    String matricula5 = teclado.nextLine();
                    int velocidad = c.atenderMostrarVelocidad(matricula5);
                    System.out.println("Velocidad actual del coche: " + velocidad + " km/h.");
                    break;

                case 7:
                    System.out.println("\n--- Coches en el parking ---");
                    List<Coche> lista = c.atenderMostrarLista();
                    if (lista.isEmpty()) {
                        System.out.println("El parking está vacío.");
                    } else {
                        for (Coche ch : lista) {
                            System.out.printf("Modelo: %s | Matrícula: %s | Velocidad: %d km/h | Recorrido: %.2f km | Gasolina: %.2f L\n",
                                    ch.modelo, ch.matricula, ch.velocidad, ch.km, ch.gasolina);
                        }
                        System.out.println("----------------------------");
                    }
                    break;

                case 8:
                    System.out.println("\nSaliendo del programa... ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige un número del 1 al 8.");
                    break;
            }

        } while (opcion != 8);

        teclado.close();
    }
}