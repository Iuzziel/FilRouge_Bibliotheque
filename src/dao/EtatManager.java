package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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

	/**
	 * Retourne le numero d'indice max d'etat.
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getMaxEtat() 
			throws ClassNotFoundException, SQLException{

		int tmp = 0;

		String sql = "SELECT MAX(num_etat) \"max\" FROM etat";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		ResultSet rs = stm.executeQuery();

		if(rs.next()){
			tmp = rs.getInt("max");
		}

		rs.close();
		stm.close();	
		return tmp;
	}

	/**
	 * Retourne un Vector<String> avec tout les etats possible contenu dans la BDD.
	 * @return Vector<String>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Vector<String> getVectorAllEtat() 
			throws ClassNotFoundException, SQLException{

		Vector<String> v = new Vector<String>();

		String sql = "SELECT etat FROM etat";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		ResultSet rs = stm.executeQuery();

		while(rs.next()) {
			v.add(rs.getString("etat"));
		}

		rs.close();
		stm.close();	

		return v;
	}
}
