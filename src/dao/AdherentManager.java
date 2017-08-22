package dao;

import java.sql.*;

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

		Adherent tmp=null;

		String sql = "SELECT * FROM adherent WHERE num_adherent = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setString(1, adherent.getNum_adherent());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Adherent(rs.getString("num_adherent"),
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
}
