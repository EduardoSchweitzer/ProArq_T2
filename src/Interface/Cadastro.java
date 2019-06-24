package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class Cadastro {

	private JFrame frmCadastro;

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
		frmCadastro = new CadastroDecorator();
	}
}
