package edLineares2020_21;

/**
 * Interfaz para la creación de listas estáticas y dinámicas con todos sus métodos
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 13/11/2020
 * @version 1.0
 */
public interface List <T> {
    public void add(T element);
    public boolean isEmpty();
    public boolean exists(T element);
    public int size();
    public T get(int n);
    public void put(T element, int n);
    public void remove(int n);
    public int locate(T element);
    public String toString();
}
