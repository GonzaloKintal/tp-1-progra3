package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
	
	public List<RankingEntry> leerRanking() {
		List<RankingEntry> ranking = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("data/ranking.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String nombre = parts[0];
                int puntaje = Integer.parseInt(parts[1]);
                ranking.add(new RankingEntry(nombre, puntaje));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ordenar el ranking de mayor a menor según el puntaje
        Collections.sort(ranking, Comparator.comparingInt(RankingEntry::getPuntaje).reversed());
        
        return ranking;
	}
	
	// Clase interna para representar una entrada en el ranking
    public static class RankingEntry {
        String nombre;
        int puntaje;

        public RankingEntry(String nombre, int puntaje) {
            this.nombre = nombre;
            this.puntaje = puntaje;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPuntaje() {
            return puntaje;
        }
    }

}
