package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import logica.Archivo;
import utils.Config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class Final {

	private JFrame frame;
	public String nombre;
	private String mensaje;
	private Ranking ranking;
	public int score;
	private Config config = new Config();

	/**
	 * Create the application.
	 */
	public Final(String nombre, int score, String mensaje) {
		this.nombre = nombre;
		this.score = score;
		this.mensaje = mensaje;
		this.ranking = new Ranking();
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void Final(String n, int score, String mensaje) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Final window = new Final(n, score, mensaje);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		configurarFrame();
		JPanel panelFinal = crearPanelGameOver();
		panelFinal.setLayout(new BorderLayout());

		configurarMensajes();
		configurarBotones();

		frame.getContentPane().add(panelFinal, BorderLayout.CENTER);

		crearImagen(panelFinal);

		crearBordes(panelFinal);

		JPanel panelRanking = this.ranking.obtenerPanelRanking();

		JSplitPane splitPane = dividirPantalla(panelFinal, panelRanking);

		frame.getContentPane().add(splitPane);

		List<Archivo.RankingEntry> rankingLista = new Archivo().leerRanking();

		ranking.mostrarRanking(rankingLista);

	}

	private void crearImagen(JPanel panelFinal) {
		JLabel lblImage = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/2048-image.png")).getImage();
		lblImage.setIcon(new ImageIcon(img));
		lblImage.setBounds(40, 300, 368, 130);
		frame.getContentPane().add(lblImage);
		frame.getContentPane().add(lblImage);
		frame.getContentPane().add(panelFinal, BorderLayout.CENTER);
	}

	private JSplitPane dividirPantalla(JPanel panelGameOver, JPanel panelRanking) {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelGameOver, panelRanking);
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerSize(0);
		panelRanking.setPreferredSize(panelGameOver.getPreferredSize());
		return splitPane;
	}

	private JPanel crearPanelGameOver() {
		JPanel panelGameOver = new JPanel(new BorderLayout());
		panelGameOver.setPreferredSize(new Dimension(config.WIDTH, config.HEIGHT));
		panelGameOver.setMaximumSize(new Dimension(config.WIDTH, config.HEIGHT));
		panelGameOver.setMinimumSize(new Dimension(200, config.HEIGHT));
		return panelGameOver;

	}

	private void crearBordes(JPanel panelFinal) {
		Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 0);
		Border lineBorder = BorderFactory.createLineBorder(new Color(17, 110, 141), 2);
		Border compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder);
		panelFinal.setBorder(compoundBorder);
	}

	private void configurarBotones() {
		JButton btnVolverAJugar = new JButton("JUGAR DE NUEVO");
		btnVolverAJugar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVolverAJugar.setBackground(new Color(106, 226, 246));
		btnVolverAJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Interfaz interfaz = new Interfaz(nombre);
			}
		});
		btnVolverAJugar.setBounds(50, 210, 170, 50);
		frame.getContentPane().add(btnVolverAJugar);

		JButton btnSalirDelJuego = new JButton("SALIR DEL JUEGO");
		btnSalirDelJuego.setFont(new Font("Arial", Font.BOLD, 14));
		btnSalirDelJuego.setBackground(new Color(106, 226, 246));
		btnSalirDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSalirDelJuego.setBounds(245, 210, 170, 50);
		frame.getContentPane().add(btnSalirDelJuego);
	}

	private void configurarMensajes() {
		JLabel lblGameOver = new JLabel(this.mensaje + " " + this.nombre);
		lblGameOver.setFont(new Font("Arial", Font.BOLD, 30));
		lblGameOver.setHorizontalAlignment(SwingConstants.CENTER);
		lblGameOver.setBounds(-20, 60, 500, 60);
		frame.getContentPane().add(lblGameOver);

		JLabel lblScoreLabel = new JLabel("Score: " + this.score);
		lblScoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreLabel.setBounds(-20, 110, 500, 60);
		frame.getContentPane().add(lblScoreLabel);
	}

	private void configurarFrame() {
		frame = new JFrame();
		frame.setTitle("2048");
		frame.setResizable(false);
		frame.setBounds(100, 100, config.WIDTH + 300, config.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - 400, dim.height / 2 - 500 / 2);
	}

}
