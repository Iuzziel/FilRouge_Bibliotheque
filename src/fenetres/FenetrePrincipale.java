package fenetres;

import javax.swing.JFrame;
import javax.swing.JPanel;
import dao.Parametre;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

public final class FenetrePrincipale extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -629171735000729382L;

	//Donnees membres
	public static PartieVisiteur partieVisiteur;
	public static PartieEmploye partieEmploye;
	public static JPanel contentPane;
	public static Vector<Parametre> vectParametre = new Vector<Parametre>();

	// Constructeur de la fenetre
	public FenetrePrincipale() {
		this.setMinimumSize(new Dimension(800, 600));
		contentPane = (JPanel) this.getContentPane();
		contentPane.setMinimumSize(new Dimension(800, 600));
		contentPane.setLayout(new BorderLayout(5, 5));
		contentPane.setVisible(true);

		//Par defaut l'application s'ouvrre sur la partie visiteur
		partieVisiteur = new PartieVisiteur();

		contentPane.add(partieVisiteur, BorderLayout.CENTER);
	}

	//Methodes
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

}
