package dao;

import java.text.SimpleDateFormat;
import java.util.Vector;
import java.sql.Date;

public class Emprunt {

	private int num_emprunt;
	private int num_adherent;
	private Date emp_date_emp;
	private Date emp_date_ret;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Emprunt (int num_emprunt){
		this.num_emprunt = num_emprunt;
	}

	public Emprunt (int num_emprunt, int num_adherent, Date emp_date_emp){
		this(num_emprunt);
		this.num_adherent = num_adherent;
		this.emp_date_emp = emp_date_emp;
	}
	
	public Emprunt (int num_emprunt, int num_adherent, Date emp_date_emp, Date emp_date_ret){
		this(num_emprunt);
		this.num_adherent = num_adherent;
		this.emp_date_emp = emp_date_emp;
		this.emp_date_ret = emp_date_ret;
	}

	public String toString(){
		return num_emprunt + " " + num_adherent + " " + sdf.format(emp_date_emp)+ " " + sdf.format(emp_date_ret);
	}

	public Vector<String> toVector(){
		Vector<String> v = new Vector<String>();
		v.add(String.valueOf(num_emprunt));
		v.add(String.valueOf(num_adherent));
		v.add(sdf.format(emp_date_emp));
		v.add(sdf.format(emp_date_ret));
		return v;
	}

	public int getNum_emprunt() {
		return num_emprunt;
	}
	public int getNum_adherent() {
		return num_adherent;
	}
	public Date getEmp_date_emp() {
		return emp_date_emp;
	}
	public Date getEmp_date_ret() {
		return emp_date_ret;
	}	
}