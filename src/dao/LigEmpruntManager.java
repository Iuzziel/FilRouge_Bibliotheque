package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LigEmpruntManager {

	/**
	 * Retourne une ligne d'un emprunt a partir de son numero.
	 * Obsolete avece le EmpruntManager.setEmprunt
	 * @param 
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int setLigEmprunt(LigEmprunt ligEmprunt) 
			throws ClassNotFoundException, SQLException{

		String sql = "INSERT INTO ligEmprunt (num_emprunt, num_exemplaire, num_etat) VALUES (?, ?, ?)";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, ligEmprunt.getNum_emprunt());
		stm.setInt(2, ligEmprunt.getNum_exemplaire());
		stm.setInt(3, ligEmprunt.getNum_etat());

		int res = stm.executeUpdate();

		stm.close();	
		return res;
	}

	/**
	 * Retourne par un string "Oui" "Non" si un exemplaire est disponible.
	 * Voir aussi getNbDisp() getNbDispBiblio().
	 * @param 
	 * @return String
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static String getDisp(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		String sql = "SELECT COUNT(*) \"bool\" "
				+ "FROM ligEmprunt "
				+ "JOIN EMPRUNT "
				+ "ON EMPRUNT.num_emprunt = ligEmprunt.num_emprunt "
				+ "WHERE num_exemplaire = ? "
				+ "AND EMPRUNT.emp_date_ret IS NULL";

		PreparedStatement stm = 
				ConnectionManager.getConnection().prepareStatement(sql);

		stm.setInt(1, exemplaire.getNum_exemplaire());

		ResultSet rs = stm.executeQuery();
		rs.next();
		int res = rs.getInt("bool");
		rs.close();
		stm.close();
		if(res == 0) return "Oui";
		return "Non";
	}

	/**
	 * Retourne le nombre d'exemplaire indisponible du meme livre.
	 * Voir aussi getNbIndispBiblio().
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getNbIndisp(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		int tmp = 0;

		String sql = "SELECT COUNT(*) \"nbdisp\" "
				+ "FROM EXEMPLAIRE e "
				+ "JOIN LIGEMPRUNT l "
				+ "ON e.NUM_EXEMPLAIRE = l.NUM_EXEMPLAIRE "
				+ "JOIN EMPRUNT emp "
				+ "ON l.NUM_EMPRUNT = EMP.NUM_EMPRUNT "
				+ "WHERE e.num_livre = ? "
				+ "AND emp.emp_date_ret IS NULL";

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
	 * Retourne le nombre d'exemplaire de ce livre indispo dans cette bibliotheque.
	 * Voir aussi getNbIndisp().
	 * @param 
	 * @return int
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static int getNbIndispBiblio(Exemplaire exemplaire) 
			throws ClassNotFoundException, SQLException{

		int tmp = 0;

		String sql = "SELECT COUNT(*) \"nbdisp\" "
				+ "FROM EXEMPLAIRE "
				+ "JOIN LIGEMPRUNT "
				+ "ON LIGEMPRUNT.num_exemplaire = EXEMPLAIRE.num_exemplaire "
				+ "JOIN EMPRUNT "
				+ "ON EMPRUNT.num_emprunt = LIGEMPRUNT.num_emprunt "
				+ "WHERE EXEMPLAIRE.num_livre = ? "
				+ "AND EXEMPLAIRE.num_biblio = ?"
				+ "AND EMPRUNT.EMP_DATE_RET IS NULL";

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
}