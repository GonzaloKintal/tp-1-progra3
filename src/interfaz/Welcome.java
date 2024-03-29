package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - 500 / 2, dim.height / 2 - 500 / 2);

		JLabel lblNewLabel = new JLabel("Escriba su nombre aquí");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(130, 60, 300, 60);
		frame.getContentPane().add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(90, 110, 300, 40);
		textField.setFont(new Font("Arial", Font.BOLD, 20));
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Jugar");
		btnNewButton.setBounds(140, 200, 200, 70);
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 20));
		btnNewButton.setBackground(new Color(106, 226, 246));
		frame.getContentPane().add(btnNewButton);

		JLabel lblImage = new JLabel();
		lblImage.setIcon(
				new ImageIcon("C:\\Users\\kinta\\eclipse-workspace\\TrabajoPráctico1\\src\\utils\\2048-image.png"));
		lblImage.setBounds(40, 320, 368, 130);
		frame.getContentPane().add(lblImage);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				if (nombre.length() <= 0)
					return;
				else if (nombre.length() > 20) {
					return;
				}

				frame.dispose();
				Interfaz interfaz = new Interfaz(nombre);
				interfaz.Interfaz(nombre);
			}
		});
	}
}
