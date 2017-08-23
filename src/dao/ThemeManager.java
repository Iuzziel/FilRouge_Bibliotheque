package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeManager {

	/**
	 * Retourne un Theme a partir de son numero
	 * @param 
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */

	public static Theme getTheme(Theme theme) 
			throws ClassNotFoundException, SQLException{

		Theme tmp = null;

		String sql = "SELECT * FROM theme WHERE num_theme = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, theme.getNum_theme());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp = new Theme(rs.getInt("num_theme"),
					rs.getString("theme"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}
}
