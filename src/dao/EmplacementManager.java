package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmplacementManager {
	/**
	 * Retourne un emprunt a partir de son numero
	 * @param 
	 * @return Emprunt
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Emplacement getEmplacement(Emplacement emplacement) 
			throws ClassNotFoundException, SQLException{

		Emplacement tmp = null;

		String sql = "SELECT * FROM emplacement WHERE num_emplacement = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, emplacement.getNum_emplacement());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Emplacement(rs.getInt("num_emplacement"),
					rs.getInt("num_theme"),
					rs.getInt("num_biblio"),
					rs.getString("emplacement"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}
}
