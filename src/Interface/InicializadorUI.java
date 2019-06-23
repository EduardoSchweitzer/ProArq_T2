package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class InicializadorUI {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login.loginMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
