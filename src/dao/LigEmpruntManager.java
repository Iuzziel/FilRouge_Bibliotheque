package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LigEmpruntManager {

	/**
	 * Retourne une ligne d'un emprunt a partir de son numero.
	 * Obsolete avece le EmpruntManager.setEmprunt
	 * @param 
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int setLigEmprunt(LigEmprunt ligEmprunt) 
			throws ClassNotFoundException, SQLException{

		String sql = "INSERT INTO ligEmprunt (num_emprunt, num_exemplaire, num_etat) VALUES (?, ?, ?)";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, ligEmprunt.getNum_emprunt());
		stm.setInt(2, ligEmprunt.getNum_exemplaire());
		stm.setInt(3, ligEmprunt.getNum_etat());

		int res = stm.executeUpdate();

		stm.close();	
		return res;
	}

	/**
	 * Retourne une ligne d'un emprunt a partir de son numero.
	 * Obsolete avece le EmpruntManager.setEmprunt
	 * @param 
	 * @return 
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getDisp(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		String sql = "SELECT COUNT(*) \"bool\" "
				+ "FROM ligEmprunt "
				+ "JOIN EMPRUNT "
				+ "ON EMPRUNT.num_emprunt = ligEmprunt.num_emprunt "
				+ "WHERE num_exemplaire = ? "
				+ "AND EMPRUNT.emp_date_ret IS NULL";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, exemplaire.getNum_exemplaire());

		ResultSet rs = stm.executeQuery();
		rs.next();
		int res = rs.getInt("bool");
		stm.close();
		if(res == 0) return "Oui";
		return "Non";
	}
}