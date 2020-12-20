package edLineares2020_21;

/**
 * Clase que nos permite crear nodos así como modificarlos y obtener valores de ellos
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 29/10/2020
 * @version 1.0
 */
public class Node<T> {
    T element;	//Elemento de la pila
    Node<T> next;	//Creación del Nodo next

    /**
     * Método que crea un Nodo vacío que apunta a null
     */
    public Node () {
        next = null;
    }//Cierre del método

    /**
     * Método que crea un Nodo que apunta al elemento Nodo next y guarda el siguiente valor element
     * @param element Elemento al que se refiere el Nodo
     * @param next Nodo al que deberá apuntar
     */
    public Node (T element, Node<T> next) {
        this.element = element;
        this.next = next;
    }//Cierre del método

    /**
     * Método que devuelve el valor element de un nodo
     * @return Elemento del Nodo
     */
    public T getElement () {
        return element;
    }//Cierre del método

    /**
     * Método que devuelve el valor element del Nodo next
     * @return Elemento del Nodo next
     */
    public Node<T> getNext () {
        return next;
    }//Cierre del método

    /**
     * Método que inserta un valor element en un nodo
     * @param element Elemento a insertar en el nodo
     */
    public void setElement (T element) {
        this.element = element;
    }//Cierre del método

    /**
     * Método que indica a que nodo debe apuntar otro nodo
     * @param next Nodo al que debe apuntar
     */
    public void setNext (Node<T> next) {
        this.next = next;
    }//Cierre del método
}//Cierre de la clase

