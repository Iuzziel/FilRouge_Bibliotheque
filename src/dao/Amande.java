package dao;

public class Amande {
	private int num_amande;
	private int num_emprunt;
	private int montant;
	
	public Amande(int num_emprunt, int montant){
		this.num_emprunt = num_emprunt;
		this.montant = montant;
	}
	
	public Amande(int num_amande, int num_emprunt, int montant){
		this.num_amande = num_amande;
		this.num_emprunt = num_emprunt;
		this.montant = montant;
	}

	public int getNum_amande() {
		return num_amande;
	}
	public int getNum_emprunt() {
		return num_emprunt;
	}
	public int getMontant() {
		return montant;
	}
}
