package edNoLineales;
import java.util.*;

import graphsDSESIUCLM.Edge;
import graphsDSESIUCLM.Element;
import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.TreeMapGraph;
import graphsDSESIUCLM.Vertex;
/**
 * Esta clase ejecuta los distintos apartados, haciendo uso de las distintas clases para la lectura del archivo, creación del grafo y lectura y escritura
 * de los elementos del grafo.
 * 
 * @author Iván Cantalejo, Rafael González y Daniel Martín.
 * 
 * @since 10/12/2020
 *
 * @version 4.0
 */
public class Marvel {
	/**
	 * Método main que se ejecuta al inicio del programa y que llama a otros métodos para la resolución de los apartados.
	 */
	public static void main (String[] args) {
		String direccion = "/home/rafagl/Documentos/Java/marvel-unimodal-edges.csv";
		Graph <Vertex, Edge> grafo = crearGrafo(readCsv(direccion));
		System.out.println("---------------------------------------");
		System.out.println("Apartado A:");
		System.out.println("Número de personajes:");
		System.out.println(grafo.getN());
		System.out.println("Número de relaciones:");
		System.out.println(grafo.getM());
		comparador(grafo, true, 0);
		
		System.out.println("---------------------------------------");
		System.out.println("Apartado B:");
		System.out.println("Camino más corto entre dos personajes introducidos.");
		vertices(grafo, true);
		
		resetear(grafo);
		
		System.out.println("---------------------------------------");
		System.out.println("Apartado C:");
		System.out.println("Equipo con dos personajes introducidos.");
		vertices(grafo, false);
	}// Cierre del método main
		
	/**
	 * Método que lee el archivo .csv.
	 * @param dir Directorio introducido como cadena de caracteres en el que se encuentra el archivo.
	 * @return Array multidimensional que contiene todos los elementos del archivo separados. 
	 */
	public static String [][] readCsv (String dir)  {
		readCsv arrayCompleto = new readCsv (dir);
		String [][] csvLeido = arrayCompleto.getArrayCsv();
		return csvLeido;
	}// Cierre del método
	
	/**
	 * Método que crea un grafo con los distintos elementos obtenidos del archivo .csv.
	 * @param arrayGrafo array multidimensional con todos los personajes y pesos.
	 * @return Grafo creado a partir del array introducido
	 */
	public static Graph crearGrafo (String [][] arrayGrafo) {
		int j = 0;
		Graph gr = new TreeMapGraph();
		for (int i = 1; i < arrayGrafo.length; i++) {
			Element <String> personaje1 = new DecoratedElement<String>(arrayGrafo[i][0], arrayGrafo[i][0]);
			Element <String> personaje2 = new DecoratedElement<String>(arrayGrafo[i][1], arrayGrafo[i][1]);
			Element <Integer> peso = new DecoratedElement<Integer>(String.valueOf(j++), Integer.parseInt(arrayGrafo[i][2]));
			gr.insertEdge(personaje1,personaje2,peso);
		}
		return gr;
	}// Cierre del método
	
	/**
	 * Método que obtiene los personajes (o personaje) con más o menos relaciones.
	 * @param gr Grafo con todos los elementos.
	 * @param opt Valor booleano para identificar que acción realizar<ul>
	 * 																	<li>true: Obtener los personajes con más relaciones</li>
	 * 																	<li>false: Obtener los personajes con menos relaciones</li>
	 * @param prev Si se desea obtener los personajes con más relaciones, tendrá un valor de 0. Si por el contrario deseamos obtener 
	 * los personajes con menos relaciones, almacenará el número de relaciones máximo alcanzado al obtener los personajes con más relaciones.
	 */
	public static void comparador(Graph gr, boolean opt, int prev) {
		System.out.println();
		int elementosArray=0;
		String[] personajes= new String[3];
		Iterator<Vertex<Vertex>> ver = gr.getVertices();
		while(ver.hasNext()) {
			Vertex vertice = ver.next();
			int pesoAct = pesoPersonaje(gr, vertice);
				
			String persAct = vertice.getElement().toString();			
			if(pesoAct>prev && opt || pesoAct<prev && !opt) {
				prev=pesoAct;		
				for(int u=0; u<elementosArray;u++) personajes[u]=null;
				elementosArray=1;
				personajes[0]=persAct;
			}
			else if(pesoAct==prev) {
				try {
					personajes[elementosArray]=persAct;
				}catch(Exception ArrayIndexOutOfBoundsException) {
					personajes = Arrays.copyOf(personajes, elementosArray+3);
					personajes[elementosArray]=persAct;
				}
				elementosArray++;
			}
		}
		
		if(opt)System.out.println("Personajes con más relaciones:");
		else System.out.println("Personajes con menos relaciones:");
		for(String print:personajes) {
			if(print==null)continue;
			System.out.println("- "+print);
		}
		if(opt)comparador(gr, false, prev);
	}// Cierre del método
	
