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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logica.Archivo;
import logica.Juego;
import utils.Config;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JTable;
import javax.swing.BorderFactory;

public class Interfaz {

	private JFrame frame;
	private Juego juego;
	private Config config = new Config();
	public String nombre;

	// Agrego un JLabel para mostrar el score
	private JLabel scoreLabel;
	private static JTable table;

	private Archivo archivo;

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
	public Interfaz(String nombre) {
		this.nombre = nombre;
		this.archivo = new Archivo();
		initialize();
		this.frame.setVisible(true);
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
		panelJuego.setPreferredSize(new Dimension(config.WIDTH, config.HEIGHT)); // Establece el tamaño preferido
		panelJuego.setMaximumSize(new Dimension(config.WIDTH, config.HEIGHT));
		panelJuego.setMinimumSize(new Dimension(200, config.HEIGHT));

		// Crea los bordes individuales
		Border emptyBorder = BorderFactory.createEmptyBorder(20, 20, 20, 0);
		Border lineBorder = BorderFactory.createLineBorder(new Color(17, 110, 141), 2);
		Border compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder);
		panelJuego.setBorder(compoundBorder);

		// Config panel (Tablero)
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 230));
		panel.setLayout(new GridLayout(config.TAMAÑO_MATRIZ, config.TAMAÑO_MATRIZ, 0, 0));
		panel.setLayout(new GridLayout(4, 4, 7, 7)); // Padding
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// Panel para el header
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

		frame.getContentPane().add(panelJuego);

		// Panel para el ranking
		JPanel panelRanking = new JPanel();
		panelRanking.setBackground(new Color(240, 240, 240));

		Border emptyBorder2 = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		Border lineBorder2 = BorderFactory.createLineBorder(new Color(17, 110, 141), 2);
		Border compoundBorder2 = BorderFactory.createCompoundBorder(emptyBorder2, lineBorder2);
		panelRanking.setBorder(compoundBorder2);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelJuego, panelRanking);
		panelRanking.setLayout(new BorderLayout(0, 0));
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerSize(0);

		JLabel lblNewLabel_1 = new JLabel("");
		panelRanking.add(lblNewLabel_1, BorderLayout.WEST);

		JLabel lblNewLabel_2 = new JLabel("");
		panelRanking.add(lblNewLabel_2, BorderLayout.EAST);

		JLabel lblNewLabel_4 = new JLabel("");
		panelRanking.add(lblNewLabel_4, BorderLayout.SOUTH);

		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(50, 30));
		panel_1.setMinimumSize(new Dimension(50, 50));
		panelRanking.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(50, 50));
		panel_1.setBackground(new Color(17, 110, 141));

		JLabel lblPuntaje = new JLabel("PUNTAJE");
		lblPuntaje.setHorizontalTextPosition(SwingConstants.LEADING);
		lblPuntaje.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));
		lblPuntaje.setSize(new Dimension(0, 20));
		lblPuntaje.setForeground(Color.WHITE);
		panel_1.add(lblPuntaje, BorderLayout.EAST);

		JLabel lblPosición = new JLabel("POS");
		lblPosición.setBorder(BorderFactory.createEmptyBorder(0, 17, 0, 0));
		lblPosición.setForeground(Color.WHITE);
		panel_1.add(lblPosición, BorderLayout.WEST);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setForeground(Color.WHITE);
		panel_1.add(lblNombre, BorderLayout.CENTER);

		frame.getContentPane().add(splitPane);

		panelRanking.setPreferredSize(panelJuego.getPreferredSize());

		table = new JTable();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.setEnabled(false);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("");
		model.addColumn("");
		model.addColumn("");
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


		table.setRowHeight(30);
		table.setBackground(new Color(230, 230, 230));
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);


		panelRanking.add(table, BorderLayout.CENTER);

		// Init juego
		this.juego = new Juego(config.TAMAÑO_MATRIZ);

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
					juego.escribirDatosEnArchivo(nombre);
					frame.dispose();
					Win winScreen = new Win(nombre, juego.getPuntaje());
					winScreen.win(nombre, juego.getPuntaje());
				}

				if (juego.jugadorPerdio() || e.getKeyCode() == KeyEvent.VK_2) {
					juego.escribirDatosEnArchivo(nombre);
					frame.dispose();
					GameOver goScreen = new GameOver(nombre, juego.getPuntaje());
					goScreen.gameOver(nombre, juego.getPuntaje());
				}
				actualizarPantalla(panel);
				actualizarPuntaje();
			}
		}

		);

		actualizarPantalla(panel);
		
		List<Archivo.RankingEntry> ranking = juego.leerRanking();
        mostrarRanking(ranking);
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

	 // Método para llenar la tabla
    private static void mostrarRanking(List<Archivo.RankingEntry> ranking) {
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
    	
    	int filasFaltantes = 13 - model.getRowCount();

        for (int i=0; i<filasFaltantes; i++) {
        	model.addRow(new Object[]{"", "", ""});
        }
        
        for (int i = 0; i < ranking.size(); i++) {
            Archivo.RankingEntry entry = ranking.get(i);
            model.setValueAt(i + 1, i, 0);
            model.setValueAt(entry.getNombre(), i, 1);
            model.setValueAt(entry.getPuntaje(), i, 2);
        }
    }
}
