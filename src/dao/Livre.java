package dao;

import java.util.Vector;

public class Livre {
	private int num_livre;
	private String titre;
	private String ISBN;
	private String ISSN;
	private String livre_comment;
	private int num_auteur;
	private int num_theme;

	public Livre(int num_livre){
		this.num_livre = num_livre;
	}

	public Livre (int num_livre, String titre, String ISBN, String ISSN, String livre_comment, int num_auteur, int num_theme){
		this.num_livre = num_livre;
		this.titre = titre;
		this.ISBN = ISBN;
		this.ISSN = ISSN;
		this.livre_comment = livre_comment;
		this.num_auteur = num_auteur;
		this.num_theme = num_theme;
	}

	public String toString(){
		return num_livre + " " + titre + " " + ISBN + " " + ISSN + " " + livre_comment + " " + num_auteur + " " + num_theme;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_livre));
		v.add(titre);
		v.add(ISBN);
		v.add(ISSN);
		v.add(livre_comment);
		v.add(String.valueOf(num_auteur));
		v.add(String.valueOf(num_theme));
		return v;
	}

	public int getNum_livre() {
		return num_livre;
	}

	public void setNum_livre(int num_livre) {
		this.num_livre = num_livre;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getISSN() {
		return ISSN;
	}

	public void setISSN(String iSSN) {
		ISSN = iSSN;
	}

	public String getLivre_comment() {
		return livre_comment;
	}

	public void setLivre_comment(String livre_comment) {
		this.livre_comment = livre_comment;
	}

	public int getNum_auteur() {
		return num_auteur;
	}

	public void setNum_auteur(int num_auteur) {
		this.num_auteur = num_auteur;
	}

	public int getNum_theme() {
		return num_theme;
	}

	public void setNum_theme(int num_theme) {
		this.num_theme = num_theme;
	}
}