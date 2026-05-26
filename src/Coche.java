/**
 * Representa un vehículo terrestre de tipo Coche.
 * Permite gestionar la información básica del automóvil, como su modelo,
 * matrícula, velocidad actual y el kilometraje acumulado.
 * * @author Josué
 * @version 1.0
 */
public class Coche {

    /** La matrícula identificativa del coche. */
    public String matricula;

    /** El modelo o nombre comercial del coche. */
    public String modelo;

    /** La velocidad actual del coche en km/h. */
    public Integer velocidad;

    /** El kilometraje total recorrido por el coche. */
    public int km;

    /**
     * Constructor para instanciar un nuevo Coche con sus datos iniciales.
     * Al crearse, el coche inicia estacionado (velocidad 0) y sin recorrido (0 km).
     * * @param modelo El modelo o marca del vehículo (ej. "Seat Ibiza").
     * @param matricula La matrícula del vehículo (ej. "1234-BBB").
     */
    public Coche(String modelo, String matricula) {
        this.modelo = modelo;
        this.matricula = matricula;
        this.velocidad = 0;
        this.km = 0;
    }
}
