package panneaux;

import dao.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import java.awt.BorderLayout;

public class EmpruntRetour extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2734805873664823251L;

	// Donnees membres
	private JTable jTabLivreEmpruntScan;
	private JScrollPane srlLivreEmpruntScanne;
	private JTable jTabLivreRetourScan;
	private JScrollPane srlLivreRetourScanne;
	private JButton btnEmprAjouter = new JButton("Ajouter");
	private JButton btnEmprSupprimer = new JButton("Supprimer");
	private JButton btnEmpruntEnregistrerFinal = new JButton("Valider Emprunt");
	private JButton btnRetAjouter = new JButton("Ajouter");
	private JButton btnRetSupprimer = new JButton("Supprimer");
	private JButton btnValiderRetour = new JButton("Valider Retour");

	// Constructeur
	public EmpruntRetour() {
		setMinimumSize(new Dimension(200, 200));
		setPreferredSize(new Dimension(375, 250));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Selection Emprunt/Retour", TitledBorder.LEADING, TitledBorder.TOP));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		// tabEmpruntRetour
		JTabbedPane tabEmpruntRetour = new JTabbedPane(JTabbedPane.TOP);
		add(tabEmpruntRetour);

		// tabEmpruntRetour/panEmprunt
		JPanel panEmprunt = new JPanel();
		tabEmpruntRetour.addTab("Emprunt", panEmprunt);
		panEmprunt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panEmprunt.setLayout(new BorderLayout(0, 0));

		JPanel panEmprCentre = new JPanel();
		panEmprunt.add(panEmprCentre);
		panEmprCentre.setLayout(new BoxLayout(panEmprCentre, BoxLayout.Y_AXIS));

		// tabEmpruntRetour/panEmprunt/Centre
		JLabel lblEmpruntTitre = new JLabel("Livre selectionne(s) : ");
		panEmprCentre.add(lblEmpruntTitre);
		jTabLivreEmpruntScan = new JTable();
		jTabLivreEmpruntScan.setModel(new DefaultTableModel(
				new Object[][] {
					{"Titre 1", "Auteur 1", "Exemplaire 1", "Oui", "rue machin"},
					{"Titre 2", "Auteur 2", "Exemplaire 1", "Non", "rue machin"},
					{"Titre 3", "Auteur 3", "Exemplaire 1", "Oui", "rue truc"},
				},
				new String[] {
						"Titre", "Auteur", "N\u00B0", "Disponible", "Bibliotheque"
				}
				));
		srlLivreEmpruntScanne = new JScrollPane(jTabLivreEmpruntScan);
		panEmprCentre.add(srlLivreEmpruntScanne);
		srlLivreEmpruntScanne.setViewportBorder(new BevelBorder(BevelBorder.LOWERED));

		// tabEmpruntRetour/panEmprunt/Sud
		JPanel panEmprSud = new JPanel();
		panEmprunt.add(panEmprSud, BorderLayout.SOUTH);
		panEmprSud.add(btnEmprAjouter);
		panEmprSud.add(btnEmprSupprimer);
		panEmprSud.add(btnEmpruntEnregistrerFinal);

		// tabEmpruntRetour/panRetour
		JPanel panRetour = new JPanel();
		tabEmpruntRetour.addTab("Retour", panRetour);
		panRetour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panRetour.setLayout(new BorderLayout(0, 0));

		JPanel panRetCentre = new JPanel();
		panRetour.add(panRetCentre, BorderLayout.CENTER);
		panRetCentre.setLayout(new BoxLayout(panRetCentre, BoxLayout.Y_AXIS));

		// tabEmpruntRetour/panRetour/Centre
		JLabel lblRetourTitre = new JLabel("Livre selectionne(s) : ");
		panRetCentre.add(lblRetourTitre);
		jTabLivreRetourScan = new JTable();
		jTabLivreRetourScan.setModel(new DefaultTableModel(
				new Object[][] {
					{"Titre 1", "Auteur 1", "Exemplaire 1", "Oui", "rue machin"},
					{"Titre 2", "Auteur 2", "Exemplaire 1", "Non", "rue machin"},
					{"Titre 3", "Auteur 3", "Exemplaire 1", "Oui", "rue machin"},
				},
				new String[] {
						"Titre", "Auteur", "N\u00B0", "Disponible", "Bibliotheque"
				}
				));
		srlLivreRetourScanne = new JScrollPane(jTabLivreRetourScan);
		panRetCentre.add(srlLivreRetourScanne);
		srlLivreRetourScanne.setViewportBorder(new BevelBorder(BevelBorder.LOWERED));

		// tabEmpruntRetour/panRetour/Sud
		JPanel panRetSud = new JPanel();
		panRetour.add(panRetSud, BorderLayout.SOUTH);
		panRetSud.add(btnRetAjouter);
		panRetSud.add(btnRetSupprimer);
		panRetSud.add(btnValiderRetour);

		//Abonnement aux listeners
		btnEmprAjouter.addActionListener(new appActionListener());
		btnEmprSupprimer.addActionListener(new appActionListener());
		btnEmpruntEnregistrerFinal.addActionListener(new appActionListener());
		btnRetAjouter.addActionListener(new appActionListener());
		btnRetSupprimer.addActionListener(new appActionListener());
		btnValiderRetour.addActionListener(new appActionListener());
	}

	//Actions listeners
	private class appActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == btnEmprAjouter) {
			}
			if(e.getSource() == btnEmprSupprimer) {
			}
			if(e.getSource() == btnEmpruntEnregistrerFinal) {//TODO Temporairement un bouton test
				try {
					int temp = EmpruntManager.creerEmprunt(new Adherent(1));
					System.out.println("Pkg:panneaux-Class:EmpruntRetour\nValeur de la seq : " + temp);
				} catch (ClassNotFoundException | SQLException e1) {
					System.out.println("Pkg:panneaux-Class:EmpruntRetour");
					e1.printStackTrace();
				}
			}
			if(e.getSource() == btnRetAjouter) {
			}
			if(e.getSource() == btnRetSupprimer) {
			}
			if(e.getSource() == btnValiderRetour) {
			}
		}
	}

	// Methodes
	public void remplirListeLivreScan(String codeExemplaire) {
		// TODO Transformer le codeExemplaire en une requete pour aller chercher le livre correspondant et ajouter ca au tableau
		this.repaint();
		return;
	}
}
