package edLineares2020_21;

/**
 * Esta clase sirve para crear listas dinámicas. También nos permite modificarlas
 * y obtener valores de ellas.
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 14/11/2020
 * @version 1.0
 */
public class ListDynamic <T> implements List <T>{
    private int N; // Elementos en la lista
    private Node <T> tail, head;
    /**
     * Constructor de listas dinámicas
     */
    public ListDynamic(Class<T[]> c) {
        N = 0;  // Variable encargada de guardar el número de elementos almacenados en la lista (Se inicializa a 0)
        head = new Node <T>(); //Nodo que contiene el primer elemento y apunta al siguiente (al crearlo no hay elementos por lo que no apunta a nada "=null")
        tail = new Node <T>(); //Nodo que contiene el último elemento añadido y por tanto no tiene nodo al que apuntar.
    }//Cierre del constructor

    /**
     * Método que introduce un elemento en la lista
     * @param element Elemento que el método se encargará de introducir en la lista
     */
    public void add(T element) {
        Node <T> aux = new Node<T>(element,null); // Creación de un nodo auxiliar que contendrá al elemento

        if (isEmpty())
            head=aux;
        else
            tail.setNext(aux);
        tail = aux;
        N++;
    }//Cierre del método

    /**
     * Método que comprueba si una lista está vacía
     * @return <ul>
     *          <li>true: La cola está vacia</li>
     *          <li>false: La cola contiene algún valor</li>
     *          </ul>
     */
    public boolean isEmpty() {
        return N == 0;
    }//Cierre del método

    /**
     * Método que devuelve el tamaño de la lista
     * @return Tamaño de la lista
     */
    public int size () {
        return N;
    }//Cierre del método

    /**
     * Método que comprueba si un elemento introducido se encuentra dentro de la lista y devuelve un valor booleano correspondiente
     * @param element Elemento a buscar dentro de la lista
     * @return <ul>
     *         <li>true: El elemento introducido se encuentra en la lista</li>
     *         <li>false: El elemento no se encuentra en la lista</li>
     *         </ul>
     */
    public boolean exists(T element) {
        boolean match = false; // Parametro que tomará el valor booleano correspondiente a devolver
        Node <T> aux = new Node<T>(element,null); // Nodo auxiliar en el que almacenamos el elemento y recorre toda la lista comparando con los distintos elementos
        if(!isEmpty()) {
            aux = head;
            while (aux != null && !match) {
                match = (element == aux.getElement());
                aux = aux.getNext();
            }
        }
        return match;
    }

    /**
     * Método que devuelve el elemento de la lista de una posición index dada.
     * Si la lista está vacía nos imprime un mensaje de error
     * @param index Posición en la que se encuentra el elemento deseado
     * @return Elemento almacenado en la posición indicada
     */
    public T get(int index) {

        if (isEmpty())
            System.out.println ("La lista está vacía");

        else {
            if(index<0 || index>N)
                System.out.println ("Valor de índice fuera de rango");
            else {
                Node <T> aux = new Node<T>(); // Nodo auxiliar que recorre la lista hasta el indice deseado
                aux = head;
                if (index > 0) {
                    for (int j = 0; j < index; j++) {
                        aux=aux.getNext();
                    }
                }
                return aux.getElement();
            }
        }
        return null;
    }//Cierre del método

    /**
     * Método que introduce un elemento en la posición index.
     * Para ello, el nodo que apuntaba a la posición index deberá apuntar ahora al nodo con el elemento introducido, el cual a su vez apuntará al nodo en index
     * @param element Elemento a introducir
     * @param index Posición en la que introducir el elemento
     */
    public void put(T element, int index) {
        if(index<0 || index>N)
            System.out.println ("Valor de índice fuera de rango");
        else {
            if(index == 0) {
                Node <T> aux2 = new Node<T>(element, head);
                head = aux2;
            }else {
                Node <T> aux = new Node<T>();
                aux = head;
                Node <T> prev = new Node<T>();
                for (int j = 0; j < index; j++) {
                    prev = aux;
                    aux = aux.getNext();
                }
                Node <T> aux2 = new Node<T>(element, aux);
                prev.setNext(aux2);
            }
            N++;
        }
    }//Cierre del método

    /**
     * Método que elimina el elemento que se encuentra en la posición dada. El nodo anterior a este apuntará al nodo posterior.
     * @param index posición del elemento a eliminar
     */
    public void remove(int index) {
        if (isEmpty()) {
            System.out.println ("La lista está vacía");
        }else {
            if(index<0 || index>N)
                System.out.println ("Valor de índice fuera de rango");
            else {
                if (index == 0) {
                    head = head.getNext();
                    if (head == null) {
                        tail = null;
                    }
                }else {
                    Node <T> aux = new Node<T>();
                    Node <T> prev = new Node<T>();
                    aux = head;
                    for (int j = 0; j < index ; j++) {
                        prev = aux;
                        aux = aux.getNext();
                    }	// aux = elemento a eliminar. prev = elemento previo
                    /**
                     * Si el elemento a eliminar corresponde al último de la lista, el elemento previo no apuntará a ningún nodo para así convertirlo
                     * en el tail, si no es el último de la lista, el nodo previo pasará a apuntar al siguiente del eliminado.
                     */
                    if (tail == aux) {
                        prev.setNext(null);
                        tail = prev;
                    }
                    else
                        prev.setNext(aux.getNext());
                }
                N--;
            }
        }
    }//Cierre del método

    /**
     * Método que devuelve la posición de un elemento dado, si no se encuentra el elemento, el valor retornará -1
     * @param elemLoc elemento a encontrar en la lista
     * @return Posición del elemento
     */
    public int locate(T elemLoc) {
        int index = -1;
        if (isEmpty()) {
            System.out.println ("La lista está vacía");
        }else {
            Node <T> aux = new Node<T>();
            aux = head;
            T element = null;
            for (int j = 0; j < N && index == -1; j++) {
                element = aux.getElement();
                if (element == elemLoc) {
                    index = j;
                }
                aux = aux.getNext();
            }
        }
        return index;
    }// Cierre del método

    /**
     * Método que imprime todos los valores de la lista o indica que está vacía
     * @return Valores de la pila
     */
    public String toString() {
        String values = ""; // En este String se almacenarán todos los elementos a imprimir
        Node <T> aux = head; // Creación de un nodo auxiliar que nos permitirán sacar uno a uno cada valor de la lista
        if(isEmpty()) {
            return "La cola está vacía";
        }else {
            while(aux!=null) {
                values+=aux.getElement();
                aux = aux.getNext();
            }
        }
        return values;
    }//Cierre del método

}// Cierre de la clase
