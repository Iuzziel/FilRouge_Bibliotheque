package dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Exemplaire {
	private int num_exemplaire;
	private int num_livre;
	private int num_biblio;
	private int num_etat;
	private int num_emplacement;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Exemplaire (int num_exemplaire){
		this.num_exemplaire = num_exemplaire;
	}

	public Exemplaire (int num_exemplaire, int num_livre, int num_biblio, int num_etat, int num_emplacement){
		this.num_exemplaire = num_exemplaire;
		this.num_livre = num_livre;
		this.num_biblio = num_biblio;
		this.num_etat = num_etat;
		this.num_emplacement = num_emplacement;

	}

	public String toString(){
		return num_exemplaire + " " + num_livre + " " + num_biblio + " " + num_etat + " " + num_emplacement;
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
			Date today = new Date(); 
			Calendar c = Calendar.getInstance();
			c.setTime(EmpruntManager.getEmprunt(new Emprunt(EmpruntManager.getEmpFromExemp(this))).getEmp_date_emp());
			c.add(Calendar.DAY_OF_YEAR, ParametreManager.getParametre(new Parametre("nbjouremprunt")).getValeur());
			Date tempDate = c.getTime();

			liv = LivreManager.getLivreInfo(this);
			aut = AuteurManager.getAuteur(new Auteur(liv.getNum_auteur()));


			v.add(String.valueOf(num_exemplaire));
			v.add(liv.getTitre());
			v.add(aut.toString());
			v.add(sdf.format(EmpruntManager.getEmprunt(new Emprunt(EmpruntManager.getEmpFromExemp(this))).getEmp_date_emp()));
			v.add(sdf.format(tempDate));
			if(today.compareTo(tempDate) > 0){//affichage de l'amande par livre.				
				long temp = ((today.getTime() - c.getTimeInMillis()) / 1000 / 60 / 60 / 24) 
						* ParametreManager.getParametre(new Parametre("pxamande")).getValeur();
				v.add(temp + "€");
			}else{
				v.add("Aucune");
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:dao-Class:Exemplaire-Tag:1");
			e.printStackTrace();
		}
		return v;
	}

	public int getNum_exemplaire() {
		return num_exemplaire;
	}
	public int getNum_livre() {
		return num_livre;
	}
	public int getNum_biblio() {
		return num_biblio;
	}
	public int getNum_etat() {
		return num_etat;
	}
	public int getNum_emplacement() {
		return num_emplacement;
	}

}