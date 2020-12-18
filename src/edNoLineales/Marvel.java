package edNoLineales;

import java.util.Iterator;

public class Marvel {
	public static void main (String[] args) {
		Graph <Vertex, Edge> grafo = crearGrafo(readCsv());
		
		
		System.out.println("Número de personajes:");
		System.out.println(grafo.getN());
		System.out.println("Número de relaciones:");
		System.out.println(grafo.getM());
		
	}
	public static String [][] readCsv ()  {
		readCsv arrayCompleto = new readCsv ("/home/ivyn/Descargas/marvel-unimodal-edges.csv");
		String [][] csvLeido = arrayCompleto.getArrayCsv();
		return csvLeido;
	}
	
	public static Graph crearGrafo (String [][] arrayGrafo) {
		int j = 0;
		Graph <Vertex, Edge> gr = new TreeMapGraph <Vertex, Edge> ();
		String arrayAux[] = new String[327];
		int index0 = 0, index1 = 0;

		for (int i = 1; i < arrayGrafo.length; i++) {
			//personaje1
			for (int a = 0; a < arrayAux.length; a++) {
				if (arrayGrafo[i][0] == arrayAux[a]) {
					index0 = a; 
					break;
				}
				if (arrayAux[a] == null) {
					index0 = a;
					arrayAux[a] = arrayGrafo[i][0];
					break;
				}
			}
			//personaje2
			for (int a = 0; a < arrayAux.length; a++) {
				if (arrayGrafo[i][1] == arrayAux[a]) {
					index1 = a; 
					break;
				}
				if (arrayAux[a] == null) {
					index1 = a;
					arrayAux[a] = arrayGrafo[i][1];
					break;
				}
			}
			Element <String> personaje1 = new DecoratedElement<String>(String.valueOf(index0), arrayGrafo[i][0]);
			Element <String> personaje2 = new DecoratedElement<String>(String.valueOf(index1), arrayGrafo[i][1]);
			Edge<DecoratedElement> peso = new DecoratedElement (String.valueOf(j++), arrayGrafo[i][2]);
			gr.insertEdge(personaje1, personaje2, peso);
		}
		return gr;
	}
	
}
