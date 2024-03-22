package logica;

import java.awt.Point;
import java.util.Random;

public class Tablero {
	int[][] matriz;

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
		// Intentar mover todos los valores hacia la derecha del tablero

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (!estaOcupado(i, j))
					continue;

				int celdaContigua = j + 1;
				if (!estaEnRango(celdaContigua)) {
					continue;
				}

				if (matriz[i][j] == matriz[i][celdaContigua]) {
					matriz[i][celdaContigua] += matriz[i][j];
					matriz[i][j] = 0;
					j++;
				} else if(!estaOcupado(i, celdaContigua)) {
					matriz[i][celdaContigua] = matriz[i][j];
					matriz[i][j] = 0;					
				}
			}
		}
	}
	
//	private boolean casillerosTienenMismoValor() {
//		return
//	}

	private boolean estaEnRango(int pos) {
		return pos < matriz.length || pos < 0;
	}

	public boolean estaOcupado(int pos1, int pos2) {
		return this.matriz[pos1][pos2] != 0;
	}

	public String obtenerValor(int i, int j) {
		return this.matriz[i][j] != 0 ? Integer.toString(this.matriz[i][j]) : "";
	}

	public void agregarNumero() {
		Point nuevaCasilla = generarPosicion();
		this.matriz[nuevaCasilla.x][nuevaCasilla.y] = esDosOCuatro();
//		for(int i=0; i < matriz.length; i++) {
//			System.out.println("\n");
//			for(int j= 0; j < matriz[0].length; j++) {
//				System.out.print(matriz[i][j]);
//			}
//		}
//		System.out.println("----------------------");
	}
}
