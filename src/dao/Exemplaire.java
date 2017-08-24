package dao;

import java.sql.SQLException;
import java.util.Vector;

public class Exemplaire {
	private int num_exemplaire;
	private int num_livre;
	private int num_biblio;
	private int num_etat;

	public Exemplaire (int num_exemplaire){
		this.num_exemplaire = num_exemplaire;
	}

	public Exemplaire (int num_exemplaire, int num_livre, int num_biblio, int num_etat){
		this.num_exemplaire = num_exemplaire;
		this.num_livre = num_livre;
		this.num_biblio = num_biblio;
		this.num_etat = num_etat;

	}

	public String toString(){
		return num_exemplaire + " " + num_livre + " " + num_biblio + " " + num_etat;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_exemplaire));
		v.add(String.valueOf(num_livre));
		v.add(String.valueOf(num_biblio));
		v.add(String.valueOf(num_etat));;
		return v;
	}

	public Vector<String> toInfoVector(){
		Livre liv;
		Auteur aut;
		Bibliotheque bibli;
		Etat eta;
		Vector<String> v = new Vector<String>();
		try {
			liv = LivreManager.getLivreInfo(this);
			aut = AuteurManager.getAuteur(new Auteur(liv.getNum_auteur()));
			bibli = BibliothequeManager.getBibliotheque(new Bibliotheque(num_biblio));
			eta = EtatManager.getEtat(new Etat(this.num_etat));
			v.add(String.valueOf(num_exemplaire));
			v.add(String.valueOf(liv.getTitre()));
			v.add(String.valueOf(aut.toString()));
			v.add(String.valueOf(bibli.getBibliotheque()));
			v.add(String.valueOf(eta.getEtat()));;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:dao-Class:Exemplaire-Tag:1");
			e.printStackTrace();
		}
		return v;
	}
	
	public Vector<String> toEmpRetVector(){
		Livre liv;
		Auteur aut;
		Bibliotheque bibli;
		Vector<String> v = new Vector<String>();
		try {
			liv = LivreManager.getLivreInfo(this);
			aut = AuteurManager.getAuteur(new Auteur(liv.getNum_auteur()));
			bibli = BibliothequeManager.getBibliotheque(new Bibliotheque(num_biblio));
			v.add(String.valueOf(num_exemplaire));
			v.add(String.valueOf(liv.getTitre()));
			v.add(String.valueOf(aut.toString()));
			v.add(String.valueOf(bibli.getBibliotheque()));
			v.add(String.valueOf(LigEmpruntManager.getDisp(this)));
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:dao-Class:Exemplaire-Tag:1");
			e.printStackTrace();
		}
		return v;
	}

	public Vector<String> toAdherEmpVector(){
		Livre liv;
		Auteur aut;
		Vector<String> v = new Vector<String>();
		try {
			liv = LivreManager.getLivreInfo(this);
			aut = AuteurManager.getAuteur(new Auteur(liv.getNum_auteur()));
			v.add(String.valueOf(num_exemplaire));
			v.add(liv.getTitre());
			v.add(aut.toString());
			v.add("date emprunt");
			v.add("date retour prevu");
			v.add("amande");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:dao-Class:Exemplaire-Tag:1");
			e.printStackTrace();
		}
		return v;
	}
	
	public int getNum_exemplaire() {
		return num_exemplaire;
	}

	public void setNum_exemplaire(int num_exemplaire) {
		this.num_exemplaire = num_exemplaire;
	}

	public int getNum_livre() {
		return num_livre;
	}

	public void setNum_livre(int num_livre) {
		this.num_livre = num_livre;
	}

	public int getNum_biblio() {
		return num_biblio;
	}

	public void setNum_biblio(int num_biblio) {
		this.num_biblio = num_biblio;
	}

	public int getNum_etat() {
		return num_etat;
	}

	public void setNum_etat(int num_etat) {
		this.num_etat = num_etat;
	}

}