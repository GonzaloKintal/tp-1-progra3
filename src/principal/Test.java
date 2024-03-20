package principal;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;

public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
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
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("2048");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(new GridLayout(4, 4, 0, 0));

        int[][] miMatriz = {
        		{0, 0, 0, 0},
        		{0, 0, 1, 0},
        		{0, 0, 0, 0},
        		{0, 0, 1, 0}
        };
        
        // Celdas
        for (int i = 0; i < miMatriz.length; i++) {
        	for(int j = 0; j < miMatriz[0].length; j++) {
        		JLabel label = new JLabel("1");
        		if(miMatriz[i][j] == 1) {
        			label.setBackground(Color.BLUE);        		
        		}
        		label.setForeground(Color.WHITE);
        		label.setOpaque(true);
        		label.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        		label.setBorder(new LineBorder(Color.BLACK));
        		label.setHorizontalAlignment(SwingConstants.CENTER);
        		panel.add(label);        		
        	}
        }
	}
	
	

}
