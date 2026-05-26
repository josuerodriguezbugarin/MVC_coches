import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de manejar y gestionar los datos del sistema.
 * Actúa como el repositorio centralizado (simulando una base de datos en memoria)
 * para administrar los vehículos estacionados en el parking.
 * * @author Josué
 * @version 1.0
 */
public class Model {

    /** Lista estática que almacena todos los coches que se encuentran en el parking. */
    static ArrayList<Coche> parking = new ArrayList<>();

    /**
     * Crea un nuevo coche y lo registra dentro de la lista del parking.
     * * @param modelo El modelo o marca del coche a registrar.
     * @param matricula El identificador único del coche (matrícula).
     * @return El objeto {@link Coche} que ha sido creado y almacenado.
     */
    public Coche crearCoche(String modelo, String matricula){
        Coche aux = new Coche(modelo, matricula);
        parking.add(aux);
        return aux;
    }

    /**
     * Busca un coche específico dentro del parking utilizando su matrícula.
     * * @param matricula La matrícula del coche que se desea buscar.
     * @return El objeto {@link Coche} que coincide con la matrícula,
     * o {@code null} si no se encuentra ningún resultado.
     */
    public Coche getCoche(String matricula){
        Coche aux = null;
        for (Coche e: parking) {
            if (e.matricula.equals(matricula)) {
                aux = e;
            }
        }
        return aux;
    }

    /**
     * Elimina un coche del parking a partir de su matrícula si este existe.
     * * @param matricula La matrícula del coche que se desea retirar.
     * @return El objeto {@link Coche} que ha sido eliminado,
     * o {@code null} si el coche no estaba registrado.
     */
    public Coche quitarCoche(String matricula) {
        Coche cocheEncontrado = getCoche(matricula);
        if (cocheEncontrado != null) {
            parking.remove(cocheEncontrado);
            return cocheEncontrado;
        }
        return null;
    }

    /**
     * Modifica la velocidad actual de un coche registrado.
     * * @param matricula La matrícula del coche a modificar.
     * @param v La nueva velocidad que se le asignará al vehículo.
     * @return La nueva velocidad asignada tras realizar el cambio.
     * @throws NullPointerException si no se encuentra ningún coche con la matrícula proporcionada.
     */
    public int cambiarVelocidad(String matricula, Integer v) {
        getCoche(matricula).velocidad = v;
        return getCoche(matricula).velocidad;
    }

    /**
     * Incrementa en una unidad el kilometraje acumulado de un coche específico.
     * * @param matricula La matrícula del coche que va a avanzar.
     * @return El objeto {@link Coche} con su kilometraje actualizado,
     * o {@code null} si el coche no existe.
     */
    public Coche hacerAvanzarCoche(String matricula) {
        Coche aux = getCoche(matricula);
        if (aux != null) {
            aux.km++;
        }
        return aux;
    }

    /**
     * Obtiene la velocidad actual de un coche según su matrícula.
     * * @param matricula La matrícula del coche a consultar.
     * @return La velocidad actual del vehículo en km/h.
     * @throws NullPointerException si el coche con la matrícula dada no existe en el registro.
     */
    public int getVelocidad(String matricula) {
        return getCoche(matricula).velocidad;
    }

    /**
     * Devuelve la lista completa de todos los coches que están actualmente en el parking.
     * * @return Una interfaz {@link List} que contiene los objetos {@link Coche} registrados.
     */
    public List<Coche> getListaParking() {
        return parking;
    }
}