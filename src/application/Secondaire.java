package application;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import fenetres.FenetreEmploye;

public class Secondaire extends Thread {
	public Secondaire(String name){
		super(name);
	}
	public void run(){
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		Rectangle tailleEcran = ge.getMaximumWindowBounds();
		FenetreEmploye fenetreEmploye = new FenetreEmploye();
		fenetreEmploye.setSize(tailleEcran.getSize());
		fenetreEmploye.setVisible(true);
		fenetreEmploye.setAlwaysOnTop(true);
	}
}
