package dao;

import java.util.Vector;

public class Auteur {
	private int num_auteur;
	private String auteurNom;
	private String auteurPrenom;

	public Auteur (int num_auteur){
		this.num_auteur = num_auteur;
	}

	public Auteur (int num_auteur, String auteurNom, String auteurPrenom){
		this.num_auteur = num_auteur;
		this.auteurNom = auteurNom;
		this.auteurPrenom = auteurPrenom;
	}

	public String toString(){
		return num_auteur + " " + auteurNom + " " + auteurPrenom;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_auteur));
		v.add(auteurNom);
		v.add(auteurPrenom);
		return v;
	}

	public int getNum_auteur() {
		return num_auteur;
	}

	public void setNum_auteur(int num_auteur) {
		this.num_auteur = num_auteur;
	}

	public String getAuteurNom() {
		return auteurNom;
	}

	public void setAuteurNom(String auteurNom) {
		this.auteurNom = auteurNom;
	}

	public String getAuteurPrenom() {
		return auteurPrenom;
	}

	public void setAuteurPrenom(String auteurPrenom) {
		this.auteurPrenom = auteurPrenom;
	}
}