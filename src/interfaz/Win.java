package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Win {

	private JFrame frame;
	public String nombre;
	public int score;

	/**
	 * Create the application.
	 */
	public Win(String nombre, int score) {
		this.nombre = nombre;
		this.score = score;
		initialize();
	}

	/**
	 * Launch the application.
	 */
	public static void win(String n, int score) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Win window = new Win(n, score);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - 500 / 2, dim.height / 2 - 500 / 2);

		JLabel lblWinLabel = new JLabel("¡Ganaste " + this.nombre + "!");
		lblWinLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblWinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWinLabel.setBounds(0, 60, 500, 60);
		frame.getContentPane().add(lblWinLabel);

		JLabel lblScoreLabel = new JLabel("Score: " + this.score);
		lblScoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblScoreLabel.setBounds(0, 110, 500, 60);
		frame.getContentPane().add(lblScoreLabel);

		JButton btnVolverAJugar = new JButton("JUGAR DE NUEVO");
		btnVolverAJugar.setFont(new Font("Arial", Font.BOLD, 16));
		btnVolverAJugar.setBackground(new Color(106, 226, 246));
		btnVolverAJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Interfaz interfaz = new Interfaz(nombre);
				interfaz.Interfaz(nombre);
			}
		});
		btnVolverAJugar.setBounds(30, 210, 200, 70);
		frame.getContentPane().add(btnVolverAJugar);

		JButton btnSalirDelJuego = new JButton("SALIR DEL JUEGO");
		btnSalirDelJuego.setFont(new Font("Arial", Font.BOLD, 16));
		btnSalirDelJuego.setBackground(new Color(106, 226, 246));
		btnSalirDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSalirDelJuego.setBounds(255, 210, 200, 70);
		frame.getContentPane().add(btnSalirDelJuego);

		JLabel lblImage = new JLabel();
		lblImage.setIcon(
				new ImageIcon("C:\\Users\\kinta\\eclipse-workspace\\TrabajoPráctico1\\src\\utils\\2048-image.png"));
		lblImage.setBounds(40, 320, 368, 130);
		frame.getContentPane().add(lblImage);
	}
}
