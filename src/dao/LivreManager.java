package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LivreManager {

	/**
	 * Retourne un livre a partir de son numero
	 * @param 
	 * @return Livre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Livre getLivre(Livre livre) 
			throws ClassNotFoundException, SQLException{

		Livre tmp = null;

		String sql = "SELECT * FROM livre WHERE num_livre = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, livre.getNum_livre());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Livre(rs.getInt("num_livre"),
					rs.getString("titre"),
					rs.getString("isbn"),
					rs.getString("issn"),
					rs.getString("livre_comment"),
					rs.getInt("num_auteur"),
					rs.getInt("num_theme"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}

	/**
	 * Retourne un livre a partir d'un numero exemplaire
	 * @param 
	 * @return Livre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Livre getLivreInfo (Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		Livre tmp = null;

		String sql = "SELECT * FROM livre WHERE num_livre = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, exemplaire.getNum_livre());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Livre(rs.getInt("num_livre"),
					rs.getString("titre"),
					rs.getString("isbn"),
					rs.getString("issn"),
					rs.getString("livre_comment"),
					rs.getInt("num_auteur"),
					rs.getInt("num_theme"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}

}