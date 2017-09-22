package dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee {
	private int num_employee;
	private String empNom;
	private String empPrenom;
	private String empAdresse;
	private Date empDateNaiss; 	
	private int num_biblio;
	private String roles;
	private String password;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Employee(String empNom){
		this.empNom = empNom;
	}
	
	public Employee(int num_employee, String empNom, String empPrenom, 
			String empAdresse, Date empDateNaiss, int num_biblio, String roles, String password){
		this.num_employee = num_employee;
		this.empNom = empNom;
		this.empPrenom = empPrenom;
		this.empAdresse = empAdresse;
		this.empDateNaiss = empDateNaiss;
		this.num_biblio = num_biblio;
		this.roles = roles;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [num_employee=" + num_employee + ", empNom=" + empNom + ", empPrenom=" + empPrenom
				+ ", empAdresse=" + empAdresse + ", empDateNaiss=" + empDateNaiss + ", num_biblio=" + num_biblio
				+ ", roles=" + roles + ", password=" + password + "]";
	}

	public String getEmpNom() {
		return empNom;
	}
	public int getNum_employee() {
		return num_employee;
	}
	public String getEmpPrenom() {
		return empPrenom;
	}
	public String getEmpAdresse() {
		return empAdresse;
	}
	public Date getEmpDateNaiss() {
		return empDateNaiss;
	}
	public int getNum_biblio() {
		return num_biblio;
	}
	public String getRoles() {
		return roles;
	}
	public String getPassword() {
		return password;
	}
	public SimpleDateFormat getSdf() {
		return sdf;
	}
}
