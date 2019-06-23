package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class CriarLeilao {

	private JFrame frmCriarLeilo;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void criarMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriarLeilao window = new CriarLeilao();
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
	public CriarLeilao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCriarLeilo = new JFrame();
		frmCriarLeilo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCriarLeilo.setResizable(false);
		frmCriarLeilo.setTitle("Criar Leil\u00E3o");
		frmCriarLeilo.setBounds(100, 100, 350, 300);
		frmCriarLeilo.getContentPane().setLayout(null);
		
		JLabel lblCriarNovoLeilo = new JLabel("Criar novo leil\u00E3o");
		lblCriarNovoLeilo.setBounds(78, 27, 199, 34);
		lblCriarNovoLeilo.setFont(new Font("Tahoma", Font.PLAIN, 28));
		frmCriarLeilo.getContentPane().add(lblCriarNovoLeilo);
		
		JLabel lblNomeDoProduto = new JLabel("Nome do produto");
		lblNomeDoProduto.setBounds(45, 86, 265, 24);
		lblNomeDoProduto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmCriarLeilo.getContentPane().add(lblNomeDoProduto);
		
		textField = new JTextField();
		textField.setBounds(45, 110, 265, 20);
		frmCriarLeilo.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblValorInicial = new JLabel("Valor Inicial");
		lblValorInicial.setBounds(45, 130, 265, 24);
		lblValorInicial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmCriarLeilo.getContentPane().add(lblValorInicial);
		
		textField_1 = new JTextField();
		textField_1.setBounds(45, 154, 265, 20);
		frmCriarLeilo.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCriar = new JButton("Criar");
		btnCriar.setBounds(45, 185, 265, 23);
		frmCriarLeilo.getContentPane().add(btnCriar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(45, 214, 265, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCriarLeilo.dispose();
				LeilaoMainInterface.leilaoMain();
			}
		});
		frmCriarLeilo.getContentPane().add(btnCancelar);
		
		frmCriarLeilo.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                LeilaoMainInterface.leilaoMain();
                e.getWindow().dispose();
            }
        });
	}

}
