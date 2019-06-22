package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class MeusLeiloes {

	private JFrame frmMeusLeiles;

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void showMeusLeilos() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeusLeiloes window = new MeusLeiloes();
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
	public MeusLeiloes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMeusLeiles = new JFrame();
		frmMeusLeiles.setTitle("Meus Leil\u00F5es");
		frmMeusLeiles.setBounds(100, 100, 613, 433);
		frmMeusLeiles.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMeusLeiles.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 597, 394);
		frmMeusLeiles.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
	}

}
