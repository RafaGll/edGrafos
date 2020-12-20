package edNoLineales;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Esta clase nos permite leer el archivo .csv y guardar los distintos personajes y el peso en un array multidimensional.
 * 
 * @author Iván Cantalejo, Rafael González y Daniel Martín.
 * 
 * @since 05/12/2020
 *
 * @version 1.0
 */
public class readCsv {

	String[][] arrayCsv;
	String ruta;
	
	
	public readCsv(String csv) {
		ruta = csv;
		arrayCsv = new String[9892][3];
	}
	
	/** 
	 * Método que nos permite guardar en elementos distintos del array cada personaje y peso. Utilizamos un StringTokenizer para marcar "," como separador de elementos.
	 * También se reemplaza ", " cuando va dentro de un nombre por otro caracter para no dar problemas en la separación de elementos y se vuelve a colocar al guardarlo en el array.
	 * @return Array multidimensional almacenando todos los elementos del archivo .csv.
	 */
	public String[][] getArrayCsv() {
		File csv = new File(ruta);
		Scanner lectura = null;
		
		try {
			int i = 0;
			lectura = new Scanner (csv);
			while(lectura.hasNext()) {
				String line = lectura.nextLine().replaceAll(", ","¡");
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				arrayCsv[i][0] = tokenizer.nextToken().replaceAll("¡", ", ");
				arrayCsv[i][1] = tokenizer.nextToken().replaceAll("¡", ", ");
				arrayCsv[i][2] = tokenizer.nextToken();
				i++;
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
		
		lectura.close();		
		return arrayCsv;
	}
}
