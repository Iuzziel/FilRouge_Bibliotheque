package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EtatManager {

	/**
	 * Retourne un etat a partir de son numero
	 * @param 
	 * @return etat
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Etat getEtat(Etat etat) 
			throws ClassNotFoundException, SQLException{

		Etat tmp = null;

		String sql = "SELECT * FROM etat WHERE num_etat = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, etat.getNum_etat());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Etat(rs.getInt("num_etat"),
					rs.getString("etat"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}
}
