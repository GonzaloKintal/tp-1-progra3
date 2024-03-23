package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logica.Juego;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Interfaz {

	private JFrame frame;
	private int HEIGHT = 500;
	private int WIDTH = 500;
	private Juego juego;
	private Color[] colores = { new Color(200, 241, 254), new Color(106, 226, 246), new Color(32, 200, 233),
			new Color(10, 173, 208), new Color(12, 138, 174), new Color(17, 110, 141), new Color(23, 90, 115),
			new Color(24, 75, 97), new Color(9, 48, 67), new Color(9, 48, 67), new Color(9, 48, 67) };

	// Agrego un JLabel para mostrar el score
	private JLabel scoreLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.juego = new Juego();
		// Config Frame
		frame = new JFrame();
		frame.setTitle("2048");
		frame.setResizable(false);
		frame.setBounds(100, 100, this.WIDTH, this.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Config panel (Tablero)
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(4, 4, 0, 0));

		// Agrego un JLabel para mostrar el puntaje en el encabezado
		JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		scoreLabel = new JLabel("Score: 0");
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
		headerPanel.add(scoreLabel);
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);

		frame.addKeyListener(new KeyAdapter() {
			// Key Pressed method
			public void keyPressed(KeyEvent e) {
				// Check if an up key was pressed
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					juego.moverDerecha();
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					juego.moverIzquierda();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					juego.moverAbajo();
				}
				juego.agregarNumero();
				actualizarPantalla(panel);
				actualizarPuntaje();
			}
			
		});
		actualizarPantalla(panel);
	}

	private void actualizarPantalla(JPanel panel) {
		// Elimina todos los componentes del panel
		panel.removeAll();

		for (int i = 0; i < juego.getTamañoTablero(); i++) {
			for (int j = 0; j < juego.getTamañoTablero(); j++) {
				int valorCasilla = juego.obtenerValor(i, j);
				JLabel label = new JLabel(Integer.toString(valorCasilla));

				if (valorCasilla != 0) {
					int valor = (int) log2(valorCasilla);
					label.setBackground(this.colores[valor - 1]);
					label.setForeground(Color.BLACK);
				} else {
					label.setForeground(new Color(240, 240, 240));
				}

				label.setOpaque(true);
				label.setFont(new Font("Comic Sans", Font.PLAIN, 20));
				label.setBorder(new LineBorder(Color.BLACK));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label);
			}
		}

		// Actualiza y repinta el panel
		panel.revalidate();
		panel.repaint();
	}

	private void actualizarPuntaje() {
		int puntaje = juego.getPuntaje();
		scoreLabel.setText("Score: " + puntaje);
	}

	private static double log2(int x) {
		return Math.log(x) / Math.log(2);
	}

}
