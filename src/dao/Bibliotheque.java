package dao;

import java.util.Vector;

public class Bibliotheque {
	private int num_biblio;
	private String bibliotheque;
	private int num_muni;


	public Bibliotheque(int num_biblio){
		this.num_biblio = num_biblio;
	}

	public Bibliotheque (int num_biblio, String bibliotheque, int num_muni){
		this.num_biblio = num_biblio;
		this.bibliotheque = bibliotheque;
		this.num_muni = num_muni;
	}

	public String toString(){
		return num_biblio + " " + bibliotheque + " " + num_muni;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_biblio));
		v.add(bibliotheque);
		v.add(String.valueOf(num_muni));
		return v;
	}
	
	
	public int getNum_biblio() {
		return num_biblio;
	}
	public void setNum_biblio(int num_biblio) {
		this.num_biblio = num_biblio;
	}
	public String getBibliotheque() {
		return bibliotheque;
	}
	public void setBibliotheque(String bibliotheque) {
		this.bibliotheque = bibliotheque;
	}
	public int getNum_muni() {
		return num_muni;
	}
	public void setNum_muni(int num_muni) {
		this.num_muni = num_muni;
	}
}
