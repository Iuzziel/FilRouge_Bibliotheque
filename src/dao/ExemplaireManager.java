package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

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
					rs.getInt("num_etat"),
					rs.getInt("num_emplacement"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}
	
	/**
	 * Retourne le nombre d'exemplaire d'un meme livre, dans TOUTE les bibliotheques, a partir de son num_livre.
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getNbDisp(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		int tmp = 0;

		String sql = "SELECT COUNT(*) \"nbdisp\" "
				+ "FROM EXEMPLAIRE "
				+ "WHERE EXEMPLAIRE.num_livre = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, exemplaire.getNum_livre());

		ResultSet rs = stm.executeQuery();

		rs.next();
		tmp = rs.getInt("nbdisp");

		rs.close();
		stm.close();
		return tmp;
	}
	
	/**
	 * Retourne le nombre d'exemplaire d'un meme livre, dans LA bibliotheques de l'exemplaire en parametre.
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getNbDispBiblio(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		int tmp = 0;

		String sql = "SELECT COUNT(*) \"nbdisp\" "
				+ "FROM EXEMPLAIRE e "
				+ "WHERE e.num_livre = ? "
				+ "AND e.num_biblio = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, exemplaire.getNum_livre());
		stm.setInt(2, exemplaire.getNum_biblio());

		ResultSet rs = stm.executeQuery();

		rs.next();
		tmp = rs.getInt("nbdisp");

		rs.close();
		stm.close();
		return tmp;
	}
	
	/**
	 * Retourne la liste des num_exemplaire d'un meme livre, a partir de son num_livre
	 * @param 
	 * @return Vector<Integer>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Vector<Integer> getAllExemplaire(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		Vector<Integer> tmp = new Vector<Integer>();

		String sql = "SELECT e.num_exemplaire "
				+ "FROM EXEMPLAIRE e "
				+ "WHERE e.num_livre = ?";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, exemplaire.getNum_livre());

		ResultSet rs = stm.executeQuery();

		if(rs.next())
		{
			tmp.add(rs.getInt("num_exemplaire"));
		}

		rs.close();
		stm.close();	
		return tmp;
	}

}