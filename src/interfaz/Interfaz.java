package interfaz;

import java.awt.Color;
import java.awt.EventQueue;
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
import java.awt.event.KeyListener;

public class Interfaz{

	private JFrame frame;
	private int HEIGHT = 500;
	private int WIDTH = 500;
	private Juego juego;

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
		this.addKeyListener(this);
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
        actualizarPantalla(panel);
				
		frame.addKeyListener(new KeyAdapter() {
            // Key Pressed method
            public void keyPressed(KeyEvent e) {
                // Check if an up key was pressed
                if(e.getKeyCode() == KeyEvent.VK_UP){
                	juego.agregarNumero();
                }
                actualizarPantalla(panel);
            }
        });
	}
	
	private void actualizarPantalla(JPanel panel) {
		for (int i = 0; i < juego.getTamañoTablero(); i++) {
			for (int j = 0; j < juego.getTamañoTablero(); j++) {
				JLabel label = new JLabel(juego.obtenerValor(i, j));
				if (juego.tableroTieneNumero(i, j)) {
					label.setBackground(Color.BLUE);
				}
				label.setForeground(Color.GREEN);
				label.setOpaque(true);
				label.setFont(new Font("Comic Sans", Font.PLAIN, 20));
				label.setBorder(new LineBorder(Color.BLACK));
				label.setHorizontalAlignment(SwingConstants.CENTER);
				panel.add(label);
			}
		}
	}
}