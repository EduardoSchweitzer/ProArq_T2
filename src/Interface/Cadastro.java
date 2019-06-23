package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Dimension;

public class Cadastro {

	private JFrame frmCadastro;
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfEmail;
	private JPasswordField pfSenha;
	private JPasswordField pfConfirmarSenha;

	/**
	 * Launch the application.
	 */
	public static void cadastroMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro window = new Cadastro();
					window.frmCadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastro = new JFrame();
		frmCadastro.setMinimumSize(new Dimension(400, 500));
		frmCadastro.setTitle("Cadastro");
		frmCadastro.setBounds(100, 100, 400, 500);
		frmCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);
		
		JLabel lblNovaConta = new JLabel("Criar uma nova conta");
		lblNovaConta.setBounds(58, 11, 268, 34);
		frmCadastro.getContentPane().add(lblNovaConta);
		lblNovaConta.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(45, 59, 36, 17);
		frmCadastro.getContentPane().add(lblNome);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfNome = new JTextField();
		tfNome.setBounds(45, 82, 294, 20);
		frmCadastro.getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(45, 108, 31, 17);
		frmCadastro.getContentPane().add(lblEmail);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfEmail = new JTextField();
		tfEmail.setBounds(45, 131, 294, 20);
		frmCadastro.getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblCpf = new JLabel("Cpf");
		lblCpf.setBounds(45, 157, 21, 17);
		frmCadastro.getContentPane().add(lblCpf);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		tfCpf = new JTextField();
		tfCpf.setBounds(45, 180, 294, 20);
		frmCadastro.getContentPane().add(tfCpf);
		tfCpf.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(45, 206, 38, 17);
		frmCadastro.getContentPane().add(lblSenha);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		pfSenha = new JPasswordField();
		pfSenha.setBounds(45, 229, 294, 20);
		frmCadastro.getContentPane().add(pfSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar senha");
		lblConfirmarSenha.setBounds(45, 255, 294, 17);
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmCadastro.getContentPane().add(lblConfirmarSenha);
		
		pfConfirmarSenha = new JPasswordField();
		pfConfirmarSenha.setBounds(45, 278, 294, 20);
		frmCadastro.getContentPane().add(pfConfirmarSenha);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Admin");
		chckbxNewCheckBox.setBounds(45, 304, 70, 23);
		frmCadastro.getContentPane().add(chckbxNewCheckBox);
		
		JPanel panel = new JPanel();
		panel.setBounds(58, 355, 268, 34);
		frmCadastro.getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		btnCancelar.setMinimumSize(new Dimension(90, 25));
		btnCancelar.setMaximumSize(new Dimension(90, 25));
		
		JButton btnConfirmar = new JButton("Confirmar");
		panel.add(btnConfirmar);
		btnConfirmar.setMaximumSize(new Dimension(90, 25));
		btnConfirmar.setMinimumSize(new Dimension(90, 25));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LeilaoMainInterface.leilaoMain();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login.loginMain();
			}
		});
		
		JLabel lblXInvalido = new JLabel("");
		lblXInvalido.setBounds(192, 432, 0, 0);
		lblXInvalido.setForeground(Color.RED);
		lblXInvalido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmCadastro.getContentPane().add(lblXInvalido);
	}
}
