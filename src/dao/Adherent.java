package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Adherent {
	private String num_adherent;
	private String adherNom;
	private String adherPrenom;
	private String adherAdresse;
	private Date adherDateNaiss;
	private Date adherDateCoti;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Adherent (String num_adherent){
		this.num_adherent = num_adherent;
	}

	public Adherent (String num_adherent, String adherNom, String adherPrenom, String adherAdresse, Date adherDateNaiss, Date adherDateCoti){
		this.num_adherent = num_adherent;
		this.adherNom = adherNom;
		this.adherPrenom = adherPrenom;
		this.adherAdresse = adherAdresse;
		this.adherDateNaiss = adherDateNaiss;
		this.adherDateCoti = adherDateCoti;
	}

	public String toString(){
		return num_adherent + " " + adherNom + " " 
				+ adherPrenom + " " + adherAdresse + " " + sdf.format(adherDateNaiss) 
				+ " " + sdf.format(adherDateCoti);
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_adherent));
		v.add(String.valueOf(adherNom));
		v.add(String.valueOf(adherPrenom));
		v.add(adherAdresse);
		v.add(sdf.format(adherDateNaiss));
		v.add(sdf.format(adherDateCoti));
		return v;
	}

	public String getNum_adherent() {
		return num_adherent;
	}

	public void setNum_adherent(String num_adherent) {
		this.num_adherent = num_adherent;
	}

	public String getAdherNom() {
		return adherNom;
	}

	public void setAdherNom(String adherNom) {
		this.adherNom = adherNom;
	}

	public String getAdherPrenom() {
		return adherPrenom;
	}

	public void setAdherPrenom(String adherPrenom) {
		this.adherPrenom = adherPrenom;
	}

	public String getAdherAdresse() {
		return adherAdresse;
	}

	public void setAdherAdresse(String adherAdresse) {
		this.adherAdresse = adherAdresse;
	}

	public Date getAdherDateNaiss() {
		return adherDateNaiss;
	}

	public void setAdherDateNaiss(Date adherDateNaiss) {
		this.adherDateNaiss = adherDateNaiss;
	}

	public Date getAdherDateCoti() {
		return adherDateCoti;
	}

	public void setAdherDateCoti(Date adherDateCoti) {
		this.adherDateCoti = adherDateCoti;
	}

}