package application;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import fenetres.*;

public class Principale {
	//*********************************Début de la Main*********************************//
	public static void main (String [] args){
		FenetreClient fenetre = new FenetreClient();
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		Rectangle rec = ge.getMaximumWindowBounds();
		
		fenetre.setSize(rec.getSize());
		
		fenetre.setVisible(true);
		/*FenetreEmploye fenetreEmploye = new FenetreEmploye();
		fenetreEmploye.setVisible(true);*/
	}

}
