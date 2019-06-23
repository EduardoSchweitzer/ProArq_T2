package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Negocio.Leilao;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.Dimension;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class LeilaoAtual {

	private JFrame frame;
	private static JLabel lblValueLabel;
	private static JLabel lblProductLabel;
	private JScrollPane scrollPane;
	private JButton btnDarLance;
	private JTextField textField;
	private Leilao leilao;
	private Negocio.Usuario usuarioAtual;
	private JTable table;
	private JPanel panel;
	private JLabel lblMaiorLance;
	private JLabel lblLanceInvalido;


	/**
	 * Launch the application.
	 */
	public static void leilaoAtualMain(Leilao leilao, Negocio.Usuario usuarioAtual) {
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
	public LeilaoAtual(Leilao leilao, Negocio.Usuario usuarioAtual) {
		this.leilao = leilao;
		this.usuarioAtual = usuarioAtual;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setMinimumSize(new Dimension(300, 300));
		frame.setBounds(100, 100, 300, 300);
		frame.setTitle(leilao.getNomeProduto());
		frame.getContentPane().setLayout(null);
		
		
		lblProductLabel = new JLabel(leilao.getNomeProduto());
		lblProductLabel.setBounds(24, 30, 236, 22);
		lblProductLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProductLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblProductLabel);
		
		panel = new JPanel();
		panel.setBounds(0, 84, 282, 29);
		frame.getContentPane().add(panel);
		
		lblMaiorLance = new JLabel("Maior Lance: ");
		lblMaiorLance.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblMaiorLance);
		
		lblValueLabel = new JLabel(String.valueOf(leilao.getMaiorLance().getValor()));
		panel.add(lblValueLabel);
		lblValueLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setBounds(39, 136, 197, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnDarLance = new JButton("Dar lance");
		
		btnDarLance.setBounds(39, 167, 197, 27);
		btnDarLance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frame.getContentPane().add(btnDarLance);
		
		lblLanceInvalido = new JLabel("");
		lblLanceInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanceInvalido.setBounds(39, 206, 210, 22);
		lblLanceInvalido.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLanceInvalido.setForeground(Color.RED);
		frame.getContentPane().add(lblLanceInvalido);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 260, 2, 1);
		frame.getContentPane().add(scrollPane);
		
		frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	LeilaoMainInterface.leilaoMain(usuarioAtual);
                e.getWindow().dispose();
            }
        });
		
		btnDarLance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblLanceInvalido.setText("");
				try {
					Negocio.Leilao checarBanco = new Negocio.LeilaoFachada().buscarPorId(leilao.getId());
					if (leilao.getUltimaModificacao() < checarBanco.getUltimaModificacao()) {
						leilao = checarBanco;
						lblValueLabel.setText(String.valueOf(leilao.getMaiorLance().getValor()));
						lblLanceInvalido.setText("Houve outro lance.");
					} else {
						leilao.setUltimaModificacao(leilao.getUltimaModificacao()+1);
						new Negocio.LeilaoFachada().addLance(
								Double.valueOf(textField.getText()), 
								usuarioAtual.getCpf(), 
								leilao.getId());
						lblValueLabel.setText(String.valueOf(new Negocio.LeilaoFachada().buscarPorId(leilao.getId()).getMaiorLance().getValor()));
					}
				} catch (Negocio.LeilaoException exc) {
					lblLanceInvalido.setText("Valor invalido");
				} catch (Persistencia.LeilaoDAOIdInexistenteException exc) {
					
				}
			}
		});
	}
}
