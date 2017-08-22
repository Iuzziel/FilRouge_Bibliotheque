package fenetres;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public final class FenetrePrincipale extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -629171735000729382L;

	//Donnees membres
	public static PartieVisiteur partieVisiteur;
	public static PartieEmploye partieEmploye;
	public static JPanel contentPane;

	// Constructeur de la fenetre
	public FenetrePrincipale() {
		contentPane = (JPanel) this.getContentPane();
		contentPane.setMinimumSize(new Dimension(800, 600));
		contentPane.setLayout(new BorderLayout(5, 5));
		contentPane.setVisible(true);

		//Par defaut l'application s'ouvrre sur la partie visiteur
		partieVisiteur = new PartieVisiteur();
		
		contentPane.add(partieVisiteur, BorderLayout.CENTER);
		System.out.println("Constructeur atteint : Fenetre client");
	}

	//Accesseurs
	public static PartieVisiteur getPartieVisiteur() {
		return partieVisiteur;
	}

	public static void setPartieVisiteur(PartieVisiteur partieVisiteur) {
		FenetrePrincipale.partieVisiteur = partieVisiteur;
	}

	public static PartieEmploye getPartieEmploye() {
		return partieEmploye;
	}

	public static void setPartieEmploye(PartieEmploye partieEmploye) {
		FenetrePrincipale.partieEmploye = partieEmploye;
	}

	public static void changerPartieClient() {
		if(FenetreConnexion.isEstConnecte()) {
			FenetrePrincipale.contentPane.remove(FenetrePrincipale.partieVisiteur);
			FenetrePrincipale.setPartieEmploye(new PartieEmploye());
			FenetrePrincipale.contentPane.add(FenetrePrincipale.partieEmploye);
			FenetrePrincipale.contentPane.validate();
			FenetrePrincipale.contentPane.repaint();
		}else{
			FenetrePrincipale.contentPane.remove(FenetrePrincipale.partieEmploye);
			FenetrePrincipale.contentPane.add(FenetrePrincipale.partieVisiteur);
			FenetrePrincipale.contentPane.validate();
			FenetrePrincipale.contentPane.repaint();
		}		
	}
}