	/**
	 * Método que nos indica el número de interacciones totales de un personaje.
	 * @param gr Grafo con todos los elementos.
	 * @param vertice Personaje del cual queremos obtener sus relaciones.
	 * @return Valor entero correspondiente a las interacciones del personaje.
	 */
	public static int pesoPersonaje(Graph gr, Vertex vertice) {
		vertice=gr.getVertex(vertice.getID());
		
		Iterator<Edge<Edge>> edg=gr.incidentEdges(vertice);
		int pesoAct=0;
		while(edg.hasNext()) {
			Edge edge = edg.next();
			pesoAct+=Integer.parseInt(edge.getElement().toString());
		}
		return pesoAct;
	}// Cierre del método
	
	/**
	 * Método que nos permite leer dos personajes introducidos y averiguar si existen dentro de nuestro grafo.
	 * En el caso de introducir un personaje no existente se indicará el personaje erroneo y se pedirá introducir de nuevo ambos.
	 * @param gr Grafo con todos los elementos.
	 * @param opt Valor booleano para identificar que acción realizar<ul>
	 * 																<li>true: Camino más corto que relaciona a dos personajes</li>
	 * 																<li>false: Camino con límite de interacciones entre dos personajes para formar un equipo</li>
	 */
	public static void vertices(Graph gr, boolean opt) {
		Scanner lectura = new Scanner(System.in);
		System.out.print("Introducir nombre de un personaje: ");
		String personaje1 =lectura.nextLine();
		System.out.print("Introducir nombre de otro personaje: ");
		String personaje2 =lectura.nextLine();
		//lectura.close();
		
		Vertex name1=gr.getVertex(personaje1);
		Vertex name2=gr.getVertex(personaje2);
		if(name1==null||name2==null) {
			if(name1==null)System.out.println("Personaje 1 no existe.");
			if(name2==null)System.out.println("Personaje 2 no existe.");
			System.out.println("Pruebe otra vez.");
			vertices(gr, opt);
			return;
		}
		busquedaCamino(gr, name1, name2, opt);
	}// Cierre del método
	
	/**
	 * Método que imprime el camino deseado o nos indica que no existe un camino entre ambos personajes.
	 * @param gr Grafo con todos los elementos.
	 * @param name1 Personaje en el que iniciaremos la busqueda.
	 * @param name2 Personaje a encontrar
	 * @param opt Valor booleano para identificar que acción realizar<ul>
	 * 																<li>true: Búsqueda BFS para obtener el camino más corto</li>
	 * 																<li>false: Búsqueda DFS para obtener el equipo con los dos personajes</li>
	 */
	public static void busquedaCamino(Graph gr, Vertex name1, Vertex name2, boolean opt) {
		DecoratedElement node = null;	
		boolean noPath;
	    Vertex<DecoratedElement> aux, s = null, t = null;
	    Vertex v;
	    Stack<Vertex> p1 = new Stack(), p2 = new Stack();
	    Stack <DecoratedElement> sp = new Stack();
	    
	    if(opt) {
	    	node = BFS(gr, name1, name2);

		    if (node.getParent() == null)System.out.println("\nNo se ha encontrado un camino entre los personajes.");
		    else{
		    	while (node.getParent() != null) {
		    		sp.push(node);
		    		node = node.getParent();
		    	}
		    	sp.push(node);

		    	int size = sp.size();
		    	for (int i = 0; i<size; i++){
		    		node = sp.pop();
		    		System.out.print(node.getElement().toString() + " -- ");
		    	}
		    	System.out.println(" Distancia = "+ node.getDistance());
		     }
	    }
	    else {
		    p1.push(name1);
		    noPath = DFS(gr, name1, name2, p1);
		    if (!noPath) {
		    	while(!p1.isEmpty()) p2.push(p1.pop());
		    	System.out.println("\nEquipo:");
		    	while (!p2.isEmpty()) {
		    		v = p2.pop();
		    		System.out.println("- " +v.getElement());
		    	}
		    } 
		    else System.out.println("\nNo se ha encontrado ningún equipo para esos personajes.");
	    }
	}// Cierre del método

