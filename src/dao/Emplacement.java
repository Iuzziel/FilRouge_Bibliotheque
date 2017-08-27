package dao;

import java.util.Vector;

public class Emplacement {

	private int num_emplacement;
	private int num_theme;
	private int num_biblio;
	private String emplacement;

	public Emplacement (int num_emplacement){
		this.num_emplacement = num_emplacement;
	}

	public Emplacement (int num_emplacement, int num_theme, int num_biblio, String emplacement){
		this(num_emplacement);
		this.num_theme = num_theme;
		this.num_biblio = num_biblio;
		this.emplacement = emplacement;
	}

	public String toString(){
		return num_emplacement + " " + num_theme + " " + num_biblio+ " " + emplacement;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_emplacement));
		v.add(String.valueOf(num_theme));
		v.add(String.valueOf(num_biblio));
		v.add(emplacement);

		return v;
	}

	public int getNum_emplacement() {
		return num_emplacement;
	}
	public int getNum_theme() {
		return num_theme;
	}
	public int getNum_biblio() {
		return num_biblio;
	}
	public String getEmplacement() {
		return emplacement;
	}
}