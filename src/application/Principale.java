package application;

import fenetres.*;

public class Principale {
	// *********************************Debut de la Main*********************************//
	public static void main(String[] args) {
		FenetreClient fenetre = new FenetreClient();
		fenetre.setVisible(true);
		FenetreEmploye fenetreEmploye = new FenetreEmploye();
		fenetreEmploye.setVisible(true);
	}

}
