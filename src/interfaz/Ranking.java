package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import logica.Archivo;

public class Ranking {

	private JTable table;
	private JPanel panelRanking;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ranking() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		crearPanelRanking();

		configurarBordes();

		panelRanking.setLayout(new BorderLayout(0, 0));

		JPanel panelHeader = crearPanelHeader();

		configurarHeader(panelHeader);

		crearTabla();

	}

	private void crearTabla() {
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
	}

	private void configurarHeader(JPanel panelHeader) {
		JLabel lblPuntaje = new JLabel("PUNTAJE");
		lblPuntaje.setHorizontalTextPosition(SwingConstants.LEADING);
		lblPuntaje.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 35));
		lblPuntaje.setSize(new Dimension(0, 20));
		lblPuntaje.setForeground(Color.WHITE);
		panelHeader.add(lblPuntaje, BorderLayout.EAST);

		JLabel lblPosición = new JLabel("POS");
		lblPosición.setBorder(BorderFactory.createEmptyBorder(0, 17, 0, 0));
		lblPosición.setForeground(Color.WHITE);
		panelHeader.add(lblPosición, BorderLayout.WEST);

		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setForeground(Color.WHITE);
		panelHeader.add(lblNombre, BorderLayout.CENTER);
	}

	private JPanel crearPanelHeader() {
		JPanel panelHeader = new JPanel();
		panelHeader.setPreferredSize(new Dimension(50, 30));
		panelHeader.setMinimumSize(new Dimension(50, 50));
		panelRanking.add(panelHeader, BorderLayout.NORTH);
		panelHeader.setLayout(new BorderLayout(50, 50));
		panelHeader.setBackground(new Color(17, 110, 141));
		return panelHeader;
	}

	private void configurarBordes() {
		Border emptyBorder2 = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		Border lineBorder2 = BorderFactory.createLineBorder(new Color(17, 110, 141), 2);
		Border compoundBorder2 = BorderFactory.createCompoundBorder(emptyBorder2, lineBorder2);
		panelRanking.setBorder(compoundBorder2);
	}

	private void crearPanelRanking() {
		panelRanking = new JPanel();
		panelRanking.setBackground(new Color(240, 240, 240));
	}

	public JPanel obtenerPanelRanking() {
		return this.panelRanking;
	}

	public JTable obtenerTabla() {
		return this.table;
	}

	public void mostrarRanking(List<Archivo.RankingEntry> ranking) {
		DefaultTableModel model = (DefaultTableModel) this.obtenerTabla().getModel();

		int filasFaltantes = 13 - model.getRowCount();

		for (int i = 0; i < filasFaltantes; i++) {
			model.addRow(new Object[] { "", "", "" });
		}

		for (int i = 0; i < tamanoRanking(ranking); i++) {
			Archivo.RankingEntry entry = ranking.get(i);
			model.setValueAt(i + 1, i, 0);
			model.setValueAt(entry.getNombre(), i, 1);
			model.setValueAt(entry.getPuntaje(), i, 2);
		}
	}

	private int tamanoRanking(List<Archivo.RankingEntry> ranking) {
		if (ranking.size() >= 13) {
			return 13;
		}
		return ranking.size();
	}

}
