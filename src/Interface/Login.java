package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Login {

	private JFrame frmLogin;

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
		frmLogin = new LoginDecorator();
	}
}
