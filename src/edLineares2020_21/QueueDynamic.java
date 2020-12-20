package edLineares2020_21;

/**
 * Esta clase sirve para crear colas dinámicas. También nos permite modificarlas
 * y obtener valores de ellas.
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 7/11/2020
 * @version 1.0
 */
public class QueueDynamic <T> implements Queue <T> {
    private int N;	// Elementos de la cola
    private Node <T> front, rear;

    /**
     * Constructor de colas dinámicas
     */
    public QueueDynamic(Class<T[]> c) {
        N = 0; // Variable encargada de guardar el número de elementos almacenados en la cola (Se inicializa a 0)
        front = new Node <T>(); // Nodo que contendrá el primer elemento añadido y apuntará al siguiente nodo
        rear = new Node <T>(); // Nodo que contendrá el último nodo añadido y por tanto no apunta a ningún nodo
    }//Cierre del constructor

    /**
     * Método que introduce un elemento en la cola
     * @param element Elemento que el método se encargará de meter en la cola
     */
    public void enqueue(T element) {
        Node <T> aux = new Node<T> (element, null); // Creación de nodo auxiliar que contendrá el elemento

        if (isEmpty())
            front = aux; // si la cola estaba vacía->aux=front=rear
        else
            rear.setNext(aux);
        rear=aux;
        N++;
    }//Cierre del método

    /**
     * Método que devuelve el elemento front de la cola y lo elimina.
     * En el caso de que la cola esté vacía, muestra un error y devuelve un elemento vacío
     * @return Elemento top de la pila
     */
    public T dequeue() {
        T element = null;
        if (isEmpty()) {
            System.out.println ("La cola está vacía");
            return element;
        }else {
            element = (T) front.getElement();
            front = front.getNext();
            N--;
            if(N == 0)
                rear = null;
            return element;
        }
    }//Cierre del método

    /**
     * Método que devuelve el elemento front de la cola sin eliminarlo.
     * En el caso de que la cola esté vacía, muestra un error y devuelve el elemento vacío
     * @return Elemento top de la pila
     */
    public T front() {
        T element = null;
        if (isEmpty())
            System.out.println ("La cola está vacía");
        else
            element = (T) front.getElement();
        return element;
    }//Cierre del método

    /**
     * Método que devuelve el tamaño de la cola
     * @return Tamaño de la cola
     */
    public int size () {
        return N;
    }//Cierre del método

    /**
     * Método que comprueba si una cola está vacía
     * @return <ul>
     *          <li>true: La cola está vacia</li>
     *          <li>false: La cola contiene algún valor</li>
     *          </ul>
     */
    public boolean isEmpty() {
        return N==0;
    }//Cierre del método

    /**
     * Método que imprime todos los valores de la cola o indica que está vacía
     * @return Valores de la cola
     */
    public String toString() {
        String values = ""; // En este String se almacenarán todos los elementos a imprimir
        Node <T> aux = front; // Creación de un nodo auxiliar que nos permitirán sacar uno a uno cada valor de la cola
        if(isEmpty())
            return "La cola está vacía";
        else {
            while(aux!=null) {
                values+=aux.getElement();
                aux = aux.getNext();
            }
        }
        return values;
    }//Cierre del método


}//Cierre de la clase
