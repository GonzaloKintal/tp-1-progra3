package interfaz;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOver {

	private JFrame frame;
	public String nombre;
	
	/**
	 * Create the application.
	 */
	public GameOver(String nombre) {
		this.nombre = nombre;
		initialize();
	}
	
	/**
	 * Launch the application.
	 */
	public static void gameOver(String n) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOver window = new GameOver(n);
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2- 500 /2, dim.height/2-500/2);
		
		JLabel lblNewLabel = new JLabel("perdiste " + this.nombre);
		lblNewLabel.setBounds(162, 93, 114, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Jugar de nuevo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Interfaz interfaz = new Interfaz(nombre);
				interfaz.Interfaz(nombre);
			}
		});
		btnNewButton.setBounds(139, 164, 147, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSalirDelJuego = new JButton("salir del juego");
		btnSalirDelJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnSalirDelJuego.setBounds(139, 202, 147, 23);
		frame.getContentPane().add(btnSalirDelJuego);
	}
}
