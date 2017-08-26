package application;

import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.UIManager;
import fenetres.*;

public class Principale {
	//Donnees static de l'application
	public static FenetrePrincipale fenetrePrincipale;

	//*********************************Debut de la Main*********************************//
	public static void main (String [] args){
		//Recuperation de l'ui systeme hote
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			System.out.println("Pkg:application-Class:Principale\nEchec du chargement lookAndFeel systeme");
		}
		fenetrePrincipale = new FenetrePrincipale();

		//recuperation de la resolution de la taille de l'affichage
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		Rectangle tailleEcran = ge.getMaximumWindowBounds();
		fenetrePrincipale.setSize(tailleEcran.getSize());
		
		//Passage en maximise
		fenetrePrincipale.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		fenetrePrincipale.setVisible(true);
		//fenetrePrincipale.setAlwaysOnTop(true);
	}
}
