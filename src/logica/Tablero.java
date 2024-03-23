package logica;

import java.awt.Point;
import java.util.Random;

public class Tablero {
	int[][] matriz;
	int puntaje;

	public Tablero(int numCasillas) {
		this.matriz = new int[numCasillas][numCasillas];
	}

	public Tablero(int[][] mat) {
		this.matriz = mat;
	}

	private Point generarPosicion() {
		Point casilla = new Point();
		boolean flag = true;
		int posRandom1 = 0;
		int posRandom2 = 0;

		while (flag) {
			posRandom1 = dameNumeroRandom(this.getSize());
			posRandom2 = dameNumeroRandom(this.getSize());

			if (!estaOcupado(posRandom1, posRandom2)) {
				flag = false;
			}
		}
		casilla.setLocation(posRandom1, posRandom2);
		return casilla;
	}

	/**
	 * Devuelve un numero random entre 0 y el valor pasado como parametro sin incluirlo
	 * @param valor int
	 * @return int
	 */
	private int dameNumeroRandom(int valor) {
		Random rand = new Random();
		int random = rand.nextInt(valor);
		return random;
	}

	private int esDosOCuatro() {
		return dameNumeroRandom(10) > 7 ? 4 : 2;
	}

	public void moverNumerosDerecha() {
		for (int fila = 0; fila < matriz.length; fila++) {
			recorrerColumnasDerIzq(fila);
		}
	}

	private void recorrerColumnasDerIzq(int fila) {
		for (int col = matriz.length - 1; col >= 0; col--) {
			movimientosDerIzq(fila, col);
		}
	}

	private void movimientosDerIzq(int fila, int col) {
		boolean yaSeSumo = false;
		for (int contigua = col - 1; contigua >= 0; contigua--) {
			if (estaVacio(fila, col) && estaOcupado(fila, contigua))
				switchCeldas(fila, col, contigua);

			if (estaVacio(fila, contigua))
				continue;

			if (!celdasSonIguales(fila, col, contigua))
				break;

			if (celdasSonIguales(fila, col, contigua) && yaSeSumo)
				moverCasilleroAMiLado(fila, col, contigua, -1);

			sumarCeldas(fila, col, contigua);
			yaSeSumo = true;
		}
	}

	public void moverNumerosIzquierda() {
		for (int fila = 0; fila < matriz.length; fila++) {
			recorrerColumnasIzqDer(fila);
		}
	}

	private void recorrerColumnasIzqDer(int fila) {
		for (int col = 0; col < matriz.length; col++) {
			movimientosIzqDer(fila, col);
		}
	}

	private void movimientosIzqDer(int fila, int col) {
		boolean yaSeSumo = false;
		for (int contigua = col + 1; contigua < matriz.length; contigua++) {
			if (estaVacio(fila, col) && estaOcupado(fila, contigua))
				switchCeldas(fila, col, contigua);
			
			if (estaVacio(fila, contigua))
				continue;

			if (!celdasSonIguales(fila, col, contigua))
				break;

			if (celdasSonIguales(fila, col, contigua) && yaSeSumo)
				moverCasilleroAMiLado(fila, col, contigua, 1);

			sumarCeldas(fila, col, contigua);
			yaSeSumo = true;
		}
	}

	/**
	 * 
	 * @param fila
	 * @param col
	 * @param contigua
	 * @param lado     1 Para dejar el casillero a mi derecha, -1 para dejar el
	 *                 casillero a mi izquierda
	 */
	public void moverCasilleroAMiLado(int fila, int col, int contigua, int lado) {
		int aux = matriz[fila][contigua];
		matriz[fila][contigua] = 0;
		matriz[fila][col + lado] = aux;
	}

	public void moverNumerosAbajo() {
		for (int col = 0; col < matriz.length; col++) {
			recorrerFilasAbajoArriba(col);
		}
	}
	
	private void recorrerFilasAbajoArriba(int col) {
		for (int fila = matriz.length-1; fila >= 0; fila--) {
			movimientosAbajoArriba(fila, col);
		}
	}
	
	private void movimientosAbajoArriba(int fila, int col) {
		boolean yaSeSumo = false;
		System.out.println("Probando");
		for (int contigua = fila-1; contigua >= 0; contigua--) {
			if (estaVacio(fila, col) && estaOcupado(fila, contigua))
				switchCeldas(fila, col, contigua);

			if (estaVacio(fila, contigua))
				continue;

			if (!celdasSonIguales(fila, col, contigua))
				break;

			if (celdasSonIguales(fila, col, contigua) && yaSeSumo) {
//				moverCeldaAbajo(fila, col, contigua);
				int aux = matriz[fila][contigua];
				matriz[fila][contigua] = 0;
				matriz[fila][col - 1] = aux;
			}

			sumarCeldas(fila, col, contigua);
			yaSeSumo = true;
		}
	}

	private void moverCeldaAbajo(int fila, int col, int filaContigua) {
		matriz[fila][col] = matriz[filaContigua][col];
	    matriz[filaContigua][col] = 0;
	}

	private void sumarContiguasAbajo(int fila, int col, int filaContigua) {
		matriz[filaContigua][col] += matriz[fila][col];
		verificarCeldaArriba(fila, col);
	}

	private void verificarCeldaArriba(int fila, int col) {
		if (estaEnRango(fila - 1) && estaOcupado(fila - 1, col)) {
			matriz[fila][col] = matriz[fila - 1][col];
			matriz[fila - 1][col] = 0;
		} else {
			matriz[fila][col] = 0;
		}
	}

	private boolean celdasSonIgualesAbajo(int fila, int col, int filaContigua) {
		return matriz[fila][col] == matriz[filaContigua][col];
	}

	private void switchCeldas(int fila, int col, int contigua) {
		matriz[fila][col] = matriz[fila][contigua];
		matriz[fila][contigua] = 0;
	}
	
	private void sumarCeldas(int fila, int col, int colContigua) {
		matriz[fila][col] += matriz[fila][colContigua];
		matriz[fila][colContigua] = 0;
		puntaje += matriz[fila][col];
	}

	private boolean celdasSonIguales(int fila, int col, int colContigua) {
		boolean existeCasilla1 = estaOcupado(fila, col);
		boolean existeCasilla2 = estaOcupado(fila, colContigua);

		if (!existeCasilla1 || !existeCasilla2) {
			return false;
		}

		return matriz[fila][col] == matriz[fila][colContigua];
	}

	private boolean estaEnRango(int pos) {
		return pos < matriz.length && pos >= 0;
	}

	public boolean estaOcupado(int pos1, int pos2) {
		return this.matriz[pos1][pos2] != 0;
	}

	public boolean estaVacio(int pos1, int pos2) {
		return this.matriz[pos1][pos2] == 0;
	}

	public boolean celdasSonDistintas(int fila, int col, int contigua) {
		return matriz[fila][col] != matriz[fila][contigua];
	}

	public int obtenerValor(int i, int j) {
		return this.matriz[i][j];
	}

	public void agregarNumero() {
		Point nuevaCasilla = generarPosicion();
		this.matriz[nuevaCasilla.x][nuevaCasilla.y] = esDosOCuatro();
	}

	public int getPuntaje() {
		return this.puntaje;
	}

	public int getSize() {
		return this.matriz.length;
	}

	public int[][] getMatriz() {
		return this.matriz;
	}
}
