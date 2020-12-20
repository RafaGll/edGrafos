package edNoLineales;

import graphsDSESIUCLM.Edge;
import graphsDSESIUCLM.Element;

/**
 * Esta clase nos permite crear, modificar y obtener valores de distintos elementos.
 * 
 * @author Iván Cantalejo, Rafael González y Daniel Martín.
 * 
 * @since 04/12/2020
 *
 * @version 1.0
 */
public class DecoratedElement<T> implements Element, Edge {

	  private String ID;                 //Vertex ID
	  private T element;                 //Data Element
	  private boolean visited;          //Attribute to label the node as visited
	  private DecoratedElement<T> parent; // Vertex from which
	                                                // the current node is accessed
	  private int distance;    // Distance (in vertices) from the original node
	  private int peso;
	  /**
	   * Constructor de elementos decorados.
	   * @param key Cadena de caracteres que nos servirá como ID para identificar el elemento en cuestión.
	   * @param element Elemento que queremos crear.
	   */
	  public DecoratedElement(String key, T element) {
	    this.element = element;
	    ID = key;
	    visited = false;
	    parent = null;
	    distance = 0;
	  }// Cierre constructor
	  
	  /**
	   * getter para obtener el elemento deseado.
	   * @return Elemento de tipo T.
	   */
	  public T getElement() {
	    return element;
	  }// Cierre getter
	  
	  /**
	   * getter para saber si un elemento ha sido visitado o no.
	   * @return Valor booleano correspondiente:<ul>
	   * 			<li>true: El elemento ha sido visitado.</li>
	   * 			<li>false: El elemento no ha sido visitado.</li>
	   */
	  public boolean getVisited() {
	    return visited;
	  }// Cierre getter
	  
	  /**
	   * setter para marcar un elemento como visitado o no visitado.
	   * @param t Debe ser un boolean: <ul>
	   * 								<li>true: Elemento visitado.</li>
	   * 								<li>false: Elemento no visitado.</li>
	   */
	  public void setVisited(boolean t) {
	    visited = t;
	  }// Cierre setter
	  
	  /**
	   * getter para obtener el padre de un elemento decorado.
	   * @return Elemento T que corresponde al padre del indicado.
	   */
	  public DecoratedElement<T> getParent() {
	    return parent;
	  }// Cierre getter
	  
	  /**
	   * setter para guardar el padre del elemento.
	   * @param u Elemento padre a guardar.
	   */
	  public void setParent(DecoratedElement<T> u) {
	    parent = u;
	  }// Cierre setter
	  
	  /**
	   * getter para obtener la distancia almacenada en el elemento.
	   * @return número entero correspondiente a la distancia almacenada.
	   */
	  public int getDistance() {
	    return distance;
	  }// Cierre getter
	  
	  /**
	   * setter que almacena la distancia indicada respecto a otro elemento.
	   * @param d Valor entero correspondiente a la distancia.
	   */
	  public void setDistance(int d) {
	    distance = d;
	  }// Cierre setter

	  /**
	   * Método que nos indica si dos vertices son iguales (el mismo) teniendo en cuenta tanto su ID(key) como el elemento.
	   * @param n Vertice a comparar.
	   * @return Valor booleano correspondiente<ul>
	   * 			<li>true: Es el mismo vertice.</li>
	   * 			<li>false: No son el mismo vertice.</li>
	   */
	  public boolean equals (Object n) {
	    return (ID.equals(((DecoratedElement) n).getID())
	       && element.equals(((DecoratedElement<T>) n).getElement()));
	  }// Cierre del método
	  
	  /**
	   * Método que nos permite obtener una cadena de caracteres correspondiente a un elemento.
	   * @return Cadena de caracteres.
	   */
	  public String toString() {
	    return element.toString();   //element needs to override toString()
	  }// Cierre del método
	  
	  /**
	   * getter para obtener el ID de un elemento.
	   * @return Cádena de caracteres.
	   */
	  public String getID() {
	    return ID;
	  }
}//Cierre de clase