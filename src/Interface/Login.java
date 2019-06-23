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
import java.awt.Color;
import java.awt.Component;
import javax.swing.SwingConstants;

public class Login {

	private JFrame frmLogin;
	private JTextField tfEmail;
	private JTextField tfSenha;

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
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(50, 114, 33, 19);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmLogin.getContentPane().add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(50, 167, 40, 19);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmLogin.getContentPane().add(lblSenha);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(50, 133, 285, 20);
		frmLogin.getContentPane().add(tfEmail);
		tfEmail.setColumns(10);
		
		tfSenha = new JTextField();
		tfSenha.setBounds(50, 186, 285, 20);
		frmLogin.getContentPane().add(tfSenha);
		tfSenha.setColumns(10);
		
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(50, 256, 285, 23);
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(50, 285, 285, 23);
		
		JLabel lblAviso = new JLabel("");
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAviso.setBounds(50, 220, 285, 22);
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmLogin.getContentPane().add(lblAviso);
		frmLogin.getContentPane().add(btnEntrar);
		frmLogin.getContentPane().add(btnCadastrar);
		
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Negocio.Usuario usuarioAutual = null;
				boolean invalido = false;
				try {
					usuarioAutual = new Negocio.UsuarioFachada().buscarPorEmail(tfEmail.getText());
					
				} catch (Negocio.UsuarioException exc) {
					lblAviso.setText("Email invalido");
					invalido = true;
				}
				
				if (usuarioAutual != null) {
					frmLogin.dispose();
					LeilaoMainInterface.leilaoMain();
				} else {
					if (!invalido) {
						lblAviso.setText("Usuario nao econtrado");
					}
				}
			}
		});
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLogin.dispose();
				Cadastro.cadastroMain();
			}
		});
	}
}
