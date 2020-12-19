package edNoLineales;

import java.util.*;

public class Marvel {
	public static void main (String[] args) {
		Graph <Vertex, Edge> grafo = crearGrafo(readCsv());
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
		String[] personajes;
		camino(grafo);
	}
	
	public static void camino(Graph gr) {
		String[] personajes= new String[3];
		Scanner lectura = new Scanner(System.in);
		System.out.print("Introducir nombre de un personaje: ");
		String personaje1 =lectura.nextLine();
		System.out.print("Introducir nombre de otro personaje: ");
		String personaje2 =lectura.nextLine();
		lectura.close();
		
		Vertex name1=gr.getVertex(personaje1);
		Vertex name2=gr.getVertex(personaje2);
		if(name1==null||name2==null) {
			if(name1==null)System.out.println("Personaje 1 no existe.");
			if(name2==null)System.out.println("Personaje 2 no existe.");
			System.out.println("Pruebe otra vez");
			camino(gr);
			return;
		}
		bfs(gr, name1, name2);
	}
	
	public static void bfs(Graph gr, Vertex name1, Vertex name2) {
	    DecoratedElement startNode, endNode, nx, node = null;
	    boolean bool1 = true, bool2 = true;
	    int size;
	    Vertex<DecoratedElement> aux, s = null, t = null;
	    Stack <DecoratedElement> sp = new Stack();
	    Iterator<Vertex<DecoratedElement>> it;


	    // Start and last vertices
	    startNode = new DecoratedElement(name1.getID(), name1.getID());
	    endNode = new DecoratedElement(name2.getID(), name2.getID());

	    it = gr.getVertices();
	    while (it.hasNext() && (bool1 || bool2)) {
	    	aux = it.next();
	    	nx = aux.getElement();
	    	if (nx.equals(startNode)) {
	    		s = aux;
	    		bool1 = false;
	    	}
	    	if (nx.equals(endNode)) {
	    		t = aux;
	    		bool2 = false;
	    	}
	    }
	    node = pathBFS(gr, s, t);

	    if (node.getParent() == null)System.out.println("\nNo se ha encontrado un camino");
	    else {
	    	while (node.getParent() != null) {
	    		sp.push(node);
	    		node = node.getParent();
	    	}
	    	sp.push(node);

	    	size = sp.size();
	    	for (int i = 0; i<size; i++){
	    		node = sp.pop();
	    		System.out.print(node.getElement().toString() + " -- ");
	    	}
	    	System.out.print(" Distancia="+ node.getDistance());
	     }
	   
	  }

	public static DecoratedElement pathBFS(Graph g,Vertex s,Vertex t) {
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
	}

	public static void comparador(Graph gr, boolean opt, int prev) {
		System.out.println();
		int elementosArray=0;
		String[] personajes= new String[3];
		Iterator<Vertex<Vertex>> ver = gr.getVertices();
		while(ver.hasNext()) {
			Vertex vertice = ver.next();
			vertice=gr.getVertex(vertice.getID());
			
			Iterator<Edge<Edge>> edg=gr.incidentEdges(vertice);
			int pesoAct=0;
			while(edg.hasNext()) {
				Edge edge = edg.next();
				pesoAct+=Integer.parseInt(edge.getElement().toString());
			}
			
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
	}
	
	public static String [][] readCsv ()  {
		readCsv arrayCompleto = new readCsv ("/home/rafagl/Documentos/Java/marvel-unimodal-edges.csv");
		String [][] csvLeido = arrayCompleto.getArrayCsv();
		return csvLeido;
	}
	
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
	}
	
	public static void printGraph(Graph gr){
		int y = 0;
		  Vertex [] v;
		  Iterator<Edge> ite;
		  System.out.println("Graph");
		  ite = gr.getEdges();
		  while (ite.hasNext()) {
		    v = gr.endVertices(ite.next());
		    System.out.print(v[0].getElement().toString());
		    System.out.println("-" + v[1].getElement().toString() + "//");
		    System.out.println("-" + v[2].getID().toString() + "//");
		    //System.out.println("//"+v[0].getElement().toString()+"--"+v[1].getElement().toString()+"--"+v[2].getElement().toString());
		  }
	}
	
}

