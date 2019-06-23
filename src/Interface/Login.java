package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Dimension;

public class Login {

	private JFrame frmLogin;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void loginMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setResizable(false);
		frmLogin.setMaximumSize(new Dimension(400, 400));
		frmLogin.setMinimumSize(new Dimension(400, 400));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 400, 400);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblEntrar = new JLabel("Entrar");
		lblEntrar.setBounds(154, 49, 76, 34);
		lblEntrar.setFont(new Font("Tahoma", Font.PLAIN, 28));
		frmLogin.getContentPane().add(lblEntrar);
		
		JLabel lblUsurio = new JLabel("Email");
		lblUsurio.setBounds(50, 114, 33, 19);
		lblUsurio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmLogin.getContentPane().add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(50, 166, 40, 19);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmLogin.getContentPane().add(lblSenha);
		
		textField = new JTextField();
		textField.setBounds(50, 133, 285, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(50, 185, 285, 20);
		frmLogin.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(50, 255, 285, 23);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				LeilaoMainInterface.leilaoMain();
			}
		});
		frmLogin.getContentPane().add(btnEntrar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(50, 284, 285, 23);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				Cadastro.cadastroMain();
			}
		});
		frmLogin.getContentPane().add(btnCadastrar);
	}
}
