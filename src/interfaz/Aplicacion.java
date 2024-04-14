package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import logica.Archivo;
import logica.Juego;
import utils.Config;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;

public class Aplicacion {

	private JFrame frame;
	private Juego juego;
	private Config config = new Config();
	public String nombre;

	// Agrego un JLabel para mostrar el score
	private JLabel scoreLabel;
	private Ranking ranking;

	/**
	 * Launch the application.
	 */
	public static void Interfaz(String nombre) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplicacion(String nombre) {
		this.nombre = nombre;
		this.ranking = new Ranking();
		initialize();
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		crearFrame();

		JPanel panelJuego = crearPanelJuego();

		crearBordes(panelJuego);

		JPanel panelTablero = crearPanelTablero();

		JPanel headerPanel = crearPanelHeader();

		JLabel lastMovementLabel = configurarHeader(headerPanel);

		panelJuego.add(panelTablero);
		panelJuego.add(headerPanel, BorderLayout.NORTH);

		frame.getContentPane().add(panelJuego);

		JPanel panelRanking = this.ranking.obtenerPanelRanking();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelJuego, panelRanking);
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerSize(0);

		frame.getContentPane().add(splitPane);

		panelRanking.setPreferredSize(panelJuego.getPreferredSize());

		this.juego = new Juego(config.TAMAÑO_MATRIZ);

		detectarMovimiento(panelTablero, lastMovementLabel);

		actualizarPantalla(panelTablero);

		List<Archivo.RankingEntry> rankingLista = new Archivo().leerRanking();
		ranking.mostrarRanking(rankingLista);

	}

	public Juego obtenerJuego() {
		return juego;
	}

	private void detectarMovimiento(JPanel panelTablero, JLabel lastMovementLabel) {
		frame.addKeyListener(new KeyAdapter() {
			// Key Pressed method
			public void keyPressed(KeyEvent e) {
				// Check if an up key was pressed
				if (e.getKeyCode() == KeyEvent.VK_RIGHT && juego.posibleMovimientoDerecha()) {
					juego.moverHorizontal(1);
					lastMovementLabel.setText("→ ");
					juego.agregarNumero();
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT && juego.posibleMovimientoIzquierda()) {
					juego.moverHorizontal(-1);
					lastMovementLabel.setText("← ");
					juego.agregarNumero();
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN && juego.posibleMovimientoAbajo()) {
					juego.moverVertical(1);
					lastMovementLabel.setText("↓ ");
					juego.agregarNumero();
				}

				if (e.getKeyCode() == KeyEvent.VK_UP && juego.posibleMovmientoArriba()) {
					juego.moverVertical(-1);
					lastMovementLabel.setText("↑ ");
					juego.agregarNumero();
				}

				// Presionar 1 para ganar el juego
				if (juego.jugadorGano() || e.getKeyCode() == KeyEvent.VK_1) {
					juego.escribirDatosEnArchivo(nombre);
					frame.dispose();
					Final finalScreen = new Final(nombre, juego.getPuntaje(), "Ganaste");
					finalScreen.Final(nombre, juego.getPuntaje(), "Ganaste");
				}

				// Presionar 2 para perder el juego
				if (juego.jugadorPerdio() || e.getKeyCode() == KeyEvent.VK_2) {
					juego.escribirDatosEnArchivo(nombre);
					frame.dispose();
					Final finalScreen = new Final(nombre, juego.getPuntaje(), "Perdiste");
					finalScreen.Final(nombre, juego.getPuntaje(), "Perdiste");
				}
				actualizarPantalla(panelTablero);
				actualizarPuntaje();
			}
		}

		);
	}

	private JLabel configurarHeader(JPanel headerPanel) {
		JLabel lastMovementLabel = new JLabel("");
		lastMovementLabel.setVerticalAlignment(SwingConstants.TOP);
		lastMovementLabel.setFont(new Font("Arial", Font.BOLD, 20));

		headerPanel.setPreferredSize(new Dimension(config.WIDTH, 30));
		headerPanel.setLayout(new BorderLayout(0, 0));
		headerPanel.add(scoreLabel, BorderLayout.WEST);
		headerPanel.add(lastMovementLabel, BorderLayout.EAST);
		return lastMovementLabel;
	}

	private JPanel crearPanelHeader() {
		JPanel headerPanel = new JPanel();
		scoreLabel = new JLabel(" Score: 0");
		scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
		return headerPanel;
	}

	private JPanel crearPanelTablero() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 230));
		panel.setLayout(new GridLayout(config.TAMAÑO_MATRIZ, config.TAMAÑO_MATRIZ, 0, 0));
		panel.setLayout(new GridLayout(4, 4, 7, 7)); // Padding
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		return panel;
	}

	private void crearBordes(JPanel panelJuego) {
		Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 0);
		Border lineBorder = BorderFactory.createLineBorder(new Color(17, 110, 141), 2);
		Border compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder);
		panelJuego.setBorder(compoundBorder);
	}

	private JPanel crearPanelJuego() {
		JPanel panelJuego = new JPanel(new BorderLayout());
		panelJuego.setPreferredSize(new Dimension(config.WIDTH, config.HEIGHT));
		panelJuego.setMaximumSize(new Dimension(config.WIDTH, config.HEIGHT));
		panelJuego.setMinimumSize(new Dimension(200, config.HEIGHT));
		return panelJuego;
	}

	private void crearFrame() {
		frame = new JFrame();
		frame.setTitle("2048");
		frame.setResizable(false);
		frame.setBounds(100, 100, config.WIDTH + 300, config.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - 400, dim.height / 2 - 500 / 2);
	}

	private void actualizarPantalla(JPanel panel) {
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
