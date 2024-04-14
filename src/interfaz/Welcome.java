package interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.Border;

import logica.Archivo;
import utils.Config;

public class Welcome {

	private JFrame frame;
	private JTextField textField;
	private Config config = new Config();
	private Ranking ranking;

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
		this.ranking = new Ranking();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		crearFrame();

		JPanel panelWelcome = crearPanelWelcome();

		crearBordes(panelWelcome);

		crearLabelNombreUsuario();
		crearInputNombreUsuario();

		JButton btnNewButton = crearBotonJugar();

		frame.getContentPane().add(crearImagen());

		JPanel panelRanking = this.ranking.obtenerPanelRanking();

		JSplitPane splitPane = dividirPantalla(panelWelcome, panelRanking);

		frame.getContentPane().add(splitPane);

		List<Archivo.RankingEntry> rankingLista = new Archivo().leerRanking();

		ranking.mostrarRanking(rankingLista);

		verificarInputYJugar(btnNewButton);

	}

	private JLabel crearImagen() {
		JLabel lblImage = new JLabel();
		Image img = new ImageIcon(this.getClass().getResource("/2048-image.png")).getImage();
		lblImage.setIcon(new ImageIcon(img));
		lblImage.setBounds(40, 300, 500, 130);
		return lblImage;
	}

	private JSplitPane dividirPantalla(JPanel panelWelcome, JPanel panelRanking) {
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelWelcome, panelRanking);
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerSize(0);
		panelRanking.setPreferredSize(panelWelcome.getPreferredSize());
		return splitPane;
	}

	private JPanel crearPanelWelcome() {
		JPanel panelWelcome = new JPanel(new BorderLayout());
		panelWelcome.setPreferredSize(new Dimension(config.WIDTH, config.HEIGHT));
		panelWelcome.setMaximumSize(new Dimension(config.WIDTH, config.HEIGHT));
		panelWelcome.setMinimumSize(new Dimension(200, config.HEIGHT));
		return panelWelcome;
	}

	private void crearBordes(JPanel panelWelcome) {
		Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 0);
		Border lineBorder = BorderFactory.createLineBorder(new Color(17, 110, 141), 2);
		Border compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder);
		panelWelcome.setBorder(compoundBorder);
	}

	private void verificarInputYJugar(JButton btnNewButton) {
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				if (nombre.length() <= 0) {
					JOptionPane.showMessageDialog(frame, "Debe ingresar su nombre", "ATENCIÓN",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (nombre.length() > 20) {
					JOptionPane.showMessageDialog(frame, "El nombre debe exceder los 20 caracteres", "ATENCIÓN",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (nombre.contains(" ")) {
					JOptionPane.showMessageDialog(frame, "El nombre NO debe contener espacios", "ATENCIÓN",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else if (existeEnTabla(nombre)) {
					JOptionPane.showMessageDialog(frame, "El nombre ya existe", "ATENCIÓN",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				frame.dispose();
				Interfaz interfaz = new Interfaz(nombre);
			}
		});
	}

	private boolean existeEnTabla(String nombre) {
		Archivo archivo = new Archivo();
		return archivo.existeNombre(nombre);
	}

	private JButton crearBotonJugar() {
		JButton btnNewButton = new JButton("Jugar");
		btnNewButton.setBounds(140, 190, 200, 70);
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
		frame.setTitle("2048");
		frame.setResizable(false);
		frame.setBounds(100, 100, config.WIDTH + 300, config.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - 400, dim.height / 2 - 500 / 2);
	}
}
