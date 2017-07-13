package application;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import fenetres.*;

public class Principale {
	//*********************************Début de la Main*********************************//
	public static void main (String [] args){
		FenetreClient fenetre = new FenetreClient();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		Rectangle tailleEcran = ge.getMaximumWindowBounds();
		
		fenetre.setSize(tailleEcran.getSize());
		fenetre.setVisible(true);
		
		FenetreEmploye fenetreEmploye = new FenetreEmploye();
		fenetreEmploye.setSize(tailleEcran.getSize());
		fenetreEmploye.setVisible(true);
	}

}
