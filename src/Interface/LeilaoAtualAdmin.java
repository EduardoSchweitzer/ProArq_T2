package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class LeilaoAtualAdmin {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void leilaoAtualMain(Negocio.Leilao leilao) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoAtualAdmin window = new LeilaoAtualAdmin(leilao);
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
	public LeilaoAtualAdmin(Negocio.Leilao leilao) {
		frame = new LeilaoAtualAdminDecorator(leilao);
	}
}
