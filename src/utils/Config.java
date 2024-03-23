package utils;

import java.awt.Color;

public class Config {
	public int HEIGHT;
	public int WIDTH;
	public int DIMENSION_TABLERO;
	public Color[] COLORES = { new Color(200, 241, 254), new Color(106, 226, 246), new Color(32, 200, 233),
			new Color(10, 173, 208), new Color(12, 138, 174), new Color(17, 110, 141), new Color(23, 90, 115),
			new Color(24, 75, 97), new Color(9, 48, 67), new Color(9, 48, 67), new Color(9, 48, 67) };	
	
	public Config() {
		this.HEIGHT = 500;
		this.WIDTH = 500;
		this.DIMENSION_TABLERO = 4;
	}
		
	public Config(int tamañoTablero) {
		this.HEIGHT = 500;
		this.WIDTH = 500;
		this.DIMENSION_TABLERO = tamañoTablero;
	}
	
	public Config(int resolucion, int tamañoTablero) {
		this.HEIGHT = resolucion;
		this.WIDTH = resolucion;
		this.DIMENSION_TABLERO = tamañoTablero;
	}
}
