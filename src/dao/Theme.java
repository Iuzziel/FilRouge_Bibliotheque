package dao;

import java.util.Vector;

public class Theme {
	private int num_theme;
	private String theme;

	public Theme (int num_theme){
		this.num_theme = num_theme;
	}

	public Theme (int num_theme, String theme){
		this.num_theme = num_theme;
		this.theme = theme;
	}

	public String toString(){
		return num_theme + " " + theme;
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_theme));
		v.add(theme);
		return v;
	}

	public int getNum_theme() {
		return num_theme;
	}
	public void setNum_theme(int num_theme) {
		this.num_theme = num_theme;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
}
