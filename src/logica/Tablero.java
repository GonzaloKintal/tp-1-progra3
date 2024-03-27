package logica;

import java.awt.Point;
import java.util.Random;

public class Tablero {
	int[][] matriz;
//	int[][] matriz = {
//			{ 2, 4, 0, 2 },
//			{ 2, 2, 4, 8 },
//			{ 4, 0, 0, 4 }, 
//			{ 4, 0, 2, 2 }
//	};
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
	 * Devuelve un numero random entre 0 y el valor pasado como parametro sin
	 * incluirlo
	 * 
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

	// Métodos de movimiento genéricos

	public void moverHorizontal(int direccion) {
		for (int fila = 0; fila < matriz.length; fila++) {
			if (direccion == 1) {
				recorrerColumnasDerIzq(fila);
			} else {
				recorrerColumnasIzqDer(fila);
			}
		}
	}

	public void moverVertical(int direccion) {
		for (int col = 0; col < matriz.length; col++) {
			if (direccion == 1) {
				recorrerFilasAbajoArriba(col);
			} else {
				recorrerFilasArribaAbajo(col);
			}
		}
	}

	// Movimientos específicos

	private void recorrerColumnasDerIzq(int fila) {
		for (int col = matriz.length - 1; col >= 0; col--) {
			movimientosDerIzq(fila, col);
		}
	}

	private void movimientosDerIzq(int fila, int col) {
		boolean yaSeSumo = false;
		for (int contigua = col - 1; contigua >= 0; contigua--) {
			if (estaVacio(fila, col) && estaOcupado(fila, contigua))
				switchCeldasHorizontal(fila, col, contigua);

			if (estaVacio(fila, contigua))
				continue;

			if (!celdasSonIgualesHorizontal(fila, col, contigua))
				break;

			if (celdasSonIgualesHorizontal(fila, col, contigua) && yaSeSumo)
				moverCeldaAMiLadoHorizontal(fila, col, contigua, -1);

			sumarCeldasHorizontal(fila, col, contigua);
			yaSeSumo = true;
			break;
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
				switchCeldasHorizontal(fila, col, contigua);

			if (estaVacio(fila, contigua))
				continue;

			if (!celdasSonIgualesHorizontal(fila, col, contigua))
				break;

			if (celdasSonIgualesHorizontal(fila, col, contigua) && yaSeSumo)
				moverCeldaAMiLadoHorizontal(fila, col, contigua, 1);

			sumarCeldasHorizontal(fila, col, contigua);
			yaSeSumo = true;
			break;
		}
	}

	private void recorrerFilasAbajoArriba(int col) {
		for (int fila = matriz.length - 1; fila >= 0; fila--) {
			movimientosAbajoArriba(fila, col);
		}
	}

	private void movimientosAbajoArriba(int fila, int col) {
		boolean yaSeSumo = false;
		for (int contigua = fila - 1; contigua >= 0; contigua--) {
			if (estaVacio(fila, col) && estaOcupado(contigua, col)) {
				switchCeldasVertical(fila, col, contigua);
			}

			if (estaVacio(contigua, col))
				continue;

			if (sonCeldasDistintasVertical(fila, col, contigua))
				break;

			if (celdasSonIgualesVertical(fila, col, contigua) && yaSeSumo) {
				moverCeldaAMiLadoVertical(fila, col, contigua, -1);
			}

			sumarCeldasVertical(fila, col, contigua);
			yaSeSumo = true;
			break;
		}
	}

	public void recorrerFilasArribaAbajo(int col) {
		for (int fila = 0; fila < matriz.length; fila++) {
			movimientosArribaAbajo(fila, col);
		}
	}

	public void movimientosArribaAbajo(int fila, int col) {
		boolean yaSeSumo = false;
		for (int contigua = fila + 1; contigua < matriz.length; contigua++) {
			if (estaVacio(fila, col) && estaOcupado(contigua, col)) {
				switchCeldasVertical(fila, col, contigua);
			}

			if (estaVacio(contigua, col))
				continue;

			if (sonCeldasDistintasVertical(fila, col, contigua))
				break;

			if (celdasSonIgualesVertical(fila, col, contigua) && yaSeSumo) {
				moverCeldaAMiLadoVertical(fila, col, contigua, 1);
			}

			sumarCeldasVertical(fila, col, contigua);
			yaSeSumo = true;
			break;
		}
	}

	private boolean celdasSonIgualesVertical(int fila, int col, int contigua) {
		return matriz[fila][col] == matriz[contigua][col];
	}

	private void switchCeldasVertical(int fila, int col, int contigua) {
		matriz[fila][col] = matriz[contigua][col];
		matriz[contigua][col] = 0;
	}

	private boolean sonCeldasDistintasVertical(int fila, int col, int contigua) {
		return matriz[fila][col] != matriz[contigua][col];
	}

	private void moverCeldaAMiLadoVertical(int fila, int col, int contigua, int paso) {
		int aux = matriz[contigua][col];
		matriz[contigua][col] = 0;
		matriz[fila + paso][col] = aux;
	}

	private void sumarCeldasVertical(int fila, int col, int contigua) {
		matriz[fila][col] += matriz[contigua][col];
		matriz[contigua][col] = 0;
		puntaje += matriz[fila][col];
	}

	private boolean celdasSonIgualesHorizontal(int fila, int col, int colContigua) {
		boolean existeCasilla1 = estaOcupado(fila, col);
		boolean existeCasilla2 = estaOcupado(fila, colContigua);

		if (!existeCasilla1 || !existeCasilla2) {
			return false;
		}

		return matriz[fila][col] == matriz[fila][colContigua];
	}

