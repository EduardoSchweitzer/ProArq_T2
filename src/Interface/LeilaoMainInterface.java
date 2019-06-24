package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class LeilaoMainInterface {

	private JFrame frmPaginaInicial;

	/**
	 * Launch the application.
	 */
	public static void leilaoMain(Negocio.Usuario usuarioAtual) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoMainInterface window = new LeilaoMainInterface(usuarioAtual);
					window.frmPaginaInicial.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LeilaoMainInterface(Negocio.Usuario usuarioAtual) {
		frmPaginaInicial = new LeilaoMainInterfaceDecorator(usuarioAtual);
	}
}
