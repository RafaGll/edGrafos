package edLineares2020_21;

/**
 * Interfaz para la creación de pilas estáticas y dinámicas
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 30/10/2020
 * @version 1.0
 */

public interface Stack <T> {
    public T pop ();
    public T top ();
    public void push (T element);
    public boolean isEmpty ();
    public int size ();
    public String toString();
}
