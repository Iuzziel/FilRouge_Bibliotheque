package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuteurManager {

	/**
	 * Retourne un Auteur a partir de son numero
	 * @param 
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static Auteur getAuteur(Auteur auteur) 
			throws ClassNotFoundException, SQLException{

		Auteur tmp = null;

		String sql = "SELECT * FROM auteur WHERE num_auteur = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, auteur.getNum_auteur());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Auteur(rs.getInt("num_auteur"),
					rs.getString("auteurNom"),
					rs.getString("auteurPrenom"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}
}