	/**
	 * Método que realiza la búsqueda en anchura (BFS) para encontrar el cámino más corto que relaciona dos peronajes.
	 * @param g Grafo con todos los elementos.
	 * @param s Vertice(personaje) en el que se inicia la busqueda
	 * @param t Vertice(personaje) en el que debe finalizar la busqueda
	 * @return Valor Elemento decorado en el que finaliza la busqueda
	 */
	public static DecoratedElement BFS(Graph g,Vertex s,Vertex t) {
		Queue<Vertex<DecoratedElement>> q = new LinkedList();
		boolean noEnd = true;
		Vertex<DecoratedElement> u, v = null;
		Edge e;
		Iterator<Edge> it;
		
		((DecoratedElement) s.getElement()).setVisited(true);
		q.offer(s);
		
		while (!q.isEmpty() && noEnd) {
			u = q.poll();
			it = g.incidentEdges(u);
			while (it.hasNext() && noEnd) {
				e = it.next();
				int e2 = Integer.parseInt(e.getElement().toString());
				v = g.opposite(u, e);
				if (!(v.getElement()).getVisited()) {
					(v.getElement()).setVisited(true);
					(v.getElement()).setParent(u.getElement());
					(v.getElement()).setDistance(((u.getElement()).getDistance()) + 1);
					q.offer(v);
					noEnd = !(v.getElement().equals(t.getElement()));
				}
				
			}
		}
		if (noEnd)v.getElement().setParent(null);
		return v.getElement();
	}// Cierre del método
	
	/**
	 * Método que realiza la búsqueda en profundidad (DFS) de forma recursiva para encontrar un equipo con los dos personajes indicados.
	 * @param g Grafo con todos los elementos.
	 * @param v Vertice(personaje) en el que se inicia la busqueda
	 * @param z Vertice(personaje) en el que debe finalizar la busqueda
	 * @param sp Pila en la que se almacenan los vertices por los que pasa (miembros del equipo)
	 * @return Valor booleano que nos permite averiguar cuando termina la recursividad del método así como la existencia de un cámino o no<ul>
	 * 																							<li>true: Existe un camino entre ambos vértices.</li>
	 * 																							<li>false: No existe ningún camino entre los vertices.</li>
	 */
	public static boolean DFS(Graph g,Vertex v,Vertex z,Stack<Vertex> sp) {
		boolean noEnd = !v.equals(z);
		Edge e;
		Iterator<Edge<DecoratedElement>> it;
		Vertex<DecoratedElement> w;

		((DecoratedElement) v.getElement()).setVisited(true);

		it = g.incidentEdges(v);
		while (it.hasNext() && noEnd) {
			e = it.next();
			int e2 = Integer.parseInt(e.getElement().toString());
			if(e2>9)continue;
			else {
				w = g.opposite(v, e);
				if (!w.getElement().getVisited()) {
					sp.push(w);
					noEnd = DFS(g, w, z, sp);
					if (noEnd) sp.pop();
				}
			}
		}
		return noEnd;
	}// Cierre del método

	/**
	 * Método que establece todos los elementos del grafo como no visitados y devuelve la distancia a 0.
	 * @param gr Grafo con todos los elementos
	 */
	public static void resetear(Graph gr) {
		Iterator<Vertex<Vertex>> it;
		it=gr.getVertices();
		Vertex<Vertex> P;
		while(it.hasNext()) {
			P=it.next();
			((DecoratedElement) P.getElement()).setVisited(false);
			((DecoratedElement) P.getElement()).setDistance(0);
		}
	}
	
}// Cierre de la clase

