package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import br.com.caelum.argentum.Negocio;

public class ArgentumUI {
	private JFrame janela;
	private JPanel painelPrincpal;
	private JTable tabela;

	public static void main(String[] args) {

		new ArgentumUI().montaTela();

	}

	private void montaTela() {
		setNimbusLookAndFeel();
		preparaJanela();
		preparaPainelPrincipal();
		preparaTitulo();
		preparaTabela();
		preparaBotaoCarregar();
		preparaBotaoSair();
		mostraJanela();

	}

	private void preparaJanela() {
		janela = new JFrame("Argentum");
		janela.setResizable(false);		
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void preparaPainelPrincipal() {
		painelPrincpal = new JPanel();
		janela.add(painelPrincpal);

	}
	
	public void preparaTabela() {
		tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.black));
		tabela.setGridColor(Color.black);
		tabela.setShowGrid(true);
		
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela);
		scroll.setSize(450, 450);
		
		painelPrincpal.add(scroll);
	}
	

	private void preparaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Negocio> lista  = new EscolhedorDeXML().escolhe();
				NegociosTableModel ntm = new NegociosTableModel(lista);				
				tabela.setModel(ntm);
			}
		});
		painelPrincpal.add(botaoCarregar);
	}

	private void preparaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		painelPrincpal.add(botaoSair);
	}
	
	
	private void setNimbusLookAndFeel() {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (UnsupportedLookAndFeelException e) {
		    // handle exception
		} catch (ClassNotFoundException e) {
		    // handle exception
		} catch (InstantiationException e) {
		    // handle exception
		} catch (IllegalAccessException e) {
		    // handle exception
		}
	}
	
	private void preparaTitulo() {
		JLabel titulo = new JLabel("Lista de Negocios", SwingConstants.CENTER);
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		painelPrincpal.add(titulo);
	}

	private void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
		
	}

}
