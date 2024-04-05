package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Archivo {
	
	String directorio;
	
	public Archivo() {
		File directoryPath = new File("data/ranking.txt");
		this.directorio = directoryPath.getAbsolutePath();
	}
	

	public String leerTxt() {
		String texto = "";
		try {
			BufferedReader bf = new BufferedReader(new FileReader(this.directorio));
			String temp = "";
			String bfRead;

			while ((bfRead = bf.readLine()) != null) {
				temp = temp + bfRead + "\n";
			}

			texto = temp;
			bf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return texto;
	}
	
	public void escribirTxt(String text) {
		try {
			PrintWriter salida = new PrintWriter(new FileWriter(this.directorio, true));
			salida.println(text);
			salida.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
