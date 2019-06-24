package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class LeilaoAtualProponente {

	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void leilaoAtualMain(Negocio.Leilao leilao, Negocio.Usuario usuarioAtual) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeilaoAtualProponente window = new LeilaoAtualProponente(leilao, usuarioAtual);
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
	public LeilaoAtualProponente(Negocio.Leilao leilao, Negocio.Usuario usuarioAtual) {
		frame = new LeilaoAtualProponenteDecorator(leilao, usuarioAtual);
	}
}
