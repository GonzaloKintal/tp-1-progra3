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

	public void moverNumerosDerecha() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = 3; col >= 0; col--) {
				boolean yaSeSumo = false;
				for (int contigua = col - 1; contigua >= 0; contigua--) {
					if (estaVacio(fila, col) && estaOcupado(fila, contigua)) {
						matriz[fila][col] = matriz[fila][contigua];
						matriz[fila][contigua] = 0;
					}

					if (estaVacio(fila, contigua)) {
						continue;
					}
					
					if (estaOcupado(fila, col) && estaOcupado(fila, contigua)
							&& !celdasSonIguales(fila, col, contigua)) {
						break;
					}

					if (celdasSonIguales(fila, col, contigua)) {
						if (yaSeSumo) {
							int aux = matriz[fila][contigua];
							matriz[fila][contigua] = 0;
							matriz[fila][col - 1] = aux;
						} else {
							matriz[fila][col] += matriz[fila][contigua];
							matriz[fila][contigua] = 0;
							yaSeSumo = true;
						}
					}
				}
			}
		}
	}

	public void moverNumerosIzquierda() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = 0; col < matriz.length; col++) {
				boolean yaSeSumo = false;
				for (int contigua = col + 1; contigua < matriz.length; contigua++) {
					if (estaVacio(fila, col) && estaOcupado(fila, contigua)) {
						matriz[fila][col] = matriz[fila][contigua];
						matriz[fila][contigua] = 0;
					}

					if (estaVacio(fila, contigua)) {
						continue;
					}
					
					if (!celdasSonIguales(fila, col, contigua)) {
						break;
					}


					if (celdasSonIguales(fila, col, contigua)) {
						if (yaSeSumo) {
							moverCasilleroAMiLado(fila, col, contigua, 1);
						} else {
							sumarCeldas(fila, col, contigua);
							yaSeSumo = true;
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * @param fila
	 * @param col
	 * @param contigua
	 * @param lado 1 Para dejar el casillero a mi derecha, -1 para dejar el casillero a mi izquierda
	 */
	public void moverCasilleroAMiLado(int fila, int col, int contigua, int lado) {
		int aux = matriz[fila][contigua];
		matriz[fila][contigua] = 0;
		matriz[fila][col + 1] = aux;
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

	private void sumarCeldas(int fila, int col, int colContigua) {
		matriz[fila][col] += matriz[fila][colContigua];
		matriz[fila][colContigua] = 0;
	}

	private boolean celdasSonIguales(int fila, int col, int colContigua) {
		boolean existeCasilla1 = estaOcupado(fila, col);
		boolean existeCasilla2 = estaOcupado(fila, colContigua);
		
		if(!existeCasilla1 || !existeCasilla2) {
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
