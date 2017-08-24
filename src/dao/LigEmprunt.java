package dao;

import java.sql.SQLException;
import java.util.Vector;

public class LigEmprunt {
	private int num_emprunt;
	private int num_exemplaire;
	private int num_etat;
	
	public LigEmprunt (int num_emprunt, int num_exemplaire){
		this.num_emprunt = num_emprunt;
		this.num_exemplaire = num_exemplaire;
		try {
			this.num_etat = (ExemplaireManager.getExemplaire(new Exemplaire(num_exemplaire)).getNum_etat());
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Pkg:dao-Class:LigEmprunt-Tag:1");
			e.printStackTrace();
		}
	}

	public String toString(){
		return num_emprunt + " " + num_exemplaire + " " + num_etat;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_emprunt));
		v.add(String.valueOf(num_exemplaire));
		v.add(String.valueOf(num_etat));
		return v;
	}

	public int getNum_emprunt() {
		return num_emprunt;
	}
	public int getNum_exemplaire() {
		return num_exemplaire;
	}
	public int getNum_etat() {
		return num_etat;
	}
}
