package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class CriarLeilao {

	private JFrame frmCriarLeilo;

	/**
	 * Launch the application.
	 */
	public static void criarMain(Negocio.Usuario usuarioAtual) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarLeilao window = new CriarLeilao(usuarioAtual);
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
	public CriarLeilao(Negocio.Usuario usuarioAtual) {
		frmCriarLeilo = new CriarLeilaoDecorator(usuarioAtual);
	}
}
