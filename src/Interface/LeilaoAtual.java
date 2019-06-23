package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Negocio.Leilao;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Dimension;

public class LeilaoAtual {

	private JFrame frame;
	private static JLabel lblValueLabel;
	private static JLabel lblProductLabel;
	private JScrollPane scrollPane;
	private JButton btnDarLance;
	private JTextField textField;
	private Leilao leilao;
	private JTable table;
	private JPanel panel;
	private JLabel lblMaiorLance;
	private JLabel label;


	/**
	 * Launch the application.
	 */
	public static void leilaoAtualMain(Leilao leilao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoAtual window = new LeilaoAtual(leilao);
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
	public LeilaoAtual(Leilao leilao) {
		this.leilao = leilao;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(300, 300));
		frame.setBounds(100, 100, 300, 300);
		frame.setTitle(leilao.getNomeProduto());
		frame.getContentPane().setLayout(null);
		
		
		lblProductLabel = new JLabel(leilao.getNomeProduto());
		lblProductLabel.setBounds(88, 34, 106, 22);
		lblProductLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblProductLabel);
		
		panel = new JPanel();
		panel.setBounds(0, 88, 282, 29);
		frame.getContentPane().add(panel);
		
		lblMaiorLance = new JLabel("Maior Lance: ");
		lblMaiorLance.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblMaiorLance);
		
		lblValueLabel = new JLabel(String.valueOf(leilao.getMaiorLance().getValor()));
		panel.add(lblValueLabel);
		lblValueLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setBounds(68, 140, 140, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnDarLance = new JButton("Dar lance");
		btnDarLance.setBounds(68, 166, 140, 27);
		btnDarLance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(btnDarLance);
		
		label = new JLabel("");
		label.setBounds(0, 219, 282, 0);
		frame.getContentPane().add(label);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 246, 2, -30);
		frame.getContentPane().add(scrollPane);
	}
}
