package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AmandeManager {
	
	/**
	 * Insert un montant et un num_emprunt dans la table amande. Retourne 1 si l'operation est bonne.
	 * @param amande
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int insertAmande(Amande amande) 
			throws ClassNotFoundException, SQLException{

		String sql = "INSERT INTO amande (num_amande, num_emprunt, montant) VALUES (SEQ_AMANDE_num_amande.NEXTVAL, ?, ?)";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, amande.getNum_emprunt());
		stm.setInt(2, amande.getMontant());

		int res = stm.executeUpdate();

		stm.close();	
		return res;
	}
	
	/**
	 * Retourne si l'amande a deja ete payee, en booleen.
	 * @param num_emprunt
	 * @return boolean true si deja paye.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static boolean getIfAmandEmpPayee(int num_emprunt) 
			throws ClassNotFoundException, SQLException{

		boolean temp = false;
		
		String sql = "SELECT COUNT(*) \"exist\" FROM amande WHERE num_emprunt = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, num_emprunt);

		ResultSet res = stm.executeQuery();

		if(res.next()){
			int i = res.getInt("exist");
			if((i > 0) ? (temp = true) : (temp = false));
		}
		
		res.close();
		stm.close();	
		return temp;
	}
}
