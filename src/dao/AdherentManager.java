package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdherentManager {

	/**
	 * Retourne un adherent a partir de son numero
	 * @param 
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Adherent getAdherent(Adherent adherent) 
			throws ClassNotFoundException, SQLException{

		Adherent tmp = null;

		String sql = "SELECT * FROM adherent WHERE num_adherent = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, adherent.getNum_adherent());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Adherent(rs.getInt("num_adherent"),
					rs.getString("adherNom"),
					rs.getString("adherPrenom"),
					rs.getString("adherAdresse"),
					rs.getDate("adherDateNaiss"),
					rs.getDate("adherDateCoti"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}
	
	/**
	 * Update la date de coti d'un adherent a partir de son numero.
	 * @param num_adherent
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int updateAdheCoti(int num_adherent) 
			throws ClassNotFoundException, SQLException{

		String sql = "UPDATE adherent SET adherDateCoti = SYSDATE WHERE num_adherent = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, num_adherent);

		int res = stm.executeUpdate();

		stm.close();	
		return res;
	}
}
