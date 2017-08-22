package fenetres;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

public final class FenetreClient extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -629171735000729382L;

	//Donnees membres
	public static PartieVisiteur partieVisiteur;
	public static PartieEmploye partieEmploye;
	//private TimerListener timerListener = new TimerListener();
	//private Timer timerFenetreClient = new Timer(1000, timerListener);
	private static JPanel fenetreClient;


	// Constructeur de la fenetre
	public FenetreClient() {
		fenetreClient = (JPanel) this.getContentPane();
		fenetreClient.setMinimumSize(new Dimension(800, 600));
		fenetreClient.setLayout(new BorderLayout(5, 5));
		fenetreClient.setVisible(true);

		//timer de verification de connexion
		//timerFenetreClient.start();

		//Par defaut l'application s'ouvrre sur la partie visiteur
		partieVisiteur = new PartieVisiteur();
		
		fenetreClient.add(partieVisiteur, BorderLayout.CENTER);
		System.out.println("Constructeur atteint : Fenetre client");
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

	//Listeners
	//Timer de l'application, verifie toutes les 1000ms si la connexion Ã  la partie employe est correct
	//S'occupe aussi du remplissage de la fenetre principale, en fonction du statut de connexion
	/*private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(partieVisiteur.getModuleHeader().getFenetreConnexion().isEstConnecte()) {
				fenetreClient.removeAll();
				fenetreClient.add(partieEmploye = new PartieEmploye());
				fenetreClient.validate();
				fenetreClient.repaint();
			}else {
				fenetreClient.removeAll();
				fenetreClient.add(partieVisiteur);
				fenetreClient.validate();
				fenetreClient.repaint();
			}
		}

	}*/
}
