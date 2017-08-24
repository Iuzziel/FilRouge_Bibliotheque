package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BibliothequeManager {

	/**
	 * Retourne une bibliotheque a partir de son numero
	 * @param 
	 * @return Emprunt
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Bibliotheque getBibliotheque(Bibliotheque bibliotheque) 
			throws ClassNotFoundException, SQLException{

		Bibliotheque tmp = null;

		String sql = "SELECT * FROM bibliotheque WHERE num_biblio = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, bibliotheque.getNum_biblio());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Bibliotheque(rs.getInt("num_biblio"),
					rs.getString("bibliotheque"),
					rs.getInt("num_muni"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}

}
