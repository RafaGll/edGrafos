package edLineares2020_21;

/**
 * Esta clase sirve para crear pilas dinámicas. También nos permite modificarlas
 * y obtener valores de ellas.
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 31/10/2020
 * @version 1.0
 */
public class StackDynamic <T> implements Stack <T>{
    private int N;	// Elementos de la pila
    private Node<T> top = null;	// Nodo top que contendrá el ultimo elemento añadido y apuntará al nodo anterior
    /**
     * Constructor de pilas dinámicas
     */
    public StackDynamic (Class<T[]> c) {
        N = 0; // Variable encargada de guardar el número de elementos almacenados en la pila (Se inicializa a 0)
        top = new Node <T>(); // Creación del nodo top
    }//Cierre del constructor

    /**
     * Método que devuelve el elemento top de la pila y lo elimina.
     * En el caso de que la pila esté vacía, muestra un error
     * @return Elemento top de la pila
     */
    public T pop(){
        T element = null;
        if(isEmpty())
            System.out.println ("La pila está vacía");
        else {
            element = (T) top.getElement();
            top = top.getNext();
            N--;
        }
        return element;
    }//Cierre del método


    /**
     * Método que introduce un nuevo elemento en la pila
     * @param element Elemento que el método se encargará de introducir
     */
    public void push (T element) {
        Node<T> aux = new Node <T>(element, top); // Creación de un nodo auxiliar conteniendo el elemento y apuntando al nodo anterior
        top=aux;
        N++;
    }//Cierre del método

    /**
     * Método que devuelve el elemento top de la pila.
     * En el caso de que la pila esté vacía, muestra un error
     * @return Elemento top de la pila
     */
    public T top(){
        T element = null;
        if (isEmpty())
            System.out.println ("La pila está vacía");
        else
            element = (T) top.getElement();
        return element;
    }//Cierre del método


    /**
     * Método que devuelve el tamaño de la pila
     * @return Tamaño de la pila
     */
    public int size () {
        return N;
    }//Cierre del método

    /**
     * Método que comprueba si una pila está vacía
     * @return <ul>
     *          <li>true: La pila está vacia</li>
     *          <li>false: La pila contiene algún valor</li>
     *          </ul>
     */
    public boolean isEmpty () {
        boolean isEmpty = (N==0);
        return isEmpty;
    }//Cierre del método

    /**
     * Método que imprime todos los elementos de la pila o indica que está vacía
     * @return Valores de la pila
     */
    public String toString() {
        String values = ""; // En este String se almacenarán todos los elementos a imprimir
        Node <T> aux = top; // Creación de un nodo auxiliar que nos permitirán sacar uno a uno cada valor de la pila
        if(isEmpty()) {
            return "La pila está vacía";
        }else {
            while(aux.getElement()!=null) {
                values+=aux.getElement();
                aux = aux.getNext();
            }
        }
        return values;
    }//Cierre del método
}//Cierre de la clase
