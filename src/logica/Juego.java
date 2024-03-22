package logica;

//import logica.Tablero;

public class Juego {
	Tablero tablero;
	private int puntaje;
	
	public Juego(){
		this.tablero = new Tablero(4);
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

	public void moverHorizontal() {
		this.tablero.moverNumerosHorizontal();
		
		puntaje = tablero.getPuntaje();
	}
	
	public int getPuntaje() {
		return this.puntaje;
	}
	
}
