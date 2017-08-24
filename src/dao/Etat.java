package dao;

import java.util.Vector;

public class Etat {
	private int num_etat;
	private String etat;


	public Etat (int num_etat){
		this.num_etat = num_etat;
	}
	public Etat (int num_etat, String etat){
		this.num_etat = num_etat;
		this.etat = etat;		
	}

	public String toString(){
		return num_etat + " " + etat;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_etat));
		v.add(etat);
		return v;
	}

	public int getNum_etat() {
		return num_etat;
	}
	public void setNum_etat(int num_etat) {
		this.num_etat = num_etat;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
}
