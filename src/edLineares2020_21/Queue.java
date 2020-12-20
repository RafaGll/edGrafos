package edLineares2020_21;

/**
 * Interfaz para la creación de colas estáticas y dinámicas
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 6/11/2020
 * @version 1.0
 */
public interface Queue <T> {
    public void enqueue(T element);
    public T dequeue();
    public T front();
    public boolean isEmpty();
    public int size();
    public String toString();
}
