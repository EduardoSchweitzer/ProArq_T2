package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CriarLeilao {

	private JFrame frmCriarLeilo;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void criarMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarLeilao window = new CriarLeilao();
					window.frmCriarLeilo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CriarLeilao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCriarLeilo = new JFrame();
		frmCriarLeilo.setTitle("Criar Leil\u00E3o");
		frmCriarLeilo.setBounds(100, 100, 564, 379);
		frmCriarLeilo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCriarLeilo.getContentPane().setLayout(null);
		
		JLabel lblNomeDoProduto = new JLabel("Nome do produto");
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoProduto.setBounds(10, 32, 147, 24);
		frmCriarLeilo.getContentPane().add(lblNomeDoProduto);
		
		JLabel lblValorInicial = new JLabel("Valor Inicial");
		lblValorInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorInicial.setBounds(10, 104, 147, 24);
		frmCriarLeilo.getContentPane().add(lblValorInicial);
		
		JLabel lblCpfDoCriador = new JLabel("Cpf do criador");
		lblCpfDoCriador.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCpfDoCriador.setBounds(10, 183, 147, 17);
		frmCriarLeilo.getContentPane().add(lblCpfDoCriador);
		
		textField = new JTextField();
		textField.setBounds(10, 73, 179, 20);
		frmCriarLeilo.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 139, 179, 20);
		frmCriarLeilo.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 217, 179, 20);
		frmCriarLeilo.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.setBounds(347, 73, 116, 52);
		frmCriarLeilo.getContentPane().add(btnCriar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeilaoMainInterface.leilaoMain();
			}
		});
		btnCancelar.setBounds(347, 167, 116, 52);
		frmCriarLeilo.getContentPane().add(btnCancelar);
	}

}
