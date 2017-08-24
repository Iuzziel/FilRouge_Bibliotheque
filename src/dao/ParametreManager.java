package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ParametreManager {

	/**
	 * Retourne une valeure de parametre a partir de son nom.
	 * ex : pxamande = ParametreManager.getParametre(new Parametre("pxamande"));
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Parametre getParametre(Parametre parametre) 
			throws ClassNotFoundException, SQLException{

		Parametre tmp = null;

		String sql = "SELECT * FROM parametre WHERE parametre LIKE ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setString(1, parametre.getParametre());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Parametre(rs.getString("parametre"),
					rs.getInt("valeur"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}
}