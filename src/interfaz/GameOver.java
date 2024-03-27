package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOver {

	private JFrame frame;
	public String nombre;
	public int score;
	
	/**
	 * Create the application.
	 */
	public GameOver(String nombre, int score) {
		this.nombre = nombre;
		this.score = score;
		initialize();
	}
	
	/**
	 * Launch the application.
	 */
	public static void gameOver(String n, int score) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver window = new GameOver(n, score);
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
		frame.setLocation(dim.width/2- 500 /2, dim.height/2-500/2);
		
		JLabel lblNewLabel = new JLabel("Perdiste " + this.nombre);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(162, 93, 300, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblScoreLabel = new JLabel("Score: " + this.score);
		lblScoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblScoreLabel.setBounds(162, 120, 300, 60);
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
		btnVolverAJugar.setBounds(30, 250, 200, 70);
		frame.getContentPane().add(btnVolverAJugar);
		
		JButton btnSalirDelJuego = new JButton("SALIR DEL JUEGO");
		btnSalirDelJuego.setFont(new Font("Arial", Font.BOLD, 16));
		btnSalirDelJuego.setBackground(new Color(106, 226, 246));
		btnSalirDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSalirDelJuego.setBounds(250, 250, 200, 70);
		frame.getContentPane().add(btnSalirDelJuego);
	}
}
