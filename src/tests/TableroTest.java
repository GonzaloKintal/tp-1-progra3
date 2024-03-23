package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import logica.Tablero;

public class TableroTest {
	int[][] matriz_init = {
			{ 0, 2, 0, 2 },
			{ 2, 2, 2, 2 },
			{ 0, 0, 4, 4 }, 
			{ 0, 2, 0, 0 }
			};
	
	int[][] matrizMovidaALaDerecha = {
			{ 0, 0, 0, 4 },
			{ 0, 0, 4, 4 },
			{ 0, 0, 0, 8 },
			{ 0, 0, 0, 2 }, 
			};
	
	int[][] matrizMovidaALaIzquierda = {
			{ 4, 0, 0, 0 },
			{ 4, 4, 0, 0 },
			{ 8, 0, 0, 0 },
			{ 2, 0, 0, 0 }, 
	};

	public Tablero tablero;

	@Test
	public void sizeShouldBe4() {
		this.tablero = new Tablero(matriz_init);
		assertEquals(this.tablero.getSize(), 4);
	}

	@Test
	public void sizeShouldNotBe5() {
		this.tablero = new Tablero(matriz_init);
		assertNotEquals(this.tablero.getSize(), 5);
	}

	@Test
	public void tableroAndMatrizMovidaALaDerechaAreNotEqual() {
		this.tablero = new Tablero(matriz_init);
		assertFalse(matricesAreEqual(this.tablero.getMatriz(), matrizMovidaALaDerecha));
	}

	@Test
	public void tableroMovedToTheRightIsEqual() {
		this.tablero = new Tablero(matriz_init);
		this.tablero.moverNumerosDerecha();
		assertTrue(matricesAreEqual(this.tablero.getMatriz(), matrizMovidaALaDerecha));
	}
	
	@Test
	public void tableroMovedToTheLeftIsEqual() {
		this.tablero = new Tablero(matriz_init);
		this.tablero.moverNumerosIzquierda();
		assertTrue(matricesAreEqual(this.tablero.getMatriz(), matrizMovidaALaIzquierda));
	}

	// Utility methods
	
	private boolean matricesAreEqual(int[][] matrix1, int[][] matrix2) {
		if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
			return false;
		}

		for (int i = 0; i < matrix1.length; i++) {
			for (int j = 0; j < matrix1[0].length; j++) {
				if (matrix1[i][j] != matrix2[i][j]) {
					return false;
				}
			}
		}

		return true;
	}
}
