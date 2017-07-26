package fenetres;

import javax.swing.JPanel;
import javax.swing.JWindow;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class FenetreClient extends JWindow {
	/**
	 * 
	 */
	private static final long serialVersionUID = -629171735000729382L;

	//Donnees membres
	public static PartieVisiteur partieVisiteur;
	public static PartieEmploye partieEmploye;
	private static JPanel fenetreClient = new JPanel();
	private static boolean boolVisiteur;


	// Constructeur de la fenetre
	public FenetreClient() {
		fenetreClient = new JPanel();
		this.getContentPane().add(fenetreClient);
		fenetreClient.setMinimumSize(new Dimension(800, 600));
		fenetreClient.setLayout(new BorderLayout(5, 5));
		fenetreClient.setVisible(true);

		//Par defaut l'application s'ouvrre sur la partie visiteur
		partieVisiteur = new PartieVisiteur();
		boolVisiteur = true;
		fenetreClient.add(partieVisiteur, BorderLayout.CENTER);
		System.out.println("Constructeur atteint : Fenetre client");
	}

	//Methode
	public static void changerPartieClient(){
		if (boolVisiteur) {
			fenetreClient.remove(partieVisiteur);
			fenetreClient.add(partieEmploye);
			fenetreClient.validate();
			fenetreClient.repaint();
		}else{
			fenetreClient.remove(partieEmploye);
			fenetreClient.add(partieVisiteur);
			fenetreClient.validate();
			fenetreClient.repaint();
		}
	}

	//Accesseurs
	public static PartieVisiteur getPartieVisiteur() {
		return partieVisiteur;
	}

	public static void setPartieVisiteur(PartieVisiteur partieVisiteur) {
		FenetreClient.partieVisiteur = partieVisiteur;
	}

	public static PartieEmploye getPartieEmploye() {
		return partieEmploye;
	}

	public static void setPartieEmploye(PartieEmploye partieEmploye) {
		FenetreClient.partieEmploye = partieEmploye;
	}

}
