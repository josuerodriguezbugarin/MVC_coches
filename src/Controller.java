import java.util.List;

/**
 * Clase que actúa como el Controlador en el patrón MVC (Modelo-Vista-Controlador).
 * Se encarga de coordinar el flujo de datos entre la interfaz de usuario (View)
 * y la lógica de negocio o almacenamiento (Model).
 * * @author Josué
 * @version 3.0
 */
public class Controller {

    /** Instancia global de la vista para gestionar la interfaz del menú. */
    static View miView = new View();

    /** Instancia del modelo que sirve como base de datos en memoria para los coches. */
    Model database = new Model();

    /**
     * Metodo principal de entrada de la aplicación.
     * Inicia la interfaz de usuario mostrando el menú principal.
     * * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        miView.menu();
    }

    /**
     * Solicita al modelo la creación de un nuevo coche y verifica si se ha guardado correctamente.
     * * @param modelo El modelo o marca del coche que se desea registrar.
     * @param matricula La matrícula única identificativa del coche.
     * @return {@code true} si el coche se creó y guardó correctamente en el sistema;
     * {@code false} en caso contrario.
     */
    public boolean AgregarCoche(String modelo, String matricula){
        Coche creado = database.crearCoche(modelo, matricula);
        if (creado == database.getCoche(matricula)) {
            return true;
        }
        return false;
    }

    /**
     * Intercepta la petición de la Vista para eliminar un coche del parking por su matrícula.
     * * @param matricula La matrícula enviada desde la Vista.
     * @return El objeto {@link Coche} eliminado para que la vista decida qué mostrar,
     * o {@code null} si el coche no existía.
     */
    public Coche atenderQuitarCoche(String matricula) {
        Coche cocheQuitado = database.quitarCoche(matricula);
        return cocheQuitado;
    }

    /**
     * Intercepta la petición de la Vista para hacer avanzar un coche pasándole los metros.
     * * @param matricula La matrícula del coche que debe avanzar.
     * @param metros Cantidad de metros que se sumarán al recorrido.
     * @return El objeto {@link Coche} actualizado, o {@code null} si no se encuentra.
     */
    public Coche atenderAvanzarCoche(String matricula, int metros) {
        Coche cocheActualizado = database.hacerAvanzarCoche(matricula, metros);
        return cocheActualizado;
    }

    /**
     * Intercepta la petición de la Vista para añadir litros de gasolina al depósito de un coche.
     * * @param matricula La matrícula del coche que va a repostar.
     * @param litros Cantidad de gasolina en litros que se desea cargar.
     * @return El objeto {@link Coche} actualizado, o {@code null} si no se encuentra en el sistema.
     */
    public Coche atenderCargarGasolina(String matricula, double litros) {
        Coche cocheActualizado = database.repostarCoche(matricula, litros);
        return cocheActualizado;
    }

    /**
     * Solicita al modelo que modifique la velocidad de un coche específico.
     * * @param matricula La matrícula del coche a modificar.
     * @param nuevaVelocidad La nueva velocidad que se le quiere asignar.
     * @return La velocidad final resultante tras la modificación.
     * @throws NullPointerException si la matrícula no corresponde a ningún coche registrado.
     */
    public int atenderCambiarVelocidad(String matricula, Integer nuevaVelocidad) {
        int velocidadResultante = database.cambiarVelocidad(matricula, nuevaVelocidad);
        return velocidadResultante;
    }

    /**
     * Solicita al modelo la velocidad actual de un vehículo concreto.
     * * @param matricula La matrícula del coche a consultar.
     * @return La velocidad actual del vehículo en km/h.
     * @throws NullPointerException si la matrícula no corresponde a ningún coche registrado.
     */
    public int atenderMostrarVelocidad(String matricula) {
        return database.getVelocidad(matricula);
    }

    /**
     * Obtiene la lista completa de vehículos estacionados directamente desde el modelo.
     * * @return Una interfaz {@link List} que contiene todos los objetos {@link Coche} en el parking.
     */
    public List<Coche> atenderMostrarLista() {
        return database.getListaParking();
    }
}