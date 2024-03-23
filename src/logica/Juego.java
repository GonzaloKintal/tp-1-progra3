package logica;

//import logica.Tablero;

public class Juego {
	Tablero tablero;
	private int puntaje;
	
	public Juego(){
		this.tablero = new Tablero(4);
		this.tablero.agregarNumero();
		this.tablero.agregarNumero();
		System.out.println("Iniciando juego");
	}
	
	public int getTamañoTablero () {
		return 4;
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

	public void moverIzquierda() {
		this.tablero.moverNumerosIzquierda();	
		puntaje = tablero.getPuntaje();
	}
	
	public void moverDerecha() {
		this.tablero.moverNumerosDerecha();	
		puntaje = tablero.getPuntaje();
	}
	
	public void moverAbajo() {
		this.tablero.moverNumerosAbajo();
		
		puntaje = tablero.getPuntaje();
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
}
