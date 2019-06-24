package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;


public class LeilaoMainInterfaceAdmin {

	private JFrame frmPaginaInicial;
	/**
	 * Launch the application.
	 */
	public static void leilaoMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoMainInterfaceAdmin window = new LeilaoMainInterfaceAdmin();
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
	public LeilaoMainInterfaceAdmin() {
		frmPaginaInicial = new InterfaceAtivosAdminDecorator();
	}
}
