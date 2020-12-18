package edNoLineales;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class readCsv {

	String[][] arrayCsv;
	String ruta;
	
	
	public readCsv(String csv) {
		ruta = csv;
		arrayCsv = new String[9892][3];
	}
	
	public String[][] getArrayCsv() {
		File csv = new File(ruta);
		Scanner lectura = null;
		
		try {
			int i = 0;
			lectura = new Scanner (csv);
			while(lectura.hasNext()) {
				String line = lectura.nextLine().replaceAll(", "," ");
				StringTokenizer tokenizer = new StringTokenizer(line, ",");
				arrayCsv[i][0] = tokenizer.nextToken();
				arrayCsv[i][1] = tokenizer.nextToken();
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
