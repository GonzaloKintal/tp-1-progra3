package logica;

import java.util.List;

public class Juego {
	Tablero tablero;
	private int puntaje;
	
	private Archivo archivo;

	public Juego(int numCasillas) {
		this.tablero = new Tablero(numCasillas);
		this.tablero.agregarNumero();
		this.tablero.agregarNumero();
		this.archivo = new Archivo();
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
		archivo.escribirTxt(nombre, this.puntaje);
	}
	
	public List<Archivo.RankingEntry> leerRanking() {
		return archivo.leerRanking();
	}
	
	public boolean existeNombre(String nombre)  {
		return archivo.existeNombre(nombre);
	}

}
