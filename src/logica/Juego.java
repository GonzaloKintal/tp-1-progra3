package logica;

//import logica.Tablero;

public class Juego {
	Tablero tablero;
	
	public Juego(){
		this.tablero = new Tablero(4);
		System.out.println("Iniciando juego");
	}
	 
	public int dameNum() {
		return 2;
	}
	
	public int getTamañoTablero () {
		return 4;
	}
	
	public boolean tableroTieneNumero(int pos1, int pos2) {
		return this.tablero.estaOcupado(pos1, pos2);
	}

	public String obtenerValor(int i, int j) {
		return this.tablero.obtenerValor(i, j);
	}

	public void agregarNumero() {
		this.tablero.agregarNumero();
	}
}
