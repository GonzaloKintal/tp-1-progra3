package logica;

import java.awt.Point;
import java.util.Random;

public class Tablero {
	int[][] matriz;
	int puntaje;

	Tablero(int numCasillas) {
		this.matriz = new int[numCasillas][numCasillas];
		this.agregarNumero();
		this.agregarNumero();
	}

	private Point generarPosicion() {
		Point casilla = new Point();
		boolean flag = true;
		int posRandom1 = 0;
		int posRandom2 = 0;

		while (flag) {
			posRandom1 = dameRandom(4);
			posRandom2 = dameRandom(4);

			if (!estaOcupado(posRandom1, posRandom2)) {
				flag = false;
			}
		}
		casilla.setLocation(posRandom1, posRandom2);
		return casilla;
	}

	private int dameRandom(int valor) {
		Random rand = new Random();
		int random = rand.nextInt(valor);
		return random;
	}

	private int esDosOCuatro() {
		return dameRandom(10) > 7 ? 4 : 2;
	}

	public void moverNumerosHorizontal() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = 3; col >= 0; col--) {
				int celdaContigua = col - 1;
				if (!estaOcupado(fila, col) || !estaEnRango(celdaContigua))
					continue;

				if (celdasSonIguales(fila, col, celdaContigua)) {
					sumarContiguas(fila, col, celdaContigua);
//					if (estaOcupado(fila, celdaContigua)) {
//						col++;
//					}
//					puntaje += matriz[fila][col];
				} else if (!estaOcupado(fila, celdaContigua)) {
					moverCelda(fila, col, celdaContigua);
				}
			}
		}
	}

	public void moverNumerosAbajo() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = 0; col < matriz.length; col++) {
				int celdaContigua = fila + 1;
				if (!estaOcupado(fila, col) || !estaEnRango(celdaContigua))
					continue;

				if (celdasSonIgualesAbajo(fila, col, celdaContigua)) {
					sumarContiguasAbajo(fila, col, celdaContigua);
					if (estaOcupado(celdaContigua, col)) {
						fila++;
					}
					puntaje += matriz[celdaContigua][col];
				} else if (!estaOcupado(celdaContigua, col)) {
					moverCeldaAbajo(fila, col, celdaContigua);
				}
			}
		}

	}

	private void moverCeldaAbajo(int fila, int col, int filaContigua) {
		matriz[filaContigua][col] = matriz[fila][col];
		matriz[fila][col] = 0;

	}

	private void sumarContiguasAbajo(int fila, int col, int filaContigua) {
		matriz[filaContigua][col] += matriz[fila][col];
		verificarCeldaArriba(fila, col);
	}

	private void verificarCeldaArriba(int fila, int col) {
		if (estaEnRango(fila-1)&& estaOcupado(fila - 1, col)) {
			matriz[fila][col] = matriz[fila - 1][col];
			matriz[fila - 1][col] = 0;
		} else {
			matriz[fila][col] = 0;
		}
	}

	private boolean celdasSonIgualesAbajo(int fila, int col, int filaContigua) {
		return matriz[fila][col] == matriz[filaContigua][col];
	}

	private void moverCelda(int fila, int col, int colContigua) {
		matriz[fila][col] = matriz[fila][colContigua];
		matriz[fila][colContigua] = 0;
	}

	private void sumarContiguas(int fila, int col, int colContigua) {
		matriz[fila][col] += matriz[fila][colContigua];
		matriz[fila][colContigua] = 0;
	}

	private boolean celdasSonIguales(int fila, int col, int colContigua) {
		return matriz[fila][col] == matriz[fila][colContigua];
	}

	private boolean estaEnRango(int pos) {
		return pos < matriz.length && pos >=0;
	}

	public boolean estaOcupado(int pos1, int pos2) {
		return this.matriz[pos1][pos2] != 0;
	}

//	public String obtenerValor(int i, int j) {
//		return this.matriz[i][j] != 0 ? Integer.toString(this.matriz[i][j]) : "";
//	}
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

}
