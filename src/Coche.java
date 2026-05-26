/**
 * Representa un vehículo terrestre de tipo Coche.
 * Permite gestionar la información básica del automóvil, como su modelo,
 * matrícula, velocidad actual, el kilometraje acumulado y el nivel de combustible.
 * * @author Josué
 * @version 3.0
 */
public class Coche {

    /** La matrícula identificativa del coche. */
    public String matricula;

    /** El modelo o nombre comercial del coche. */
    public String modelo;

    /** La velocidad actual del coche en km/h. */
    public Integer velocidad;

    /** El kilometraje total recorrido por el coche en kilómetros. */
    public double km;

    /** Los litros de gasolina disponibles en el depósito del coche. */
    public double gasolina;

    /**
     * Constructor para instanciar un nuevo Coche con sus datos iniciales.
     * Al crearse, el coche inicia estacionado (velocidad 0), sin recorrido (0.0 km)
     * y con el depósito inicializado a 50.0 litros de gasolina.
     * * @param modelo El modelo o marca del vehículo (ej. "Seat Ibiza").
     * @param matricula La matrícula del vehículo (ej. "1234-BBB").
     */
    public Coche(String modelo, String matricula) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.velocidad = 0;
        this.km = 0.0;
        this.gasolina = 50.0;
    }

    /**
     * Incrementa los litros de combustible disponibles en el depósito del coche.
     * * @param litros Cantidad de litros de gasolina a introducir en el tanque.
     */
    public void recargarCombustible(double litros) {
        if (litros > 0) {
            this.gasolina += litros;
        }
    }

    /**
     * Hace avanzar al coche una cantidad de metros. Calcula el consumo de combustible
     * basándose en los metros recorridos y la velocidad actual del vehículo.
     * Si el coche se queda sin gasolina durante el trayecto, detiene su marcha.
     * * @param metros Cantidad de metros enteros que se desean avanzar.
     */
    public void acumularMetros(int metros) {
        if (metros <= 0) return;

        double consumo = (metros * this.velocidad) / 100000.0;

        if (this.gasolina >= consumo) {
            this.gasolina -= consumo;
            this.km += (metros / 1000.0);
        } else {
            if (this.velocidad > 0) {
                double metrosPosibles = (this.gasolina * 100000.0) / this.velocidad;
                this.km += (metrosPosibles / 1000.0);
            }
            this.gasolina = 0.0;
            this.velocidad = 0;
        }
    }
}

