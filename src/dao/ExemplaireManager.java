package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExemplaireManager {

	/**
	 * Retourne un livre a partir de son numero
	 * @param 
	 * @return Livre
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Exemplaire getExemplaire(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		Exemplaire tmp = null;

		String sql = "SELECT * FROM exemplaire WHERE num_exemplaire = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, exemplaire.getNum_exemplaire());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Exemplaire(rs.getInt("num_exemplaire"),
					rs.getInt("num_livre"),
					rs.getInt("num_biblio"),
					rs.getInt("num_etat"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}

}