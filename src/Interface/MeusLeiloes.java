package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MeusLeiloes {

	private JFrame frmMeusLeiles;
	
	/**
	 * Launch the application.
	 * @return 
	 */
	public static void showMeusLeilos(Negocio.Usuario usuarioAtual) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeusLeiloes window = new MeusLeiloes(usuarioAtual);
					window.frmMeusLeiles.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MeusLeiloes(Negocio.Usuario usuarioAtual) {
		frmMeusLeiles = new MeusLeiloesDecorator(usuarioAtual);
	}
}
