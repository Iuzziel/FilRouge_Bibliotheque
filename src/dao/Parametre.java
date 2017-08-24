package dao;

import java.util.Vector;

public class Parametre {
	private String parametre;
	private int valeur;
	
	public Parametre (String parametre){
		this.parametre = parametre;
	}
	
	public Parametre (String parametre, int valeur){
		this.parametre = parametre;
		this.valeur = valeur;
	}

	public String toString(){
		return parametre + " " + valeur;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(parametre);
		v.add(String.valueOf(valeur));
		return v;
	}

	public int getValeur() {
		return valeur;
	}
	public void setValeur(int valeur) {
		this.valeur = valeur;
	}
	public String getParametre() {
		return parametre;
	}
}
