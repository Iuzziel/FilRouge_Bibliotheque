package panneaux;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

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
		panEmprunt.setLayout(new BoxLayout(panEmprunt, BoxLayout.Y_AXIS));
		panEmprunt.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// tabEmpruntRetour/panEmprunt/*
		JLabel lblEmpruntTitre = new JLabel("Livre selectionne(s) : ");
		panEmprunt.add(lblEmpruntTitre);
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
		srlLivreEmpruntScanne.setViewportBorder(new BevelBorder(BevelBorder.LOWERED));
		panEmprunt.add(srlLivreEmpruntScanne);
		JProgressBar pgsBarEmprunt = new JProgressBar();
		panEmprunt.add(pgsBarEmprunt);
		JButton btnEmpruntEnregistrerFinal = new JButton("Valider Emprunt");
		panEmprunt.add(btnEmpruntEnregistrerFinal);

		// tabEmpruntRetour/panRetour
		JPanel panRetour = new JPanel();
		tabEmpruntRetour.addTab("Retour", panRetour);
		panRetour.setLayout(new BoxLayout(panRetour, BoxLayout.Y_AXIS));
		panRetour.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		// tabEmpruntRetour/panRetour/*
		JLabel lblRetourTitre = new JLabel("Livre selectionne(s) : ");
		panRetour.add(lblRetourTitre);
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
		srlLivreRetourScanne.setViewportBorder(new BevelBorder(BevelBorder.LOWERED));
		panRetour.add(srlLivreRetourScanne);
		JProgressBar pgsBarRetour = new JProgressBar();
		panRetour.add(pgsBarRetour);
		JButton btnValiderRetour = new JButton("Valider Retour");
		panRetour.add(btnValiderRetour);
	}

	// Methodes
	public void remplirListeLivreScan(String codeExemplaire) {
		// TODO Transformer le codeExemplaire en une requete pour aller chercher le livre correspondant et ajouter ca au tableau
		this.repaint();
		return;
	}
}
