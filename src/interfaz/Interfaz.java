package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import logica.Juego;
import utils.Config;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.ComponentOrientation;

public class Interfaz {

	private JFrame frame;
	private Juego juego;
	private Config config = new Config();
	public String nombre;

	// Agrego un JLabel para mostrar el score
	private JLabel scoreLabel, nameLabel;

	/**
	 * Launch the application.
	 */
	public static void Interfaz(String nombre) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz(nombre);
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
	public Interfaz(String nombre) {
		this.nombre = nombre;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Config Frame
		frame = new JFrame();
		frame.setTitle("2048");
		frame.setResizable(false);
		frame.setBounds(100, 100, config.WIDTH + 300, config.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - 400, dim.height / 2 - 500 / 2);

		// Panel juego
		JPanel panelJuego = new JPanel(new BorderLayout());

		// Config panel (Tablero)
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 230));
		panel.setLayout(new GridLayout(config.DIMENSION_TABLERO, config.DIMENSION_TABLERO, 0, 0));
		panel.setLayout(new GridLayout(4, 4, 4, 4)); // Padding

		// Paneles para bordes derecho e izquierdo
		JPanel leftHeaderPanel = new JPanel(new BorderLayout());
		JLabel leftHeaderLabel = new JLabel(" ", SwingConstants.CENTER);
		leftHeaderLabel.setFont(new Font("Arial", Font.BOLD, 16));
		leftHeaderPanel.add(leftHeaderLabel, BorderLayout.CENTER);

		JPanel rightHeaderPanel = new JPanel(new BorderLayout());
		JLabel rightHeaderLabel = new JLabel(" ", SwingConstants.CENTER);
		rightHeaderLabel.setFont(new Font("Arial", Font.BOLD, 16));
		rightHeaderPanel.add(rightHeaderLabel, BorderLayout.CENTER);

		// Panel para el score
		JPanel headerPanel = new JPanel();
		scoreLabel = new JLabel(" Score: 0");
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));

		JLabel lastMovementLabel = new JLabel("");
		lastMovementLabel.setVerticalAlignment(SwingConstants.TOP);
		lastMovementLabel.setFont(new Font("Arial", Font.BOLD, 20));

		headerPanel.setPreferredSize(new Dimension(config.WIDTH, 30));
		headerPanel.setLayout(new BorderLayout(0, 0));
		headerPanel.add(scoreLabel, BorderLayout.WEST);
		headerPanel.add(lastMovementLabel, BorderLayout.EAST);

		panelJuego.add(panel);
		panelJuego.add(headerPanel, BorderLayout.NORTH);
		panelJuego.add(leftHeaderPanel, BorderLayout.WEST);
		panelJuego.add(rightHeaderPanel, BorderLayout.EAST);

		frame.getContentPane().add(panelJuego);

		// Panel para el ranking
		JPanel panelRanking = new JPanel();
		panelRanking.setBackground(Color.WHITE);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelRanking, panelJuego);
		splitPane.setResizeWeight(1.0);
		splitPane.setDividerSize(0);

		frame.getContentPane().add(splitPane);

		// Init juego
		this.juego = new Juego(config.DIMENSION_TABLERO);

		frame.addKeyListener(new KeyAdapter() {
			// Key Pressed method
			public void keyPressed(KeyEvent e) {
				// Check if an up key was pressed
				if (juego.posibleMovimientoDerecha() && e.getKeyCode() == KeyEvent.VK_RIGHT) {
					juego.moverHorizontal(1);
					lastMovementLabel.setText("→ ");
					juego.agregarNumero();// Derecha
				}
				if (juego.posibleMovimientoIzquierda() && e.getKeyCode() == KeyEvent.VK_LEFT) {
					juego.moverHorizontal(-1);
					lastMovementLabel.setText("← ");
					juego.agregarNumero();// Izquierda
				}

				if (juego.posibleMovimientoAbajo() && e.getKeyCode() == KeyEvent.VK_DOWN) {
					juego.moverVertical(1);
					lastMovementLabel.setText("↓ ");
					juego.agregarNumero();// Abajo
				}

				if (juego.posibleMovmientoArriba() && e.getKeyCode() == KeyEvent.VK_UP) {
					juego.moverVertical(-1);
					lastMovementLabel.setText("↑ ");
					juego.agregarNumero();// Arriba
				}

				if (juego.jugadorGano() || e.getKeyCode() == KeyEvent.VK_1) {
					frame.dispose();
					Win w = new Win(nombre, juego.getPuntaje());
					w.win(nombre, juego.getPuntaje());
				}

				if (juego.jugadorPerdio() || e.getKeyCode() == KeyEvent.VK_2) {
					frame.dispose();
					GameOver go = new GameOver(nombre, juego.getPuntaje());
					go.gameOver(nombre, juego.getPuntaje());
				}
				actualizarPantalla(panel);
				actualizarPuntaje();
			}
		}

		);

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
					label.setBackground(config.COLORES[valor - 1]);
					if (valorCasilla >= 64) {
						label.setForeground(Color.WHITE);
					} else {
						label.setForeground(Color.BLACK);
					}
				} else {
					label.setForeground(new Color(240, 240, 240));
				}

				label.setOpaque(true);
				label.setFont(new Font("Comic Sans", Font.BOLD, 25));
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
		scoreLabel.setText(" Score: " + puntaje);
	}

	private static double log2(int x) {
		return Math.log(x) / Math.log(2);
	}

}
