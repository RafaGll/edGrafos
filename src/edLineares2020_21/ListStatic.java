package edLineares2020_21;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Esta clase sirve para crear listas estáticas. También nos permite modificarlas
 * y obtener valores de ellas.
 *
 * @author Iván Cantalejo, Rafael González y Daniel Martín. Universidad de Castilla la Mancha.
 *
 * @since 13/11/2020
 * @version 1.0
 */
public class ListStatic <T> implements  List <T>{
    private int N; //Tamaño de la lista
    private T L [];	//Creación de lista estática

    /**
     * Constructor de listas estáticas con un tamaño estandarizado de 10
     */
    public ListStatic (Class<T[]> c) {
        N = 0; // Variable encargada de almacenar el tamaño de la cola (Se inicializa a 1)
        L = c.cast(Array.newInstance (c.getComponentType(), 1));
    }//Cierre del constructor

    /**
     * Método que añade un elemento a la lista.
     * En el caso de que la lista esté llena, se redimensionará añadiendo el elemento
     * @param element Elemento a incluir en la lista
     */
    public void add (T element){
        try {
            L[N] = element;
            N++;
        }catch(java.lang.ArrayIndexOutOfBoundsException e){
            L = Arrays.copyOf(L, (N+1)); // Redimensión de la lista
            L[N] = element;
            N++;
        }
    }//Cierre del método

    /**
     * Método que comprueba si un elemento introducido se encuentra dentro de la lista
     * @param element Elemento a buscar en la lista
     * @return <ul>
     *         <li>true: El elemento introducido se encuentra en la lista</li>
     *         <li>false: El elemento no se encuentra en la lista</li>
     *         </ul>
     */
    public boolean exists(T element) {
        boolean match = false; // Variable tomará el valor boolean correspondiente
        if(!isEmpty()) {
            for (int i=0; i<=(N) && !match; i++){
                match=L[i]==element;
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
    public T get(int index){
        T element=null;
        if (isEmpty()) {
            System.out.println ("La lista está vacía");
        }else {
            if(index<0 || index>N)
                System.out.println ("Valor de índice fuera de rango");
            else
                element = L[index];

        }
        return element;
    }//Cierre del método

    /**
     * Método que comprueba si una lista está vacía y devuelve el valor booleano correspondiente
     * @return <ul>
     *          <li>true: La lista está vacia</li>
     *          <li>false: La lista contiene algún valor</li>
     *          </ul>
     */
    public boolean isEmpty() {
        boolean isEmpty = (N == 0);
        return isEmpty;
    }//Cierre del método

    /**
     * Método que devuelve el tamaño de la lista
     * @return Tamaño de la lista
     */
    public int size () {
        return N;
    }//Cierre del método

    /**
     * Método que introduce un elemento en la posición anterior a index, para ello tanto al elemento en index como a sus posteriores, les suma una posición.
     * Si la lista está llena, la redimiensiona y mete el valor en su posición correspondiente
     * @param element Elemento a introducir
     * @param index Posición siguiente a la que introduciremos el elemento
     */
    public void put(T element, int index){
        try {
            for(int j = N; j >= index; j--) {
                L[j] = L[j-1];
            }
            L [index] = element;
            N++;
        }
        catch(java.lang.ArrayIndexOutOfBoundsException e) {
            if (index < 0 || index > N) {
                System.out.println ("Valor de índice fuera de rango");
            }else {
                L = Arrays.copyOf(L, (N+1));
                for(int j = N; j >= index; j--) {
                    L[j] = L[j-1];
                }
                L [index] = element;
                N++;
            }
        }
    }//Cierre del método

    /**
     * Método que elimina el elemento que se encuentra en la posición dada y resta a todos los elementos posteriores una posición.
     * @param index posición del elemento a eliminar
     */
    public void remove(int index){
        if(isEmpty()) {
            System.out.println ("La lista está vacía");
        }else{

        }if (index<0 || index>N){
            System.out.println ("Valor de índice fuera de rango");
        }else {
            for (int j = index; j < N-1; j++) {
                L[j] = L[j+1];
            }
            N--;
        }
    }//Cierre del método

    /**
     * Método que devuelve la posición de un elemento dado. En caso de no encontrarlo, devuelve -1
     * @param element elemento a encontrar en la lista
     * @return Posición del elemento
     */
    public int locate (T element){
        int pos = -1; // Posición en la que se encuentra el elemento, su valor será -1 hasta encontrar el elemento
        if(isEmpty()) {
            System.out.println ("La lista está vacía");
        }else{
            for(int j = 0; (j <= N+1) && (pos == -1); j++) {
                if ( L [j] == element)
                    pos = j;
            }
        }
        return pos;
    }//Cierre del método

    /**
     * Método que imprime todos los elementos de la lista o indica que está vacía
     * @return Elementos de la lista
     */
    public String toString() {
        String values = ""; // En este String se almacenarán todos los elementos a imprimir
        int aux = 0; // Variable auxiliar que nos permite recorrer la lista
        if(isEmpty()) {
            return "La cola está vacía";
        }else {
            while(aux < N) {
                values+=L[aux++];
            }
        }
        return values;
    }//Cierre del método

}//Cierre de la clase
