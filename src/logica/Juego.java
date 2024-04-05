package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Juego {
	Tablero tablero;
	private int puntaje;
	
	private Archivo archivo;

	public Juego(int numCasillas) {
		this.tablero = new Tablero(numCasillas);
		this.tablero.agregarNumero();
		this.tablero.agregarNumero();
		this.archivo = new Archivo();
		System.out.println("Iniciando juego");
	}

	public int getTamañoTablero() {
		return this.tablero.getSize();
	}

	public boolean tableroTieneNumero(int pos1, int pos2) {
		return this.tablero.estaOcupado(pos1, pos2);
	}

	public int obtenerValor(int i, int j) {
		return this.tablero.obtenerValor(i, j);
	}

	public void agregarNumero() {
		this.tablero.agregarNumero();
	}

	public void moverHorizontal(int direccion) {
		this.tablero.moverHorizontal(direccion);
		puntaje = tablero.getPuntaje();
	}

	public void moverVertical(int direccion) {
		this.tablero.moverVertical(direccion);
		puntaje = tablero.getPuntaje();
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public boolean jugadorGano() {
		return tablero.hayGanador();
	}

	public boolean jugadorPerdio() {
		return tablero.hayPerdedor();
	}

	public boolean posibleMovimientoDerecha() {
		return tablero.posibleMovimientoDerecha();
	}

	public boolean posibleMovimientoIzquierda() {
		return tablero.posibleMovimientoIzquierda();
	}

	public boolean posibleMovimientoAbajo() {
		return tablero.posibleMovimientoAbajo();
	}

	public boolean posibleMovmientoArriba() {
		return tablero.posibleMovimientoArriba();
	}
	
	public void escribirDatosEnArchivo(String nombre) {
		archivo.escribirTxt(nombre + " " + this.puntaje);
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
