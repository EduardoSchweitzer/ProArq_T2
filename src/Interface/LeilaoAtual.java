package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Negocio.Leilao;
import java.awt.Font;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class LeilaoAtual {

	private JFrame frame;
	private static JLabel lblValueLabel;
	private static JLabel lblProductLabel;
	private JList list;
	private JScrollPane scrollPane;
	private JButton btnDarLance;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void leilaoAtualMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoAtual window = new LeilaoAtual();
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
	public LeilaoAtual() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 593, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblProductLabel = new JLabel("New label");
		lblProductLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductLabel.setBounds(38, 24, 109, 45);
		frame.getContentPane().add(lblProductLabel);
		
		lblValueLabel = new JLabel("New label");
		lblValueLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblValueLabel.setBounds(289, 24, 109, 45);
		frame.getContentPane().add(lblValueLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 339, 188, -224);
		frame.getContentPane().add(scrollPane);
		
		list = new JList();
		scrollPane.setViewportView(list);
		list.setBackground(Color.DARK_GRAY);
		
		btnDarLance = new JButton("Dar lance");
		btnDarLance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnDarLance.setBounds(337, 111, 139, 65);
		frame.getContentPane().add(btnDarLance);
		
		textField = new JTextField();
		textField.setBounds(337, 211, 139, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	public static void recebeLeilao(Leilao leilao) {
		lblProductLabel.setText(leilao.getNomeProduto());
		lblValueLabel.setText(String.valueOf(leilao.getMaiorLance().getValor()));
		
	}

}