	private void switchCeldasHorizontal(int fila, int col, int contigua) {
		matriz[fila][col] = matriz[fila][contigua];
		matriz[fila][contigua] = 0;
	}

	public boolean sonCeldasSonDistintasHorizontal(int fila, int col, int contigua) {
		return matriz[fila][col] != matriz[fila][contigua];
	}

	public void moverCeldaAMiLadoHorizontal(int fila, int col, int contigua, int lado) {
		int aux = matriz[fila][contigua];
		matriz[fila][contigua] = 0;
		matriz[fila][col + lado] = aux;
	}

	private void sumarCeldasHorizontal(int fila, int col, int colContigua) {
		matriz[fila][col] += matriz[fila][colContigua];
		matriz[fila][colContigua] = 0;
		puntaje += matriz[fila][col];
	}

	public boolean estaOcupado(int pos1, int pos2) {
		return this.matriz[pos1][pos2] != 0;
	}

	public boolean estaVacio(int pos1, int pos2) {
		return this.matriz[pos1][pos2] == 0;
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

	public boolean hayPerdedor() {
		return estaLLeno() && noHayPosiblesMovimientos();
	}

	public boolean estaLLeno() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = 0; col < matriz.length; col++) {
				if (estaVacio(fila, col)) {
					return false;
				}
			}

		}
		return true;
	}

	private boolean noHayPosiblesMovimientos() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = 0; col < matriz.length; col++) {
				if (tieneMovimiento(fila, col)) {
					return false;
				}
			}

		}
		return true;
	}

	private boolean tieneMovimiento(int fila, int col) {
		if (estaEnRango(col + 1) && celdasSonIgualesHorizontal(fila, col, col + 1)) {
			return true;
		}
		if (estaEnRango(col - 1) && celdasSonIgualesHorizontal(fila, col, col - 1)) {
			return true;
		}
		if (estaEnRango(fila + 1) && celdasSonIgualesVertical(fila, col, fila + 1)) {
			return true;
		}
		if (estaEnRango(fila - 1) && celdasSonIgualesVertical(fila, col, fila - 1)) {
			return true;
		}
		return false;

	}

	private boolean estaEnRango(int pos) {
		return pos < matriz.length && pos >= 0;
	}

	public boolean hayGanador() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = 0; col < matriz.length; col++) {
				if (condicionGanar(fila, col)) {
					return true;
				}
			}

		}
		return false;
	}

	private boolean condicionGanar(int fila, int col) {
		return matriz[fila][col] == 2048;
	}

	public boolean posibleMovimientoDerecha() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = 0; col < matriz.length - 1; col++) {
				if (!estaOcupado(fila, col)) {
					continue;
				}
				if (posibleMovimientoDerecha(fila, col)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean posibleMovimientoDerecha(int fila, int col) {
		if (estaEnRango(col + 1) && !estaOcupado(fila, col + 1)) {
			return true;
		}
		if (estaEnRango(col + 1) && celdasSonIgualesHorizontal(fila, col, col + 1)) {
			return true;
		}
		if (!estaEnRango(col + 1)) {
			return false;
		}
		return posibleMovimientoDerecha(fila, col + 1);

	}

	public boolean posibleMovimientoIzquierda() {
		for (int fila = 0; fila < matriz.length; fila++) {
			for (int col = matriz.length - 1; col >= 1; col--) {
				if (!estaOcupado(fila, col)) {
					continue;
				}
				if (posibleMovimientoIzquierda(fila, col)) {
					return true;
				}
			}
		}

		return false;

	}

	private boolean posibleMovimientoIzquierda(int fila, int col) {
		if (estaEnRango(col - 1) && !estaOcupado(fila, col - 1)) {
			return true;
		}
		if (estaEnRango(col - 1) && celdasSonIgualesHorizontal(fila, col, col - 1)) {
			return true;
		}
		if (!estaEnRango(col - 1)) {
			return false;
		}
		return posibleMovimientoIzquierda(fila, col - 1);
	}

	public boolean posibleMovimientoAbajo() {
		for (int fila = 0; fila < matriz.length - 1; fila++) {
			for (int col = 0; col < matriz.length; col++) {
				if (!estaOcupado(fila, col)) {
					continue;
				}
				if (posibleMovimientoAbajo(fila, col)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean posibleMovimientoAbajo(int fila, int col) {
		if (estaEnRango(fila + 1) && !estaOcupado(fila + 1, col)) {
			return true;
		}
		if (estaEnRango(fila + 1) && celdasSonIgualesVertical(fila, col, fila + 1)) {
			return true;
		}
		if (!estaEnRango(fila + 1)) {
			return false;
		}
		return posibleMovimientoAbajo(fila + 1, col);
	}

	public boolean posibleMovimientoArriba() {
		for (int fila = matriz.length - 1; fila >= 1; fila--) {
			for (int col = 0; col < matriz.length; col++) {
				if (!estaOcupado(fila, col)) {
					continue;
				}
				if (posibleMovimientoArriba(fila, col)) {
					return true;
				}
			}
		}

		return false;

	}

	private boolean posibleMovimientoArriba(int fila, int col) {
		if (estaEnRango(fila - 1) && !estaOcupado(fila - 1, col)) {
			return true;
		}
		if (estaEnRango(fila - 1) && celdasSonIgualesVertical(fila, col, fila - 1)) {
			return true;
		}
		if (!estaEnRango(fila - 1)) {
			return false;
		}
		return posibleMovimientoArriba(fila - 1, col);
	}
}
