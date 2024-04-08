package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Welcome {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome window = new Welcome();
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
	public Welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		crearFrame();

		crearLabelNombreUsuario();

		crearInputNombreUsuario();

		JButton btnNewButton = crearBotonJugar();

		JLabel lblImage = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/2048-image.png")).getImage();
		
		lblImage.setIcon(new ImageIcon(img));
				
		lblImage.setBounds(40, 320, 368, 130);
		frame.getContentPane().add(lblImage);

		verificarInputYJugar(btnNewButton);
		
	}

	private void verificarInputYJugar(JButton btnNewButton) {
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				if (nombre.length() <= 0) {
					JOptionPane.showMessageDialog(frame, "Debe ingresar su nombre", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
					return;
				}
				else if (nombre.length() > 20) {
					JOptionPane.showMessageDialog(frame, "El nombre debe exceder los 20 caracteres", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
					return;
				}
				else if (nombre.contains(" ")) {
					JOptionPane.showMessageDialog(frame, "El nombre NO debe contener espacios", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
					return;
				}

				frame.dispose();
				Interfaz interfaz = new Interfaz(nombre);
			}
		});
	}

	private JButton crearBotonJugar() {
		JButton btnNewButton = new JButton("Jugar");
		btnNewButton.setBounds(140, 200, 200, 70);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(106, 226, 246));
		frame.getContentPane().add(btnNewButton);
		return btnNewButton;
	}

	private void crearInputNombreUsuario() {
		textField = new JTextField();
		textField.setBounds(90, 110, 300, 40);
		textField.setFont(new Font("Arial", Font.BOLD, 20));
		textField.setMargin(new Insets(0, 5, 0, 0));
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

	private void crearLabelNombreUsuario() {
		JLabel lblNameLabel = new JLabel("Escriba su nombre aquí");
		lblNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNameLabel.setBounds(130, 60, 300, 60);
		frame.getContentPane().add(lblNameLabel);
	}

	private void crearFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - 500 / 2, dim.height / 2 - 500 / 2);
	}
}
