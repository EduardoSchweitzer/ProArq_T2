package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class LeilaoAtual {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void leilaoAtualMain(Negocio.Leilao leilao, Negocio.Usuario usuarioAtual) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoAtual window = new LeilaoAtual(leilao, usuarioAtual);
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
	public LeilaoAtual(Negocio.Leilao leilao, Negocio.Usuario usuarioAtual) {
		frame = new LeilaoAtualDecorator(leilao, usuarioAtual);
	}
}
